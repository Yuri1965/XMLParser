package com.epam.education.XMLParsing.entityObjects.classes;

import com.epam.education.XMLParsing.entityObjects.enums.ColorType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for visualParamType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="visualParamType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="colorName" type="{http://www.epam.education.com/Gems}colorType"/>
 *         &lt;element name="transparencyValue" type="{http://www.epam.education.com/Gems}transparencyType"/>
 *         &lt;element name="quantitySides" type="{http://www.epam.education.com/Gems}quantitySideType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "visualParamType", namespace = "http://www.epam.education.com/Gems", propOrder = {
    "colorName",
    "transparencyValue",
    "quantitySides"
})
public class VisualParamType {

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected ColorType colorName;

    @XmlElement(required = true)
    protected TransparencyType transparencyValue;

    @XmlElement(required = true)
    protected QuantitySideType quantitySides;

    public VisualParamType() {
    }

    /**
     * Constructor
     * @param colorName
     * @param transparencyValue
     * @param quantitySides
     */
    public VisualParamType(ColorType colorName, TransparencyType transparencyValue, QuantitySideType quantitySides) {
        this.colorName = colorName;
        this.transparencyValue = transparencyValue;
        this.quantitySides = quantitySides;
    }

    /**
     * Constructor
     * @param colorName
     * @param transparencyValue
     * @param weightTransparency
     * @param quantitySides
     * @param quantitySideName
     */
    public VisualParamType(ColorType colorName, int transparencyValue, String weightTransparency,
                           int quantitySides, String quantitySideName) {
        this.colorName = colorName;
        this.transparencyValue = new TransparencyType(transparencyValue, weightTransparency);
        this.quantitySides = new QuantitySideType(quantitySides, quantitySideName);
    }

    /**
     * Gets the value of the colorName property.
     * 
     * @return
     *     possible object is
     *     {@link ColorType }
     *     
     */
    public ColorType getColorName() {
        return colorName;
    }

    /**
     * Sets the value of the colorName property.
     * 
     * @param value
     *     allowed object is
     *     {@link ColorType }
     *     
     */
    public void setColorName(ColorType value) {
        this.colorName = value;
    }

    /**
     * Gets the value of the transparencyValue property.
     * 
     * @return
     *     possible object is
     *     {@link TransparencyType }
     *     
     */
    public TransparencyType getTransparencyValue() {
        return transparencyValue;
    }

    /**
     * Sets the value of the transparencyValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link TransparencyType }
     *     
     */
    public void setTransparencyValue(TransparencyType value) {
        this.transparencyValue = value;
    }

    /**
     * Gets the value of the quantitySides property.
     * 
     * @return
     *     possible object is
     *     {@link QuantitySideType }
     *     
     */
    public QuantitySideType getQuantitySides() {
        return quantitySides;
    }

    /**
     * Sets the value of the quantitySides property.
     * 
     * @param value
     *     allowed object is
     *     {@link QuantitySideType }
     *     
     */
    public void setQuantitySides(QuantitySideType value) {
        this.quantitySides = value;
    }

    @Override
    public String toString() {
        return  "Color = " + colorName.value() +
                ", " + transparencyValue.toString() +
                ", " + quantitySides.toString();
    }
}
