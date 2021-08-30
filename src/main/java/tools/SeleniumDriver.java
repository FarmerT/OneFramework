package tools;

import core.BaseClass;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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
        launchDriver();
    }


    //Start Driver using WebDriver Manager Reference https://github.com/bonigarcia/webdrivermanager
    public void launchDriver() {
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
            driver.navigate().to(url);
            Log.info("Navigating to URL: " + url);
            return true;
        }
        catch (Exception e)
        {
            Log.error("Failed to Navigate to URL - " + e.getMessage());
            return false;
        }

    }

    //Pause Function
    public void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            Log.info("PAUSED");
        }

    }

    //Wait Function
    public boolean waitForElement(By selector) {
        boolean found = false;
        int counter = 0;
        try {
            while (!found && counter < 30) {
                try {
                    WebDriverWait wait = new WebDriverWait(driver, 1);
                    wait.until((ExpectedConditions.presenceOfElementLocated(selector)));
                    wait.until(ExpectedConditions.elementToBeClickable(selector));
                    if (wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(selector)) != null) {
                        found = true;
                        Log.info("Web Element found: " + selector);
                        break;
                    }

                } catch (Exception e) {
                    found = false;
                }
                pause(1000);
                counter++;
            }
            if (counter == 30) {
                Log.error("Reached TimeOut whilst waiting for Web Element '" + selector + "'");
                return false;

            }
        } catch (Exception e) {
            Log.error("waiting for Web Element '" + selector + "' - " + e.getMessage());

        }
        return found;
    }

    //Pop up function
    public boolean popUpHandler() {
        try {

            Alert alert = driver.switchTo().alert();
            alert.getText();
            alert.accept();
            Log.info("Alert Accepted Successfully");
            return true;
        } catch (Exception e) {
            Log.error("Failed to Accept popup alert - " + e.getMessage());

            return false;
        }
    }

    //Switch Tab or Window function
    public boolean switchToTabOrWindow() {
        try {
            String winHandleBefore = SeleniumDriverInstance.driver.getWindowHandle();
            for (String winHandle : SeleniumDriverInstance.driver.getWindowHandles()) {
                SeleniumDriverInstance.driver.switchTo().window(winHandle);
            }
        } catch (Exception e) {
            Log.error("Could not switch to new tab " + e.getMessage());

            return false;
        }
        Log.info("Switched to window " + driver.getTitle());
        return true;
    }

    //Scroll to Element Function
    public boolean scrollToElement(By selector) {
        try {
            WebElement element = driver.findElement(selector);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            Log.info("Scrolled to element Successfully: " + selector);
            return true;
        } catch (Exception e) {
            Log.error("Error scrolling to element - " + selector + " - " + e.getStackTrace());
            return false;
        }

    }

    //Validate page function
    public void validatePage(String title) {
        try {
            if (driver.getTitle().equalsIgnoreCase(title)) {
                Log.info("'" + title + "' Successfully Validated");
            } else {
                Log.error("Failed to Validate Page - " + title);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    //Click Function
    public boolean click(By selector) {
        try {
            waitForElement(selector);
            WebDriverWait wait = new WebDriverWait(driver, 1);
            wait.until(ExpectedConditions.elementToBeClickable(selector));
            WebElement elementToClick = driver.findElement(selector);
            elementToClick.click();
            Log.info("Clicked Element: " + selector);
            return true;

        } catch (Exception e) {
            Log.error("Failed to click on Web Element - " + e.getMessage());
            return false;
        }
    }

    //Type Function
    public boolean enterText(By selector, String textToEnter) {
        try {
            waitForElement(selector);
            WebDriverWait wait = new WebDriverWait(this.driver, 1);
            wait.until(ExpectedConditions.elementToBeClickable(selector));
            WebElement elementToEnter = this.driver.findElement(selector);
            elementToEnter.clear();
            elementToEnter.click();
            elementToEnter.sendKeys(textToEnter);
            Log.info("Typed text '" + textToEnter + "' - " + selector);
            return true;
        } catch (Exception e) {
            Log.error("Failed to Type text: " + textToEnter + " to: " + selector + " - " + e.getMessage());
            return false;
        }
    }

    //Press Enter key
    public boolean pressEnterKey(String selector)
    {
        try
        {
            WebElement enterKey = this.driver.findElement(By.xpath(selector));
            enterKey.sendKeys(Keys.ENTER);
            Log.info("Enter key pressed Successfully");
            return true;

        }
        catch(Exception e)
        {
            Log.error("Failed to press 'Enter' key -" + e.getMessage());
            return false;
        }
    }

    //Select from dropdown list
    public boolean selectFromDropdownList(String selector, String valueToSelect)
    {
        try
        {
            WebDriverWait wait = new WebDriverWait(this.driver, 1);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(selector)));
            WebElement dropDownElement = this.driver.findElement(By.xpath(selector));
            dropDownElement.click();
            Select selectOption = new Select(this.driver.findElement(By.xpath(selector)));
            selectOption.selectByVisibleText(valueToSelect);
            Log.info("Selected Value '" + valueToSelect + "' from Dropdown list - " + selector);
            return true;
        }
        catch(Exception e)
        {
            Log.error("Failed to select '" + valueToSelect + "' from dropdown list");
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
            Log.info("Text Retrieved Successfully - " + element.getText());
            return element.getText();
        } catch (Exception e) {
            Log.error("Failed to get text from element - " + e.getMessage());
            return "";
        }
    }

    //Validate Text Function
    public boolean validateElementText(String selector, String textToValidate) {
        try {

            WebDriverWait wait = new WebDriverWait(this.driver, 3);
            WebElement element = this.driver.findElement(By.xpath(selector));

            if (!element.getText().equals(textToValidate))
            {
                Log.error("Failed to validate " + element.getText() + ", against " + textToValidate + ".");
            }
            else{
                Log.info("Text: "+ element.getText() +" - Successfully Validated");
            }


            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //Clear Function
    public boolean clearText(By selector){
        try
        {
            waitForElement(selector);
            WebDriverWait wait = new WebDriverWait(this.driver,3);
            wait.until(ExpectedConditions.elementToBeClickable(selector));
            WebElement clearText = this.driver.findElement(selector);
            clearText.clear();
            Log.info("Text Cleared Successfully");
            return true;

        }
        catch(Exception e){
            Log.error("Failed to clear text");
            return  false;

        }
    }

    //Screenshot Function
    public String takeScreenshot(boolean testStatus) {
        screenshotCounter++;
        StringBuilder imagePathBuilder = new StringBuilder();
        StringBuilder relativePathBuilder = new StringBuilder();

        try {
            imagePathBuilder.append(getReportDirectory());
            relativePathBuilder.append("Screenshots\\");
            new File(imagePathBuilder.toString() + (relativePathBuilder).toString()).mkdirs();

            relativePathBuilder.append(screenshotCounter + "_");
            if (testStatus) {
                relativePathBuilder.append("PASSED");
            } else {
                relativePathBuilder.append("FAILED");
            }
            relativePathBuilder.append(".png");

            File screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(imagePathBuilder.append(relativePathBuilder).toString()));

            return "./" + relativePathBuilder.toString();
        } catch (Exception e) {
            return null;
        }
    }

}


