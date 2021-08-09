package logics;

import core.BaseClass;
import org.openqa.selenium.support.ui.Select;
import tools.Report;

public class GoogleClearSearch extends BaseClass
{
    public GoogleClearSearch()
    {
        if(!clearAndSearch())
        {
            Report.testFailed("Failed to Clear and Google Search");
        }
        Report.finaliseTest();
    }

    public static boolean clearAndSearch()
    {
        //Navigate to Google
        if(!SeleniumDriverInstance.navigate(pageObjects.HomePageObject.url()))
        {
            Report.testFailed("Failed to Navigate to " + pageObjects.HomePageObject.url());
            return false;
        }

        //Clear field
        if(!SeleniumDriverInstance.clearText(pageObjects.HomePageObject.searchBar())){
            Report.testFailed("Failed to Clear text in search bar");
            return false;
        }

        //Search
        if(!SeleniumDriverInstance.enterText(pageObjects.HomePageObject.searchBar(), "Orange")){
            Report.testFailed("Failed to Type into search bar");
            return false;
        }

        //Click Search button
        if (!SeleniumDriverInstance.click(pageObjects.HomePageObject.searchButton())) {
             Report.testFailed("Failed to click search button");
             return false;
         }
        return true;
    }

}
