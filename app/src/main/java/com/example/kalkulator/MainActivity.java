package com.example.kalkulator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button nr1, nr2,nr3, nr4,nr5, nr6,nr7, nr8,nr9, nr0,
    CE,back,divide,percent,sqrt,sqr,multiply,minus,plus,equal,
    plusMinus,comma,reciprocal;

    double x1 = 0,x2 = 0;
    int codeAction = 0;
    TextView screen;

    boolean writeMode = true,
            firstAction = true;

    View.OnClickListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findControls();
        createListener();
        addListener();
    }

    private void createListener() {

        listener = new View.OnClickListener(){

            @Override
            public void onClick(View v) {

            int id = v.getId();
            switch (id){

                case R.id.nr0: addDigit( "0"); break;
                case R.id.nr1 : addDigit( "1"); break;
                case R.id.nr2 : addDigit( "2"); break;
                case R.id.nr3 : addDigit( "3"); break;
                case R.id.nr4 : addDigit( "4"); break;
                case R.id.nr5 : addDigit( "5"); break;
                case R.id.nr6 : addDigit( "6"); break;
                case R.id.nr7 : addDigit( "7"); break;
                case R.id.nr8 : addDigit( "8"); break;
                case R.id.nr9 : addDigit( "9"); break;
                /////////////////////////////////////////
                case R.id.plus : action(1); break;
                case R.id.minus : action(2); break;
                case R.id.multiply : action(3); break;
                case R.id.divide: action(4); break;
                case R.id.equal : action(0); break;
                case R.id.back : cancel(); break;
                case R.id.plusMinus : changeDigit(); break;
                case R.id.comma : addComma(); break;
                case R.id.percent : function(1); break;
                case R.id.sqrt : function(2); break;
                case R.id.sqr : function(3); break;
                case R.id.reciprocal: function(4); break;
                case  R.id.CE : delete(); break;
             }

            }


        };
    }

    private void findControls() {

        nr0 = findViewById(R.id.nr0);
        nr1 = findViewById(R.id.nr1);
        nr2 = findViewById(R.id.nr2);
        nr3 = findViewById(R.id.nr3);
        nr4 = findViewById(R.id.nr4);
        nr5 = findViewById(R.id.nr5);
        nr6 = findViewById(R.id.nr6);
        nr7 = findViewById(R.id.nr7);
        nr8 = findViewById(R.id.nr8);
        nr9 = findViewById(R.id.nr9);
        /////////////////////////////
        back = findViewById(R.id.back);
        CE= findViewById(R.id.CE);
        divide = findViewById(R.id.divide);
        percent = findViewById(R.id.percent);
        sqrt = findViewById(R.id.sqrt);
        sqr = findViewById(R.id.sqr);
        multiply = findViewById(R.id.multiply);
        minus = findViewById(R.id.minus);
        plus = findViewById(R.id.plus);
        equal = findViewById(R.id.equal);
        plusMinus = findViewById(R.id.plusMinus);
        comma = findViewById(R.id.comma);
        reciprocal = findViewById(R.id.reciprocal);
        screen= findViewById(R.id.wyswietlacz);



    }

    private void addListener() {

        nr0.setOnClickListener(listener);
        nr1.setOnClickListener(listener);
        nr2.setOnClickListener(listener);
        nr3.setOnClickListener(listener);
        nr4.setOnClickListener(listener);
        nr5.setOnClickListener(listener);
        nr6.setOnClickListener(listener);
        nr7.setOnClickListener(listener);
        nr8.setOnClickListener(listener);
        nr9.setOnClickListener(listener);
        /////////////////////////////
        back.setOnClickListener(listener);
        CE.setOnClickListener(listener);
        divide.setOnClickListener(listener);
        percent.setOnClickListener(listener);
        sqrt.setOnClickListener(listener);
        sqr.setOnClickListener(listener);
        multiply.setOnClickListener(listener);
        minus.setOnClickListener(listener);
        plus.setOnClickListener(listener);
        equal.setOnClickListener(listener);
        plusMinus.setOnClickListener(listener);
        comma.setOnClickListener(listener);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            reciprocal.setOnClickListener(listener);

    }

    private void addDigit(String s){

    if(writeMode){
        String temp = screen.getText().toString();
        if(temp.equals("0")) temp="0";
        screen.setText(temp+s);

    }else {
        screen.setText(s);
        writeMode = true;

    }

    }

    private void remove(){

        String s = screen.getText().toString();
        if(s.length()>1){
           s.substring(0,s.length()-1);

        }else {
           s = "0";
           screen.setText(s);

        }

    }

    private void action(int codeButton){
        double score = 0;
        if(firstAction){
            x1 = Double.parseDouble(screen.getText().toString());
            firstAction = false;
        }else{
            x2 = Double.parseDouble(screen.getText().toString());

            switch ((codeAction)){
                case 1 : score = x1+x2; break;
                case 2 : score = x1-x2; break;
                case 3 : score = x1*x2; break;
                case 4 : score = x1/x2; break;
            }
            x1 = score;
            screen.setText(String.valueOf(score));
        }
        codeAction = codeButton;
        if(codeButton == 0 ){
            firstAction = true;
        }
        writeMode = false;

    }

    private void show(double w){
        w = Math.round(w*100000)/100000.0;
        String s = String.valueOf(w);
        if(s.substring(s.length()-2).equals(".0"))
            s = s.substring(0,s.length()-2);
        screen.setText(s);
    }

    private void function(int codeButton){
        double score = 0;
        Double x = Double.parseDouble(screen.getText().toString());
        switch (codeButton){
            case 1 : score = x*100; break;
            case 2 : score =  Math.sqrt(x); break;
            case 3 : score = x*x; break;
            case 4 : score = 1/x; break;
        }
        show(score);
        writeMode = false;
    }

    private void changeDigit() {
        String s = screen.getText().toString();
        double w = Double.valueOf(s);
        show(-w);

    }

    private void delete(){
        screen.setText("0");
        firstAction = true;
        codeAction = 0;
        writeMode = true;
    }

    private void cancel(){
        String s = screen.getText().toString();
        if(s.length()>1){
            s = s.substring(0,s.length()-1);
        }else{
            s = "0";
        }
        screen.setText(s);
    }

    private void addComma(){
        if(writeMode){
            String s = screen.getText().toString();
            if(s.indexOf(".")<0){
                s+=".";
                screen.setText(s);
            }
        }else{
            screen.setText("0.");
            writeMode = true;
        }
    }
}