package com.homemadeigor.lesson3;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Start {
    public static void main(String[] args) throws InterruptedException {
        //вот эта история с указанием расположения webdriver устарела - теперь selenium-java сам находит
        //где лежит драйвер
        //показывает где находится webdriver. Руками прописываем адрес до папке где расположен webriver.
        //System.setProperty("webdriver.chrome.driver", "simplehomework/src/test/resources/chromedriver");

        //создали экземпляр Webdriver
        WebDriver driver = new ChromeDriver();

        //передали в созданный экземпляр на вход данные сайта который хотели бы открыть
        driver.get("https://developer.chrome.com/docs/chromedriver/get-started?hl=ru");

        //добавили 5 секунд ожидания, чтобы увидеть, что браузер открывался
        Thread.sleep(5000);
        //закрывает текущую вкладку браузера
        // driver.close();
        //закрывает браузер целиком
        driver.quit();
    }
}
