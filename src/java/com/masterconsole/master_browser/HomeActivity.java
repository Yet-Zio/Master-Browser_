package com.masterconsole.master_browser;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener
{
     private Button SearchButtonHome;
    private EditText InputURL;
    private ImageButton facebook_imgbtn;
    private ImageButton google_imgbtn;
    private ImageButton youtube_imgbtn;
    private ImageButton twitter_imgbtn;
    private ImageButton gmail_imgbtn;
    private ImageButton blogger_imgbtn;
    private ProgressDialog Loadingbar;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Loadingbar = new ProgressDialog(this);

        SearchButtonHome = (Button) findViewById(R.id.search_button_home);
        InputURL = (EditText) findViewById(R.id.search_url_home);
        facebook_imgbtn = (ImageButton) findViewById(R.id.facebook);
        google_imgbtn = (ImageButton) findViewById(R.id.google);
        youtube_imgbtn = (ImageButton) findViewById(R.id.youtube);
        twitter_imgbtn = (ImageButton) findViewById(R.id.twitter);
        gmail_imgbtn = (ImageButton) findViewById(R.id.gmail);
        blogger_imgbtn = (ImageButton) findViewById(R.id.blogger);

         Loadingbar.setTitle("Welcome Cadet!");
         Loadingbar.setMessage("Experience a simple yet enough mastered Browser.\nClick anywhere on the\nscreen to close this box");
         Loadingbar.show();


         SearchButtonHome.setOnClickListener(this);
        facebook_imgbtn.setOnClickListener(this);
        google_imgbtn.setOnClickListener(this);
        youtube_imgbtn.setOnClickListener(this);
        twitter_imgbtn.setOnClickListener(this);
        gmail_imgbtn.setOnClickListener(this);
        blogger_imgbtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View view)
    {
         if(view == SearchButtonHome)
         {
             OpenWebSite();

         }

         if(view == facebook_imgbtn)
         {
             Intent Open_facebook = new Intent(HomeActivity.this, SearchURL.class);
             Open_facebook.putExtra("url_address", "https://www.facebook.com");
             startActivity(Open_facebook);

         }
        if(view == google_imgbtn)
        {
            Intent Open_google = new Intent(HomeActivity.this, SearchURL.class);
            Open_google.putExtra("url_address", "https://www.google.com");
            startActivity(Open_google);

        }
        if(view == youtube_imgbtn)
        {
            Intent Open_youtube = new Intent(HomeActivity.this, SearchURL.class);
            Open_youtube.putExtra("url_address", "https://www.youtube.com");
            startActivity(Open_youtube);

        }
        if(view == twitter_imgbtn)
        {
            Intent Open_twitter = new Intent(HomeActivity.this, SearchURL.class);
            Open_twitter.putExtra("url_address", "https://twitter.com");
            startActivity(Open_twitter);

        }
        if(view == gmail_imgbtn)
        {
            Intent Open_gmail = new Intent(HomeActivity.this, SearchURL.class);
            Open_gmail.putExtra("url_address", "https://www.google.com/gmail");
            startActivity(Open_gmail);

        }
        if(view == blogger_imgbtn)
        {
            Intent Open_blogger = new Intent(HomeActivity.this, SearchURL.class);
            Open_blogger.putExtra("url_address", "https://www.blogger.com");
            startActivity(Open_blogger);

        }

    }

    private void OpenWebSite()
    {
        Loadingbar.setTitle("Loading...");
        Loadingbar.setMessage("Please wait friend...\nYour Url is being loaded.\nWhen its completed, click anywhere on the screen.");
        Loadingbar.show();


        String Url_Address = InputURL.getText().toString();

          if(TextUtils.isEmpty(Url_Address))
          {
              Toast empty = Toast.makeText(HomeActivity.this, "It seems that you have not entered a Url. Please enter a Url or Web Address!", Toast.LENGTH_LONG);
              empty.show();

          }
          else{
              String url_without_https = Url_Address.replaceAll("https://www.", "");
              String https = "https://";
              String www = "www.";

              Intent search = new Intent(HomeActivity.this, SearchURL.class);
              search.putExtra("url_address", https+www+url_without_https);
              startActivity(search);

              InputURL.setText("");
              InputURL.requestFocus();

          }
    }
}
