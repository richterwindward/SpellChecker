import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SpellChecker spellChecker = new SpellChecker(Paths.get(Main.class.getClassLoader().getResource("dict.txt").getPath()), 500000);
        System.out.println(spellChecker.dict.size());
    }
}
