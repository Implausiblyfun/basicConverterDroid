package com.example.folly.basicconverterandroid;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class sillyStart extends Activity {
    // Note having all these fields is bad but I was just doing a fast setup
    Context context;
    Bundle thisBundle;
    EditText celcius;
    EditText farenheit;
    EditText descriptor;
    int flag;
    int nonUserFlag;
    unitConverterHelper converter;

    public void onCreate(Bundle savedStuff) {
        super.onCreate(savedStuff);
        // This part isnt important yet but i just dump it in
        this.thisBundle = savedStuff;
        this.context = getApplicationContext();

        // This sets up the ability to use all the unitConverter methods
        this.converter = new unitConverterHelper();

        // Sets up the view and links to the buttons
        setContentView(R.layout.activity_silly_start);
        celcius = (EditText) findViewById(R.id.celciusInput);
        farenheit = (EditText) findViewById(R.id.farenheitInput);
        descriptor = (EditText) findViewById(R.id.conversionIndicator);

        flag = 0; // Start with direction celc -> faren
        nonUserFlag = 0;

        // Creates the listeners for the buttons
        convertDirection(celcius, 0);
        convertDirection(farenheit, 1);
        findViewById(R.id.convertButton).setOnClickListener(convert);
    }


    public void convertDirection(EditText entry, final int type){
        entry.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(nonUserFlag == 1){return;}//dont set stuff if its not the user
                if(type ==  0){
                    descriptor.setText("C to F");
                    flag = 0;
                }
                if(type == 1){
                    descriptor.setText("F to C");
                    flag = 1;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }



    private final View.OnClickListener convert;
    {
        convert = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String celciusStr   = celcius.getText().toString();
                String farenheitStr =  farenheit.getText().toString();
                String outStr = converter.convert(celciusStr, farenheitStr, flag);
                //Toast.makeText(getApplicationContext(), Integer.toString(flag), Toast.LENGTH_LONG).show(); //debug output method for android
                nonUserFlag = 1;
                if(flag == 1){
                    celcius.setText(outStr);
                    //Toast.makeText(getApplicationContext(), outStr, Toast.LENGTH_SHORT).show(); //debug output method for android
                }
                if(flag == 0){
                    farenheit.setText(outStr);
                    //Toast.makeText(getApplicationContext(), "faren", Toast.LENGTH_SHORT).show(); //debug
                }
                nonUserFlag = 0;
            }
        };
    }

}
