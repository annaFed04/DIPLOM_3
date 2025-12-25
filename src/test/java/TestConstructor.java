
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import pages.PageHome;

import static org.junit.Assert.assertTrue;

public class TestConstructor extends TestBase {
    private PageHome homePage;

    @Before
    public void setUpTest() {
        homePage = new PageHome(driver);
        homePage.openHomePage(); // Используем метод Page Object вместо driver.get()
    }

    @Test
    @DisplayName("Переход к разделу 'Булки'")
    @Description("Проверка перехода к разделу Булки в конструкторе")
    public void switchToBunsSectionTest() {
        // Сначала переходим к другому разделу
        homePage.clickSaucesTab();
        homePage.waitForSaucesActive();

        // Затем возвращаемся к Булкам
        homePage.clickBunsTab();
        homePage.waitForBunsActive();

        // Проверяем активность
        assertTrue("Раздел Булки не активен",
                homePage.isBunsTabActive());
    }

    @Test
    @DisplayName("Переход к разделу 'Соусы'")
    @Description("Проверка перехода к разделу Соусы в конструкторе")
    public void switchToSaucesSectionTest() {
        homePage.clickSaucesTab();
        homePage.waitForSaucesActive();

        // Проверяем активность
        assertTrue("Раздел Соусы не активен",
                homePage.isSaucesTabActive());
    }

    @Test
    @DisplayName("Переход к разделу 'Начинки'")
    @Description("Проверка перехода к разделу Начинки в конструкторе")
    public void switchToFillingsSectionTest() {
        // Сначала переходим к другому разделу, чтобы убедиться, что Начинки изначально не активны
        homePage.clickSaucesTab();
        homePage.waitForSaucesActive();

        // Затем переходим к Начинкам
        homePage.clickFillingsTab();
        homePage.waitForFillingsActive();

        // Проверяем активность
        assertTrue("Раздел Начинки не активен",
                homePage.isFillingsTabActive());
    }
}