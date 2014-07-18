package com.epam.phones.parsers;


import com.epam.phones.entity.Phone;
import com.epam.phones.exception.ParseException;
import com.epam.phones.parsetag.ParseTag;
import javax.xml.stream.XMLStreamException;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Dmitry on 09.07.2014.
 */
public class StaxPhonesParser extends AbstractPhonesParser {

    private XMLInputFactory xmlFactory;
    public StaxPhonesParser() {
        xmlFactory = XMLInputFactory.newFactory();
    }

    private void parsePhone( XMLEventReader eventReader, Phone phone ) throws ParseException {

        try {
            while (eventReader.hasNext()) {

                XMLEvent event = eventReader.nextEvent();

                if (event.isStartElement()) {
                    String eventString = event.asStartElement().getName().getLocalPart();
                    ParseTag tag = ParseTag.toEnum(eventString);
                    switch (tag) {
                        case MANUFACTURER:
                            event = eventReader.nextEvent();
                            phone.setManuf(event.asCharacters().getData());
                            break;
                        case OS:
                            event = eventReader.nextEvent();
                            phone.setOs(event.asCharacters().getData());
                            break;
                        case DISPLAY_TYPE:
                            event = eventReader.nextEvent();
                            phone.setDisplay(event.asCharacters().getData());
                            break;
                        case SD_TYPE:
                            event = eventReader.nextEvent();
                            phone.setSd(Phone.SDCard.valueOf(event.asCharacters().getData()));
                            break;
                        case MANUFACTURE_DATE:
                            event = eventReader.nextEvent();
                            phone.setManufDate(Integer.parseInt(event.asCharacters().getData()));
                            break;
                        default:
                            throw new ParseException("Unknown tag");
                    }
                } else if (event.isEndElement()) {

                    EndElement endElement = event.asEndElement();
                    if (ParseTag.PHONE == ParseTag.toEnum(endElement.getName().getLocalPart())) {

                        return;

                    }
                }
            }
        }
        catch (XMLStreamException e) {
            throw new ParseException( e );
        }
    }
    public ArrayList<Phone> parse( File file ) throws ParseException {

        try {
            FileInputStream fileInStream = new FileInputStream(file);
            XMLEventReader eventReader = xmlFactory.createXMLEventReader(fileInStream);

            ArrayList<Phone> phones = new ArrayList<Phone>();
            phones.clear();
            Phone phone;

            while (eventReader.hasNext()) {

                XMLEvent event = eventReader.nextEvent();

                if (event.isStartElement()) {
                    phone = new Phone();
                    StartElement startElement = event.asStartElement();
                    String name = startElement.getName().getLocalPart();

                    Iterator<Attribute> attributes = startElement.getAttributes();

                    ParseTag tag = ParseTag.toEnum(name);
                    if (tag != null) {
                        switch (tag) {
                            case PHONE:
                                parsePhone(eventReader, phone);
                                break;
                            case PHONES:
                                continue;

                            default:
                                throw new ParseException();
                        }
                    }

                    while (attributes.hasNext()) {

                        Attribute attribute = attributes.next();
                        tag = ParseTag.toEnum(attribute.getName().getLocalPart());
                        if (tag != null) {
                            switch (tag) {
                                case NAME:
                                    phone.setName(attribute.getValue());
                                    break;
                                case SIZE:
                                    phone.setSize(Double.parseDouble(attribute.getValue()));
                                    break;
                                default:
                                    throw new ParseException();
                            }
                        }
                    }
                    phones.add(phone);
                }
            }
            return phones;
        } catch (FileNotFoundException e) {
            throw new ParseException( "Impossible to find file ", e );
        } catch (XMLStreamException e) {
            throw new ParseException( e );
        }
    }
}

