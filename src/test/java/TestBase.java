import statik.Constants;
import statik.WebDriverProvider;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

public class TestBase {
    protected WebDriver driver;

    @Before
    public void setUp() {
        driver = WebDriverProvider.createDriver();  //
        driver.get(Constants.BASE_URL);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
