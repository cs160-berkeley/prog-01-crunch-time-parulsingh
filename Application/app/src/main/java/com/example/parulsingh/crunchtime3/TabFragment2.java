package com.example.parulsingh.crunchtime3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.graphics.Color;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import android.widget.TextView;

public class TabFragment2 extends Fragment {
    Button button;
    String exerciseType;
    FragmentActivity currActivity = getActivity();
    public static String allExercises[] = new String[]{"Pushups", "Situps", "Squats", "Leg-lifts", "Planks",
            "Jumping Jacks", "Pullups", "Cycling", "Walking", "Jogging", "Swimming", "Stair-Climbing"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_fragment_2, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        addListenerOnButton();
    }
    public void addListenerOnButton() {

        button = (Button) getView().findViewById(R.id.calcAmount);

        button.setOnClickListener(new View.OnClickListener() {
            TextView calsQuant = (TextView) getView().findViewById(R.id.calsQuant);
            @Override
            public void onClick(View arg0) {
                int calories = Integer.parseInt(calsQuant.getText().toString());
                LinearLayout layout = (LinearLayout)getView().findViewById(R.id.scrollLinear);
                if (layout.findViewById(new Integer(500)) == null) {
                    EditText texty = new EditText(getActivity());
                    texty.setId(new Integer(500));
                    texty.setTextColor(Color.parseColor("#FFFFFF"));
                    texty.setText("Burn " + calories + " calories by doing:");
                    texty.setBackgroundColor(Color.TRANSPARENT);
                    texty.setFocusable(false);
                    layout.addView(texty);
                }
                int i = 0;
                for (String elem : allExercises){
                    layout.removeView(layout.findViewById(i));
                    EditText myEditText = new EditText(getActivity()); // Pass it an Activity or Context
                    myEditText.setId(i);
                    myEditText.setTextColor(Color.parseColor("#FFFFFF"));
                    myEditText.setBackgroundColor(Color.TRANSPARENT);
                    myEditText.setFocusable(false);
                    myEditText.setText(calsToQuant(elem, calories) + " of " + elem);
                    myEditText.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)); // Pass two args; must be LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, or an integer pixel value.
                    layout.addView(myEditText);
                    i++;
                }
            }

        });
    }

    private String calsToQuant(String type, double cals){
        double calsDouble = (double)cals/100;
        if (type.equals("Pushups")){
            return String.valueOf(calsDouble*350) + " reps";
        } else if (type.equals("Situps")) {
            return calsDouble*200 + " reps";
        } else if (type.equals("Squats")) {
            return calsDouble*225 + " reps";
        } else if (type.equals("Pullups")) {
            return calsDouble*100 + " reps";
        } else if (type.equals("Leg-lifts")) {
            return calsDouble*25 + " minutes";
        } else if (type.equals("Planks")) {
            return calsDouble*25 + " minutes";
        } else if (type.equals("Jumping Jacks")) {
            return calsDouble*10 + " minutes";
        } else if (type.equals("Cycling")) {
            return calsDouble*12 + " minutes";
        } else if (type.equals("Walking")) {
            return calsDouble*20 + " minutes";
        } else if (type.equals("Jogging")) {
            return calsDouble*12 + " minutes";
        } else if (type.equals("Swimming")) {
            return calsDouble*13 + " minutes";
        } else if (type.equals("Stair-Climbing")) {
            return calsDouble*15 + " minutes";
        }
        return 0 + " minutes";
    }
}