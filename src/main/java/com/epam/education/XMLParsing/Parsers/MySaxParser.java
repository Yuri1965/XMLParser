package com.epam.education.XMLParsing.Parsers;

import com.epam.education.XMLParsing.Utils.GemsHandler;
import com.epam.education.XMLParsing.Utils.MyErrorHandler;
import com.epam.education.XMLParsing.entityObjects.classes.Gem;
import com.epam.education.XMLParsing.entityObjects.classes.ListGems;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.xml.sax.SAXException;
import com.sun.org.apache.xerces.internal.parsers.SAXParser;

import java.io.IOException;
import java.util.ArrayList;

/**
 * SAX Parser class
 */
public class MySaxParser {
    // Логгер
    public static Logger myLoggerSAXParser = LogManager.getLogger(MySaxParser.class.getName());

    // входной файл для парсера
    private String xmlFilePath;

    // SAX парсер
    private SAXParser mySAXParser;

    // Gems Handler
    private GemsHandler gemsHandler;

    // конструктор
    public MySaxParser(String xmlFilePath) throws SAXException {
        this.xmlFilePath = xmlFilePath;

        this.gemsHandler = new GemsHandler(myLoggerSAXParser, xmlFilePath, new ArrayList<Gem>());

        this.mySAXParser = new SAXParser();
        this.mySAXParser.setContentHandler(this.gemsHandler);
        this.mySAXParser.setErrorHandler(new MyErrorHandler(myLoggerSAXParser));
    }

    // парсит и заполняет список объектов из xml файла
    public ListGems LoadFromXMLFile(boolean outputToConsole) {
        ListGems listGems = new ListGems();

        try {
            if(gemsHandler != null) {
                // передаем в gemsHandler ссылку на коллекцию объектов, которая будет заполняться при разборе xml
                gemsHandler.setGems(listGems.getGem());

                // разбор XML-документа
                mySAXParser.parse(xmlFilePath);

                // вывод в консоль
                if (outputToConsole) {
                    System.out.println(listGems.toString());
                }
            }
        } catch (SAXException e) {
            myLoggerSAXParser.error(e.getMessage());
        } catch (IOException e) {
            myLoggerSAXParser.error(e.getMessage());
        } finally {
            return listGems;
        }
    }
}
