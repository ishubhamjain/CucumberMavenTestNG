package Utilities;
/**
 * @author Shubham Jain
 *
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration 
{
    private Properties properties = new Properties();
    private static Configuration globalConfiguration = null;
    // TODO: figure out how we can have a default config file for framework specified.
 //   private final static String DEFAULT_CONFIG_PATH = "src/automationframework/defaultconfig.properties";
    private final static String DEFAULT_CONFIG_PATH = "configuration/config.properties";

    protected Configuration()
    {
        // Load default configuration from framework specific location.
        loadAllProperties(DEFAULT_CONFIG_PATH);
    }

    private void loadAllProperties(String configFilePath) 
    {
        properties = new Properties();
        try 
        {
            properties.load(new FileInputStream(configFilePath));
        }
        catch (IOException e) 
        {
            throw new RuntimeException("Could not read the properties file");
        }
    }

    public void setGlobalConfigurationFile(String configFile)
    {
        reloadAllProperties(configFile);
    }

    private void reloadAllProperties(String configFile)
    {
        loadAllProperties(configFile);
    }

    public static Configuration globalConfiguration()
    {
        if (globalConfiguration == null)
        {
            globalConfiguration = new Configuration();
        }
        return globalConfiguration;
    }

    private String readConfigurationProperty(String propertyName) 
    {
        String defaultValue = "";
        return properties.getProperty(propertyName, defaultValue);
    }

    public static String getConfigurationValueForProperty(String propertyName)
    {
        return Configuration.globalConfiguration().readConfigurationProperty(propertyName);
    }

    public static String applicationUnderTestURL()
    {
    	return "http://" + Configuration.getConfigurationValueForProperty("applicationURL");
    }
    
    public static String RemoteURLIPAndPort()
    {
    	return Configuration.getConfigurationValueForProperty("RemoteIPAndPort");
    }
    
    public static String getExcelFile()
    {
    	String excelvalue = Configuration.getConfigurationValueForProperty("Excelfile");
    	return excelvalue;		
    }

    public static String getValidEmail()
    {
         String email = Configuration.getConfigurationValueForProperty("email");
        return email;
    }

    public static String getPassword()
    {
        String password = Configuration.getConfigurationValueForProperty("password");
        return password;
    }
    
    public static String macFirefoxName()
    {
        String name = Configuration.getConfigurationValueForProperty("FirefoxName");
        return name;
    }
    
    public static String executionType()
    {
        String type = Configuration.getConfigurationValueForProperty("execution-type");
        return type;
    }
    
    public static String LoginRequiredEveryTime()
    {
        String type = Configuration.getConfigurationValueForProperty("execution-type");
        return type;
    }
    
    public static String ReportInitialName()
    {
        String reportName = Configuration.getConfigurationValueForProperty("Report-Initial-Name");
        return reportName;
    }
    
    public static String ReportUploadPlatformName()
    {
        String PlatformName = Configuration.getConfigurationValueForProperty("Report-Upload-Platform-Name");
        return PlatformName;
    }
    
    public static String emailForsendingnotification()
    {
        String PlatformName = Configuration.getConfigurationValueForProperty("Email-Sending-Notification");
        return PlatformName;
    }
    
    public static String PasswordForsendingnotification()
    {
        String PlatformName = Configuration.getConfigurationValueForProperty("Pass-Sending-Notification");
        return PlatformName;
    }
    
    ////////////////
    
    static String XMLFileName = "MMFeed.xml";

    public static String getFXTestDataSheetForMM() {
//    	FXTestData = "./utilities/TestData/MMTestDataTest.xlsx";
    String FXTestData = "./utilities/TestData/MMTestData.xlsx";
    return FXTestData;
    }

    public static String getDealPushTemplatePathForMM() {
    return "./utilities/XMLGenFiles/XMLTemplateForMM/"+XMLFileName;	
    }


    /*public static String getCMSPushSwiftXMLTemplatePathForMM() {
    return "./utilities/XMLGenFiles/XMLTemplate/CMS.xml";	
    }	*/

    public static String getXMLPumpingFolderPathForMM() {
    return "./utilities/XMLGenFiles/Generatedxml/";	
    }

    public static String getNewDealXMLPathForMM(){
    String NewXMLPath = "./utilities/XMLGenFiles/Generatedxml/"+XMLFileName;	
    return NewXMLPath;
    }

    public static String getScriptPropertyPath() {
    String mmscriptPropVal = "./config/mmscript.properties";
    return mmscriptPropVal;
    }
}
