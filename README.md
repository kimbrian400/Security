# Security
Overall, this code demonstrates how to implement a secure biometric authentication process in an Android app, handling both successful and failed authentication attempts and providing fallback options when needed.

## Prerequisites
You will need the following to run this project:
1. A laptop or desktop machine with internet access
2. Android Studio 3.1 and above. All stable Channel (Preferably the latest Stable Release)



## Setting Up
* Clone the Repository from Github
* Open the project folder using Android Studio IDE



### Main Dashboard 
Once you install the app and open it, it opens up the MainActivity.java which quickly calls the BiometricPrompt which in turn, asks you to scan your fingerprint.

This is what it looks like:

<img src="https://github.com/kimbrian400/Security/blob/master/screenshots/001.PNG" width="280"/>  

### Next 
The app sets up the BiometricPrompt instance with a callback to handle authentication results. If authentication succeeds, it calls goToHomeActivity() to navigate to the home screen.

This is what HomeActivity looks like:

<img src="https://github.com/kimbrian400/Security/blob/master/screenshots/002.PNG"/>

### On failing to authenticate using fingerprint
If it fails, it increments the failedAttempts count and, if the maximum number of allowed failed attempts is reached, which is 3, it calls goToPinInputActivity() to navigate to the PIN input screen. Otherwise, it displays a toast message indicating the failure.

This is what it looks like when you enter the wrong fingerprint:

<img src="https://github.com/kimbrian400/Security/blob/master/screenshots/003.PNG"/>

### PinInputActivity

This is what happens when you enter 3 wrong fingerprints:

<img src="https://github.com/kimbrian400/Security/blob/master/screenshots/004.PNG"/>

### Extra Features
* Comming sool

### Enjoy 

