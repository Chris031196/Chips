package com.milieur.chips.engine;

import android.opengl.Matrix;

import com.milieur.chips.engine.util.jglm.Mat4;
import com.milieur.chips.engine.util.jglm.Vec3;

public class Chip {
	
	private Object3D object3D;
	
	private Vec3 position;
	private Vec3 velocity;
	private Vec3 rotation;
	
	public Chip(Vec3 position, Object3D object3D) {
		this.position = position;
		this.object3D = object3D;
	}
	
	public void update(float deltaTime) {
		
	}
	
	public void flick(float[] dragVector) {
		
	}
	
	public Mat4 getModelMatrix() {
		float[] rotA = new float[16];
		Matrix.setRotateM(rotA, 0, rotation.getX(), rotation.getY(), rotation.getZ(), 1.0f);

		float[] transA = new float[16];
		Matrix.translateM(transA, 0, position.getX(), position.getY(), position.getZ());

		Mat4 rotM = new Mat4(rotA);
		Mat4 transM = new Mat4(transA);

		return transM.multiply(rotM);
	}
}
