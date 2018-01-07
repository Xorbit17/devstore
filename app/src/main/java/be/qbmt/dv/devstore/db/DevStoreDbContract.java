package be.qbmt.dv.devstore.db;

import android.database.Cursor;
import android.provider.BaseColumns;


public final class DevStoreDbContract {
    private DevStoreDbContract() {
    }

    public static final class UserEntry implements BaseColumns {
        public static final String TABLE_NAME = "users";

        public static final String C_USERNAME = "username";
        public static final String C_PASSWORD = "password";

        public static final String CREATE_SQL = "CREATE TABLE " + TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY, " +
                C_USERNAME + " TEXT UNIQUE NOT NULL, " +
                C_PASSWORD + " TEXT NOT NULL)";


        public static User constructFromCursor(Cursor cursor) {
            int idPos = cursor.getColumnIndex(_ID);
            int userNamePos = cursor.getColumnIndex(C_USERNAME);
            int passwordPos = cursor.getColumnIndex(C_PASSWORD);

            return new User(cursor.getInt(idPos),cursor.getString(userNamePos), cursor.getString(passwordPos));
        }

    }


    public static final class ProductEntry implements BaseColumns {
        public static final String TABLE_NAME = "products";

        public static final String C_NAME = "name";
        public static final String C_IMAGE_RESOURCE = "imageresource";
        public static final String C_DESCRIPTION = "description";
        public static final String C_PRICE = "price";

        public static final String CREATE_SQL = "CREATE TABLE " + TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY, " +
                C_NAME + " TEXT UNIQUE NOT NULL, " +
                C_IMAGE_RESOURCE + " TEXT NOT NULL, " +
                C_DESCRIPTION + " TEXT NOT NULL, " +
                C_PRICE + " INTEGER NOT NULL)";

        public static Product constructFromCursor(Cursor cursor) {
            int idPos = cursor.getColumnIndex(_ID);
            int userNamePos = cursor.getColumnIndex(C_NAME);
            int imageResourcePos = cursor.getColumnIndex(C_IMAGE_RESOURCE);
            int descriptionPos = cursor.getColumnIndex(C_DESCRIPTION);
            int pricePos = cursor.getColumnIndex(C_PRICE);

            return new Product(
                    cursor.getInt(idPos),
                    cursor.getString(userNamePos),
                    cursor.getString(imageResourcePos),
                    cursor.getString(descriptionPos),
                    cursor.getInt(pricePos));
        }

    }

    public static class Product {
        public int id;
        public String name;
        public String imageResource;
        public String description;
        public int price;

        public Product(int id, String name, String imageResource, String description, int price) {
            this.id = id;
            this.name = name;
            this.imageResource = imageResource;
            this.description = description;
            this.price = price;
        }

        public String getInsertSql() {
            return "INSERT INTO " + ProductEntry.TABLE_NAME + " (" +
                    ProductEntry._ID + "," +
                    ProductEntry.C_NAME + "," +
                    ProductEntry.C_IMAGE_RESOURCE + "," +
                    ProductEntry.C_DESCRIPTION + "," +
                    ProductEntry.C_PRICE +
                    ") VALUES (NULL,\"" +
                    name + "\",\"" + imageResource + "\",\"" + description + "\"," + price + ");";
        }

    }


    public static class User {
        public int id;
        public String username;
        public String password;

        public User(int id, String username, String password) {
            this.id = id;
            this.username = username;
            this.password = password;
        }

        public String getInsertSql() {
            return "INSERT INTO " + UserEntry.TABLE_NAME + " ("+
                    UserEntry._ID + "," +
                    UserEntry.C_USERNAME + "," +
                    UserEntry.C_PASSWORD +
                    ") VALUES (NULL, \"" +
                    username + "\",\"" + password + "\");";
        }
    }
}
