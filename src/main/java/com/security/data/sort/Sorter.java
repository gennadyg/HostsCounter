package com.security.data.sort;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Lital on 4/19/2016.
 */
public class Sorter {

    private SortStrategy sortStrategy;

    public enum SortStrategy{

        DESCENDING,
        ASCENDING
    }

    public Sorter( SortStrategy sortStrategy ){

        this.sortStrategy = sortStrategy;
    }

    public void printSorted( Map<String, Long> data ){

        switch( sortStrategy ){

        case DESCENDING:
                Comparator<Map.Entry<String, Long>> byValue = (entry1, entry2) -> entry1.getValue().compareTo(
                        entry2.getValue());

                Map<String, Long> sortedMapDescending =
                    data.entrySet().stream()
                            .sorted( byValue.reversed() )
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                                    (e1, e2) -> e1, LinkedHashMap::new));

                for( String currentHost : sortedMapDescending.keySet() ){
                    System.out.println( "Host: " + currentHost + " Count: " + sortedMapDescending.get( currentHost ));
                }
            break;
            case ASCENDING:

                Map<String, Long> sortedMapAscending =
                    data.entrySet().stream()
                            .sorted( Map.Entry.comparingByValue())
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                                    (e1, e2) -> e1, LinkedHashMap::new));
        }
    }
}
