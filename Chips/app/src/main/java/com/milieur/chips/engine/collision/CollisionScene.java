package com.milieur.chips.engine.collision;

import com.milieur.chips.engine.util.jglm.Vec3;
import com.milieur.chips.engine.util.obj.Obj;
import com.milieur.chips.engine.util.obj.ObjReader;
import com.milieur.chips.engine.util.obj.ObjUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Chris on 10.07.2017.
 */

public class CollisionScene {

    private ArrayList<CollisionBox> boxes;

    public CollisionScene(InputStream stream) throws IOException {
        Obj obj = ObjUtils.convertToRenderable(ObjReader.read(stream));

        boxes = new ArrayList<>();
        for(int i=0;i<obj.getNumGroups();i++) {
            boxes.add(new CollisionBox(ObjUtils.groupToObj(obj, obj.getGroup(i), null)));
        }
    }

    public Vec3 collides(Vec3 vertex) {

        Vec3 bounce = new Vec3();
        for(CollisionBox box: boxes) {
            bounce.add(box.collides(vertex));
        }

        return bounce;
    }
}
