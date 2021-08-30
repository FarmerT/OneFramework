package logics;

import core.BaseClass;
import pageObjects.OneUI_PageObjects;
import tools.Report;

public class FindProvider extends BaseClass
{
    public static String SearchProvider()
    {
        if(!SeleniumDriverInstance.navigate(OneUI_PageObjects.url())){
            return Report.testFailed("Failed to Navigate to ONEUI");
        }

        //Validate ONEUI Page
        SeleniumDriverInstance.validatePage("One UI");

        //Take Screenshot
        Report.stepPassedWithScreenshot("One UI Home Page");

        //Select Call Center Role
        if(!SeleniumDriverInstance.waitForElement(OneUI_PageObjects.role_CallCenter())){
            return Report.testFailed("Failed to Wait for ONEUI to Load");
        }
        if(!SeleniumDriverInstance.click(OneUI_PageObjects.role_CallCenter())){
            return Report.testFailed("Failed select Call Center Role");
        }

        //Find Member
        if(!SeleniumDriverInstance.waitForElement(OneUI_PageObjects.tile_findProvider())){
            return Report.testFailed("Failed to Wait for 'Find Provider' tile");
        }
        if(!SeleniumDriverInstance.click(OneUI_PageObjects.tile_findProvider())){
            return Report.testFailed("Failed to Click 'Find Provider' tile");
        }

        //Enter Provider Number
        if(!SeleniumDriverInstance.enterText(OneUI_PageObjects.txtPracticeNo(), "27")){
            return Report.testFailed("Failed to Type Practice Number");
        }

        //Press Enter
        if(!SeleniumDriverInstance.pressEnterKey(OneUI_PageObjects.practiceNumberFilter())){
            return Report.testFailed("Failed to Press Enter Key");
        }

        //Click View Member button
        if(!SeleniumDriverInstance.click(OneUI_PageObjects.btnViewProvider())){
            return Report.testFailed("Failed to Click 'View Provider' button");
        }

        SeleniumDriverInstance.pause(7000);
        Report.stepPassedWithScreenshot("Provider Profile Page");

        //Wait for Provider Profile Page
        if(!SeleniumDriverInstance.waitForElement(OneUI_PageObjects.tab_basic())){
            return Report.testFailed("Failed to wait for Provider Basic Information");
        }

        //Validate Provider Number and Name
        if(!SeleniumDriverInstance.validateElementText(OneUI_PageObjects.strPracticeNo(), "27")){
            return Report.testFailed("Failed to Validate Practice Number");
        }
        if(!SeleniumDriverInstance.validateElementText(OneUI_PageObjects.strPracticeName(), "DR DOUWE VELLEMA DR")){
            return Report.testFailed("Failed to Validate Provider Name");
        }

        return Report.finaliseTest();
    }

}


