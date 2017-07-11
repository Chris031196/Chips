package com.milieur.chips.activity;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

import com.milieur.chips.activity.graphics.Graphics;
import com.milieur.chips.engine.Chip;
import com.milieur.chips.engine.Level;
import com.milieur.chips.engine.util.jglm.Vec3;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Chris on 10.07.2017.
 */

public class Controller {

    private static Graphics graphics;
    private static LevelActivity activity;
    private Level lvl;
    private Chip chip;

    private float lastX;
    private float lastY;

    private static final RectF chipArea = new RectF(0.25f, 0.33f, 0.75f, 0.66f);
    private Point screenSize;


    public Controller(Context context, LevelActivity activity){
        Controller.graphics = new Graphics(context);
        Controller.activity = activity;

        screenSize = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(screenSize);

        initLevel();
    }

    public void initLevel() {
        try {
            lvl = new Level();
            chip = new Chip(lvl);
        } catch (IOException e) {
            Controller.error(e.getMessage());
        }
    }

    public View getGraphics() {
        return graphics;
    }

    public static void error(String errorMsg) {
        activity.finish();
    }

    public static InputStream getResource(int id) {
        return activity.getResources().openRawResource(id);
    }

    public void onTouch(MotionEvent e) {
        float relX = e.getX() / screenSize.x;
        float relY = e.getY() / screenSize.y;

        switch(e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = relX;
                lastY = relY;
                break;
            case MotionEvent.ACTION_UP:
                chip.flick();
                break;
            case MotionEvent.ACTION_MOVE:
                chip.setTension(new Vec3(lastY - relY, 0f, lastX - relX));
                break;
        }

//        if(chipArea.contains(lastX, lastY) && !chipArea.contains(relX, relY)) {
//            chip.flick(lastX - relX, lastY - relY);
//        }
    }

    public static void setLookAt(Vec3 lookAt) {
        graphics.getRenderer().setLookAt(lookAt);
    }
}
