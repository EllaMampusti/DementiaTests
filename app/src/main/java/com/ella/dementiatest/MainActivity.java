package com.ella.dementiatest;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button startBtn;
    ImageView infoBtn;
    TextView linkText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int exit = getIntent().getIntExtra("EXIT", 1);
        if(exit == 0)
        {
            //android.os.Process.killProcess(android.os.Process.myPid());
            System.out.println("Finish the APP!");
            ((Activity)getApplicationContext()).finish();
        }

        linkText = (TextView) findViewById(R.id.privacy_policy_text);
        linkText.setMovementMethod(LinkMovementMethod.getInstance());

        infoBtn = (ImageView) findViewById(R.id.info_button);
        infoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = getLayoutInflater();
                View alertLayout = inflater.inflate(R.layout.dialog_custom, null);

                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                TextView title = new TextView(MainActivity.this);
// You Can Customise your Title here
                title.setText("INFORMATION");
                title.setPadding(10, 10, 10, 10);
                title.setGravity(Gravity.CENTER);
                title.setTextSize(16);

                alert.setCustomTitle(title);
                //alert.setTitle("INFORMATION");
                // this is set the view from XML inside AlertDialog
                alert.setView(alertLayout);
                // disallow cancel of AlertDialog on click of back button and outside touch
                alert.setCancelable(false);

                alert.setPositiveButton("Done", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = alert.create();
                dialog.show();

            }
        });

        startBtn =(Button) findViewById(R.id.start_button);


        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent controllerActivity = new Intent(getBaseContext(), ControllerActivity.class);
                startActivity(controllerActivity);
            }
        });


    }
}
