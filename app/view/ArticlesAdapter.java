
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import info.androidhive.sqlite.R;
import info.androidhive.sqlite.database.model.Article;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.MyViewHolder> {

    private Context context;
    private List<Article> articlesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView author;
        public TextView title;
        public TextView description;
        public TextView url;
        public TextView urlToImage;
        public TextView content;
        public TextView publishedAt;

        public MyViewHolder(View view) {
            super(view);
            author = view.findViewById(R.id.author);
            title = view.findViewById(R.id.title);
            description = view.findViewById(R.id.description);
            url = view.findViewById(R.id.url);
            urlToImage = view.findViewById(R.id.urlToImage);
            content = view.findViewById(R.id.content);
            publishedAt = view.findViewById(R.id.publishedAt);
        }
    }

    public ArticlesAdapter(Context context, List<Article> articlesList) {
        this.context = context;
        this.articlesList = articlesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.article_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Article article = articlesList.get(position);

        holder.article.setText(article.getArticle());

        // Displaying dot from HTML character code
        holder.dot.setText(Html.fromHtml("&#8226;"));

        // Formatting and displaying getPublishedAt
        holder.publishedAt.setText(formatDate(article.getPublishedAt()));
    }

    @Override
    public int getItemCount() {
        return articlesList.size();
    }

    /**
     * Formatting timestamp to `MMM d` format
     * Input: 2018-02-21 00:15:42
     * Output: Feb 21
     */
    private String formatDate(String dateStr) {
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = fmt.parse(dateStr);
            SimpleDateFormat fmtOut = new SimpleDateFormat("MMM d");
            return fmtOut.format(date);
        } catch (ParseException e) {

        }

        return "";
    }
}