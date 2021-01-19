package jp.co.futureantiques.myweight.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.LineData;

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
        setContentView(R.layout.activity_main);
        mDBManager = new DBManager(MainActivity.this);
        textView = findViewById(R.id.testText);
        mWeight = findViewById(R.id.textWeight);
        mFat = findViewById(R.id.textFat);

        //登録
        Button registerButton = findViewById(R.id.aButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("InsertButton", "登録ボタンが押されました");

                //EditTextに入力された値を変数へ格納する
                weight = mWeight.getText().toString();
                fat = mFat.getText().toString();

                //DataBaseManagerクラスのinsertメソッドを使用してDBへ保存
                mDBManager.insert(weight, fat);
            }
        });


        //読み込み
        Button button = findViewById(R.id.rButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("SelectButton", "読込ボタンが押されました。");

                //selectTestの戻り値を格納する
                stringBuilder = mDBManager.selectTest(MainActivity.this);

                //TextViewへ表示
                textView.setText(stringBuilder.toString());
            }
        });


        //グラフ部分設計
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
        Log.i("Chart","グラフが表示されます。");
    }

    //メニューオプション
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.home_icon_button:
                Log.i("home_icon_buttonP", "ホームiconが選択されました。");
                Intent intent0 = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent0);
                return true;
            case R.id.add_icon_button:
                Log.i("add_icon_buttonP", "新規追加iconが選択されました。");
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}