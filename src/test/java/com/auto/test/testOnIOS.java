package com.auto.test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

/**
 * Created by PeChen on 8/29/16.
 */
public class testOnIOS {
    AppiumDriver wd;

    @Before
    public void setup() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium-version", "1.0");
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("platformVersion", "9.3");
        capabilities.setCapability("deviceName", "iPhone 6");
        capabilities.setCapability("app", "/Users/PeChen/Library/Developer/Xcode/DerivedData/UICatalog-cqeiiqxltbnvwyesbbiobsvfuxss/Build/Products/Debug-iphonesimulator/UICatalog.app");
        wd = new IOSDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

    }

    @Test
    public void click() {
        int width = wd.manage().window().getSize().width;
        int height = wd.manage().window().getSize().height;
        wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIATableView[1]/UIATableCell[5]/UIAStaticText[1]")).click();
        WebElement title = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIANavigationBar[1]/UIAStaticText[1]"));
        assertEquals("Date Picker", title.getText());
        wd.swipe(width / 100, height  / 2, width * 2 / 3, height / 2, 500);
        WebElement home = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIANavigationBar[1]/UIAStaticText[1]"));
        assertEquals("UICatalog", home.getText());
    }

    @Test
    public void swipe() {
        int width = wd.manage().window().getSize().width;
        int height = wd.manage().window().getSize().height;
//        Assert.assertTrue(wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIANavigationBar[1]/UIAStaticText[1]")).isDisplayed());
//        wd.swipe(111,398,111,310,2000);
//        WebElement date = wd.findElement(By.xpath("/UIAApplication[1]/UIAWindow[2]/UIAStaticText[1]"));
//        assertEquals("Aug 31, 2016, 8:10 PM", date.getText());


        wd.swipe(width / 2, height * 4 / 5, width / 2, height / 4, 1000);
        WebElement home = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIANavigationBar[1]/UIAStaticText[1]"));
        assertEquals("UICatalog", home.getText());
    }


}
