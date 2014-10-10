package in.bhardwaj.intenttesting;

import android.content.Context;
import android.content.Intent;

import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentProducer;
import org.apache.http.entity.EntityTemplate;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpRequestHandler;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * Created by akshay on 09/10/14.
 */
public class HomeCommandHandler implements HttpRequestHandler {
    private Context context = null;
    public HomeCommandHandler(Context context) {
        this.context = context;
    }
    @Override
    public void handle(HttpRequest request, HttpResponse response,
                       HttpContext httpContext) throws IOException{
        new Thread(new Runnable(){
            public void run(){
//                while (true){
                    Intent intent = new Intent();
                    intent.setAction("android.provider.SoftKeyboard.A");
                    context.sendBroadcast(intent);
                    System.out.println("Intent firing done");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                }
            }
        }).start();
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Handling");
        HttpEntity entity = new EntityTemplate(new ContentProducer(){
            public void writeTo(final OutputStream outStream) throws IOException{
                OutputStreamWriter writer = new OutputStreamWriter(outStream, "UTF-8");
                String resp = "<html><head></head><body><h1>Home<h1><p>This is the homepage.</p></body></html>";
                writer.write(resp);
                writer.flush();
            }
        });
        response.setHeader("Content-Type", "text/html");
        response.setEntity(entity);

    }

    public Context getContext(){
        return context;
    }
}
