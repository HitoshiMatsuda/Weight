package jp.co.futureantiques.myweight.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;

import jp.co.futureantiques.myweight.Chart.ChartManager;
import jp.co.futureantiques.myweight.Database.DBManager;
import jp.co.futureantiques.myweight.R;


public class MainActivity extends AbstractWeightBaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //レイアウトを紐付け
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.MenuBar);
        setSupportActionBar(toolbar);
        mId = findViewById(R.id.id_Text);
        mWeight = findViewById(R.id.weight_Text);
        mFat = findViewById(R.id.fat_Text);

        //DBを実装
        mDBManager = new DBManager(MainActivity.this);

        //グラフ部分設計
        chartManager = new ChartManager();

        //体重と体脂肪を取り出す
        wBox = mDBManager.wSelect();
        fBox = mDBManager.fSelect();
        Log.i("wfBox_Insert", "wBox,fBoxへ取り込みました。");

        //LineSetへ格納
        lineData = chartManager.setData(wBox, fBox);

        //折れ線グラフを紐付けする
        mChart = findViewById(R.id.lineChartExample1);

        //グラフの背景色
        mChart.setDrawGridBackground(true);

        //グラフの説明テキスト表示を許可
        mChart.getDescription().setEnabled(true);

        //グラフの説明テキスト（アプリ名など）
        mChart.getDescription().setText(getResources().getString(R.string.app_name));


        //X軸の設定
        XAxis xAxis = mChart.getXAxis();
        //X軸のラベルの傾き指定
        xAxis.setLabelRotationAngle(45f);
        //X軸のMAX,min
        //X軸に同時に表示できるデータの数
        //10個まで同時に表示可能と設定する
        xAxis.setAxisMaximum(30f);
        xAxis.setAxisMinimum(0f);
        //DBへ登録した年月日をX軸へ追加
        //同じ日に複数登録した場合は？
        //xAxis.setValueFormatter(new IndexAxisValueFormatter(getDate()));
        //X軸を破線にする
        xAxis.enableAxisLineDashedLine(5f, 5f, 1f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);


        //Y軸の設定
        YAxis yAxis = mChart.getAxisLeft();
        //Y軸のMAX,min
        yAxis.setAxisMaximum(100f);
        yAxis.setAxisMinimum(0f);
        //Y軸を破線にする
        yAxis.enableAxisLineDashedLine(5f, 5f, 1f);
        yAxis.setDrawZeroLine(true);

        //グラフの右側に目盛りが不要であれば"false"
        mChart.getAxisRight().setEnabled(false);

        //mChart.animateX(1500);
        //グラフをスクロール可能にする
        mChart.setVisibleXRangeMaximum(10f);
        mChart.setData(lineData);

        //グラフの表示
        mChart.invalidate();
        Log.i("Chart", "グラフが表示されます。");
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
                Intent intent0 = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent0);
                return true;
            case R.id.home_icon:
                Log.i("home_icon_buttonP", "HomeIconが選択されました。");
                Intent intent1 = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}