package com.game.nsh;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private TextView scoreLAbel;
    private TextView startLabel;
    private ImageView box,orange,pink,black;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreLAbel = (TextView) findViewById(R.id.scoreLabel);
        startLabel= (TextView) findViewById(R.id.startLabel);
        box= (ImageView) findViewById(R.id.box);
        orange=(ImageView) findViewById(R.id.orange);
        pink=(ImageView) findViewById(R.id.pink);
        black=(ImageView) findViewById(R.id.black);

        orange.setX(-80);
        orange.setY(-80);
        pink.setX(-80);
        pink.setY(-80);
        black.setX(-80);
        black.setY(-80);

        startLabel.setVisibility(View.INVISIBLE);
    }
}
