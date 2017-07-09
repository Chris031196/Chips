package com.milieur.chips.activity;

import android.app.Activity;
import android.os.Bundle;

import com.milieur.chips.activity.graphics.Graphics;


public class LevelActivity extends Activity {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(new Graphics(getApplicationContext(), this));
    }


}
