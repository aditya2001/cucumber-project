package Utils;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Lib {
    public static Properties configdata= new Properties();
    public static ThreadLocal<String> browser= new ThreadLocal<String>();
    public static Logger logger = LogManager.getLogger(Lib.class.getName());
    static String testName;

    public static Properties ReadConfigData(String environment, String application) throws Exception{
          File file= new File("src/main/resources" + "//" + environment + "//" + application + ".xml");
        FileInputStream fileInput= new FileInputStream(file);
        configdata.loadFromXML(fileInput);
        return configdata;

    }

    public static void maximizeBrowser(WebDriver driver) throws Exception{
        driver.manage().window().maximize();
    }



    public static synchronized WebDriver initiateWebDriver(String Browser) throws Exception{

        Integer nWait=60;
        String sGrid="false";
        String sHub="http://<machinename or IP Address>:4444/wd/hub";
        String sUserDir=System.getProperty("user.dir").toString();
        WebDriver driver = null;
        if(Browser==null)
            browser.set("FF");
        else
            browser.set(Browser);
        switch(browser.get().toUpperCase()){
            case "IE":
                System.setProperty("webdriver.ie.driver", sUserDir + "\\IEDriverServer.exe");
                DesiredCapabilities dc= new DesiredCapabilities();

                dc.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
                dc.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);
                dc.setCapability(InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR, true);
                dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                dc.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);

                dc.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                dc.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);

                driver= new InternetExplorerDriver(dc);
                break;

            case "CHROME":
                System.setProperty("webdriver.chrome.driver", sUserDir +"//chromedriver.exe");
                System.setProperty("webdriver.chrome.logfile", "./chromedriver.log");
                System.setProperty("webdriver.chrome.verboseLogging", "true");
                ChromeOptions chromeOptions= new ChromeOptions();
                chromeOptions.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
                Map<String, Object> prefs= new HashMap<String, Object>();
                prefs.put("profile.default_content_settings.popups", 0);
                prefs.put("download.default_directory", System.getProperty("java.io.tmpdir")+ "\\" + testName);
                prefs.put("download.prompt_for_download", false);
                chromeOptions.setExperimentalOption("prefs", prefs);
                chromeOptions.addArguments("--test-type");
                driver= new ChromeDriver(chromeOptions);
                break;

            case "FF":
            case "FF-DEBUG":
                if(sGrid.trim().toLowerCase().equals("true")){
                    DesiredCapabilities capability= DesiredCapabilities.firefox();
                    capability.setBrowserName("firefox");
                    driver= new RemoteWebDriver( new URL(sHub), capability);
                }else {
                    FirefoxProfile profile;
                    if( browser.get().toUpperCase().equals("FF-DEBUG")){
                        ProfilesIni allProfiles= new ProfilesIni();
                        profile=allProfiles.getProfile("default");

                    }else {
                        profile= new FirefoxProfile();
                    }
                    profile.setAcceptUntrustedCertificates(true);
                    profile.setAssumeUntrustedCertificateIssuer(true);
                    profile.setPreference("browser.download.folderlist", 2);
                    profile.setPreference("browser.download.manager.showWhenStarting", false);
                    profile.setPreference("browser.download.dir", System.getProperty("java.io.tmdir")+ "\\" + testName);
                    profile.setPreference("browser.helperApps.neverAsk.openFile",
                            "text/csv/application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml");
                    profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
                            "text/csv/application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml");
                    profile.setPreference("browser.helperApps.alwaysAsk,force",false);
                    profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
                    profile.setPreference("browser.download.manager.focusWhenStarting", false);
                    profile.setPreference("browser.download.manager.useWindow", false);
                    profile.setPreference("browser.download.manager.showAlertOnComplete", false);
                    profile.setPreference("browser.download.manager.closeWhenDone", false);
                    driver= new FirefoxDriver((Capabilities) profile);

                }
                break;
            default:
                break;


        }
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(nWait, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(nWait, TimeUnit.SECONDS);
        return driver;


    }

    public static WebElement getObject(WebDriver driver, String objName) {
        String identifier;
        String description;
        WebElement element = null;
        By SearchBy = null;

        try {
            if (objName.equals(null) || objName.trim().equals("")) {
                throw new Exception("Object description not provided");

            } else {
                identifier = objName.split("~")[0].trim();
                description = objName.split("~")[1].trim();
                if (identifier.equals("xpath")) {
                    SearchBy = By.xpath(description);
                }
                else if (identifier.equals("name")) {
                    SearchBy = By.name(description);
                }
                else if (identifier.equals("id")) {
                    SearchBy = By.name(description);
                } else if (identifier.equals("css")) {
                    SearchBy = By.name(description);
                } else if (identifier.equals("tagname")) {
                    SearchBy = By.name(description);
                } else if (identifier.equals("partialinktext")) {
                    SearchBy = By.name(description);
                } else if (identifier.equals("classname")) {
                    SearchBy = By.name(description);
                } else {
                    throw new Exception("Object description/search type not valid");
                }
                driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
                WebDriverWait wait = new WebDriverWait(driver, 60);
                wait.until(ExpectedConditions.visibilityOfElementLocated(SearchBy));
                element = driver.findElement(SearchBy);

                JavascriptExecutor jse = (JavascriptExecutor) driver;
                jse.executeScript("arguments[0].scrollIntoView()", element);
            }

        } catch (Exception e) {
               logger.error("Unable to locate object", e);
        }
        return element;

    }

    public static WebElement getObject(WebDriver driver, String objName, Integer time) {
        String identifier;
        String description;
        WebElement element = null;
        By SearchBy = null;

        try {
            if (objName.equals(null) || objName.trim().equals("")) {
                throw new Exception("Object description not provided");

            } else {
                identifier = objName.split("~")[0].trim();
                description = objName.split("~")[1].trim();
                if (identifier.equals("xpath")) {
                    SearchBy = By.xpath(description);
                }
               else if (identifier.equals("name")) {
                    SearchBy = By.name(description);
                }
                else if (identifier.equals("id")) {
                    SearchBy = By.name(description);
                } else if (identifier.equals("css")) {
                    SearchBy = By.name(description);
                } else if (identifier.equals("tagname")) {
                    SearchBy = By.name(description);
                } else if (identifier.equals("partialinktext")) {
                    SearchBy = By.name(description);
                } else if (identifier.equals("classname")) {
                    SearchBy = By.name(description);
                } else {
                    throw new Exception("Object description/search type not valid");
                }
                driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
                WebDriverWait wait = new WebDriverWait(driver, 60);
                wait.until(ExpectedConditions.visibilityOfElementLocated(SearchBy));
                Thread.sleep(time);
                element = driver.findElement(SearchBy);

                JavascriptExecutor jse = (JavascriptExecutor) driver;
                jse.executeScript("arguments[0].scrollIntoView()", element);
            }

        } catch (Exception e) {
            logger.error("Unable to locate object", e);
        }
        return element;

    }


    public static WebElement getObject(WebDriver driver, String objName, String condition) {
        String identifier;
        String description;
        WebElement element = null;
        By SearchBy = null;

        try {
            if (objName.equals(null) || objName.trim().equals("")) {
                throw new Exception("Object description not provided");

            } else {
                identifier = objName.split("~")[0].trim();
                description = objName.split("~")[1].trim();
                if (identifier.equals("xpath")) {
                    SearchBy = By.xpath(description);
                }
                else if (identifier.equals("name")) {
                    SearchBy = By.name(description);
                }
                else if (identifier.equals("id")) {
                    SearchBy = By.name(description);
                } else if (identifier.equals("css")) {
                    SearchBy = By.name(description);
                } else if (identifier.equals("tagname")) {
                    SearchBy = By.name(description);
                } else if (identifier.equals("partialinktext")) {
                    SearchBy = By.name(description);
                } else if (identifier.equals("classname")) {
                    SearchBy = By.name(description);
                } else {
                    throw new Exception("Object description/search type not valid");
                }
                if (condition.equals("elementSelectionStateToBe")){
                    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
                    WebDriverWait wait = new WebDriverWait(driver, 60);
                    wait.until(ExpectedConditions.elementSelectionStateToBe(SearchBy,false));
                    element = driver.findElement(SearchBy);

                }
                else if (condition.equals("elementToBeSelected")){
                    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
                    WebDriverWait wait = new WebDriverWait(driver, 60);
                    wait.until(ExpectedConditions.elementToBeSelected(SearchBy));
                    element = driver.findElement(SearchBy);

                }

                JavascriptExecutor jse = (JavascriptExecutor) driver;
                jse.executeScript("arguments[0].scrollIntoView()", element);
            }

        } catch (Exception e) {
            logger.error("Unable to locate object", e);
        }
        return element;

    }



    public static boolean doesObjectExist(WebDriver driver, String objName) throws Exception{
        String identifier;
        String description;
        boolean returnVal=true;
        By SearchBy=null;
        if (objName.equals(null) || objName.trim().equals("")){
            throw new Exception ("Object description not provided");

        }else {
            identifier= objName.split("~")[0].trim();
            description= objName.split("~")[1].trim();
            if (identifier.equals("xpath")){
                SearchBy=By.xpath(description);
            }
            else if (identifier.equals("name")){
                SearchBy= By.name(description);
            }
            else if (identifier.equals("id")){
                SearchBy= By.name(description);
            }
            else if (identifier.equals("css")){
                SearchBy= By.name(description);
            }
            else if (identifier.equals("tagname")){
                SearchBy= By.name(description);
            }
            else if (identifier.equals("partialinktext")){
                SearchBy= By.name(description);
            }
            else if (identifier.equals("classname")){
                SearchBy= By.name(description);
            }
            else{
                throw new Exception ("Search type is Not valid");
            }
            try{
                driver.findElement(SearchBy);

            }catch (Exception e) {
                returnVal=false;
            }
            return returnVal;
        }

    }

      public static Map<String, String> convertListToMap(List<Map<String, String>> ffelements){
          Map<String, String> finalMap = null;
        try {
            finalMap = ffelements
                    .stream()
                    .reduce((firstMap, secondMap) -> {
                        firstMap.putAll(secondMap);
                        return firstMap;
                    }).orElse(null);

        }
          catch(Exception e){
              e.printStackTrace();
            }
          return finalMap;
      }


    public static void selectDropDownValue(WebDriver driver, String ObjName, String valueToSelect) throws Exception{
        WebElement dropdownlist= getObject(driver,ObjName);
        Select dropdownval= new Select(dropdownlist);
        dropdownval.selectByVisibleText(valueToSelect);
    }

    public static void selectDropDownByIndex(WebDriver driver, String ObjName, int index) throws Exception{
        WebElement dropdownlist= getObject(driver,ObjName);
        Select dropdownindex= new Select(dropdownlist);
        dropdownindex.selectByIndex(index);

    }

    public static void click(WebDriver driver, String objName, Integer time) throws Exception {
        WebElement element = getObject(driver, objName, time);
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        Thread.sleep(time);
        element.click();
    }

    public static void click(WebDriver driver, String objName){
        getObject(driver,objName).click();
    }

   public static void acceptAlert(WebDriver driver){
        Alert alert = driver.switchTo().alert();
        alert.accept();
   }

    public static java.sql.Connection setUpDBConnection(String DB, String url, String username, String password)
            throws ClassNotFoundException, SQLException {
        java.sql.Connection con = null;
        switch (DB) {
            case "ORACLE":
                Class.forName("Oracle.jdbc.OracleDriver");
                con = DriverManager.getConnection(url, username, password);
                break;

            case "DB2":
                Class.forName("com.ibm.db2.jcc.DB2Driver");
                //DriverManager.registerDriver(new com.ibm.as400.access.AS400JDBCDriver());
                con = DriverManager.getConnection(url, username, password);
                break;

            default:
                System.out.println("Invalid DB");
        }
        return con;
    }

    public static void compare(Map<String, String> actualResult, Map<String, String> expectedResult){
        String[] expectedKeys = extractKeys(expectedResult);
        for (String key : expectedKeys) {
            logger.info("Expected value for key :" + key + " = " + expectedResult.get(key) + "\"");
            logger.info("Actual value for key :" + key + " = " + actualResult.get(key) + "\"");
            Assert.assertEquals("Validation failed", actualResult.get(key), expectedResult.get(key));
        }
    }

    public static String[] extractKeys(Map<String, String> expectedResult){
        String[] extractedKey = new String[expectedResult.size()];
        Set<String> keySet = expectedResult.keySet();
        int i=0;
        for (String key :keySet){
            extractedKey[i]=key;
            i++;
        }
        return extractedKey;
    }


}
