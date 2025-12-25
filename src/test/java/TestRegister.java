import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import pages.PageHome;
import pages.PageLogin;

import pages.PageRegister;
import userProfile.StepUser;
import userProfile.User;
import static org.junit.Assert.assertTrue;

public class TestRegister extends TestBase {

    private PageHome homePage;
    private PageRegister registerPage;
    private PageLogin loginPage;
    private StepUser stepUser;
    private User testUser;
    private String accessToken;

    @Before
    public void setUpTest() {
        homePage = new PageHome(driver);
        registerPage = new PageRegister(driver);
        loginPage = new PageLogin(driver);
        stepUser = new StepUser();

        // Используем метод Page Object для открытия страницы
        registerPage.openRegistrationPage();
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

        // Регистрируем пользователя через UI с ожиданием перехода на страницу входа
        registerPage.registerUserWithRedirect(testUser.getName(), testUser.getEmail(), testUser.getPassword());

        // Проверяем, что перешли на страницу входа
        assertTrue("Не перешли на страницу входа после регистрации",
                loginPage.isLoginPageOpened());

        // Входим с зарегистрированными данными
        loginPage.loginUser(testUser.getEmail(), testUser.getPassword());

        // Проверяем, что вход выполнен успешно
        assertTrue("Пользователь не авторизован после регистрации и входа",
                homePage.isCheckoutButtonDisplayed());
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

        // Проверяем, что сообщение об ошибке отображается
        assertTrue("Сообщение об ошибке не отображается",
                registerPage.isErrorMessageDisplayed());
    }

    @After
    public void cleanUp() {
        // Если пользователь был создан и у него есть токен, удаляем через API
        if (testUser != null && accessToken != null) {
            testUser.updateAccessToken(accessToken);
            stepUser.removeUser(testUser);
        }
    }
}