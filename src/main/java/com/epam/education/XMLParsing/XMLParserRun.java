package com.epam.education.XMLParsing;

import com.epam.education.XMLParsing.Parsers.MyJAXBParser;
import com.epam.education.XMLParsing.Utils.PropertiesFileUtil;
import com.epam.education.XMLParsing.entityObjects.classes.Gem;
import com.epam.education.XMLParsing.entityObjects.classes.ListGems;
import com.epam.education.XMLParsing.entityObjects.classes.ObjectFactory;
import com.epam.education.XMLParsing.entityObjects.enums.ColorType;
import com.epam.education.XMLParsing.entityObjects.enums.ContinentType;
import com.epam.education.XMLParsing.entityObjects.enums.PreciousnessType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.UUID;


/**
 * Application Runner class
 *
 */
public class XMLParserRun {
    static Logger myLogger = LogManager.getLogger(XMLParserRun.class.getName());

    public static void main( String[] args ) throws IOException {
        myLogger.info("Application started");

        // заполним параметры для запуска приложения
        PropertiesFileUtil.loadPropertiesFromFile();
        String xmlOriginalFilePath = PropertiesFileUtil.myProperties.getProperty("originalFile");
        String xsdFilePath = PropertiesFileUtil.myProperties.getProperty("xsdFile");
        String xmlChangeFilePath = PropertiesFileUtil.myProperties.getProperty("changeFile");
        String xslFilePath = PropertiesFileUtil.myProperties.getProperty("xslFile");
        String xmlTransformFilePath = PropertiesFileUtil.myProperties.getProperty("transformFile");

        // разбор и загрузка данных в объекты из файла xml при помощи JAXB
        // создаем наш парсер
        MyJAXBParser myJaxbParser = new MyJAXBParser(xmlOriginalFilePath, xsdFilePath, xmlChangeFilePath);

        // проверяем на валидность по схеме наш файл с данными
        if (myJaxbParser.validate(xmlOriginalFilePath, xsdFilePath)) {
            // создаем объекты нашего приложения
            ObjectFactory myObjectsFactory = new ObjectFactory();
            ListGems listGems = myObjectsFactory.createListGems();
            // загружаем данные в наши объекты из xml файла и выводим их в консоль
            listGems = myJaxbParser.LoadFromXMLFile(true);

            // создадим 2 новых записи в списке объектов и сохраним этот список в новый xml файл
            listGems.getGem().add(new Gem("Diamond Blue",
                    BigDecimal.valueOf(1150.50), "ct", ContinentType.AZIA, "China", "Goncong", PreciousnessType.JEWEL,
                    ColorType.BLUE, 100, "%", 12, "side", "uuid_" + UUID.randomUUID().toString()));

            listGems.getGem().add(new Gem("Emerald",
                    BigDecimal.valueOf(250.80), "ct", ContinentType.AUSTRALIA, "Australia", "Ceiptawn-sity",
                    PreciousnessType.JEWEL, ColorType.GREEN, 98, "%", 7, "side", "uuid_" + UUID.randomUUID().toString()));

            myJaxbParser.SaveToXMLFile(listGems);
        }

        // трансформация XML файла согласно шаблону, который определен в файле XSL
        myJaxbParser.transformXMLByXSL(xslFilePath, xmlChangeFilePath, xmlTransformFilePath);

        myLogger.info("Application stopped");
    }
}
