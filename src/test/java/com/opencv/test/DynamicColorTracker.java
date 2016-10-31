//package com.opencv.test;
//
//import org.bytedeco.javacpp.opencv_core;
//import org.bytedeco.javacpp.opencv_imgproc;
//import org.bytedeco.javacv.CanvasFrame;
//import org.bytedeco.javacv.FrameGrabber;
//import org.bytedeco.javacv.OpenCVFrameConverter;
//import org.bytedeco.javacv.VideoInputFrameGrabber;
//
//import java.util.ArrayList;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//import static org.bytedeco.javacpp.opencv_core.*;
//import static org.bytedeco.javacpp.opencv_imgcodecs.cvSaveImage;
//import static org.bytedeco.javacpp.opencv_imgproc.*;
//
//public class DynamicColorTracker implements Runnable {
//
//    private volatile int i = 100;
//    private double reds[] = new double[7];
//    private double greens[] = new double[7];
//    private double blues[] = new double[7];
//    private FrameGrabber grabber;
//    private OpenCVFrameConverter.ToIplImage converter;
//    private CanvasFrame canvas;
//    private opencv_core.IplImage img;
//
//
//    public DynamicColorTracker() {
//        grabber = new VideoInputFrameGrabber(0);
//        converter = new OpenCVFrameConverter.ToIplImage();
//        canvas = new CanvasFrame("Video Feed");
//        canvas.setDefaultCloseOperation(canvas.EXIT_ON_CLOSE);
//    }
//
//    public opencv_core.CvScalar meanColor(opencv_core.IplImage img, int x, int y, int w, int h) {
//        opencv_core.CvRect roi = new opencv_core.CvRect(x, y, w, h);
//
//        opencv_core.cvSetImageROI(img, roi);
//        opencv_core.CvScalar sc = opencv_core.cvAvg(img);
//        opencv_core.cvResetImageROI(img);
//        return sc;
//    }
//
//    @Override
//    public void run() {
//
//        try {
//            grabber.start();
//            opencv_core.IplImage temp = null;
//            while (i > 0) {
//                img = converter.convert(grabber.grab());
//                opencv_core.cvFlip(img, img, 1);
//                opencv_imgproc.CvFont mcvf = new opencv_imgproc.CvFont();
//                opencv_imgproc.cvInitFont(mcvf, opencv_imgproc.CV_FONT_HERSHEY_SIMPLEX, 0.5, 0.5, 0, 1, opencv_imgproc.CV_AA);
//                opencv_imgproc.cvPutText(img, "Register your palm by covering the points. Recognition in " + i / 10, cvPoint(100, 50), mcvf, cvScalar(0, 0, 255, 0));
//                opencv_imgproc.cvRectangle(img, cvPoint(250, 100), cvPoint(270, 120), org.bytedeco.javacpp.helper.opencv_core.CV_RGB(255, 0, 0));
//                opencv_imgproc.cvRectangle(img, cvPoint(210, 170), cvPoint(230, 190), org.bytedeco.javacpp.helper.opencv_core.CV_RGB(255, 0, 0));
//                opencv_imgproc.cvRectangle(img, cvPoint(250, 170), cvPoint(270, 190), org.bytedeco.javacpp.helper.opencv_core.CV_RGB(255, 0, 0));
//                opencv_imgproc.cvRectangle(img, cvPoint(310, 170), cvPoint(330, 190), org.bytedeco.javacpp.helper.opencv_core.CV_RGB(255, 0, 0));
//                opencv_imgproc.cvRectangle(img, cvPoint(190, 290), cvPoint(210, 310), org.bytedeco.javacpp.helper.opencv_core.CV_RGB(255, 0, 0));
//                opencv_imgproc.cvRectangle(img, cvPoint(230, 290), cvPoint(250, 310), org.bytedeco.javacpp.helper.opencv_core.CV_RGB(255, 0, 0));
//                opencv_imgproc.cvRectangle(img, cvPoint(290, 290), cvPoint(310, 310), org.bytedeco.javacpp.helper.opencv_core.CV_RGB(255, 0, 0));
//
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException ex) {
//                    Logger.getLogger(DynamicColorTracker.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                canvas.showImage(converter.convert(img));
//                i--;
//
//            }
//
//            temp = img;
//            canvas.showImage(converter.convert(img));
//            opencv_core.CvScalar sc = meanColor(temp, 250, 100, 20, 20);
//            reds[0] = sc.val(2);
//            greens[0] = sc.val(1);
//            blues[0] = sc.val(0);
//            sc = meanColor(temp, 210, 170, 20, 20);
//            reds[1] = sc.val(2);
//            greens[1] = sc.val(1);
//            blues[1] = sc.val(0);
//            sc = meanColor(temp, 250, 170, 20, 20);
//            reds[2] = sc.val(2);
//            greens[2] = sc.val(1);
//            blues[2] = sc.val(0);
//            sc = meanColor(temp, 310, 170, 20, 20);
//            reds[3] = sc.val(2);
//            greens[3] = sc.val(1);
//            blues[3] = sc.val(0);
//            sc = meanColor(temp, 190, 290, 20, 20);
//            reds[4] = sc.val(2);
//            greens[4] = sc.val(1);
//            blues[4] = sc.val(0);
//            sc = meanColor(temp, 230, 290, 20, 20);
//            reds[5] = sc.val(2);
//            greens[5] = sc.val(1);
//            blues[5] = sc.val(0);
//            sc = meanColor(temp, 290, 290, 20, 20);
//            reds[6] = sc.val(2);
//            greens[6] = sc.val(1);
//            blues[6] = sc.val(0);
//            System.out.println("Color Profile:");
//            for (int i = 0; i < 7; i++) {
//                System.out.println("B:" + blues[i] + " G:" + greens[i] + " R:" + reds[i]);
//            }
//            opencv_core.IplImage op = getCombinedImage(img);
//            cvSaveImage("thresh.jpg", op);
//            cvSaveImage("orig.jpg", img);
//            canvas.showImage(converter.convert(op));
//            trackHand();
//        } catch (FrameGrabber.Exception ex) {
//            Logger.getLogger(DynamicColorTracker.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
//
//    private opencv_core.IplImage getThresh(opencv_core.IplImage orgImg, double minr, double maxr, double ming, double maxg, double minb, double maxb) {
//        opencv_core.IplImage imgThreshold = cvCreateImage(cvGetSize(orgImg), 8, 1);
//        opencv_core.CvScalar min_range = new opencv_core.CvScalar(minb, ming, minr, 0);
//        opencv_core.CvScalar max_range = new opencv_core.CvScalar(maxb, maxb, maxr, 0);
//        cvInRangeS(orgImg, min_range, max_range, imgThreshold);
//        cvSmooth(imgThreshold, imgThreshold, CV_MEDIAN, 15, 0, 0, 0);
//        cvMorphologyEx(imgThreshold, imgThreshold, null, null, CV_MOP_OPEN, 1);
//
//        cvSaveImage("thresh" + minr + ".jpg", imgThreshold);
//        return imgThreshold;
//    }
//
//    private IplImage getCombinedImage(IplImage img) {
//        opencv_core.IplImage op = null;
//        for (int i = 0; i < 7; i++) {
//            opencv_core.IplImage thresh = getThresh(img, reds[i] - 20, reds[i] + 20, greens[i] - 20, greens[i] + 20, blues[i] - 20, blues[i] + 20);
//            if (i == 0) {
//                op = thresh;
//            }
//            opencv_core.cvAdd(op, thresh, op);
//
//        }
//        return op;
//    }
//
//
//
//    public void trackHand() throws FrameGrabber.Exception {
//
//        while (true) {
//            img = converter.convert(grabber.grab());
//            opencv_core.cvFlip(img, img, 1);
//            img = getCombinedImage(img);
//            canvas.showImage(converter.convert(img));
//
//        }
//    }
//
//    public enum FingerName {
//        LITTLE, RING, MIDDLE, INDEX, THUMB, UNKNOWN;
//
//        public FingerName getNext() {
//            int nextIdx = ordinal() + 1;
//            if (nextIdx == (values().length)) {
//                nextIdx = 0;
//            }
//            return values()[nextIdx];
//        } // end of getNext()
//
//        public FingerName getPrev() {
//            int prevIdx = ordinal() - 1;
//            if (prevIdx < 0) {
//                prevIdx = values().length - 1;
//            }
//            return values()[prevIdx];
//        } // end of getPrev()
//    }
//
//    private boolean usedName(ArrayList<FingerName> nms, FingerName name) // does the fingers list contain name already?
//    {
//        for (FingerName fn : nms) {
//            if (fn == name) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public static void main(String[] args) {
//        new Thread(new DynamicColorTracker()).start();
//    }
//
//}