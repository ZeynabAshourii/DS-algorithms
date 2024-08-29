import java.util.Scanner;

public class Cake {
    static long mod(long a , long k, long p){
        if (k == 0) return 1;
        if (k == 1) return a%p;
        long count = mod(a , k/2 , p);
        count = (count*count)%p;
        if (k%2 == 1) {
            count = (count*a)%p;
        }
        return count;
    }
    static long inverse(long a , long p){
        return mod(a , p-2 , p);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long[] factor = new long[1000003];
        factor[0] = 1;
        int t = scanner.nextInt();
        int p = scanner.nextInt();
        int[][] tests = new int[t][2];
        int maxN = 0;
        for (int i = 0; i < t; i++){
            tests[i][0] = scanner.nextInt();
            tests[i][1] = scanner.nextInt();
            if (tests[i][0] > maxN){
                maxN = tests[i][0];
            }
        }
        for (int i = 1; i <= maxN; i++){
            factor[i] = (factor[i-1]*i)%p;
        }
        for (int i = 0; i < t; i++){
            System.out.println((((factor[tests[i][0]]*inverse( factor[tests[i][1]] , p))%p)*inverse(factor[ (tests[i][0] - tests[i][1])] , p))%p);
        }
    }
}
