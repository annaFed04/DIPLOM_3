import static1.Constants;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import pages.PageForgotPassword;
import pages.PageHome;
import pages.PageLogin;
import pages.PageRegister;
import userProfile.StepUser;
import userProfile.User;

import static org.junit.Assert.assertTrue;
import pages.*;


public class TestLogin extends TestBase {

    private PageHome homePage;
    private PageLogin loginPage;
    private PageRegister registerPage;
    private PageForgotPassword forgotPasswordPage;
    private StepUser stepUser;
    private User testUser;
    private String accessToken;

    @Before
    public void setUpTest() {
        homePage = new PageHome(driver);
        loginPage = new PageLogin(driver);
        registerPage = new PageRegister(driver);
        forgotPasswordPage = new PageForgotPassword(driver);
        stepUser = new StepUser();

        testUser = new User()
                .withName("Мария-Мерабелла")
                .withEmail("test" + System.currentTimeMillis() + "@пипец.com")
                .withPassword("password314");

        var response = stepUser.createNewUser(testUser);
        accessToken = stepUser.getAccessTokenFromResponse(response);
        testUser.updateAccessToken(accessToken);
    }

    @Test
    @DisplayName("Вход по кнопке 'Войти в аккаунт' на главной странице")
    @Description("Проверка успешного входа через кнопку на главной")
    public void loginViaMainPageButtonTest() {
        driver.get(Constants.BASE_URL);

        // Шаг 1: Клик на кнопку "Войти в аккаунт"
        homePage.clickEnterAccountButton();

        // Шаг 2: Ввод учетных данных и вход
        loginPage.loginUser(testUser.getEmail(), testUser.getPassword());

        // Шаг 3: Ожидание появления кнопки "Оформить заказ"
        homePage.waitCheckoutButton();

        // Шаг 4: Проверка, что кнопка "Оформить заказ" отображается
        boolean isButtonDisplayed = homePage.isCheckoutButtonDisplayed();

        // Проверка: сравниваем ожидаемый результат (true) с актуальным
        assertTrue("Вход не выполнен - кнопка 'Оформить заказ' не отображается", isButtonDisplayed);
    }

    @Test
    @DisplayName("Вход через кнопку 'Личный кабинет'")
    @Description("Проверка успешного входа через кнопку Личный кабинет")
    public void loginViaPersonalAccountButtonTest() {
        driver.get(Constants.BASE_URL);

        // Шаг 1: Клик на кнопку "Личный кабинет"
        homePage.clickPersonalAccountButton();

        // Шаг 2: Ввод учетных данных и вход
        loginPage.loginUser(testUser.getEmail(), testUser.getPassword());

        // Шаг 3: Ожидание появления кнопки "Оформить заказ"
        homePage.waitCheckoutButton();

        // Шаг 4: Проверка отображения кнопки
        boolean isButtonDisplayed = homePage.isCheckoutButtonDisplayed();

        // Проверка
        assertTrue("Вход не выполнен - кнопка 'Оформить заказ' не отображается", isButtonDisplayed);
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    @Description("Проверка перехода на страницу входа из формы регистрации")
    public void loginViaRegisterFormLinkTest() {
        driver.get(Constants.PAGE_REGISTER);

        // Шаг 1: Клик на ссылку "Войти" на странице регистрации
        registerPage.clickLoginLink();

        // Шаг 2: Ввод учетных данных и вход
        loginPage.loginUser(testUser.getEmail(), testUser.getPassword());

        // Шаг 3: Ожидание появления кнопки "Оформить заказ"
        homePage.waitCheckoutButton();

        // Шаг 4: Проверка отображения кнопки
        boolean isButtonDisplayed = homePage.isCheckoutButtonDisplayed();

        // Проверка
        assertTrue("Вход не выполнен - кнопка 'Оформить заказ' не отображается", isButtonDisplayed);
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("Проверка перехода на страницу входа из формы восстановления пароля")
    public void loginViaForgotPasswordLinkTest() {
        driver.get(Constants.BASE_URL + "/forgot-password");

        // Шаг 1: Клик на ссылку "Войти" на странице восстановления пароля
        forgotPasswordPage.clickSignInLink();

        // Шаг 2: Ввод учетных данных и вход
        loginPage.loginUser(testUser.getEmail(), testUser.getPassword());

        // Шаг 3: Ожидание появления кнопки "Оформить заказ"
        homePage.waitCheckoutButton();

        // Шаг 4: Проверка отображения кнопки
        boolean isButtonDisplayed = homePage.isCheckoutButtonDisplayed();

        // Проверка
        assertTrue("Вход не выполнен - кнопка 'Оформить заказ' не отображается", isButtonDisplayed);
    }

    @After
    public void cleanUp() {
        // Удаляем тестового пользователя
        if (testUser.getAccessToken() != null) {
            stepUser.removeUser(testUser);
        }
    }
}