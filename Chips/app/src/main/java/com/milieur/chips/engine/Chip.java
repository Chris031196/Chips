package com.milieur.chips.engine;

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
		
		return null;
	}
}
