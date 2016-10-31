package com.auto.test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


import static org.junit.Assert.assertEquals;

/**
 * Created by PeChen on 8/18/16.
 */
public class testWechat {
    AppiumDriver wd;
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    @Before
    public void setup() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium-version", "1.0");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "5.0");
        capabilities.setCapability("deviceName", "192.168.56.101:5555");
        capabilities.setCapability("app", "/Users/PeChen/Downloads/weixin6322android821.apk");
        wd = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }

    //login
    @Test
    public void login() {
        wd.findElement(By.xpath("//android.view.View[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.RelativeLayout[1]/android.widget.Button[1]")).click();
        wd.findElement(By.xpath("//android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[2]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[2]/android.widget.Button[2]")).click();
        wd.findElement(By.xpath("//android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[2]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.EditText[1]")).sendKeys("251344698");
        wd.findElement(By.xpath("//android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[2]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.EditText[1]")).sendKeys("password");
        wd.findElement(By.xpath("//android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[2]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.Button[1]")).click();
        wd.findElement(By.xpath("//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.LinearLayout[1]/android.widget.Button[1]")).click();
//        takeScreenShot();

        wd.quit();
    }


    @Test
    public void sendText() {
        wd.findElement(By.xpath("//android.view.View[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.RelativeLayout[1]/android.widget.Button[1]")).click();
        wd.findElement(By.xpath("//android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[2]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[2]/android.widget.Button[2]")).click();
        wd.findElement(By.xpath("//android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[2]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.EditText[1]")).sendKeys("251344698");
        wd.findElement(By.xpath("//android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[2]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.EditText[1]")).sendKeys("password");
        wd.findElement(By.xpath("//android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[2]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.Button[1]")).click();
        wd.findElement(By.xpath("//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.LinearLayout[1]/android.widget.Button[1]")).click();
//        wd.findElement(By.xpath("//android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.support.v4.view.ViewPager[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.view.View[1]")).click();

        wd.findElement(By.xpath("//android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.support.v4.view.ViewPager[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.ImageView[1]")).click();
        wd.findElement(By.xpath("//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.EditText[1]")).sendKeys("appium test");
        wd.findElement(By.xpath("//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.RelativeLayout[1]/android.widget.Button[1]")).click();

        WebElement text = wd.findElement(By.xpath("//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[2]/android.widget.FrameLayout[1]/android.widget.ListView[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.TextView[1]"));
        assertEquals("appium test", text.getText());
        wd.quit();;
    }

    //send moments
    @Test
    public void sendPrivateMoments() {
        //login
        wd.findElement(By.xpath("//android.view.View[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.RelativeLayout[1]/android.widget.Button[1]")).click();
        wd.findElement(By.xpath("//android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[2]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[2]/android.widget.Button[2]")).click();
        wd.findElement(By.xpath("//android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[2]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.EditText[1]")).sendKeys("251344698");
        wd.findElement(By.xpath("//android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[2]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.EditText[1]")).sendKeys("password");
        wd.findElement(By.xpath("//android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[2]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.Button[1]")).click();
        wd.findElement(By.xpath("//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.LinearLayout[1]/android.widget.Button[1]")).click();

        //go to moments page
        wd.findElement(By.xpath("//android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[3]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.ImageView[1]")).click();
        wd.findElement(By.xpath("//android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.support.v4.view.ViewPager[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/com.tencent.mm.ui.MMImageView[1]")).click();

        //send moments
        WebElement moments =  wd.findElement(By.id("com.tencent.mm:id/dp"));
        TouchAction action = new TouchAction(wd);
        action.longPress(moments, 3000).release().perform();
        wd.findElement(By.id("com.tencent.mm:id/b5b")).click();
        wd.findElement(By.id("com.tencent.mm:id/b5e")).sendKeys("This moments is for appium test");

        wd.findElement(By.id("com.tencent.mm:id/abp")).click();
        wd.findElement(By.xpath("//android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[2]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ExpandableListView[1]/android.widget.LinearLayout[2]/android.widget.RelativeLayout[1]/android.widget.ImageView[1]")).click();
        wd.findElement(By.id("com.tencent.mm:id/eg")).click();
        wd.findElement(By.xpath("//android.view.View[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.LinearLayout[2]/android.widget.LinearLayout[1]/android.widget.TextView[1]")).click();

        wd.findElement(By.xpath("//android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.ImageView[2]")).click();
        WebElement text = wd.findElement(By.xpath("//android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[2]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[3]/android.widget.LinearLayout[2]/android.widget.LinearLayout[2]/android.widget.TextView[1]"));
        assertEquals("This moments is for appium test", text.getText());

    }

    @Test
    public void opencvTest() throws Exception {
        wd.findElement(By.xpath("//android.view.View[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.RelativeLayout[1]/android.widget.Button[1]"));
        takeScreenShot("screenshot_login");
        int location[] = getMatchingLocation("screenshots/screenshot_login.png", "temp/Login.png", "compare/login_compared.png");
        wd.tap(1, location[0], location[1], 100);

    }

    public void takeScreenShot(String arg) {
        // Set folder name to store screenshots.
        String destDir = "screenshots";
        // Capture screenshot.
        File scrFile = wd.getScreenshotAs(OutputType.FILE);
        // Create folder under project with name "screenshots" provided to destDir.
        new File(destDir).mkdirs();
        // Set file name
        String destFile = arg + ".png";

        try {
            // Copy paste file at destination folder location
            FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int[] getMatchingLocation(String... args) throws Exception {
        // create input and output mat
        Mat src = Imgcodecs.imread(
                args[0], 0);
        Mat tmp = Imgcodecs.imread(
                args[1], 0);
        // Create the result matrix
        int result_cols = src.cols() - tmp.cols() +1;
        int result_rows = src.rows() - tmp.rows() +1;
        Mat result = new Mat(result_cols,result_rows, CvType.CV_32FC1);

        // Match Template Function from OpenCV
        Imgproc.matchTemplate(src, tmp, result, Imgproc.TM_SQDIFF_NORMED);
        Core.normalize(result,result,0,1,Core.NORM_MINMAX,-1,new Mat());

        // Got location
        Core.MinMaxLocResult mmr = Core.minMaxLoc(result);
        Point matchLoc;
        matchLoc = mmr.minLoc;

        //show what we got
        Imgproc.rectangle(src, matchLoc,
                new Point(matchLoc.x + tmp.cols(), matchLoc.y + tmp.rows()), new Scalar(0,255,0));

        Imgcodecs.imwrite(args[2],src);

        //return location
        Double x1 = matchLoc.x;
        Double y1 = matchLoc.y;
        int x = x1.intValue() + tmp.cols()/2;
        int y = y1.intValue() + tmp.rows()/2;

        System.out.print("x= " + x);
        System.out.print("y= " + y);

        return new int[] {x,y};


    }


}
