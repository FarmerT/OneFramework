package logics;

import core.BaseClass;
import jxl.common.Assert;
import tools.Report;

public class GoogleSearch extends BaseClass
{
    public static String Search(String searchTerm) {

        //Navigate to Google
        if(!SeleniumDriverInstance.navigate(pageObjects.HomePageObject.url()))
        {
            return Report.testFailed("Failed to Navigate to " + pageObjects.HomePageObject.url());
        }

        //Hover over search text field
        if(!SeleniumDriverInstance.hoverOverElement(pageObjects.HomePageObject.searchField()))
        {
            return Report.testFailed("Failed to hover over search bar");

        }

        //Type on Search text field
        if (!SeleniumDriverInstance.enterText(pageObjects.HomePageObject.searchBar(), searchTerm)) {
            return Report.testFailed("Failed to Type " + searchTerm + " to search bar");
        }

        //Press Enter
        if(!SeleniumDriverInstance.pressEnterKey(pageObjects.HomePageObject.searchField()))
        {
            return Report.testFailed("Failed to press 'Enter' key from search bar");
        }
        return Report.finaliseTest();
    }
}
