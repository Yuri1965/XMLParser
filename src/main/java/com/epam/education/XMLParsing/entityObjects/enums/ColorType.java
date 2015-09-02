package com.epam.education.XMLParsing.entityObjects.enums;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for colorType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="colorType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="WHITE"/>
 *     &lt;enumeration value="RED"/>
 *     &lt;enumeration value="GREEN"/>
 *     &lt;enumeration value="YELLOW"/>
 *     &lt;enumeration value="BLUE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "colorType", namespace = "http://www.epam.education.com/Gems")
@XmlEnum
public enum ColorType {

    WHITE,
    RED,
    GREEN,
    YELLOW,
    BLUE;

    public String value() {
        return name();
    }

    public static ColorType fromValue(String v) {
        return valueOf(v);
    }

}
