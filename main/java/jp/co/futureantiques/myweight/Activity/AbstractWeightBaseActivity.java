package jp.co.futureantiques.myweight.Activity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.LineData;

import jp.co.futureantiques.myweight.Chart.ChartManager;
import jp.co.futureantiques.myweight.Database.DBManager;
import jp.co.futureantiques.myweight.R;

public class AbstractWeightBaseActivity extends AppCompatActivity {
    protected LineChart mChart;
    protected ChartManager chartManager;
    protected DBManager mDBManager;
    protected Toolbar toolbar;
    protected EditText mWeight;
    protected EditText mFat;
    protected EditText mId;
    protected int iId;
    protected int iWeight;
    protected int iFat;
    protected String weight;
    protected String fat;
    protected TextView textView;
    protected int[] wBox;
    protected int[] fBox;
    protected LineData lineData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abstract_weight_base);
        toolbar = findViewById(R.id.MenuBar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onResume() {
        super.onResume();
        textView = findViewById(R.id.DBView);
        mWeight = findViewById(R.id.weight_Text);
        mFat = findViewById(R.id.fat_Text);
        //グラフ説明の文字色指定
        //mChart.getDescription().setTextColor(android.R.color.black);
        //グラフ説明の文字サイズ指定
        //mChart.getDescription().setTextSize(10f);
        //グラフ説明の配置指定
        //mChart.getDescription().setPosition(0,0);
    }
}