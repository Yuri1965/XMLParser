package com.epam.education.XMLParsing.entityObjects.classes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * <p>Java class for transparencyType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="transparencyType">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.epam.education.com/Gems>transparency">
 *       &lt;attribute name="weightTransparency" use="required" type="{http://www.w3.org/2001/XMLSchema}string" fixed="%" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "transparencyType", namespace = "http://www.epam.education.com/Gems", propOrder = {
    "value"
})
public class TransparencyType {

    @XmlValue
    protected int value;

    @XmlAttribute(name = "weightTransparency", required = true)
    protected String weightTransparency = "%";

    public TransparencyType() {
    }

    /**
     * Constructor
     * @param value
     * @param weightTransparency
     */
    public TransparencyType(int value, String weightTransparency) {
        this.value = value;

        if (weightTransparency == null) {
            weightTransparency = "%";
        }
        this.weightTransparency = weightTransparency;
    }

    /**
     * Gets the value of the value property.
     * 
     */
    public int getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Gets the value of the weightTransparency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWeightTransparency() {
        if (weightTransparency == null) {
            return "%";
        } else {
            return weightTransparency;
        }
    }

    /**
     * Sets the value of the weightTransparency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWeightTransparency(String value) {
        this.weightTransparency = value;
    }

    @Override
    public String toString() {
        return "Transparency = " + value + " " + weightTransparency;
    }
}
