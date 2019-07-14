import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import info.androidhive.sqlite.database.model.Article;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "articles_parser";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create notes table
        db.execSQL(Note.CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Note.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public long insertArticle(
            String pageId,
            String author,
            String title,
            String description,
            String url,
            String urlToImage,
            String content,
            String publishedAt
    ) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        // `id` will be inserted automatically.
        values.put(Article.COLUMN_PAGE_ID, pageId);
        values.put(Article.COLUMN_AUTHOR, author);
        values.put(Article.COLUMN_TITLE, title);
        values.put(Article.COLUMN_DESCRIPTION, description);
        values.put(Article.COLUMN_URL, url);
        values.put(Article.COLUMN_URL_TO_IMAGE, urlToImage);
        values.put(Article.COLUMN_CONTENT, content);
        values.put(Article.COLUMN_PUBLISHED_AT, publishedAt);

        // insert row
        long id = db.insert(Article.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }


}