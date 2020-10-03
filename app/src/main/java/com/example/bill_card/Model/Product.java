package com.example.bill_card.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {
    private String btw_tarif;
    private String foodType;
    private Long itemCount;
    private String itemName;
    private String itemPrice;
    private String itemType;
    private String pushKey;
    private String action;
    private Boolean available;

    public Product() {
    }

    public Product(String btw_tarif, String foodType, Long itemCount, String itemName, String itemPrice, String itemType, String pushKey, String action, Boolean available) {
        this.setBtw_tarif(btw_tarif);
        this.setFoodType(foodType);
        this.setItemCount(itemCount);
        this.setItemName(itemName);
        this.setItemPrice(itemPrice);
        this.setItemType(itemType);
        this.setPushKey(pushKey);
        this.setAction(action);
        this.setAvailable(available);
    }


    protected Product(Parcel in) {
        btw_tarif = in.readString();
        foodType = in.readString();
        if (in.readByte() == 0) {
            itemCount = null;
        } else {
            itemCount = in.readLong();
        }
        itemName = in.readString();
        itemPrice = in.readString();
        itemType = in.readString();
        pushKey = in.readString();
        action = in.readString();
        byte tmpAvailable = in.readByte();
        available = tmpAvailable == 0 ? null : tmpAvailable == 1;
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public boolean equals(Product product) {
        if (product != null) {
            return (product.pushKey.equals(this.pushKey) && product.pushKey == this.pushKey);
        } else {
            return false;
        }
    }

    public String getBtw_tarif() {
        return btw_tarif;
    }

    public void setBtw_tarif(String btw_tarif) {
        this.btw_tarif = btw_tarif;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public Long getItemCount() {
        return itemCount;
    }

    public void setItemCount(Long itemCount) {
        this.itemCount = itemCount;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getPushKey() {
        return pushKey;
    }

    public void setPushKey(String pushKey) {
        this.pushKey = pushKey;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(btw_tarif);
        parcel.writeString(foodType);
        if (itemCount == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(itemCount);
        }
        parcel.writeString(itemName);
        parcel.writeString(itemPrice);
        parcel.writeString(itemType);
        parcel.writeString(pushKey);
        parcel.writeString(action);
        parcel.writeByte((byte) (available == null ? 0 : available ? 1 : 2));
    }


}
