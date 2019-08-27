package com.ella.dementiatest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class QuestionsFragment extends Fragment {
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
    RadioGroup scidsRG;
    RadioButton noRGBtn;
    RadioButton bitRGBtn;
    RadioButton somewhatRGBtn;
    RadioButton worseRGBtn;

    int qTracker;
    int SCIDSScore;

//    private OnFragmentInteractionListener mListener;

    public QuestionsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QuestionsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuestionsFragment newInstance(String param1, String param2) {
        QuestionsFragment fragment = new QuestionsFragment();
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
        View view =  inflater.inflate(R.layout.fragment_questions, container, false);


        final List<String> questions = initialiseList();
        qTracker = 0;

        qTextView = (TextView) view.findViewById(R.id.question_textview);
        trackerTextView = (TextView) view.findViewById(R.id.tracker_textview);
        scidsRG = (RadioGroup) view.findViewById(R.id.scids_options) ;
        noRGBtn = (RadioButton) view.findViewById(R.id.scids_no);
        bitRGBtn = (RadioButton) view.findViewById(R.id.scids_bit);
        somewhatRGBtn = (RadioButton) view.findViewById(R.id.scids_somewhat);
        worseRGBtn = (RadioButton) view.findViewById(R.id.scids_worse);

        qTextView.setText(questions.get(qTracker));
        trackerTextView.setText((qTracker + 1) + " of  "+ questions.size());

        nextBtn = (Button) view.findViewById(R.id.next_button);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedRadioButtonID = scidsRG.getCheckedRadioButtonId();
                noRGBtn.setSelected(false);
                bitRGBtn.setSelected(false);
                somewhatRGBtn.setSelected(false);
                worseRGBtn.setSelected(false);
                if (selectedRadioButtonID != -1 || ( noRGBtn.isChecked() || bitRGBtn.isChecked() || somewhatRGBtn.isChecked() || worseRGBtn.isChecked()))
                {
                    switch (selectedRadioButtonID)
                    {
                        case R.id.scids_no:
                        {
                            noRGBtn.setChecked(true);
                            SCIDSScore += 0;
                            ((ControllerActivity) getActivity()).scids.put("answer-" + (qTracker + 1), "No, there has been no change");
                            ((ControllerActivity) getActivity()).scids.put("score-" + (qTracker + 1), 0);
                            ((ControllerActivity) getActivity()).both.put("answer-" + (qTracker + 1), "No, there has been no change");
                            ((ControllerActivity) getActivity()).both.put("score-" + (qTracker + 1), 0);
                            break;
                        }
                        case R.id.scids_bit:
                        {
                            bitRGBtn.setChecked(true);
                            SCIDSScore += 1;
                            ((ControllerActivity) getActivity()).scids.put("answer-" + (qTracker + 1), "A bit worse");
                            ((ControllerActivity) getActivity()).scids.put("score-" + (qTracker + 1), 1);
                            ((ControllerActivity) getActivity()).both.put("answer-" + (qTracker + 1), "A bit worse");
                            ((ControllerActivity) getActivity()).both.put("score-" + (qTracker + 1), 1);
                            break;
                        }
                        case R.id.scids_somewhat:
                        {
                            somewhatRGBtn.setChecked(true);
                            SCIDSScore += 2;
                            ((ControllerActivity) getActivity()).scids.put("answer-" + (qTracker + 1), "Somewhat worse");
                            ((ControllerActivity) getActivity()).scids.put("score-" + (qTracker + 1), 2);
                            ((ControllerActivity) getActivity()).both.put("answer-" + (qTracker + 1), "Somewhat worse");
                            ((ControllerActivity) getActivity()).both.put("score-" + (qTracker + 1), 2);
                            break;
                        }
                        case R.id.scids_worse:
                        {
                            worseRGBtn.setChecked(true);
                            SCIDSScore += 3;
                            ((ControllerActivity) getActivity()).scids.put("answer-" + (qTracker + 1), "Much worse");
                            ((ControllerActivity) getActivity()).scids.put("score-" + (qTracker + 1), 3);
                            ((ControllerActivity) getActivity()).both.put("answer-" + (qTracker + 1), "Much worse");
                            ((ControllerActivity) getActivity()).both.put("score-" + (qTracker + 1), 3);
                            break;
                        }
                        default:
                        {
                            Toast toast = Toast.makeText(getContext(), "Please select an answer.", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }
                    if(qTracker < questions.size()-1)
                    {
                        ((ControllerActivity) getActivity()).scids.put("question-" + (qTracker + 1),  questions.get(qTracker));
                        ((ControllerActivity) getActivity()).both.put("question-" + (qTracker + 1),  questions.get(qTracker));
                        qTracker++;
                        qTextView.setText("" + questions.get(qTracker));
                        trackerTextView.setText((qTracker + 1) + " of " + questions.size());

                    }
                    else{
                        System.out.println("SCORE: " +  SCIDSScore);

                        if(((ControllerActivity) getActivity()).getTestType().toString().equals("INFORMANT"))
                        {
                            ((ControllerActivity) getActivity()).scids.put("totalscore", SCIDSScore);
                            ControllerActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new SubmitFragment(), null).commit();
                            ((ControllerActivity) getActivity()).setFinalSCIDSScore(SCIDSScore);
                        }
                        else if(((ControllerActivity) getActivity()).getTestType().toString().equals("BOTH"))
                        {
                            ((ControllerActivity) getActivity()).both.put("scidsscore", SCIDSScore);
                            ControllerActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new CITFragment(), null).commit();
                            ((ControllerActivity) getActivity()).setFinalSCIDSScore(SCIDSScore);
                        }
                    }

                }
                else
                {
                    Toast toast = Toast.makeText(getContext(), "Please select an answer.", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
        return view;
    }

     public List<String> initialiseList()
     {
         List<String> questions = new ArrayList<String>();
         questions.add("Forgetting dates to do something, e.g., paying bills, appointments or when something was done,e.g., going on an outing, when visitors come.");
         questions.add("Forgetting where he/she put something.");
         questions.add("Forgetting what someone just told him/her.");
         questions.add("Forgetting his/her address or telephone number.");
         questions.add("Forgetting where things are usually kept.");
         questions.add("Not knowing where to find things that have been put in a different place than usual.");
         questions.add("Forgetting things about family and friends, e.g., where friends live, social occasions that may have happened in the past.");
         questions.add("Not recognizing the faces of people he/she knows, e.g., friends, neighbors.");
         questions.add("Forgetting what day, month and year it is.");
         questions.add("Forgetting whether it was breakfast or dinner at the appropriate times.");
         questions.add("Losing his/her way around places that are familiar to him/her, e.g., the local shops, when driving in places that are familiar to him/her (visiting family and friends), or in the home (finding where the bathroom is).");
          questions.add("Losing his/her way around places outside his/her usual neighbourhood, e.g., the city");
         return questions;
     }

    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

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
