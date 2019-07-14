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
            int pageId,
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

    public Article getArticle(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Article.TABLE_NAME,
                new String[]{
                        Article.COLUMN_ID,
                        Article.COLUMN_PAGE_ID,
                        Article.COLUMN_AUTHOR,
                        Article.COLUMN_TITLE,
                        Article.COLUMN_DESCRIPTION,
                        Article.COLUMN_URL,
                        Article.COLUMN_URL_TO_IMAGE,
                        Article.COLUMN_CONTENT,
                        Article.COLUMN_PUBLISHED_AT
                },
                Article.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null, null, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        Article article = new Article(
                cursor.getInt(cursor.getColumnIndex(Article.COLUMN_ID)),
                cursor.getInt(cursor.getColumnIndex(Article.COLUMN_PAGE_ID)),
                cursor.getString(cursor.getColumnIndex(Article.COLUMN_AUTHOR)),
                cursor.getString(cursor.getColumnIndex(Article.COLUMN_TITLE)),
                cursor.getString(cursor.getColumnIndex(Article.COLUMN_DESCRIPTION)),
                cursor.getString(cursor.getColumnIndex(Article.COLUMN_URL)),
                cursor.getString(cursor.getColumnIndex(Article.COLUMN_URL_TO_IMAGE)),
                cursor.getString(cursor.getColumnIndex(Article.COLUMN_CONTENT)),
                cursor.getString(cursor.getColumnIndex(Article.COLUMN_PUBLISHED_AT)));

        // close the db connection
        cursor.close();

        return article;
    }

    public List<Article> getArticlesByPage(int pageId) {
        List<Article> articles = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Article.TABLE_NAME + " ORDER BY " +
                Article.COLUMN_PUBLISHED_AT + " DESC WHERE pageId = " + pageId;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Article article = new Article();
                article.setId(cursor.getInt(cursor.getColumnIndex(Note.COLUMN_ID)));
                article.setPageId(cursor.getInt(cursor.getColumnIndex(Note.COLUMN_PAGE_ID)));
                article.setAuthor(cursor.getString(cursor.getColumnIndex(Note.COLUMN_AUTHOR)));
                article.setTitle(cursor.getString(cursor.getColumnIndex(Note.COLUMN_TITLE)));
                article.setDescription(cursor.getString(cursor.getColumnIndex(Note.COLUMN_DESCRIPTION)));
                article.setUrl(cursor.getString(cursor.getColumnIndex(Note.COLUMN_URL)));
                article.setUrlToImage(cursor.getString(cursor.getColumnIndex(Note.COLUMN_URL_TO_IMAGE)));
                article.setContent(cursor.getString(cursor.getColumnIndex(Note.COLUMN_CONTENT)));
                article.setPublishedAt(cursor.getString(cursor.getColumnIndex(Note.COLUMN_PUBLISHED_AT)));

                articles.add(article);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return articles list
        return articles;
    }
}