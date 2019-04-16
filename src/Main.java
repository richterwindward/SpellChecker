
import java.nio.file.Paths;


public class Main {
    public static void main(String[] args) {
        SpellChecker spellChecker = new SpellChecker(Paths.get(Main.class.getClassLoader().getResource("dict.txt").getPath()), Paths.get(Main.class.getClassLoader().getResource("google-10000-english-usa.txt").getPath()),500000);
        System.out.println(spellChecker.generateAndSortWords("solider"));
    }
}
