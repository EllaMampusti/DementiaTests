package com.ella.dementiatest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class TakerFragment extends Fragment {

//    private OnFragmentInteractionListener mListener;
    CardView selfBtn;
    CardView informantBtn;
    CardView bothBtn;


    public TakerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_taker, container, false);

        selfBtn = (CardView) view.findViewById(R.id.self_card);
        informantBtn = (CardView) view.findViewById(R.id.inform_card);
        bothBtn = (CardView) view.findViewById(R.id.both_card);

        selfBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ControllerActivity) getActivity()).setTest("SELF");
                ControllerActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new CITFragment(), null).commit();
            }
        });

        informantBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ControllerActivity) getActivity()).setTest("INFORMANT");
                ControllerActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new QuestionsFragment(), null).commit();
            }
        });

        bothBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ControllerActivity) getActivity()).setTest("BOTH");
                ControllerActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new QuestionsFragment(), null).commit();
            }
        });

        return view;

    }

//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
//
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
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
