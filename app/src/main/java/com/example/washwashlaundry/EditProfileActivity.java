package com.example.washwashlaundry;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditProfileActivity extends AppCompatActivity {
    private EditText nameEditText, phoneEditText, passwordEditText;
    private TextView emailTextView;  // Use TextView instead of EditText for email
    private Button saveButton;
    private FirebaseAuth mAuth;
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        emailTextView = findViewById(R.id.emailText);  // Initialize the TextView
        nameEditText = findViewById(R.id.nameInput);
        phoneEditText = findViewById(R.id.phoneInput);
        saveButton = findViewById(R.id.saveButton);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance("https://washwashlaundry-ef8c4-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Users");

        // Load current user profile
        loadUserProfile();

        saveButton.setOnClickListener(v -> saveProfile());
    }

    private void loadUserProfile() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            String userId = user.getUid();
            database.child(userId).get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    User userData = task.getResult().getValue(User.class);
                    if (userData != null) {
                        nameEditText.setText(userData.getName());
                        emailTextView.setText(userData.getEmail());  // Set the email in TextView
                        phoneEditText.setText(userData.getPhone());
                    }
                }
            });
        }
    }

    private void saveProfile() {
        String newName = nameEditText.getText().toString();
        String newPhone = phoneEditText.getText().toString();

        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            String userId = user.getUid();
            User updatedUser = new User(userId, newName, user.getEmail(), newPhone);
            database.child(userId).setValue(updatedUser).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(EditProfileActivity.this, "Profile updated", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(EditProfileActivity.this, "Failed to update profile", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}