package com.example.handi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {

    Button login_button;
    EditText username;
    EditText password;
    TextView signup;
    CentralStorage cstorage ;
    String un,pw;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Storing data into SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);

// Creating an Editor object to edit(write to the file)
        SharedPreferences.Editor myEdit = sharedPreferences.edit();

// Storing the key and its value as the data fetched from edittext

        login_button=findViewById(R.id.login_button);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        signup = findViewById(R.id.signup);
        mAuth=FirebaseAuth.getInstance();
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                un=String.valueOf(username.getText()).trim();
                pw=String.valueOf(password.getText()).trim();
                if(un.isEmpty()){
                    username.setError("Enter valid Email Address");
                }
                else if(pw.isEmpty()){
                    password.setError("Enter valid password");
                }




                mAuth.signInWithEmailAndPassword(un, pw)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    /** if if have used email verification
                                     * then
                                     * check email verified or not
                                     * otherwise skip this part, directly inform user or route to dashboard
                                     */
                                    FirebaseUser fUser = mAuth.getCurrentUser();
                                    String userID = mAuth.getUid(); // get whose email to verify
                                    //boolean check = fUser.isEmailVerified();
                                    //if (check) {
                                    // if email is verified

                                    /**
                                     if required, here you need to store the user for session purpose
                                     write up the code yourself accordingly
                                     */
                                    Toast.makeText(login.this, "LOGIN DONE !", Toast.LENGTH_SHORT).show();
                                    myEdit.putString("name", userID.toString());

// Once the changes have been made,
// we need to commit to apply those changes made,
// otherwise, it will throw an error
                                    myEdit.commit();
                                    Intent in = new Intent(login.this,MainActivity.class);
                                    startActivity(in);
                                    login.this.finish();
                                    /*} else {
                                        // .. in case email is not verified
                                        Toast.makeText(login_page.this,
                                                "Please verify your email first!!!",
                                                Toast.LENGTH_LONG).show();
                                    }*/
                                } else {
                                    Toast.makeText(login.this,
                                            "SignIn failed",
                                            Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(login.this,signup.class);
                startActivity(in);
                login.this.finish();
            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();

        // Creating a shared pref object
        // with a file name "MySharedPref"
        // in private mode
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();

        // write all the data entered by the user in SharedPreference and apply
        myEdit.putString("name", username.getText().toString());
        myEdit.apply();
    }
}