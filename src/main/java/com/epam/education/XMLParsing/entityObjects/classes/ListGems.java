package com.epam.education.XMLParsing.entityObjects.classes;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="gem" type="{http://www.epam.education.com/Gems}Gem" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"gem"})
@XmlRootElement(name = "ListGems", namespace = "http://www.epam.education.com/Gems")
public class ListGems {

    @XmlElement(required = true)
    protected List<Gem> gem;

    /**
     * Gets the value of the gem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the gem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Gem }
     * 
     * 
     */
    public List<Gem> getGem() {
        if (gem == null) {
            gem = new ArrayList<Gem>();
        }

        return this.gem;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("ListGems:\n\n");

        for (Gem gemChose : gem) {
            result.append(gemChose.toString() + "\n\n");
        }

        return result.toString();
    }
}
