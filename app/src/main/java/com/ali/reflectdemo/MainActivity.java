
package com.ali.reflectdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ali.butterknifelibrary.BindView;
import com.ali.butterknifelibrary.Butterknife;
import com.ali.butterknifelibrary.MyDeclare;
import com.ali.butterknifelibrary.OnClick;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {
    @MyDeclare
    @BindView(R.id.tv_name)
    TextView textView;

   @BindView(R.id.btn_two)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Butterknife.bind(this);
        textView.setText("我是通过自定义butterknife赋值得");
    }

    @OnClick(value = {R.id.tv_name, R.id.btn_click, R.id.btn_two})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_name:
                Toast.makeText(MainActivity.this, "文本被电击", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_click:
                Toast.makeText(MainActivity.this, "按钮被电击", Toast.LENGTH_SHORT).show();

                break;
            case R.id.btn_two:
                Toast.makeText(MainActivity.this, "第二按钮被电击", Toast.LENGTH_SHORT).show();

                break;
        }
    }


    @Subscribe(threadMode = ThreadMode.BACKGROUND, priority = 100, sticky = true)
    public void onGetPerson(Person person) throws InterruptedException {

        Thread.sleep(2000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
