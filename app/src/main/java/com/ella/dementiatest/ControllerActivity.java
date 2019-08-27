package com.ella.dementiatest;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class ControllerActivity extends AppCompatActivity {

    public static FragmentManager fragmentManager;
    private String testType;
    private int finalCITScore;
    private int finalSCIDSScore;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference citRef = database.getReference("6cit");
    DatabaseReference scidsRef = database.getReference("scids");
    DatabaseReference bothRef = database.getReference("both");
    Map cit = new HashMap();
    Map scids = new HashMap();
    Map both = new HashMap();
    CardView profileCard;
    CardView questionsCard;
    TextView profileText;
    TextView questionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controller);

        fragmentManager = getSupportFragmentManager();

        database = FirebaseDatabase.getInstance();
        citRef = database.getReference("6cit");
        scidsRef = database.getReference("scids");
        bothRef = database.getReference("both");

        cit = new HashMap();
        scids = new HashMap();
        both = new HashMap();

        if(findViewById(R.id.fragment_container) != null)
        {
            if(savedInstanceState != null)
            {
                return;
            }

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            ProfileFragment profileFragment = new ProfileFragment();
            fragmentTransaction.add(R.id.fragment_container, profileFragment, null);
            fragmentTransaction.commit();
        }
        profileCard = (CardView) findViewById(R.id.profile_card);
        questionsCard = (CardView) findViewById(R.id.questions_cards);
        profileText = (TextView) findViewById(R.id.profile_step_text);
        questionText = (TextView) findViewById(R.id.questions_step_text);
    }

    public void startQuestion()
    {
        profileCard.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));
        questionsCard.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
        profileText.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorTextDark));
        questionText.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));
    }

    public void setTest(String type)
    {
        this.testType = type;
    }

    public String getTestType() { return testType; }


    public int getFinalSCIDSScore() {
        return finalSCIDSScore;
    }

    public void setFinalSCIDSScore(int finalSCIDSScore) {
        this.finalSCIDSScore = finalSCIDSScore;
    }

    public int getFinalCITScore() {
        return finalCITScore;
    }

    public void setFinalCITScore(int finalCITScore) {
        this.finalCITScore = finalCITScore;
    }
}
