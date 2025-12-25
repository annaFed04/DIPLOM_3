import statik.Constants;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.By;
import pages.PageHome;
import pages.PageLogin;
import pages.PageRegister;
import userProfile.StepUser;
import userProfile.User;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TestRegister extends TestBase {

    private PageHome homePage;
    private PageRegister registerPage;
    private PageLogin loginPage;
    private StepUser stepUser;
    private User testUser;

    @Before
    public void setUpTest() {
        homePage = new PageHome(driver);
        registerPage = new PageRegister(driver);
        loginPage = new PageLogin(driver);
        stepUser = new StepUser();

        driver.get(Constants.PAGE_REGISTER);
    }

    @Test
    @DisplayName("Успешная регистрация пользователя")
    @Description("Проверка успешной регистрации с валидными данными")
    public void successfulRegistrationTest() {
        // Создаем случайного пользователя с уникальным email
        String timestamp = String.valueOf(System.currentTimeMillis());
        testUser = new User()
                .withName("TestUser" + timestamp)
                .withEmail("test" + timestamp + "@example.com")
                .withPassword("password123");

        // Регистрируем пользователя через UI
        registerPage.registerUser(testUser.getName(), testUser.getEmail(), testUser.getPassword());

        // После регистрации переходим на страницу входа
        driver.get(Constants.PAGE_LOGIN);

        // Входим с зарегистрированными данными
        loginPage.loginUser(testUser.getEmail(), testUser.getPassword());

        // Проверяем, что вход выполнен успешно
        homePage.waitCheckoutButton();

        // Убираем вызов API - пользователь уже создан через UI
        // Вместо этого просто проверяем, что мы вошли в систему
        assertTrue("Пользователь не авторизован после регистрации и входа",
                driver.findElement(By.xpath(".//button[text()='Оформить заказ']")).isDisplayed());
    }

    @Test
    @DisplayName("Ошибка при регистрации с коротким паролем")
    @Description("Проверка отображения ошибки при пароле меньше 6 символов")
    public void shortPasswordRegistrationErrorTest() {
        // Создаем пользователя с коротким паролем
        String timestamp = String.valueOf(System.currentTimeMillis());
        User user = new User()
                .withName("TestUser" + timestamp)
                .withEmail("test" + timestamp + "@example.com")
                .withPassword("123"); // меньше 6 символов

        // Пытаемся зарегистрироваться
        registerPage.registerUser(user.getName(), user.getEmail(), user.getPassword());

        // ОР: отображается сообщение об ошибке
        // Даем время для появления ошибки
        try {
            Thread.sleep(2000); // небольшая пауза
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Проверяем, что сообщение об ошибке отображается
        assertTrue("Сообщение об ошибке не отображается",
                registerPage.isErrorMessageDisplayed());
    }

    @After
    public void cleanUp() {
        // Если нужно удалить пользователя, можно попробовать через API
        // Но пока убираем этот код, так как вызывает 403 ошибку
    }
}