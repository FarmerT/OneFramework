package pageObjects;

import org.openqa.selenium.By;

public class GooglePageObject
{
    public static String url() {
        return "https://www.google.com/";
    }

    public static By searchBar() {
        return By.xpath("//input[@title='Search']");
    }

    public static String searchField() {
        return "//input[@title='Search']";
    }


    public static By searchButton() {
        return By.xpath("//input[@value='Google Search']");
    }

    public static By validateSearch(String term) {
        return By.xpath("//input[@value='"+term+"']");
    }

    public static String strValidate(){
        return "//input[@value='Orange']";
    }
}
