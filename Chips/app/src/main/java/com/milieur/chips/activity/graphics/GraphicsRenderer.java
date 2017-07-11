package com.milieur.chips.activity.graphics;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.SystemClock;

import com.milieur.chips.R;
import com.milieur.chips.activity.Controller;
import com.milieur.chips.engine.DrawableObject3D;
import com.milieur.chips.engine.util.jglm.Mat4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by chris on 08.07.2017.
 */

public class GraphicsRenderer implements GLSurfaceView.Renderer {

    private Graphics graphics;

    //Shader uniforms
    private int shader_program;
    private int shader_mvp;
    private int shader_m;
    private int shader_v;
    private int shader_p;
    private int shader_l;

    //Matrices
    private Mat4 matrix_p;

    private static ArrayList<DrawableObject3D> objects = new ArrayList<>();


    public GraphicsRenderer(Graphics graphics) {
        super();
        this.graphics = graphics;
    }

    public static void register(DrawableObject3D object) {
        objects.add(object);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        GLES20.glLineWidth(20.0f);
        GLES20.glEnable(GL10.GL_DEPTH_TEST);
        GLES20.glDepthFunc(GL10.GL_LESS);
        GLES20.glEnable(GL10.GL_CULL_FACE);

        if(!initShaders()){
            graphics.initFailed();
        }

        shader_mvp = GLES20.glGetUniformLocation(shader_program, "mvp");
        shader_m = GLES20.glGetUniformLocation(shader_program, "m");
        shader_v = GLES20.glGetUniformLocation(shader_program, "v");
        shader_p = GLES20.glGetUniformLocation(shader_program, "p");
        shader_l = GLES20.glGetUniformLocation(shader_program, "light_position");
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);

        // make adjustments for screen ratio
        float ratio = (float) width / height;
        float[] projection = new float[16];
        Matrix.frustumM(projection, 0, -ratio, ratio, -1f, 1f, 1f, 30f);
        matrix_p = new Mat4(projection);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        GLES20.glUseProgram(shader_program);

        float[] vMat = new float[16];
        Matrix.setLookAtM(vMat, 0, 0f, 3f, 4f, 0f, 0f, 0f, 0f, 1f, 0f);
        Mat4 matrix_v = new Mat4(vMat);

        for(DrawableObject3D object: objects) {
            object.update(1.0f);
            Mat4 matrix_m = object.getModelMatrix();
            Mat4 matrix_mvp = matrix_p.multiply(matrix_v).multiply(matrix_m);

            GLES20.glUniformMatrix4fv(shader_m, 1, false, matrix_m.getBuffer());
            GLES20.glUniformMatrix4fv(shader_v, 1, false, matrix_v.getBuffer());
            GLES20.glUniformMatrix4fv(shader_p, 1, false, matrix_p.getBuffer());
            GLES20.glUniformMatrix4fv(shader_mvp, 1, false, matrix_mvp.getBuffer());
            GLES20.glUniform3fv(shader_l, 1, new float[] {30f, 10f, 10f}, 0);


            //drawing
            GLES20.glEnableVertexAttribArray(0);
            GLES20.glVertexAttribPointer(0, 3, GLES20.GL_FLOAT, false, 0, object.getvBuffer());

            GLES20.glEnableVertexAttribArray(1);
            GLES20.glVertexAttribPointer(1, 3, GLES20.GL_FLOAT, false, 0, object.getnBuffer());

            GLES20.glDrawElements(GLES20.GL_TRIANGLES, object.getNumVertex() * 3, GLES20.GL_UNSIGNED_INT , object.getiBuffer());

            GLES20.glDisableVertexAttribArray(0);
            GLES20.glDisableVertexAttribArray(1);
        }
    }

    public boolean initShaders() {

        System.err.println("OpenGL version is " + GLES20.glGetString(GLES20.GL_VERSION));

        String vertexShaderCode = "";
        String fragmentxShaderCode = "";
        try {
            //read vertex shader code
            BufferedReader reader = new BufferedReader(new InputStreamReader(Controller.getResource(R.raw.vertex_shader)));
            String line = "";
            while((line = reader.readLine()) != null) {
                vertexShaderCode += line +"\n";
            }

            //read fragment shader code
            reader = new BufferedReader(new InputStreamReader(Controller.getResource(R.raw.fragment_shader)));
            line = "";
            while((line = reader.readLine()) != null) {
                fragmentxShaderCode += line + "\n";
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
            return false;
        }


        if(vertexShaderCode.length() > 1 && fragmentxShaderCode.length() > 1) {

            //create vertex shader
            int vShaderLoc = GLES20.glCreateShader(GLES20.GL_VERTEX_SHADER);
            GLES20.glShaderSource(vShaderLoc, vertexShaderCode);
            GLES20.glCompileShader(vShaderLoc);
            int[] result = { 0 };
            GLES20.glGetShaderiv(vShaderLoc, GLES20.GL_COMPILE_STATUS, result, 0);
            if(result[0] != GLES20.GL_TRUE) {
                System.err.println(GLES20.glGetShaderInfoLog(vShaderLoc));
                GLES20.glDeleteShader(vShaderLoc);
                return false;
            }

            //create fragment shader
            int fShaderLoc = GLES20.glCreateShader(GLES20.GL_FRAGMENT_SHADER);
            GLES20.glShaderSource(fShaderLoc, fragmentxShaderCode);
            GLES20.glCompileShader(fShaderLoc);
            int[] result2 = { 0 };
            GLES20.glGetShaderiv(fShaderLoc, GLES20.GL_COMPILE_STATUS, result2, 0);
            if(result2[0] != GLES20.GL_TRUE) {
                System.err.println(GLES20.glGetShaderInfoLog(fShaderLoc));
                GLES20.glDeleteShader(vShaderLoc);
                GLES20.glDeleteShader(fShaderLoc);
                return false;
            }

            //create program
            shader_program = GLES20.glCreateProgram();
            GLES20.glAttachShader(shader_program, vShaderLoc);
            GLES20.glAttachShader(shader_program, fShaderLoc);
            GLES20.glLinkProgram(shader_program);
            int[] result3 = { 0 };
            GLES20.glGetProgramiv(shader_program, GLES20.GL_LINK_STATUS, result3, 0);
            if(result3[0] != GLES20.GL_TRUE) {
                System.err.println(GLES20.glGetProgramInfoLog(shader_program));
                GLES20.glDeleteShader(vShaderLoc);
                GLES20.glDeleteShader(fShaderLoc);
                GLES20.glDeleteProgram(shader_program);
                return false;
            }
        }
        return true;
    }
}
