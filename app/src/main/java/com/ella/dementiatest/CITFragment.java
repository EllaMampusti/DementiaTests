package com.ella.dementiatest;

import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;



public class CITFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Button nextBtn;
    TextView qTextView;
    TextView trackerTextView;
    EditText dateEditText;
    Spinner monthSpinner;
    LinearLayout spinnerLinear;
    TextView phraseTextView;
    LinearLayout timeLinear;
    TimePicker timePicker;
    LinearLayout countBackLinear;
    TextView countback1;
    TextView countback2;
    TextView countback3;
    TextView countback4;
    TextView countback5;
    TextView countback6;
    TextView countback7;
    TextView countback8;
    TextView countback9;
    TextView countback10;
    LinearLayout mosLinear;
    LinearLayout phraseLinear;
    TextView janTextview;
    TextView febTextview;
    TextView marTextview;
    TextView aprTextview;
    TextView mayTextview;
    TextView junTextview;
    TextView julTextview;
    TextView augTextview;
    TextView sepTextview;
    TextView octTextview;
    TextView novTextview;
    TextView decTextview;
    CardView janCard;
    TextView mosdisplay;
    TextView countdisplay;
    CardView resetCard;
    CardView resetCount;
    EditText word1;
    EditText word2;
    EditText word3;
    EditText word4;
    EditText word5;

    private Date currentDate;
    private int currentYr;
    private int currentMonth;
    private int currentHr;
    private int currentMin;
    private int currentAMPM;
    public int CITScore;
    private Time currentTime;
    CountDownTimer monthTimer;

    private final String[] Months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November" , "December"};
    private final String[] RevMonths = {"December", "November", "October", "September", "August", "July", "June", "May", "April", "March", "February" , "January"};

    private String monthRevAnswer = "Answer: ";
    private String countRevAnswer = "Answer: ";
    private List<String> MonthsAns;
    private List<String> CountbackAns;
    private int reverseCtr;
    int qTracker;
    static int monthFlag = 0; // if countdown reaches 0, set to 1

//    private OnFragmentInteractionListener mListener;

    public CITFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CITFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CITFragment newInstance(String param1, String param2) {
        CITFragment fragment = new CITFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cit, container, false);

        final List<String> questions = initialiseList();
        MonthsAns = new ArrayList<String>();
        CountbackAns = new ArrayList<String>();
        initialiseAnswers();

        qTracker = 0;
        CITScore = 0;

        qTextView = (TextView) view.findViewById(R.id.cit_question_textview);
        trackerTextView = (TextView) view.findViewById(R.id.cit_tracker_textview);
        dateEditText = (EditText) view.findViewById(R.id.date_edittext);
        monthSpinner = (Spinner) view.findViewById(R.id.months_spinner);
        spinnerLinear = (LinearLayout) view.findViewById(R.id.spinner_linear);
        phraseTextView = (TextView) view.findViewById(R.id.phrase_text);
        timeLinear = (LinearLayout) view.findViewById(R.id.time_linear);
        timePicker = (TimePicker) view.findViewById(R.id.time_picker);
        timePicker.setIs24HourView(true);
        timePicker.setEnabled(true);
        if (Build.VERSION.SDK_INT >= 23 )
        {
            timePicker.setHour(0);
            timePicker.setMinute(0);
        }
        else
        {
            timePicker.setCurrentHour(0);
            timePicker.setCurrentMinute(0);
        }
        countBackLinear = (LinearLayout) view.findViewById(R.id.count_back_group);
        countback1 = (TextView) view.findViewById(R.id.one_text);
        countback2 = (TextView) view.findViewById(R.id.two_text);
        countback3 = (TextView) view.findViewById(R.id.three_text);
        countback4 = (TextView) view.findViewById(R.id.four_text);
        countback5 = (TextView) view.findViewById(R.id.five_text);
        countback6 = (TextView) view.findViewById(R.id.six_text);
        countback7 = (TextView) view.findViewById(R.id.seven_text);
        countback8 = (TextView) view.findViewById(R.id.eight_text);
        countback9 = (TextView) view.findViewById(R.id.nine_text);
        countback10 = (TextView) view.findViewById(R.id.ten_text);
        janTextview = (TextView) view.findViewById(R.id.jan_text);
        febTextview = (TextView) view.findViewById(R.id.feb_text);
        marTextview = (TextView) view.findViewById(R.id.mar_text);
        aprTextview = (TextView) view.findViewById(R.id.apr_text);
        mayTextview = (TextView) view.findViewById(R.id.may_text);
        junTextview = (TextView) view.findViewById(R.id.jun_text);
        julTextview = (TextView) view.findViewById(R.id.jul_text);
        augTextview = (TextView) view.findViewById(R.id.aug_text);
        sepTextview = (TextView) view.findViewById(R.id.sep_text);
        octTextview = (TextView) view.findViewById(R.id.oct_text);
        novTextview = (TextView) view.findViewById(R.id.nov_text);
        decTextview = (TextView) view.findViewById(R.id.dec_text);
        janCard = (CardView) view.findViewById(R.id.jan_cardview);
        mosLinear = (LinearLayout) view.findViewById(R.id.months_group);
        resetCard = (CardView) view.findViewById(R.id.reset_cardview);
        resetCount = (CardView) view.findViewById(R.id.count_reset_cardview);
        phraseLinear = (LinearLayout) view.findViewById(R.id.phrase_group);
        word1 = (EditText) view.findViewById(R.id.phrase_word1);
        word2 = (EditText) view.findViewById(R.id.phrase_word2);
        word3 = (EditText) view.findViewById(R.id.phrase_word3);
        word4 = (EditText) view.findViewById(R.id.phrase_word4);
        word5 = (EditText) view.findViewById(R.id.phrase_word5);

        mosdisplay = (TextView) view.findViewById(R.id.months_ans_text);
        countdisplay = (TextView) view.findViewById(R.id.count_ans_text);
        nextBtn = (Button) view.findViewById(R.id.cit_next_button);

        ArrayAdapter<CharSequence> mosAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.months_array, R.layout.spinner_item);
        monthSpinner.setAdapter(mosAdapter);

        qTextView.setText(questions.get(qTracker));
        trackerTextView.setText((qTracker + 1) + " of  "+ questions.size());

        resetCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                janTextview.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorSand));
                febTextview.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorSand));
                marTextview.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorSand));
                aprTextview.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorSand));
                mayTextview.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorSand));
                junTextview.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorSand));
                julTextview.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorSand));
                augTextview.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorSand));
                sepTextview.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorSand));
                octTextview.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorSand));
                novTextview.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorSand));
                decTextview.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorSand));
                mosdisplay.setText("Answer: ");
                monthRevAnswer = "Answer: ";
                MonthsAns.clear();
            }
        });

        resetCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countback1.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorSand));
                countback2.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorSand));
                countback3.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorSand));
                countback4.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorSand));
                countback5.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorSand));
                countback6.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorSand));
                countback7.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorSand));
                countback8.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorSand));
                countback9.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorSand));
                countback10.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorSand));
                countdisplay.setText("Answer: ");
                countRevAnswer = "Answer: ";
                CountbackAns.clear();
            }
        });


        View.OnClickListener monthCardListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monthTimer.cancel();
                String selectMonth = ((TextView)v).getText().toString();
                if(MonthsAns.contains(selectMonth))
                {
                    Toast toast = Toast.makeText(getContext(), "That month is already selected. Press Reset to start again.", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else
                {
                    MonthsAns.add(selectMonth);
                    ((TextView)v).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
                    monthRevAnswer += selectMonth + "  ";
                    mosdisplay.setText(monthRevAnswer);
                }

            }
        };

        janTextview.setOnClickListener(monthCardListener);
        febTextview.setOnClickListener(monthCardListener);
        marTextview.setOnClickListener(monthCardListener);
        aprTextview.setOnClickListener(monthCardListener);
        mayTextview.setOnClickListener(monthCardListener);
        junTextview.setOnClickListener(monthCardListener);
        julTextview.setOnClickListener(monthCardListener);
        augTextview.setOnClickListener(monthCardListener);
        sepTextview.setOnClickListener(monthCardListener);
        octTextview.setOnClickListener(monthCardListener);
        novTextview.setOnClickListener(monthCardListener);
        decTextview.setOnClickListener(monthCardListener);

        View.OnClickListener countCardListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectNo = ((TextView)v).getText().toString();
                if(CountbackAns.contains(selectNo))
                {
                    Toast toast = Toast.makeText(getContext(), "That number is already selected. Press Reset to start again.", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else
                {
                    CountbackAns.add(selectNo);
                    ((TextView)v).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
                    countRevAnswer += selectNo + " ";
                    countdisplay.setText(countRevAnswer);
                }

            }
        };


        countback1.setOnClickListener(countCardListener);
        countback2.setOnClickListener(countCardListener);
        countback3.setOnClickListener(countCardListener);
        countback4.setOnClickListener(countCardListener);
        countback5.setOnClickListener(countCardListener);
        countback6.setOnClickListener(countCardListener);
        countback7.setOnClickListener(countCardListener);
        countback8.setOnClickListener(countCardListener);
        countback9.setOnClickListener(countCardListener);
        countback10.setOnClickListener(countCardListener);


        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String answer = dateEditText.getText().toString();

                System.out.println("ANSWER: " + answer);

                if(qTracker < questions.size())
                {
                    switch(qTracker)
                    {
                        // Question 1
                        // Correct Answer: Current Year
                        case 0:
                        {
                            int qScore = 0;
                            if(answer.equals(""))
                            {
                                Toast toast = Toast.makeText(getContext(), "Please provide an answer.", Toast.LENGTH_LONG);
                                toast.show();
                            }
                            else
                            {
                                if(Integer.parseInt(answer) == currentYr)
                                {
                                    qScore += 0;
                                }
                                else
                                {
                                    qScore += 4;
                                }
                                CITScore += qScore;
                                qTracker++;
                                dateEditText.setText("");
                                qTextView.setText(questions.get(qTracker));
                                trackerTextView.setText((qTracker + 1) + " of  "+ questions.size());
                                dateEditText.setVisibility(View.GONE);
                                spinnerLinear.setVisibility(View.VISIBLE);
                                if(((ControllerActivity) getActivity()).getTestType().toString().equals("BOTH"))
                                {
                                    ((ControllerActivity) getActivity()).both.put("answer-13", answer);
                                    ((ControllerActivity) getActivity()).both.put("score-13", qScore);
                                }
                                else
                                {
                                    ((ControllerActivity) getActivity()).cit.put("answer-1", answer);
                                    ((ControllerActivity) getActivity()).cit.put("score-1", qScore);
                                }
                            }

                            break;
                        }
                        // Question 2
                        // Correct Answer: Current Month
                        case 1:
                        {
                            int qScore = 0;
                            answer = monthSpinner.getSelectedItem().toString();
                            System.out.println("MONTH" + Months[currentMonth]);
                            if(answer.equals("Select"))
                            {
                                Toast toast = Toast.makeText(getContext(), "Please select an answer.", Toast.LENGTH_SHORT);
                                toast.show();
                            }
                            else
                            {
                                if(answer.equals(Months[currentMonth]))
                                {
                                    qScore += 0;
                                }
                                else
                                {
                                    qScore += 3;
                                }
                                CITScore += qScore;
                                qTracker++;
                                spinnerLinear.setVisibility(View.GONE);
                                qTextView.setText(questions.get(qTracker));
                                trackerTextView.setText((qTracker + 1) + " of  "+ questions.size());
                                phraseTextView.setVisibility(View.VISIBLE);
                                if(((ControllerActivity) getActivity()).getTestType().toString().equals("BOTH"))
                                {
                                    ((ControllerActivity) getActivity()).both.put("answer-14", answer);
                                    ((ControllerActivity) getActivity()).both.put("score-14", qScore);
                                }
                                else
                                {
                                    ((ControllerActivity) getActivity()).cit.put("answer-2", answer);
                                    ((ControllerActivity) getActivity()).cit.put("score-2", qScore);
                                }
                            }

                            break;

                        }
                        //Question 3
                        //Remember Phrase
                        case 2:
                        {
                            qTracker++;
                            phraseTextView.setVisibility(View.GONE);
                            qTextView.setText(questions.get(qTracker));
                            trackerTextView.setText((qTracker + 1) + " of  "+ questions.size());
                            timeLinear.setVisibility(View.VISIBLE);
                            if(((ControllerActivity) getActivity()).getTestType().toString().equals("BOTH"))
                            {
                                ((ControllerActivity) getActivity()).both.put("answer-15", "John, Smith, 42, High Street, Bedford");
                                ((ControllerActivity) getActivity()).both.put("score-15", 0);
                            }
                            else {
                                ((ControllerActivity) getActivity()).cit.put("answer-3", "John, Smith, 42, High Street, Bedford");
                                ((ControllerActivity) getActivity()).cit.put("score-3", 0);
                            }
                            break;
                        }
                        //Question 4
                        //Answer: Current Time
                        case 3:
                        {
                            int hourAns;
                            int minAns;
                            if (Build.VERSION.SDK_INT >= 23 )
                            {
                                hourAns = timePicker.getHour();
                                minAns = timePicker.getMinute();
                            }
                            else
                            {
                                hourAns = timePicker.getCurrentHour();
                                minAns = timePicker.getCurrentMinute();
                            }
                            SimpleDateFormat sdf = new SimpleDateFormat("HH:MM");
                            Calendar mcurrentTime = Calendar.getInstance();
                            int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                            int minute = mcurrentTime.get(Calendar.MINUTE);
                            //convert time
                            int totalTimeAns = (hourAns * 60) + minAns;
                            int correctTime = (hour * 60) + minute;
                            // get time diff
                            int timeDiff =  Math.abs(totalTimeAns - correctTime) ;
                            int qScore = 0;
                            System.out.println("TIME: " + sdf);
                            if(false)
                            {
                                Toast toast = Toast.makeText(getContext(), "Please provide an answer.", Toast.LENGTH_SHORT);
                                toast.show();
                            }
                            else
                            {
                                if(timeDiff > 60)
                                {
                                    qScore += 3;
                                }
                                else
                                {
                                    qScore += 0;
                                }
                                CITScore += qScore;
                                qTracker++;
                                timeLinear.setVisibility(View.GONE);
                                qTextView.setText(questions.get(qTracker));
                                trackerTextView.setText((qTracker + 1) + " of  "+ questions.size());
                                countBackLinear.setVisibility(View.VISIBLE);
                                if(((ControllerActivity) getActivity()).getTestType().toString().equals("BOTH"))
                                {
                                    ((ControllerActivity) getActivity()).both.put("answer-16", hourAns + ":" + minAns);
                                    ((ControllerActivity) getActivity()).both.put("score-16", qScore);
                                }
                                else {
                                    ((ControllerActivity) getActivity()).cit.put("answer-4", hourAns + ":" + minAns);
                                    ((ControllerActivity) getActivity()).cit.put("score-4", qScore);
                                }
                            }
                            break;
                        }
                        //Question 5
                        //Count backward from 20-1
                        case 4:
                        {
                            //get answers
                            int ctr = CountbackAns.size();
                            int revCtr = 10;
                            int errCtr = 0;
                            int qScore = 0;
                            if(ctr < 10)
                            {
                                Toast toast = Toast.makeText(getContext(), "Selected items should be 10.", Toast.LENGTH_SHORT);
                                toast.show();
                            }
                            else
                            {
                                String countAns = "";

                                for(int i = 0; i < ctr; i++)
                                {
                                    System.out.println("ANSWER  "+ CountbackAns.get(i) + "CORRECT" + revCtr);
                                    countAns += CountbackAns.get(i) + " ";
                                    if( Integer.parseInt(CountbackAns.get(i)) == revCtr )
                                    {
                                        errCtr += 0;
                                    }
                                    else
                                    {
                                        errCtr += 1;
                                    }
                                    revCtr--;
                                }
                                if(errCtr > 2)
                                {
                                    qScore = 2;
                                }
                                else{
                                    qScore = errCtr;
                                }
                                CITScore += qScore;
                                qTracker++;
                                countBackLinear.setVisibility(View.GONE);
                                qTextView.setText(questions.get(qTracker));
                                trackerTextView.setText((qTracker + 1) + " of  "+ questions.size());
                                mosLinear.setVisibility(View.VISIBLE);
                                if(((ControllerActivity) getActivity()).getTestType().toString().equals("BOTH"))
                                {
                                    ((ControllerActivity) getActivity()).both.put("answer-17", countAns);
                                    ((ControllerActivity) getActivity()).both.put("score-17", qScore);
                                }
                                else {
                                    ((ControllerActivity) getActivity()).cit.put("answer-5", countAns);
                                    ((ControllerActivity) getActivity()).cit.put("score-5", qScore);
                                }
                                // Month timer
                                monthTimer = new CountDownTimer(8000, 1000){
                                    public void onTick(long millisUntilFinished){

                                    }
                                    public  void onFinish()
                                    {
                                        System.out.println("TIMER FINISH!!"); // stopping the timer after user selects December
                                        if(MonthsAns.isEmpty())
                                        {
                                            monthRevAnswer = "Answer: December ";
                                            decTextview.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
                                            MonthsAns.add("December");
                                            monthFlag = 1;
                                        }
                                    }
                                }.start();
                            }

                            break;
                        }
                        //Question 6
                        //Reverse Months
                        case 5:
                        {
                            int ctr = MonthsAns.size();
                            int errCtr = 0;
                            int qScore = 0;

                            if(ctr < 12)
                            {
                                Toast toast = Toast.makeText(getContext(), "Selected items should be 12.", Toast.LENGTH_SHORT);
                                toast.show();
                            }
                            else
                            {
                                //check answers
                                String monthsAns = "";
                                for(int i = 0; i < ctr; i++)
                                {
                                    System.out.println("ANSWER  "+ MonthsAns.get(i) + "CORRECT" + RevMonths[i]);
                                    monthsAns += MonthsAns.get(i) + " ";
                                    //EDIT If there is one error, force two errors since one position will take antoher
                                    //errCtr = 2 is qScore = 4
                                    if( !(MonthsAns.get(i).equals(RevMonths[i])) )
                                    {
                                        errCtr = 2;
                                        break;
                                    }
                                }
                                errCtr = errCtr + monthFlag;
                                if(errCtr >= 2)
                                {
                                    qScore = 4; // If the error is more than 2, automatic 4 points, always true
                                }
                                else
                                {
                                    qScore = errCtr;
                                }
                                CITScore += qScore;
                                System.out.println("QUESTION 6: " + qScore);
                                qTracker++;
                                mosLinear.setVisibility(View.GONE);
                                qTextView.setText(questions.get(qTracker));
                                trackerTextView.setText((qTracker + 1) + " of  "+ questions.size());
                                phraseLinear.setVisibility(View.VISIBLE);
                                if(((ControllerActivity) getActivity()).getTestType().toString().equals("BOTH"))
                                {
                                    ((ControllerActivity) getActivity()).both.put("answer-17", monthsAns);
                                    ((ControllerActivity) getActivity()).both.put("score-17", qScore);
                                }
                                else {
                                    ((ControllerActivity) getActivity()).cit.put("answer-6", monthsAns);
                                    ((ControllerActivity) getActivity()).cit.put("score-6", qScore);
                                }
                            }

                            break;
                        }
                        case 6:
                        {
                            int errCtr = 0;
                            int qScore = 0;
                            // Check answers
                            // 11132018 ETM Nan for if all answers are left blank
                            if(word1.getText().toString().equals("") && word2.getText().toString().equals("") &&
                                    word3.getText().toString().equals("") && word4.getText().toString().equals("") &&
                                    word5.getText().toString().equals(""))
                            {
                                answer = "NaN";
                                qScore = 10;
                                CITScore += qScore;
                                qTracker++;
                                if(((ControllerActivity) getActivity()).getTestType().toString().equals("BOTH"))
                                {
                                    ((ControllerActivity) getActivity()).both.put("answer-18", answer);
                                    ((ControllerActivity) getActivity()).both.put("score-18", qScore);
                                }
                                else {
                                    ((ControllerActivity) getActivity()).cit.put("answer-7", answer);
                                    ((ControllerActivity) getActivity()).cit.put("score-7", qScore);
                                }
                            }
                            else
                            {
                                answer = word1.getText().toString() + "  "  + word2.getText().toString()  + "  "  +
                                        word3.getText().toString()  + "  "  + word4.getText().toString()  + "  "  +
                                        word5.getText().toString();
                                System.out.println("WORD 1 : " + word1.getText().toString());
                                System.out.println("WORD 2 : " + word2.getText().toString());
                                System.out.println("WORD 3 : " + word3.getText().toString());
                                System.out.println("WORD 4 : " + word4.getText().toString());
                                System.out.println("WORD 5 : " + word5.getText().toString());
                                if( !word1.getText().toString().equalsIgnoreCase("John") )
                                {
                                    System.out.println("JOHN NOT MATCH");
                                    errCtr += 1;
                                }
                                if( !word2.getText().toString().equalsIgnoreCase("Smith") )
                                {
                                    System.out.println("SMITH NOT MATCH");
                                    errCtr += 1;
                                }
                                if( !word3.getText().toString().equalsIgnoreCase("42") )
                                {
                                    System.out.println("42 NOT MATCH");
                                    errCtr += 1;
                                }
                                if( !word4.getText().toString().equalsIgnoreCase("High Street") )
                                {
                                    System.out.println("HIGH STREET NOT MATCH");
                                    errCtr += 1;
                                }
                                if( !word5.getText().toString().equalsIgnoreCase("Bedford") )
                                {
                                    System.out.println("BEDFORD NOT MATCH");
                                    errCtr += 1;
                                }
                                // Compute Question Score
                                switch (errCtr)
                                {
                                    case 0 : qScore = 0; break;
                                    case 1 : qScore = 2; break;
                                    case 2 : qScore = 4; break;
                                    case 3 : qScore = 6; break;
                                    case 4 : qScore = 8; break;
                                    case 5 : qScore = 10; break;
                                }

                                CITScore += qScore;
                                qTracker++;
                                if(((ControllerActivity) getActivity()).getTestType().toString().equals("BOTH"))
                                {
                                    ((ControllerActivity) getActivity()).both.put("answer-18", answer);
                                    ((ControllerActivity) getActivity()).both.put("score-18", qScore);
                                }
                                else {
                                    ((ControllerActivity) getActivity()).cit.put("answer-7", answer);
                                    ((ControllerActivity) getActivity()).cit.put("score-7", qScore);
                                }
                            }
                            break;
                        }
                    }
                }
                else{
                    System.out.println("6 CIT SCORE: " +  CITScore);
                    System.out.println("TEST TYPE: " +  ((ControllerActivity) getActivity()).getTestType().toString());
                    if(((ControllerActivity) getActivity()).getTestType().toString().equals("SELF"))
                    {
                        ((ControllerActivity) getActivity()).cit.put("totalscore", CITScore);
                        ControllerActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new SubmitFragment(), null).commit();
                        ((ControllerActivity) getActivity()).setFinalCITScore(CITScore);
                    }
                    else if(((ControllerActivity) getActivity()).getTestType().toString().equals("BOTH"))
                    {
                        ((ControllerActivity) getActivity()).both.put("citscore", CITScore);
                        ControllerActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new SubmitFragment(), null).commit();
                        ((ControllerActivity) getActivity()).setFinalCITScore(CITScore);
                    }

                }
            }
        });
        return view;
    }

    public List<String> initialiseList()
    {
        List<String> questions = new ArrayList<String>();
        questions.add("What year is it?");
        questions.add("What month is it?");
        questions.add("Remember the following words:");
        questions.add("About what time is it?");
        questions.add("Count backward from 10 to 1");
        questions.add("Select the months of the year in reverse. The last month of the year is:");
        questions.add("Repeat the name and address you were asked to remember");
        if(((ControllerActivity) getActivity()).getTestType().toString().equals("BOTH"))
        {
            ((ControllerActivity) getActivity()).both.put("question-13", "What year is it?");
            ((ControllerActivity) getActivity()).both.put("question-14", "What month is it?");
            ((ControllerActivity) getActivity()).both.put("question-15", "John, Smith, 42, High Street, Bedford");
            ((ControllerActivity) getActivity()).both.put("question-16", "About what time is it? (Picker is in 24-Hour format)");
            ((ControllerActivity) getActivity()).both.put("question-17", "Count BACKWARD from 10 to 1");
            ((ControllerActivity) getActivity()).both.put("question-18", "Select the months of the year in REVERSE. The last month of the year is:");
            ((ControllerActivity) getActivity()).both.put("question-19", "Repeat the name and address you were asked to remember");
        }
        else {
            ((ControllerActivity) getActivity()).cit.put("question-1", "What year is it?");
            ((ControllerActivity) getActivity()).cit.put("question-2", "What month is it?");
            ((ControllerActivity) getActivity()).cit.put("question-3", "John, Smith, 42, High Street, Bedford");
            ((ControllerActivity) getActivity()).cit.put("question-4", "About what time is it? (Picker is in 24-Hour format)");
            ((ControllerActivity) getActivity()).cit.put("question-5", "Count BACKWARD from 10 to 1");
            ((ControllerActivity) getActivity()).cit.put("question-6", "Select the months of the year in REVERSE. The last month of the year is:");
            ((ControllerActivity) getActivity()).cit.put("question-7", "Repeat the name and address you were asked to remember");
        }
        return questions;
    }

    private void initialiseAnswers()
    {
        currentDate = Calendar.getInstance().getTime();

        System.out.println("CURRENT TIME: " + currentDate);
        currentYr = Calendar.getInstance().get(Calendar.YEAR);
        currentMonth = Calendar.getInstance().get(Calendar.MONTH);
        currentHr = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        currentMin = Calendar.getInstance().get(Calendar.MINUTE);
        if(((ControllerActivity) getActivity()).getTestType().toString().equals("BOTH")) {

            ((ControllerActivity) getActivity()).both.put("date-taken", String.format("%1$tb %1$td %1$tY at %1$tI:%1$tM %1$Tp", currentDate) + " Android");
        }
        else
        {

            ((ControllerActivity) getActivity()).cit.put("date-taken", String.format("%1$tb %1$td %1$tY at %1$tI:%1$tM %1$Tp", currentDate) + " Android");
        }
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }



}
