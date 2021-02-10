package jp.co.futureantiques.myweight.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import jp.co.futureantiques.myweight.Database.DBManager;
import jp.co.futureantiques.myweight.R;

public class SecondActivity extends AbstractWeightBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDBManager = new DBManager(SecondActivity.this);
        toolbar = findViewById(R.id.MenuBar);
        setSupportActionBar(toolbar);

        textView = findViewById(R.id.DBView);
        mWeight = findViewById(R.id.weight_Text);
        mFat = findViewById(R.id.fat_Text);

        //登録
        Button registerButton = findViewById(R.id.register_Button);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("InsertButton", "登録ボタンが押されました");
                //EditTextに入力された値を変数へ格納する
                weight = mWeight.getText().toString();
                fat = mFat.getText().toString();
                mDBManager.insert(weight, fat);
            }
        });

        //更新処理
        Button upDataButton = findViewById(R.id.reRode_Button);
        upDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //EditTextに入力された値を変数へ格納する
                iId = Integer.parseInt(mId.getText().toString());
                iWeight = Integer.parseInt(mWeight.getText().toString());
                iFat = Integer.parseInt(mFat.getText().toString());
                mDBManager.UpData(iId, iWeight, iFat);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_icon:
                Log.i("add_icon_buttonP", "AddIconが選択されました。");
                Intent intent0 = new Intent(SecondActivity.this, SecondActivity.class);
                startActivity(intent0);
                return true;
            case R.id.home_icon:
                Log.i("home_icon_buttonP", "HomeIconが選択されました。");
                Intent intent1 = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}