import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MissingJsonFinder {

    public static void main(String[] args) {
        List<String> results = new ArrayList<String>();

        File[] files = new File("/Users/vamshin/code0.9/email-content-config/product/attachments").listFiles();
        for (File file : files) {
            if (file.isFile()) {
                results.add(file.getName());
            }
        }

        for (String name : results) {
            search("\"doc_reference\": \"" + name + "\"");
        }

    }

    public static void search(String inputSearch) {
        double count = 0;
        String filePath =
            "/Users/vamshin/code0.9/email-content-config/product/config/document-list-accountopening-version1.json";
        BufferedReader br;

        String line = "";

        try {
            br = new BufferedReader(new FileReader(filePath));
            try {
                while ((line = br.readLine()) != null) {

                    if (line.contains(inputSearch)) {
                        count++;

                    }

                }
                br.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (count == 0) {
            System.out.println("Missing :" + inputSearch);
        } else if (count > 1) {
            System.out.println("Duplicate :" + inputSearch);
        }
    }
}
