import java.util.Scanner;

public class Displacement {
    static int binarySearch(int arr[], int l, int r, int x) {
        while (l <= r) {
            if (l == r){
                if (arr[l] <= x){
                    return l+1;
                }
                else {
                    return l;
                }
            }
            int mid = (l + r) / 2;

            if (arr[mid] == x){
                return mid+1;
            }
            else if (arr[mid] > x) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return -1;
    }
    static long inversions(int[] A , int[] L, int[] R){
        int count = 0;
        int m = 0;
        int n = 0;
        while (m < L.length || n < R.length){
            if (m == L.length){
                n++;
            } else if (n == R.length) {
                m++;
            } else if(19*R[n] < L[m]){
                count += L.length - m;
                n++;
            }
            else {
                m++;
            }
        }
        int i = 0;
        int j = 0;
        while (i < L.length || j < R.length){
            if (i == L.length){
                A[i+j] = R[j];
                j++;
            } else if (j == R.length) {
                A[i+j] = L[i];
                i++;
            }else if (L[i] <= R[j]){
                A[i+j] = L[i];
                i++;
            } else {
                A[i+j] = R[j];
                j++;
            }
        }
        return count;
    }

    static long count(int[] A){
        int count = 0;
        if (A.length != 1){
            int[] L = new int[A.length / 2];
            int[] R = new int[A.length - A.length / 2];
            for (int i = 0; i < A.length / 2; i++) {
                L[i] = A[i];
            }
            for (int i = 0; i < A.length - A.length / 2; i++) {
                R[i] = A[i + A.length / 2];
            }
            count += count(L) + count(R) + inversions(A , L , R);
        }

        return count;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] A = new int[n];
        for (int i = 0; i < n; i++){
            A[i] = scanner.nextInt();
        }
        System.out.println(count(A));
    }
}
