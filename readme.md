# Readme - Word search

This is an implementation of a word search command line program that satisfies the code kata at  
https://github.com/PillarTechnology/kata-word-search

## Build, Run Tests, and Create Jar, Install in local Maven repo
```
mvn clean install
```

## Run the App
```
java -cp target/word-search-0.0.1-SNAPSHOT.jar com.bobmhong.kata.App INPUTFILEPATH

Example:
java -cp target/word-search-0.0.1-SNAPSHOT.jar com.bobmhong.kata.App ./target/trekSampleData.txt
```
