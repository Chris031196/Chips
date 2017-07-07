package com.milieur.chips.engine;

public class Chip {
	
	private Object3D object3D;
	
	private float[] position;
	private float[] velocity;
	private float[] rotation;
	
	public Chip(float[] position, Object3D object3D) {
		this.position = position;
		this.object3D = object3D;
	}
	
	public void update(float deltaTime) {
		
	}
	
	public void flick(float[] dragVector) {
		
	}
}
