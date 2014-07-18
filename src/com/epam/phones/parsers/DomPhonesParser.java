package com.epam.phones.parsers;


import com.epam.phones.entity.Phone;
import com.epam.phones.exception.ParseException;
import com.epam.phones.exception.ParserCreationException;
import com.epam.phones.parsetag.ParseTag;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Dmitry on 09.07.2014.
 */
public class DomPhonesParser extends AbstractPhonesParser {

    private DocumentBuilderFactory factory;
    private DocumentBuilder docBuilder;
    public DomPhonesParser() throws ParserCreationException {
        try {
            factory = DocumentBuilderFactory.newInstance();
            docBuilder = factory.newDocumentBuilder();
        } catch ( ParserConfigurationException e ){
            throw new ParserCreationException( e );
        }
    }

    public ArrayList<Phone> parse( File file ) throws ParseException {

        ArrayList<Phone> phones = new ArrayList<Phone>();
        phones.clear();
        try {
            Document doc = docBuilder.parse(file);
            NodeList nodeList = doc.getElementsByTagName(ParseTag.PHONE.getTagName());

            for (int i = 0; i < nodeList.getLength(); i++) {

                Phone phone = new Phone();

                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element elem = (Element) node;

                    phone.setName(elem.getAttribute(ParseTag.NAME.getTagName()));
                    phone.setSize(Double.parseDouble(elem.getAttribute(ParseTag.SIZE.getTagName())));
                    phone.setManuf(elem.getElementsByTagName(ParseTag.MANUFACTURER.getTagName()).item(0).getTextContent());
                    phone.setDisplay(elem.getElementsByTagName(ParseTag.DISPLAY_TYPE.getTagName()).item(0).getTextContent());
                    phone.setOs(elem.getElementsByTagName(ParseTag.OS.getTagName()).item(0).getTextContent());
                    phone.setSd(Phone.SDCard.valueOf(elem.getElementsByTagName(ParseTag.SD_TYPE.getTagName()).item(0).getTextContent()));
                    phone.setManufDate(Integer.parseInt(elem.getElementsByTagName(ParseTag.MANUFACTURE_DATE.getTagName()).item(0).getTextContent()));

                    phones.add(phone);
                }
            }
            return phones;
        } catch (SAXException e) {
            throw new ParseException( "Error during file parsing", e );
        } catch (IOException e) {
            throw new ParseException( "Error during file reading", e );
        }
    }

}
