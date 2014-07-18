package com.epam.parserservlet; /**
 * Created by Dmitry on 14.07.2014.
 */
import com.epam.phones.entity.Phone;
import com.epam.phones.exception.ParseException;
import com.epam.phones.exception.ParserCreationException;
import com.epam.phones.exception.ValidatorException;
import com.epam.phones.parserfactory.PhonesParserFactory;
import com.epam.phones.parsers.AbstractPhonesParser;
import com.epam.phones.validator.XsdValidator;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;

@WebServlet("/ParserServlet")
public class ParserServlet extends HttpServlet {
    final static String xmlFileName = "/WEB-INF/xml/phones.xml";
    final static String xsdFileName = "/WEB-INF/xml/schema.xsd";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            ServletContext context = getServletContext();
            String parseAction = request.getParameter("parseButton");
            ArrayList<Phone> phones;
            File xmlFile = new File(context.getResource(xmlFileName).getFile());
            if ( request.getParameter("validateCheckbox") != null ){
                File xsdFile = new File( context.getResource(xsdFileName).getFile());
                XsdValidator validator = new XsdValidator( xsdFile );
                validator.validate( xmlFile );
                request.setAttribute("validation", "Validation done" );
            } else {
                request.setAttribute("validation", "No validation" );
            }
            if ( request.getParameter("parseButton" )!= null) {

                AbstractPhonesParser parser = new PhonesParserFactory().newInstance(parseAction);
                //String filePart = request.getParameter("fileChooser");

                phones = parser.parse( xmlFile );
                request.setAttribute( "phones", phones );
                request.setAttribute( "parsingDone", true );
            }
        } catch (ParserCreationException e) {
            request.setAttribute("parsingDone", false );
        } catch (ParseException e) {
            request.setAttribute("parsingDone", false );
        } catch (ValidatorException e) {
            request.setAttribute( "validation", "Wrong file format" );
        }
        request.getRequestDispatcher("/jsp/result.jsp").forward(request, response);
    }
}
