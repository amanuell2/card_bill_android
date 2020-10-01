package com.example.bill_card;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.example.bill_card.Adapter.TabPagerAdapter;
import com.example.bill_card.Model.Product;
import com.example.bill_card.Util.FirebaseUtil;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

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

        FirebaseUtil.openFbReference("QmdbYI28MyP0NCFJJvt2ASH7s2K3");
        mFirebaseDatabase = FirebaseUtil.sFirebaseDatabase;
        mDatabaseReference = FirebaseUtil.sDatabaseReference;
        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

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

    private TabPagerAdapter createCardAdapter(int size, ArrayList<ArrayList> productContainer) {
        TabPagerAdapter adapter = new TabPagerAdapter(this, size, productContainer);
        return adapter;
    }
}