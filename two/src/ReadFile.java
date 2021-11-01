import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Reads in the barebones file and parses it into statements
 */
public class ReadFile {
    private final ArrayList<String> rawFileLines = new ArrayList<String>();
    private final ArrayList<Statement> programStatements = new ArrayList<Statement>();

    ReadFile(String filePath) {
        try {
            File myObj = new File(filePath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                rawFileLines.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Parses lines into statements
     */
    public void parseStatements() {
        // Joins up the entire program and splits it into individual statements
        String fullProgram = String.join("", rawFileLines);
        String[] statements = fullProgram.split(";");

        // Goes through potential statements and creates new lines for each of them
        for (String statement : statements) {
            programStatements.add(new Statement(statement.trim()));
        }
    }

    public ArrayList<Statement> getStatements() {
        return programStatements;
    }

    /**
     * Ensures that all characters are either upper or lower case alphabet characters
     */
    public void validateCharacters() {
        String fullProgram = String.join("", rawFileLines);
        if (fullProgram.contains("/[^A-z0-9;]/g")) {
            System.err.println("File contains invalid characters");
            System.exit(1);
        }
    }
}