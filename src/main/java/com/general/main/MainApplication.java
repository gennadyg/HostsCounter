package com.general.main;

import com.security.data.DataReader;
import com.security.data.DataReaderFactory;
import com.security.data.sort.Sorter;

import java.util.Map;

/** Main application class to run
 *
 * Created by Gennady on 4/19/2016.
 */
public class MainApplication {

    /*
        1. Program trying to read first argument to customize file path, if such not exists, default is used.
        2. DataReaderFactory created an appropriate reader instance - file in this scenario, TODO: create others per type.
        3. Reader reads file and counts unique host names using HashMap, where key is hostname and value number of times
           that hostname appeared in file.
        4. Sorter object is used to sort out hostnames using ascending strategy.
     */
    public static void main( String[] args ){

        String fileLocation = "input/log_example.log";
        if( ( args != null ) && ( args.length == 1 ) && ( args[0] != null ) && ( args[0].length() > 0 )){

            fileLocation = args[0];
        }
        DataReader dataReader = ( new DataReaderFactory()).getReaderByType( DataReaderFactory.ReaderType.FILE );

        // TODO: Get input type and file/dir name from user
        Map<String, Long> data = dataReader.read( fileLocation );

        // Sorter will apply sorting strategy from input
        Sorter sorter = new Sorter( Sorter.SortStrategy.DESCENDING );
        sorter.printSorted( data );

    }


}
