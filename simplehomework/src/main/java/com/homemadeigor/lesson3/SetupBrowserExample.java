package com.homemadeigor.lesson3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;

public class SetupBrowserExample {
    public static void main(String[] args) throws InterruptedException {// объявление исключения throws InterruptedException
        //настроили аргументы для инстенса Хромдрайвера
        ChromeOptions options = new ChromeOptions();
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-notifications");
        //открыли экземпляр хромдрайвера
        WebDriver driver = new ChromeDriver();
        //запускает загрузку и установку необходимого драйвера
        WebDriverManager.chromedriver().setup();

        //Запуск
        driver.get("https:google.com");

        //Открываем новую вкладку и указываем адрес УРЛа новой вкладки ('about:blank', '_blank');

        ((JavascriptExecutor) driver).executeScript("window.open('about:blank', '_blank');");

        /*
        Переключение на вкладку
        создали массив из вкладок на следующей строке указали через tab
        что активна первая вкладка (у нас есть 0 и 1) можно завести и 2 и 4...
        */
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));


        //Пауза 3 секунд - в начале метода объявлено исключение для метода throws InterruptedException
        Thread.sleep(3000);
        //Закрытие после окончания
        driver.quit();



    }
}
