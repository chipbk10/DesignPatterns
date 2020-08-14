package strategy;

import java.util.HashMap;
import java.util.Map;

public class Data {

    public interface LetterTransformer {
        String transform();
    }

    public static class Letter_A_Transformer implements LetterTransformer {
        @Override
        public String transform() { return "aa"; }
    }

    public static class Letter_B_Transformer implements LetterTransformer {
        @Override
        public String transform() { return "bbb"; }
    }

    public static class Letter_C_Transformer implements LetterTransformer {
        @Override
        public String transform() { return "cccc"; }
    }

    public static class LetterTransformerFactory {

        private Map<Character, LetterTransformer> map = new HashMap<>();

        public LetterTransformerFactory() {
            map.put('a', new Letter_A_Transformer());
            map.put('b', new Letter_B_Transformer());
            map.put('c', new Letter_C_Transformer());
        }

        public LetterTransformer getTransformer(char c) {
            return map.getOrDefault(c, null);
        }
    }

    public static class TransformerTool {

        public String transform(String s) {

            LetterTransformerFactory factory = new LetterTransformerFactory();
            StringBuilder sb = new StringBuilder();

            for (char c : s.toCharArray()) {
                LetterTransformer transformer = factory.getTransformer(c);
                if (transformer != null)
                    sb.append(transformer.transform());
            }

            return sb.toString();
        }
    }
}
