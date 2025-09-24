package com.homemadeigor.lesson3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DemoWebShop {
    private static WebDriver driver;
    private static final String SHOP_URL = "https://demowebshop.tricentis.com";


    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.get(SHOP_URL);

        login();

        Thread.sleep(1000);

        driver.quit();


    }
    public static void login() {
        // Ожидаем появление кнопки "Login"
        WebElement loginLink = waitForElement(By.cssSelector("a.ico-login"), 10);
        loginLink.click();
        
        //driver.findElement(By.cssSelector("a.ico-login"));
        //WebElement element = driver.findElement(By.id("email"));
        //driver.findElement(By.id("Password")).sendKeys("adminadmin");
        //driver.findElement(By.xpath("input[@type='submit']"));

        // Ожидаем форму авторизации
        WebElement emailInput = waitForElement(By.id("Email"), 5);
        emailInput.sendKeys("igor111@mail.ru");


        WebElement passwordInput = waitForElement(By.id("Password"), 5);
        passwordInput.sendKeys("adminadmin");


        WebElement submitBtn = waitForElement(By.xpath("//input[@value='Log in']"), 5);
        submitBtn.click();


    }
    /**
     * Метод ожидания элемента на странице
     *
     * @param by - локатор элемента
     * @param timeOut - время ожидания (секунды)
     * @return найденный элемент
     */
    private static WebElement waitForElement(By by, long timeOut) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeOut)).until(ExpectedConditions.presenceOfElementLocated(by));
    }
}
