import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

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
     * Constructs an HTTP request to the ECS servers with the correct cookies to get the relevant information
     */
    public HttpResponse<String> fetchData(fetchDataFrom type, String input) throws IOException, InterruptedException{

        String cookies = "noteswiki304f3058RemoteToken=ec3g21; noteswiki304f3058UserName=Ec3g21; _opensaml_req_ss:mem:21c88f185062124055c0c2cdc0cf5e00d2965109646e0aeecee8dcac4f38849a=_72bbef9dfda6401f6745fc9347d96d63; _opensaml_req_ss:mem:a3e3af3b15e537c262a255145be17c543c3f703a955b9e2f21d8e63150bd278f=_511718a50cabeb825994dfe7462e55bf; _opensaml_req_ss:mem:0f6c0b7ad895ff89cf6c39e780a80ffb1231fc64900986f98a865ae74c2b9c8c=_018e6e27005a013c0a3fd8c5ba4978b0; _opensaml_req_ss:mem:15b61e30534ddf83bb66c78bf9039925db9f7b81a1dd8e28bc929db755fb4544=_1c3a5ecd8907c3e9182f5e5be8b691dc; _opensaml_req_ss:mem:4f8fcb70432ca20e08f9252407dbc170cbd2b2fd6ec795847da0a0345c9a5422=_d43a9359173d70c02a0292ebb71835d5; _opensaml_req_ss:mem:fd4d34ac77fe61072f39b0142c02815efca1330a5bc94b4692de26eda9f39a46=_0ec8ee4921a1a138aa2ca6448000da98; noteswiki304f3058UserID=15888; noteswiki304f3058Token=59f17df03a373052330193b3b16c9192; studentwiki304f3058RemoteToken=ec3g21; studentwiki304f3058UserID=10837; studentwiki304f3058UserName=Ec3g21; studentwiki304f3058Token=73f7420b5af94411d28716a991641743; _opensaml_req_ss:mem:0a48c5430fdfcaaee6084437871ca73a51f60d6e45c50a096904c11c4b07db86=_e43e66b46059296073f02303e9ae0e02; noteswiki304f3058_session=lbp4ohqhmpb9tohrp5g1bqvl8tacimjv; studentwiki304f3058_session=l76k14461o14lsc7iss07ppglbf50b7n; _shibsession_64656661756c7468747470733a2f2f7365637572652e6563732e736f746f6e2e61632e756b2f73686962626f6c657468=_b63f95cf3fb4d9932bdabdf64e653841";

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