package pages;


import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageHome {
    WebDriver driver;
    private final By loginButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By placeOrderButton = By.xpath(".//button[text()='Оформить заказ']");
    private final By profileButton = By.xpath(".//p[contains(@class, 'AppHeader_header__linkText') and contains(@class, 'ml-2') and text()='Личный Кабинет']");
    private final By saucesTab = By.xpath(".//span[text()='Соусы']/parent::div");
    private final By bunsTab = By.xpath(".//span[text()='Булки']/parent::div");
    private final By fillingsTab = By.xpath(".//span[text()='Начинки']/parent::div");
    private final WebDriverWait wait;

    public PageHome(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Нажатие на кнопку «Войти в аккаунт»")
    public void clickEnterAccountButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    @Step("Ожидание появления кнопки «Войти в аккаунт»")
    public void waitForEnterAccountButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton));
    }

    @Step("Ожидание появления кнопки «Оформить заказ»")
    public void waitCheckoutButton() {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(placeOrderButton));
    }
    @Step("Проверка отображения кнопки 'Оформить заказ'")
    public boolean isCheckoutButtonDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(placeOrderButton)).isDisplayed();
    }

    @Step("Получение текста кнопки 'Оформить заказ'")
    public String getCheckoutButtonText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(placeOrderButton)).getText();
    }


    @Step("Ожидание появления кнопки «Личный Кабинет»")
    public void waitForPersonalAccountButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(profileButton));
    }

    @Step("Нажатие на кнопку «Личный Кабинет»")
    public void clickPersonalAccountButton() {
        driver.findElement(profileButton).click();
    }

    @Step("Нажатие на вкладку «Булки»")
    public void clickBunsLink() {
        wait.until(ExpectedConditions.elementToBeClickable(bunsTab)).click();
    }

    @Step("Нажатие на вкладку «Соусы»")
    public void clickSaucesLink() {
        wait.until(ExpectedConditions.elementToBeClickable(saucesTab)).click();
    }

    @Step("Нажатие на вкладку «Начинки»")
    public void clickFillingsLink() {
        wait.until(ExpectedConditions.elementToBeClickable(fillingsTab)).click();
    }

    @Step("Получение атрибута class у вкладки «Булки»")
    public String getClassNameBuns() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(bunsTab)).getAttribute("class");
    }

    @Step("Получение атрибута class у вкладки «Соусы»")
    public String getClassNameSauces() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(saucesTab)).getAttribute("class");
    }

    @Step("Получение атрибута class у вкладки «Начинки»")
    public String getClassNameFillings() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(fillingsTab)).getAttribute("class");
    }

    @Step("Ожидание, что вкладка «Булки» станет активной")
    public void waitForBunsActive(long timeoutSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.attributeContains(bunsTab, "class", "tab_tab_type_current__2BEPc"));
    }

    @Step("Ожидание, что вкладка «Соусы» станет активной")
    public void waitForSaucesActive(long timeoutSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.attributeContains(saucesTab, "class", "tab_tab_type_current__2BEPc"));
    }

    @Step("Ожидание, что вкладка «Начинки» станет активной")
    public void waitForFillingsActive(long timeoutSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.attributeContains(fillingsTab, "class", "tab_tab_type_current__2BEPc"));
    }
}