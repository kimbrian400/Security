package com.kimbrian.security;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private Executor executor;
    private BiometricPrompt biometricPrompt;

    private int failedAttempts = 0;
    private static final int MAX_FAILED_ATTEMPTS = 3;


    @SuppressLint("MissingInflatedId")
    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize executor
        executor = Executors.newSingleThreadExecutor();

        // Create a BiometricPrompt instance
        biometricPrompt = new BiometricPrompt(this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                // Fingerprint authentication succeeded; allow access to the app.
                // You can put your app's logic here.
                goToHomeActivity();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                // Fingerprint authentication failed.
                // Increment the failed attempts count
                failedAttempts++;

                // Check if max failed attempts reached
                if (failedAttempts >= MAX_FAILED_ATTEMPTS) {
                    // Max failed attempts reached, take the user to PinInputActivity
                    failedAttempts = 0; // Reset the failed attempts count
                    goToPinInputActivity();
                } else {
                    // Display a message to the user indicating the failure on the UI thread
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "Fingerprint authentication failed. Attempt " + failedAttempts + " of " + MAX_FAILED_ATTEMPTS, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        // Configure the authentication prompt
        BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Fingerprint Authentication")
                .setSubtitle("Unlock the app with your fingerprint")
                .setNegativeButtonText("Cancel")
                .build();

        // Trigger the fingerprint authentication dialog immediately in onCreate
        biometricPrompt.authenticate(promptInfo);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Cancel the fingerprint authentication when the activity is stopped
        biometricPrompt.cancelAuthentication();
    }

    private void goToHomeActivity() {
        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    private void goToPinInputActivity() {
        Intent intent = new Intent(MainActivity.this, PinInputActivity.class);
        startActivity(intent);
        finish();
    }
}