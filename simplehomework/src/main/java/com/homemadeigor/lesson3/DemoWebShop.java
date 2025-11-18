package com.homemadeigor.lesson3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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
        choosingBook();
        shoppingCart();

        Thread.sleep(4000);

        //driver.quit();
    }

    public static void login() {
        // Ожидаем появление кнопки "Login"
        WebElement loginLink = waitForElementPresence(By.cssSelector("a.ico-login"), 10);
        loginLink.click();


        // Ожидаем форму авторизации
        WebElement emailInput = waitForElementPresence(By.id("Email"), 5);
        emailInput.sendKeys("igor111@mail.ru");


        WebElement passwordInput = waitForElementPresence(By.id("Password"), 5);
        passwordInput.sendKeys("adminadmin");


        WebElement submitBtn = waitForElementPresence(By.xpath("//input[@value='Log in']"), 5);
        submitBtn.click();

    }

    public static void choosingBook() throws InterruptedException {
        // Ожидаем список книг
        WebElement listBookBtn = waitForElementPresence(By.xpath(
                "//div[@class=\"master-wrapper-main\"]//a[contains(text(),\"Books\")]"), 10);
        listBookBtn.click();

        // Ожидаем выбор конкретной книги
        WebElement selectSpecificBook = waitForElementVisibility(By.xpath(
                "//div[@class=\"master-wrapper-main\"]//img[@alt=\"Picture of Computing and Internet\"]"), 5);
        selectSpecificBook.click();
        Thread.sleep(3000);

        // Ожидаем добавления в корзину
        WebElement addToCartBtn = waitForElementPresence(By.xpath("//*[@id=\"add-to-cart-button-13\"]"), 5);
        addToCartBtn.click();
    }

    public static void shoppingCart() {
        // Ожидаем форму входа в корзину
        WebElement shoppingCart = waitForElementPresence(By.xpath("//*[@id=\"topcartlink\"]//span[@class=\"cart-label\"]"), 5);
        shoppingCart.click();

        //Кликаем в чек бокс для подтверждения условия приобретения
        WebElement checkboxAgree = waitForElementPresence(By.xpath("//*[@id=\"termsofservice\"]"), 10);
        checkboxAgree.click();

        //Кликаем кнопку Checkout
        WebElement checkoutBtn = waitForElementPresence(By.xpath("//*[@id=\"checkout\"]"), 10);
        checkoutBtn.click();

        //Заполняем поле Country
        WebElement coutrySelect = waitForElementVisibility(By.xpath("//*[@id=\"BillingNewAddress_CountryId\"]"), 10);
        // Находим элемент select
        // Создаем объект Select
        Select select = new Select(coutrySelect);
        // Выбираем страну по значению (например, "Angola")
        select.selectByValue("91");

        // Заполняем поле City
        WebElement city = waitForElementPresence(By.xpath("//*[@id=\"BillingNewAddress_City\"]"), 10);
        city.sendKeys("Saint Peterburg");

        //Заполняем поле Adress 1
        WebElement address1 = waitForElementPresence(By.xpath("//*[@id=\"BillingNewAddress_Address1\"]"), 10);
        address1.sendKeys("Streen Star");

        //Заполняем поле Postal code
        WebElement postalCode = waitForElementPresence(By.xpath("//*[@id=\"BillingNewAddress_ZipPostalCode\"]"), 10);
        postalCode.sendKeys("198206");

        //Заполняем поле Phone number
        WebElement phoneNumber = waitForElementPresence(By.xpath("//*[@id=\"BillingNewAddress_PhoneNumber\"]"), 10);
        phoneNumber.sendKeys("2(4)323423");

        //Подтверждаем заполнение billing Address кнопкой Continue
        WebElement continueBtn = waitForElementVisibility(By.xpath("//*[@id='billing-buttons-container']/input"), 10);
        continueBtn.click();
    }


    /**
     * Метод ожидания элемента на странице
     * presenceOfElementLocated - вариант, когда надо убедиться в том, что элемент появился в DOM, но не нужно
     * с ним взаимодействовать
     *
     * @param by      - локатор элемента
     * @param timeOut - время ожидания (секунды)
     * @return найденный элемент
     */

    private static WebElement waitForElementPresence(By by, long timeOut) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeOut)).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    /**
     * Метод ожидания элемента на странице
     * visibilityOfElementLocated - вариант, когда гарантированно с элементом можно взаимодействовать (кликать)
     *
     * @param by - локатор элемента
     *           * @param timeOut - время ожидания (секунды)
     *           * @return найденный элемент
     */

    private static WebElement waitForElementVisibility(By by, long timeOut) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeOut)).until(ExpectedConditions.visibilityOfElementLocated(by));
    }

}