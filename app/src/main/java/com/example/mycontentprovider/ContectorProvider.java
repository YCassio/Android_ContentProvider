package com.example.mycontentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.database.sqlite.SQLiteDatabase;

public class ContectorProvider extends ContentProvider {

    public static final int CONTECTOR_DIR = 0;

    public static final int CONTECTOR_ITEM = 1;

    public static final String AUTHORITY = "com.example.mycontentprovider.provider";

    private static UriMatcher uriMatcher;

    private ContactsDatabase dbHelper;


    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY,"contacts",CONTECTOR_DIR);
        uriMatcher.addURI(AUTHORITY,"contacts/#",CONTECTOR_ITEM);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        // 鍒犻櫎鏁版嵁
        /*SQLiteDatabase db = dbHelper.getWritableDatabase();
        int deletedRows = 0;
        switch (uriMatcher.match(uri)) {
            case CONTECTOR_DIR:
                deletedRows = db.delete("Contacts", selection, selectionArgs);
                break;
            case CONTECTOR_ITEM:
                String contectorId = uri.getPathSegments().get(1);
                deletedRows = db.delete("Contacts", "id = ?",
                        new String[] { contectorId });
                break;
            default:
                break;
        }
        return deletedRows;*/
        return 0;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        /*switch (uriMatcher.match(uri)) {
            case CONTECTOR_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.mycontentprovider.provider.contacts";
            case CONTECTOR_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.mycontentprovider.provider.contacts";
        }*/
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        // 娣诲姞鏁版嵁
        /*SQLiteDatabase db = dbHelper.getWritableDatabase();
        Uri uriReturn = null;
        switch (uriMatcher.match(uri)) {
            case CONTECTOR_DIR:
            case CONTECTOR_ITEM:
                long newContectorId = db.insert("Contacts", null, values);
                uriReturn = Uri.parse("content://" + AUTHORITY + "/contacts/" + newContectorId);
                break;
            default:
                break;
        }
        return uriReturn;*/
        return null;
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        dbHelper = new ContactsDatabase(getContext(), "MyContector.db", null, 2);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
        // 鏌ヨ鏁版嵁
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        switch (uriMatcher.match(uri)) {
            case CONTECTOR_DIR:
                cursor = db.query("Contacts", projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case CONTECTOR_ITEM:
                String contectorId = uri.getPathSegments().get(1);
                cursor = db.query("Contacts", projection, "id = ?",
                        new String[] { contectorId }, null, null, sortOrder);
                break;
            default:
                break;
        }
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.

        // 鏇存柊鏁版嵁
        /*SQLiteDatabase db = dbHelper.getWritableDatabase();
        int updatedRows = 0;
        switch (uriMatcher.match(uri)) {
            case CONTECTOR_DIR:
                updatedRows = db.update("Contacts", values, selection, selectionArgs);
                break;
            case CONTECTOR_ITEM:
                String contectorId = uri.getPathSegments().get(1);
                updatedRows = db.update("Contacts", values, "id = ?",
                        new String[] { contectorId });
                break;
            default:
                break;
        }
        return updatedRows;*/
        return 0;
    }
}
