package com.epam.education.XMLParsing.entityObjects.enums;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for preciousnessType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="preciousnessType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Jewel"/>
 *     &lt;enumeration value="NoJewel"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "preciousnessType", namespace = "http://www.epam.education.com/Gems")
@XmlEnum
public enum PreciousnessType {

    @XmlEnumValue("Jewel")
    JEWEL("Jewel"),

    @XmlEnumValue("NoJewel")
    NO_JEWEL("NoJewel");

    private final String value;

    PreciousnessType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PreciousnessType fromValue(String v) {
        for (PreciousnessType c: PreciousnessType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
