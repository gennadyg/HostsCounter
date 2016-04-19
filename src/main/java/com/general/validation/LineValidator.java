package com.general.validation;

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
        }
        return ret;
    }
}
