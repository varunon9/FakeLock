package me.varunon9.fakelock;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;

import me.varunon9.fakelock.utility.HideAppsPreferenceUtility;

public class LockScreenActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText inputPasswordEditText;
    private Vibrator vibrator;
    private TextView headingTextView;
    private TextView subHeadingTextView;
    private TextView quoteTextView;
    private boolean isLockLinearLayoutVisible = false;
    private LinearLayout lockLinearLayout;
    private Map<String, ?> hiddenAppsMap;
    private PackageManager packageManager;

    // settings preferences
    private SharedPreferences settingsPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set up our Lockscreen
        makeFullScreen();
        startService(new Intent(this,LockScreenService.class));

        setContentView(R.layout.activity_lock_screen);

        settingsPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        Button button0 = (Button) findViewById(R.id.button0);
        button0.setOnClickListener(this);
        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(this);
        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(this);
        Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(this);
        Button button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(this);
        Button button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(this);
        Button button6 = (Button) findViewById(R.id.button6);
        button6.setOnClickListener(this);
        Button button7 = (Button) findViewById(R.id.button7);
        button7.setOnClickListener(this);
        Button button8 = (Button) findViewById(R.id.button8);
        button8.setOnClickListener(this);
        Button button9 = (Button) findViewById(R.id.button9);
        button9.setOnClickListener(this);

        ImageButton buttonEnter = (ImageButton) findViewById(R.id.buttonEnter);
        buttonEnter.setOnClickListener(this);
        ImageButton buttonBack = (ImageButton) findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(this);

        inputPasswordEditText = (EditText) findViewById(R.id.inputPasswordEditText);

        vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);

        lockLinearLayout = (LinearLayout) findViewById(R.id.lockLinearLayout);

        headingTextView = (TextView) findViewById(R.id.headingTextView);
        subHeadingTextView = (TextView) findViewById(R.id.subHeadingTextView);
        quoteTextView = (TextView) findViewById(R.id.quoteTextView);

        // setting headings and quote
        String heading = settingsPreferences.getString("heading_preference", null);
        if (heading != null && !heading.equals("")) {
            headingTextView.setText(heading);
        }

        String subheading = settingsPreferences.getString("subheading_preference", null);
        if (subheading != null && !subheading.equals("")) {
            subHeadingTextView.setText(subheading);
        }

        String quote = settingsPreferences.getString("quote_preference", null);
        if (quote != null && !quote.equals("")) {
            quoteTextView.setText(quote);
        }

        quoteTextView.setOnTouchListener(new View.OnTouchListener() {

            private GestureDetector gestureDetector = new GestureDetector(new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onDoubleTap(MotionEvent e) {
                    if (isLockLinearLayoutVisible) {
                        lockLinearLayout.setVisibility(View.GONE);
                    } else {
                        lockLinearLayout.setVisibility(View.VISIBLE);
                    }
                    isLockLinearLayoutVisible = !isLockLinearLayoutVisible;
                    playVibration(100);
                    return super.onDoubleTap(e);
                }
            });

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                return true;
            }
        });

        packageManager = getPackageManager();

        // hiding apps
        hiddenAppsMap =
                new HideAppsPreferenceUtility(getApplicationContext()).getAllHiddenAppsMap();
        /*for (Map.Entry<String, ?> app : hiddenAppsMap.entrySet()) {
            String packageName = app.getValue().toString();
            System.out.println(packageName);
            ComponentName componentName =
                    new ComponentName(packageName, packageName + ".MainActivity.class");
            packageManager.setComponentEnabledSetting(componentName,
                    PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
        }*/
    }

    /**
     * A simple method that sets the screen to fullscreen.  It removes the Notifications bar,
     *   the Actionbar and the virtual keys (if they are on the phone)
     */
    public void makeFullScreen() {
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if(Build.VERSION.SDK_INT < 19) { // View.SYSTEM_UI_FLAG_IMMERSIVE is only on API 19+
            this.getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        } else {
            this.getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE);
        }
    }

    @Override
    public void onBackPressed() {
        return; //Do nothing!
    }

    private void unlockScreen(boolean isPasswordRight) {
        if (isPasswordRight) {
            finish(); // closing activity
        }

        // going to home screen
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button0: {
                playVibration(50);
                String password = inputPasswordEditText.getText().toString();
                password += "0";
                inputPasswordEditText.setText(password);
                break;
            }

            case R.id.button1: {
                playVibration(50);
                String password = inputPasswordEditText.getText().toString();
                password += "1";
                inputPasswordEditText.setText(password);
                break;
            }

            case R.id.button2: {
                playVibration(50);
                String password = inputPasswordEditText.getText().toString();
                password += "2";
                inputPasswordEditText.setText(password);
                break;
            }

            case R.id.button3: {
                playVibration(50);
                String password = inputPasswordEditText.getText().toString();
                password += "3";
                inputPasswordEditText.setText(password);
                break;
            }

            case R.id.button4: {
                playVibration(50);
                String password = inputPasswordEditText.getText().toString();
                password += "4";
                inputPasswordEditText.setText(password);
                break;
            }

            case R.id.button5: {
                playVibration(50);
                String password = inputPasswordEditText.getText().toString();
                password += "5";
                inputPasswordEditText.setText(password);
                break;
            }

            case R.id.button6: {
                playVibration(50);
                String password = inputPasswordEditText.getText().toString();
                password += "6";
                inputPasswordEditText.setText(password);
                break;
            }

            case R.id.button7: {
                playVibration(50);
                String password = inputPasswordEditText.getText().toString();
                password += "7";
                inputPasswordEditText.setText(password);
                break;
            }

            case R.id.button8: {
                playVibration(50);
                String password = inputPasswordEditText.getText().toString();
                password += "8";
                inputPasswordEditText.setText(password);
                break;
            }

            case R.id.button9: {
                playVibration(50);
                String password = inputPasswordEditText.getText().toString();
                password += "9";
                inputPasswordEditText.setText(password);
                break;
            }

            case R.id.buttonEnter: {
                playVibration(200);
                String password = inputPasswordEditText.getText().toString();
                checkAndUnlockScreen(password);
                break;
            }

            case R.id.buttonBack: {
                playVibration(50);
                String password = inputPasswordEditText.getText().toString();
                if (password.length() > 0) {
                    password = password.substring(0, password.length() - 1);
                    inputPasswordEditText.setText(password);
                }
                break;
            }
        }
    }

    private void playVibration(long duration) {
        try {
            vibrator.vibrate(duration);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * If password is right-
     * Unhide selected apps, finish LockScreen Activity and go to home screen
     * else go to home screen
     * @param password
     */
    private void checkAndUnlockScreen(String password) {
        boolean isPasswordRight = false;
        String pin = settingsPreferences.getString("password_preference", "");

        // also true when no password is set
        if (pin.equals(password) || pin.equals("")) {
            isPasswordRight = true;
        }
        unlockScreen(isPasswordRight);
    }
}
