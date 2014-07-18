package com.epam.phones.parsers;


import com.epam.phones.entity.Phone;
import com.epam.phones.exception.ParseException;
import com.epam.phones.exception.ParserCreationException;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by Dmitry on 11.07.2014.
 */

public class SaxPhonesParser extends AbstractPhonesParser {

    SaxPhonesHandler saxHandler;
    SAXParserFactory saxFactory;
    SAXParser saxParser;

    public SaxPhonesParser() throws ParserCreationException {
        try {
            saxHandler = new SaxPhonesHandler();
            saxFactory = SAXParserFactory.newInstance();
            saxParser = saxFactory.newSAXParser();
        } catch (ParserConfigurationException e) {
            throw new ParserCreationException( e );
        } catch (SAXException e) {
            throw new ParserCreationException( e );
        }
    }
    public ArrayList<Phone> parse( File file) throws ParseException {
        try {
            saxParser.parse( file, saxHandler );
            return saxHandler.getPhones();
        } catch (SAXException e) {
            throw new ParseException(e);
        } catch (IOException e) {
            throw new ParseException(e);
        }

    }
}
