package com.testapp.tests;

import com.microsoft.appcenter.appium.Factory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import com.microsoft.appcenter.appium.EnhancedAndroidDriver;
import org.junit.rules.TestWatcher;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.junit.Rule;
import org.junit.*;

import io.appium.java_client.*;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.android.nativekey.AndroidKey;

public class MainTest {
    @Rule
    public TestWatcher watcher = Factory.createWatcher();

    private static EnhancedAndroidDriver<MobileElement> driver;

    @Before
    public void startUp() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "device");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "android");
        capabilities.setCapability("appPackage", "com.example.testapp");
        capabilities.setCapability("appActivity", "com.example.testapp.MainActivity");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");

        try {
            MainTest.driver = Factory.createAndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
        }
        catch(MalformedURLException e){
            System.out.println(e);
        }
    }

    @Test
    public void testRun() {
        String testMessage = "Test12345";

        //setting main elements
        MobileElement editText = driver.findElementById("com.example.testapp:id/editText");
        MobileElement sendButton = driver.findElementById("com.example.testapp:id/send_button");
        MobileElement resetButton = driver.findElementById("com.example.testapp:id/reset_button");
        MobileElement alertCheckbox = driver.findElementById("com.example.testapp:id/alert_checkbox");

        editText.sendKeys(testMessage);
        sendButton.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.label("Send text");

        MobileElement textView = driver.findElementById("com.example.testapp:id/textView");
        Assert.assertEquals(testMessage, textView.getText());
        
        ((AndroidDriver<MobileElement>) MainTest.driver).pressKey(new KeyEvent(AndroidKey.BACK));
        editText.sendKeys(testMessage);
        alertCheckbox.click();
        sendButton.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.label("Send text as alert");

        String message = driver.findElementById("android:id/message").getText();
        driver.findElementById("android:id/button3").click();
        Assert.assertEquals(testMessage, message);

        resetButton.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.label("Reset elements");

        Assert.assertEquals("Enter a message", editText.getText());
        Assert.assertEquals("false", alertCheckbox.getAttribute("checked"));
    }

    @After
    public void TearDown(){
        driver.label("Stopping App");
        driver.quit();
    }
}