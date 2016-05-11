package com.yrkh;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.List;

import static org.junit.Assert.*;

public class MainWDTest {

    @Test
    public void testFindAllLinks() {
        WebDriver driver = new FirefoxDriver();
        driver.get("https://www.google.com/");
        List<WebElement> links = driver.findElements(By.tagName("a"));
        System.out.print("Number of links: " + links.size());
        assertEquals(40, links.size());
        driver.close();
    }

    @Test
    public void testGetLinkByText() {
        WebDriver driver = new FirefoxDriver();
        driver.get("https://www.google.com/");
        WebElement link = driver.findElement(By.linkText("Gmail"));
        assertEquals("https://mail.google.com/mail/?tab=wm", link.getAttribute("href"));
        driver.close();
    }

    @Test
    public void testGetLinkByPartialText() {
        WebDriver driver = new FirefoxDriver();
        driver.get("https://www.google.com/");
        WebElement mailLink =
                driver.findElement(By.partialLinkText("mail"));
        System.out.println(mailLink.getText());
        assertEquals("Gmail", mailLink.getText());
        driver.close();
    }
}