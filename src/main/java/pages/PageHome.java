package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import statik.Constants;

import java.time.Duration;

public class PageHome {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Локаторы
    private final By loginButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By placeOrderButton = By.xpath(".//button[text()='Оформить заказ']");
    private final By profileButton = By.xpath(".//p[contains(@class, 'AppHeader_header__linkText') and contains(@class, 'ml-2') and text()='Личный Кабинет']");
    private final By saucesTab = By.xpath(".//span[text()='Соусы']/parent::div");
    private final By bunsTab = By.xpath(".//span[text()='Булки']/parent::div");
    private final By fillingsTab = By.xpath(".//span[text()='Начинки']/parent::div");

    public PageHome(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Step("Открыть главную страницу")
    public void openHomePage() {
        driver.get(Constants.BASE_URL);
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton));
    }

    @Step("Нажатие на кнопку «Войти в аккаунт»")
    public void clickEnterAccountButton() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        element.click();
    }

    @Step("Ожидание появления кнопки «Войти в аккаунт»")
    public void waitForEnterAccountButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton));
    }

    @Step("Ожидание появления кнопки «Оформить заказ»")
    public void waitForCheckoutButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(placeOrderButton));
    }

    @Step("Проверка отображения кнопки 'Оформить заказ'")
    public boolean isCheckoutButtonDisplayed() {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(placeOrderButton));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Получение текста кнопки 'Оформить заказ'")
    public String getCheckoutButtonText() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(placeOrderButton));
        return element.getText();
    }

    @Step("Ожидание появления кнопки «Личный Кабинет»")
    public void waitForPersonalAccountButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(profileButton));
    }

    @Step("Нажатие на кнопку «Личный Кабинет»")
    public void clickPersonalAccountButton() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(profileButton));
        element.click();
    }

    @Step("Нажатие на вкладку «Булки»")
    public void clickBunsTab() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(bunsTab));
        element.click();
    }

    @Step("Нажатие на вкладку «Соусы»")
    public void clickSaucesTab() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(saucesTab));
        element.click();
    }

    @Step("Нажатие на вкладку «Начинки»")
    public void clickFillingsTab() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(fillingsTab));
        element.click();
    }

    @Step("Проверить, активна ли вкладка «Булки»")
    public boolean isBunsTabActive() {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(bunsTab));
            String className = element.getAttribute("class");
            return className.contains("tab_tab_type_current__2BEPc");
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Проверить, активна ли вкладка «Соусы»")
    public boolean isSaucesTabActive() {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(saucesTab));
            String className = element.getAttribute("class");
            return className.contains("tab_tab_type_current__2BEPc");
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Проверить, активна ли вкладка «Начинки»")
    public boolean isFillingsTabActive() {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(fillingsTab));
            String className = element.getAttribute("class");
            return className.contains("tab_tab_type_current__2BEPc");
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Ожидание, что вкладка «Булки» станет активной")
    public void waitForBunsActive() {
        wait.until(ExpectedConditions.attributeContains(bunsTab, "class", "tab_tab_type_current__2BEPc"));
    }

    @Step("Ожидание, что вкладка «Соусы» станет активной")
    public void waitForSaucesActive() {
        wait.until(ExpectedConditions.attributeContains(saucesTab, "class", "tab_tab_type_current__2BEPc"));
    }

    @Step("Ожидание, что вкладка «Начинки» станет активной")
    public void waitForFillingsActive() {
        wait.until(ExpectedConditions.attributeContains(fillingsTab, "class", "tab_tab_type_current__2BEPc"));
    }
}