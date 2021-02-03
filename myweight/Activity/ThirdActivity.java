package jp.co.futureantiques.myweight.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import jp.co.futureantiques.myweight.R;

public class ThirdActivity extends AbstractWeightBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
    }

    @Override
    protected void onResume(){
        super.onResume();
        setContentView(R.layout.activity_third);
        toolbar = findViewById(R.id.MenuBar);
        setSupportActionBar(toolbar);
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
                Intent intent0 = new Intent(ThirdActivity.this, SecondActivity.class);
                startActivity(intent0);
                return true;
            case R.id.dash_icon:
                Log.i("add_icon_buttonP", "AddIconが選択されました。");
                Intent intent1 = new Intent(ThirdActivity.this, ThirdActivity.class);
                startActivity(intent1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}