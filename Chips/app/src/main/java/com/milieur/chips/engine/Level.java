package com.milieur.chips.engine;

import com.milieur.chips.R;
import com.milieur.chips.activity.Controller;
import com.milieur.chips.activity.graphics.GraphicsRenderer;
import com.milieur.chips.engine.collision.CollisionScene;
import com.milieur.chips.engine.util.jglm.Mat4;
import com.milieur.chips.engine.util.jglm.Vec3;

import java.io.IOException;

/**
 * Created by chris on 08.07.2017.
 */

public class Level extends DrawableObject3D{

    private CollisionScene collision;
    private CollisionScene win;


    public Level() throws IOException {
        loadModel(Controller.getResource(R.raw.lvl1vis));
        GraphicsRenderer.register(this);

        collision = new CollisionScene(Controller.getResource(R.raw.lvl1col));
        win = new CollisionScene(Controller.getResource(R.raw.lvl1win));
    }


    public CollisionScene getCollision() {
        return collision;
    }

    public CollisionScene getWin() {
        return win;
    }

    @Override
    public Mat4 getModelMatrix() {
        return Mat4.MAT4_IDENTITY;
    }

    @Override
    public void update(float deltaTime) {

    }
}
