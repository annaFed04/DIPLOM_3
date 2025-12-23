import Static.Constants;
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

        homePage.clickEnterAccountButton();
        loginPage.loginUser(testUser.getEmail(), testUser.getPassword());

        homePage.waitCheckoutButton();
        assertTrue("Вход не выполнен - кнопка 'Оформить заказ' не отображается",
                driver.findElement(By.xpath(".//button[text()='Оформить заказ']")).isDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку 'Личный кабинет'")
    @Description("Проверка успешного входа через кнопку Личный кабинет")
    public void loginViaPersonalAccountButtonTest() {
        driver.get(Constants.BASE_URL);

        homePage.enterPersonalAccountButton();
        loginPage.loginUser(testUser.getEmail(), testUser.getPassword());

        homePage.waitCheckoutButton();
        assertTrue("Вход не выполнен - кнопка 'Оформить заказ' не отображается",
                driver.findElement(By.xpath(".//button[text()='Оформить заказ']")).isDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    @Description("Проверка перехода на страницу входа из формы регистрации")
    public void loginViaRegisterFormLinkTest() {
        driver.get(Constants.PAGE_REGISTER);

        registerPage.clickLoginLink();  // метод из PageRegister
        loginPage.loginUser(testUser.getEmail(), testUser.getPassword());

        homePage.waitCheckoutButton();
        assertTrue("Вход не выполнен - кнопка 'Оформить заказ' не отображается",
                driver.findElement(By.xpath(".//button[text()='Оформить заказ']")).isDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("Проверка перехода на страницу входа из формы восстановления пароля")
    public void loginViaForgotPasswordLinkTest() {
        driver.get(Constants.BASE_URL + "/forgot-password");

        forgotPasswordPage.clickSignInLink();  // метод из PageForgotPassword
        loginPage.loginUser(testUser.getEmail(), testUser.getPassword());

        homePage.waitCheckoutButton();
        assertTrue("Вход не выполнен - кнопка 'Оформить заказ' не отображается",
                driver.findElement(By.xpath(".//button[text()='Оформить заказ']")).isDisplayed());
    }

    @After
    public void cleanUp() {
        // Удаляем тестового пользователя
        if (testUser.getAccessToken() != null) {
            stepUser.removeUser(testUser);
        }
    }
}