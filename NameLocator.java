import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.io.IOException;

public class NameLocator {


    public static void main(String[] args) throws IOException{
        String DefaultUrl = "https://www.ecs.soton.ac.uk/people/";

        System.out.println("Enter a soton email ID");
        BufferedReader ConsoleReader = new BufferedReader((new InputStreamReader(System.in)));
        String ID = ConsoleReader.readLine();

        URL PageURL = new URL (DefaultUrl + ID);
        System.out.println(DefaultUrl + ID);
        BufferedReader PageReader = new BufferedReader(new InputStreamReader(PageURL.openStream()));

        boolean LineFound = false;
        String CurrentLine = "";

        // Breaks out of the loop as soon as the line is found so it does not have to loop through entire page
        while (!LineFound & CurrentLine != null){

            CurrentLine = PageReader.readLine();
            // Only attempts to read what is in the line if it is not empty
            if (CurrentLine != null) {
                // Checks if the line contains the part where it specifies the name
                if (CurrentLine.contains("property=\"name\">")) {
                    LineFound = true;
                }
            }
        }
        if (LineFound) {
            String Name;
            // Splits the Line where the Name is and then splits it again after the name to leave only the name
            Name = CurrentLine.split("property=\"name\">")[1].split("<em property")[0];
            System.out.println(Name);
        }else {
            System.out.println("No name was found for the ID input.");
        }
    }
}
