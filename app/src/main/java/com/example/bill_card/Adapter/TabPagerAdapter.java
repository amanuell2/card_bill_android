package com.example.bill_card.Adapter;

import android.os.Parcelable;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.bill_card.Fragment.CardFragment;
import com.example.bill_card.Model.Product;

import java.util.ArrayList;


public class TabPagerAdapter extends FragmentStateAdapter {
    private int numOfTab;

    ArrayList productListContainer = new ArrayList<ArrayList>();

    public TabPagerAdapter(@NonNull FragmentActivity fragmentActivity, int numOfTab, ArrayList productArrayListContainer) {
        super(fragmentActivity);
        this.numOfTab = numOfTab;
        this.productListContainer = productArrayListContainer;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        return CardFragment.newInstance(position, (ArrayList<? extends Parcelable>) productListContainer);
    }

    @Override
    public int getItemCount() {
        return numOfTab;
    }
}
