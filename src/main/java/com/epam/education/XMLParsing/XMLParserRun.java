package com.epam.education.XMLParsing;

import com.epam.education.XMLParsing.Parsers.MyDOMParser;
import com.epam.education.XMLParsing.Parsers.MyJAXBParser;
import com.epam.education.XMLParsing.Parsers.MySaxParser;
import com.epam.education.XMLParsing.Parsers.MyStAXParser;
import com.epam.education.XMLParsing.Utils.ParserUtil;
import com.epam.education.XMLParsing.Utils.PropertiesFileUtil;
import com.epam.education.XMLParsing.entityObjects.classes.Gem;
import com.epam.education.XMLParsing.entityObjects.classes.ListGems;
import com.epam.education.XMLParsing.entityObjects.classes.ObjectFactory;
import com.epam.education.XMLParsing.entityObjects.enums.ColorType;
import com.epam.education.XMLParsing.entityObjects.enums.ContinentType;
import com.epam.education.XMLParsing.entityObjects.enums.PreciousnessType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.UUID;


/**
 * Application Runner class
 *
 */
public class XMLParserRun {
    static Logger myLogger = LogManager.getLogger(XMLParserRun.class.getName());

    public static void main( String[] args ) throws IOException, SAXException {
        myLogger.info("Application started");

        // ***********************************************************
        // заполним параметры для запуска приложения
        PropertiesFileUtil.loadPropertiesFromFile();
        // оригинал xml
        String xmlOriginalFilePath = PropertiesFileUtil.myProperties.getProperty("originalFile");
        // схема xsd
        String xsdFilePath = PropertiesFileUtil.myProperties.getProperty("xsdFile");
        // измененный файл после сохранения через JAXB парсер
        String xmlChangeJAXBFilePath = PropertiesFileUtil.myProperties.getProperty("changeJAXBFile");
        // измененный файл после сохранения через DOM парсер
        String xmlChangeDOMFilePath = PropertiesFileUtil.myProperties.getProperty("changeDOMFile");
        // измененный файл после сохранения через StAX парсер
        String xmlChangeStAXFilePath = PropertiesFileUtil.myProperties.getProperty("changeStAXFile");

        // файл схемы трансформации в новый формат
        String xslFilePath = PropertiesFileUtil.myProperties.getProperty("xslFile");
        // файл сохраненный после трансформации
        String xmlTransformFilePath = PropertiesFileUtil.myProperties.getProperty("transformFile");
        // ***********************************************************

        // ***********************************************************
        // проверяем на валидность по схеме наш файл с данными
        // ***********************************************************
        if (ParserUtil.validateXMLFile(xmlOriginalFilePath, xsdFilePath, myLogger)) {
            // создаем объекты нашего приложения
            ObjectFactory myObjectsFactory = new ObjectFactory();
            ListGems listGems = myObjectsFactory.createListGems();

            // ***********************************************************
            // разбор и загрузка данных в объекты из файла xml при помощи JAXB
            myLogger.info("JAXBParser start working...");
            // создаем наш JAXB парсер
            MyJAXBParser myJaxbParser = new MyJAXBParser(xmlOriginalFilePath);
            // загружаем данные в наши объекты из xml файла и выводим их в консоль
            listGems = myJaxbParser.LoadFromXMLFile(false);

            // создадим 2 новых записи в списке объектов и сохраним этот список в новый xml файл
            listGems.getGem().add(new Gem("Diamond Blue",
                    BigDecimal.valueOf(1150.50), "ct", ContinentType.AZIA, "China", "Goncong", PreciousnessType.JEWEL,
                    ColorType.BLUE, 100, "%", 12, "side", "uuid_" + UUID.randomUUID().toString()));
            listGems.getGem().add(new Gem("Emerald",
                    BigDecimal.valueOf(250.80), "ct", ContinentType.AUSTRALIA, "Australia", "Ceiptawn-sity",
                    PreciousnessType.JEWEL, ColorType.GREEN, 98, "%", 7, "side", "uuid_" + UUID.randomUUID().toString()));
            // сохраним измененную коллекцию в новый xml файл
            myJaxbParser.SaveToXMLFile(listGems, xmlChangeJAXBFilePath);
            myLogger.info("JAXBParser end working...");

            // проверка измененного файла на валидность (результат см. лог)
            ParserUtil.validateXMLFile(xmlChangeJAXBFilePath, xsdFilePath, myLogger);
            // ***********************************************************

            // ***********************************************************
            // разбор и загрузка данных в объекты из файла xml при помощи DOM
            myLogger.info("DOMParser start working...");
            // создаем наш JAXB парсер
            MyDOMParser myDOMParser = new MyDOMParser(xmlOriginalFilePath);
            // загружаем данные в наши объекты из xml файла и выводим их в консоль
            listGems = myDOMParser.LoadFromXMLFile(false);

            // создадим 2 новых записи в списке объектов и сохраним этот список в новый xml файл
            listGems.getGem().add(new Gem("Diamond Blue",
                    BigDecimal.valueOf(1150.50), "ct", ContinentType.AZIA, "China", "Goncong", PreciousnessType.JEWEL,
                    ColorType.BLUE, 100, "%", 12, "side", "uuid_" + UUID.randomUUID().toString()));
            listGems.getGem().add(new Gem("Emerald",
                    BigDecimal.valueOf(250.80), "ct", ContinentType.AUSTRALIA, "Australia", "Ceiptawn-sity",
                    PreciousnessType.JEWEL, ColorType.GREEN, 98, "%", 7, "side", "uuid_" + UUID.randomUUID().toString()));
            // сохраним измененную коллекцию в новый xml файл
            myDOMParser.SaveToXMLFile(listGems, xmlChangeDOMFilePath);
            myLogger.info("DOMParser end working...");

            // проверка измененного файла на валидность (результат см. лог)
            ParserUtil.validateXMLFile(xmlChangeDOMFilePath, xsdFilePath, myLogger);
            // ***********************************************************

            // ***********************************************************
            // разбор и загрузка данных в список объектов из файла xml при помощи SAX
            myLogger.info("SAXParser start working...");
            // создаем наш SAX парсер
            MySaxParser mySaxParser = new MySaxParser(xmlOriginalFilePath);
            // загружаем данные в наши объекты из xml файла и выводим их в консоль
            listGems = mySaxParser.LoadFromXMLFile(false);
            myLogger.info("SAXParser end working...");
            // ***********************************************************

            // ***********************************************************
            // разбор и загрузка данных в список объектов из файла xml при помощи StAX
            myLogger.info("StAXParser start working...");
            // создаем наш SAX парсер
            MyStAXParser myStAXParser = new MyStAXParser(xmlOriginalFilePath);
            // загружаем данные в наши объекты из xml файла и выводим их в консоль
            listGems = myStAXParser.LoadFromXMLFile(true);

            // создадим 2 новых записи в списке объектов и сохраним этот список в новый xml файл
            listGems.getGem().add(new Gem("Diamond Blue",
                    BigDecimal.valueOf(85.55), "ct", ContinentType.AZIA, "China", "Goncong", PreciousnessType.JEWEL,
                    ColorType.BLUE, 98, "%", 15, "side", "uuid_" + UUID.randomUUID().toString()));
            listGems.getGem().add(new Gem("Emerald",
                    BigDecimal.valueOf(785.80), "ct", ContinentType.AUSTRALIA, "Australia", "Ceiptawn-sity",
                    PreciousnessType.JEWEL, ColorType.GREEN, 68, "%", 10, "side", "uuid_" + UUID.randomUUID().toString()));
            // сохраним измененную коллекцию в новый xml файл
            myStAXParser.SaveToXMLFile(listGems, xmlChangeStAXFilePath);
            myLogger.info("StAXParser end working...");

            // проверка измененного файла на валидность (результат см. лог)
            ParserUtil.validateXMLFile(xmlChangeStAXFilePath, xsdFilePath, myLogger);
            // ***********************************************************
        }

        // ***********************************************************
        // трансформация XML файла согласно шаблону, который определен в файле XSL
        ParserUtil.transformXMLByXSL(xslFilePath, xmlChangeJAXBFilePath, xmlTransformFilePath, myLogger);
        // ***********************************************************

        myLogger.info("Application stopped");
    }
}
