package com.epam.education.XMLParsing.Parsers;

import com.epam.education.XMLParsing.entityObjects.classes.ListGems;
import com.sun.xml.internal.bind.marshaller.NamespacePrefixMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.*;
import java.io.*;

/**
 * JAXB Parser class
 */
public class MyJAXBParser {
    // Логгер
    public static Logger myLoggerJAXBParser = LogManager.getLogger(MyJAXBParser.class.getName());

    // Константы
    private static final String FIRST_PREFIX = "gems";
    private static final String FIRST_URI = "http://www.epam.education.com/Gems";
    private static final String BAR_PREFIX = "xsi";
    private static final String BAR_URI = "http://www.w3.org/2001/XMLSchema-instance";

    private static final String PROPERTY_JAXB_SCHEMA_LOCATION =
            "http://www.epam.education.com/Gems http://www.epam.education.com/Gems/Gems.xsd";
    private static final  String PROPERTY_NAME_SPACE_PREFIX_MAPPER = "com.sun.xml.internal.bind.namespacePrefixMapper";

    // входной файл для парсера
    private String xmlFilePath;

    // контекст парсера
    private JAXBContext myJAXBContext;

    /**
     * Constructor
     * @param xmlFilePath
     */
    public MyJAXBParser(String xmlFilePath) {
        this.xmlFilePath = xmlFilePath;

        try {
            this.myJAXBContext = JAXBContext.newInstance(ListGems.class);
        } catch (JAXBException e) {
            e.printStackTrace();
            myLoggerJAXBParser.error(e.getMessage());
        }
    }

    // парсит и заполняет список объектов из xml файла
    public ListGems LoadFromXMLFile(boolean outputToConsole) {
        ListGems listGems = null;

        try {
            File xmlFile = new File(xmlFilePath);
            Unmarshaller jaxbUnmarshaller = myJAXBContext.createUnmarshaller();

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
    public void SaveToXMLFile(ListGems listGems, String xmlChangeFilePath) {
        try {
            myLoggerJAXBParser.info("Start save data to file: " + xmlChangeFilePath + " ...");

            Marshaller jaxbMarshaller = myJAXBContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, PROPERTY_JAXB_SCHEMA_LOCATION);
            try {
                jaxbMarshaller.setProperty(PROPERTY_NAME_SPACE_PREFIX_MAPPER, new MyNameSpaceMapper());
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
        @Override
        public String getPreferredPrefix(String namespaceUri, String suggestion, boolean requirePrefix) {
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
}
