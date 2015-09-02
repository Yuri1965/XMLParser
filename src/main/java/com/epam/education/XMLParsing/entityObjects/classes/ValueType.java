package com.epam.education.XMLParsing.entityObjects.classes;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * <p>Java class for valueType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="valueType">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.epam.education.com/Gems>value">
 *       &lt;attribute name="weightName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" fixed="ct" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "valueType", namespace = "http://www.epam.education.com/Gems", propOrder = {
    "value"
})
public class ValueType {

    @XmlValue
    protected BigDecimal value;

    @XmlAttribute(name = "weightName", required = true)
    protected String weightName = "ct";

    public ValueType() {
    }

    /**
     * Constructor
     * @param value
     * @param weightName
     */
    public ValueType(BigDecimal value, String weightName) {
        this.value = value;

        if (weightName == null) {
            weightName = "ct";
        }
        this.weightName = weightName;
    }

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setValue(BigDecimal value) {
        this.value = value;
    }

    /**
     * Gets the value of the weightName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWeightName() {
        if (weightName == null) {
            return "ct";
        } else {
            return weightName;
        }
    }

    /**
     * Sets the value of the weightName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWeightName(String value) {
        this.weightName = value;
    }

    @Override
    public String toString() {
        return "Value = " + value + " " + weightName;
    }
}
