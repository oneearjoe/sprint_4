import pageobjects.MainPage;
import pageobjects.OrderPage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static config.AppConfig.APP_URL;
import static org.junit.Assert.assertTrue;

public class OrderStatusTest {
    private WebDriver driver;

    public OrderPage objOrderPage;

    public MainPage objMainPage;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        objOrderPage = new OrderPage(driver);
        objMainPage = new MainPage(driver);
        driver.get(APP_URL);
    }

    //Проверяем ошибку если закза не найден
    @Test
    public void checkErrorMessageWhenOrderDoesNotExist(){
        objMainPage.clickOrderStatusButton();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        objMainPage.inputOrderNumber("123");
        objMainPage.clickGoButton();
        assertTrue(objOrderPage.isOrderNotFoundErrorVisible());
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
