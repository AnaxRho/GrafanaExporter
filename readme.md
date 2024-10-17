# GrafanaExporter

+ [Introduction](#introduction)
+ [Command Line Interface](#cli)
+ [XML config](#xml-config)

## Introduction

GrafanaExporter is a utility program for saving batches of chart .PNG images from specific [Grafana](https://grafana.com/grafana/) dashboard with configured [grafana-image-renderer](https://grafana.com/grafana/plugins/grafana-image-renderer/) plugin.

> [RU] 
> Утилита для сохранения PNG изображений графиков с определенного дашборда [Grafana](https://grafana.com/grafana/) с настроенным плагином [Grafana Image Renderer](https://grafana.com/grafana/plugins/grafana-image-renderer/). 

GrafanaExporter uses `grafana-image-renderer`-provided HTTP API interface to download and save chart images.

Download URL for Grafana with `grafana-image-renderer` example:
`http://grafana_host:3000/render/d-solo/PS4sLaC7z/sample-dashboard?panelId=1&from=now-1h&to=now&theme=light&width=1000&height=500&tz=Europe%2FMoscow&timeout=120`

## CLI

Usage: `java -jar GrafanaExporter.jar [options]`

Options:

| Option                | Type      | Description                                                                                                                                                                                    |
|-----------------------|-----------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `--config`, `-c`      | Required  | Path to GrafanaExporter XML configuration file                                                                                                                                                 |
| `--from`, `-f`        | Required  | Value to use as time range 'from' part for charts.<br/> Absoulute, relative, unixtime should work. See [documentation](https://grafana.com/docs/grafana/v9.0/dashboards/time-range-controls/). |
| `--to`, `-t`          | Required  | Value to use as time range 'to' part for charts. Value format is the same as above.                                                                                                            |
| `--concurrency`       | Optional  | Number of concurrent downloads. Default: 1                                                                                                                                                     |
| `--destination`, `-d` | Optional  | Destination path for saving images files. Overrides destination specified in XML configuration file                                                                                            |
| `--prefix`, `-p`      | Optional  | Prefix to add to all file names specified in XML configuration file                                                                                                                            |
| `--help`, `-h`        | Optional  | Show usage help                                                                                                                                                                                |

### Run Examples

YYYY-MM-DD'T'HH24:MI:SS time format:
```shell
    java -jar GrafanaExporter.jar --config C:\config.xml --from 2020-01-01T10:00:00 --to 2020-01-01T11:00:00
````

Relative time:
```shell
    java -jar GrafanaExporter.jar -c C:\config.xml -f now-1h -t now
````

Epoch millis time:
```shell
    java -jar GrafanaExporter.jar -c C:\config.xml -f 1655308790000 -t 16553087960000
````


## XML config

### Config example

```xml
<Configuration>
    <Host>http://myhost:3000</Host>
    <Dashboard>sample-sashboard</Dashboard>
    <DashboardUID>SSVf6YRWk</DashboardUID>
    <ApiKey>AQ==</ApiKey>
    <Destination>C:\out</Destination>
    <Timezone>Europe/Moscow</Timezone>
    <Timeout>120</Timeout>
    <Param name="theme">light</Param>
    <Panel>
        <PanedId>1</PanedId>
        <Name>cpu_usage</Name>
        <Folder>myhost</Folder>
        <Width>1000</Width>
        <Height>500</Height>
        <Var name="host">myhost</Var>
    </Panel>
    <Panel>
        <PanedId>1</PanedId>
        <Name>cpu_usage</Name>
        <Folder>myhost</Folder>
        <Width>1000</Width>
        <Height>500</Height>
        <Var name="host">myhost2</Var>
    </Panel>
    <Panel>
        <PanedId>2</PanedId>
        <Name>mem_usage</Name>
        <Folder>myhost</Folder>
        <Width>1000</Width>
        <Height>500</Height>
        <Var name="host">myhost</Var>
    </Panel>
    <Panel>
        <PanedId>2</PanedId>
        <Name>mem_usage</Name>
        <Folder>myhost</Folder>
        <Width>1000</Width>
        <Height>500</Height>
        <Var name="host">myhost2</Var>
    </Panel>
</Configuration>
```

### Root XMLElement `Configuration`

| XML Element    | Type                     | Occurrence | Description                                                                                                          |
|----------------|--------------------------|------------|----------------------------------------------------------------------------------------------------------------------|
| `Host`         | String                   | {1}        | Grafana base URL. <br/> Example:  http://localhost:3000                                                              |
| `Dashboard`    | String                   | {1}        | Dashboard name. <br/> Example: <span>http://</span>localhost/grafana/d/5FGhJhv4z/***sample-sashboard***?orgId=1      |
| `DashboardUID` | String                   | {1}        | Dashboard unique ID. <br/> Example: <span>http://</span>localhost/grafana/d/***5FGhJhv4z***/sample-sashboard?orgId=1 |
| `ApiKey`       | String                   | {1}        | Grafana API key. See [documentation](https://grafana.com/docs/grafana/latest/administration/api-keys/)               |
| `Destination`  | String                   | {1}        | Destination directory to save images. Example: `C:\out`                                                              |
| `Timezone`     | String                   | {0, 1}     | Timezone. Example: `Europe/Moscow`                                                                                   |
| `Timeout`      | Integer                  | {0, 1}     | Timeout for downloads. Example: `120`                                                                                |
| `Param`        | Array    | {0,}          | Array of Grafana params to pass in `%name%=%value%` format. Examples: `theme=light` |
| `Var`          | Array    | {0,}          | Array of Grafana variables to pass in `var-%name%=%value%` for dashboard variables. Examples: `var-host=myhost` |
| `Panel`        | Array of 'Panel' element | {0,}       | List of panel specific information.                                                                                  |

### Child Element `Graph`

| XML Element  | Type     | Occurrence    | Description                                                                                                                                    |
|--------------|----------|---------------|------------------------------------------------------------------------------------------------------------------------------------------------|
| `PanedId`    | String   | {1}           | Id of dashboard panel. <br/> Example: <span>http://</span>localhost/grafana/d/5FGhJhv4z/sample-sashboard?viewPanel=***1***&orgId=1             |
| `Name`       | String   | {1}           | Name for chart image file. Example: `cpu_usage_myhost`                                                                                         |
| `Folder`     | String   | {0,1}         | Subfolder to save image to. Example: `myhost`                                                                                                  |
| `Width`      | Integer  | {1}           | Image width in pixes. Example: `1000`                                                                                                          |
| `Height`     | Integer  | {1}           | Image height in pixes. Example: `500`                                                                                                          |
| `Param`      | Array    | {0,}          | Array of Grafana params to pass in `%name%=%value%` format. Examples: `theme=light` |
| `Var`        | Array    | {0,}          | Array of Grafana variables to pass in `var-%name%=%value%` for dashboard variables. Examples: `var-host=myhost` |
