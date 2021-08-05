package testClasses;

import core.BaseClass;
import tools.Report;

public class GoogleSearch extends BaseClass
{
    public static String Search(String searchTerm) {

        if(!SeleniumDriverInstance.navigate(pageObjects.HomePageObject.url()))
        {
            return Report.testFailed("Failed to Navigate to " + pageObjects.HomePageObject.url());
        }


        if (!SeleniumDriverInstance.enterText(pageObjects.HomePageObject.searchBar(), searchTerm)) {
            return Report.testFailed("Failed to enter " + searchTerm + " to search bar");
        }


        Report.stepPassed("Search Input");

        if (!SeleniumDriverInstance.clickElement(pageObjects.HomePageObject.searchButton())) {
            return Report.testFailed("Failed to click search button");
        }

        // if (!SeleniumDriverInstance.validateElementText(pageObjects.GooglePageObjects.validateSearch(searchTerm), searchTerm)) {
        //    return Reporting.testFailed("Failed to validate search term");
        // }
        return Report.finaliseTest();
    }
}
