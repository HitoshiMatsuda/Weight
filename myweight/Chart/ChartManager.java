package jp.co.futureantiques.myweight.Chart;

import android.database.Cursor;
import android.graphics.Color;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

import jp.co.futureantiques.myweight.Database.DBHelper;
import jp.co.futureantiques.myweight.Database.DBManager;

public class ChartManager {
    private DBManager dbManager;
    private DBHelper dbHelper;
    private float weight;
    private float fat;

    public ChartManager() {
    }

    public void getFromDB(){
        //DBより情報取得
        dbManager = new DBManager();
        Cursor cursor = dbManager.selectAll();
        cursor.moveToFirst();

        ArrayList<Float> wTest = new ArrayList<>();
        while (cursor.moveToNext()) {
            if (cursor == null) {
                wTest.add(0f);
            } else {
                weight = cursor.getFloat(
                        cursor.getColumnIndex("weight"));
                wTest.add(weight);
            }
        }

        ArrayList<Float> fTest = new ArrayList();
        while (cursor.moveToNext()) {
            if (cursor == null) {
                fTest.add(0f);
            } else {
                fat = cursor.getFloat(
                        cursor.getColumnIndex("fat"));
                fTest.add(fat);
            }
        }
    }

    public LineData setData() {

        //getFromDB();

        //ラインのデータ設定等
        //表示用サンプルデータ
        float[] swData = {95, 85, 70, 75, 80, 75, 73, 69, 68, 67, 65, 64, 62};
        float[] sfData = {20, 17, 15, 14, 15, 11, 10, 10, 9, 9, 7, 7, 6};
        //体重用リスト
        ArrayList<Entry> wData = new ArrayList<>();
        for (int i = 0; i < swData.length; i++) {
            wData.add(new Entry(i, swData[i], null, null));
        }
        //体脂肪用リスト
        ArrayList<Entry> fData = new ArrayList<>();
        for (int i = 0; i < sfData.length; i++) {
            fData.add(new Entry(i, sfData[i], null, null));
        }
        LineDataSet weights;
        LineDataSet fats;

        //体重ラインの設定
        //ラベル名
        weights = new LineDataSet(wData, "体重");
        //線の色
        weights.setColor(Color.RED);
        //座標の色
        weights.setCircleColor(Color.WHITE);
        //座標の大きさ
        weights.setCircleRadius(3f);
        //線の太さ
        weights.setLineWidth(5f);
        //線の下を塗りつぶすか
        weights.setDrawFilled(true);
        //塗り潰したフィールドの色
        weights.setFillColor(Color.RED);

        //体脂肪ラインの設定
        //ラベル名
        fats = new LineDataSet(fData, "体脂肪");
        //線の色
        fats.setColor(Color.BLUE);
        //座標の色
        fats.setCircleColor(Color.WHITE);
        //座標の大きさ
        fats.setCircleRadius(3f);
        fats.setLineWidth(5f);
        fats.setDrawFilled(true);
        fats.setFillColor(Color.BLUE);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(weights);
        dataSets.add(fats);
        LineData lineData = new LineData(dataSets);

        return lineData;
    }
}