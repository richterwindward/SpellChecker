import java.util.ArrayList;

public abstract class WordEditor {//tested ✔
    public static ArrayList<String> stringPermutations(String s){// norvig.com/spell-correct.html
        ArrayList<String> arr = new ArrayList<>();
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
}
