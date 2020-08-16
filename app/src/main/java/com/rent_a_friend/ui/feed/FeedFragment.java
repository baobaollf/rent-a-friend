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
import java.util.Map;

import kotlin.collections.ArrayDeque;

public class FeedFragment extends Fragment {

    List<String> username = new ArrayList<>();
    List<String> imageUrl = new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View listItemsView = inflater.inflate(R.layout.fragment_feed, container, false);
        RecyclerView recyclerView = listItemsView.findViewById(R.id.feed_recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);


        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getContext(), username, imageUrl);

        recyclerView.setAdapter(adapter);
        getFeeds(adapter);
        return listItemsView;
    }

    private void getFeeds(final RecyclerViewAdapter adapter) {
        db.collection("feeds").orderBy("time", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {

                    return;
                }
                for (QueryDocumentSnapshot doc : value) {
                    if(doc.get("image") != null) {
                        imageUrl.add(doc.getString("image"));
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