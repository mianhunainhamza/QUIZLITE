package com.example.projectjavafx;

import java.io.*;

public class imageSetter {
    static String url;
    public static String getUrl() throws IOException {
        File file=new File("src/main/java/com/example/projectjavafx/imageData.txt");
        FileReader fileReader=new FileReader(file);
        BufferedReader bufferedReader=new BufferedReader(fileReader);
       String URL= bufferedReader.readLine();
       bufferedReader.close();
       if (URL!=null)
           return URL;
        else
           return url;
    }
    public static void setUrl(String url) throws IOException {
        imageSetter.url=url;
        File file=new File("src/main/java/com/example/projectjavafx/imageData.txt");
        FileWriter fileWriter=new FileWriter(file);
        BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
        bufferedWriter.write(url);
        bufferedWriter.close();
    }
}
