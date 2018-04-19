package com.masterconsole.master_browser;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.DownloadListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class SearchURL extends AppCompatActivity implements View.OnClickListener {

    private Button SearchUrlButton;
    private EditText UrlInput;
    private ImageButton HomeButton;
    private WebView SearchWebAddress;
    String url;
    private ProgressDialog Loadingbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_url);

        Loadingbar = new ProgressDialog(this);

        SearchUrlButton = (Button) findViewById(R.id.search_url_button);
        UrlInput = (EditText)  findViewById(R.id.input_search_url);
        HomeButton = (ImageButton) findViewById(R.id.home_button);
        SearchWebAddress = (WebView) findViewById(R.id.SearchWebsite);

        url = getIntent().getExtras().get("url_address").toString();
        UrlInput.setText(url);



        WebSettings webSettings = SearchWebAddress.getSettings();
        webSettings.setJavaScriptEnabled(true);
        SearchWebAddress.loadUrl(url);
        SearchWebAddress.setWebViewClient(new WebViewClient());

        Loadingbar.setTitle("Loading...");
        Loadingbar.setMessage("Please wait friend...\nYour Url is being loaded.\nWhen its completed, click anywhere on the screen.");
        Loadingbar.show();


        SearchUrlButton.setOnClickListener(this);
        HomeButton.setOnClickListener(this);
        SearchWebAddress.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {

                MimeTypeMap mtm = MimeTypeMap.getSingleton();
                DownloadManager.Request myRequest = new DownloadManager.Request(Uri.parse(url));
                Uri downloadUri = Uri.parse(url);
                String fileName = downloadUri.getLastPathSegment();
                int pos = 0;
                if (contentDisposition != null && (pos = contentDisposition.toLowerCase().lastIndexOf("filename=")) >= 0) {
                    fileName = contentDisposition.substring(pos + 9);
                    pos = fileName.lastIndexOf(";");

                    if (pos > 0) {
                        fileName = fileName.substring(0, pos - 1);
                    }
                }

                String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).toLowerCase();
                String mimeType = mtm.getMimeTypeFromExtension(fileExtension);
                myRequest.setTitle(fileName);
                myRequest.setDescription(url);
                myRequest.setMimeType(mimeType);
                myRequest.allowScanningByMediaScanner();
                myRequest.setDestinationInExternalFilesDir(SearchURL.this, Environment.DIRECTORY_DOWNLOADS,fileName);
                myRequest.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

                DownloadManager myManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                myManager.enqueue(myRequest);

                Toast.makeText(SearchURL.this, "File is being downloaded...", Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public void onBackPressed() {

        if(SearchWebAddress.canGoBack())
        {
            SearchWebAddress.goBack();


        }
        else
        {
            super.onBackPressed();

        }
    }


    @Override
    public void onClick(View view)
    {
       if(view == HomeButton)
       {

           finish();

           Intent homePage = new Intent(SearchURL.this, HomeActivity.class);
           startActivity(homePage);

       }
       if(view == SearchUrlButton)
       {
           SearchWebAddress();

       }


    }

    private void SearchWebAddress()
    {
        Loadingbar.setTitle("Loading...");
        Loadingbar.setMessage("Please wait friend...\nYour Url is being loaded.\nWhen its completed, click anywhere on the screen.");
        Loadingbar.show();

        String Url_Address = UrlInput.getText().toString();

        if(TextUtils.isEmpty(Url_Address))
        {
            Toast empty = Toast.makeText(SearchURL.this, "It seems that you have not entered a Url. Please enter a Url or Web Address!", Toast.LENGTH_LONG);
            empty.show();

        }
        else{
            String url_without_https = Url_Address.replaceAll("https://www.", "");
            String https = "https://";
            String www = "www.";

            Intent search = new Intent(SearchURL.this, SearchURL.class);
            search.putExtra("url_address", https+www+url_without_https);
            startActivity(search);

            UrlInput.setText("");
            UrlInput.requestFocus();

        }
        SearchWebAddress.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {

                MimeTypeMap mtm = MimeTypeMap.getSingleton();
                DownloadManager.Request myRequest = new DownloadManager.Request(Uri.parse(url));
                Uri downloadUri = Uri.parse(url);
                String fileName = downloadUri.getLastPathSegment();
                int pos = 0;
                if (contentDisposition != null && (pos = contentDisposition.toLowerCase().lastIndexOf("filename=")) >= 0) {
                    fileName = contentDisposition.substring(pos + 9);
                    pos = fileName.lastIndexOf(";");

                    if (pos > 0) {
                        fileName = fileName.substring(0, pos - 1);
                    }
                }

                String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).toLowerCase();
                String mimeType = mtm.getMimeTypeFromExtension(fileExtension);
                myRequest.setTitle(fileName);
                myRequest.setDescription(url);
                myRequest.setMimeType(mimeType);
                myRequest.allowScanningByMediaScanner();
                myRequest.setDestinationInExternalFilesDir(SearchURL.this, Environment.DIRECTORY_DOWNLOADS,fileName);
                myRequest.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

                DownloadManager myManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                myManager.enqueue(myRequest);

                Toast.makeText(SearchURL.this, "File is being downloaded...", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
