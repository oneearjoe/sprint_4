import pageobjects.MainPage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static config.AppConfig.APP_URL;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LogoTest{
    private WebDriver driver;
    public MainPage objMainPage;


    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        objMainPage = new MainPage(driver);
        driver.get(APP_URL);
    }

    //Проверка что после клика на лого Самокат переходим на главную страницу
    @Test
    public void  checkThatClickOnScooterLogoLeadsToTheMainPage(){
        objMainPage.clickOrderButton(0);
        objMainPage.clickOnScooterLogo();
        assertTrue("Открылась не главная страница, не найден MainHeader", objMainPage.mainHeaderIsPresent());
    }

    //Проверка что после клика на яндекс попадаем на дзен
    @Test
    public void checkThatClickonYandexLogoLeadsToDzen(){
        objMainPage.clickOnYandexLogo();
        //переключение на новую вкладку
        String originalWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        //ожидание, что страница загрузилась
        new WebDriverWait(driver, 5).
                until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("form[action='https://yandex.ru/search/']")));

        // проверка, что открылась нужная страница
        String expectedUrl = "https://dzen.ru/?yredirect=true";
        String actualUrl = driver.getCurrentUrl();
        assertEquals(expectedUrl, actualUrl);

    }

    @After
    public void teardown() {
        driver.quit();
    }

}
