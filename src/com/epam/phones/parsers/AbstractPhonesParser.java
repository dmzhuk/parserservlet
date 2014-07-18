package com.epam.phones.parsers;

import com.epam.phones.entity.Phone;
import com.epam.phones.exception.ParseException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Dmitry on 13.07.2014.
 */
public abstract class AbstractPhonesParser {
    public abstract ArrayList<Phone> parse( File file ) throws ParseException;
}
