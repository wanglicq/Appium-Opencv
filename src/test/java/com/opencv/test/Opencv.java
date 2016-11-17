package com.opencv.test;

import org.junit.Test;
import org.opencv.calib3d.Calib3d;
import org.opencv.core.*;
import org.opencv.features2d.DescriptorExtractor;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.FeatureDetector;
import org.opencv.features2d.Features2d;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.opencv.core.CvType.CV_8UC1;
import static org.opencv.imgcodecs.Imgcodecs.IMREAD_GRAYSCALE;
import static org.opencv.imgcodecs.Imgcodecs.imwrite;
import static org.opencv.imgproc.Imgproc.CHAIN_APPROX_SIMPLE;
import static org.opencv.imgproc.Imgproc.COLOR_BGR2GRAY;

public class Opencv {

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    private MatOfKeyPoint regions;

    public static void smooth(String filename) {
        Mat image = new Mat();
        image = Imgcodecs.imread(filename);
        if (image != null) {
            Imgproc.blur(image, image, new Size(3, 3));
            System.out.println("Smooth Done!");
        }
    }

    public static void templateMatching(String... args) throws Exception {
        Mat src = Imgcodecs.imread(args[0], IMREAD_GRAYSCALE);
        Mat tmp = Imgcodecs.imread(args[1], IMREAD_GRAYSCALE);
        int result_cols = src.cols() - tmp.cols() + 1;
        int result_rows = src.rows() - tmp.rows() + 1;
        Mat result = new Mat(result_cols, result_rows, CvType.CV_32FC1);

        Mat src_roi = new Mat();

        Rect roi = new Rect(0, 1400, 1400, 500);
        src_roi = src.submat(roi);
        Imgcodecs.imwrite("src_roi.png", src_roi);

        // Match Template Function from OpenCV
        Imgproc.matchTemplate(src, tmp, result, Imgproc.TM_CCORR_NORMED);

//        Core.normalize(result,result,0,1,Core.NORM_MINMAX,-1,new Mat());

        Mat v = new Mat();
        Mat vv = new Mat();
        result.convertTo(vv, CV_8UC1);
        List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
        Imgproc.findContours(vv, contours, v, Imgproc.RETR_LIST,
                CHAIN_APPROX_SIMPLE);

        double maxArea = 100;
        int maxAreaIdx = -1;
        Rect r = null;
        ArrayList<Rect> rect_array = new ArrayList<Rect>();
        ArrayList<Point> locations = new ArrayList<>();
        Point loc = null;

        for (int idx = 0; idx < contours.size(); idx++) {
            Mat contour = contours.get(idx);
            double contourarea = Imgproc.contourArea(contour);
            if (contourarea > maxArea) {
                // maxArea = contourarea;
                maxAreaIdx = idx;
                r = Imgproc.boundingRect(contours.get(maxAreaIdx)); //Calculates the up-right bounding rectangle of a point set
                rect_array.add(r);
                Imgproc.rectangle(src, r.br(), new Point(r.tl().x + tmp.cols(), r.tl().y + tmp.rows()), new Scalar(0, 0, 255), 3, 8, 0);
                loc = new Point(r.tl().x + tmp.cols() / 2, r.tl().y + tmp.rows() / 2);
                locations.add(loc);
            }

        }


        Imgcodecs.imwrite(args[2], src);
        System.out.print("done");
        for (int i = 0; i < locations.size(); i++) {
            System.out.print(locations.get(i));
        }

    }

    public static void sift(String... args) {
        Mat src = Imgcodecs.imread(
                "screenshots/" + args[0]);
        Mat tmp = Imgcodecs.imread(
                "temp/" + args[1]);

        Mat v = new Mat();
        Mat vv = new Mat();
        Imgproc.cvtColor(tmp, v, COLOR_BGR2GRAY);
        Imgproc.cvtColor(src, vv, COLOR_BGR2GRAY);
        FeatureDetector fd = FeatureDetector.create(FeatureDetector.FAST);
        MatOfKeyPoint tmp_regions = new MatOfKeyPoint();
        MatOfKeyPoint src_regions = new MatOfKeyPoint();
        fd.detect(v, tmp_regions);
        fd.detect(vv, tmp_regions);
        Mat output = new Mat();
//        Features2d.drawKeypoints(v, regions, output);
        Features2d.drawKeypoints(vv, tmp_regions, output);

        Imgcodecs.imwrite("result.png", output);
        System.out.print("done");
    }

    public static void main(String[] args) throws Exception {
//        smooth("thoughtworks.png");
//        sift("screenshot.png", "thoughtworks.png");
        templateMatching("screenshots_PianoTiles/screenshot_black.png", "temp_PianoTiles/black.png", "compare_PianoTiles/blacks_compared.png");
    }

}
