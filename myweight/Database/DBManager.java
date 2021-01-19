package jp.co.futureantiques.myweight.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class DBManager extends AppCompatActivity {
    private DBHelper mHelper;
    private SQLiteDatabase db;

    public DBManager(Context context) {
        mHelper = new DBHelper(context);
    }

    //追加処理
    //AddActivity
    public void insert(String weight, String fat) {
        db = mHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("weight", Integer.parseInt(weight));
        contentValues.put("fat", Integer.parseInt(fat));
        db.insert("WEIGHT_MASTER", null, contentValues);

        Log.i("Insert_Success", "Insertに成功しました。");
        db.close();
    }

    //SELECT
    public StringBuilder selectTest(Context context) {
        if (mHelper == null) {
            mHelper = new DBHelper(context);
        }
        if (db == null) {
            db = mHelper.getReadableDatabase();
        }
        Cursor cursor = db.query(
                "WEIGHT_MASTER",
                new String[]{"id", "weight", "fat"},
                null,
                null,
                null,
                null,
                null
        );

        //WeightDataへ格納する
        cursor.moveToFirst();

        StringBuilder sBuilder = new StringBuilder();
        for (int i = 0; i < cursor.getCount(); i++) {
            sBuilder.append("------------------------------\n");
            sBuilder.append(cursor.getString(cursor.getColumnIndex("id")));
            sBuilder.append("\n");
            sBuilder.append(cursor.getString(cursor.getColumnIndex("weight")));
            sBuilder.append("\n");
            sBuilder.append(cursor.getString(cursor.getColumnIndex("fat")));
            sBuilder.append("\n");
            sBuilder.append("------------------------------\n");
            cursor.moveToNext();
        }
        cursor.close();
        return sBuilder;
    }
}
