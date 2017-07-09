package com.milieur.chips.engine;


import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

public class Object3D {

	private FloatBuffer vBuffer;
	private FloatBuffer nBuffer;
	private FloatBuffer cBuffer;
	

	public Object3D(String filename) {
		loadModel(filename);
	}

	public Object3D() {

	}

	private boolean loadModel(String filename) {
		return true;
	}
}
