package be.qbmt.dv.devstore;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class StoreFragment extends Fragment {
    private RecyclerView rView;
    private StoreFragmentListener listener;

    public StoreFragment() {
        listener = (StoreFragmentListener)getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store, container, false);
        rView = (RecyclerView)view.findViewById(R.id.recycleView);

        rView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        rView.setAdapter(new ProductAdapter(this.getActivity()));

        return view;
    }



}
