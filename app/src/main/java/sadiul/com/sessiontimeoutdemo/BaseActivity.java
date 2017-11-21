package sadiul.com.sessiontimeoutdemo;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;

import sadiul.com.sessiontimeoutdemo.app.AppController;

/**
 * Created by Kazi Md. Saidul Email: Kazimdsaidul@gmail.com  Mobile: +8801675349882 on 11/21/17.
 */

public class BaseActivity extends AppCompatActivity {

    private static final String TAG = BaseActivity.class.getName();
    private long lastActivity;


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        checkSession();

        return super.dispatchTouchEvent(ev);

    }

    private void checkSession() {
        if (AppController.isSessionActive){
            lastActivity = System.currentTimeMillis();
            if ( lastActivity > AppController.logoutTime) {
                Log.e(TAG, "auto log out");

                AppController.isSessionActive = false;
                showAutoLogout();

            }else {
                AppController.addSessionTimeoutTime();

            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    public void showAutoLogout(){

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                BaseActivity.this);
        alertDialog.setCancelable(false);
        alertDialog.setTitle("Alert");
        alertDialog
                .setMessage("Session Timeout, Hit ok to go to previous screen.");
        alertDialog.setNegativeButton("OK",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(BaseActivity.this,
                                LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
                                | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);

                        finish();

                        dialog.cancel();
                    }
                });

        alertDialog.show();
    }


}
