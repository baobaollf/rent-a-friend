package com.rent_a_friend.ui.feed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rent_a_friend.R;

import java.util.List;

public class FeedRecyclerViewAdapter extends RecyclerView.Adapter<FeedRecyclerViewAdapter.ViewHolder> {
    Button seeComments;
    List<String> bitmaps;
    List<String> username;
    List<String> documentID;
    Context context;
    FragmentManager fg;

    FeedRecyclerViewAdapter(Context context, List<String> username, List<String> bitmaps, List<String> documentID, FragmentManager fg) {
        this.bitmaps = bitmaps;
        this.username = username;
        this.documentID = documentID;
        this.context = context;
        this.fg = fg;
    }


    @NonNull
    @Override
    public FeedRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.feed_card, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull FeedRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.username.setText(username.get(position));
        holder.id = documentID.get(position);
        Glide.with(context).load(bitmaps.get(position)).into(holder.mainImage);
    }

    @Override
    public int getItemCount() {
        return username.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mainImage;
        TextView username;
        String id;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            seeComments = itemView.findViewById(R.id.feed_card_comments_icon);
            mainImage = itemView.findViewById(R.id.feed_card_main_image);
            username = itemView.findViewById(R.id.feed_card_user_name);
            seeComments.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fg.beginTransaction().replace(R.id.fragment_container,
                            new CommentsFragment(id)).addToBackStack(null).commit();
                }
            });
        }
    }
}
