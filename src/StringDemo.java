
public class StringDemo {

    public static void main(String[] args) {
        String text = "\"file_name\": \"BYBTariff.pdf\",";
        
        if(text.contains("file_name")){
            text = text.replace("\"file_name\": \"", "");
            text = text.replace(".pdf\",", ".pdf");
            System.out.println(text);
        }
    }

}
