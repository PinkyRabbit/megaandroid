public class Note {
    public static final String TABLE_NAME = "articles";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_PAGE_ID = "page_id";
    public static final String COLUMN_AUTHOR = "author";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_URL = "url";
    public static final String COLUMN_URL_TO_IMAGE = "urlToImage";
    public static final String COLUMN_CONTENT = "content";
    public static final String COLUMN_PUBLISHED_AT = "publishedAt";

    private int id;
    private String pageId;
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String content;
    private String publishedAt;

    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_PAGE_ID + " TEXT,"
                    + COLUMN_AUTHOR + " TEXT,"
                    + COLUMN_TITLE + " TEXT,"
                    + COLUMN_DESCRIPTION + " TEXT,"
                    + COLUMN_URL + " TEXT,"
                    + COLUMN_URL_TO_IMAGE + " TEXT,"
                    + COLUMN_CONTENT + " TEXT,"
                    + COLUMN_PUBLISHED_AT + " TEXT"
                    + ")";

    public Article() {
    }

    public Article(
            int id,
            String pageId,
            String author,
            String title,
            String description,
            String url,
            String urlToImage,
            String content,
            String publishedAt
    ) {
        this.id = id;
        this.pageId = pageId;
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.content = content;
        this.publishedAt = publishedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }
}