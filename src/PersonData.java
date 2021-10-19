import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

class PersonData {
    String name;
    String email;
    String position;
    Integer ECSMemberId;
    Boolean visible;


    PersonData(String html) {
        parseAuthHTML(html);
    }



    /**
     * Parses HTML from authenticated sources
     */
    public void parseAuthHTML(String html){
        Document doc = Jsoup.parse(html);

        name = doc.select("#name > span").text();

        if (doc.select("#email > span").text().equals("")) {
            email = doc.select("#content > div.pageContentBars1 > div:nth-child(4) > a.email").text();
        }
        else {
            email = doc.select("#email > span").text();
        }

        String visibilityAndId = doc.select("#content > div.pageContentBars1 > div:nth-child(5)").text();

        visible = !visibilityAndId.contains("Visible to University of Southampton members only");

        ECSMemberId = Integer.parseInt(
                visibilityAndId.split(":")[2].replaceAll("Role ID Number", "").replaceAll(" ", "")
        );

        String positionBlob = doc.select("#content > div.pageContentBars1 > div:nth-child(4)").text();

        position = positionBlob.split(":")[1].replaceAll("Email", "").substring(1);

        System.out.println(email);
        System.out.println(name);
        System.out.println(ECSMemberId);
        System.out.println(visible);
        System.out.println(position);
    }

    /**
     * Parses HTML from public page sources
     */
    public void parsePublicHtml(String html) {
        Document doc = Jsoup.parse(html);

        System.out.println(doc.select("#page > article > div > h1"));

        doc.select("#page > article > div > h1");
    }

    public String toString(){
        return "Test" + name;
    }

    /**
     * Stores any data encapsulated in this object to a JSON file
     */
    public void storeToJSON(PersonData person){
        System.out.println("Storing details about '" + name + "' to JSON file");
        System.out.print(person);
    }
}
