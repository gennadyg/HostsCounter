package com.general.main;

import com.security.data.DataReader;
import com.security.data.DataReaderFactory;
import com.security.data.sort.Sorter;

import java.util.Map;

/**
 * Created by Gennady on 4/19/2016.
 */
public class MainApplication {


    public static void main( String[] args ){


        DataReader dataReader = ( new DataReaderFactory()).getReaderByType(DataReaderFactory.ReaderType.FILE );

        // TODO: Get input type and file/dir name from user
        Map<String, Long> data = dataReader.read("input/log_example.log");

        // Sorter willappy sorting strategy from input
        Sorter sorter = new Sorter( Sorter.SortStrategy.DESCENDING );
        sorter.printSorted( data );

    }


}
