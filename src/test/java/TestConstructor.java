import Static.Constants;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import pages.PageHome;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class TestConstructor extends TestBase {
    private PageHome homePage;

    @Before
    public void setUpTest() {
        homePage = new PageHome(driver);
        driver.get(Constants.BASE_URL);
    }

    // Вспомогательный метод для проверки активности с несколькими вариантами классов
    private boolean isElementActive(String className) {
        return className.contains("current") ||
                className.contains("active") ||
                className.contains("tab_tab__active") ||
                className.contains("tab_tab_type_current");
    }

    @Test
    @DisplayName("Переход к разделу 'Булки'")
    @Description("Проверка перехода к разделу Булки в конструкторе")
    public void switchToBunsSectionTest() {
        // Сначала переходим к другому разделу
        homePage.clickSaucesLink();

        // Ждем немного, чтобы Соусы стали активными
        waitForElementToBeActive("Соусы", 5);

        // Затем возвращаемся к Булкам
        homePage.clickBunsLink();

        // Ждем, пока Булки станут активными
        waitForElementToBeActive("Булки", 5);

        // Проверяем активность
        String className = homePage.getClassNameBuns();
        System.out.println("Класс элемента Булки: " + className);
        assertTrue("Раздел Булки не активен. Класс: " + className, isElementActive(className));
    }

    @Test
    @DisplayName("Переход к разделу 'Соусы'")
    @Description("Проверка перехода к разделу Соусы в конструкторе")
    public void switchToSaucesSectionTest() {
        homePage.clickSaucesLink();

        // Ждем, пока Соусы станут активными
        waitForElementToBeActive("Соусы", 5);

        // Получаем и выводим класс для отладки
        String className = homePage.getClassNameSauces();
        System.out.println("Класс элемента Соусы: " + className);

        // Проверяем активность
        assertTrue("Раздел Соусы не активен. Класс: " + className, isElementActive(className));
    }

    @Test
    @DisplayName("Переход к разделу 'Начинки'")
    @Description("Проверка перехода к разделу Начинки в конструкторе")
    public void switchToFillingsSectionTest() {
        homePage.clickFillingsLink();

        // Ждем, пока Начинки станут активными
        waitForElementToBeActive("Начинки", 5);

        // Получаем и выводим класс для отладки
        String className = homePage.getClassNameFillings();
        System.out.println("Класс элемента Начинки: " + className);

        // Проверяем активность
        assertTrue("Раздел Начинки не активен. Класс: " + className, isElementActive(className));
    }

    // Вспомогательный метод для ожидания активности элемента
    private void waitForElementToBeActive(String elementName, long timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));

        // Ожидаем, пока класс элемента не станет активным (содержит один из маркеров активности)
        wait.until(driver -> {
            String className = "";

            switch (elementName) {
                case "Булки":
                    className = homePage.getClassNameBuns();
                    break;
                case "Соусы":
                    className = homePage.getClassNameSauces();
                    break;
                case "Начинки":
                    className = homePage.getClassNameFillings();
                    break;
            }

            return isElementActive(className);
        });
    }
}