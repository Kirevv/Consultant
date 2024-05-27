package tests;

import manager.ApplicationManager;
import org.junit.jupiter.api.BeforeEach;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
    protected static ApplicationManager app;

    //фикстура запуска тестов
    @BeforeEach
    public void setUp() throws IOException {
        if (app == null) {
            //добавление параметров тестов из файла
            var properties = new Properties();
            properties.load(new FileReader(System.getProperty("target", "local.properties")));
            app = new ApplicationManager();
            //параметризация браузера
            app.init(properties.getProperty("browser"), properties);
        }
    }


    //используемые константы
    String expectedText1 = "налоговый кодекс";
    String expectedText2 = "часть вторая";
    String searchText = "нк ч2";
}
