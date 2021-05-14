public class FindMaximumAndIndex {
    public static void main(String[] args) {
        int[] intArray = { 72, 60, 83, 47, 89, 95, 1, 22, 64, 15, 87, 45, 13, 99 };
        int maximum;
        maximum = findMaximum(intArray);
        System.out.println("최댓값=" + maximum);
        int maxLocation = binarySearch(intArray, 0, intArray.length - 1, maximum);
        System.out.println("최대값의 인덱스는=" + maxLocation);
    }

    public static int findMaximum(int[] values) {
        int i;
        int max;
        // 최댓값을 첫번째 요소로 정한다
        max = values[0];
        // 최댓값을 두번째 요소부터 마지막 요소까지 차례대로 비교한다.
        // 만약 비교한 요소가 최대값보다 크다면 최대값을 그 요소로 바꾼다.
        for (i = 1; i < values.length; i++) {
            if (values[i] > max) {
                max = values[i];
            }
        }
        return max;
    }

    public static int binarySearch(int[] A, int first, int last, int target) {
        int result;
        if (first > last) {
            result = -1; // 찾는 값이 없는 경우 -1 반환
        } else {
            // 중간 요소의 지수를 계산한다
            int mid = (first + last) / 2;
            System.out.println("mid?"+mid);
            if (target == A[mid]) {
                result = mid;
            } else if (target < A[mid]) {
                // target이 중간 요소보다 작다면 앞쪽 반에서 찾는다.
                result = binarySearch(A, first, mid - 1, target);
            } else {
                // target이 중간 요소보다 크다면 뒷쪽에서 찾는다.
                result = binarySearch(A, mid + 1, last, target);
            }
        }
        return result;
    }
}
// 문제 의도
// 주어진 배열에서 최대값을 찾는 방법 = 순차적으로 접근
// 그 해답의 인덱스를 찾는 방법 = 이진 탐색
// 하나의 문제를 통해 두가지의 알고리즘을 연습 해 봄.