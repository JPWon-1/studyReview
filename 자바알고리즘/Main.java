import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();

        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt(); // 학생 수
        int n = scanner.nextInt(); // 테스트 수
        int[][] arr = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = scanner.nextInt();
            }
        }

        System.out.println(main.solution(arr, m, n));

    }

    public int solution(int[][] arr, int memberCnt, int examCnt) {
        int answer = 0;
        for (int i = 1; i <= memberCnt; i++) {
            for (int j = 1; j <= memberCnt; j++) {
                int tmpCnt = 0;
                for (int k = 0; k < examCnt; k++) {
                    int pi = 0, pj = 0;
                    for (int s = 0; s < memberCnt; s++) {
                        if (arr[k][s] == i)
                            pi = s;
                        if (arr[k][s] == j)
                            pj = s;
                    }
                    if (pi < pj)
                        tmpCnt++;
                }
                if (tmpCnt == examCnt) {
                    answer++;
                }
            }
        }

        return answer;
    }

}
