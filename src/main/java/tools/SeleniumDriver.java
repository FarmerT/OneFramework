package tools;

import core.BaseClass;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class SeleniumDriver extends BaseClass {

    private WebDriver driver;
    private static int screenshotCounter = 0;

    //Browser Types to use
    public enum BrowserType {CHROME, IE, FIREFOX}

    private BrowserType currentBrowser;

    public SeleniumDriver(BrowserType browser) {
        this.currentBrowser = browser;
        lauchDriver();
    }


    //Start Driver using WebDriver Manager Reference https://github.com/bonigarcia/webdrivermanager
    public boolean lauchDriver() {
        switch (this.currentBrowser) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                this.driver = new ChromeDriver();
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                this.driver = new FirefoxDriver();
                break;
            default:
                break;
        }

        this.driver.manage().window().maximize();
        return true;
    }

    //Get Driver
    public WebDriver getDriver() {
        return driver;
    }

    //Get current browser
    public BrowserType getCurrentBrowser() {
        return currentBrowser;
    }

    //close driver
    public boolean shutDown() {
        try {
            this.driver.close();
            this.driver.quit();

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //Navigate Function
    public boolean navigate(String url) {
        try {
            this.driver.navigate().to(url);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //Click Function
    public boolean clickElement(By selector) {
        try {
            waitForElement(selector);
            WebDriverWait wait = new WebDriverWait(this.driver, 1);
            wait.until(ExpectedConditions.elementToBeClickable(selector));
            WebElement elementToClick = this.driver.findElement(selector);
            elementToClick.click();

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //Type Function
    public boolean enterText(By selector, String textToEnter) {
        try {
            waitForElement(selector);
            WebDriverWait wait = new WebDriverWait(this.driver, 1);
            wait.until(ExpectedConditions.elementToBeClickable(selector));
            WebElement elementToClick = this.driver.findElement(selector);
            elementToClick.clear();
            elementToClick.sendKeys(textToEnter);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //Clear Text Function
    public boolean clearText(By selector) {
        try {
            waitForElement(selector);
            WebDriverWait wait = new WebDriverWait(this.driver, 1);
            wait.until(ExpectedConditions.elementToBeClickable(selector));
            WebElement elementToClick = this.driver.findElement(selector);
            elementToClick.clear();

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //Retrieve Text Function
    public String getTextFromElement(By selector) {
        try {
            waitForElement(selector);
            WebDriverWait wait = new WebDriverWait(this.driver, 1);
            wait.until(ExpectedConditions.elementToBeClickable(selector));
            WebElement element = this.driver.findElement(selector);
            return element.getText();
        } catch (Exception e) {
            return "";
        }
    }

    //Validate Text Function
    public boolean validateElementText(By selector, String textToValidate) {
        try {
            waitForElement(selector);
            WebDriverWait wait = new WebDriverWait(this.driver, 1);
            wait.until(ExpectedConditions.elementToBeClickable(selector));
            WebElement element = this.driver.findElement(selector);

            return element.getText().equals(textToValidate);
        } catch (Exception e) {
            return false;
        }
    }

    //Wait Function
    public boolean waitForElement(By selector) {
        boolean found = false;
        int counter = 0;
        try {
            while (!found && counter < 30) {
                try {
                    WebDriverWait wait = new WebDriverWait(this.driver, 1);
                    wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(selector));
                    found = true;
                } catch (Exception e) {
                    counter++;
                    pause(1000);
                }
            }
            return found;
        } catch (Exception e) {
            return false;
        }
    }

    //**********************************************


    public void waitForElementPresent(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(this.driver, 1);
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        }
        catch(Exception e) {
            System.out.println("some error occured while waiting for the element" + locator.toString());
        }

    }

    //**********************************************

    //Pause Function
    public void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    //Screenshot Function
    public String takeScreenshot(boolean testStatus)
    {
        screenshotCounter ++;
        StringBuilder imagePathBuilder = new StringBuilder();
        StringBuilder relativePathBuilder = new StringBuilder();

        try
        {
            imagePathBuilder.append(getReportDirectory());
            relativePathBuilder.append("Screenshots\\");
            new File(imagePathBuilder.toString() + (relativePathBuilder).toString()).mkdirs();

            relativePathBuilder.append(screenshotCounter+ "_");
            if(testStatus)
            {
                relativePathBuilder.append("PASSED");
            }
            else
            {
                relativePathBuilder.append("FAILED");
            }
            relativePathBuilder.append(".png");

            File screenshot = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(imagePathBuilder.append(relativePathBuilder).toString()));

            return "./" + relativePathBuilder.toString();
        }
        catch(Exception e)
        {
            return null;
        }
    }

}


