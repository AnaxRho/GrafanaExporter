package com.github.mdvinyaninov.tools.config;

import lombok.Getter;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class PanelConfig {
    @Getter
    private long panelId;
    @Getter
    private long width = 1000;
    @Getter
    private long height = 500;
    @Getter
    private String name;
    @Getter
    private String folder;
    @Getter
    private List<Param> panelParams;
    @Getter
    private List<PanelVariable> panelVariables;

    @XmlElement(name = "PanelId")
    public void setPanelId(long panelId) { this.panelId = panelId; }
    @XmlElement(name = "Name")
    public void setName(String name) { this.name = name; }
    @XmlElement(name = "Folder")
    public void setFolder(String folder) { this.folder = folder; }
    @XmlElement(name = "Width")
    public void setWidth(long width) { this.width = width; }
    @XmlElement(name = "Height")
    public void setHeight(long height) { this.height = height; }
    @XmlElement(name = "Param")
    public void setPanelParams(List<Param> panelParams) { this.panelParams = panelParams; }
    @XmlElement(name = "Var")
    public void setPanelVariables(List<PanelVariable> panelVariables) { this.panelVariables = panelVariables; }
}
