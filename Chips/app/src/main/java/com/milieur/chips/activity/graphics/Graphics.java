package com.milieur.chips.activity.graphics;

import android.content.Context;
import android.opengl.GLSurfaceView;

import com.milieur.chips.activity.Controller;
import com.milieur.chips.activity.LevelActivity;

/**
 * Created by chris on 08.07.2017.
 */

public class Graphics extends GLSurfaceView {

    private GraphicsRenderer renderer;

    public Graphics(Context context) {
        super(context);
        setEGLContextClientVersion(2);
        renderer = new GraphicsRenderer(this);
        setRenderer(renderer);
    }

    public void initFailed(){
        Controller.error("Init Failed!");
    }

    public GraphicsRenderer getRenderer() {
        return renderer;
    }
}
