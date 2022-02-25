
//Program 2
import java.util.Scanner;
public class Solution {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();

        int notPresent = 0;

        for (int x : arr) notPresent ^= x;

        System.out.println(notPresent);

    }
}


/***
 Scanner sc = new Scanner(System.in);
 int temp = sc.nextInt();

 int evenSum = 0, oddSum = 0;
 while (temp > 0) {
 int digit = temp % 10;
 if (digit % 2 == 0) evenSum += digit;
 else oddSum += digit;
 temp /= 10;
 }

 System.out.println(Math.abs(evenSum - oddSum));
 ***/