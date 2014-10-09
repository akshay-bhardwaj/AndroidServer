package in.bhardwaj.intenttesting;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.StrictMode;
import android.util.Log;

/**
 * Created by akshay on 09/10/14.
 */
public class WebServerService extends Service {
    private WebServer server = null;

    @Override
    public void onCreate(){
        Log.i("HttpService", "Creating and starting the web server");
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        final Context contextT = this;
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i("HttpService", "super create done");
                server = new WebServer(contextT);
                Log.i("HttpService", "server object created");
                server.startServer();
                Log.i("HttpService", "startServer done");
            }
        }).start();
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy(){
        Log.i("HttpService", "Destroying httpService");
        server.stopServer();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent){
        return null;
    }
}
