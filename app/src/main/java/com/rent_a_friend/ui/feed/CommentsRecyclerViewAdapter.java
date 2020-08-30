package com.rent_a_friend.ui.feed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rent_a_friend.R;

import java.util.List;

public class CommentsRecyclerViewAdapter extends RecyclerView.Adapter<CommentsRecyclerViewAdapter.ViewHolder>{
    List<String> comments;
    List<String> username;
    Context context;
    public CommentsRecyclerViewAdapter(Context context, List<String> username, List<String> comments) {
        this.context = context;
        this.comments = comments;
        this.username = username;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.comment_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.comment.setText(comments.get(position));
        holder.username.setText(username.get(position));
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView username;
        TextView comment;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            comment = itemView.findViewById(R.id.comment_card_comment);
            username = itemView.findViewById(R.id.comment_card_username);
        }
    }
}
