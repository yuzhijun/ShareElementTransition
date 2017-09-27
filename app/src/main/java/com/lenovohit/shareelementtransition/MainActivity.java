package com.lenovohit.shareelementtransition;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.util.Pair;
import android.view.View;
import android.view.Window;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvStories;
    private MyAdapter mAdapter;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvStories = (RecyclerView) findViewById(R.id.rvStories);
        rvStories.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rvStories.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.VERTICAL));
        mAdapter = new MyAdapter(getDatas());
        rvStories.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(MainActivity.this,DetailActivity.class);
//               startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,view.findViewById(R.id.ivChallenger),"sharedChallenger").toBundle());
              startActivity(intent,
                        ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,
                                Pair.create(view.findViewById(R.id.ivChallenger),"sharedChallenger"),
                                Pair.create(view.findViewById(R.id.tvTitle),"sharedTitle"),
                                Pair.create(view.findViewById(R.id.tvContent),"sharedContent")).toBundle());
            }
        });

        getWindow().setReenterTransition(new Explode());
    }

    public ArrayList<Story> getDatas() {
        ArrayList<Story> data = new ArrayList<>();
        for(int i = 0; i < 20; i++) {
            Story story = new Story();
            story.setTitle("探险者的故事");
            story.setContent("我们都有不断突破自己的决心和信心，未来就在自己脚下，用自己的步伐去丈量。");
            data.add(story);
        }
        return data;
    }
}
