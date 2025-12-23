package userProfile;

public class User {
    private String name;
    private String email;
    private String password;
    private String accessToken;

    // Конструкторы
    public User(String name, String email, String password, String accessToken) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.accessToken = accessToken;
    }

    public User() {
        // конструктор без параметров
    }

    // Все сеттеры
    public User withName(String name) {
        this.name = name;
        return this;
    }

    public User withEmail(String email) {
        this.email = email;
        return this;
    }

    public User withPassword(String password) {
        this.password = password;
        return this;
    }

    // Все геттеры
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    // Методы для работы с токеном
    public String getAccessToken() {
        return accessToken;
    }

    public void updateAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}