package com.simple.caiiiac.androidsimple;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText mAccountET;
    private EditText mPasswodkET;

    private ContactDao mDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAccountET = this.<EditText>findViewById(R.id.account_et);
        mPasswodkET = this.<EditText>findViewById(R.id.password_et);

        readInfo();

        mDao = new ContactDao(this);

    }

    // 登录事件
    public void loginClick(View v) {
        String account = mAccountET.getText().toString();
        String password = mPasswodkET.getText().toString();

        if (TextUtils.isEmpty(account) || TextUtils.isEmpty(password)) {
            Toast.makeText(this,"请填写完整的信息",Toast.LENGTH_SHORT).show();
            return;
        }
        saveInfo(account,password);
    }

    // 保存数据
    private void saveInfo(String account, String password) {

        SharedPreferences sp = getSharedPreferences("userInfo", MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("account", account);
        edit.putString("password", password);
        edit.commit();

        sp.edit();
//        try {
//            File file = new File(getCacheDir(),"/info.txt");
//            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
//            writer.write(account+"#"+password);
//            writer.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    // 读取数据
    private void readInfo() {
        SharedPreferences sp = getSharedPreferences("userInfo", MODE_PRIVATE);
        String account = sp.getString("account","");
        String password = sp.getString("password","");

        mAccountET.setText(account);
        mPasswodkET.setText(password);

//        try {
//            File file = new File(getCacheDir(), "/info.text");
//            if (file.exists() && file.length() > 0) {
//                BufferedReader reader = new BufferedReader(new FileReader(file));
//                String line = reader.readLine();
//                if (line.contains("#")) {
//                    String[] datas = line.split("#");
//                    mAccountET.setText(datas[0]);
//                    mPasswodkET.setText(datas[1]);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public void insertContact(View v) {
        boolean isSucceed = mDao.insertContact(mAccountET.getText().toString(), mPasswodkET.getText().toString());
        Toast.makeText(this, isSucceed ? "插入成功" : "插入失败", Toast.LENGTH_SHORT).show();
    }

    public void updateContact(View v) {
        boolean isSucceed = mDao.updateContact(mAccountET.getText().toString(), mPasswodkET.getText().toString());
        Toast.makeText(this, isSucceed ? "更新成功" : "更新失败", Toast.LENGTH_SHORT).show();
    }

    public void deleteContact(View v) {
        boolean isSucceed = mDao.deleteContact(mAccountET.getText().toString());
        Toast.makeText(this, isSucceed ? "删除成功" : "删除失败", Toast.LENGTH_SHORT).show();
    }

    public void queryContact(View v) {
        String result = mDao.queryContact(mPasswodkET.getText().toString());
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
    }
}