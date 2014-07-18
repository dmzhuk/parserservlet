package com.epam.phones.parsers;


import com.epam.phones.entity.Phone;
import com.epam.phones.parsetag.ParseTag;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

/**
 * Created by Dmitry on 11.07.2014.
 */
public class SaxPhonesHandler extends DefaultHandler {

    private ArrayList<Phone> phones;
    private Phone phone;
    private ParseTag currentTag;
    public SaxPhonesHandler(){
        phones = new ArrayList<Phone>();
    }
    public ArrayList<Phone> getPhones() {
        return phones;
    }
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentTag = ParseTag.toEnum( qName );
        if ( currentTag == ParseTag.PHONE ){
            phone = new Phone();
            phone.setName(attributes.getValue( ParseTag.NAME.getTagName() ));
            phone.setSize( Double.parseDouble( attributes.getValue( ParseTag.SIZE.getTagName() ) ));
        }
    }
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        ParseTag tag = ParseTag.toEnum( qName );
        if ( tag == ParseTag.PHONE ){
            phones.add( phone );
        }
        currentTag = null;
    }
    @Override
    public void characters(char ch[], int start, int length) throws SAXException{
        String s = new String( ch, start, length );
        if ( currentTag != null ) {
            switch (currentTag) {
                case MANUFACTURER:
                    phone.setManuf(s);
                    break;
                case MANUFACTURE_DATE:
                    phone.setManufDate(Integer.parseInt(s));
                    break;
                case OS:
                    phone.setOs(s);
                    break;
                case SD_TYPE:
                    phone.setSd(Phone.SDCard.valueOf(s));
                    break;
                case DISPLAY_TYPE:
                    phone.setDisplay(s);
                    break;
                default:

            }
        }
        currentTag = null;
    }
}
