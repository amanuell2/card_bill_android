package com.example.bill_card.Fragment;


import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.example.bill_card.Adapter.ProductAdapter;
import com.example.bill_card.Model.Product;
import com.example.bill_card.R;

import java.util.ArrayList;


public class CardFragment extends Fragment {

    private static final String ARG_COUNT = "param1";
    private static final String ARG_PROD = "param2";
    private Integer counter;
    private ArrayList<? extends Parcelable> mProducts;


    public CardFragment() {
        // Required empty public constructor
    }

    public static CardFragment newInstance(Integer counter, ArrayList<? extends Parcelable> products) {
        CardFragment fragment = new CardFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COUNT, counter);
        args.putParcelableArrayList(ARG_PROD, (ArrayList<? extends Parcelable>) products);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            counter = getArguments().getInt(ARG_COUNT);
            mProducts = getArguments().getParcelableArrayList(ARG_PROD);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.rv_fragment);
        final ProductAdapter pagerAdapter = new ProductAdapter((ArrayList<Product>) mProducts.get(counter));
        recyclerView.setAdapter(pagerAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);


    }
}