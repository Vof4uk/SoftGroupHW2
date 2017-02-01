package mykytenko;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Vocabulary {
    private String location = "src\\mykytenko\\vocabulary.txt";
    private Map<String, String> translations = new HashMap<>();
    private static final String NO_TRANSLATION_FOUND = "[no match found, sorry]";

    public Vocabulary(String location) {
        this.location = location;
        readVocabularyFile();
    }

    public Vocabulary() {
        readVocabularyFile();
    }

    private void readVocabularyFile(){

        try(BufferedReader fileReader = new BufferedReader(new FileReader(location));)
        {
            while (fileReader.ready()){
                String line = fileReader.readLine();
                String[] words = line.split(" ");
                if(words.length == 2){
                    translations.put(words[0].trim().toLowerCase(), words[1].trim().toLowerCase());
                }
                else{
                    throw new RuntimeException("this vocabulary.txt is designed for single words");
                }
            }
        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String translateWord(String word){
        String translation = translations.get(word.toLowerCase().trim());
        return translation == null ? NO_TRANSLATION_FOUND : translation;
    }
}
