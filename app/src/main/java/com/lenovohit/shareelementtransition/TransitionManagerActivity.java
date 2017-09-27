package com.lenovohit.shareelementtransition;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.transition.ChangeBounds;
import android.transition.Explode;
import android.transition.Scene;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

/**
 * Created by yuzhijun on 2017/9/27.
 */

public class TransitionManagerActivity extends AppCompatActivity {

    private FrameLayout flScene;
    private Button btnSwitchScene;
    private Button btnSwitchScene1;
    private Button btnSwitchScene2;

    private Scene scene0;
    private Scene scene1;
    private Scene scene2;
    private Scene scene3;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transition_manager_activity);

        flScene = (FrameLayout) findViewById(R.id.flScene);
        btnSwitchScene = (Button) findViewById(R.id.btnSwitchScene);
        btnSwitchScene1 = (Button) findViewById(R.id.btnSwitchScene1);
        btnSwitchScene2 = (Button) findViewById(R.id.btnSwitchScene2);

        setupLayout();
        btnSwitchScene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TransitionManager.go(scene0, new ChangeBounds());
            }
        });

        btnSwitchScene1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                TransitionManager.go(scene1, TransitionInflater.from(TransitionManagerActivity.this)
//                        .inflateTransition(R.transition.slide_and_changebounds));
                TransitionManager.go(scene1,TransitionInflater.from(TransitionManagerActivity.this).
                        inflateTransition(R.transition.slide_and_changebounds_sequential_with_interpolators));
            }
        });

        btnSwitchScene2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TransitionManager.go(scene2,TransitionInflater.from(TransitionManagerActivity.this)
                        .inflateTransition(R.transition.slide_and_changebounds_sequential));
            }
        });

        getWindow().setEnterTransition(new Slide());
        getWindow().getEnterTransition().addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {
                TransitionManager.go(scene3);
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });
        getWindow().setExitTransition(new Explode());
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void setupLayout(){
        scene0 = Scene.getSceneForLayout(flScene,R.layout.detail_activity,this);
        scene1 = Scene.getSceneForLayout(flScene,R.layout.detail_activity_scene,this);
        scene2 = Scene.getSceneForLayout(flScene,R.layout.detail_activity_scene1,this);
        scene3 = Scene.getSceneForLayout(flScene,R.layout.detail_activity_scene2,this);
    }
}
