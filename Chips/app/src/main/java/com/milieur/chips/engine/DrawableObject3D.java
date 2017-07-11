package com.milieur.chips.engine;


import com.milieur.chips.engine.util.jglm.Mat4;
import com.milieur.chips.engine.util.obj.Obj;
import com.milieur.chips.engine.util.obj.ObjData;
import com.milieur.chips.engine.util.obj.ObjReader;
import com.milieur.chips.engine.util.obj.ObjUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public abstract class DrawableObject3D {

	private int numVertex;
	private FloatBuffer vBuffer;
	private FloatBuffer nBuffer;
	private IntBuffer iBuffer;

	protected boolean loadModel(InputStream stream) throws IOException {
		Obj obj = ObjUtils.convertToRenderable(ObjReader.read(stream));

		numVertex = obj.getNumFaces();
		iBuffer = ObjData.getFaceVertexIndices(obj);
		vBuffer = ObjData.getVertices(obj);
		nBuffer = ObjData.getNormals(obj);
		return true;
	}

	public int getNumVertex() {
		return numVertex;
	}

	public FloatBuffer getvBuffer() {
		return vBuffer;
	}

	public FloatBuffer getnBuffer() {
		return nBuffer;
	}

	public IntBuffer getiBuffer() {
		return iBuffer;
	}

	public abstract Mat4 getModelMatrix();

	public abstract void update(float deltaTime);
}
