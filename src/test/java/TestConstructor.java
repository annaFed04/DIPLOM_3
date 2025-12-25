import statik.Constants;
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
        homePage.waitForSaucesActive(5);

        // Затем возвращаемся к Булкам
        homePage.clickBunsLink();
        homePage.waitForBunsActive(5);

        // Проверяем активность
        String className = homePage.getClassNameBuns();
        System.out.println("Класс элемента Булки: " + className);

        // Используем метод contains для проверки активности
        assertTrue("Раздел Булки не активен",
                className.contains("tab_tab_type_current__2BEPc"));
    }

    @Test
    @DisplayName("Переход к разделу 'Соусы'")
    @Description("Проверка перехода к разделу Соусы в конструкторе")
    public void switchToSaucesSectionTest() {
        homePage.clickSaucesLink();
        homePage.waitForSaucesActive(5);

        // Получаем и выводим класс для отладки
        String className = homePage.getClassNameSauces();
        System.out.println("Класс элемента Соусы: " + className);

        // Проверяем активность
        assertTrue("Раздел Соусы не активен",
                className.contains("tab_tab_type_current__2BEPc"));
    }

    @Test
    @DisplayName("Переход к разделу 'Начинки'")
    @Description("Проверка перехода к разделу Начинки в конструкторе")
    public void switchToFillingsSectionTest() {
        // Сначала переходим к другому разделу, чтобы убедиться, что Начинки изначально не активны
        homePage.clickSaucesLink();
        homePage.waitForSaucesActive(5);

        // Затем переходим к Начинкам
        homePage.clickFillingsLink();
        homePage.waitForFillingsActive(10); // Увеличиваем время ожидания

        // Получаем и выводим класс для отладки
        String className = homePage.getClassNameFillings();
        System.out.println("Класс элемента Начинки: " + className);

        // Проверяем активность
        assertTrue("Раздел Начинки не активен. Класс: " + className,
                className.contains("tab_tab_type_current__2BEPc"));
    }
}