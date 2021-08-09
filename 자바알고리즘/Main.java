import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] intArray = new int[n];
        for (int i = 0; i < n; i++) {
            intArray[i] = scanner.nextInt();
        }
        System.out.println(answer(n, intArray));
        
    }

    public static int answer(int n, int[] intArray) {
        int answer = 1, max = intArray[0];
        for (int i = 0; i < n; i++) {
            if(intArray[i]>max){
                answer++;
                max = intArray[i];
            }
        }
        return answer;

    }
}