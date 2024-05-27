Успел сделать пункты 1-5 и 9.
В пункте 5а тест падает с ошибкой из-за неверного локатора.

Код запускался на java17, Gradle run test using Intellij IDEA.
После установки зависимостей из build.gradle:

Отчет формируется плагином allure. После запуска тестов формируется папка allure-results, которую надо перенести в папку build.
После этого справа в меню Gradle перейти в homework\Tasks\verification и выполнить allureReport.
В компиляторе увидим > Task :allureReport Report successfully generated
Переходим в homework/build/reports/allure-report/allureReport/index.html и запускаем index.html в браузере.

Конфиг файл local.properties с параметрами тестов.

Тесты homework/src/test/java/tests/FunctionalTest.java
Классы-помощники homework/src/test/java/manager