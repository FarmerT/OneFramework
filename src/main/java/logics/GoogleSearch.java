package logics;

import core.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageObjects.GooglePageObject;
import tools.Report;

public class GoogleSearch extends BaseClass
{
    public static String Search(String searchTerm) {

        //Navigate to Google
        if(!SeleniumDriverInstance.navigate(GooglePageObject.url()))
        {
            return Report.testFailed("Failed to Navigate to " + GooglePageObject.url());
        }

        Report.stepPassedWithScreenshot("Google Home Page");

        //Type on Search text field
        if (!SeleniumDriverInstance.enterText(GooglePageObject.searchBar(), searchTerm)) {
            return Report.testFailed("Failed to Type " + searchTerm + " to search bar");
        }

        //Press Enter
        if(!SeleniumDriverInstance.pressEnterKey(GooglePageObject.searchField()))
        {
            return Report.testFailed("Failed to press 'Enter' key from search bar");
        }

        return Report.finaliseTest();
    }
}
