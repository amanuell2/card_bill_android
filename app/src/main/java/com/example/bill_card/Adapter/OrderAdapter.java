package com.example.bill_card.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bill_card.Model.Product;
import com.example.bill_card.R;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    ArrayList<Product> mProducts = new ArrayList<>();
    Context mContext;

    public OrderAdapter(ArrayList<Product> products) {
        mProducts = products;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.bill_rv_row, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Product product = mProducts.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView mTxtOrderItemName;
        TextView mTxtOrderItemCount;
        Button mBtnRemoveItem;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            mTxtOrderItemName = itemView.findViewById(R.id.tv_bill_item_name);
            mTxtOrderItemCount = itemView.findViewById(R.id.tv_bill_item_count);
            mBtnRemoveItem = itemView.findViewById(R.id.btn_bill_sub);
        }

        public void bind(Product product) {
            mTxtOrderItemName.setText(product.getItemName());
            mTxtOrderItemCount.setText(product.getItemCount().toString());
        }
    }
}
