package com.example.bill_card;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import com.example.bill_card.Adapter.OrderAdapter;
import com.example.bill_card.Adapter.TabPagerAdapter;
import com.example.bill_card.Model.Product;
import com.example.bill_card.Util.FirebaseUtil;
import com.example.bill_card.Util.SharedPreferenceUtil;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference mDatabaseReference;
    private FirebaseDatabase mFirebaseDatabase;
    private ChildEventListener mChildEventListener;
    private TabLayout mTabLayout;
    ViewPager2 mViewPager2;
    ArrayList<ArrayList> productListContainer = new ArrayList<ArrayList>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mViewPager2 = (ViewPager2) findViewById(R.id.view_pager);
        // get the bottom sheet view

        setupBottomSheet();


        FirebaseUtil.openFbReference("card-bill-android");
        mFirebaseDatabase = FirebaseUtil.sFirebaseDatabase;
        mDatabaseReference = FirebaseUtil.sDatabaseReference;
        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                // organise tab and data and parse item data
                if (snapshot.getKey().equals("QmdbYI28MyP0NCFJJvt2ASH7s2K3")) {
                    getTabsAndData(snapshot);
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        mDatabaseReference.addChildEventListener(mChildEventListener);


    }

    private void setupBottomSheet() {
        LinearLayout llBottomSheet = (LinearLayout) findViewById(R.id.bottom_sheet);

        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(llBottomSheet);

        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        bottomSheetBehavior.setPeekHeight(80);

        bottomSheetBehavior.setHideable(false);


        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

                if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                    RecyclerView recyclerView = findViewById(R.id.bill_rv);
                    Context context = getApplicationContext();
                    ArrayList<Product> products = SharedPreferenceUtil.getPreferenceArrayList(context, "ORDER");
                    final OrderAdapter orderAdapter = new OrderAdapter(products);
                    recyclerView.setAdapter(orderAdapter);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                    recyclerView.setLayoutManager(linearLayoutManager);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

    }

    private void getTabsAndData(DataSnapshot snapshot) {

        String[] titles = new String[(int) snapshot.getChildrenCount()];
        int i = 0;

        for (DataSnapshot sp : snapshot.getChildren()) {
            titles[i] = sp.getKey();
            ArrayList<Product> productArrayList = new ArrayList<>();
            for (DataSnapshot snapshot1 : sp.getChildren()) {
                Product product = snapshot1.getValue(Product.class);
                productArrayList.add(product);
            }
            productListContainer.add(productArrayList);
            i++;
        }

        mViewPager2.setAdapter(createCardAdapter(titles.length, productListContainer));
        mViewPager2.setOffscreenPageLimit(1);
        new TabLayoutMediator(mTabLayout, mViewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(titles[position]);
            }
        }).attach();

    }

    private TabPagerAdapter createCardAdapter(int size, ArrayList<ArrayList> productContainer) {
        TabPagerAdapter adapter = new TabPagerAdapter(this, size, productContainer);
        return adapter;
    }


}