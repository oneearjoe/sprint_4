import config.OrderButtonPosition;
import pageobjects.MainPage;
import pageobjects.OrderPage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import java.util.concurrent.TimeUnit;

import static config.AppConfig.APP_URL;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class OrderPagePositiveTest {
    private WebDriver driver;
    public OrderPage objOrderPage;
    public MainPage objMainPage;
    private final OrderButtonPosition orderBtnPosition;
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String subwayStation;
    private final String phoneNumber;
    private final String date;
    private final String scooterColor;
    private final String comment;

    public OrderPagePositiveTest(OrderButtonPosition orderBtnPosition, String firstName, String lastName, String address, String subwayStation, String phoneNumber, String comment, String date, String scooterColor) {
        this.orderBtnPosition = orderBtnPosition;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.subwayStation = subwayStation;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.scooterColor = scooterColor;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {OrderButtonPosition.TOP, "Дарт", "Вейдер", "Планета Корусант Храм джедааев", "Сокол", "89999999999", "Я твой отец", "05.04.2023", "black"},
                {OrderButtonPosition.BOTTOM, "Оби", "Ван", "Татуин", "Лубянка", "+79997777777", "Это не те дроиды, что вы ищете.", "15.04.2023", "grey"},
        };

    }

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        objOrderPage = new OrderPage(driver);
        objMainPage = new MainPage(driver);
        driver.get(APP_URL);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    //Тест на то что можно успешно сделать заказ
    @Test
    public void positiveMakeScooterOrder(){
        objMainPage.clickOrderButton(orderBtnPosition);
        objOrderPage.inputFirstName(firstName);
        objOrderPage.inputLastName(lastName);
        objOrderPage.inputAddress(address);
        objOrderPage.clickOnSubwayStationField();
        objOrderPage.chooseSubwayStation(subwayStation);
        objOrderPage.inputPhoneNumber(phoneNumber);
        objOrderPage.clickNext();
        objOrderPage.inputDate(date);
        objOrderPage.chooseRentTime();
        objOrderPage.chooseScooterColor(scooterColor);
        objOrderPage.inputComment(comment);
        objOrderPage.clickOrderButton();
        MatcherAssert.assertThat("Не появилось окно подтверждения заказа",
                driver.findElement(By.className("Order_ModalHeader__3FDaJ")).getText(),
                containsString("Хотите оформить заказ?"));
        //Дефект, в хром не нажимается кнопка Да
        objOrderPage.clickYesOrder();
        assertEquals("Не появилось окно (Заказ оформлен)",
                objOrderPage.getFinalOrderMessage(), "Заказ оформлен");
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
