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
    //固定値
    private static final String ID = "id";
    private static final String WEIGHT = "weight";
    private static final String FAT = "fat";
    private static final String MEMO = "memo";
    private static final String PECTORALS = "pectorals";
    private static final String BACK = "back";
    private static final String LOWER = "lower";
    private static final String SHOULDER = "shoulder";
    private static final String RUN = "run";
    private static final String REST = "rest";
    private static final String CREATION_DATE = "creation_date";
    private static final String DELETE_FLAG = "delete_flag";


    //コンストラクタ
    public DBHelper(Context context) {
        super(context, DBNAME, null, VERSION);
    }

    //データベースが作成された際に実行
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("Log_DB_Create", "dbHelperクラスのonCreateが実行されました。");

        // テーブルを作成する
        //ID|体重|体脂肪率|メモ|日付|削除フラグ
        db.execSQL("CREATE TABLE WEIGHT_MASTER("
                + ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                + WEIGHT + "INTEGER,"
                + FAT + "INTEGER,"
                + MEMO + "TEXT,"
                + CREATION_DATE + "TIMESTAMP DEFAULT (DATETIME(CURRENT_TIMESTAMP,'LOCALTIME')),"
                + DELETE_FLAG + "INTEGER DEFAULT 0)"
        );

        //ID|メモ|胸筋|背筋|下半身|肩|有酸素|オフ|日付|削除フラグ
        db.execSQL("CREATE TABLE TRAINING_RECORD("
                + ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                + MEMO + "TEXT,"
                + PECTORALS + "INTEGER,"
                + BACK + "INTEGER,"
                + LOWER + "INTEGER,"
                + SHOULDER + "INTEGER,"
                + RUN + "INTEGER,"
                + REST + "INTEGER,"
                + CREATION_DATE + "TIMESTAMP DEFAULT (DATETIME(CURRENT_TIMESTAMP,'LOCALTIME')),"
                + DELETE_FLAG + "INTEGER DEFAULT 0)"
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
