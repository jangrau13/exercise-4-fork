package solid;

import cartago.Artifact;
import cartago.OPERATION;
import cartago.OpFeedbackParam;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * A CArtAgO artifact that agent can use to interact with LDP containers in a Solid pod.
 */
public class Pod extends Artifact {

    private String podURL; // the location of the Solid pod 

  /**
   * Method called by CArtAgO to initialize the artifact. 
   *
   * @param podURL The location of a Solid pod
   */
    public void init(String podURL) {
        this.podURL = podURL;
        log("Pod artifact initialized for: " + this.podURL);
    }

  /**
   * CArtAgO operation for creating a Linked Data Platform container in the Solid pod
   *
   * @param containerName The name of the container to be created
   * 
   */
    @OPERATION
    public void createContainer(String containerName) {
        log("1. Implement the method createContainer()");
        String body = "@prefix ldp: <http://www.w3.org/ns/ldp#> .\n"+
                "@prefix dcterms: <http://purl.org/dc/terms/> . \n" + 
                "<> a ldp:Container, ldp:BasicContainer; \n" +
                "dcterms:title \"Pod for JaCoMo\" ; \n" +
                "dcterms:description \"This container will contain the files for the JaCoMo application.\" .  \n";
        createContainerRequest(body, containerName);
    }

  /**
   * CArtAgO operation for publishing data within a .txt file in a Linked Data Platform container of the Solid pod
   * 
   * @param containerName The name of the container where the .txt file resource will be created
   * @param fileName The name of the .txt file resource to be created in the container
   * @param data An array of Object data that will be stored in the .txt file
   */
    @OPERATION
    public void publishData(String containerName, String fileName, Object[] data) {
        log("2. Implement the method publishData()");
        //check if file already exists
        if (checkFileExistence(containerName, fileName)) {
            updateDataRequest(containerName, fileName, data);
        }else{
            createFileRequest(containerName, fileName, data);
        }
        
    }

  /**
   * CArtAgO operation for reading data of a .txt file in a Linked Data Platform container of the Solid pod
   * 
   * @param containerName The name of the container where the .txt file resource is located
   * @param fileName The name of the .txt file resource that holds the data to be read
   * @param data An array whose elements are the data read from the .txt file
   */
    @OPERATION
    public void readData(String containerName, String fileName, OpFeedbackParam<Object[]> data) {
        data.set(readData(containerName, fileName));
    }

  /**
   * Method for reading data of a .txt file in a Linked Data Platform container of the Solid pod
   * 
   * @param containerName The name of the container where the .txt file resource is located
   * @param fileName The name of the .txt file resource that holds the data to be read
   * @return An array whose elements are the data read from the .txt file
   */
    public Object[] readData(String containerName, String fileName) {
        log("3. Implement the method readData(). Currently, the method returns mock data");

        return getDataRequest(containerName, fileName);

    }

  /**
   * Method that converts an array of Object instances to a string, 
   * e.g. the array ["one", 2, true] is converted to the string "one\n2\ntrue\n"
   *
   * @param array The array to be converted to a string
   * @return A string consisting of the string values of the array elements separated by "\n"
   */
    public static String createStringFromArray(Object[] array) {
        StringBuilder sb = new StringBuilder();
        for (Object obj : array) {
            sb.append(obj.toString()).append("\n");
        }
        return sb.toString();
    }

  /**
   * Method that converts a string to an array of Object instances computed by splitting the given string with delimiter "\n"
   * e.g. the string "one\n2\ntrue\n" is converted to the array ["one", "2", "true"]
   *
   * @param str The string to be converted to an array
   * @return An array consisting of string values that occur by splitting the string around "\n"
   */
    public static Object[] createArrayFromString(String str) {
        return str.split("\n");
    }


  /**
   * CArtAgO operation for updating data of a .txt file in a Linked Data Platform container of the Solid pod
   * The method reads the data currently stored in the .txt file and publishes in the file the old data along with new data 
   * 
   * @param containerName The name of the container where the .txt file resource is located
   * @param fileName The name of the .txt file resource that holds the data to be updated
   * @param data An array whose elements are the new data to be added in the .txt file
   */
    @OPERATION
    public void updateData(String containerName, String fileName, Object[] data) {
        Object[] oldData = readData(containerName, fileName);
        Object[] allData = new Object[oldData.length + data.length];
        System.arraycopy(oldData, 0, allData, 0, oldData.length);
        System.arraycopy(data, 0, allData, oldData.length, data.length);
        publishData(containerName, fileName, allData);
    }

    private boolean createContainerRequest(String body, String nameOfPod) {
        String queryUrl = "https://solid.interactions.ics.unisg.ch/AnnaLuese/";

        try {
            URI uri = new URI(queryUrl);
            log("URI: " + uri.toString());
            log("query: " + body);
            log("name of pod" + nameOfPod + "/");

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .header("Link", "<http://www.w3.org/ns/ldp#BasicContainer>; rel=\"type\"")
                    .header("Content-Type", "text/turtle")
                    .header("Slug", nameOfPod + "/")
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            try {
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                if (response.statusCode() != 201) {
                    log("status code " + (response.statusCode()));
                    throw new RuntimeException("HTTP error code : " + response.statusCode());
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean checkFileExistence(String containerName, String fileName) {
        String queryUrl = "https://solid.interactions.ics.unisg.ch/AnnaLuese/" + containerName + "/" + fileName;

        try {
            URI uri = new URI(queryUrl);
            log("URI: " + uri.toString());

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .GET()
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            try {
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                if (response.statusCode() != 200) {
                    return false;
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean updateDataRequest(String containerName, String fileName, Object[] data) {
        String queryUrl = "https://solid.interactions.ics.unisg.ch/AnnaLuese/" + containerName + "/" + fileName;

        try {
            URI uri = new URI(queryUrl);
            log("URI: " + uri.toString());
            int i = 0;
            var sectionLength = data.length;
            String body = "";
            for (i = 0; i < sectionLength; i++) {
                body += data[i] + "\n";
            }
            log("body in udpate: " + body);


            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .header("Content-Type", "text/plain")
                    .PUT(HttpRequest.BodyPublishers.ofString(body))
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            try {
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                if (response.statusCode() > 210) {
                    throw new RuntimeException("HTTP error code : " + response.statusCode());
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return false;
    }

    
    
    private boolean createFileRequest(String containerName, String fileName, Object[] data) {
        String queryUrl = "https://solid.interactions.ics.unisg.ch/AnnaLuese/" + containerName + "/";

        try {
            URI uri = new URI(queryUrl);
            log("URI: " + uri.toString());
            int i = 0;
            var sectionLength = data.length;
            String body = "";
            for (i = 0; i < sectionLength; i++) {
                body += data[i] + "\n";
            }
            log("body in create: " + body);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .header("Content-Type", "text/plain")
                    .header("Slug", fileName)
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            try {
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                if (response.statusCode() != 201) {
                    throw new RuntimeException("HTTP error code : " + response.statusCode());
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    private Object[] getDataRequest(String containerName, String fileName) {
        String queryUrl = "https://solid.interactions.ics.unisg.ch/AnnaLuese/" + containerName + "/" + fileName;

        try {
            URI uri = new URI(queryUrl);
            log("URI: " + uri.toString());

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .GET()
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            try {
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                if (response.statusCode() > 210) {
                    throw new RuntimeException("HTTP error code : " + response.statusCode());
                }
                String result = response.body();
                Object[] entries = result.split("\n");
                return entries;

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return new Object[] { "" };
    }

}
