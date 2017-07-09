package com.milieur.chips.engine;

import com.milieur.chips.engine.util.jglm.Vec3;

/**
 * Created by chris on 08.07.2017.
 */

public class Level {

    private Object3D object3D;


    public Level(Object3D object3D) {
        this.object3D = object3D;
    }

    /**
     * Checks if the vertex collides with the level scenery.
     * @param vertex_position
     * @return the normal of the surface that the vertex collides with. null if no collision.
     */
    public Vec3 collides(Vec3 vertex_position) {
        //TODO implement
        return null;
    }

    public boolean win(Vec3 chip_position) {
        return false;
    }

}
