package static1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class WebDriverProvider {
    private static final Properties configuration;

    // Загружаем конфигурацию при инициализации класса
    static {
        configuration = loadConfiguration();
    }

    private static Properties loadConfiguration() {
        Properties props = new Properties();

        // Загружаем файл из classpath (рекомендуемый способ для Maven/Gradle проектов)
        try (InputStream input = WebDriverProvider.class.getClassLoader().getResourceAsStream("browser.properties")) {
            if (input == null) {
                throw new RuntimeException("Файл browser.properties не найден в classpath. " +
                        "Убедитесь, что он находится в src/test/resources");
            }
            props.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при загрузке конфигурационного файла browser.properties", e);
        }
        return props;
    }

    public static WebDriver createDriver() {
        String browser = configuration.getProperty("browser", "chrome").toLowerCase();

        switch (browser) {
            case "yandex":
                return setupYandexDriver();
            case "chrome":
                return setupChromeDriver();
            default:
                throw new IllegalArgumentException("Неподдерживаемый браузер: " + browser +
                        ". Поддерживаемые браузеры: chrome, yandex");
        }
    }

    private static WebDriver setupYandexDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        // Указываем путь к драйверу для Yandex Browser
        String yandexDriverPath = "./src/test/resources/yandexdriver.exe";
        System.setProperty("webdriver.chrome.driver", yandexDriverPath);

        // Указываем путь к бинарнику Yandex Browser
        String yandexPath = configuration.getProperty("yandex.browser.path");
        if (yandexPath == null || yandexPath.isEmpty()) {
            throw new IllegalArgumentException("Для запуска Yandex Browser необходимо указать путь к браузеру в конфигурации (yandex.browser.path).");
        }
        options.setBinary(yandexPath);

        return new ChromeDriver(options);
    }

    private static WebDriver setupChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        // Автоматически устанавливаем правильную версию ChromeDriver
        WebDriverManager.chromedriver().setup();

        return new ChromeDriver(options);
    }
}