package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageForgotPassword {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Локаторы
    private final By signInLink = By.xpath("//a[@href='/login']");

    public PageForgotPassword(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Нажатие ссылки 'Войти'")
    public void clickSignInLink() {
        wait.until(ExpectedConditions.elementToBeClickable(signInLink)).click();
    }
}
