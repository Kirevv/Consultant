package manager;

import io.qameta.allure.Step;

//общие методы
public class Common {
    private final ApplicationManager manager;

    public Common(ApplicationManager manager) {
        this.manager = manager;
    }

    public void refreshPage(ApplicationManager manager) {
        manager.driver.navigate().refresh();
    }

    @Step
    public String getPageTitleText(ApplicationManager manager) {
        return manager.driver.getTitle();
    }

    public long getTime() {
        return System.currentTimeMillis();
    }
}
