import java.util.ArrayList;
import java.util.HashSet;

public abstract class WordEditor {//tested ✔
    public static HashSet<String> stringPermutations(String s){// norvig.com/spell-correct.html
        HashSet<String> arr = new HashSet<>();
        String letters = "abcdefghijklmnopqrstuvwxyz";

        for (int i = 0; i < s.length(); i++) {
            arr.add(s.substring(0,i)+ s.substring(i+1));//delete
        }

        for (int i = 0; i < s.length()-1; i++) {
            arr.add(s.substring(0, i) + s.charAt(i+1) + s.charAt(i) + s.substring(i+2));//transpose
        }

        for (int i = 0; i < s.length(); i++) {
            for(char c : letters.toCharArray()){
                arr.add(s.substring(0, i) + c + s.substring(i+1));//replace
            }
        }

        for (int i = 0; i < s.length(); i++) {
            for(char c : letters.toCharArray()){
                arr.add(s.substring(0, i) + c + s.substring(i));//insert
            }
        }

        return arr;
    }

    private static boolean endsIn(String word, String ending) {
        if(word.length() < ending.length())
            return false;

        if(word.substring(word.length() - ending.length()).equals(ending))
            return true;

        return false;
    }

    private static String removeEnding(String word, String ending) {
        if(endsIn(word, ending)){
           return word.substring(0, word.length()-ending.length());
        }
        return word;
    }

    //
    public static HashSet<String> sanitizeWord(String s) {//this method is not useful because our dictionary contains alternate forms
        HashSet<String> hashSet = new HashSet<>();
        hashSet.add(s);
        hashSet.add(removeEnding(s, "ing"));
        hashSet.add(removeEnding(s, "s"));
        hashSet.add(removeEnding(s, "es"));
        hashSet.add(removeEnding(s, "ly"));
        hashSet.add(removeEnding(s, "ed"));
        if(endsIn(s, "ing")){
            hashSet.add(removeEnding(s, "ing")+"e");
        }
        if(endsIn(s, "ies")){
            hashSet.add(removeEnding(s, "ies")+"y");
        }
        if(endsIn(s, "es")){
            hashSet.add(removeEnding(s, "s"));
        }
        if(endsIn(s, "ed")){
            hashSet.add(removeEnding(s, "d"));
        }
        return hashSet;
    }
    public static String usefulSanitize(String s){
        return s.toLowerCase().replace(".", "").replace(",","").replace(";", "").replace("?", "").replace("!", "").replace("\"", "").replace(":", "");
    }
}
