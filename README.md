# Java EE 6 Utils
**_Maven Dependencies_**<br/>
```
<dependency>
    <groupId>fr.wayis.framework</groupId>
    <artifactId>jee6-utils</artifactId>
    <version>1.0.0</version>
</dependency>
```
##Version 1.0.0

**_@ConfigProperty(String bundle, String key, boolean mandatory, String defaultValue)_**<br/>
This annotation is a CDI @Qualifier to manage configuration properties at the application deployment.

Example to use it:
```java
    /**
     * MongoDB server address.<br/>
     * Default value: 'localhost'.
     */
    @Inject
    @ConfigProperty(bundle = "mongodb", key = "mongodb.host", defaultValue = "localhost")
    private String host;
    /**
     * MongoDB port.<br/>
     * Default value: '27017'.
     */
    @Inject
    @ConfigProperty(bundle = "mongodb", key = "mongodb.port", defaultValue = "27017")
    private Integer port;
    /**
     * MongoDB database to use.<br/>
     * If the database does not exist, it will be created after the construction of this singleton EJB class
     */
    @Inject
    @ConfigProperty(bundle = "mongodb", key = "mongodb.dbname", mandatory = true)
    private String dbName;
```