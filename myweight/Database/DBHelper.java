package jp.co.futureantiques.myweight.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    //データベースの名前
    private static final String DBNAME = "WEIGHT_MASTER";
    //データベースのバージョン
    private static final int VERSION = 1;

    //コンストラクタ
    public DBHelper(Context context) {
        super(context, DBNAME, null, VERSION);
    }

    //データベースが作成された際に実行
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("Log_DB_Create", "dbHelperクラスのonCreateが実行されました。");

        // テーブルを作成する
        //ID|体重|体脂肪率|メモ|登録日付
        db.execSQL("CREATE TABLE WEIGHT_MASTER("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "weight INTEGER,"
                + "fat INTEGER,"
                + "memo TEXT,"
                + "pectorals INTEGER,"
                + "back INTEGER,"
                + "lower INTEGER,"
                + "shoulder INTEGER,"
                + "creation_date TIMESTAMP DEFAULT (DATETIME(CURRENT_TIMESTAMP,'LOCALTIME')),"
                + "dflag INTEGER DEFAULT 0)"
        );
    }

    //データベースのバージョンアップの際に実行
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //DROPしても大丈夫？
        //テーブルの削除
        db.execSQL("DROP TABLE IF EXISTS WEIGHT_MASTER");
        //テーブルの新規作成
        onCreate(db);
    }

    //データベースが開かれた際に実行
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        Log.i("log_DB_Open", "DBが開かれました");
    }
}
