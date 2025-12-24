package pages;

import static1.Constants;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageRegister {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Локаторы полей формы
    private final By nameInput = By.xpath("//fieldset[1]//input");
    private final By emailInput = By.xpath("//fieldset[2]//input");
    private final By passwordInput = By.xpath("//fieldset[3]//input");
    private final By registerButton = By.xpath("//button[text()='Зарегистрироваться']");
    private final By loginLink = By.xpath("//a[@href='/login']");
    private final By errorMessage = By.xpath("//p[@class='input__error text_type_main-default']");

    public PageRegister(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Методы для ввода данных в форму
    @Step("Ввод имени: {name}")
    public void inputName(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput)).sendKeys(name);
    }

    @Step("Ввод email: {email}")
    public void inputEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput)).sendKeys(email);
    }

    @Step("Ввод пароля")
    public void inputPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput)).sendKeys(password);
    }

    // Методы взаимодействия с кнопками и ссылками
    @Step("Нажатие кнопки 'Зарегистрироваться'")
    public void clickRegisterButton() {
        wait.until(ExpectedConditions.elementToBeClickable(registerButton)).click();
    }

    @Step("Нажатие ссылки 'Войти'")
    public void clickLoginLink() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
    }

    // Методы ожидания (вспомогательные)
    @Step("Ожидание перехода на страницу входа после регистрации")
    public void waitForLoginPage() {
        wait.until(ExpectedConditions.urlToBe(Constants.PAGE_LOGIN));
    }

    @Step("Ожидание отображения сообщения об ошибке")
    public void waitForErrorMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
    }

    // Комплексные методы (используют предыдущие)
    @Step("Регистрация пользователя с именем: {name}, email: {email}")
    public void registerUser(String name, String email, String password) {
        inputName(name);
        inputEmail(email);
        inputPassword(password);
        clickRegisterButton();
    }

    // Методы для работы с сообщением об ошибке (валидация)
    @Step("Проверка отображения сообщения об ошибке")
    public boolean isErrorMessageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Получение текста сообщения об ошибке")
    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
    }
}