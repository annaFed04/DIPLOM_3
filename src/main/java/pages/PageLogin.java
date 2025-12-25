package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageLogin {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Локаторы полей ввода и кнопок
    private final By emailInput = By.xpath("//input[@name='name']");
    private final By passwordInput = By.xpath("//input[@name='Пароль']");
    private final By loginButton = By.xpath("//button[text()='Войти']");
    private final By registerLink = By.xpath("//a[@href='/register']");
    private final By passwordResetLink = By.xpath("//a[@href='/forgot-password']");

    public PageLogin(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Методы заполнения полей формы
    @Step("Ввод email: {email}")
    public void inputEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput)).sendKeys(email);
    }

    @Step("Ввод пароля")
    public void inputPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput)).sendKeys(password);
    }

    // Методы кликов по кнопкам
    @Step("Нажатие кнопки 'Войти'")
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    @Step("Нажатие ссылки 'Зарегистрироваться'")
    public void clickRegisterLink() {
        wait.until(ExpectedConditions.elementToBeClickable(registerLink)).click();
    }

    @Step("Нажатие ссылки 'Восстановить пароль'")
    public void clickPasswordResetLink() {
        wait.until(ExpectedConditions.elementToBeClickable(passwordResetLink)).click();
    }

    // Комплексный метод для выполнения входа
    @Step("Выполнение входа с email: {email}")
    public void loginUser(String email, String password) {
        inputEmail(email);
        inputPassword(password);
        clickLoginButton();
    }
    @Step("Проверить, что страница логина открыта")
    public boolean isLoginPageOpened() {
        try {

            By loginButton = By.xpath("//button[text()='Войти']");
            return wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}