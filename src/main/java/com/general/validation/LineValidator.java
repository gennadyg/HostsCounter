package com.general.validation;

import java.util.Arrays;

/**
 * Created by Gennady on 4/19/2016.
 *
 * Class for validation lines from data file
 */
public class LineValidator {

    public static boolean isLineValueValid( String[] line, int location ){

        boolean ret = false;

        if( ( line != null ) && ( line.length > 0 ) && line.length >= location ){

            ret = true;

        }else{
            String joined = String.join("", Arrays.asList( line ));
            System.out.println("Line - " + joined + " doesn't contain data at location [" + location + "]");

        }
        return ret;
    }
}
