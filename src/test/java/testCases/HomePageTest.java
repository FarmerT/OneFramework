package testCases;

import core.BaseClass;
import logics.GoogleClearSearch;
import logics.GoogleSearch;
import org.junit.*;
import org.junit.rules.TestName;
import org.testng.annotations.AfterTest;
import tools.Report;
import tools.SeleniumDriver;

public class HomePageTest extends BaseClass {

    @Rule
    public TestName name = new TestName();

    @Before
    public void initialize() {

        //Create Test Name
        TestName = name.getMethodName();

        //launch browser
        SeleniumDriverInstance = new SeleniumDriver(SeleniumDriver.BrowserType.FIREFOX);
        Assert.assertTrue("Driver successfully found", SeleniumDriverInstance.getDriver() != null);
        Report.info("Browser Launched");
        Log.info("Browser Launched");

        //Create report
        Report.createTest();

    }

    @Test
    public void firstTest() {

        GoogleSearch.Search("Apple");
    }

    @Test
    public void secondTest(){
        GoogleClearSearch.clearAndSearch();
    }

    @After
    public void cleanUp() {
        SeleniumDriverInstance.shutDown();
    }

}
