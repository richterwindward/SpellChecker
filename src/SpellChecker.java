import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Scanner;

public class SpellChecker {
    public HashSet<String> dict;
    public SpellChecker(Path dictionaryFile, int tableSize){//We do not take in a mispellingsFile because we generate the mispelling in run time
        Scanner s = null;
        try{
            s = new Scanner(dictionaryFile);
        } catch (IOException e){
            e.printStackTrace();
            System.exit(0);
        }

        dict = new HashSet<>(tableSize);
        while(s.hasNext()){
            dict.add(s.next().toLowerCase());
        }
        s.close();
    }

    private boolean wordExists(String s) {
        return dict.contains(s);
    }

    private HashSet<String> generatePossibleWords(String s){
        HashSet<String> allPermutations = WordEditor.stringPermutations(s);
        HashSet<String> notActuallyWords = new HashSet<>();

        for(String garbage : allPermutations){
            if(!wordExists(garbage)){
                notActuallyWords.add(garbage);
            }
        }

        for(String garbage2 : notActuallyWords){
            if(allPermutations.contains(garbage2)){
                allPermutations.remove(garbage2);
            }
        }

        return allPermutations;
    }
}
