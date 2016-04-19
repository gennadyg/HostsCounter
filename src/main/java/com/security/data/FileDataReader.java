package com.security.data;

import com.general.validation.LineValidator;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gennady on 4/19/2016.
 */
public class FileDataReader implements DataReader{

    // TODO:
    // Location of hostname should be defined in external file
    public enum DataElementsLocations {

        Comment( 0, "#", "Location of comment element to check" ),

        // TODO: location of hostname column should be checked in runtime,
        // based on column values - #Fields
        HostName( 16, "cs-host", "Location of host name in file" );

        private final int location;
        private final String description;
        private final String value;

        private DataElementsLocations( int location, String value, String description) {

            this.location = location;
            this.description = description;
            this.value = value;
        }
    }

    /**
     *  Checks if line value at specific location is valid
     *
     * @param nextLine - next line data
     * @return - true if line is comment, false otherwize
     */
    private boolean checkIfLineComment( String [] nextLine, int lineNumber ){

        // by default return comment indication
        boolean ret = true;

        try{

            if( LineValidator.isLineValueValid( nextLine, DataElementsLocations.Comment.location )){

                if( !nextLine[DataElementsLocations.Comment.location].startsWith( DataElementsLocations.Comment.value)){

                    ret = false;
                }
            }

        }catch( Exception ex ){

            // TODO: add logging to file
            System.out.println("Failed to check line[" + lineNumber + "]");
            ex.printStackTrace();
        }
        return ret;
    }

    /** Main function for processing data from file
     *
     * @param fileName
     */
    @Override
    public Map<String, Long> read( String fileName ){

        int lineNumber = 0;

        // Data structure to store hostnames counts
        // TODO: will not work for multithreaded environment,
        // replace to ConcurrentHashMap
        // neither support scaling, ideally should be replaced by
        // some key value store Redis/Couchbase with atomic increment
        Map<String, Long> hostnamesCounts = new HashMap<String, Long>();

        // TODO: More generic case would be to read all files
        // in directory and to process each with a different
        // file processor implementation per type
        try( FileReader fileReader = new FileReader( fileName ) ){

            // CSVReader library is used to read/parse lines from text files
            CSVReader reader = new CSVReader( fileReader, ' ', '\"');

            String [] nextLine;
            // read lines from input file
            while (( nextLine = reader.readNext()) != null) {

                // Checking if line is comment, assumption is that comment line
                // is started by '#' character
                if( checkIfLineComment( nextLine, lineNumber ) == false ){

                    if( ( LineValidator.isLineValueValid( nextLine, DataElementsLocations.HostName.location) )){

                        String hostNameFound = nextLine[DataElementsLocations.HostName.location];
                        if( hostnamesCounts.containsKey( hostNameFound )){

                            hostnamesCounts.put( hostNameFound, hostnamesCounts.get( hostNameFound ) + 1 );

                        }else{

                            hostnamesCounts.put( hostNameFound, Long.valueOf(1) );
                        }
                    }

                }else{

                 // TODO: For debug purposes
                 //   System.out.println("Got comment line[" + lineNumber + "]");
                }

                lineNumber++;
            }

        }catch( Exception ex ){

            System.out.println("Got error in reading line[" + lineNumber + "]");
            ex.printStackTrace();
        }
        return hostnamesCounts;
    }

    public static void main( String[] args ){

        FileDataReader fileDataReader = new FileDataReader();
        fileDataReader.read("input/log_example.log");
    }
}
