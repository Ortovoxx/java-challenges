import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.http.HttpResponse;
import java.util.Objects;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        while (true) {
            // Takes in data
            InputStreamReader input = new InputStreamReader(System.in);
            BufferedReader reader = new BufferedReader(input);

            // Prompts user for input
            System.out.println("Please enter your University of Southampton email address: ");
            String userInput = reader.readLine();
            if (Objects.equals(userInput, "quit")) break;
            System.out.println("Fetching required data...");

            // Creates a new Email object
            Email email = new Email(userInput);

            // Makes a request to the secure site first as everyone is listed there
            HttpResponse<String> returnedHtml = email.fetchData(Email.fetchDataFrom.AUTHED, email.name);

            if (returnedHtml.body().contains("This page either does not exist or you are not able to view it.")) {
                System.out.println("This person does not exist");
                continue;
            }

            System.out.println("Success");

            var person = new PersonData(returnedHtml.body());

            if (person.visible) {
                HttpResponse<String> returnedPublicHtml = email.fetchData(Email.fetchDataFrom.PUBLIC, email.name);
                person.parsePublicHtml(returnedPublicHtml.body());
            }

            person.storeToJSON(person);
        }
    }
}