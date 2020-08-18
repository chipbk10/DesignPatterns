package lib;

import java.util.List;

public class Printer {

    public static void print(List<Integer> A) {
        print(A, " ");
    }

    public static void print(List<Integer> A, String separate) {
        for (int a : A)
            print(a, separate);
    }

    public static void print(int[] A) {
        print(A, " ");
    }

    public static void print(int[] A, String separate) {
        for (int a : A)
            print(a, separate);
    }

    public static void println(String s) {
        println(s, "");
    }

    public static void print(String s) {
        print(s, "");
    }

    public static void print(boolean b) {
        print(b, "");
    }

    public static void println(boolean b) {
        println(b, "");
    }

    public static void print(boolean b, String separator) {
        String s = b ? "true" : "false";
        print(s, separator);
    }

    public static void println(boolean b, String separator) {
        String s = b ? "true" : "false";
        println(s, separator);
    }

    public static void print(String s, String separator) {
        System.out.print(s + separator);
    }

    public static void println(String s, String separator) {
        print(s + separator);
        newLine();
    }

    public static void print(int a) {
        print(a, "");
    }

    public static void print(int a, String separator) {
        System.out.print(a + separator);
    }

    public static void newLine() {
        System.out.println();
    }

    public static void println(int a) {
        println(a, "");
    }

    public static void println(int a, String s) {
        print(a + s);
        newLine();
    }

    public static void println(int[] A) {
        print(A);
        newLine();
    }

    public static void println(int[] A, String separate) {
        for (int a : A)
            print(a, separate);
        newLine();
    }
}
