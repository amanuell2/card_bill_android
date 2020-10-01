package com.example.bill_card.Util;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class FirebaseUtil {
    public static FirebaseDatabase sFirebaseDatabase;
    public static DatabaseReference sDatabaseReference;
    public static FirebaseUtil sFirebaseUtil;

    private FirebaseUtil() {
    }

    ;

    public static void openFbReference(String ref) {
        if (sFirebaseUtil == null) {
            sFirebaseUtil = new FirebaseUtil();
            sFirebaseDatabase = FirebaseDatabase.getInstance();
        }
        sDatabaseReference = sFirebaseDatabase.getReference().getRoot();
    }
}
