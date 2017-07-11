package com.milieur.chips.engine.collision;

import com.milieur.chips.engine.util.jglm.Vec3;
import com.milieur.chips.engine.util.obj.FloatTuple;
import com.milieur.chips.engine.util.obj.Obj;

/**
 * Created by Chris on 10.07.2017.
 */

public class CollisionBox {

    private float xSmall = Float.MAX_VALUE;
    private float xLarge = Float.MIN_VALUE;
    private float ySmall = Float.MAX_VALUE;
    private float yLarge = Float.MIN_VALUE;
    private float zSmall = Float.MAX_VALUE;
    private float zLarge = Float.MIN_VALUE;


    public CollisionBox(Obj obj) {

        for(int i=0;i<obj.getNumVertices();i++) {
            FloatTuple v = obj.getVertex(i);
            if(v.getX() < xSmall) {
                xSmall = v.getX();
            }
            if(v.getX() > xLarge) {
                xLarge = v.getX();
            }
            if(v.getY() < ySmall) {
                ySmall = v.getY();
            }
            if(v.getY() < ySmall) {
                ySmall = v.getY();
            }
            if(v.getZ() < zSmall) {
                zSmall = v.getZ();
            }
            if(v.getZ() < zSmall) {
                zSmall = v.getZ();
            }
        }
    }

    public Vec3 collides(Vec3 vertex) {
        Vec3 bounce = new Vec3();


        return new Vec3();
    }

    public float getxSmall() {
        return xSmall;
    }

    public float getxLarge() {
        return xLarge;
    }

    public float getySmall() {
        return ySmall;
    }

    public float getyLarge() {
        return yLarge;
    }

    public float getzSmall() {
        return zSmall;
    }

    public float getzLarge() {
        return zLarge;
    }
}
