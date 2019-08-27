package com.ella.dementiatest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
//
//import com.gigamole.library.PulseView;
//
//import pl.bclogic.pulsator4droid.library.PulsatorLayout;


public class ProfileFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Button toQuestBtn;
    EditText ageEditText;
    RadioGroup genderRG;
    RadioButton maleRB;
    RadioButton femaleRB;
    RadioButton othersRB;
    Spinner ethSpinner;
    Spinner educSpinner;
    int mAge;
    String mGender;
    String mEth;
    String mEduc;

//    private OnFragmentInteractionListener mListener;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        View view = inflater.inflate(R.layout.fragment_profile, container, false);


        genderRG = (RadioGroup) view.findViewById(R.id.gender_rbgroup);
        ethSpinner = (Spinner) view.findViewById(R.id.ethnicity_spinner);
        educSpinner = (Spinner) view.findViewById(R.id.educ_spinner);

//        takerSpinner = (Spinner) view.findViewById(R.id.taker_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> ethAdapter = ArrayAdapter.createFromResource(getContext(), R.array.ethnicity_array, R.layout.spinner_item);
        ethAdapter.setDropDownViewResource(R.layout.spinner_item);
        ethSpinner.setAdapter(ethAdapter);

        ArrayAdapter<CharSequence> educAdapter = ArrayAdapter.createFromResource(getContext(), R.array.educ_array, R.layout.spinner_item);
        educAdapter.setDropDownViewResource(R.layout.spinner_item);
        educSpinner.setAdapter(educAdapter);

        ageEditText = (EditText) view.findViewById(R.id.age_edittext);

        toQuestBtn = view.findViewById(R.id.toquestions_button);
        toQuestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getInputs();

                if(!isAllFilled())
                {
                    Toast toast = Toast.makeText(getContext(), "Please fill up all the fields.", Toast.LENGTH_LONG);
                    toast.show();
                }
                else {
                    if (!isAgeValid())
                    {
                        // MOD ETM 11232018 Limit age to 110
                        Toast toast = Toast.makeText(getContext(), "Please enter the age between 60 and 110", Toast.LENGTH_LONG);
                        toast.show();
                    }
                    else
                    {
                        //ETM Add 23052019 For Educ Level
                        ((ControllerActivity) getActivity()).cit.put("age", mAge);
                        ((ControllerActivity) getActivity()).cit.put("ethnicity", mEth);
                        ((ControllerActivity) getActivity()).cit.put("gender", mGender);
                        ((ControllerActivity) getActivity()).cit.put("educationLevel", mEduc);
                        ((ControllerActivity) getActivity()).scids.put("age", mAge);
                        ((ControllerActivity) getActivity()).scids.put("ethnicity", mEth);
                        ((ControllerActivity) getActivity()).scids.put("gender", mGender);
                        ((ControllerActivity) getActivity()).scids.put("educationLevel", mEduc);
                        ((ControllerActivity) getActivity()).both.put("age", mAge);
                        ((ControllerActivity) getActivity()).both.put("ethnicity", mEth);
                        ((ControllerActivity) getActivity()).both.put("gender", mGender);
                        ((ControllerActivity) getActivity()).both.put("educationLevel", mEduc);
                        ((ControllerActivity) getActivity()).startQuestion();
                        ControllerActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new TakerFragment(), null).commit();
                    }
                }

            }
        });

        return view;
    }

    private void getInputs()
    {
        String val = ageEditText.getText().toString();

        if(val == "" || val.equals(""))
        {
            mAge = 0;
        }
        else {
            mAge = Integer.parseInt(val);
        }

        int selectedRadioButtonID = genderRG.getCheckedRadioButtonId();

        if (selectedRadioButtonID != -1) {
            switch (selectedRadioButtonID) {
                case R.id.male_rb: {
                    mGender = "Male";
                    break;
                }
                case R.id.female_rb: {
                    mGender = "Female";
                    break;
                }
                case R.id.others_rb: {
                    mGender = "Others";
                    break;
                }
            }
        }
        else
        {
            mGender = "None";
        }

        mEth = ethSpinner.getSelectedItem().toString();
        mEduc = educSpinner.getSelectedItem().toString(); //ETM Add 23052019
        Date currentDate = Calendar.getInstance().getTime();
        ((ControllerActivity) getActivity()).scids.put("date-taken", String.format("%1$tb %1$td %1$tY at %1$tI:%1$tM %1$Tp", currentDate) + " Android");
    }

    private boolean isAllFilled()
    {
        if(mAge == 0 || mGender.equals("None") )
        {
            return false;
        }
        else {
            return true;
        }
    }

    private boolean isAgeValid()
    {
        // ETM ADD 110 limit
        if(mAge >= 60 && mAge <= 110 )
        {
            return true;
        }
        else {
            return false;
        }
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
//
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
