package com.example.dinhtrinhphuoc.onechatapplication.core.registration;

import android.app.Activity;

import com.google.firebase.auth.FirebaseUser;

/**
 * Created by TrinhPhuoc on 11-Nov-16.
 */

public interface RegisterContract {
    interface View {
        void onRegistrationSuccess(FirebaseUser firebaseUser);

        void onRegistrationFailure(String message);
    }

    interface Presenter {
        void register(Activity activity, String email, String password);
    }

    interface Interactor {
        void performFirebaseRegistration(Activity activity, String email, String password);
    }

    interface OnRegistrationListener {
        void onSuccess(FirebaseUser firebaseUser);

        void onFailure(String message);
    }
}
