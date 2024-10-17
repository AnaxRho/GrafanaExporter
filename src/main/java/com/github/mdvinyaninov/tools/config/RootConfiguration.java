package com.github.mdvinyaninov.tools.config;

import com.github.mdvinyaninov.tools.XMLUtils;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlRootElement(name = "Configuration")
@XmlType(propOrder = {"host", "apikey", "destination", "dashboard", "dashboardUID", "timezone", "timeout",
        "downloadAttempts", "globalParams", "globalVariables", "panels"})
public class RootConfiguration {
    @Setter
    private String host;
    @Setter
    private String dashboard;
    @Setter
    private String dashboardUID;
    @Setter
    private String apikey;
    @Setter
    private List<Param> globalParams;
    @Setter
    private List<PanelVariable> globalVariables;
    @Setter
    private String destination;
    @Setter
    private String timezone;
    @Setter
    private int timeout;
    @Setter
    private int downloadAttempts = 3;
    @Setter
    private List<PanelConfig> panels;

    public static RootConfiguration loadConfig(String filepath) throws Exception {
        return (RootConfiguration) XMLUtils.loadRemote(RootConfiguration.class, filepath);
    }

    public String toString() {
        return XMLUtils.produceXml(RootConfiguration.class, this);
    }

    @XmlElement(name = "Host")
    public String getHost() { return host; }
    @XmlElement(name = "Dashboard")
    public String getDashboard() { return dashboard; }
    @XmlElement(name = "DashboardUID")
    public String getDashboardUID() { return dashboardUID; }
    @XmlElement(name = "ApiKey")
    public String getApikey() { return apikey; }
    @XmlElement(name = "Destination")
    public String getDestination() {
        return destination;
    }
    @XmlElement(name = "Timezone")
    public String getTimezone() {
        return timezone;
    }
    @XmlElement(name = "Timeout")
    public int getTimeout() {
        return timeout;
    }
    @XmlElement(name = "DownloadAttempts")
    public int getDownloadAttempts() { return downloadAttempts; }
    @XmlElement(name = "Param")
    public List<Param> getGlobalParams() { return globalParams; }
    @XmlElement(name = "Var")
    public List<PanelVariable> getGlobalVariables() { return globalVariables; }
    @XmlElement(name = "Panel")
    public List<PanelConfig> getPanels() { return panels; }
}
