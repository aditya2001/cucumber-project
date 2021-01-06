package Utils;


import com.google.gson.Gson;
import com.ntrs.thb.TestOutbound;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthJSONAccessTokenResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.message.types.GrantType;
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

import javax.net.ssl.*;
import java.io.*;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Lib {
    public static Properties configdata= new Properties();
    public static ThreadLocal<String> browser= new ThreadLocal<String>();
    public static Logger logger = LogManager.getLogger(Lib.class.getName());
    static String testName;
    public static String bearerValue=null;
    public static String finalToken=null;
    private static CloseableHttpClient httpClient;

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

    public static String httpGet(String getURL) throws Exception {
        org.testng.Assert.assertNotNull(bearerValue, "Authorization token should not be null");
        String result=null;
        HttpGet httpGet= new HttpGet(getURL);

        httpGet.setHeader("Accept", "application/json");
        httpGet.setHeader("Authorization", "bearervalue");
        Thread.sleep(4000);
        final HttpResponse getResponse= httpClient.execute(httpGet);
        HttpEntity getResEntity = getResponse.getEntity();
        if (getResponse.getStatusLine().getStatusCode() ==200) {
            result= EntityUtils.toString(getResEntity, "UTF-8");
        }

        getResponse.getEntity().getContent().close();
        return result;
    }


    public static String httpPost(String fileName, String postURL) throws Exception{
//        org.testng.Assert.assertNotNull(bearerValue, "Authorization token should not be null");
        String result=null;
        File input= new File(fileName);
        HttpPost httppost= new HttpPost(postURL);
        setAccessTokenValue();
        httppost.setHeader("Content-Type", "application/json");
        httppost.setHeader("Authorization", bearerValue);
        InputStreamEntity reqEntity= new InputStreamEntity(new FileInputStream(input), -1);
        reqEntity.setContentType("application/json");
        reqEntity.setChunked(true);
        httppost.setEntity(reqEntity);
        final CloseableHttpResponse postResponse= httpClient.execute(httppost);
        HttpEntity resEntity= postResponse.getEntity();
        if (postResponse.getStatusLine().getStatusCode() ==201){
            result=EntityUtils.toString(resEntity, "UTF-8");
            System.out.println("orderId"+result);
        }
        return result;
    }


    public static List<String> getFiles(String folderName) {
        List<String> fileNames = new ArrayList<>();
        File folder = new File(folderName);
        for (final File fileEntry : folder.listFiles()) {
            if (!fileEntry.isAbsolute()) {
                fileNames.add(fileEntry.getAbsolutePath());
            }
        }
        return fileNames;
    }



    public static String getBase64Encoded(String id, String password) throws UnsupportedEncodingException {
        return Base64.getEncoder().encodeToString((id + ":" + password).getBytes("UTF-8"));
    }

    public static void setAccessTokenValue() {
        try {
            OAuthClient client = new OAuthClient(new URLConnectionClient());

            String encodedValue = getBase64Encoded(TestOutbound.CLIENT_ID, TestOutbound.CLIENT_SECRET);

            OAuthClientRequest request = OAuthClientRequest
                    .tokenLocation(TestOutbound.TOKEN_REQUEST_URL)
                    .setGrantType(GrantType.CLIENT_CREDENTIALS)
                    .buildBodyMessage();
            request.addHeader("Authorization", "Basic " +encodedValue);

            OAuthJSONAccessTokenResponse oAuthResponse = client.accessToken(request,OAuth.HttpMethod.POST, OAuthJSONAccessTokenResponse.class);
            finalToken = oAuthResponse.getAccessToken();
            org.testng.Assert.assertNotNull(finalToken, "finaltoken should not be null");
            bearerValue= "Bearer " +finalToken;
        }catch (Exception exn) {
            exn.printStackTrace();

        }finally {
            if (bearerValue == null) {
                bearerValue = "Bearer " + "M2Ptpdsfsdfsdfsdfsdfsdfsdf";
            }
        }

    }

    public static SSLConnectionSocketFactory getSSLConnectionSocketfactory()
            throws NoSuchAlgorithmException, KeyManagementException {

        SSLConnectionSocketFactory scf= null;
        TrustManager[] trustAllCerts= new TrustManager[] { new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }

            public void checkClientTrusted(X509Certificate[] certs, String authType) {

            }
            public void checkServerTrusted(X509Certificate[] certs, String authType) {

            }
        } };

        try {
            SSLContext sc = SSLContext.getInstance("TLSv1.2");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            scf = new SSLConnectionSocketFactory(sc, new String[] { "TLSv1.2" }, null,
                    SSLConnectionSocketFactory.getDefaultHostnameVerifier());
        } catch (GeneralSecurityException e) {
        }


        return scf;
    }



    static {
        try {
            HttpClientBuilder clientBuilder = HttpClients.custom();
            clientBuilder.setRetryHandler(new DefaultHttpRequestRetryHandler(3, false));

            httpClient = clientBuilder.setSSLSocketFactory(getSSLConnectionSocketfactory()).build();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }


    public static Map<String, Object> convertJsonToMap(String jsonString) {
        Gson gson = new Gson();

        Map<String, Object> jsonMap = gson.<Map<String, Object>> fromJson(jsonString, Map.class);
        return jsonMap;
    }

    @SuppressWarnings("rawtypes")
    public static List convertJsonToList(String jsonString) {
        Gson gson = new Gson();


        List jsonMap = gson.<List> fromJson(jsonString, List.class);
        return jsonMap;
    }

    public static String readTextFIle(String file) throws IOException {
        FileReader fr = new FileReader(file);
        BufferedReader reader = new BufferedReader(fr);
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");

        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }
        reader.close();
        return stringBuilder.toString();
    }


}
