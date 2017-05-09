package com.fofancy.apiconfiguration.providers;

import org.junit.Test;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import javax.xml.validation.Schema;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertNotNull;
/**
 * Created by shaylin3 on 27.04.2017.
 */
public class ProvidersContextTest {

    private final String PROVIDER_XML_PATH = "providers/providers-config.xml";
    private final String NOT_VALID_PROVIDER_XML_PATH = "providers/not-valid-providers-config.xml";
    private final String PROVIDER_XML_XSD_PATH = "providers/providers-config-schema.xsd";
    @Test(expected = SAXParseException.class)
    public void providersXmlValidatingTest() throws IOException, SAXException {
        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Source xmlFile = new StreamSource(new File(PROVIDER_XML_PATH));
        File schemaFile = new File(PROVIDER_XML_XSD_PATH);
        Schema schema = sf.newSchema(schemaFile);
        Validator validator = schema.newValidator();
        validator.validate(xmlFile);
    }

    @Test
    public void providersXmlParsingTest() {
//        SAXParserFactory factory = SAXParserFactory.newInstance();
//        try {
//            factory.setNamespaceAware( true);
//            factory.setValidating( true);
//            InputStream xmlInput  =
//                    new FileInputStream("data\\sax-example.xml");
//
//            SAXParser saxParser = factory.newSAXParser();
//            saxParser.parse(xmlInput, new DefaultHandler(){
//
//
//            });
//        } catch (Throwable err) {
//            err.printStackTrace ();
//        }
    }

    @Test
    public void providersContextTest() {
        ProvidersContext providersContext = new ProvidersContext();

        providersContext.parseProvidersXmlConfig();
        Provider wikiProvider = providersContext.getProviderByName("Wiki");

        assertNotNull(wikiProvider);
    }
}
