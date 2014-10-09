package in.bhardwaj.intenttesting;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//
//        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_my);
        Log.i("onCreate", "Going to start server");
//        new Thread(new Runnable(){
//            public void run(){
//                while (true){
//                    Intent intent = new Intent();
//                    intent.setAction("android.provider.SoftKeyboard.A");
//                    sendBroadcast(intent);
//                    Log.d("onCreate", "Intent firing done");
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();
        Log.e("MyActivity", "Going to start server");
        new Thread(new Runnable(){
            @Override
            public void run(){
                try {
                    Context context = getApplicationContext();
                    Intent webServerService = new Intent(context, WebServerService.class);
                    context.startService(webServerService);
                    Log.e("MyActivity", "Server started");
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
//        Context context = getApplicationContext();
//        Intent webServerService = new Intent(context, WebServerService.class);
//        context.startService(webServerService);
//        Log.e("MyActivity", "Server started");


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
