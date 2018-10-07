package com.bobmhong.kata;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.*;

public class AppTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }
    
    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void testMainwithGoodDataFileArg() {
        String[] args = { "./target/trekSampleData.txt" };
        String outputExpected;

        outputExpected = "Attempting to load: ./target/trekSampleData.txt\n" 
            + "BONES: (0,6),(0,7),(0,8),(0,9),(0,10)\n"
            + "KHAN: (5,9),(5,8),(5,7),(5,6)\n" 
            + "KIRK: (4,7),(3,7),(2,7),(1,7)\n"
            + "SCOTTY: (0,5),(1,5),(2,5),(3,5),(4,5),(5,5)\n" 
            + "SPOCK: (2,1),(3,2),(4,3),(5,4),(6,5)\n"
            + "SULU: (3,3),(2,2),(1,1),(0,0)\n" 
            + "UHURA: (4,0),(3,1),(2,2),(1,3),(0,4)\n";
    
        App.main(args);

        assertEquals(outputExpected, outContent.toString());
    }
    
    @Test
    @Ignore
    public void testMainwithMissingDataFileArg(){
        String[] args = {};
       
        App.main(args);
    
        String msg = "Please specify the path to your Word Search data file as the first command line parameter.\n";
        assertEquals(msg, outContent.toString());
       
    }
}
