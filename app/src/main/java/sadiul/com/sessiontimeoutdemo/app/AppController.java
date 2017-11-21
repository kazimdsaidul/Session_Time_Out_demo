package sadiul.com.sessiontimeoutdemo.app;

import android.app.Application;

/**
 * Created by Kazi Md. Saidul Email: Kazimdsaidul@gmail.com  Mobile: +8801675349882 on 11/21/17.
 */

public class AppController extends Application {

    public static long logoutTime;
    public static boolean isSessionActive= false;
    public static long SessionTimeConstant = 20000;

    @Override
    public void onCreate() {
        super.onCreate();



    }

    public static  void addSessionTimeoutTime() {
        addTimeOutTime(SessionTimeConstant);
        isSessionActive = true;
    }

    public static void addTimeOutTime(long miniSecound){
        long now = System.currentTimeMillis();
        logoutTime = now + miniSecound;

    }
}
