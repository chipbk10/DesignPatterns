package adapter;

public class Data {

    public interface IReverseString {
        String reverse(String s);
    }

    public static class ReverseString implements IReverseString {

        @Override
        public String reverse(String s) {
            StringBuilder sb = new StringBuilder();
            for (int i = s.length()-1; i >= 0; i--) {
                sb.append(s.charAt(i));
            }
            return sb.toString();
        }
    }

    public interface IReverseNumber {
        int reverse(int num) throws IllegalArgumentException;
    }

    public static class AdapterReverseNumber implements IReverseNumber {

        private IReverseString reverseString;

        public AdapterReverseNumber(IReverseString reverseString) {
            this.reverseString = reverseString;
        }

        @Override
        public int reverse(int num) throws NumberFormatException {
            String s = Integer.toString(num);
            String reversed = reverseString.reverse(s);
            return Integer.valueOf(reversed);
        }
    }

}
