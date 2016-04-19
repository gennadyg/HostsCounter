package com.security.data;

import java.util.Map;

/** Generic interface for all types of data readers
 *
 * Created by Gennady on 4/19/2016.
 */
public interface DataReader {

    public Map<String, Long> read( String fileName );
}
