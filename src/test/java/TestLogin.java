import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.PageForgotPassword;
import pages.PageHome;
import pages.PageLogin;
import pages.PageRegister;
import userProfile.StepUser;
import userProfile.User;

import static org.junit.Assert.assertTrue;


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

        // Создаем тестового пользователя через API
        testUser = new User()
                .withName("Мария-Мерабелла")
                .withEmail("test" + System.currentTimeMillis() + "@example.com")
                .withPassword("password314");

        var response = stepUser.createNewUser(testUser);
        accessToken = stepUser.getAccessTokenFromResponse(response);
        testUser.updateAccessToken(accessToken);
    }

    @Test
    @DisplayName("Вход по кнопке 'Войти в аккаунт' на главной странице")
    @Description("Проверка успешного входа через кнопку на главной")
    public void loginViaMainPageButtonTest() {
        homePage.openHomePage();
        homePage.clickEnterAccountButton();

        assertTrue("Страница логина не открылась", loginPage.isLoginPageOpened());

        loginPage.loginUser(testUser.getEmail(), testUser.getPassword());
        homePage.waitForCheckoutButton();

        assertTrue("Вход не выполнен - кнопка 'Оформить заказ' не отображается",
                homePage.isCheckoutButtonDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку 'Личный кабинет'")
    @Description("Проверка успешного входа через кнопку Личный кабинет")
    public void loginViaPersonalAccountButtonTest() {
        homePage.openHomePage();
        homePage.clickPersonalAccountButton();

        assertTrue("Страница логина не открылась", loginPage.isLoginPageOpened());

        loginPage.loginUser(testUser.getEmail(), testUser.getPassword());
        homePage.waitForCheckoutButton();

        assertTrue("Вход не выполнен - кнопка 'Оформить заказ' не отображается",
                homePage.isCheckoutButtonDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    @Description("Проверка перехода на страницу входа из формы регистрации")
    public void loginViaRegisterFormLinkTest() {
        registerPage.openRegistrationPage();
        assertTrue("Страница регистрации не открылась", registerPage.isRegisterPageLoaded());

        registerPage.clickLoginLink();
        assertTrue("Страница логина не открылась", loginPage.isLoginPageOpened());

        loginPage.loginUser(testUser.getEmail(), testUser.getPassword());
        homePage.waitForCheckoutButton();

        assertTrue("Вход не выполнен - кнопка 'Оформить заказ' не отображается",
                homePage.isCheckoutButtonDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("Проверка перехода на страницу входа из формы восстановления пароля")
    public void loginViaForgotPasswordLinkTest() {
        forgotPasswordPage.openForgotPasswordPage();
        assertTrue("Страница восстановления пароля не открылась",
                forgotPasswordPage.isForgotPasswordPageLoaded());

        forgotPasswordPage.clickSignInLink();
        assertTrue("Страница логина не открылась", loginPage.isLoginPageOpened());

        loginPage.loginUser(testUser.getEmail(), testUser.getPassword());
        homePage.waitForCheckoutButton();

        assertTrue("Вход не выполнен - кнопка 'Оформить заказ' не отображается",
                homePage.isCheckoutButtonDisplayed());
    }

    @After
    public void cleanUp() {
        // Удаляем тестового пользователя
        if (testUser != null && accessToken != null) {
            testUser.updateAccessToken(accessToken);
            stepUser.removeUser(testUser);
        }
    }
}