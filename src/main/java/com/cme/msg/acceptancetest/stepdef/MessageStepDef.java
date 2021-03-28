package com.cme.msg.acceptancetest.stepdef;

import Utils.Lib;
import Utils.Obj;
import Utils.Orep;
import com.cme.msg.acceptancetest.CucumberRunner;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class MessageStepDef {
    public static ThreadLocal<WebDriver> driverlocal = new ThreadLocal<WebDriver>();
    public WebDriver driver = null;

    Product pro = new Product();

    @Given("^Application is launched using \"([^\"]*)\"$")
    public void launchApp(String URL) throws Exception {
        driverlocal.set(Lib.initiateWebDriver("CHROME"));
        driver = GetDriver();
        driver.get(Lib.configdata.getProperty(URL));

    }


    public WebDriver GetDriver() {
        return driverlocal.get();
    }


    @When("^Admin User sends a Logon message to UI$")
    public void
    adminUserSendsALogonMessageToUI() throws Exception {


        Lib.getObject(driver, Orep.CredentialClass.userId).sendKeys(CucumberRunner.username);
        Lib.getObject(driver, Orep.CredentialClass.password).sendKeys(CucumberRunner.password);
        Lib.getObject(driver, Orep.CredentialClass.login).click();
        //   driver.close();
        Lib.maximizeBrowser(driver);


    }


    @Then("^Validate contents of the Portal$")
    public void validateContentsOfThePortal() throws Exception {

        Assert.assertTrue(Lib.doesObjectExist(driver, Orep.HomePage.dashboard));

    }


    @Then("^Close the application$")
    public void closeTheApplication() {
        driver.close();
    }

    @When("^User adds a new currency with$")
    public void userAddsANewCurrency(DataTable datatable) throws Exception {

        Lib.getObject(driver, Orep.General.gen).click();
        Lib.getObject(driver, Orep.General.currency).click();
        Lib.getObject(driver, Orep.General.add).sendKeys(Keys.RETURN);
        List<Map<String, String>> ffelements = datatable.asMaps(String.class, String.class);
//          Map<String, String> finalMap=Lib.convertListToMap(ffelements);
        Map<String, String> expectedResult = ffelements.get(0);
//        Lib.getObject(driver, Orep.General.Name).sendKeys(finalMap.get("Name"));
        Lib.getObject(driver, Orep.General.Name).sendKeys(pro.setName(expectedResult));
        Lib.getObject(driver, Orep.General.Symbol).sendKeys(expectedResult.get("Symbol"));
        Lib.getObject(driver, Orep.General.Code).sendKeys(expectedResult.get("Code"));
        Lib.getObject(driver, Orep.General.Rate).clear();
        Lib.getObject(driver, Orep.General.Rate).sendKeys(expectedResult.get("Rate"));
        Lib.selectDropDownValue(driver, Orep.General.Active, expectedResult.get("Active"));
        Lib.getObject(driver, Orep.General.Submit).click();
    }

//             Obj obj = new Obj();
//             obj.setName(map.get("Name"));
//             fieldKeyValue.put("Name", obj.getName());


    @Then("^Validate the currency details with$")
    public void validateTheCurrencyDetailsWith(DataTable datatable) throws Exception {
        List<Map<String, String>> ffelements = datatable.asMaps(String.class, String.class);
        Map<String, String> expectedResult = ffelements.get(0);
        Lib.getObject(driver, Orep.General.Search).click();
        Lib.getObject(driver, Orep.General.Searchbox).sendKeys(expectedResult.get("Code"));
        Lib.getObject(driver, Orep.General.Go).click();
        Lib.getObject(driver, Orep.General.View, 15000).click();
        Map<String, String> actualResult = new HashMap<>();
        actualResult.put("Name", Lib.getObject(driver, Orep.View.Name).getText());
        actualResult.put("Symbol", Lib.getObject(driver, Orep.View.Symbol).getText());
        actualResult.put("Code", Lib.getObject(driver, Orep.View.Code).getText());
        actualResult.put("Rate", Lib.getObject(driver, Orep.View.Rate).getText());
        actualResult.put("Active", Lib.getObject(driver, Orep.View.Active).getText());
        Lib.compare(actualResult, expectedResult);
        Lib.getObject(driver, Orep.General.Return, 15000).click();
    }


    @Then("^delete the added currency$")
    public void deleteTheAddedCurrency() {
        Lib.getObject(driver, Orep.General.CheckBox).click();
        Lib.getObject(driver, Orep.General.DeleteSelected).click();
        Lib.acceptAlert(driver);

    }


    @When("^click to Print$")
    public void clickToPrint() {
        String mainwindow = driver.getWindowHandle();
        Lib.getObject(driver, Orep.View.Print).click();
        System.out.println(mainwindow);
        Set<String> s = driver.getWindowHandles();
        System.out.println(s);
        for (String childwindow : s){
            if (!childwindow.equalsIgnoreCase(mainwindow)) {
                driver.switchTo().window(childwindow);
                driver.close();
            }
        }
        driver.switchTo().window(mainwindow);
    }

    @Then("^Take Screenshot$")
    public void takeScreenshot() throws IOException {
        Lib.takeSnapshot(driver, "C:\\Users\\adity\\OneDrive\\Desktop\\data");
    }
}



