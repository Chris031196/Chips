package com.milieur.chips.activity.graphics;

import android.content.Context;
import android.opengl.GLSurfaceView;

import com.milieur.chips.activity.LevelActivity;

/**
 * Created by chris on 08.07.2017.
 */

public class Graphics extends GLSurfaceView {

    private LevelActivity activity;

    public Graphics(Context context, LevelActivity activity) {
        super(context);
        setRenderer(new GraphicsRenderer(this));
    }

    public void initFailed(){
        activity.finish();
    }

    public LevelActivity getActivity() {
        return activity;
    }

}
