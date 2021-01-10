package jp.co.futureantiques.myweight.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import jp.co.futureantiques.myweight.WeightData;

public class DBManager {
    private DBHelper mHelper;
    private SQLiteDatabase db;

    public DBManager(Context context) {
        mHelper = new DBHelper(context);
    }

    public DBManager() {

    }

    //SELECT文
    //全選択（一覧表示）
    public Cursor selectAll() {
        //dbの初期化
        db = mHelper.getWritableDatabase();
        return db.rawQuery("SELECT id as _id, weight, fat, memo, creation_date FROM WEIGHT_MASTER", null);
    }

    /*//SELECT文
    //条件選択
    public WeightData select(String keyId) {
        db = mHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT id, weight, memo, fat, creation_data " +
                        "FROM WEIGHT_MASTER " +
                        "WHERE id = ?",
                new String[]{keyId});

        //WeightDataへ格納する
        cursor.moveToFirst();
        WeightData wData = new WeightData();
        int weight = cursor.getInt(cursor.getColumnIndex("weight"));
        int fat = cursor.getInt(cursor.getColumnIndex("fat"));
        String memo = cursor.getString(cursor.getColumnIndex("memo"));

        //戻り値の中のパラメータ
        wData.setWeight(weight);
        wData.setFat(fat);
        wData.setMemo(memo);

        //ログの出力
        Log.i("LogSelectSuccess", "DBからSELECTに成功しました");
        return wData;
    }*/
}
