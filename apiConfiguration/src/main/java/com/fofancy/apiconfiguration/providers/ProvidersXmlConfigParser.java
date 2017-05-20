package com.fofancy.apiconfiguration.providers;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by shaylin3 on 30.04.2017.
 */
public class ProvidersXmlConfigParser {
    public ConcurrentHashMap<String, Provider> parse(String filePath, String schemaPath) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            factory.setNamespaceAware( true);
            final SchemaFactory sf =
                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

            final Schema schema = sf.newSchema(
                    new StreamSource(
                            ProvidersContext.class.getClassLoader().getResourceAsStream(schemaPath)
                    )
            );
            factory.setSchema(schema);

            InputStream xmlInput  = ProvidersContext.class.getClassLoader().getResourceAsStream(filePath);

            SAXParser saxParser = factory.newSAXParser();

            ProvidersXmlConfigSaxHandler handler = new ProvidersXmlConfigSaxHandler();



            saxParser.parse(xmlInput, handler);

            return handler.getProviders();
        } catch (ParserConfigurationException e) {
            throw new ProvidersXmlConfigParsingException(e.getMessage());
        } catch (FileNotFoundException e) {
            throw new ProvidersXmlConfigParsingException(e.getMessage());
        } catch (IOException e) {
            throw new ProvidersXmlConfigParsingException(e.getMessage());
        } catch (SAXException e) {
            throw new ProvidersXmlConfigParsingException(e.getMessage());
        }
    }
}
