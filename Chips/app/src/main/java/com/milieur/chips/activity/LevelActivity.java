package com.milieur.chips.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;

import com.milieur.chips.activity.graphics.Graphics;


public class LevelActivity extends Activity {


    private Controller controller;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        controller = new Controller(getApplicationContext(), this);
        this.setContentView(controller.getGraphics());
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        controller.onTouch(e);
        return true;
    }


}
