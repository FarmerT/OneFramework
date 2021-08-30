package testCases;

import core.BaseClass;
import logics.FindProvider;
import org.junit.*;
import org.junit.rules.TestName;
import tools.Report;
import tools.SeleniumDriver;

public class FindProviderTest extends BaseClass {

    @Rule
    public TestName name = new TestName();

    @Before
    public void initialize() {

        //Create Test Name
        TestName = name.getMethodName();

        //launch browser
        SeleniumDriverInstance = new SeleniumDriver(SeleniumDriver.BrowserType.CHROME);
        Assert.assertTrue("Driver successfully found", SeleniumDriverInstance.getDriver() != null);
        Report.info("Browser Launched");
        Log.info("Browser Launched");

        //Create report
        Report.createTest();

    }

    @Test
    public void validateProvider() {

        FindProvider.SearchProvider();
    }

    @After
    public void cleanUp() {
        SeleniumDriverInstance.shutDown();

    }

}
