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
        results.remove(".DS_Store");

        System.out.println("************** Missing/Duplicate Json Entries ***************");
        for (String name : results) {
            search("\"file_name\": \"" + name + "\"");
        }
        System.out.println("************** Missing Attachments ***************");
        searchFile(results);

    }

    private static void searchFile(List<String> results) {
        // System.out.println(results);
        String filePath =
            "/Users/vamshin/code0.9/email-content-config/product/config/document-list-accountopening-version2.json";
        BufferedReader br;
        String line = "";
        try {
            br = new BufferedReader(new FileReader(filePath));
            try {
                while ((line = br.readLine()) != null) {

                    if (line.contains("file_name")) {
                        line = line.replace("\"file_name\": \"", "");
                        line = line.replace(".pdf\",", ".pdf");
                        // System.out.println(line+""+results.contains(line.trim()));
                        if (!results.contains(line.trim()))
                            System.out.println(line.trim());
                        // break;

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
        // System.out.println(results);

    }

    public static void search(String inputSearch) {
        double count = 0;
        String filePath =
            "/Users/vamshin/code0.9/email-content-config/product/config/document-list-accountopening-version2.json";
        BufferedReader br;

        String line = "";
        String datedFile = inputSearch.replace(".pdf\"", "_");
        // System.out.println(inputSearch+" or "+datedFile);

        try {
            br = new BufferedReader(new FileReader(filePath));
            try {
                while ((line = br.readLine()) != null) {

                    if (line.contains(inputSearch) || line.contains(datedFile)) {
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
