package com.rent_a_friend.ui.feed;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.rent_a_friend.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CommentsFragment extends Fragment {
    List<String> username = new ArrayList<>();
    List<String> comments = new ArrayList<>();
    String documentID;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    CommentsFragment(String documentID) {
        this.documentID = documentID;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View listItemsView = inflater.inflate(R.layout.fragment_comment, container, false);
        RecyclerView recyclerView = listItemsView.findViewById(R.id.comment_recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);


        CommentsRecyclerViewAdapter adapter = new CommentsRecyclerViewAdapter(getContext(), username, comments);

        recyclerView.setAdapter(adapter);
        getFeeds(adapter);
        return listItemsView;
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
