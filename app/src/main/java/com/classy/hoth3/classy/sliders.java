package com.classy.hoth3.classy;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.*;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.Button;
import android.widget.SeekBar;
import android.content.Intent;

import static android.widget.Toast.*;

public class sliders extends AppCompatActivity {

    private Spinner spinner;
    private static final String[]subjects = {"Computer Science", "Electrical Engineering", "Physics", "Scandinavian"};

    //Arguments to pass to next activity
    private int rating, easy, work, clarity, help = 1;
    private String subject_to_pass = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliders);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        spinner = (Spinner)findViewById(R.id.spinner_subject);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(sliders.this, android.R.layout.simple_spinner_dropdown_item, subjects);

        //spinner for selecting a Subject from drop down list
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                subject_to_pass = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                ToastIfNothingSelected();
            }
        });

        Button goBtn = (Button) findViewById(R.id.goBtn);
        goBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (subject_to_pass == "") // if no subject selected, make toast and do nothing
                    ToastIfNothingSelected();
                else  // else go to next activity
                {
                    ////// TO DO
                    //Intent nextIntent = new Intent(this, sliders.class);

                    //Create bundle to pass rating values and subject to next activity
                    Bundle bundle = new Bundle();
                    bundle.putInt("overall", rating);
                    bundle.putInt("easy", easy);
                    bundle.putInt("work", work);
                    bundle.putInt("clarity", clarity);
                    bundle.putInt("help", help);
                    bundle.putString("subject", subject_to_pass);
                    //Add the bundle to the intent
                    //nextIntent.putExtras(bundle);
                    //startActivity(nextIntent);

                }
           }
        });
        // change values of selected rating when that seek bar's progress changes
        class customSkBarListener implements SeekBar.OnSeekBarChangeListener {

            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                int identifier = seekBar.getId();
                switch (identifier)
                {
                    case R.id.seekBar_rating:
                        rating = progress+1;
                        break;
                    case R.id.seekBar_easy:
                        easy = progress+1;
                        break;
                    case R.id.seekBar_work:
                        work = progress+1;
                        break;
                    case R.id.seekBar_clarity:
                        clarity = progress+1;
                        break;
                    case R.id.seekBar_help:
                        help = progress+1;
                        break;
                    default:
                        break;
                }

            }

            public void onStartTrackingTouch(SeekBar seekBar) {}

            public void onStopTrackingTouch(SeekBar seekBar) {}

        }
        SeekBar skbrRating =(SeekBar)findViewById(R.id.seekBar_rating);
        skbrRating.setOnSeekBarChangeListener(new customSkBarListener());
        SeekBar skbrEasy = (SeekBar)findViewById(R.id.seekBar_easy);
        skbrEasy.setOnSeekBarChangeListener(new customSkBarListener());
        SeekBar skbrWork = (SeekBar)findViewById(R.id.seekBar_work);
        skbrWork.setOnSeekBarChangeListener(new customSkBarListener());
        SeekBar skbrClr = (SeekBar)findViewById(R.id.seekBar_clarity);
        skbrClr.setOnSeekBarChangeListener(new customSkBarListener());
        SeekBar skbrHelp = (SeekBar)findViewById(R.id.seekBar_help);
        skbrHelp.setOnSeekBarChangeListener(new customSkBarListener());


    }


    void ToastIfNothingSelected(){
        makeText(getApplicationContext(), "Please select a subject.",
                LENGTH_SHORT).show();
    }

}