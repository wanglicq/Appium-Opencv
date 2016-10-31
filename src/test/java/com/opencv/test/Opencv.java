package com.opencv.test;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class Opencv {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static void smooth(String filename) {
        Mat image = new Mat();
        image = Imgcodecs.imread(filename);
        if (image != null) {
            Imgproc.blur(image,image, new Size(3,3));
            System.out.println("Smooth Done!");
        }
    }

    public static void templateMatching(String... args) throws Exception {
        Mat src = Imgcodecs.imread(
                args[0], 0);
        Mat tmp = Imgcodecs.imread(
                args[1], 0);
        int result_cols = src.cols() - tmp.cols() +1;
        int result_rows = src.rows() - tmp.rows() +1;
        Mat result = new Mat(result_cols,result_rows, CvType.CV_32FC1);

        // Match Template Function from OpenCV
        Imgproc.matchTemplate(src, tmp, result, Imgproc.TM_SQDIFF_NORMED);
        Core.normalize(result,result,0,1,Core.NORM_MINMAX,-1,new Mat());

        Core.MinMaxLocResult mmr = Core.minMaxLoc(result);

        Point matchLoc;
        matchLoc = mmr.minLoc;

        Imgproc.rectangle(src, matchLoc,
                new Point(matchLoc.x + tmp.cols(), matchLoc.y + tmp.rows()), new Scalar(0,255,0));

        Imgcodecs.imwrite(args[2],src);
        System.out.print("done");
    }

    public static void main(String[] args) throws Exception {
//        smooth("thoughtworks.png");
        templateMatching("screenshots/screenshot_login.png", "temp/Login.png", "compare/login_compared.png");
    }

}
