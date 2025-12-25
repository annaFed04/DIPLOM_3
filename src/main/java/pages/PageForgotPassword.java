package pages;


import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import statik.Constants;

import java.time.Duration;

public class PageForgotPassword {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Локаторы
    private final By signInLink = By.xpath("//a[@href='/login']");
    private final By emailInput = By.xpath("//input[@type='text']");
    private final By resetButton = By.xpath("//button[text()='Восстановить']");

    public PageForgotPassword(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Открыть страницу восстановления пароля")
    public void openForgotPasswordPage() {
        driver.get(Constants.PAGE_FORGOT_PASSWORD);
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput));
    }

    @Step("Проверить, что страница восстановления пароля загружена")
    public boolean isForgotPasswordPageLoaded() {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(resetButton));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Нажатие ссылки 'Войти'")
    public void clickSignInLink() {
        wait.until(ExpectedConditions.elementToBeClickable(signInLink)).click();
    }
}