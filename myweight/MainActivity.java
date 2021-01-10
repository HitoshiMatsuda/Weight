package jp.co.futureantiques.myweight;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.LineData;

import jp.co.futureantiques.myweight.Chart.ChartManager;


public class MainActivity extends AppCompatActivity {

    private LineChart mChart;
    private ChartManager chartManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_main);

        chartManager = new ChartManager();

        //折れ線グラフを紐付けする
        mChart = findViewById(R.id.lineChartExample1);

        //グラフの背景色
        mChart.setDrawGridBackground(true);

        //グラフの説明テキスト表示を許可
        mChart.getDescription().setEnabled(true);

        //グラフの説明テキスト（アプリ名など）
        mChart.getDescription().setText(getResources().getString(R.string.app_name));

        //グラフをスクロール可能にする
        mChart.canScrollHorizontally(100);

        //グラフ説明の文字色指定
        //mChart.getDescription().setTextColor(android.R.color.black);
        //グラフ説明の文字サイズ指定
        //mChart.getDescription().setTextSize(10f);
        //グラフ説明の配置指定
        //mChart.getDescription().setPosition(0,0);

        //X軸の設定
        XAxis xAxis = mChart.getXAxis();
        //X軸のラベルの傾き指定
        xAxis.setLabelRotationAngle(45);
        //X軸のMAX,min
        //X軸に同時に表示できるデータの数
        //10個まで同時に表示可能と設定する
        xAxis.setAxisMaximum(10f);
        xAxis.setAxisMinimum(0f);
        //DBへ登録した年月日をX軸へ追加
        //同じ日に複数登録した場合は？
        //xAxis.setValueFormatter(new IndexAxisValueFormatter(getDate()));
        //X軸を破線にする
        xAxis.enableAxisLineDashedLine(10f, 10f, 1f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);


        //Y軸の設定
        YAxis yAxis = mChart.getAxisLeft();
        //Y軸のMAX,min
        yAxis.setAxisMaximum(100f);
        yAxis.setAxisMinimum(0f);
        //Y軸を破線にする
        yAxis.enableAxisLineDashedLine(10f, 10f, 1f);
        yAxis.setDrawZeroLine(true);

        //グラフの右側に目盛りが不要であれば"false"
        mChart.getAxisRight().setEnabled(false);

        LineData lineData = chartManager.setData();
        mChart.setData(lineData);
        mChart.animateX(1500);

        //グラフの表示
        mChart.invalidate();
    }
}