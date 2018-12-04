package com.simple.caiiiac.androidsimple;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;


@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    // 普通样式
    @Event(value = R.id.normalDialog)
    private void openDialog(View view) {
        new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage("这是一个对话框")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"点击了确定", Toast.LENGTH_SHORT).show();
//                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton("取消", null)
                .create()
                .show();

    }

    // 单选框
    String[] sexs = new String[]{"男", "女"};
    @Event(value = R.id.singleDialog)
    private void openSingleDialog(View view) {
        new AlertDialog.Builder(this)
                .setTitle("请选择您的性别")
                .setSingleChoiceItems(sexs, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, sexs[which], Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
//                .setPositiveButton("确定", null)
                .create()
                .show();
    }

    // 复选框
    String[] colors = new String[]{"红色","黄色","绿色"};
    boolean[] checkeds = new boolean[]{true, false, false};
    @Event(value = R.id.multiDialog)
    private void openMultiDialog(View view) {
        new AlertDialog.Builder(this)
                .setTitle("选择喜欢的颜色")
                .setMultiChoiceItems(colors, checkeds, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        checkeds[which] = isChecked;
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String text = "";
                        for (int i = 0; i < checkeds.length; i++) {
                            if (checkeds[i]) {
                                text += colors[i] + " ";
                            }
                        }
                        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("取消", null)
                .create()
                .show();
    }
}