package com.auto.test;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

/**
 * Created by wangli on 17/12/2016.
 */
public class Matching {
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
        //Imgproc.matchTemplate(src, tmp, result, Imgproc.TM_SQDIFF_NORMED);
        Imgproc.matchTemplate(src, tmp, result, Imgproc.TM_CCOEFF_NORMED);
        //Core.normalize(result,result,0,1,Core.NORM_MINMAX,-1,new Mat());


        // Got location
        Core.MinMaxLocResult mmr = Core.minMaxLoc(result);
        Point matchLoc;
        double matchPercentage;
        //matchLoc = mmr.minLoc;
        //matchPercentage = 1 - mmr.minVal;
        matchLoc = mmr.maxLoc;
        matchPercentage = mmr.maxVal;
        System.out.println(matchPercentage);

        if(matchPercentage >= 0.95) {
            //show what we got
            Imgproc.rectangle(src, matchLoc,
                    new Point(matchLoc.x + tmp.cols(), matchLoc.y + tmp.rows()), new Scalar(0, 255, 0));

            Imgcodecs.imwrite(args[2], src);

            //return location
            Double x1 = matchLoc.x;
            Double y1 = matchLoc.y;
            int x = x1.intValue() + tmp.cols() / 2;
            int y = y1.intValue() + tmp.rows() / 2;

            System.out.println("x= " + x);
            System.out.println("y= " + y);

            return new int[]{x, y};
        }
        else {
            return null;
        }

    }
}
