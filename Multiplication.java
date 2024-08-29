import java.util.Scanner;

public class Multiplication {
    static int product(int[] R , int[] S){
        long answer = 1;
        for (int i = 0; i < R.length; i++){
            answer *= ((R[i] + S[i])%13800905);
            answer = answer%13800905;
        }
        return (int)answer;
    }
    static int[] row(int[][] A , int m , int r){
        int[] row = new int[m];
        for (int i = 0; i < m; i++){
            row[i] = A[r][i];
        }
        return row;
    }
    static int[] column(int[][] B , int m , int c){
        int[] column = new int[m];
        for (int i = 0; i < m; i++){
            column[i] = B[i][c];
        }
        return column;
    }
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int k = scanner.nextInt();
        int[][] A = new int[n][m];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                A[i][j] = scanner.nextInt();
            }
        }
        int[][] B = new int[m][k];
        for (int i = 0; i < m; i++){
            for (int j = 0; j < k; j++){
                B[i][j] = scanner.nextInt();
            }
        }
        int[][] C = new int[n][k];
        for (int i = 0; i < n ; i++){
            for (int j = 0; j < k; j++){
                C[i][j] = product(row(A , m , i) , column(B , m , j));
            }
        }
        for (int i = 0; i < n ; i++){
            for (int j = 0; j < k; j++){
                System.out.print(C[i][j] + " ");
            }
            System.out.println();
        }

    }
}
