package com.epam.education.XMLParsing.entityObjects.classes;

import com.epam.education.XMLParsing.entityObjects.enums.ColorType;
import com.epam.education.XMLParsing.entityObjects.enums.ContinentType;
import com.epam.education.XMLParsing.entityObjects.enums.PreciousnessType;

import javax.xml.bind.annotation.XmlRegistry;
import java.math.BigDecimal;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.epam.education.XMLParsing.entityObjects package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.epam.education.XMLParsing.entityObjects
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ListGems }
     * 
     */
    public ListGems createListGems() {
        return new ListGems();
    }

    /**
     * Create an instance of {@link Gem }
     * 
     */
    public Gem createGem() {
        return new Gem();
    }

    public Gem createGem(String name, ValueType value, OriginsType origin, PreciousnessType preciousness,
                         VisualParamType visualParams, String gemID) {
        return new Gem(name, value, origin, preciousness, visualParams, gemID);
    }

    public Gem createGem(String name,
                         BigDecimal value, String weightName,
                         ContinentType continent, String country, String region,
                         PreciousnessType preciousness,
                         ColorType colorName, int transparencyValue, String weightTransparency,
                         int quantitySides, String quantitySideName,
                         String gemID) {
        return new Gem(name, value, weightName, continent, country, region, preciousness,
                colorName, transparencyValue, weightTransparency, quantitySides, quantitySideName, gemID);
    }

    /**
     * Create an instance of {@link TransparencyType }
     * 
     */
    public TransparencyType createTransparencyType() {
        return new TransparencyType();
    }

    public TransparencyType createTransparencyType(int value, String weightTransparency) {
        return new TransparencyType(value, weightTransparency);
    }

    /**
     * Create an instance of {@link QuantitySideType }
     * 
     */
    public QuantitySideType createQuantitySideType() {
        return new QuantitySideType();
    }

    public QuantitySideType createQuantitySideType(int value, String quantitySideName) {
        return new QuantitySideType(value, quantitySideName);
    }

    /**
     * Create an instance of {@link OriginsType }
     * 
     */
    public OriginsType createOriginsType() {
        return new OriginsType();
    }

    public OriginsType createOriginsType(ContinentType continent, String country, String region) {
        return new OriginsType(continent, country, region);
    }

    /**
     * Create an instance of {@link VisualParamType }
     * 
     */
    public VisualParamType createVisualParamType() {
        return new VisualParamType();
    }

    public VisualParamType createVisualParamType(ColorType colorName, TransparencyType transparencyValue,
                                                 QuantitySideType quantitySides) {
        return new VisualParamType(colorName, transparencyValue, quantitySides);
    }

    public VisualParamType createVisualParamType(ColorType colorName, int transparencyValue, String weightTransparency,
                                                 int quantitySides, String quantitySideName) {
        return new VisualParamType(colorName, transparencyValue, weightTransparency, quantitySides, quantitySideName);
    }

    /**
     * Create an instance of {@link ValueType }
     * 
     */
    public ValueType createValueType() {
        return new ValueType();
    }

    public ValueType createValueType(BigDecimal value, String weightName) {
        return new ValueType(value, weightName);
    }

}
