package com.epam.education.XMLParsing.entityObjects.classes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * <p>Java class for quantitySideType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="quantitySideType">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.epam.education.com/Gems>quantitySide">
 *       &lt;attribute name="quantitySideName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" fixed="side" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "quantitySideType", namespace = "http://www.epam.education.com/Gems", propOrder = {
    "value"
})
public class QuantitySideType {

    @XmlValue
    protected int value;

    @XmlAttribute(name = "quantitySideName", required = true)
    protected String quantitySideName = "side";

    public QuantitySideType() {
    }

    /**
     * Constructor
     * @param value
     * @param quantitySideName
     */
    public QuantitySideType(int value, String quantitySideName) {
        this.value = value;

        if (quantitySideName == null) {
            quantitySideName = "side";
        }
        this.quantitySideName = quantitySideName;
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
     * Gets the value of the quantitySideName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuantitySideName() {
        if (quantitySideName == null) {
            return "side";
        } else {
            return quantitySideName;
        }
    }

    /**
     * Sets the value of the quantitySideName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuantitySideName(String value) {
        this.quantitySideName = value;
    }

    @Override
    public String toString() {
        return "quantitySide = " + value + " " + quantitySideName;
    }

}
