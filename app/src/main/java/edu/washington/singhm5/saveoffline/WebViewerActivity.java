package edu.washington.singhm5.saveoffline;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class WebViewerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final WebView webView = (WebView) findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

            }
        });
        findViewById(R.id.loadWebButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.loadUrl("http://www.google.com");
            }
        });

        findViewById(R.id.saveWebButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.saveWebArchive(getFilesDir().getAbsolutePath() + File.separator + "1.xml");
                Log.i("filepath", getFilesDir().getAbsolutePath() + File.separator + "1.xml");
            }
        });

        findViewById(R.id.clearButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.loadUrl("about:blank");
            }
        });

        findViewById(R.id.loadLocalButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filePath = getFilesDir().getAbsolutePath() + File.separator + "1.xml";
                Log.i("filepath", filePath);
                String raw_data = null;
//                try {
//                    raw_data = FileUtilities.getFileContents(new File(filePath));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                raw_data = raw_data.substring(raw_data.indexOf("<html"), raw_data.indexOf("</html") + 7);
                Log.i("raw_data", raw_data);
                //webView.loadData(raw_data, "text/html", null);
                webView.loadDataWithBaseURL("http://google.com/", raw_data, "text/html", "utf-8", null);
            }
        });
    }

    void continueWhenLoaded(WebView webView) {
        webView.setWebViewClient(new MyWebClient());
    }

    private class MyWebClient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url)
        {

        }
    }
}