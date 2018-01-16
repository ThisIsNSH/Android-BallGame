package com.game.nsh;

import android.graphics.Point;
import android.media.Image;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.FileReader;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private TextView scoreLAbel;
    private TextView startLabel;
    private ImageView box,orange,pink,black;

    private int boxY;
    private int orangeX;
    private int orangeY;
    private int pinkX;
    private int pinkY;
    private int blackX;
    private int blackY;


    private  int score=0;


    private Handler handler = new Handler();
    private Timer timer= new Timer();

    private int screenWidth;
    private int screenHeight;

    private int frameheight;
    private int boxSize;

    private boolean action_flg=false;
    private boolean start_flg=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreLAbel = (TextView) findViewById(R.id.scoreLabel);
        startLabel = (TextView) findViewById(R.id.startLabel);
        box = (ImageView) findViewById(R.id.box);
        orange = (ImageView) findViewById(R.id.orange);
        pink = (ImageView) findViewById(R.id.pink);
        black = (ImageView) findViewById(R.id.black);

        orange.setX(-80);
        orange.setY(-80);
        pink.setX(-80);
        pink.setY(-80);
        black.setX(-80);
        black.setY(-80);

        scoreLAbel.setText("Score : 0");


        WindowManager wm = getWindowManager();
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);
        screenWidth=size.x;
        screenHeight= size.y;

    }


    public void changePos(){
        hitCheck();
orangeX-=12;
if (orangeX<0){
    orangeX = screenWidth + 20;
    orangeY = (int) Math.floor(Math.random() * (frameheight - orange.getHeight()));
}
orange.setX(orangeX);
orange.setY(orangeY);

blackX -=16;
if(blackX < 0 ){
    blackX = screenWidth +10;
    blackY = (int ) Math.floor(Math.random()* frameheight - black.getHeight());
}
black.setY(blackY);
black.setX(blackX);

pinkX -=20;
if (pinkX<0)
{
    pinkX = screenWidth +5000;
    pinkY = (int ) Math.floor(Math.random()* frameheight - pink.getHeight());
}

pink.setX(pinkX);
pink.setY(pinkY);

        if(action_flg==true)
        {
            boxY-=20;
        }
else{
            boxY+=20;
        }

        if(boxY<0) boxY=0;
        if (boxY > frameheight - boxSize) boxY = frameheight - boxSize;
        box.setY(boxY);

scoreLAbel.setText("Score : "+score);

    }
    public  void hitCheck()
    {
        int orangeCenterX= orangeX + orange.getWidth() / 2;
        int orangeCenterY = orangeY + orange.getHeight() / 2;

        if (0<= orangeCenterX  && orangeCenterX <= boxSize && boxY <=orangeCenterY && orangeCenterY <= boxY + boxSize)
        {
            score+=10;
            orangeX = -10;

        }
        int pinkCenterX = pinkX + pink.getWidth() / 2;
        int pinkCenterY = pinkY + pink.getHeight() / 2;
        if (0<= pinkCenterX  && pinkCenterX <= boxSize && boxY <=pinkCenterY && pinkCenterY <= boxY + boxSize)
        {
            score+=30;
            pinkX = -10;

        }

    }

    public boolean onTouchEvent(MotionEvent me) {
       if (start_flg==false)
       {
           start_flg=true;
           FrameLayout frame = (FrameLayout) findViewById(R.id.frame);
           frameheight= frame.getHeight();

                   boxY = (int) box.getY();
           boxSize=box.getHeight();

           startLabel.setVisibility(View.GONE);

           timer.schedule(new TimerTask() {
               @Override
               public void run() {
                   handler.post(new Runnable() {
                       @Override
                       public void run() {

                           changePos();}
                   });
               }
           },0,20);
       }


        else
       {
           if(me.getAction()== MotionEvent.ACTION_DOWN)
           {
               action_flg=true;
           }
           else if (me.getAction()==MotionEvent.ACTION_UP)
           {
               action_flg=false;
           }
       }
        return true;
    }
}
