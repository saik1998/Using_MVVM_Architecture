package com.firstapp.using_mvvm_architecture.viewmodels;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.firstapp.using_mvvm_architecture.models.AuthRepository;
import com.google.firebase.auth.FirebaseUser;

public class AuthViewModels extends AndroidViewModel {

    private AuthRepository authRepository;
    private MutableLiveData<FirebaseUser> userdata;



    public MutableLiveData<FirebaseUser> getUserdata() {
        return userdata;
    }

    private MutableLiveData<Boolean> loggedstatus;

    public MutableLiveData<Boolean> getLoggedstatus() {
        return loggedstatus;
    }

    public AuthViewModels(@NonNull Application application1) {
        super(application1);

        authRepository=new AuthRepository(application1);

        userdata=authRepository.getFirebaseUserMutableLiveData();
        loggedstatus=authRepository.getUserLoggedMutableLiveData();
    }
    public void register(String email, String password) {
        authRepository.register(email, password);
    }
    public void signIn(String email, String password) {
        authRepository.register(email, password);
    }
    public void signOut() {
        authRepository.signOut();

    }
}
//
//    public void register(String email , String pass){
//        repository1.register(email, pass);
//    }
//    public void signIn(String email , String pass){
//        repository1.login(email, pass);
//    }
//    public void signOut(){
//
//        repository1.signOut();
//    }



