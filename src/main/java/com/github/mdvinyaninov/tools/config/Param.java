package com.github.mdvinyaninov.tools.config;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
public class Param {
    public static final String FORMAT = "%s=%s";
    @XmlAttribute
    @Getter
    @Setter
    private String name;
    @XmlValue
    @Getter
    @Setter
    private String value;

    public String getUrlValue() {
        return String.format(FORMAT, this.name, this.value); }
}
