package com.epam.education.XMLParsing.Utils;

import com.epam.education.XMLParsing.entityObjects.classes.*;

import com.epam.education.XMLParsing.entityObjects.enums.ColorType;
import com.epam.education.XMLParsing.entityObjects.enums.ContinentType;
import com.epam.education.XMLParsing.entityObjects.enums.PreciousnessType;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.math.BigDecimal;
import java.util.List;

/**
 * Gems Handler for SAX Parser
 */
public class GemsHandler extends DefaultHandler {
    // Логгер
    private Logger logger;

    // входной файл
    private String xmlFilePath;

    // список объектов gem
    private List<Gem> gems;

    // элемент Gem
    private Gem gem;

    // составные части элемента Gem
    private ValueType value;
    private OriginsType origin;
    private VisualParamType visualParam;
    private TransparencyType transparencyType;
    private QuantitySideType quantitySideType;

    // для определения какой объект внутри Gem надо создать
    private boolean bName = false;
    private boolean bValue = false;

    private boolean bOrigin = false;
    private boolean bContinent = false;
    private boolean bCountry = false;
    private boolean bRegion = false;

    private boolean bPreciousness = false;

    private boolean bVisualParams = false;
    private boolean bColorName = false;
    private boolean bTransparencyValue = false;
    private boolean bQuantitySides = false;

    // конструктор
    public GemsHandler(Logger logger, String xmlFilePath, List<Gem> toGems) {
        this.logger = logger;
        this.xmlFilePath = xmlFilePath;
        this.gems = toGems;
    }

    // геттеры и сеттеры
    public List<Gem> getGems() {
        return gems;
    }

    public void setGems(List<Gem> gems) {
        this.gems = gems;
    }

    // методы чтения данных из файла xml, в которых заполняется переданная коллекция объектов
    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        logger.info("Start parse file: " + xmlFilePath + " ...");
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        logger.info("End parse file: " + gems.size() + " objects Gem found...");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);

        if (qName.equalsIgnoreCase("Gem")) {
            gem = new Gem();
            gem.setGemID(attributes.getValue("GemID"));

        } else if (qName.equalsIgnoreCase("Name")) {
            bName = true;

        } else if (qName.equalsIgnoreCase("Value")) {
            value = new ValueType();
            value.setWeightName(attributes.getValue("weightName"));
            bValue = true;

        } else if (qName.equalsIgnoreCase("Origin")) {
            origin = new OriginsType();
            bOrigin = true;
        } else if (qName.equalsIgnoreCase("continent")) {
            bContinent = true;
        } else if (qName.equalsIgnoreCase("country")) {
            bCountry = true;
        } else if (qName.equalsIgnoreCase("region")) {
            bRegion = true;

        } else if (qName.equalsIgnoreCase("Preciousness")) {
            bPreciousness = true;

        } else if (qName.equalsIgnoreCase("VisualParams")) {
            visualParam = new VisualParamType();
            bVisualParams = true;
        } else if (qName.equalsIgnoreCase("colorName")) {
            bColorName = true;
        } else if (qName.equalsIgnoreCase("transparencyValue")) {
            transparencyType = new TransparencyType();
            transparencyType.setWeightTransparency(attributes.getValue("weightTransparency"));
            bTransparencyValue = true;
        } else if (qName.equalsIgnoreCase("quantitySides")) {
            quantitySideType = new QuantitySideType();
            quantitySideType.setQuantitySideName(attributes.getValue("quantitySideName"));
            bQuantitySides = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);

        if (qName.equalsIgnoreCase("Gem")) {
            gems.add(gem);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        if (bName) {
            gem.setName(new String(ch, start, length));
            bName = false;

        } else if (bValue) {
            value.setValue(BigDecimal.valueOf(Double.parseDouble(new String(ch, start, length))));
            gem.setValue(value);
            bValue = false;

        } else if (bOrigin) {
            gem.setOrigin(origin);
            bOrigin = false;
        } else if (bContinent) {
            gem.getOrigin().setContinent(ContinentType.fromValue(new String(ch, start, length)));
            bContinent = false;
        } else if (bCountry) {
            gem.getOrigin().setCountry(new String(ch, start, length));
            bCountry = false;
        } else if (bRegion) {
            gem.getOrigin().setRegion(new String(ch, start, length));
            bRegion = false;

        } else if (bVisualParams) {
            gem.setVisualParams(visualParam);
            bVisualParams = false;
        } else if (bColorName) {
            gem.getVisualParams().setColorName(ColorType.fromValue(new String(ch, start, length)));
            bColorName = false;
        } else if (bTransparencyValue) {
            gem.getVisualParams().setTransparencyValue(transparencyType);
            gem.getVisualParams().getTransparencyValue().setValue(Integer.valueOf(new String(ch, start, length)));
            bTransparencyValue = false;
        } else if (bQuantitySides) {
            gem.getVisualParams().setQuantitySides(quantitySideType);
            gem.getVisualParams().getQuantitySides().setValue(Integer.valueOf(new String(ch, start, length)));
            bQuantitySides = false;

        } else if (bPreciousness) {
            gem.setPreciousness(PreciousnessType.fromValue(new String(ch, start, length)));
            bPreciousness = false;
        }
    }
}
