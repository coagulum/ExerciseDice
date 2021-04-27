package com.example.dice;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class MainActivity extends AppCompatActivity{

    private TextView txtValue;
    private Button btnRoll;
    private Button btnAddExercise;
    private EditText edtAddExercise;
    private Button btnRemExercise;
    private EditText edtRemExercise;

    private ArrayList<String> exerciseItems;
    private ListView lsExercise;
    private ArrayAdapter<String> exerciseAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtValue = findViewById(R.id.txtValue);
        btnRoll = findViewById(R.id.btnRoll);
        btnAddExercise = findViewById(R.id.btnAddExercise);
        edtAddExercise = findViewById(R.id.edtAddExercise);
        btnRemExercise = findViewById(R.id.btnRemExercise);
        edtRemExercise = findViewById(R.id.edtRemExercise);
        lsExercise = (ListView) findViewById(R.id.lsExercise);

        ArrayList<String> exerciseList = new ArrayList<>();

        exerciseList.add("Push ups");
        exerciseList.add("Squat");
        exerciseList.add("Lunges");
        exerciseList.add("Burpees");

        ArrayList<String> exerciseDuration = new ArrayList<String>();

        exerciseDuration.add("30 seconds");
        exerciseDuration.add("45 seconds");
        exerciseDuration.add("60 seconds");
        exerciseDuration.add("75 seconds");
        exerciseDuration.add("90 seconds");
        exerciseDuration.add("105 seconds");
        exerciseDuration.add("120 seconds");

        exerciseItems = new ArrayList<String>();
        exerciseAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, exerciseItems);

        lsExercise.setAdapter(exerciseAdapter);

        btnRoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (exerciseList.isEmpty()) {
                    Toast.makeText(MainActivity.this, "There are no exercises", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(MainActivity.this, "Rolling...", Toast.LENGTH_SHORT).show();

                    Random randomEx = new Random();
                    int randomExercise = randomEx.nextInt(exerciseList.size());

                    Random randomDur = new Random();
                    int randomDuration = randomDur.nextInt(exerciseDuration.size());

                    txtValue.setText("Exercise: " + exerciseList.get(randomExercise) + " Duration: " + exerciseDuration.get(randomDuration));

                    exerciseItems.add(exerciseList.get(randomExercise) + " " + exerciseDuration.get(randomDuration));
                }
            }
        });

        btnAddExercise.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if (edtAddExercise.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "You can't exercise with nothing", Toast.LENGTH_SHORT).show();
                } else {
                    String strExercise = edtAddExercise.getText().toString();
                    Toast.makeText(MainActivity.this, "Added " + strExercise, Toast.LENGTH_SHORT).show();
                    exerciseList.add(strExercise);
                    InputMethodManager inputManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                    edtAddExercise.getText().clear();
                }
            }
        });

        btnRemExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strRemExercise = edtRemExercise.getText().toString();
                if (edtRemExercise.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "You can't remove nothing", Toast.LENGTH_SHORT).show();
                } else if (exerciseList.contains(strRemExercise)){
                    Toast.makeText(MainActivity.this, "Removed " + strRemExercise, Toast.LENGTH_SHORT).show();
                    exerciseList.remove(strRemExercise);
                    edtRemExercise.onEditorAction(EditorInfo.IME_ACTION_DONE);
                    edtRemExercise.getText().clear();
                } else {
                    Toast.makeText(MainActivity.this, "exercise doesn't exist", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnRemExercise.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(MainActivity.this, "Removed all exercises", Toast.LENGTH_SHORT).show();
                exerciseList.clear();
                return true;
            }
        });
    }

}