package logics;

import core.BaseClass;
import pageObjects.OneUI_PageObjects;
import tools.Report;

public class FindMember extends BaseClass
{
    public static String FindMembership()
    {
        //Navigationg to ONEUI url
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
        if(!SeleniumDriverInstance.waitForElement(OneUI_PageObjects.tile_findMember())){
            return Report.testFailed("Failed to Wait for 'Find Member' tile");
        }
        if(!SeleniumDriverInstance.click(OneUI_PageObjects.tile_findMember())){
            return Report.testFailed("Failed to Click 'Find Member' tile");
        }

        //Enter Member Number
        if(!SeleniumDriverInstance.enterText(OneUI_PageObjects.txtMemberNumberSearch(), "10111")){
            return Report.testFailed("Failed to Type Member Number");
        }

        if(!SeleniumDriverInstance.clearText(OneUI_PageObjects.txtMemberNumberSearch())){
            return Report.testFailed("Failed to clear text on member number");
        }

        if(!SeleniumDriverInstance.enterText(OneUI_PageObjects.txtMemberNumberSearch(), "1234")){
            return Report.testFailed("Failed to Type Member Number");
        }

        //Press Enter
        if(!SeleniumDriverInstance.pressEnterKey(OneUI_PageObjects.memberNumberFilter())){
            return Report.testFailed("Failed to Press Enter Key");
        }

        //Click View Member button
        if(!SeleniumDriverInstance.click(OneUI_PageObjects.btnViewMember())){
            return Report.testFailed("Failed to Click 'View Member' button");
        }

        SeleniumDriverInstance.pause(7000);
        Report.stepPassedWithScreenshot("Membership Profile Page");

        //Wait for Member Profile Page
        if(!SeleniumDriverInstance.waitForElement(OneUI_PageObjects.tab_basic())){
            return Report.testFailed("Failed to wait for Member Basic Information");
        }
        //Scroll Down
        if(!SeleniumDriverInstance.scrollToElement(OneUI_PageObjects.tab_basic())){
            return Report.testFailed("Failed to scroll to Basic Information tab");
        }

        if(!SeleniumDriverInstance.scrollToElement(OneUI_PageObjects.MemberName())){
            return Report.testFailed("Failed to scroll to BAsic Information tab");
        }


        return Report.finaliseTest();
    }

}


