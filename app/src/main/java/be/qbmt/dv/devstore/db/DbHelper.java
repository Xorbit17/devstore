package be.qbmt.dv.devstore.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import be.qbmt.dv.devstore.db.DevStoreDbContract.ProductEntry;
import be.qbmt.dv.devstore.db.DevStoreDbContract.UserEntry;

/**
 * Created by dv on 27/12/2017.
 */

public class DbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "devstore.db";
    public static final int DATABASE_VERSION = 1;

    public static final DevStoreDbContract.User defaultUser = new DevStoreDbContract.User(-1, "dv", "qbmt8400");

    public static final DevStoreDbContract.Product[] defaultProducts = new DevStoreDbContract.Product[] {
            new DevStoreDbContract.Product(-1, "Coca cola",
                    "cola.jpg", "So sweet and tasty you'll want to buy two. Just to pour one over your body as you feel good about its fatness.", 61),
            new DevStoreDbContract.Product(-1, "Coca cola Zero",
                    "zero.jpg", "For people who rather pay more for less sugar. And for those who prefer cancer to diabetes.", 61),
            new DevStoreDbContract.Product(-1, "Sugar Waffle",
                    "wafel.png", "Dirt cheap way to treat yourself to metabolic syndrome. Join a large part of the civilisation in an unhealthy lifestyle. Fatness over fitness.", 10),
            new DevStoreDbContract.Product(-1, "Snickers",
                    "snickers.png",
                    "For those who do not accept compromises in both chocolateness and nuttiness. Absolutely guaranteed to make you feel fat and guilty. Unless you are one of the chosen ones.", 32)
        };


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserEntry.CREATE_SQL);
        db.execSQL(ProductEntry.CREATE_SQL);
        db.execSQL(defaultUser.getInsertSql());
        for (DevStoreDbContract.Product p:defaultProducts) {
            db.execSQL(p.getInsertSql());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
