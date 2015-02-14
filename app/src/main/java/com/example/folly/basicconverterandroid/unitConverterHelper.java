package com.example.folly.basicconverterandroid;

/**
 * Created by Folly on 2/14/2015.
 */
public class unitConverterHelper {

    // Needs to  be implemented
    public String convert(String celc, String faren,int flag){
        Integer celcius = Integer.parseInt(celc);
        celcius +=1;
        return Integer.toString(celcius);
    }
}
