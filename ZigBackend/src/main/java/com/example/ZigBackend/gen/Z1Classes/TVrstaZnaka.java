//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.01.11 at 12:28:33 AM CET 
//


package com.example.ZigBackend.gen.Z1Classes;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TVrstaZnaka.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TVrstaZnaka"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="verbalni"/&gt;
 *     &lt;enumeration value="graficki"/&gt;
 *     &lt;enumeration value="kombinovani"/&gt;
 *     &lt;enumeration value="trodimenzionalni"/&gt;
 *     &lt;enumeration value="drugo"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "TVrstaZnaka")
@XmlEnum
public enum TVrstaZnaka {

    @XmlEnumValue("verbalni")
    VERBALNI("verbalni"),
    @XmlEnumValue("graficki")
    GRAFICKI("graficki"),
    @XmlEnumValue("kombinovani")
    KOMBINOVANI("kombinovani"),
    @XmlEnumValue("trodimenzionalni")
    TRODIMENZIONALNI("trodimenzionalni"),
    @XmlEnumValue("drugo")
    DRUGO("drugo");
    private final String value;

    TVrstaZnaka(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TVrstaZnaka fromValue(String v) {
        for (TVrstaZnaka c: TVrstaZnaka.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}