package com.example.android.math4kids;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import static android.icu.text.RelativeDateTimeFormatter.Direction.THIS;

public class MainActivity extends AppCompatActivity {

    // minimum number for the random range
    int minNumber=1;
    // maximum number for the random range
    int maxNumber=10;
    //variables that store 2 random numbers
    int number1;
    int number2;
    //variables that store user answers
    int answer1;
    int answer2;
    int answer3;
    int answer4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //When the app starts it calls the function that initializes values
        start();
    }

    //Initialize values with the help of 2 methods
    public void start() {
        randomNumbers();
        setInitialValues();
    }

    // calculates 2 random numbers within the range set by minNumber and maxNumber
    private void randomNumbers(){
        Random rand = new Random();
        number1 = rand.nextInt(maxNumber-minNumber)+minNumber;
        number2 = rand.nextInt(maxNumber-minNumber)+minNumber;
    }
    //Sets the initial values for calculations to the appropriate TextViews
    private void setInitialValues(){
        //for addition
        TextView add1NumberText = (TextView) findViewById(R.id.add1NumberText);
        add1NumberText.setText(number1);
        TextView add2NumberText = (TextView) findViewById(R.id.add2NumberText);
        add2NumberText.setText(number2);

        //for subtraction
        TextView subtract1NumberText = (TextView) findViewById(R.id.subtract1NumberText);
        subtract1NumberText.setText(number1);
        TextView subtract2NumberText = (TextView) findViewById(R.id.subtract2NumberText);
        subtract2NumberText.setText(number2);

        //for multiplication
        TextView multi1NumberText = (TextView) findViewById(R.id.multi1NumberText);
        multi1NumberText.setText(number1);
        TextView multi2NumberText = (TextView) findViewById(R.id.multi2NumberText);
        multi2NumberText.setText(number2);

        //for inequality
        TextView ineq1NumberText = (TextView) findViewById(R.id.ineq1NumberText);
        ineq1NumberText.setText(number1);
        TextView ineq2NumberText = (TextView) findViewById(R.id.ineq2NumberText);
        ineq2NumberText.setText(number2);

    }

    //Gets answers of users
    private void getAnswers(){

        //for addition
        TextView answer1EditText = (TextView) findViewById(R.id.answer1EditText);
        answer1 = answer1EditText.getInputType();

        //for subtraction
        TextView answer2EditText = (TextView) findViewById(R.id.answer2EditText);
        answer2 = answer1EditText.getInputType();

        //for multiplication
        TextView answer3EditText = (TextView) findViewById(R.id.answer3EditText);
        answer3 = answer1EditText.getInputType();

        //for inequality we take as an answer the id of the chosen radio button
        RadioGroup answer4RadioGroup = (RadioGroup) findViewById(R.id.answer4RadioGroup);
        answer4 =  answer4RadioGroup.getCheckedRadioButtonId();
    }

    //Checks whether answers are true or false
    public void checkAnswers(){

        getAnswers();

        //set initial values for wright(true) or wrong false) results
        String result1 = "Wrong";
        String result2 = "Wrong";
        String result3 = "Wrong";
        String result4 = "Wrong";
        String allResults = "";
        int numOfRightAnswers=0;

        //for addition
        if (answer1 == number1 + number2){
            result1 = "Right";
            numOfRightAnswers+=1;
        }

        //for subtraction
        if (answer2 == number1 - number2) {
            result2 = "Right";
            numOfRightAnswers+=1;
        }

        //for multiplication
        if (answer3 == number1 * number2) {
            result3 = "Right";
            numOfRightAnswers+=1;
        }

        //for inequality
        if (answer4 == R.id.trueRadioButton && number1>number2){
            result4 = "Right";
            numOfRightAnswers+=1;
        }
        else if (answer4 == R.id.falseRadioButton && number1<number2){
            result4 = "Right";
            numOfRightAnswers+=1;
        }

        if (numOfRightAnswers == 4){
            allResults = getString(R.string.results_Toast_Message1);
        }
        else{
            allResults = getString(R.string.results_Toast_Message2);
        }
        displayResults(result1, result2, result3, result4, allResults);
    }

    /** Displays results
     *
     *@param result1 for addition's true/false result
     *@param result2 for subtraction's true/false result
     *@param result3 for multiplication's true/false result
     *@param result4 for inequality's true/false result
    */
    private void displayResults(String result1, String result2, String result3, String result4, String allResults){
        String message = "\n" + getString(R.string.message1, result1);
        message += "\n" + getString(R.string.message2, result2);
        message += "\n" + getString(R.string.message3, result3);
        message += "\n" + getString(R.string.message4, result4);
        TextView results = (TextView) findViewById(R.id.resultsText);
        results.setText(message);
        Toast.makeText(this, allResults, Toast.LENGTH_SHORT);
    }
}
