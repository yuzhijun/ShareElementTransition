package com.lenovohit.shareelementtransition;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.view.View;
import android.widget.LinearLayout;

import com.kogitune.activity_transition.ActivityTransition;

/**
 * Created by yuzhijun on 2017/9/27.
 */
public class DetailActivity extends AppCompatActivity {
    private LinearLayout llDetail;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        llDetail = (LinearLayout) findViewById(R.id.llDetail);


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

        llDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity.this,TransitionManagerActivity.class);
                startActivity(intent);
            }
        });
    }
}
