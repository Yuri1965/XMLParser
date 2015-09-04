package com.epam.education.XMLParsing.Parsers;

import com.epam.education.XMLParsing.Utils.MyErrorHandler;
import com.epam.education.XMLParsing.entityObjects.classes.*;
import com.epam.education.XMLParsing.entityObjects.enums.ColorType;
import com.epam.education.XMLParsing.entityObjects.enums.ContinentType;
import com.epam.education.XMLParsing.entityObjects.enums.PreciousnessType;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.w3c.dom.*;

import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * DOM Parser class
 */
public class MyDOMParser {
    // Логгер
    public static Logger myLoggerDOMParser = LogManager.getLogger(MyDOMParser.class.getName());

    // входной файл для парсера
    private String xmlFilePath;

    // DOM парсер Xerces
    private DOMParser myDOMParser;

    public MyDOMParser(String xmlFilePath) {
        this.xmlFilePath = xmlFilePath;
        this.myDOMParser = new DOMParser();
        // установка обработчика ошибок
        this.myDOMParser.setErrorHandler(new MyErrorHandler(myLoggerDOMParser));
    }

    // парсит и заполняет список объектов из xml файла
    public ListGems LoadFromXMLFile(boolean outputToConsole) {
        ListGems listGems = null;

        try {
            myLoggerDOMParser.info("Start parse file: " + xmlFilePath + " ...");
            // парсинг файла
            myDOMParser.parse(xmlFilePath);
            Document document = myDOMParser.getDocument();
            Element root = document.getDocumentElement();

            // заполнение списка объектов
            listGems = listBuilder(root);
            myLoggerDOMParser.info("End parse file: " + listGems.getGem().size() + " objects Gem found...");

            if (outputToConsole) {
                System.out.println(listGems.toString());
            }
        } catch (SAXException e) {
            myLoggerDOMParser.error(e.getMessage());
        }
        catch (IOException e) {
            e.printStackTrace();
            myLoggerDOMParser.error(e.getMessage());
        } finally {
            return listGems;
        }
    }

    private static ListGems listBuilder(Element root) throws SAXException, IOException {
        ListGems listGems = new ListGems();
        ArrayList<Gem> list = (ArrayList<Gem>) listGems.getGem();

        // получение списка дочерних элементов <Gem>
        NodeList gemNodes = root.getElementsByTagName("gem");

        Gem gem = null;
        for (int i = 0; i < gemNodes.getLength(); i++) {
            gem = new Gem();
            Element gemElement = (Element) gemNodes.item(i);

            // заполнение объекта gem
            gem.setGemID(gemElement.getAttribute("GemID"));
            gem.setName(getChildValue(gemElement, "Name"));
            gem.setPreciousness(PreciousnessType.fromValue(getChildValue(gemElement, "Preciousness")));

            ValueType value = new ValueType();
            Element valueElement = getChild(gemElement, "Value");
            value.setValue(BigDecimal.valueOf(Double.parseDouble(getChildValue(gemElement, "Value"))));
            value.setWeightName(valueElement.getAttribute("weightName"));
            gem.setValue(value);

            OriginsType origin = new OriginsType();
            Element originElement = getChild(gemElement, "Origin");
            origin.setContinent(ContinentType.fromValue(getChildValue(originElement, "continent")));
            origin.setCountry(getChildValue(originElement, "country"));
            origin.setRegion(getChildValue(originElement, "region"));
            gem.setOrigin(origin);

            VisualParamType visuals = new VisualParamType();
            Element visualsElement = getChild(gemElement, "VisualParams");
            visuals.setColorName(ColorType.fromValue(getChildValue(visualsElement, "colorName")));

            TransparencyType transparency = new TransparencyType();
            transparency.setValue(Integer.valueOf(getChildValue(visualsElement, "transparencyValue")));
            Element transparencyElement = getChild(visualsElement, "transparencyValue");
            transparency.setWeightTransparency(transparencyElement.getAttribute("weightTransparency"));
            visuals.setTransparencyValue(transparency);

            QuantitySideType quantitySide = new QuantitySideType();
            quantitySide.setValue(Integer.valueOf(getChildValue(visualsElement, "quantitySides")));
            Element quantitySideElement = getChild(visualsElement, "quantitySides");
            quantitySide.setQuantitySideName(quantitySideElement.getAttribute("quantitySideName"));
            visuals.setQuantitySides(quantitySide);

            gem.setVisualParams(visuals);

            list.add(gem);
        }

        return listGems;
    }

    // возвращает дочерний элемент по его имени и родительскому элементу
    private static Element getChild(Element parent, String childName) {
        NodeList list = parent.getElementsByTagName(childName);
        Element child = (Element) list.item(0);
        return child;
    }

    // возвращает текст, содержащийся в элементе
    private static String getChildValue(Element parent, String childName) {
        Element child = getChild(parent, childName);
        Node node = child.getFirstChild();
        String value = node.getNodeValue();
        return value;
    }

    private Document documentCreate(List<Gem> list) {
        Document doc = null;

        try {
            doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().
                    getDOMImplementation().createDocument("\"http://www.epam.education.com/Gems\"" +
                            " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"" +
                            " xsi:schemaLocation=\"http://www.epam.education.com/Gems http://www.epam.education.com/Gems/Gems.xsd\"",
                    "gems:ListGems", null);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (DOMException e) {
            e.printStackTrace();
        }

        doc.setXmlStandalone(true);

        Element root = doc.getDocumentElement();


        Iterator<Gem> gemIterator = list.iterator();

        while(gemIterator.hasNext()) {
            Gem gem = gemIterator.next();
            Element gemElement = doc.createElement("gem");

            gemElement.setAttribute("GemID", gem.getGemID());

            Element nameElement = doc.createElement("Name");
            nameElement.setTextContent(gem.getName());
            gemElement.appendChild(nameElement);

            Element valueElement = doc.createElement("Value");
            valueElement.setAttribute("weightName", gem.getValue().getWeightName());
            valueElement.setTextContent(gem.getValue().getValue().toString());
            gemElement.appendChild(valueElement);

            Element originElement = doc.createElement("Origin");
            Element continentElement = doc.createElement("continent");
            continentElement.setTextContent(gem.getOrigin().getContinent().value());
            originElement.appendChild(continentElement);
            Element countryElement = doc.createElement("country");
            countryElement.setTextContent(gem.getOrigin().getCountry());
            originElement.appendChild(countryElement);
            Element regionElement = doc.createElement("region");
            regionElement.setTextContent(gem.getOrigin().getRegion());
            originElement.appendChild(regionElement);
            gemElement.appendChild(originElement);

            Element preciousnessElement = doc.createElement("Preciousness");
            preciousnessElement.setTextContent(gem.getPreciousness().value());
            gemElement.appendChild(preciousnessElement);

            Element visualsElement = doc.createElement("VisualParams");
            Element colorNameElement = doc.createElement("colorName");
            colorNameElement.setTextContent(gem.getVisualParams().getColorName().value());
            visualsElement.appendChild(colorNameElement);
            Element transparencyElement = doc.createElement("transparencyValue");
            transparencyElement.setAttribute("weightTransparency", gem.getVisualParams().getTransparencyValue().getWeightTransparency());
            transparencyElement.setTextContent(Integer.toString(gem.getVisualParams().getTransparencyValue().getValue()));
            visualsElement.appendChild(transparencyElement);
            Element quantitySidesElement = doc.createElement("quantitySides");
            quantitySidesElement.setAttribute("quantitySideName", gem.getVisualParams().getQuantitySides().getQuantitySideName());
            quantitySidesElement.setTextContent(Integer.toString(gem.getVisualParams().getQuantitySides().getValue()));
            visualsElement.appendChild(quantitySidesElement);
            gemElement.appendChild(visualsElement);

            root.appendChild(gemElement);
        }

        return doc;
    }

    // сохраняет список объектов в xml файл
    public void SaveToXMLFile(ListGems listGems, String xmlChangeFilePath) {
        Document doc = documentCreate(listGems.getGem());

        myLoggerDOMParser.info("Start save data to file: " + xmlChangeFilePath + " ...");

        try {
            Source source = new DOMSource(doc);
            File file = new File(xmlChangeFilePath);
            Result resultToFile = new StreamResult(file);
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer();

            transformer.setOutputProperty(OutputKeys.METHOD,"xml");
            transformer.setOutputProperty(OutputKeys.INDENT,"yes");
            transformer.setOutputProperty(OutputKeys.STANDALONE,"yes");
            transformer.setOutputProperty(OutputKeys.ENCODING,"UTF-8");

            transformer.transform(source, resultToFile);

            myLoggerDOMParser.info("Data saving to file: " + xmlChangeFilePath + " ...");
        }
        catch (  TransformerConfigurationException e) {
            myLoggerDOMParser.error(e.getMessage());
        }
        catch (  TransformerException e) {
            myLoggerDOMParser.error(e.getMessage());
        }
    }

}
