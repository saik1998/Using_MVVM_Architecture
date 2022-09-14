package com.firstapp.using_mvvm_architecture.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firstapp.using_mvvm_architecture.R;
import com.firstapp.using_mvvm_architecture.viewmodels.AuthViewModels;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class SignupFragment extends Fragment {
     TextInputLayout emailEdit, passEdit,fnameEdit,mobileedt;
     TextView signuphere;
     Button signupclick;
     AuthViewModels viewModel;
     NavController navController;

    public SignupFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this , (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory
                .getInstance(getActivity().getApplication())).get(AuthViewModels.class);
        viewModel.getUserdata().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if (firebaseUser != null){
                    navController.navigate(R.id.action_signupFragment_to_loginFragment);
                }
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fnameEdit = view.findViewById(R.id.username1);
        mobileedt = view.findViewById(R.id.mobile1);
        emailEdit=view.findViewById(R.id.signemail1);
        passEdit=view.findViewById(R.id.signinpassword1);
        //text button id
        signuphere = view.findViewById(R.id.newlogin);
        //signup button click function
        signupclick = view.findViewById(R.id.signup);

        navController = Navigation.findNavController(view);

        signuphere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_signupFragment_to_loginFragment);
            }
        });
        signupclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEdit.getEditText().getText().toString();
                String pass = passEdit.getEditText().getText().toString();

                if (!email.isEmpty() && !pass.isEmpty()){
                    viewModel.register(email , pass);
                }
            }
        });
    }
    }
