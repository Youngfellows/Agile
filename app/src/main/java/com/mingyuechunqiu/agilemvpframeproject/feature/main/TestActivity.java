package com.mingyuechunqiu.agilemvpframeproject.feature.main;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnInputConfirmListener;
import com.mingyuechunqiu.agilemvpframeproject.R;

/**
 * <pre>
 *     author : xyj
 *     Github : https://github.com/MingYueChunQiu
 *     e-mail : xiyujieit@163.com
 *     time   : 2019/4/22
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        TestBottomDialogFragment fragment = new TestBottomDialogFragment();
//        fragment.show(getSupportFragmentManager(), "23");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                TestDialogFragment testDialogFragment = new TestDialogFragment();
//                testDialogFragment.show(getSupportFragmentManager(), "fewf");
                new XPopup.Builder(TestActivity.this).asInputConfirm("我是标题", "请输入内容。",
                        new OnInputConfirmListener() {
                            @Override
                            public void onConfirm(String text) {
                                Toast.makeText(TestActivity.this, "input text: " + text, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        }, 1000);
    }
}
