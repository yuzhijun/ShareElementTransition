package com.lenovohit.shareelementtransition;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.kogitune.activity_transition.ActivityTransition;

/**
 * Created by yuzhijun on 2017/9/27.
 */
public class DetailActivity extends AppCompatActivity {
    private Button btnNext;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        btnNext = (Button) findViewById(R.id.btnNext);
        btnNext.setVisibility(View.VISIBLE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//        getWindow().setEnterTransition(new Slide());//从场景的边缘移入或移出
            getWindow().setEnterTransition(new Fade());//调整透明度产生渐变效果
//        getWindow().setEnterTransition(new Explode());//从场景的中心移入或移出

            ChangeBounds changeBounds = new ChangeBounds();//改变目标视图的布局边界
//        ChangeTransform changeTransform = new ChangeTransform();//改变目标视图的缩放比例和旋转角度
//        ChangeImageTransform changeImageTransform = new ChangeImageTransform();//改变目标图片的大小和缩放比例
//        ChangeClipBounds changeClipBounds = new ChangeClipBounds();//裁剪目标视图边界
//        changeBounds.setDuration(1000);
            getWindow().setSharedElementEnterTransition(changeBounds);
        }else{
            ActivityTransition.with(getIntent()).to(findViewById(R.id.ivChallenger))
                    .duration(300).start(savedInstanceState);
        }

        btnNext.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity.this,TransitionManagerActivity.class);
                final Pair<View, String>[] pairs = TransitionHelper.createSafeTransitionParticipants(DetailActivity.this, true);
                ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(DetailActivity.this,
                        pairs);
                startActivity(intent, transitionActivityOptions.toBundle());
            }
        });
    }
}
