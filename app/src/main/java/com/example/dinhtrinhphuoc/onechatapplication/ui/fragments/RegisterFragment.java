package com.example.dinhtrinhphuoc.onechatapplication.ui.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dinhtrinhphuoc.onechatapplication.R;
import com.example.dinhtrinhphuoc.onechatapplication.core.registration.RegisterContract;
import com.example.dinhtrinhphuoc.onechatapplication.core.registration.RegisterPresenter;
import com.example.dinhtrinhphuoc.onechatapplication.core.users.add.AddUserContract;
import com.example.dinhtrinhphuoc.onechatapplication.core.users.add.AddUserPresenter;
import com.example.dinhtrinhphuoc.onechatapplication.ui.activities.UserListingActivity;
import com.google.firebase.auth.FirebaseUser;

public class RegisterFragment extends Fragment implements View.OnClickListener, RegisterContract.View, AddUserContract.View {
    private static final String TAG = RegisterFragment.class.getSimpleName();

    private RegisterPresenter mRegisterPresenter;
    private AddUserPresenter mAddUserPresenter;

    private EditText mETxtEmail, mETxtPassword;
    private Button mBtnRegister;

    private ProgressDialog mProgressDialog;

    public static RegisterFragment newInstance() {
        Bundle args = new Bundle();
        RegisterFragment fragment = new RegisterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.register_fragment, container, false);
        bindViews(fragmentView);
        return fragmentView;
    }

    private void bindViews(View view) {
        mETxtEmail = (EditText) view.findViewById(R.id.edit_text_email_id);
        mETxtPassword = (EditText) view.findViewById(R.id.edit_text_password);
        mBtnRegister = (Button) view.findViewById(R.id.button_register);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {
        mRegisterPresenter = new RegisterPresenter(this);
        mAddUserPresenter = new AddUserPresenter(this);

        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setTitle(getString(R.string.loading));
        mProgressDialog.setMessage(getString(R.string.please_wait));
        mProgressDialog.setIndeterminate(true);

        mBtnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();

        switch (viewId) {
            case R.id.button_register:
                onRegister(view);
                break;
        }
    }

    private void onRegister(View view) {
        String emailId = mETxtEmail.getText().toString().trim();
        String password = mETxtPassword.getText().toString().trim();

        if (!TextUtils.isEmpty(emailId) && !TextUtils.isEmpty(password)) {
            mRegisterPresenter.register(getActivity(), emailId, password);
            mProgressDialog.show();
        } else {
            Toast.makeText(getActivity(), "can't be blank", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onRegistrationSuccess(FirebaseUser firebaseUser) {
        mProgressDialog.setMessage(getString(R.string.adding_user_to_db));
        Toast.makeText(getActivity(), "Registration Successful!", Toast.LENGTH_SHORT).show();
        mAddUserPresenter.addUser(getActivity().getApplicationContext(), firebaseUser);
    }

    @Override
    public void onRegistrationFailure(String message) {
        mProgressDialog.dismiss();
        mProgressDialog.setMessage(getString(R.string.please_wait));
        Log.e(TAG, "onRegistrationFailure: " + message);
        Toast.makeText(getActivity(), "Registration failed!+\n" + message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAddUserSuccess(String message) {
        mProgressDialog.dismiss();
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        UserListingActivity.startActivity(getActivity(),
                Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    @Override
    public void onAddUserFailure(String message) {
        mProgressDialog.dismiss();
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
