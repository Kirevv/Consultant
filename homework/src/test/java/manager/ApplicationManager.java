package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Properties;

public class ApplicationManager {
    protected WebDriver driver;
    private SearchHelper search;
    private DocumentHelper document;
    private RevisionHelper revision;
    private Common common;
    public Properties properties;

    public void init(String browser, Properties properties) {
        this.properties = properties;

        //кроссбраузерность
        if (driver == null) {
            if ("chrome".equals(browser)) {
                driver = new ChromeDriver();
            } else if ("firefox".equals(browser)) {
                driver = new FirefoxDriver();
            } else {
                throw new IllegalArgumentException(String.format("Unknown browser %s", browser));
            }

            //завершение работы драйвера
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));

            //открытие сайта из параметров
            driver.get(properties.getProperty("web.baseUrl"));
            driver.manage().window().maximize();

            //текст в инпут поиска
            search().putText("нк ч2");

            //нажатие кнопки поиска
            search().clickSearchButton();

            //ожидание загрузки страницы
            search().waitResultsPage();

            //выбор первого результат
            search().pickFirstResult();

            //переклювение на новую страницу
            search().switchNextTab();
        }
    }

    //"ленивая" инициализация
    public SearchHelper search() {
        if (search == null) {
            search = new SearchHelper(this);
        }
        return search;
    }

    public DocumentHelper document() {
        if (document == null) {
            document = new DocumentHelper(this);
        }
        return document;
    }

    public RevisionHelper revision() {
        if (revision == null) {
            revision = new RevisionHelper(this);
        }
        return revision;
    }

    public Common common() {
        if (common == null) {
            common = new Common(this);
        }
        return common;
    }


}
