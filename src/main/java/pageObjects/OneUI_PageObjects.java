package pageObjects;

import org.openqa.selenium.By;

public class OneUI_PageObjects {
    public static String url() {
        return "https://oneui.mmihealth.co.za/oneui/";
    }

    //ONEUI Role Selection
    public static By role_CallCenter() {
        return By.xpath("//div[contains(text(), 'Call Center')]");
    }

    public static By role_WalkInCenter() {
        return By.xpath("//div[contains(text(), 'Walk In Center')]");
    }

    public static By role_BackOffice() {
        return By.xpath("//div[contains(text(), 'Back Office')]");
    }

    //Find Member
    public static By tile_findMember() {
        return By.xpath("//div[@class = 'icon-big icon-warning text-center']");
    }

    public static By txtMemberNumberSearch() {
        return By.xpath("//input[@id = 'memberNumberSearchInput']");
    }

    public static String memberNumberFilter() {
        return "//input[@id = 'memberNumberSearchInput']";
    }

    public static String enter(){
        return "//input[@id = 'searchSurnameInput']";
    }

    public static By validateMemberName(String text) {
        return By.xpath("//table[@class ='table table-striped' ]//td[contains(text(), '" + text + "')]");

    }

    public static By btnViewMember() {
        return By.xpath("//a[contains(text(), 'View Member')]");
    }

    public static By tab_basic() {
        return By.xpath("//ion-segment-button[@ng-reflect-value = 'basic']");
    }

    public static By tab_address() {
        return By.xpath("//ion-segment-button[@ng-reflect-value = 'address']");
    }

    public static By tab_bank() {
        return By.xpath("//ion-segment-button[@ng-reflect-value = 'bank']");
    }

    public static By tab_claims() {
        return By.xpath("//ion-segment-button[@ng-reflect-value = 'claims']");
    }

    public static By tab_benefits() {
        return By.xpath("//ion-segment-button[@ng-reflect-value = 'benefits']");
    }

    //Member Basic Info
    public static By provider() {
        return By.xpath("//div[@class='provider-name']");
    }

    public static String strName() {
        return "//*[@id=\"content\"]/div[2]/div/div[2]/div/div/div/div[2]/div/div[2]/ion-list/div[1]/div[1]/div/div[2]";
    }

    public static String strSurname() {
        return "//*[@id=\"content\"]/div[2]/div/div[2]/div/div/div/div[2]/div/div[2]/ion-list/div[1]/div[2]/div/div[2]";
    }

    public static String strID() {
        return "//[@id=\"content\"]/div[2]/div/div[2]/div/div/div/div[2]/div/div[2]/ion-list/div[1]/div[11]/div/div[2]";
    }
    public static By MemberName() {
        return By.xpath("//*[@id=\"content\"]/div[2]/div/div[2]/div/div/div/div[2]/div/div[2]/ion-list/div[1]/div[1]/div/div[2]");
    }


    //Find Provider
    public static By tile_findProvider() {
        return By.xpath("//div[@class= 'icon-big icon-success text-center']");
    }

    public static By txtPracticeNo() {
        return By.xpath("//input[@id = 'searchPcnsPracticeNumberInput']");
    }

    public static String practiceNumberFilter() {
        return "//input[@id = 'searchPcnsPracticeNumberInput']";
    }

    public static By btnViewProvider() {
        return By.xpath("//a[contains(text(), 'View Provider')]");
    }

    //Practice Basic Info
    public static String strPracticeNo() {
        return "//*[@id=\"content\"]/div[2]/div/div[2]/div/div/div/div/div/div[2]/ion-list/div[1]/div[1]/div/div[2]";
    }

    public static String strPracticeName() {
        return "//*[@id=\"content\"]/div[2]/div/div[2]/div/div/div/div/div/div[2]/ion-list/div[1]/div[2]/div/div[2]";
    }


}
