import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class SpellChecker {
    public HashSet<String> dict;
    HashMap<String, Integer> wordFreq;

    public SpellChecker(Path dictionaryFile, Path mostCommonWords, int tableSize){//We do not take in a mispellingsFile because we generate the mispelling in run time
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


        try{
            s = new Scanner(mostCommonWords);
        }catch (IOException e){
            e.printStackTrace();
            System.exit(0);
        }

        wordFreq = new HashMap<>(11000);

        int i = 0;
        while(s.hasNext()){
            wordFreq.put(s.next(), i++);
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

    private PriorityQueue<String> sortPossibleWords(HashSet<String> possibleWords){
        PriorityQueue pq = new PriorityQueue(wordFreqComparator);
        for(String s : possibleWords){
            pq.add(s);
        }
        return pq;
    }

    public PriorityQueue<String> generateAndSortWords(String s){
        return sortPossibleWords(generatePossibleWords(s));
    }

    private Comparator<String> wordFreqComparator = new Comparator<String>() {
        public int compare(String s1, String s2) {
            int s1freq = wordFreq.containsKey(s1)?wordFreq.get(s1):10001;
            int s2freq = wordFreq.containsKey(s2)?wordFreq.get(s2):10001;
            return s1freq - s2freq;
        }
    };
}
