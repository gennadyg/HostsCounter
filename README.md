# HostsCounter

## General main classes explanation

1. Program trying to read first argument to customize file path, if such not exists, default is used - 'input/log_example.log'( file from e-mail).
2. DataReaderFactory created an appropriate reader instance - file in this scenario, TODO: create others per type.
3. Reader reads file and counts unique host names using HashMap, where key is hostname and value number of times
   that hostname appeared in file.
4. Sorter object is used to sort out hostnames using sort strategy( ascending ).

##To run program from command line win/lnux:
```
git clone https://github.com/gennadyg/HostsCounter
//windows
gradlew.bat createExecutableJar runFinalJar
//linux 
./gradlew createExecutableJar runFinalJar
```

Java 8 is required !
