package com.github.mdvinyaninov.tools.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
@AllArgsConstructor
public class PanelVariable {
    public static final String FORMAT = "var-%s=%s";

    @XmlAttribute
    @Getter
    @Setter
    private String name;
    @XmlValue
    @Getter
    @Setter
    private String value;

    public String getUrlValue() {
        return String.format(FORMAT, this.name, this.value);
    }

}
