package tests.parkingmachines.files;

import org.junit.Before;
import org.junit.Test;
import parkingmachines.Ticket;
import parkingmachines.feestrategies.SpecialEvent;
import parkingmachines.files.CsvTicketFileManager;
import parkingmachines.files.TicketFileManager;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

public class CsvTicketFileManagerTest {

    private TicketFileManager csvFileManager;
    private String fileName;
    private String testString;
    private ArrayList<String[]> testArray;

    @Before
    public void setUp() throws IOException {
        fileName = "test.csv";
        testString =  "1,2020-05-14T07:09,2020-05-14T15:31,SPECIAL_EVENT";

        testArray = new ArrayList<>();
        testArray.add(testString.split(","));

        FileWriter fileWriter = new FileWriter(fileName);
        fileWriter.write(testString);
        fileWriter.close();
        csvFileManager = new CsvTicketFileManager(fileName);
    }

    @Test
    public void readTickets() {
        assertArrayEquals(testArray.get(0), csvFileManager.readTickets().get(0));
    }

    @Test
    public void writeTickets() {
        HashMap<Integer, Ticket> testTickets = new HashMap<>();
        String[] data = testArray.get(0);
        Ticket testTicket = new Ticket(data[0], data[1], data[2], new SpecialEvent(20));
        testTickets.put(1, testTicket);
        csvFileManager.writeTickets(testTickets);

        assertArrayEquals(testArray.get(0), csvFileManager.readTickets().get(0));
    }
}