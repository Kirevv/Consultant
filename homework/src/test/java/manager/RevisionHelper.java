package manager;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

//методы для страницы редакций
public class RevisionHelper {
    private final ApplicationManager manager;

    public RevisionHelper(ApplicationManager manager) {
        this.manager = manager;
    }

    public void revisionButtonClick() {
        manager.driver.findElement(By.xpath("//*[.='Редакции']")).click();
    }

    public void waitRevisionPage() {
        new WebDriverWait(manager.driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(text(), 'не вступившими в силу')]")));
    }

    @Step
    public int countUnconfirmedRevision() {
        return manager.driver.findElements(By.xpath("//div[contains(text(), 'не вступившими в силу')]")).size();
    }
}
