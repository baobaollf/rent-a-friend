package com.rent_a_friend.ui.feed;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.rent_a_friend.R;

import java.util.ArrayList;
import java.util.List;

public class FeedFragment extends Fragment {

    List<String> username = new ArrayList<>();
    List<String> imageUrl = new ArrayList<>();
    List<String> documentID = new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View listItemsView = inflater.inflate(R.layout.fragment_feed, container, false);

        RecyclerView recyclerView = listItemsView.findViewById(R.id.feed_recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);


        FeedRecyclerViewAdapter adapter = new FeedRecyclerViewAdapter(getContext(), username, imageUrl, documentID, getActivity().getSupportFragmentManager());

        recyclerView.setAdapter(adapter);
        getFeeds(adapter);


        return listItemsView;
    }

    private void getFeeds(final FeedRecyclerViewAdapter adapter) {
        db.collection("feeds").orderBy("time", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    return;
                }
                imageUrl.clear();
                username.clear();
                for (QueryDocumentSnapshot doc : value) {
                    if(doc.get("image") != null) {
                        imageUrl.add(doc.getString("image"));
                    }
                    if (doc.get("username") != null) {
                        username.add(doc.getString("username"));
                    }
                    documentID.add(doc.getId());

                }
                adapter.notifyDataSetChanged();
            }
        });
    }
}