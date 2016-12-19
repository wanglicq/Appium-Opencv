package com.auto.test;

import io.appium.java_client.AppiumDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;

import java.io.File;
import java.io.IOException;

/**
 * Created by wangli on 17/12/2016.
 */
public class TakeScreenshot {
    public void take(AppiumDriver wd, String arg) {
        // Set folder name to store screenshots.
        String destDir = "screenshots";
        // Capture screenshot.
        File scrFile = wd.getScreenshotAs(OutputType.FILE);
        // Create folder under project with name "screenshots" provided to destDir.
        new File(destDir).mkdirs();
        // Set file name
        String destFile = arg + ".png";

        try {
            // Copy paste file at destination folder lo cation
            FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
