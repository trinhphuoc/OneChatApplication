package com.example.dinhtrinhphuoc.onechatapplication.core.users.add;

import android.content.Context;

import com.google.firebase.auth.FirebaseUser;

/**
 * Created by TrinhPhuoc on 11-Nov-16.
 */

public class AddUserPresenter implements AddUserContract.Presenter, AddUserContract.OnUserDatabaseListener {
    private AddUserContract.View mView;
    private AddUserInteractor mAddUserInteractor;

    public AddUserPresenter(AddUserContract.View view) {
        this.mView = view;
        mAddUserInteractor = new AddUserInteractor(this);
    }

    @Override
    public void addUser(Context context, FirebaseUser firebaseUser) {
        mAddUserInteractor.addUserToDatabase(context, firebaseUser);
    }

    @Override
    public void onSuccess(String message) {
        mView.onAddUserSuccess(message);
    }

    @Override
    public void onFailure(String message) {
        mView.onAddUserFailure(message);
    }
}
