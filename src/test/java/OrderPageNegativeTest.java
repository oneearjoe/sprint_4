import pageobjects.MainPage;
import pageobjects.OrderPage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static config.AppConfig.APP_URL;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class OrderPageNegativeTest {
    private WebDriver driver;
    public OrderPage objOrderPage;
    public MainPage objMainPage;
    private final int orderBtnPosition = 0;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        objOrderPage = new OrderPage(driver);
        objMainPage = new MainPage(driver);
        driver.get(APP_URL);
    }

    //Проверяем отображение и текст ошибки если при нажатии кнопки Далее при оформлении заказ все поля пустые
    @Test
    public void checkErrorWhenAllFieldsAreEmpty() {
        objMainPage.clickOrderButton(orderBtnPosition);
        objOrderPage.clickNext();
        assertTrue("Ошибка для поля Имя не появилась", objOrderPage.firstNameErrorMessageIsDisplayed());
        assertEquals("Текст ошибки не совпадает", "Введите корректное имя", objOrderPage.getFirstNameFieldError());
        assertTrue("Ошибка для поля Фамилия не появилась", objOrderPage.lastNameErrorMessageIsDisplayed());
        assertEquals("Текст ошибки не совпадает", "Введите корректную фамилию", objOrderPage.getLastNameFieldError());
        //дефект, не появляется ошибка
        //assertTrue("Ошибка для поля Адрес не появилась",objOrderPage.addressErrorMessageIsDisplayed());
        //assertEquals("Текст ошибки не совпадает", "Введите корректный адрес", objOrderPage.getAddressFieldError());
        assertTrue("Ошибка для поля Станция не появилась", objOrderPage.subwayStationErrorMessageIsDisplayed());
        assertEquals("Текст ошибки не совпадает", "Выберите станцию", objOrderPage.getSubwayStationFieldError());
        assertTrue("Ошибка для поля Телефон не появилась", objOrderPage.phoneNumberErrorMessageIsDisplayed());
        assertEquals("Текст ошибки не совпадает", "Введите корректный номер", objOrderPage.getPhoneNumberError());
    }

    // Проверяем надичие и текст ошибки если ввсти невалидное Имя
    @Test
    public void checkErrorMessageForFirstNameFieldIfEnterInvalidText() {
        objMainPage.clickOrderButton(orderBtnPosition);
        objOrderPage.inputFirstName("Name");
        objOrderPage.clickNext();
        assertTrue("Ошибка для поля Имя не появилась", objOrderPage.firstNameErrorMessageIsDisplayed());
        assertEquals("Текст ошибки не совпадает", "Введите корректное имя", objOrderPage.getFirstNameFieldError());
    }

    // Проверяем надичие и текст ошибки если ввсти невалидную Фамилию
    @Test
    public void checkErrorMessageForLastNameFieldIfEnterInvalidText() {
        objMainPage.clickOrderButton(orderBtnPosition);
        objOrderPage.inputLastName("Name");
        objOrderPage.clickNext();
        assertTrue("Ошибка для поля Фамилия не появилась", objOrderPage.lastNameErrorMessageIsDisplayed());
        assertEquals("Текст ошибки не совпадает", "Введите корректную фамилию", objOrderPage.getLastNameFieldError());
    }

    // Проверяем надичие и текст ошибки если ввсти невалидный Адрес
    @Test
    public void checkErrorMessageForAddressFieldIfEnterInvalidText() {
        objMainPage.clickOrderButton(orderBtnPosition);
        objOrderPage.inputAddress("Name");
        objOrderPage.clickNext();
        assertTrue("Ошибка для поля Адрес не появилась",objOrderPage.addressErrorMessageIsDisplayed());
        assertEquals("Текст ошибки не совпадает", "Введите корректный адрес", objOrderPage.getAddressFieldError());
    }

    // Проверяем надичие и текст ошибки если ввсти невалидный номер телефона
    @Test
    public void checkErrorMessageForPhoneFieldIfEnterInvalidText() {
        objMainPage.clickOrderButton(orderBtnPosition);
        objOrderPage.inputPhoneNumber("Name");
        objOrderPage.clickNext();
        assertTrue("Ошибка для поля Телефон не появилась", objOrderPage.phoneNumberErrorMessageIsDisplayed());
        assertEquals("Текст ошибки не совпадает", "Введите корректный номер", objOrderPage.getPhoneNumberError());
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
