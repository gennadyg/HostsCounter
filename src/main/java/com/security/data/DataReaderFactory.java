package com.security.data;

/**
 * Created by Lital on 4/19/2016.
 */
public class DataReaderFactory {

    public enum ReaderType{

        FILE,
        LINK,
        FILE_DIRECTORY,
        DATABASE
    }

    public DataReader getReaderByType( ReaderType readerType ){

        DataReader dataReader = null;
        switch( readerType ){

            case FILE: dataReader = new FileDataReader();
            default:
            break;
        }
        return dataReader;
    }
}
