package com.epam.education.XMLParsing.Parsers;

import com.epam.education.XMLParsing.entityObjects.classes.*;
import com.epam.education.XMLParsing.entityObjects.enums.ColorType;
import com.epam.education.XMLParsing.entityObjects.enums.ContinentType;
import com.epam.education.XMLParsing.entityObjects.enums.PreciousnessType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.List;

/**
 * StAX Parser class
 */
public class MyStAXParser {
    // Логгер
    public static Logger myLoggerStAXParser = LogManager.getLogger(MySaxParser.class.getName());

    // входной файл для парсера
    private String xmlFilePath;

    // StAX парсер
    private XMLStreamReader myStAXParser;

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
    public MyStAXParser(String xmlFilePath) {
        this.xmlFilePath = xmlFilePath;
    }

    // парсит и заполняет список объектов из xml файла
    public ListGems LoadFromXMLFile(boolean outputToConsole) {
        // инициализируем наш объект для заполнения данными из xml файла
        ListGems listGems = new ListGems();
        gems = listGems.getGem();

        myLoggerStAXParser.info("Start parse file: " + xmlFilePath + " ...");
        // парсим данные
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {
            myStAXParser = xmlInputFactory.createXMLStreamReader(new FileInputStream(xmlFilePath));
            int event = myStAXParser.getEventType();

            while (myStAXParser.hasNext()) {
                if (event == XMLStreamConstants.START_ELEMENT) {
                    if(myStAXParser.getLocalName().equalsIgnoreCase("Gem")){
                        gem = new Gem();
                        gem.setGemID(myStAXParser.getAttributeValue(myStAXParser.getNamespaceURI(), "GemID"));
                    } else if (myStAXParser.getLocalName().equalsIgnoreCase("Name")) {
                        bName = true;
                    } else if (myStAXParser.getLocalName().equalsIgnoreCase("Value")) {
                        value = new ValueType();
                        value.setWeightName(myStAXParser.getAttributeValue(myStAXParser.getNamespaceURI(), "weightName"));
                        bValue = true;

                    } else if (myStAXParser.getLocalName().equalsIgnoreCase("Origin")) {
                        origin = new OriginsType();
                        bOrigin = true;
                    } else if (myStAXParser.getLocalName().equalsIgnoreCase("continent")) {
                        bContinent = true;
                    } else if (myStAXParser.getLocalName().equalsIgnoreCase("country")) {
                        bCountry = true;
                    } else if (myStAXParser.getLocalName().equalsIgnoreCase("region")) {
                        bRegion = true;

                    } else if (myStAXParser.getLocalName().equalsIgnoreCase("Preciousness")) {
                        bPreciousness = true;

                    } else if (myStAXParser.getLocalName().equalsIgnoreCase("VisualParams")) {
                        visualParam = new VisualParamType();
                        bVisualParams = true;
                    } else if (myStAXParser.getLocalName().equalsIgnoreCase("colorName")) {
                        bColorName = true;
                    } else if (myStAXParser.getLocalName().equalsIgnoreCase("transparencyValue")) {
                        transparencyType = new TransparencyType();
                        transparencyType.setWeightTransparency(myStAXParser.getAttributeValue(myStAXParser.getNamespaceURI(), "weightTransparency"));
                        bTransparencyValue = true;
                    } else if (myStAXParser.getLocalName().equalsIgnoreCase("quantitySides")) {
                        quantitySideType = new QuantitySideType();
                        quantitySideType.setQuantitySideName(myStAXParser.getAttributeValue(myStAXParser.getNamespaceURI(),"quantitySideName"));
                        bQuantitySides = true;
                    }

                } else if (event == XMLStreamConstants.CHARACTERS) {
                    if (bName) {
                        gem.setName(myStAXParser.getText());
                        bName = false;

                    } else if (bValue) {
                        value.setValue(BigDecimal.valueOf(Double.parseDouble(myStAXParser.getText())));
                        gem.setValue(value);
                        bValue = false;

                    } else if (bOrigin) {
                        gem.setOrigin(origin);
                        bOrigin = false;
                    } else if (bContinent) {
                        gem.getOrigin().setContinent(ContinentType.fromValue(myStAXParser.getText()));
                        bContinent = false;
                    } else if (bCountry) {
                        gem.getOrigin().setCountry(myStAXParser.getText());
                        bCountry = false;
                    } else if (bRegion) {
                        gem.getOrigin().setRegion(myStAXParser.getText());
                        bRegion = false;

                    } else if (bVisualParams) {
                        gem.setVisualParams(visualParam);
                        bVisualParams = false;
                    } else if (bColorName) {
                        gem.getVisualParams().setColorName(ColorType.fromValue(myStAXParser.getText()));
                        bColorName = false;
                    } else if (bTransparencyValue) {
                        gem.getVisualParams().setTransparencyValue(transparencyType);
                        gem.getVisualParams().getTransparencyValue().setValue(Integer.valueOf(myStAXParser.getText()));
                        bTransparencyValue = false;
                    } else if (bQuantitySides) {
                        gem.getVisualParams().setQuantitySides(quantitySideType);
                        gem.getVisualParams().getQuantitySides().setValue(Integer.valueOf(myStAXParser.getText()));
                        bQuantitySides = false;

                    } else if (bPreciousness) {
                        gem.setPreciousness(PreciousnessType.fromValue(myStAXParser.getText()));
                        bPreciousness = false;
                    }

                } else if (event == XMLStreamConstants.END_ELEMENT) {
                    if (myStAXParser.getLocalName().equalsIgnoreCase("Gem")) {
                        gems.add(gem);
                    }
                }

                event = myStAXParser.next();
            }

            // вывод в консоль
            if (outputToConsole) {
                System.out.println(listGems.toString());
            }

            myLoggerStAXParser.info("End parse file: " + gems.size() + " objects Gem found...");
        } catch (FileNotFoundException | XMLStreamException e) {
            myLoggerStAXParser.error(e.getMessage());
        } finally {
            return listGems;
        }
    }

    // сохраняет список объектов в xml файл
    public void SaveToXMLFile(ListGems listGems, String xmlChangeFilePath) {
        myLoggerStAXParser.info("Start save data to file: " + xmlChangeFilePath + " ...");

        XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
        try{
            XMLStreamWriter xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(new FileOutputStream(xmlChangeFilePath), "UTF-8");

            xmlStreamWriter.writeStartDocument("UTF-8", "1.0");
            xmlStreamWriter.writeCharacters("\n");

            xmlStreamWriter.writeStartElement("gems:ListGems");
            xmlStreamWriter.writeNamespace("gems", "http://www.epam.education.com/Gems");

            // создаем элементы и заполняем ими xml файл
            for (Gem gemChose : listGems.getGem()) {
                xmlStreamWriter.writeCharacters("\n\t");
                xmlStreamWriter.writeStartElement("gem");
                xmlStreamWriter.writeAttribute("GemID", gemChose.getGemID());

                xmlStreamWriter.writeCharacters("\n\t\t");
                xmlStreamWriter.writeStartElement("Name");
                xmlStreamWriter.writeCharacters(gemChose.getName());
                xmlStreamWriter.writeEndElement();

                xmlStreamWriter.writeCharacters("\n\t\t");
                xmlStreamWriter.writeStartElement("Value");
                xmlStreamWriter.writeAttribute("weightName", gemChose.getValue().getWeightName());
                xmlStreamWriter.writeCharacters(gemChose.getValue().getValue().toString());
                xmlStreamWriter.writeEndElement();

                xmlStreamWriter.writeCharacters("\n\t\t");
                xmlStreamWriter.writeStartElement("Origin");
                xmlStreamWriter.writeCharacters("\n\t\t\t");
                xmlStreamWriter.writeStartElement("continent");
                xmlStreamWriter.writeCharacters(gemChose.getOrigin().getContinent().value());
                xmlStreamWriter.writeEndElement();
                xmlStreamWriter.writeCharacters("\n\t\t\t");
                xmlStreamWriter.writeStartElement("country");
                xmlStreamWriter.writeCharacters(gemChose.getOrigin().getCountry());
                xmlStreamWriter.writeEndElement();
                xmlStreamWriter.writeCharacters("\n\t\t\t");
                xmlStreamWriter.writeStartElement("region");
                xmlStreamWriter.writeCharacters(gemChose.getOrigin().getRegion());
                xmlStreamWriter.writeEndElement();
                xmlStreamWriter.writeCharacters("\n\t\t");
                xmlStreamWriter.writeEndElement();

                xmlStreamWriter.writeCharacters("\n\t\t");
                xmlStreamWriter.writeStartElement("Preciousness");
                xmlStreamWriter.writeCharacters(gemChose.getPreciousness().value());
                xmlStreamWriter.writeEndElement();

                xmlStreamWriter.writeCharacters("\n\t\t");
                xmlStreamWriter.writeStartElement("VisualParams");
                xmlStreamWriter.writeCharacters("\n\t\t\t");
                xmlStreamWriter.writeStartElement("colorName");
                xmlStreamWriter.writeCharacters(gemChose.getVisualParams().getColorName().value());
                xmlStreamWriter.writeEndElement();
                xmlStreamWriter.writeCharacters("\n\t\t\t");
                xmlStreamWriter.writeStartElement("transparencyValue");
                xmlStreamWriter.writeAttribute("weightTransparency", gemChose.getVisualParams().getTransparencyValue().getWeightTransparency());
                xmlStreamWriter.writeCharacters(Integer.toString(gem.getVisualParams().getTransparencyValue().getValue()));
                xmlStreamWriter.writeEndElement();
                xmlStreamWriter.writeCharacters("\n\t\t\t");
                xmlStreamWriter.writeStartElement("quantitySides");
                xmlStreamWriter.writeAttribute("quantitySideName", gemChose.getVisualParams().getQuantitySides().getQuantitySideName());
                xmlStreamWriter.writeCharacters(Integer.toString(gem.getVisualParams().getQuantitySides().getValue()));
                xmlStreamWriter.writeEndElement();
                xmlStreamWriter.writeCharacters("\n\t\t");
                xmlStreamWriter.writeEndElement();

                xmlStreamWriter.writeCharacters("\n\t");
                xmlStreamWriter.writeEndElement();
            }

            xmlStreamWriter.writeCharacters("\n");
            xmlStreamWriter.writeEndElement();
            xmlStreamWriter.writeEndDocument();

            xmlStreamWriter.flush();
            xmlStreamWriter.close();
        } catch(XMLStreamException | FileNotFoundException e){
            myLoggerStAXParser.error(e.getMessage());
        }
        myLoggerStAXParser.info("Data saving to file: " + xmlChangeFilePath + " ...");
    }
}