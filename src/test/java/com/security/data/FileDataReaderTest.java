package com.security.data;

import com.security.data.sort.Sorter;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Gennady on 4/20/2016.
 */
public class FileDataReaderTest {

    private FileDataReader fileDataReader = null;

    @Before
    public void setUp() throws Exception {

        fileDataReader = new FileDataReader();
    }
    @Test
    public void countHostnames() throws Exception {

        try{

            Map<String, Long> hostnamesFound = fileDataReader.read("input/log_example.log");
            Long numOfDuplicates = hostnamesFound.get("www.google-analytics.com");
            assertEquals("Hostname www.google-analytics.com failed", numOfDuplicates, Long.valueOf( 24 ) );
            Sorter sorter = new Sorter( Sorter.SortStrategy.DESCENDING );
            sorter.printSorted( hostnamesFound );

        }catch( Exception ex ){

            fail("Failed to test empty file");
        }


    }
    @Test
    public void readNotExistedFile() throws Exception {

        try{

            fileDataReader.read("input/not_existed_file.log");

        }catch( Exception ex ){

            fail("Failed to test empty file");
        }


    }
    @Test
    public void readFileWithDamagedLine(){

        try{

            Map<String, Long> results = fileDataReader.read("input/damagedLine.log");
            assertTrue("Failed to process file with broken line", results.size() == 1 ? true : false );

        }catch( Exception ex ){

            fail("Failed to process file with broken line");
        }
    }
}