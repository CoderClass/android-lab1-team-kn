package apidez.com.lab1.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import apidez.com.lab1.R;
import apidez.com.lab1.model.Post;

/**
 * Created by nongdenchet on 10/9/16.
 */

public class PostAdapter extends ArrayAdapter<Post> {
    private List<Post> mPosts;
    Context context;


    public PostAdapter(Context context, List<Post> posts) {
        super(context, -1);
        mPosts = posts;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mPosts.size();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
                viewHolder.ivAvatar = (ImageView) convertView.findViewById(R.id.ivAvatar);
                viewHolder.ivImage = (ImageView) convertView.findViewById(R.id.ivImage);
                viewHolder.tvDate = (TextView) convertView.findViewById(R.id.tvDate);
                viewHolder.tvDescription = (TextView) convertView.findViewById(R.id.tvDescription);
                viewHolder.tvUsername = (TextView) convertView.findViewById(R.id.tvUsername);

                convertView.setTag(viewHolder);
        } else {
            Log.d("Null", "AS");
            viewHolder = (ViewHolder) convertView.getTag();
        }

        bindViewHolder(position, viewHolder);

        return convertView;
    }

    private void bindViewHolder(int position, ViewHolder viewHolder) {
        Post post = mPosts.get(position);

        viewHolder.tvDate.setText(post.getDate());
        viewHolder.tvDescription.setText(post.getDescription());
        viewHolder.tvUsername.setText(post.getUsername());

        loadImage(viewHolder.ivImage, post.getImage());
        loadImage(viewHolder.ivAvatar, post.getAvatar());
    }

    private void loadImage(ImageView imageView, String path) {
        Picasso.with(getContext())
                .load(path)
                .placeholder(R.drawable.placeholder)
                .into(imageView);
    }

    public class ViewHolder {
        public TextView tvUsername;
        public TextView tvDescription;
        public TextView tvDate;
        public ImageView ivAvatar;
        public ImageView ivImage;
    }
}
