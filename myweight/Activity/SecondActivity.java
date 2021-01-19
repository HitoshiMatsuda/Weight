package jp.co.futureantiques.myweight.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import jp.co.futureantiques.myweight.Database.DBManager;
import jp.co.futureantiques.myweight.R;

public class SecondActivity extends AppCompatActivity {
    protected DBManager mDBManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDBManager = new DBManager(SecondActivity.this);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("selectTestB","ボタンが押されました。");
                mDBManager.selectTest(SecondActivity.this);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        //タイトルの文字色指定
        getSupportActionBar().setTitle(Html.fromHtml("<font color = black>" + getString(R.string.app_name) + "</font>"));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.home_icon_button:
                Log.i("home_icon_buttonP", "ホームiconが選択されました。");
                Intent intent0 = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent0);
                return true;
            case R.id.add_icon_button:
                Log.i("add_icon_buttonP", "新規追加iconが選択されました。");
                Intent intent = new Intent(SecondActivity.this, SecondActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}