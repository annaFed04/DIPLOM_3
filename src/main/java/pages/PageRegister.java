package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import statik.Constants;

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

    // Навигация
    @Step("Открыть страницу регистрации")
    public void openRegistrationPage() {
        driver.get(Constants.PAGE_REGISTER);
        wait.until(ExpectedConditions.visibilityOfElementLocated(registerButton));
    }

    @Step("Проверить, что страница регистрации загружена")
    public boolean isRegisterPageLoaded() {
        try {
            WebElement registerBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(registerButton));
            WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput));
            return registerBtn.isDisplayed() && nameField.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Методы для ввода данных в форму
    @Step("Ввод имени: {name}")
    public void inputName(String name) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput));
        element.clear();
        element.sendKeys(name);
    }

    @Step("Ввод email: {email}")
    public void inputEmail(String email) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput));
        element.clear();
        element.sendKeys(email);
    }

    @Step("Ввод пароля")
    public void inputPassword(String password) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        element.clear();
        element.sendKeys(password);
    }

    // Методы взаимодействия с кнопками и ссылками
    @Step("Нажатие кнопки 'Зарегистрироваться'")
    public void clickRegisterButton() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(registerButton));
        element.click();
    }

    @Step("Нажатие ссылки 'Войти'")
    public void clickLoginLink() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(loginLink));
        element.click();
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

    @Step("Полная регистрация с ожиданием перехода на страницу входа")
    public void registerUserWithRedirect(String name, String email, String password) {
        registerUser(name, email, password);
        waitForLoginPage();
    }

    // Методы для работы с сообщением об ошибке (валидация)
    @Step("Проверка отображения сообщения об ошибке")
    public boolean isErrorMessageDisplayed() {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Получение текста сообщения об ошибке")
    public String getErrorMessage() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
        return element.getText();
    }

    // Дополнительные методы
    @Step("Получить введенное имя")
    public String getNameFieldValue() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput));
        return element.getAttribute("value");
    }

    @Step("Получить введенный email")
    public String getEmailFieldValue() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput));
        return element.getAttribute("value");
    }

    @Step("Получить введенный пароль")
    public String getPasswordFieldValue() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        return element.getAttribute("value");
    }

    @Step("Проверить, активна ли кнопка 'Зарегистрироваться'")
    public boolean isRegisterButtonEnabled() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(registerButton));
        return element.isEnabled();
    }

    @Step("Очистить поле имени")
    public void clearNameField() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput));
        element.clear();
    }

    @Step("Очистить поле email")
    public void clearEmailField() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput));
        element.clear();
    }

    @Step("Очистить поле пароля")
    public void clearPasswordField() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        element.clear();
    }

    @Step("Очистить все поля формы")
    public void clearAllFields() {
        clearNameField();
        clearEmailField();
        clearPasswordField();
    }
}