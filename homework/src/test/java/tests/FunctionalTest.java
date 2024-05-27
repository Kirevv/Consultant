package tests;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Properties;


public class FunctionalTest extends TestBase {

    @Test
    public void pageLoadTime() {
        //получаем время начала теста
        long startTime = app.common().getTime();

        //обновляем страницу
        app.common().refreshPage(app);

        //ждем, пока страницу загрузится
        Allure.step("Waiting document page load", step -> {
            app.document().waitDocumentPage();
        });

        //получаем время окончания теста
        long endTime = app.common().getTime();

        //сравниваем разницу второго и первого значения с параметром из local.properties, перед этим приведя к одному типу
        Allure.step("Validating results", step -> {
            Assertions.assertTrue(endTime - startTime < Long.parseLong(app.properties.getProperty("waitingTime")));
        });
    }


    @Test
    public void pageTitleText() {
        //получаем заголовок страницы
        String pageTitle = app.common().getPageTitleText(app);

        //сравниваем содержимое без учета регистра с первым параметром
        Allure.step("Validating result for expectedText1", step -> {
            Assertions.assertTrue(pageTitle.toLowerCase().contains(expectedText1.toLowerCase()));
        });

        //сравниваем содержимое без учета регистра с первым параметром
        Allure.step("Validating result for expectedText2", step -> {
            Assertions.assertTrue(pageTitle.toLowerCase().contains(expectedText2.toLowerCase()));
        });
    }

    @Test
    public void documentTitleText() {
        //дожидаемся окончания загрузки документа
        Allure.step("Waiting document page load", step -> {
            app.document().waitDocumentPage();
        });

        //получаем заголовок из текста документа
        String expectedDocumentTitleText1 = app.document().getExpectedDocumentTitleText();

        //сравниваем содержимое без учета регистра с первым параметром
        Allure.step("Validating result for expectedText1", step -> {
            Assertions.assertTrue(expectedDocumentTitleText1.toLowerCase().contains(expectedText1.toLowerCase()));
        });

        //сравниваем содержимое без учета регистра с первым параметром
        Allure.step("Validating result for expectedText2", step -> {
            Assertions.assertTrue(expectedDocumentTitleText1.toLowerCase().contains(expectedText2.toLowerCase()));
        });
    }

    @Test
    public void searchInputText() {

        //дожидаемся окончания загрузки документа
        Allure.step("Waiting document page load", step -> {
            app.document().waitDocumentPage();
        });

        //сохраняем текст из инпута поиска
        String actualText = app.search().getSearchInputText();

        //сравниваем сохраненный текст с ожидаемым
        Allure.step("Validating result for searchText2", step -> {
            Assertions.assertEquals(searchText, actualText);
        });
    }

    @Test
    public void documentWithUnconfirmedChangesExistence() {

        //дожидаемся окончания загрузки документа
        Allure.step("Waiting document page load", step -> {
            app.document().waitDocumentPage();
        });

        //жмем кнопку Редакции
        Allure.step("Revision button click", step -> {
            app.revision().revisionButtonClick();
        });

        //дожидаемся окончания загрузки страницы с редакциями
        Allure.step("Waiting revision page load", step -> {
            app.revision().waitRevisionPage();
        });

        //считаем все элементы с требуемым условием
        int getCount = app.revision().countUnconfirmedRevision();

        //сравниваем счетчик с 0
        Allure.step("Validating result for unconfirmed changes existence ", step -> {
            Assertions.assertTrue(getCount > 0);
        });

    }

}
