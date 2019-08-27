package com.ella.dementiatest;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SubmitFragment extends Fragment {

    Button submitBtn;
    CheckBox diagnosedCB;
    CheckBox usedCB;
    int cbChecker;
    boolean stopper;

    public SubmitFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_submit, container, false);

        diagnosedCB = (CheckBox) view.findViewById(R.id.diagnosed_cb);
        usedCB = (CheckBox) view.findViewById(R.id.used_cb);
        submitBtn = (Button) view.findViewById(R.id.submit_button);
        cbChecker = 0;
        stopper = true;
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String CITclassification = "";
                String SCIDSclassification = "";
                Intent resActivity = new Intent(getContext(), ResultsActivity.class);
                //ADD If test has not been taken before, continue to submit
                for(int i=0 ; i <= 1 ; i++)
                {
                    if(diagnosedCB.isChecked())
                    {
                        System.out.println("+++++NEXT ACTIVITY");
                        if(((ControllerActivity) getActivity()).getTestType().toString().equals("SELF"))
                        {
                            ((ControllerActivity) getActivity()).cit.put("isDiagnosed", diagnosedCB.isChecked());
                            ((ControllerActivity) getActivity()).cit.put("isUsed", diagnosedCB.isChecked());
                            resActivity.putExtra("TYPE", ((ControllerActivity) getActivity()).getTestType());
                            int score = ((ControllerActivity) getActivity()).getFinalCITScore();
                            resActivity.putExtra("CIT-SCORE", score );

                            if (score >= 0 && score <= 4)
                            {
                                ((ControllerActivity) getActivity()).cit.put("class", "no");
                            }
                            else if(score >= 5 && score <= 9)
                            {
                                ((ControllerActivity) getActivity()).cit.put("class", "maybe");
                            }
                            else if(score >= 10)
                            {
                                ((ControllerActivity) getActivity()).cit.put("class", "yes");
                            }

                            ((ControllerActivity) getActivity()).citRef.push().setValue(((ControllerActivity) getActivity()).cit);
                        }
                        else if(((ControllerActivity) getActivity()).getTestType().toString().equals("INFORMANT"))
                        {
                            ((ControllerActivity) getActivity()).scids.put("isDiagnosed", diagnosedCB.isChecked());
                            ((ControllerActivity) getActivity()).scids.put("isUsed", diagnosedCB.isChecked());
                            resActivity.putExtra("TYPE", ((ControllerActivity) getActivity()).getTestType());
                            int score = ((ControllerActivity) getActivity()).getFinalSCIDSScore();
                            resActivity.putExtra("SCIDS-SCORE", score);
                            if (score <= 4)
                            {
                                ((ControllerActivity) getActivity()).scids.put("class", "no");
                            }
                            else if(score >= 5)
                            {
                                ((ControllerActivity) getActivity()).scids.put("class", "yes");
                            }

                            ((ControllerActivity) getActivity()).scidsRef.push().setValue(((ControllerActivity) getActivity()).scids);
                        }
                        else if(((ControllerActivity) getActivity()).getTestType().toString().equals("BOTH"))
                        {
                            ((ControllerActivity) getActivity()).both.put("isDiagnosed", diagnosedCB.isChecked());
                            ((ControllerActivity) getActivity()).both.put("isUsed", diagnosedCB.isChecked());
                            resActivity.putExtra("TYPE", ((ControllerActivity) getActivity()).getTestType());
                            int citscore = ((ControllerActivity) getActivity()).getFinalCITScore();
                            int scidsscore = ((ControllerActivity) getActivity()).getFinalSCIDSScore();
                            resActivity.putExtra("CIT-SCORE", citscore);
                            resActivity.putExtra("SCIDS-SCORE", scidsscore);
                            // check cit class
                            if (citscore >= 0 && citscore <= 4)
                            {
                                ((ControllerActivity) getActivity()).cit.put("class", "no");
                                CITclassification = "NO";
                            }
                            else if(citscore >= 5 && citscore <= 9)
                            {
                                ((ControllerActivity) getActivity()).cit.put("class", "maybe");
                                CITclassification = "MAYBE";
                            }
                            else if(citscore >= 10)
                            {
                                ((ControllerActivity) getActivity()).cit.put("class", "yes");
                                CITclassification = "YES";
                            }
                            //check scids class
                            if (scidsscore <= 4)
                            {
                                ((ControllerActivity) getActivity()).scids.put("class", "no");
                                SCIDSclassification = "NO";
                            }
                            else if(scidsscore >= 5)
                            {
                                ((ControllerActivity) getActivity()).scids.put("class", "yes");
                                SCIDSclassification = "YES";
                            }

                            System.out.println("CIT CLASS: " + CITclassification);
                            System.out.println("SCIDS CLASS: " + SCIDSclassification);
                            // Gte CIT class if class are different
                            if((CITclassification.equalsIgnoreCase("NO") || CITclassification.equalsIgnoreCase("MAYBE")) &&
                                    SCIDSclassification.equalsIgnoreCase("NO"))
                            {
                                ((ControllerActivity) getActivity()).both.put("cit-class", "NO");
                                ((ControllerActivity) getActivity()).both.put("scids-class", "NO");
                                ((ControllerActivity) getActivity()).both.put("class", "NO");
                                resActivity.putExtra("DESC", "NO");
                                System.out.println("BOTH CLASS: NO ");
                            }
                            else if((CITclassification.equalsIgnoreCase("YES")) &&
                                    SCIDSclassification.equalsIgnoreCase("YES"))
                            {
                                ((ControllerActivity) getActivity()).both.put("cit-class", "YES");
                                ((ControllerActivity) getActivity()).both.put("scids-class", "YES");
                                ((ControllerActivity) getActivity()).both.put("class", "YES");
                                resActivity.putExtra("DESC", "YES");
                                System.out.println("BOTH CLASS: YES ");
                            }
                            // if conflicting results
                            else if(((CITclassification.equalsIgnoreCase("NO") || CITclassification.equalsIgnoreCase("MAYBE")) &&
                                    SCIDSclassification.equalsIgnoreCase("YES")))
                            {
                                ((ControllerActivity) getActivity()).both.put("cit-class", CITclassification);
                                ((ControllerActivity) getActivity()).both.put("scids-class", SCIDSclassification);
                                ((ControllerActivity) getActivity()).both.put("class", "NO");
                                resActivity.putExtra("DESC", "NO");
                                System.out.println("BOTH CLASS: NO ");
                            }
                            else if(((CITclassification.equalsIgnoreCase("YES")) &&
                                    SCIDSclassification.equalsIgnoreCase("NO")))
                            {
                                ((ControllerActivity) getActivity()).both.put("cit-class", CITclassification);
                                ((ControllerActivity) getActivity()).both.put("scids-class", SCIDSclassification);
                                ((ControllerActivity) getActivity()).both.put("class", "YES");
                                resActivity.putExtra("DESC", "YES");
                                System.out.println("BOTH CLASS: YES ");
                            }

                            ((ControllerActivity) getActivity()).bothRef.push().setValue(((ControllerActivity) getActivity()).both);
                        }

                        startActivity(resActivity);
                    }
                    else if(!diagnosedCB.isChecked() && (cbChecker==0))
                    {
                        System.out.println("+++++NOT checked and no checker" + cbChecker);
                        cbChecker = 1;
                        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        TextView title = new TextView(getActivity());
                        title.setText("Alert");
                        title.setGravity(Gravity.CENTER);
                        builder.setMessage("Has the respondent been diagnosed with Dementia? If YES please select the check box or else you can submit the test.");
                        builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                }

            }
        });


        return view;
    }

}
