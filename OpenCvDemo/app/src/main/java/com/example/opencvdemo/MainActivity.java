package com.example.opencvdemo;

import androidx.appcompat.app.AppCompatActivity;
import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    private ImageView imageView;
    //private ImageView grayImgView;
    private Button button;

    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS: {
                    Log.i(TAG, "OpenCV loaded successfully");
                }
                break;
                default: {
                    super.onManagerConnected(status);
                }
                break;
            }
        }
    };

    @Override
    public void  onResume(){
        super.onResume();
        if (!OpenCVLoader.initDebug()) {
            Log.d(TAG, "Internal OpenCV library not found. Using OpenCV Manager for initialization");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION, this, mLoaderCallback);
        } else {
            Log.d(TAG, "OpenCV library found inside package. Using it!");
            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.img);
        //grayImgView = findViewById(R.id.grayimg);
        button = findViewById(R.id.Gray);

         Bitmap bitmap = ((BitmapDrawable)getResources().getDrawable(R.mipmap.img)).getBitmap();
         Log.d(TAG,String.format("width: %s",bitmap.getWidth()));
         Log.d(TAG,String.format("height: %s",bitmap.getHeight()));

        imageView.setImageBitmap(bitmap);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Bitmap grayBitmap = toGrayByOpenCv(bitmap);
                 imageView.setImageBitmap(grayBitmap);
            }
        });


    }

    public Bitmap toGrayByOpenCv(Bitmap bitmap) {
        Bitmap graybm = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(),Bitmap.Config.ARGB_8888);
        try {

            Mat mat = new Mat();
            Utils.bitmapToMat(bitmap, mat);
            Mat grayMat = new Mat();
            //Imgproc.medianBlur(mat, grayMat, 9);//中值滤波
            Imgproc.cvtColor(mat, grayMat, Imgproc.COLOR_BGR2GRAY);//灰度化
            //Imgproc.Canny(mat, grayMat, 50, 100);//边缘检测
            //Imgproc.cvtColor(mat, grayMat, Imgproc.COLOR_BGRA2GRAY);

            Utils.matToBitmap(grayMat, graybm);
            Log.d(TAG,String.format("graybm width: %s",graybm.getWidth()));
            Log.d(TAG,String.format("graybm height: %s",graybm.getHeight()));

        }catch (Exception e){
            Log.d(TAG,"Error");
        }
        return graybm;

//        Mat mat = new Mat();
//        Utils.bitmapToMat(bitmap,mat);
//        Mat grayMat = new Mat();
//        Imgproc.cvtColor(mat,grayMat,Imgproc.color_g);
//        Utils.matToBitmap(grayMat,bitmap);
//
//        grayImgView.setImageBitmap(bitmap);
//        return bitmap;
    }




}