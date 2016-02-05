package com.example.parulsingh.crunchtime3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.graphics.Color;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class TabFragment1 extends Fragment {
    Button button;
    String exerciseType;
    FragmentActivity currActivity = getActivity();

    public static String allExercises[] = new String[]{"Pushups", "Situps", "Squats", "Leg-lifts", "Planks",
            "Jumping Jacks", "Pullups", "Cycling", "Walking", "Jogging", "Swimming", "Stair-Climbing"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_fragment_1, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        CharSequence text = "Select exercise and input quantity to calculate calories burned!";
        int duration = Toast.LENGTH_LONG;
//        Toast toast = Toast.makeText(getActivity(), text, duration);
//        toast.setGravity(Gravity.TOP| Gravity.CENTER_HORIZONTAL, 0, 0);
//        toast.show();
//        toast.show();
        addListenerOnButton();
        Spinner spinner = (Spinner) getView().findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.exercises, R.layout.spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.spinner_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner
                .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int pos, long id) {

                        exerciseType = arg0.getItemAtPosition(pos)
                                .toString();
                        TextView repsOrMin = (TextView) getView().findViewById(R.id.repsOrMin);
                        if (pos <= 3){
                            repsOrMin.setText("reps");
                        } else {
                            repsOrMin.setText("minutes");
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                        // TODO Auto-generated method stub

                    }
                });
    }
    public void addListenerOnButton() {

        button = (Button) getView().findViewById(R.id.calcCalories);

        button.setOnClickListener(new View.OnClickListener() {
            TextView exerciseQuant = (TextView) getView().findViewById(R.id.exerciseQuantity);
            EditText calsBurned = (EditText) getView().findViewById(R.id.calsBurned);
            @Override
            public void onClick(View arg0) {
                int quantity = Integer.parseInt(exerciseQuant.getText().toString());
                calsBurned.setText("You burned " + String.valueOf(quantToCals(exerciseType, quantity)) + " calories!");
                String[] otherExercises = createArrayOfOthers(exerciseType);
                LinearLayout layout = (LinearLayout)getView().findViewById(R.id.scrollLinear);
                if (layout.findViewById(new Integer(500)) == null) {
                    EditText texty = new EditText(getActivity());
                    texty.setId(new Integer(500));
                    texty.setTextColor(Color.parseColor("#FFFFFF"));
                    texty.setText("This is equivalent to:");
                    texty.setBackgroundColor(Color.TRANSPARENT);
                    texty.setFocusable(false);
                    layout.addView(texty);
                }
                int i = 0;
                for (String elem : otherExercises){
                    layout.removeView(layout.findViewById(i));
                    EditText myEditText = new EditText(getActivity()); // Pass it an Activity or Context
                    myEditText.setId(i);
                    myEditText.setTextColor(Color.parseColor("#FFFFFF"));
                    myEditText.setBackgroundColor(Color.TRANSPARENT);
                    myEditText.setFocusable(false);
                    myEditText.setText(calsToQuant(elem, quantToCals(exerciseType, quantity)) + " of " + elem);
                    myEditText.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)); // Pass two args; must be LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, or an integer pixel value.
                    layout.addView(myEditText);
                    i++;
                }
            }

        });
    }

    private double quantToCals(String type, int quant){
        if (type.equals("Pushups")){
            return ((double)quant/350)*100;
        } else if (type.equals("Situps")) {
            return ((double)quant/200)*100;
        } else if (type.equals("Squats")) {
            return ((double)quant/225)*100;
        } else if (type.equals("Pullups")) {
            return ((double)quant/100)*100;
        } else if (type.equals("Leg-lifts")) {
            return ((double)quant/25)*100;
        } else if (type.equals("Planks")) {
            return ((double)quant/25)*100;
        } else if (type.equals("Jumping Jacks")) {
            return ((double)quant/10)*100;
        } else if (type.equals("Cycling")) {
            return ((double)quant/12)*100;
        } else if (type.equals("Walking")) {
            return ((double)quant/20)*100;
        } else if (type.equals("Jogging")) {
            return ((double)quant/12)*100;
        } else if (type.equals("Swimming")) {
            return ((double)quant/13)*100;
        } else if (type.equals("Stair-Climbing")) {
            return ((double)quant/15)*100;
        }
        return 0;
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

    String[] createArrayOfOthers(String currentExercise){
        String[] ret = new String[11];
        int j = 0;
        for (int i=0; i<12; i++){
            if(!allExercises[i].equals(currentExercise)){
                ret[j] = allExercises[i];
                j++;
            }
        }
        return ret;
    }
}