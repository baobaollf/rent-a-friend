package com.rent_a_friend.ui.feed;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.rent_a_friend.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CommentsFragment extends Fragment {
    List<String> username = new ArrayList<>();
    List<String> comments = new ArrayList<>();
    String documentID;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Button uploadCommentButton;
    EditText editText;
    CommentsFragment(String documentID) {
        this.documentID = documentID;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View listItemsView = inflater.inflate(R.layout.fragment_comment, container, false);
        uploadCommentButton = (Button) listItemsView.findViewById(R.id.upload_comment_button);
        editText = (EditText) listItemsView.findViewById(R.id.comment_edit_text);
        RecyclerView recyclerView = listItemsView.findViewById(R.id.comment_recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);


        CommentsRecyclerViewAdapter adapter = new CommentsRecyclerViewAdapter(getContext(), username, comments);

        recyclerView.setAdapter(adapter);
        getFeeds(adapter);
        uploadCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadComment();
            }
        });
        return listItemsView;
    }

    private void uploadComment() {
        String text = editText.getText().toString();
        if (text.length() >= 1) {
            String path = "feeds" + "/" + documentID + "/" + "comments";
            Map<String, Object> comment = new HashMap<>();
            comment.put("comment", text);
            comment.put("time", Calendar.getInstance().getTime());
            comment.put("username", "comment upload user");
            db.collection(path).add(comment);
        } else {
            Toast.makeText(getContext(), "Enter text to upload", Toast.LENGTH_SHORT).show();
        }
        editText.getText().clear();
    }


    private void getFeeds(final CommentsRecyclerViewAdapter adapter) {
        String path = "feeds" + "/" + documentID + "/" + "comments";
        db.collection(path).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    return;
                }
                for (QueryDocumentSnapshot doc : value) {
                    if(doc.get("comment") != null) {
                        comments.add(doc.getString("comment"));
                    }
                    if (doc.get("username") != null) {
                        username.add(doc.getString("username"));
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });
    }
}
