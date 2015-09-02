package com.epam.education.XMLParsing.Parsers;

import com.epam.education.XMLParsing.entityObjects.classes.ListGems;
import com.sun.xml.internal.bind.marshaller.NamespacePrefixMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sun.org.apache.xerces.internal.jaxp.validation.XMLSchemaFactory;
import org.xml.sax.SAXException;

import javax.xml.bind.*;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;

/**
 * Created by URA on 01.09.2015.
 */
public class MyJAXBParser {
    static Logger myLoggerJAXBParser = LogManager.getLogger(MyJAXBParser.class.getName());

    // входной файл для парсера
    private String xmlFilePath;

    // файл схемы для парсера
    private String xsdFilePath;

    // выходной файл для парсера
    private String xmlChangeFilePath;

    /**
     * Constructor
     * @param xmlFilePath
     * @param xsdFilePath
     * @param xmlChangeFilePath
     */
    public MyJAXBParser(String xmlFilePath, String xsdFilePath, String xmlChangeFilePath) {
        this.xmlFilePath = xmlFilePath;
        this.xsdFilePath = xsdFilePath;
        this.xmlChangeFilePath = xmlChangeFilePath;
    }

    // парсит и заполняет список объектов из xml файла
    public ListGems LoadFromXMLFile(boolean outputToConsole) {
        ListGems listGems = null;

        try {
            File xmlFile = new File(xmlFilePath);
            JAXBContext jaxbContext = JAXBContext.newInstance(ListGems.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            myLoggerJAXBParser.info("Start parse file: " + xmlFilePath + " ...");
            listGems = (ListGems) jaxbUnmarshaller.unmarshal(xmlFile);
            myLoggerJAXBParser.info("End parse file: " + listGems.getGem().size() + " objects Gem found...");

            if (outputToConsole) {
                System.out.println(listGems.toString());
            }

        } catch (JAXBException e) {
            e.printStackTrace();
            myLoggerJAXBParser.error(e.getMessage());
        } finally {
            return listGems;
        }
    }

    // сохраняет список объектов в xml файл
    public void SaveToXMLFile(ListGems listGems) {
        try {
            myLoggerJAXBParser.info("Start save data to file: " + xmlChangeFilePath + " ...");

            JAXBContext jaxbContext = JAXBContext.newInstance(ListGems.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION,
                    "http://www.epam.education.com/Gems http://www.epam.education.com/Gems/Gems.xsd");
            try {
                jaxbMarshaller.setProperty("com.sun.xml.internal.bind.namespacePrefixMapper", new MyNameSpaceMapper());
            } catch(PropertyException e) {
                myLoggerJAXBParser.error(e.getMessage());
            }

            OutputStream outputStream = new FileOutputStream(xmlChangeFilePath);
            jaxbMarshaller.marshal(listGems, outputStream);

            myLoggerJAXBParser.info("Data saving to file: " + xmlChangeFilePath + " ...");
        } catch (JAXBException e) {
            e.printStackTrace();
            myLoggerJAXBParser.error(e.getMessage());
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            myLoggerJAXBParser.error(e.getMessage());
        }
    }

    // класс для указания правильных namespace и т.п. при записи данных в xml файл с данными
    private class MyNameSpaceMapper extends NamespacePrefixMapper {

        private static final String FIRST_PREFIX = "gems"; // DEFAULT NAMESPACE
        private static final String FIRST_URI = "http://www.epam.education.com/Gems";

        private static final String BAR_PREFIX = "xsi";
        private static final String BAR_URI = "http://www.w3.org/2001/XMLSchema-instance";

        @Override
        public String getPreferredPrefix(String namespaceUri, String suggestion,
                                         boolean requirePrefix) {
            if(FIRST_URI.equals(namespaceUri)) {
                return FIRST_PREFIX;
            } else if(BAR_URI.equals(namespaceUri)) {
                return BAR_PREFIX;
            }
            return suggestion;
        }

        @Override
        public String[] getPreDeclaredNamespaceUris() {
            return new String[] {FIRST_URI, BAR_URI };
        }
    }

    // валидация xml файла по схеме
    public static boolean validate(String xmlFilePath, String xmlSchemaFilePath) {
        try {
            myLoggerJAXBParser.info("Start validation file: " + xmlFilePath +
                    " by scheme file: " + xmlSchemaFilePath + " ...");

            SchemaFactory factory = new XMLSchemaFactory();
            Source schemaFile = new StreamSource(new File(xmlSchemaFilePath));
            Source xmlSource = new StreamSource(new File(xmlFilePath));
            Schema schema = factory.newSchema(schemaFile);
            Validator validator = schema.newValidator();
            validator.validate(xmlSource);

            myLoggerJAXBParser.info("File is valid...");
        } catch (SAXException e) {
            myLoggerJAXBParser.info("File: " + xmlFilePath + " is not valid...");
            myLoggerJAXBParser.error(e.getMessage());
            return false;
        } catch (IOException e) {
            myLoggerJAXBParser.info("File: " + xmlFilePath + " is not valid...");
            myLoggerJAXBParser.error(e.getMessage());
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            myLoggerJAXBParser.info("File: " + xmlFilePath + " is not valid...");
            myLoggerJAXBParser.error(e.getMessage());
            return false;
        } catch (Error e) {
            e.printStackTrace();
            myLoggerJAXBParser.info("File: " + xmlFilePath + " is not valid...");
            myLoggerJAXBParser.error(e.getMessage());
            return false;
        }

        return true;
    }

    // метод преобразования файла XML в другой файл XML согласно шаблона XSL
    public void transformXMLByXSL(String xslFilePath, String xmlFilePath, String xmlTransformFilePath) {
        try {
            myLoggerJAXBParser.info("Start transform file...");
            TransformerFactory tf =
                    TransformerFactory.newInstance();
            //установка используемого XSL-преобразования
            Transformer transformer =
                    tf.newTransformer(new StreamSource(xslFilePath));
            //установка исходного XML-документа и конечного XML-файла
            transformer.transform(
                    new StreamSource(xmlFilePath),
                    new StreamResult(xmlTransformFilePath));
            myLoggerJAXBParser.info("End transform file...");
        } catch(TransformerException e) {
            e.printStackTrace();
            myLoggerJAXBParser.error(e.getMessage());
        }
    }

}
