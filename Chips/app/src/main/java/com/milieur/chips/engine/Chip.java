package com.milieur.chips.engine;

import android.opengl.Matrix;

import com.milieur.chips.R;
import com.milieur.chips.activity.Controller;
import com.milieur.chips.activity.graphics.Graphics;
import com.milieur.chips.activity.graphics.GraphicsRenderer;
import com.milieur.chips.engine.util.jglm.Mat4;
import com.milieur.chips.engine.util.jglm.Vec3;
import com.milieur.chips.engine.util.jglm.Vec4;

import java.io.IOException;

public class Chip extends DrawableObject3D{

	private Level level;
	
	private Vec3 position;
	private Mat4 rotation;
	private float tensionAngle;
	private Vec3 tensionAxis;

	private Vec3 posVel;
	private Vec3 rotAxis;
	private float rotAngle;

	private Vec3 gravity;

	private boolean flicked;
	
	public Chip(Level level) throws IOException {
		this.level = level;
		reset();
		this.gravity = new Vec3(0.0f, -0.005f, 0.0f);
		loadModel(Controller.getResource(R.raw.chip));
		GraphicsRenderer.register(this);
	}

	private void reset() {
		flicked = false;
		this.position = new Vec3();
		this.rotation = Mat4.MAT4_IDENTITY;
		this.posVel = new Vec3();
		this.rotAngle = 0.0f;
		this.rotAxis = new Vec3();
	}

	@Override
	public void update(float deltaTime) {
		Controller.setLookAt(position);
		position = position.add(posVel.multiply(deltaTime));
		if(flicked)
			posVel = posVel.add(gravity.multiply(deltaTime));
		posVel = posVel.multiply(0.99f);

		if(rotAxis.getLength() != 0f) {
			float[] rotVel = new float[16];
			Matrix.setRotateM(rotVel, 0, rotAngle, rotAxis.getX(), rotAxis.getY(), rotAxis.getZ());
			rotation = rotation.multiply(new Mat4(rotVel));
		}

		if(position.getLength() >= 30f) {
			reset();
		}
	}
	
	public void flick() {
		if(flicked){
			return;
		}
		flicked = true;
		rotAxis = tensionAxis;
		rotAngle = -tensionAngle;

		posVel = new Vec3(rotAxis.getZ(), 0.6f * Math.abs(rotAxis.getX()), -rotAxis.getX());
	}

	@Override
	public Mat4 getModelMatrix() {

		float[] transA = new float[]{
					1f, 0f, 0f, 0f,
					0f, 1f, 0f, 0f,
					0f, 0f, 1f, 0f,
					0f, 0f, 0f, 1f
		};
		Matrix.translateM(transA, 0, position.getX(), position.getY(), position.getZ());

		Mat4 transM = new Mat4(transA);

		return transM.multiply(rotation);
	}

	public void setTension(Vec3 tension) {
		if(flicked){
			return;
		}
		float[] rotVel = new float[16];
		Matrix.setRotateM(rotVel, 0, tension.getLength() * 5f, -tension.getX(), tension.getY(), tension.getZ());
		tensionAngle = tension.getLength() * 25f;
		tensionAxis = new Vec3(-tension.getX(), tension.getY(), tension.getZ());
		rotation = new Mat4(rotVel);
	}
}
