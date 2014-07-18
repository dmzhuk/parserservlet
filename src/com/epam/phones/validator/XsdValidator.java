package com.epam.phones.validator;

import com.epam.phones.exception.ValidatorException;
import com.sun.xml.internal.ws.developer.MemberSubmissionAddressing;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

/**
 * Created by Dmitry on 13.07.2014.
 */
public class XsdValidator {

    Validator validator;

    public XsdValidator( File schemaFile ) throws ValidatorException {
        try {
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(schemaFile);
            validator = schema.newValidator();
        } catch (SAXException e) {
            throw new ValidatorException( "Wrong schema file", e );
        }
    }
    public void validate( File xmlFile ) throws ValidatorException {

        StreamSource source = new StreamSource( xmlFile );
        try {
            validator.validate( source );
        } catch (SAXException e) {
            throw new ValidatorException(e);
        } catch (IOException e) {
            throw new ValidatorException(e);
        }
    }
}
