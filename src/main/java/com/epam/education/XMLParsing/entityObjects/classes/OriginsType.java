package com.epam.education.XMLParsing.entityObjects.classes;

import com.epam.education.XMLParsing.entityObjects.enums.ContinentType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for originsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="originsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="continent" type="{http://www.epam.education.com/Gems}continentType"/>
 *         &lt;element name="country" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="region" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "originsType", namespace = "http://www.epam.education.com/Gems", propOrder = {
    "continent",
    "country",
    "region"
})
public class OriginsType {

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected ContinentType continent;

    @XmlElement(required = true)
    protected String country;

    @XmlElement(required = true)
    protected String region;

    public OriginsType() {
    }

    /**
     * Constructor
     * @param continent
     * @param country
     * @param region
     */
    public OriginsType(ContinentType continent, String country, String region) {
        this.continent = continent;
        this.country = country;
        this.region = region;
    }

    /**
     * Gets the value of the continent property.
     * 
     * @return
     *     possible object is
     *     {@link ContinentType }
     *     
     */
    public ContinentType getContinent() {
        return continent;
    }

    /**
     * Sets the value of the continent property.
     * 
     * @param value
     *     allowed object is
     *     {@link ContinentType }
     *     
     */
    public void setContinent(ContinentType value) {
        this.continent = value;
    }

    /**
     * Gets the value of the country property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the value of the country property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountry(String value) {
        this.country = value;
    }

    /**
     * Gets the value of the region property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegion() {
        return region;
    }

    /**
     * Sets the value of the region property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegion(String value) {
        this.region = value;
    }

    @Override
    public String toString() {
        return "Origin = " + continent.value() + ", " + country + ", " + region;
    }
}
