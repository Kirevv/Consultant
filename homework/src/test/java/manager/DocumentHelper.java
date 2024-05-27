package manager;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

//методы для страницы с документом
public class DocumentHelper {
    private final ApplicationManager manager;

    public DocumentHelper(ApplicationManager manager) {
        this.manager = manager;
    }

    public void waitDocumentPage() {
        new WebDriverWait(manager.driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[@id='mainContent']/div[4]/iframe[1]")));
    }

    @Step
    public String getExpectedDocumentTitleText() {
        return manager.driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[3]/div[1]/div[5]")).getText();
    }
}
