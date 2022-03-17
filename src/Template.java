//import java.util.*;
//import java.math.*;
//import static java.lang.Math.max;
//import static java.lang.Math.min;
//import static java.lang.Math.abs;
//import static java.lang.Math.round;
//import static java.lang.Math.ceil;
//import static java.lang.Math.floor;
//import static java.lang.Math.sqrt;
//import static java.lang.Math.pow;
//import static java.lang.System.out;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

import static java.lang.System.out;

public class Template {
    public static void main(String[] args) {
        FastScanner scanner = new FastScanner();
        int testCases = scanner.nextInt();
        StringBuilder sb = new StringBuilder();
        while (testCases-- > 0) {

        }
    }

    //DEBUGGING PURPOSE
    public static void print(int[] arr) {
        for (int x : arr) out.print(x + " ");
        out.println();
    }

    //DEBUGGING PURPOSE
    public static void print(long[] arr) {
        for (long x : arr) out.print(x + " ");
        out.println();
    }

    //READING VALUES TO ARRAY
    public int[] readArray(Scanner scanner, int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++)
            array[i] = scanner.nextInt();
        return array;
    }

    //READING VALUES TO ARRAY
    public long[] readArray2(Scanner scanner, int size) {
        long[] array = new long[size];
        for (int i = 0; i < size; i++)
            array[i] = scanner.nextInt();
        return array;
    }

    //REVERSE AN ARRAY
    public int[] reverseArray(int[] array) {
        int low = 0, high = array.length - 1;
        while (low < high) {
            swap(array, low, high);
            low++;
            high--;
        }
        return array;
    }

    //SWAPPING ELEMENTS IN AARAY
    public void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    //SWAPPING ELEMENTS
    public void swap(int A, int B) {
        A = A ^ B;
        B = A ^ B;
        A = A ^ B;
    }

    //Is the Number Prime.
    public static boolean isPrime(long n) {
        if (n < 2) return false;
        if (n == 2 || n == 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        long sqrtN = (long) Math.sqrt(n) + 1;
        for (long i = 6L; i <= sqrtN; i += 6) {
            if (n % (i - 1) == 0 || n % (i + 1) == 0) return false;
        }
        return true;
    }

    //GCD OF TWO NUMBERS.[a > b]
    public static long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, b % a);
    }

    //SORT ARRAY USING COLLECTIONS SORT
    public static void sort(int[] array) {
        //because Arrays.sort() uses quicksort(tim sort) which is dumb
        //Collections.sort() uses merge sort
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int element : array) list.add(element);
        Collections.sort(list);
        for (int i = 0; i < array.length; i++) array[i] = list.get(i);
    }

    //COMPRESS ARRAY REDUCING THE OUTLINERS
    public static int[] compress(int[] array) {
        int size = array.length;
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int x : array) list.add(x);
        Collections.sort(list);

        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int boof = 1; //Min Value[Starting index of our compression]
        for (int x : list)
            if (!map.containsKey(x)) map.put(x, boof++);
        int[] compressedArray = new int[size];
        for (int i = 0; i < size; i++)
            compressedArray[i] = map.get(array[i]);
        return compressedArray;
    }
}

class FastScanner {
    //I don't understand how this works lmao
    private int BS = 1 << 16;
    private char NC = (char) 0;
    private byte[] buf = new byte[BS];
    private int bId = 0, size = 0;
    private char c = NC;
    private double cnt = 1;
    private BufferedInputStream in;

    public FastScanner() {
        in = new BufferedInputStream(System.in, BS);
    }

    public FastScanner(String s) {
        try {
            in = new BufferedInputStream(new FileInputStream(new File(s)), BS);
        } catch (Exception e) {
            in = new BufferedInputStream(System.in, BS);
        }
    }

    private char getChar() {
        while (bId == size) {
            try {
                size = in.read(buf);
            } catch (Exception e) {
                return NC;
            }
            if (size == -1) return NC;
            bId = 0;
        }
        return (char) buf[bId++];
    }

    public int nextInt() {
        return (int) nextLong();
    }

    public int[] nextInts(int N) {
        int[] res = new int[N];
        for (int i = 0; i < N; i++) {
            res[i] = (int) nextLong();
        }
        return res;
    }

    public long[] nextLongs(int N) {
        long[] res = new long[N];
        for (int i = 0; i < N; i++) {
            res[i] = nextLong();
        }
        return res;
    }

    public long nextLong() {
        cnt = 1;
        boolean neg = false;
        if (c == NC) c = getChar();
        for (; (c < '0' || c > '9'); c = getChar()) {
            if (c == '-') neg = true;
        }
        long res = 0;
        for (; c >= '0' && c <= '9'; c = getChar()) {
            res = (res << 3) + (res << 1) + c - '0';
            cnt *= 10;
        }
        return neg ? -res : res;
    }

    public double nextDouble() {
        double cur = nextLong();
        return c != '.' ? cur : cur + nextLong() / cnt;
    }

    public double[] nextDoubles(int N) {
        double[] res = new double[N];
        for (int i = 0; i < N; i++) {
            res[i] = nextDouble();
        }
        return res;
    }

    public String next() {
        StringBuilder res = new StringBuilder();
        while (c <= 32) c = getChar();
        while (c > 32) {
            res.append(c);
            c = getChar();
        }
        return res.toString();
    }

    public String nextLine() {
        StringBuilder res = new StringBuilder();
        while (c <= 32) c = getChar();
        while (c != '\n') {
            res.append(c);
            c = getChar();
        }
        return res.toString();
    }

    public boolean hasNext() {
        if (c > 32) return true;
        while (true) {
            c = getChar();
            if (c == NC) return false;
            else if (c > 32) return true;
        }
    }
}

