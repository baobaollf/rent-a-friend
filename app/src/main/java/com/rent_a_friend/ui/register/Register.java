package com.rent_a_friend.ui.register;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.rent_a_friend.MainActivity;
import com.rent_a_friend.R;
import com.rent_a_friend.ui.login.Login;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    private static final String TAG = "Register";
    private static final String E_MAIL = "Email";
    private static final String FULL_NAME = "FName";
    private static final String PHONE = "Phone";
    EditText UserName, FullName;
    EditText Password;
    EditText Phone;
    Button Register;
    TextView loginBtn;
    FirebaseAuth fAuth = FirebaseAuth.getInstance();
    FirebaseFirestore fstore = FirebaseFirestore.getInstance();


    String userID;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        FullName = (EditText) findViewById(R.id.fullName);
        UserName = (EditText) findViewById(R.id.username);
        Password = (EditText) findViewById(R.id.password);
        Phone = (EditText) findViewById(R.id.phone);
        Register = (Button) findViewById(R.id.register);
        loginBtn = findViewById(R.id.createText);


        //if user already registered sent to login page
//        if(fAuth.getCurrentUser() != null){
//            startActivity(new Intent(getApplicationContext(),Login.class));
//            finish();
//        }

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //extract values from edittext
                final String uemail = UserName.getText().toString().trim();
                String upassword = Password.getText().toString().trim();
                final String FName = FullName.getText().toString();
                final String Phn = Phone.getText().toString();

                if (TextUtils.isEmpty(uemail)) {
                    UserName.setError("Email is Required");
                    return;
                }

                if (TextUtils.isEmpty(upassword)) {
                    Password.setError("Password is Required");
                    return;
                }

                if (upassword.length() < 6) {
                    Password.setError("Password Must be At least 6 characters");
                    return;
                }

                //register the user in firebase
                fAuth.createUserWithEmailAndPassword(uemail, upassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Register.this, "User Created", Toast.LENGTH_SHORT).show();
                            userID = fAuth.getCurrentUser().getUid();

                            Map<String, Object> user = new HashMap<>();
                            user.put(FULL_NAME, FName);
                            user.put(E_MAIL, uemail);
                            user.put(PHONE, Phn);
                            fstore.collection("Users").document("user created").set(user)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Toast.makeText(com.rent_a_friend.ui.register.Register.this, "User has been created", Toast.LENGTH_SHORT).show();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(com.rent_a_friend.ui.register.Register.this, "Error!", Toast.LENGTH_SHORT).show();
                                            Log.d(TAG, e.toString());
                                        }
                                    });
                            startActivity(new Intent(getApplicationContext(), Login.class));
                        } else {
                            Toast.makeText(Register.this, "Error! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


                loginBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(), Login.class));
                    }
                });

            }

        });
    }
}
