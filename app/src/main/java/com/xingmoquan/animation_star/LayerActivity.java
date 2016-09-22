package com.xingmoquan.animation_star;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class LayerActivity extends AppCompatActivity {

    private static final String TAG = "liuzw";

    private ImageView picBGView;
    private ImageView pictureView;
    private ImageView maskView;
    private ImageView frameView;
    private ImageView resultView;
    private Button startProcess;
    private Bitmap picBitmap;
    private Bitmap maskBitmap;
    private Bitmap frameBitmap;
    private Bitmap resultBitmap;
    private Bitmap fengjingBitmap;
    private Bitmap composedBitmap;

    private final int WITHOUT = -1;
    private static final int FRAME = 0;
    private static final int MASK = 1;

//  private int[] resIds = new int[]{       //斜框锯齿
//          R.drawable.pip_6_frame,
//          R.drawable.pip_6_frame_mask,
//  };

//  private int[] resIds = new int[]{       //胶条
//          R.drawable.pip_1_frame,
//          R.drawable.pip_1_frame_mask,
//  };

    private int[] resIds = new int[]{       //渐变
            WITHOUT,
//            R.drawable.pip_2_frame_mask,
            R.drawable.bg_video
    };

//  private int[] resIds = new int[]{       //心形
//          R.drawable.pip_3_frame,
//          R.drawable.pip_3_frame_mask,
//  };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layer);

        picBGView = (ImageView) findViewById(R.id.pic_bg);
//        picBGView.setImageResource(R.drawable.fengjing);
        picBGView.setImageResource(R.mipmap.big_mua);
        pictureView = (ImageView) findViewById(R.id.pic);
//        pictureView.setImageResource(R.drawable.pip_test);
        pictureView.setImageResource(R.mipmap.heart_blue);
        maskView = (ImageView) findViewById(R.id.mask);
        maskView.setImageResource(resIds[MASK]);
        frameView = (ImageView) findViewById(R.id.frame);
//        frameView.setImageResource(resIds[FRAME]);
        startProcess = (Button) findViewById(R.id.btnStart);
        startProcess.setOnClickListener(mListener);
        resultView = (ImageView) findViewById(R.id.showResult);

    }

    /**
     * 获得前置照片
     */
    private void getFrontPicture(){
        //蒙板的Bitmap
        if(maskBitmap == null || maskBitmap.isRecycled() && resIds[MASK] != WITHOUT){
            maskBitmap = BitmapFactory.decodeResource(this.getResources(), resIds[MASK]);
        }
        if(maskBitmap == null) return;

        //前置的原图，并将其缩放到跟蒙板大小一直
        if(picBitmap == null || picBitmap.isRecycled()){
            picBitmap = BitmapFactory.decodeResource(this.getResources(), R.mipmap.heart_blue);
            picBitmap = Bitmap.createScaledBitmap(picBitmap, maskBitmap.getWidth(), maskBitmap.getHeight(), false);
        }

        //相框的Bitmap
        if(frameBitmap == null || frameBitmap.isRecycled() && resIds[FRAME] != WITHOUT){
            frameBitmap = BitmapFactory.decodeResource(this.getResources(), resIds[FRAME]);
        }

        int w = maskBitmap.getWidth();
        int h = maskBitmap.getHeight();

        int edgeColor = maskBitmap.getPixel(1, 1);
        int centerColor = maskBitmap.getPixel(w/2, h/2);
        Log.d(TAG, "edgeColor = " + Integer.toHexString(edgeColor) + ", centerColor = " + Integer.toHexString(centerColor));

        if(resultBitmap == null){
            resultBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        }

        //这是背景的风景图
        if(fengjingBitmap == null){
            int res= R.drawable.big_mua;
            fengjingBitmap = BitmapFactory.decodeResource(getResources(),res);
            if(fengjingBitmap==null){
                Log.e(TAG,"fengjingBitmap1 is null");
            }else{
                Log.e(TAG,"fengjingBitmap1 is  not null");
            }
        }else{
            Log.e(TAG,"fengjingBitmap is  not null");
        }

        //前置相片添加蒙板效果
        int[] picPixels = new int[w*h];
        int[] maskPixels = new int[w*h];
        picBitmap.getPixels(picPixels, 0, w, 0, 0, w, h);
        maskBitmap.getPixels(maskPixels, 0, w, 0, 0, w, h);
        for(int i = 0; i < maskPixels.length; i++){
            if(maskPixels[i] == 0xff000000){
                picPixels[i] = 0;
            }else if(maskPixels[i] == 0){
                //donothing
            }else{
                //把mask的a通道应用与picBitmap
                maskPixels[i] &= 0xff000000;
                maskPixels[i] = 0xff000000 - maskPixels[i];
                picPixels[i] &= 0x00ffffff;
                picPixels[i] |= maskPixels[i];
            }
        }

        //生成前置图片添加蒙板后的bitmap:resultBitmap
        resultBitmap.setPixels(picPixels, 0, w, 0, 0, w, h);
    }

    /**
     * 图片合成
     */
    private void compose(){
//        if(fengjingBitmap == null || fengjingBitmap.isRecycled()){
        if(fengjingBitmap == null){
            Log.e(TAG, "compose ERROR: fengjingBitmap is not valuable");
            return;
        }
        composedBitmap = Bitmap.createBitmap(fengjingBitmap.getWidth(), fengjingBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        if(composedBitmap == null || composedBitmap.isRecycled()){
            Log.e(TAG, "compose ERROR: composedBitmap is not valuable");
            return;
        }
        if(resultBitmap == null || resultBitmap.isRecycled()){
            Log.e(TAG, "compose ERROR: resultBitmap is not valuable");
            return;
        }
        Canvas cv = new Canvas(composedBitmap);
        cv.drawBitmap(fengjingBitmap, 0, 0, null);
        cv.drawBitmap(resultBitmap, 100, 100, null);

        if(frameBitmap != null && !frameBitmap.isRecycled()){
            cv.drawBitmap(frameBitmap, 100, 100, null);
        }

        cv.save(Canvas.ALL_SAVE_FLAG);
        cv.restore();
        resultView.setImageBitmap(composedBitmap);
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        //释放资源
        resultView.setImageBitmap(null);
        if(picBitmap != null && !picBitmap.isRecycled()){
            picBitmap.recycle();
            picBitmap = null;
        }
        if(maskBitmap != null && !maskBitmap.isRecycled()){
            maskBitmap.recycle();
            maskBitmap = null;
        }
        if(frameBitmap != null && !frameBitmap.isRecycled()){
            frameBitmap.recycle();
            frameBitmap = null;
        }
        if(resultBitmap != null && !resultBitmap.isRecycled()){
            resultBitmap.recycle();
            resultBitmap = null;
        }
        if(fengjingBitmap != null && !fengjingBitmap.isRecycled()){
            fengjingBitmap.recycle();
            fengjingBitmap = null;
        }
        if(composedBitmap != null && !composedBitmap.isRecycled()){
            composedBitmap.recycle();
            composedBitmap = null;
        }
    }

    private View.OnClickListener mListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            switch(v.getId()){
                case R.id.btnStart:
                    getFrontPicture();
                    compose();
                    break;
            }
        }

    };
}
