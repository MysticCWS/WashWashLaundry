package com.example.washwashlaundry;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
    private EditText email, password, name, phone;
    private FirebaseAuth mAuth;
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        Button signUpButton = findViewById(R.id.signUpButton);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance("https://washwashlaundry-ef8c4-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Users");

        signUpButton.setOnClickListener(v -> signUpUser());
    }

    private void signUpUser() {
        String userEmail = email.getText().toString().trim();
        String userPassword = password.getText().toString().trim();
        String userName = name.getText().toString().trim();
        String userPhone = phone.getText().toString().trim();

        if (userEmail.isEmpty() || userPassword.isEmpty() || userName.isEmpty() || userPhone.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                FirebaseUser firebaseUser = mAuth.getCurrentUser();
                if (firebaseUser != null) {
                    String userId = firebaseUser.getUid();
                    User user = new User(userId, userName, userEmail, userPhone);
                    database.child(userId).setValue(user);

                    startActivity(new Intent(SignUpActivity.this, ServicesActivity.class));
                    finish();
                }
            } else {
                Toast.makeText(SignUpActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
