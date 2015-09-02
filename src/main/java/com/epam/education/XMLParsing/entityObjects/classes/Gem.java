package com.epam.education.XMLParsing.entityObjects.classes;

import com.epam.education.XMLParsing.entityObjects.enums.ColorType;
import com.epam.education.XMLParsing.entityObjects.enums.ContinentType;
import com.epam.education.XMLParsing.entityObjects.enums.PreciousnessType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.util.UUID;


/**
 * <p>Java class for Gem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Gem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Value" type="{http://www.epam.education.com/Gems}valueType"/>
 *         &lt;element name="Origin" type="{http://www.epam.education.com/Gems}originsType"/>
 *         &lt;element name="Preciousness" type="{http://www.epam.education.com/Gems}preciousnessType"/>
 *         &lt;element name="VisualParams" type="{http://www.epam.education.com/Gems}visualParamType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="GemID" use="required" type="{http://www.epam.education.com/Gems}gemID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Gem", namespace = "http://www.epam.education.com/Gems", propOrder = {
    "name",
    "value",
    "origin",
    "preciousness",
    "visualParams"
})
public class Gem {

    @XmlElement(name = "Name", required = true)
    protected String name;

    @XmlElement(name = "Value", required = true)
    protected ValueType value;

    @XmlElement(name = "Origin", required = true)
    protected OriginsType origin;

    @XmlElement(name = "Preciousness", required = true)
    @XmlSchemaType(name = "string")
    protected PreciousnessType preciousness;

    @XmlElement(name = "VisualParams", required = true)
    protected VisualParamType visualParams;

    @XmlAttribute(name = "GemID", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    protected String gemID = "uuid_" + UUID.randomUUID().toString();

    public Gem() {
    }

    /**
     * Constructor
     * @param name
     * @param value
     * @param origin
     * @param preciousness
     * @param visualParams
     * @param gemID
     */
    public Gem(String name, ValueType value, OriginsType origin, PreciousnessType preciousness,
               VisualParamType visualParams, String gemID) {
        this.name = name;
        this.value = value;
        this.origin = origin;
        this.preciousness = preciousness;
        this.visualParams = visualParams;
        this.gemID = gemID;
    }

    /**
     * Constructor
     * @param name
     * @param value
     * @param weightName
     * @param continent
     * @param country
     * @param region
     * @param preciousness
     * @param colorName
     * @param transparencyValue
     * @param weightTransparency
     * @param quantitySides
     * @param quantitySideName
     * @param gemID
     */
    public Gem(String name,
               BigDecimal value, String weightName,
               ContinentType continent, String country, String region,
               PreciousnessType preciousness,
               ColorType colorName, int transparencyValue, String weightTransparency,
               int quantitySides, String quantitySideName,
               String gemID) {
        this.gemID = gemID;
        this.name = name;
        this.preciousness = preciousness;

        this.value = new ValueType(value, weightName);
        this.origin = new OriginsType(continent, country, region);

        this.visualParams = new VisualParamType(colorName, transparencyValue, weightTransparency,
                quantitySides, quantitySideName);
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link ValueType }
     *     
     */
    public ValueType getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link ValueType }
     *     
     */
    public void setValue(ValueType value) {
        this.value = value;
    }

    /**
     * Gets the value of the origin property.
     * 
     * @return
     *     possible object is
     *     {@link OriginsType }
     *     
     */
    public OriginsType getOrigin() {
        return origin;
    }

    /**
     * Sets the value of the origin property.
     * 
     * @param value
     *     allowed object is
     *     {@link OriginsType }
     *     
     */
    public void setOrigin(OriginsType value) {
        this.origin = value;
    }

    /**
     * Gets the value of the preciousness property.
     * 
     * @return
     *     possible object is
     *     {@link PreciousnessType }
     *     
     */
    public PreciousnessType getPreciousness() {
        return preciousness;
    }

    /**
     * Sets the value of the preciousness property.
     * 
     * @param value
     *     allowed object is
     *     {@link PreciousnessType }
     *     
     */
    public void setPreciousness(PreciousnessType value) {
        this.preciousness = value;
    }

    /**
     * Gets the value of the visualParams property.
     * 
     * @return
     *     possible object is
     *     {@link VisualParamType }
     *     
     */
    public VisualParamType getVisualParams() {
        return visualParams;
    }

    /**
     * Sets the value of the visualParams property.
     * 
     * @param value
     *     allowed object is
     *     {@link VisualParamType }
     *     
     */
    public void setVisualParams(VisualParamType value) {
        this.visualParams = value;
    }

    /**
     * Gets the value of the gemID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGemID() {
        return gemID;
    }

    /**
     * Sets the value of the gemID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGemID(String value) {
        this.gemID = value;
    }

    @Override
    public String toString() {
        return "Gem { gemID = " + gemID + "\n" +
                "Name = " + name  + "\n" +
                value.toString() + "\n" +
                "Preciousness = " + preciousness.value() + "\n" +
                origin.toString() + "\n" +
                visualParams.toString() + " }";
    }
}
