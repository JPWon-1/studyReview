import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        for (int i : solution(arr)) {
            System.out.print(i + " ");
        }
    }

    public static int[] solution(int[] arr) {
        int length = arr.length;
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            int rank = 1;
            for (int j = 0; j < length; j++) {
                if (arr[i] < arr[j]) {
                    rank++;
                }
            }
            result[i] = rank;
        }
        return result;
    }

}