package com.ella.dementiatest;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    int finalScore;
    String testType;
    TextView finalscoreText;
//    TextView scidsscoreText;
//    TextView citscoreText;
    TextView description;
    TextView bothDescription;
    Button exitBtn;

    LinearLayout singleLinear;
    LinearLayout bothLinear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        singleLinear = (LinearLayout) findViewById(R.id.single_linear);
        bothLinear = (LinearLayout) findViewById(R.id.both_linear);
        finalscoreText = (TextView) findViewById(R.id.final_score);
//        scidsscoreText = (TextView) findViewById(R.id.scids_score);
//        citscoreText = (TextView) findViewById(R.id.cit_score);
        description = (TextView) findViewById(R.id.class_text);
        bothDescription = (TextView) findViewById(R.id.both_class_text);

        Intent intent = getIntent();
        testType = intent.getStringExtra("TYPE");
        if(testType.equals("SELF"))
        {
            finalScore = intent.getIntExtra("CIT-SCORE", 0);
            singleLinear.setVisibility(View.VISIBLE);
            bothLinear.setVisibility(View.GONE);
            finalscoreText.setText(String.valueOf(finalScore));
            if (finalScore >= 0 && finalScore <= 4)
            {
                description.setText("Normal cognition");
            }
            else if(finalScore >= 5 && finalScore <= 9)
            {
                description.setText("Questionable Impairment");
            }
            else if(finalScore >= 10)
            {
                description.setText("Impairment consistent with dementia. Kindly see a physician for further evaluation.");
            }
        }
        else if(testType.equals("INFORMANT"))
        {
            finalScore = intent.getIntExtra("SCIDS-SCORE", 0);
            singleLinear.setVisibility(View.VISIBLE);
            bothLinear.setVisibility(View.GONE);
            finalscoreText.setText(String.valueOf(finalScore));
            if(finalScore >= 4)
            {
                description.setText("The respondent shows symptoms of Dementia. Kindly see a physician for further evaluation.");
            }
            else
            {
                description.setText("The respondent does not show any symptoms of Dementia.");
            }
        }
        else if(testType.equals("BOTH"))
        {
            int scidsFinalScore = intent.getIntExtra("SCIDS-SCORE", 0);
            int citFinalScore = intent.getIntExtra("CIT-SCORE", 0);
            String desc = intent.getStringExtra("DESC");
            singleLinear.setVisibility(View.GONE);
            bothLinear.setVisibility(View.VISIBLE);
            if(desc.equalsIgnoreCase("YES"))
            {
                bothDescription.setText("The respondent shows symptoms of Dementia. Kindly see a physician for further evaluation.");
            }
            else
            {
                bothDescription.setText("The respondent does not show any symptoms of Dementia.");
            }
        }

        exitBtn = (Button) findViewById(R.id.done_button);
        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(ResultsActivity.this);
                builder.setTitle("Exit");
                builder.setMessage("Do you want to take the test again ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i){
                        //dialogInterface.dismiss();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("EXIT", 0);
                        startActivity(intent);
                        //((Activity)getBaseContext()).finish();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
}
