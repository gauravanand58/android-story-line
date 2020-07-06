package com.wattpad.storyline;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class StoryListAdapter extends RecyclerView.Adapter<StoryListAdapter.StoryViewholder> {
    private final LayoutInflater mInflater;
    private List<Story> mStories;
    private Context mContext;

    public StoryListAdapter(Context context){
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    class StoryViewholder extends RecyclerView.ViewHolder{
        private ImageView storyCoverImage;
        private TextView storyTitle;
        private TextView storyAuthor;

        public StoryViewholder(@NonNull View itemView) {
            super(itemView);
            storyCoverImage = itemView.findViewById(R.id.story_cover_image);
            storyTitle = itemView.findViewById(R.id.story_title);
            storyAuthor = itemView.findViewById(R.id.story_author);
        }
    }

    @NonNull
    @Override
    public StoryViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.story_item_view, parent, false);
        return new StoryViewholder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StoryViewholder holder, int position) {
        if (mStories != null) {
            Story currentStory = mStories.get(position);
            Glide.with(mContext)
                    .load(currentStory.getCoverImage())
                    .apply(new RequestOptions().error(R.drawable.noimg))
                    .transition(withCrossFade())
                    .into(holder.storyCoverImage);
            holder.storyTitle.setText(currentStory.getTitle());
            holder.storyAuthor.setText(currentStory.getUser().getUserFullName());
        } else {
            holder.storyTitle.setText("No Word");
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        if(mStories != null){
            return mStories.size();
        } else{
            return 0;
        }
    }

    void setStories(List<Story> stories){
        mStories = stories;
        notifyDataSetChanged();
    }
}