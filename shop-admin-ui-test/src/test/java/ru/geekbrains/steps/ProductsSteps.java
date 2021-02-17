package ru.geekbrains.steps;

import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.geekbrains.DriverInitializer;

public class ProductsSteps {

    private WebDriver webDriver = null;

    @Given("^Login \"([^\"]*)\" and \"([^\"]*)\"$")
    public void iOpenBrowserAndLogin(String username, String password) throws Throwable {
        webDriver = DriverInitializer.getDriver();

        webDriver.get(DriverInitializer.getProperty("login.url"));

        WebElement webElement = webDriver.findElement(By.id("inp-username"));
        Thread.sleep(2000);
        webElement.sendKeys(username);


        webElement = webDriver.findElement(By.id("inp-password"));
        Thread.sleep(2000);
        webElement.sendKeys(password);
        Thread.sleep(2000);

        webElement = webDriver.findElement(By.id("btn-login"));
        webElement.click();

    }

    @And("^click management of product$")
    public void userOpenMenuManagementProduct() throws InterruptedException {
        WebElement webElement = webDriver.findElement(By.xpath("/html/body/div/div[1]/ul/li[2]/a"));
        Thread.sleep(1000);
        webElement.click();
    }

    @And("^click products list$")
    public void userOpenProductlist() throws InterruptedException {
        WebElement webElement = webDriver.findElement(By.xpath("/html/body/div/div[1]/ul/li[2]/ul/li[1]/a"));
        Thread.sleep(1000);
        webElement.click();
    }

    @And("^click create new$")
    public void userClickCreateNew() throws InterruptedException {
        WebElement webElement = webDriver.findElement(By.xpath("/html/body/div/div[2]/div/div/form/button"));
        Thread.sleep(1000);
        webElement.click();
    }

    @And("^put in form \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"$")
    public void userPutInForm(String name, String price, String brand, String category) throws InterruptedException {
        WebElement webElement = webDriver.findElement(By.id("name"));
        Thread.sleep(2000);
        webElement.sendKeys(name);
        Thread.sleep(2000);

        webElement = webDriver.findElement(By.id("price"));
        webElement.sendKeys(price);
        Thread.sleep(2000);

        webElement = webDriver.findElement(By.xpath("/html/body/div/div[2]/form/div/div[1]/div[3]/select"));
        webElement.sendKeys(brand);
        Thread.sleep(2000);

        webElement = webDriver.findElement(By.id("category"));
        webElement.sendKeys(category);
        Thread.sleep(2000);

        webElement = webDriver.findElement(By.xpath("/html/body/div/div[2]/form/div/div[2]/button"));
        webElement.click();
        Thread.sleep(1000);
    }

    @And("^delete product$")
    public void userClickDelete() throws InterruptedException {
        WebElement webElement = webDriver.findElement(By.xpath("//*[@id=\"products\"]/tbody/tr[4]/td[6]/form/button"));
        webElement.click();
        Thread.sleep(1000);
    }

    @After
    public void endAndQuit() {
        webDriver.quit();
    }
}
