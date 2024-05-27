package manager;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


//методы для страницы поиска
public class SearchHelper {
    private final ApplicationManager manager;

    public SearchHelper(ApplicationManager manager) {
        this.manager = manager;
    }


    void clickSearchButton() {
        manager.driver.findElement(By.cssSelector("div[class*='x-search-box__search-button--bold']")).click();
    }

    void putText(String searchPhrase) {
        manager.driver.findElement(By.tagName("input")).sendKeys(searchPhrase);
    }

    void pickFirstResult() {
        manager.driver.findElement((By.cssSelector("div[data-index='0']"))).click();
    }

    void waitResultsPage() {
        new WebDriverWait(manager.driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[data-index='0']")));
    }

    void switchNextTab() {
        for (String tab : manager.driver.getWindowHandles()) {
            manager.driver.switchTo().window(tab);
        }
    }

    @Step
    public String getSearchInputText() {
        return manager.driver.findElement(By.tagName("input")).getAttribute("value");
    }
}
