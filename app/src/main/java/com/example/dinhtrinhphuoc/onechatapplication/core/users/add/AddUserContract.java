package com.example.dinhtrinhphuoc.onechatapplication.core.users.add;

import android.content.Context;

import com.google.firebase.auth.FirebaseUser;

/**
 * Created by TrinhPhuoc on 11-Nov-16.
 */

public interface AddUserContract {
    interface View {
        void onAddUserSuccess(String message);

        void onAddUserFailure(String message);
    }

    interface Presenter {
        void addUser(Context context, FirebaseUser firebaseUser);
    }

    interface Interactor {
        void addUserToDatabase(Context context, FirebaseUser firebaseUser);
    }

    interface OnUserDatabaseListener {
        void onSuccess(String message);

        void onFailure(String message);
    }
}
