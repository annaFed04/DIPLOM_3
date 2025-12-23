import Static.Constants;
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
        driver.get(Constants.BASE_URL);
    }

    @Test
    @DisplayName("Переход к разделу 'Булки'")
    @Description("Проверка перехода к разделу Булки в конструкторе")
    public void switchToBunsSectionTest() {
        // Сначала переходим к другому разделу
        homePage.clickSaucesLink();

        // Затем возвращаемся к Булкам
        homePage.clickBunsLink();

        // ОР: активен раздел Булки
        homePage.waitForBunsActive(5);
        assertTrue("Раздел Булки не активен", homePage.getClassNameBuns().contains("tab_tab_type_current__2BEPc"));
    }

    @Test
    @DisplayName("Переход к разделу 'Соусы'")
    @Description("Проверка перехода к разделу Соусы в конструкторе")
    public void switchToSaucesSectionTest() {
        homePage.clickSaucesLink();

        // ОР: активен раздел Соусы
        homePage.waitForSaucesActive(5);
        assertTrue("Раздел Соусы не активен", homePage.getClassNameSauces().contains("tab_tab_type_current__2BEPc"));
    }

    @Test
    @DisplayName("Переход к разделу 'Начинки'")
    @Description("Проверка перехода к разделу Начинки в конструкторе")
    public void switchToFillingsSectionTest() {
        homePage.clickFillingsLink();

        // ОР: активен раздел Начинки
        homePage.waitForFillingsActive(5);
        String className = homePage.getClassNameFillings();
        System.out.println("Класс элемента Начинки: " + className);
        assertTrue("Раздел Начинки не активен", className.contains("tab_tab_type_current__2BEPc"));
    }
}