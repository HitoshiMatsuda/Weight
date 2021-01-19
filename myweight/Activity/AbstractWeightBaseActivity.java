package jp.co.futureantiques.myweight.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.EditText;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;

import jp.co.futureantiques.myweight.Chart.ChartManager;
import jp.co.futureantiques.myweight.Database.DBManager;
import jp.co.futureantiques.myweight.R;

public class AbstractWeightBaseActivity extends AppCompatActivity {
    protected LineChart mChart;
    protected ChartManager chartManager;
    protected EditText mWeight;
    protected EditText mFat;
    protected String weight;
    protected String fat;
    protected DBManager mDBManager;
    protected StringBuilder stringBuilder;
    protected TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abstract_weight_base);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //グラフ説明の文字色指定
        //mChart.getDescription().setTextColor(android.R.color.black);
        //グラフ説明の文字サイズ指定
        //mChart.getDescription().setTextSize(10f);
        //グラフ説明の配置指定
        //mChart.getDescription().setPosition(0,0);
    }

    //ツールバーデザイン
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        //タイトルの文字色指定
        getSupportActionBar().setTitle(Html.fromHtml("<font color = black>" + getString(R.string.app_name) + "</font>"));
        return true;
    }
}