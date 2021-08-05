import core.BaseClass;
import org.junit.*;
import org.junit.rules.TestName;
import testClasses.GoogleSearch;
import tools.Report;
import tools.SeleniumDriver;

public class Testsuite extends BaseClass {

    @Rule
    public TestName name = new TestName();

    @Before
    public void initialize() {
        //First launch browser and create report
        TestName = name.getMethodName();
        Report.createTest();
        SeleniumDriverInstance = new SeleniumDriver(SeleniumDriver.BrowserType.FIREFOX);
        Assert.assertTrue("Driver successfully found", SeleniumDriverInstance.getDriver() != null);
        Report.info("Browser Launched");
    }


    @Test
    public void sampleTest() {

        GoogleSearch.Search("Apple");
       // String result = GoogleSearch.Search("Dog");

    }

    @After
    public void cleanUp() {
        SeleniumDriverInstance.shutDown();
    }

}
