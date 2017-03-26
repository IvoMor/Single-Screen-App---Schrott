package com.example.ivos.SingleScrApp_1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        //Hide the action bar, but it's not necesery.
        //ActionBar actionBar = getActionBar();
        //actionBar.hide();

        // setOnClickListener to make TextView clicable
        TextView reserve = (TextView) findViewById(R.id.reserve);
        final String mailTo = "ivosuv@seznam.cz";
        final String subj = "Reservation";
        final String mailTextBody = "Your reservation text .....";

        reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(mailTo,subj,mailTextBody);
            }
        });

        // setOnClickListener to make TextView clicable
        TextView dial_num = (TextView) findViewById(R.id.tel);
        final String dialNUM = "+420602760726";

        dial_num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialTo(dialNUM);
            }
        });

        // setOnClickListener to make TextView clicable
        TextView URL = (TextView) findViewById(R.id.url);
        final String wUrl = "http://www.schrott.cz/";

        URL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openURL(wUrl);
            }
        });

        // setOnClickListener to make TextView clicable
        TextView faceURL = (TextView) findViewById(R.id.facebook);
        final String fURL = "http://www.facebook.com/schrottbrno";

        faceURL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openURL(fURL);
            }
        });

        // setOnClickListener to make TextView clicable
        TextView location = (TextView) findViewById(R.id.location);
        final String loc = "geo:49.1915,16.6160";

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap(loc);
            }
        });
    }

    // it opens new email (I use implicit intent to choose email app) with
    // MailTo: address (implicitly it's my email :)), Subject: "Reservation", BodyText: "Your reservation text here....."
    public void sendMessage(String address, String subject, String text) {
        Intent send = new Intent(Intent.ACTION_SENDTO);
        String uriText = "mailto:" + Uri.encode(address) +
                "?subject=" + Uri.encode(subject) +
                "&body=" + Uri.encode(text);
        Uri uri = Uri.parse(uriText);
        send.setData(uri);
        startActivity(Intent.createChooser(send, "Send mail..."));
    }

    // it should make a call to number stored in dialNum - but it steal doesn't work :( -
    // thank you for hints to handel it - because I don´t understand it
    public void dialTo(String dialNum) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + dialNum));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Toast.makeText(MainActivity.this,"You are NOT calling now",Toast.LENGTH_LONG).show();
            return;
        }
        Toast.makeText(MainActivity.this,"You are calling now",Toast.LENGTH_LONG).show();
        startActivity(intent);
    }

    // it opens web page (I use implicit intent to choose browser) with URL stored in address variable
    public void openURL(String address) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(address));
        startActivity(intent);
    }

    // it opens map (I use implicit intent to chose map app) in place specific by loc (coordinates)
    public void openMap(String loc) {
        Uri gmmIntentUri = Uri.parse(loc);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }
}


