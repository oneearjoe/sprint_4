package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.List;


public class MainPage {

    private WebDriver driver;

    //Список вопросв
    private final static By listQuestions = By.xpath("//*[@class='accordion__button']");

    //Список ответов
    private final static By listAnswer = By.xpath("//div[@data-accordion-component='AccordionItemPanel']");

    //Нижняя кнопка "Заказать"
    private final static By bottom_order_button = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    //Верхняя кнопка "Заказать"
    private final static By top_order_buton = By.xpath(".//button[@class='Button_Button__ra12g']");
    private final static By scooterlogo =By.className("Header_LogoScooter__3lsAR");
    private final static By yandexLogo = By.className("Header_LogoYandex__3TSOI");
    private final static By mainHeader = By.className("Home_Header__iJKdX");
    private final static By orderStatusButton = By.className("Header_Link__1TAG7");
    private final static By orderNumberInputField = By.xpath("//input[@placeholder ='Введите номер заказа']");
    private final static By goButton = By.xpath("//button[text() = 'Go!']");



    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    // Коллекция из элементов вопросов
    public List<WebElement> getListQuestions() {
        return driver.findElements(listQuestions);
    }

    // Коллекция из элементов ответов
    public List<WebElement> getListAnswer() {
        return driver.findElements(listAnswer);
    }

    //Скролл до нижней кнопки "Заказать"
    public void scrollDown(){
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(bottom_order_button));
    }

    public void clickOrderButton(int btn){
        if (btn==0){
            driver.findElement(top_order_buton).click();
        } else if (btn==1){
            scrollDown();
            driver.findElement(bottom_order_button).click();
        }
    }

    public void clickOnScooterLogo(){
        driver.findElement(scooterlogo).click();
    }

    public boolean mainHeaderIsPresent(){
        return driver.findElement(mainHeader).isDisplayed();
    }

    public void clickOnYandexLogo(){
        driver.findElement(yandexLogo).click();
    }

    public void clickGoButton(){
        driver.findElement(goButton).click();
    }

    public void clickOrderStatusButton(){
        driver.findElement(orderStatusButton).click();
    }

    public void inputOrderNumber(String code){
        driver.findElement(orderNumberInputField).sendKeys(code);
    }

}
