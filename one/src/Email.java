import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

/**
 * Allows for more complex operations to be performed on emails specific to the Southampton system
 */
class Email {
    String name;

    Email(String input){
        this.name = input.replaceAll("@(.*)", "");
    }

    /**
     * Enum for all the different types of HTTP requests to make
     */
    enum fetchDataFrom {
        PUBLIC,
        AUTHED,
        SEARCH
    }

    /**
     * Reads cookies from a file
     */
    public String readCookies (String filename) {
        String cookies = "";
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                cookies = myReader.nextLine();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return cookies;
    }

    /**
     * Constructs an HTTP request to the ECS servers with the correct cookies to get the relevant information
     */
    public HttpResponse<String> fetchData(fetchDataFrom type, String input) throws IOException, InterruptedException{

        String cookies = readCookies("./one/src/Cookies.txt");

        switch (type) {
            case PUBLIC -> {
                return makeHttpReq("https://www.ecs.soton.ac.uk/people/" + input, cookies);
            }
            case AUTHED -> {
                return makeHttpReq("https://secure.ecs.soton.ac.uk/people/" + input, cookies);
            }
            case SEARCH -> {
                return makeHttpReq("https://secure.ecs.soton.ac.uk/people/?nameq=" + input, cookies);
            }
            default -> {
                return null;
            }
        }
    }

    /**
     * Makes a http request to the given URL with the correct ECS cookies
     */
    public HttpResponse<String> makeHttpReq(String url, String cookies) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .setHeader("Cookie", cookies)
                .build();

        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}