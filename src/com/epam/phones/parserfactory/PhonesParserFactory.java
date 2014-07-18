package com.epam.phones.parserfactory;

import com.epam.phones.exception.ParserCreationException;
import com.epam.phones.parsers.AbstractPhonesParser;
import com.epam.phones.parsers.DomPhonesParser;
import com.epam.phones.parsers.StaxPhonesParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by Dmitry on 13.07.2014.
 */
public class PhonesParserFactory {
    private enum ParserType {
        SAX, STAX, DOM
    }

    public AbstractPhonesParser newInstance( String s ) throws ParserCreationException {
        try {
            ParserType parserType = ParserType.valueOf(s.toUpperCase());
            switch (parserType) {
                case DOM:
                    return new DomPhonesParser();
                case STAX:
                    return new StaxPhonesParser();
                case SAX:
                    return new StaxPhonesParser();
                default:
                    throw new ParserCreationException();
            }
        } catch ( IllegalArgumentException e ){
            throw new ParserCreationException();
        }
    }
}
