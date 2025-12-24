package userProfile;

import static1.Constants;
import io.qameta.allure.Step;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;

public class StepUser {

    @Step("Создание нового пользователя")
    public ValidatableResponse createNewUser(User user) {
        return given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(Constants.API_AUTH_REGISTER)  // ← изменили здесь
                .then()
                .statusCode(SC_OK);
    }

    @Step("Удаление пользователя из системы")
    public ValidatableResponse removeUser(User user) {
        System.out.println("Токен для удаления пользователя: " + user.getAccessToken());
        return given()
                .spec(getAuthSpecification(user.getAccessToken()))
                .log().all()
                .when()
                .delete(Constants.API_AUTH_USER)  // ← изменили здесь
                .then()
                .log().all();
    }

    @Step("Извлечение токена доступа из ответа")
    public String getAccessTokenFromResponse(ValidatableResponse response) {
        return response.extract().body().jsonPath().getString("accessToken");
    }

    protected static RequestSpecification getBaseSpecification() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(Constants.BASE_URL)  // ← изменили здесь
                .build();
    }

    protected static RequestSpecification getAuthSpecification(String bearerToken) {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + bearerToken)
                .setBaseUri(Constants.BASE_URL)  // ← изменили здесь
                .build();
    }
}
