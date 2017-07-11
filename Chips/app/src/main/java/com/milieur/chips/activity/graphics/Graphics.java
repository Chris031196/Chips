package com.milieur.chips.activity.graphics;

import android.content.Context;
import android.opengl.GLSurfaceView;

import com.milieur.chips.activity.Controller;
import com.milieur.chips.activity.LevelActivity;

/**
 * Created by chris on 08.07.2017.
 */

public class Graphics extends GLSurfaceView {


    public Graphics(Context context) {
        super(context);
        setEGLContextClientVersion(2);
        setRenderer(new GraphicsRenderer(this));
    }

    public void initFailed(){
        Controller.error("Init Failed!");
    }

}
