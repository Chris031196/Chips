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

    private Graphics graphics;
    private static LevelActivity activity;
    private Level lvl;
    private Chip chip;

    private float lastX;
    private float lastY;

    private static final RectF chipArea = new RectF(0.25f, 0.33f, 0.75f, 0.66f);
    private Point screenSize;


    public Controller(Context context, LevelActivity activity){
        this.graphics = new Graphics(context);
        this.activity = activity;

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

        if(chipArea.contains(lastX, lastY) && !chipArea.contains(relX, relY)) {
            chip.flick(lastX - relX, lastY - relY);
        }
        lastX = relX;
        lastY = relY;
    }
}
