package com.milieur.chips.engine;

import android.opengl.Matrix;

import com.milieur.chips.R;
import com.milieur.chips.activity.Controller;
import com.milieur.chips.activity.graphics.Graphics;
import com.milieur.chips.activity.graphics.GraphicsRenderer;
import com.milieur.chips.engine.util.jglm.Mat4;
import com.milieur.chips.engine.util.jglm.Vec3;

import java.io.IOException;

public class Chip extends DrawableObject3D{

	private Level level;
	
	private Vec3 position;
	private Vec3 rotation;

	private Vec3 posVel;
	private Vec3 rotVel;

	private Vec3 gravity;

	private boolean flicked = false;
	
	public Chip(Level level) throws IOException {
		this.level = level;
		this.position = new Vec3();
		this.rotation = new Vec3();
		this.posVel = new Vec3();
		this.rotVel = new Vec3();
		this.gravity = new Vec3(0.0f, 1.0f, 0.0f);
		loadModel(Controller.getResource(R.raw.chip));
		GraphicsRenderer.register(this);
	}

	@Override
	public void update(float deltaTime) {
		position = position.add(posVel.multiply(deltaTime));
		posVel = posVel.add(gravity.multiply(deltaTime));
		posVel = posVel.multiply(0.99f);

		rotation = rotation.add(rotVel.multiply(deltaTime));
		rotVel = rotVel.multiply(0.99f);
	}
	
	public void flick(float xDir, float yDir) {
		if(flicked){
			return;
		}
		Vec3 vec = new Vec3(xDir, yDir, 0f);
		posVel = new Vec3(vec.getX() * 0.4f, -vec.getY() * 0.4f, vec.getY() * 0.4f);

		float[] rot = new float[16];
		Matrix.setRotateM(rot, 0, vec.getLength() * 1.0f, vec.getX() * 1.0f, vec.getY() * 1.0f, 0f);
	}

	@Override
	public Mat4 getModelMatrix() {
		float[] rotA = new float[16];
		Matrix.setRotateM(rotA, 0, rotation.getX(), rotation.getY(), rotation.getZ(), 1.0f);

		float[] transA = new float[]{
					1f, 0f, 0f, 0f,
					0f, 1f, 0f, 0f,
					0f, 0f, 1f, 0f,
					0f, 0f, 0f, 1f
		};
		Matrix.translateM(transA, 0, position.getX(), position.getY(), position.getZ());

		Mat4 rotM = new Mat4(rotA);
		Mat4 transM = new Mat4(transA);

		return transM.multiply(rotM);
	}
}
