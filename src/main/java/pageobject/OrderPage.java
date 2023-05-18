package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;


public class OrderPage {
    private WebDriver driver;

    private final By firstNameField = By.xpath("//input[@placeholder ='* Имя']");
    private final By lastNameField = By.xpath("//input[@placeholder ='* Фамилия']");
    private final By addressField = By.xpath("//input[@placeholder ='* Адрес: куда привезти заказ']"); // поле адреса
    private final By subwayStationField = By.xpath("//input[@placeholder ='* Станция метро']"); // поле метро
    private final By phoneFiled = By.xpath("//input[@placeholder ='* Телефон: на него позвонит курьер']"); // поле телефон
    private final By calendarField = By.xpath("//input[@placeholder ='* Когда привезти самокат']"); // поле календаря
    private final By leaseTime = By.className("Dropdown-placeholder"); // элемент выбора срока аренды
    private final By commentField = By.xpath("//input[@placeholder ='Комментарий для курьера']"); // поле коментария
    private final By nextButton = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM"); // кнопка далее
    private final By orderButton = By.xpath("//div[@class='Order_Buttons__1xGrp']//button[contains(text(), 'Заказать')]"); // кнопка заказать
    private final By yesOrder = By.xpath("//button[(text() =  'Да')]"); // кнопка подтверждения заказа
    private final By finalOrderMessage = By.className("Order_ModalHeader__3FDaJ");

    //локаторы с ошибками
    private final By errorFirstNameField = By.xpath("//div[text() = 'Введите корректное имя']");
    private final By errorLastNameField = By.xpath("//div[text() = 'Введите корректную фамилию']");
    private final By errorAddressField = By.xpath("//div[text() = 'Введите корректный адрес']");
    private final By errorSubwayStationField = By.xpath("//div[text() = 'Выберите станцию']");
    private final By errorPhoneNumberField = By.xpath("//div[text() = 'Введите корректный номер']");
    private final By blockOrderNotFound = By.className("Track_NotFound__6oaoY");


    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void inputFirstName(String firstName){
        driver.findElement(firstNameField).sendKeys(firstName);
    }

    public void inputLastName(String lastName){
        driver.findElement(lastNameField).sendKeys(lastName);
    }

    public void inputAddress(String address){
        driver.findElement(addressField).sendKeys(address);
    }

    public void clickOnSubwayStationField() {
        driver.findElement(subwayStationField).click();
    }

    public void chooseSubwayStation(String subwayStation){
        driver.findElement(By.className("select-search__input")).sendKeys(subwayStation);
        driver.findElement(By.className("select-search__select")).click();
    }

    public void inputPhoneNumber(String phone) {
        driver.findElement(phoneFiled).sendKeys(phone);
    }

    public void clickNext(){
        driver.findElement(nextButton).click();
    }

    public void inputDate(String date){
        driver.findElement(calendarField).sendKeys(date);
        driver.findElement(calendarField).sendKeys(Keys.ENTER);
    }

    public void chooseRentTime() {
        driver.findElement(leaseTime).click();
        driver.findElement(By.className("Dropdown-option")).click();
    }

    public void chooseScooterColor(String scooterColor ) {
        driver.findElement(By.id(scooterColor)).click();
    }

    public void inputComment(String comment ) {
        driver.findElement(commentField).sendKeys(comment);
    }

    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }

    // Клик на кнопку "Да"
    public void clickYesOrder() {
        driver.findElement(yesOrder).click();
    }
    public String getFinalOrderMessage(){
        return driver.findElement(finalOrderMessage).getText();
    }

    public boolean firstNameErrorMessageIsDisplayed(){
        return driver.findElement(errorFirstNameField).isDisplayed();
    }
    public boolean lastNameErrorMessageIsDisplayed(){
        return driver.findElement(errorLastNameField).isDisplayed();
    }

    public boolean addressErrorMessageIsDisplayed(){
        return driver.findElement(errorAddressField).isDisplayed();
    }

    public boolean subwayStationErrorMessageIsDisplayed(){
        return driver.findElement(errorSubwayStationField).isDisplayed();
    }

    public boolean phoneNumberErrorMessageIsDisplayed(){
        return driver.findElement(errorPhoneNumberField).isDisplayed();
    }

    public String getFirstNameFieldError(){
        return driver.findElement(errorFirstNameField).getText();
    }

    public String getLastNameFieldError(){
        return driver.findElement(errorLastNameField).getText();
    }

    public String getAddressFieldError(){
        return driver.findElement(errorAddressField).getText();
    }

    public String getSubwayStationFieldError(){
        return driver.findElement(errorSubwayStationField).getText();
    }
    public String getPhoneNumberError(){
        return driver.findElement(errorPhoneNumberField).getText();
    }

    public boolean isOrderNotFoundErrorVisible() {
        return driver.findElement(blockOrderNotFound).isDisplayed();
    }
}
