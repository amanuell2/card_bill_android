package com.example.bill_card.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bill_card.Model.Product;
import com.example.bill_card.R;
import com.example.bill_card.Util.SharedPreferenceUtil;
import com.google.android.gms.common.util.SharedPreferencesUtils;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    ArrayList<Product> mProducts = new ArrayList<>();
    ArrayList<Product> mProductsToShared = new ArrayList<>();
    Context mContext;

    public ProductAdapter(ArrayList<Product> products) {
        mProducts = products;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.rv_row, parent, false);

        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = mProducts.get(position);
        if (product.getItemPrice().equals("sub")) {
            holder.itemView.findViewById(R.id.tv_sub_categorise).setVisibility(View.VISIBLE);
            holder.itemView.findViewById(R.id.tv_push_key).setVisibility(View.GONE);
            holder.itemView.findViewById(R.id.tv_item_name).setVisibility(View.GONE);
            holder.itemView.findViewById(R.id.tv_item_count).setVisibility(View.GONE);
            holder.itemView.findViewById(R.id.tv_item_price).setVisibility(View.GONE);
            holder.itemView.findViewById(R.id.btn_add).setVisibility(View.GONE);
            holder.bind(product);
            return;
        }
        holder.itemView.findViewById(R.id.tv_sub_categorise).setVisibility(View.GONE);
        holder.itemView.findViewById(R.id.tv_push_key).setVisibility(View.VISIBLE);
        holder.itemView.findViewById(R.id.tv_item_name).setVisibility(View.VISIBLE);
        holder.itemView.findViewById(R.id.tv_item_count).setVisibility(View.VISIBLE);
        holder.itemView.findViewById(R.id.tv_item_price).setVisibility(View.VISIBLE);
        holder.itemView.findViewById(R.id.btn_add).setVisibility(View.VISIBLE);
        holder.bind(product);
        holder.itemView.findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                product.setItemCount((product.getItemCount() + 1));
                holder.bind(product);
                mProductsToShared.add(product);
                SharedPreferenceUtil.setPreferenceArrayList(mContext, "ORDER", mProductsToShared);
            }
        });
    }

    private void addToCart(Product product) {
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView mTxtPushKey;
        TextView mTxtSubCategorise;
        TextView mTxtItemName;
        TextView mTxtItemCount;
        TextView mTxtItemPrice;
        Button mBtnAdd;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            mTxtSubCategorise = (TextView) itemView.findViewById(R.id.tv_sub_categorise);
            mTxtPushKey = (TextView) itemView.findViewById(R.id.tv_push_key);
            mTxtItemName = (TextView) itemView.findViewById(R.id.tv_item_name);
            mTxtItemCount = (TextView) itemView.findViewById(R.id.tv_item_count);
            mTxtItemPrice = (TextView) itemView.findViewById(R.id.tv_item_price);
            mBtnAdd = (Button) itemView.findViewById(R.id.btn_add);
        }

        public void bind(Product product) {

            mTxtSubCategorise.setText(product.getItemName());
            mTxtPushKey.setText(product.getPushKey());
            mTxtItemName.setText(product.getItemName());
            mTxtItemCount.setText(product.getItemCount().toString());
            mTxtItemPrice.setText("€" + product.getItemPrice());


        }
    }
}
