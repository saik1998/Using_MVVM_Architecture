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
// * Use the {@link LoginFragment# factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {
    private TextInputLayout emailEdit, passEdit;
    private Button login;
    private TextView signhere;
    private AuthViewModels viewModel;
    private NavController navController;

    public void SigninFragment() {
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
                    navController.navigate(R.id.action_loginFragment_to_profileFragment);
                }
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        emailEdit = view.findViewById(R.id.Email1);
        passEdit = view.findViewById(R.id.Password1);
        login = view.findViewById(R.id.login);
        signhere = view.findViewById(R.id.newsignup);

        navController = Navigation.findNavController(view);

        signhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_loginFragment_to_signupFragment);
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEdit.getEditText().getText().toString();
                String pass = passEdit.getEditText().getText().toString();

                if (!email.isEmpty() && !pass.isEmpty()){
                    viewModel.signIn(email , pass);
                }
            }
        });
    }
}