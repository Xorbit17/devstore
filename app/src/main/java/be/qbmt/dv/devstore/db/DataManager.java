package be.qbmt.dv.devstore.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import be.qbmt.dv.devstore.db.DevStoreDbContract.UserEntry;
import be.qbmt.dv.devstore.db.DevStoreDbContract.ProductEntry;

/**
 * Created by dv on 27/12/2017.
 */

public class DataManager {
    private SQLiteDatabase db;

    public DataManager(SQLiteOpenHelper dbHelper) {
        db = dbHelper.getReadableDatabase();
    }

    public List<DevStoreDbContract.User> getAllUsers() {
        final String[] cols = {UserEntry._ID,UserEntry.C_USERNAME,UserEntry.C_PASSWORD};

        Cursor cursor = db.query(UserEntry.TABLE_NAME,cols,null,null,null,null,null);
        List<DevStoreDbContract.User> result = new ArrayList<>();
        while (cursor.moveToNext()) {
            result.add(UserEntry.constructFromCursor(cursor));
        }
        cursor.close();
        return result;
    }

    public List<DevStoreDbContract.Product> getAllProducts() {
        final String[] cols = {ProductEntry._ID,ProductEntry.C_NAME,ProductEntry.C_PRICE,ProductEntry.C_IMAGE_RESOURCE,ProductEntry.C_DESCRIPTION};

        Cursor cursor = db.query(ProductEntry.TABLE_NAME,cols,null,null,null,null,null);
        List<DevStoreDbContract.Product> result = new ArrayList<>();
        while (cursor.moveToNext()) {
            result.add(ProductEntry.constructFromCursor(cursor));
        }
        cursor.close();
        return result;
    }

    public DevStoreDbContract.User getUserByName(String name) throws NotFoundException {
        final String[] cols = {UserEntry._ID,UserEntry.C_USERNAME,UserEntry.C_PASSWORD};
        Cursor cursor = db.query(UserEntry.TABLE_NAME,cols,UserEntry.C_USERNAME + "=\"" + name + "\"",null,null,null,null,null);
        if (cursor.isLast()) {
            cursor.close();
            throw new NotFoundException("User not found");
        }
        cursor.moveToNext();
        DevStoreDbContract.User result = UserEntry.constructFromCursor(cursor);
        cursor.close();
        return result;
    }

    public DevStoreDbContract.User getUserById(int id) throws NotFoundException {
        final String[] cols = {UserEntry._ID,UserEntry.C_USERNAME,UserEntry.C_PASSWORD};
        Cursor cursor = db.query(UserEntry.TABLE_NAME,cols,UserEntry._ID + "=" + id,null,null,null,null,null);
        if (cursor.isLast()) {
            cursor.close();
            throw new NotFoundException("User not found");
        }
        cursor.moveToNext();
        DevStoreDbContract.User result = UserEntry.constructFromCursor(cursor);
        cursor.close();
        return result;
    }

    public class NotFoundException extends Exception {
        public NotFoundException(String msg) {
            super(msg);
        }
    }

    public Cursor getProductsCursor() {
        final String[] cols = {ProductEntry._ID,ProductEntry.C_NAME,ProductEntry.C_PRICE,ProductEntry.C_IMAGE_RESOURCE,ProductEntry.C_DESCRIPTION};

        return db.query(ProductEntry.TABLE_NAME,cols,null,null,null,null,null);
    }

}
