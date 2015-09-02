package com.epam.education.XMLParsing.entityObjects.enums;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for continentType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="continentType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Afrique"/>
 *     &lt;enumeration value="Australia"/>
 *     &lt;enumeration value="Azia"/>
 *     &lt;enumeration value="Europe"/>
 *     &lt;enumeration value="North America"/>
 *     &lt;enumeration value="South America"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "continentType", namespace = "http://www.epam.education.com/Gems")
@XmlEnum
public enum ContinentType {

    @XmlEnumValue("Afrique")
    AFRIQUE("Afrique"),

    @XmlEnumValue("Australia")
    AUSTRALIA("Australia"),

    @XmlEnumValue("Azia")
    AZIA("Azia"),

    @XmlEnumValue("Europe")
    EUROPE("Europe"),

    @XmlEnumValue("North America")
    NORTH_AMERICA("North America"),

    @XmlEnumValue("South America")
    SOUTH_AMERICA("South America");

    private final String value;

    ContinentType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ContinentType fromValue(String v) {
        for (ContinentType c: ContinentType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }

        throw new IllegalArgumentException(v);
    }

}
