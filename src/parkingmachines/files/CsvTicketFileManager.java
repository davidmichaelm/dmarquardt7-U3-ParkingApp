package parkingmachines.files;

import parkingmachines.Ticket;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of TicketFileManager that writes to a CSV file.
 */
public class CsvTicketFileManager implements TicketFileManager {

    private File file;

    /**
     * Returns a new instance of a CsvTicketFileManager.
     * @param fileName The name of the file to write to
     */
    public CsvTicketFileManager(String fileName)
    {
        setFile(fileName);
    }

    /**
     * Reads tickets from a CSV file into String arrays.
     * @return An List of String arrays with ticket information
     */
    public ArrayList<String[]> readTickets() {
        ArrayList<String[]> ticketStringArrays = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;
            while ((line = br.readLine()) != null) {
                String[] lineArray = line.split(",");
                ticketStringArrays.add(lineArray);
            }

            br.close();
        } catch (Exception e) {
            System.out.println("Unable to read file");
        }

        return ticketStringArrays;
    }

    /**
     * Writes tickets to a CSV file. Overwrites the file.
     * @param ticketList A HashMap with all previous tickets
     */
    public void writeTickets(HashMap<Integer, Ticket> ticketList) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));

            for (Map.Entry<Integer, Ticket> integerTicketEntry : ticketList.entrySet()) {
                Ticket ticket = integerTicketEntry.getValue();
                bw.write(ticket.toString());
                bw.newLine();
            }

            bw.flush();
            bw.close();
        } catch (IOException e) {
            System.out.println("Unable to write to file");
        }
    }

    /**
     * Sets the file to write to.
     * @param fileName The name of the file to write to
     */
    public void setFile(String fileName) {
        if (fileName != null && !fileName.isEmpty()) {
            this.file = new File(fileName);
        } else {
            throw new IllegalArgumentException("Unable to load file");
        }
    }
}
