package com.bobmhong.kata;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import org.apache.logging.log4j.LogManager;
import org.junit.Rule;
import org.junit.Test;
import java.io.File;

public class AppTest {

    @Rule
    public LogAppenderResource appender = new LogAppenderResource(LogManager.getLogger(App.class));

    @Test
    public void testMainwithGoodDataFileArg() {
        String[] args = { "./target/trekSampleData.txt" };
        StringBuilder bld = new StringBuilder();
        
        bld.append("Attempting to load: ./target/trekSampleData.txt");
        bld.append ("BONES: (0,6),(0,7),(0,8),(0,9),(0,10)");
        bld.append ("KHAN: (5,9),(5,8),(5,7),(5,6)");
        bld.append ("KIRK: (4,7),(3,7),(2,7),(1,7)");
        bld.append ("SCOTTY: (0,5),(1,5),(2,5),(3,5),(4,5),(5,5)");
        bld.append ("SPOCK: (2,1),(3,2),(4,3),(5,4),(6,5)");
        bld.append ("SULU: (3,3),(2,2),(1,1),(0,0)");
        bld.append ("UHURA: (4,0),(3,1),(2,2),(1,3),(0,4)");

        App.main(args);

        assertThat(appender.getOutput(), containsString(bld.toString()));
    }

    @Test
    public void testMainwithUnlistedWordsFileArg() {
        File resourcesDirectory = new File("src/test/resources/trekSampleDataUnlistedWords.txt");

        //String[] args = { "./target/trekSampleDataUnlistedWords.txt" };
        String[] args = { resourcesDirectory.getAbsolutePath() };
        
        App.main(args);

        assertThat(appender.getOutput(), containsString("Sorry, No word matches were found."));
    }

    @Test
    public void testMainwithMissingDataFileArg() {
        String[] args = {};

        App.main(args);

        String msg = "Please specify the path to your Word Search data file as the first command line parameter.";
        // assertEquals(msg, outContent.toString());
        assertThat(appender.getOutput(), containsString(msg));

    }
}
