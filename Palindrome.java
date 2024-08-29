import java.util.Scanner;

public class Palindrome {
    public static boolean isPal(int r , int s ,int p , int n , long[] powerOfQ , long[] hashCode , long[] reverseHashCode){
        long x = 0;
        long y  = 0;
        if (r != 0){
            x = hashCode[r-1];
        }
        if(s != n-1 ){
            y = reverseHashCode[n-s-2];
        }
        if ((s-r)%2 == 0){
            if (r >= n - 1 - s){
                if ((hashCode[(r+s)/2 - 1] - x + p)%p == (powerOfQ[r-n+1+s]*((reverseHashCode[n-((r+s)/2) -2] - y + p)%p) + p ) %p){
                    return  true;
                }
            } else {
                if (((((hashCode[(r + s) / 2 - 1] - x + p)%p) * powerOfQ[n - 1 - r - s] + p)+p) % p == (reverseHashCode[n - ((r + s) / 2) - 2] - y + p) % p) {
                    return true;
                }
            }
        } else {
            if (r >= n - 1 - s){
                if (((hashCode[(r+s-1)/2 - 1] - x) + p)%p == (powerOfQ[r-n+1+s]*((reverseHashCode[n-((r+s-1)/2) - 3]-y+p)%p) + p)%p){
                    return  true;
                }
            } else if((((hashCode[(r+s-1)/2 - 1] - x + p)%p)*powerOfQ[n-1-r-s]+p)%p == ((reverseHashCode[n-((r+s-1)/2) - 3] - y) + p)%p) {
                return true;
            }
        }
        return false;
    }
    public static void hash(String str, int n , int q , int p ,long[] powerOfQ , long[] hashCode , long[] reverseHashCode){
        powerOfQ[0] = 1;
        for (int i = 1; i < n; i++){
            powerOfQ[i] = (powerOfQ[i-1]*q)%p;
        }
        hashCode[0] = (str.charAt(0)%32);
        reverseHashCode[0] = (str.charAt(n-1)%32);
        for (int i = 1; i < n; i++ ){
            hashCode[i] = (hashCode[i-1] + (str.charAt(i)%32)*powerOfQ[i])%p;
            reverseHashCode[i] = (reverseHashCode[i-1] + (str.charAt(n-1-i)%32)*powerOfQ[i])%p;
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.next();
        int n = string.length();
        long[] powerOfQ1 = new long[n];
        long[] hashCode1 = new long[n];
        long[] reverseHashCode1 = new long[n];
        hash(string , n , 97 ,99800143 , powerOfQ1 , hashCode1 , reverseHashCode1);
        int[] lengthOdd = new int[n];
        int[] lengthEven = new int[n];
        for (int i = 0; i < n/2; i++){
            int l = 0;
            int r = i;
            while (l+1 < r){
                int mid;
                if ((l+r)%2 == 0){
                    mid = (l+r)/2;
                }else {
                    mid = (l+r+1)/2;
                }
                if (isPal(mid , 2*i - mid ,99800143 , n , powerOfQ1 , hashCode1 , reverseHashCode1) ){
                    r = mid;
                }else {
                    l = mid;
                }
            }
            if (l+1 == r){
                if(isPal(l , 2*i - l, 99800143 , n , powerOfQ1 ,hashCode1 , reverseHashCode1)){
                    r = l;
                }
            }
            lengthOdd[i] = 2*(i-r)+1;
            l = n-i-1;
            r = n-1;
            while (l+1 < r){
                int mid;
                if ((l+r)%2 == 0){
                    mid = (l+r)/2;
                }else {
                    mid = (l+r+1)/2;
                }
                if (isPal(2*(n-i-1) - mid , mid ,99800143 , n , powerOfQ1 , hashCode1 , reverseHashCode1)){
                    l = mid;
                }else {
                    r = mid;
                }
            }
            if (l+1 == r){
                if(isPal(2*(n-i-1) - r , r ,99800143 , n, powerOfQ1 ,hashCode1 , reverseHashCode1) ){
                    l = r;
                }
            }
            lengthOdd[n-1-i] = 2*(l-(n-i-1))+1;
        }
        if(n%2 == 1){
            int l = 0;
            int r = n/2;
            while (l+1 < r){
                int mid;
                if ((l+r)%2 == 0){
                    mid = (l+r)/2;
                }else {
                    mid = (l+r+1)/2;
                }
                if (isPal(mid , n - mid -1 ,99800143 , n , powerOfQ1 , hashCode1 , reverseHashCode1)){
                    r = mid;
                }else {
                    l = mid;
                }
            }
            if (l+1 == r){
                if(isPal(l , n - l-1, 99800143 , n , powerOfQ1 ,hashCode1 , reverseHashCode1)){
                    r = l;
                }
            }
            lengthOdd[n/2] = 2*(n/2-r)+1;
        }
        for (int i = 0; i < n/2; i++){
            if (string.charAt(i)%32 == string.charAt(i+1)%32) {
                int l = 0;
                int r = i;
                while (l + 1 < r) {
                    int mid;
                    if ((l + r) % 2 == 0) {
                        mid = (l + r) / 2;
                    } else {
                        mid = (l + r + 1) / 2;
                    }
                    if (isPal(mid, 2 * i - mid + 1, 99800143, n, powerOfQ1, hashCode1, reverseHashCode1)) {
                        r = mid;
                    } else {
                        l = mid;
                    }
                }
                if (l + 1 == r) {
                    if (isPal(l, 2 * i - l + 1, 99800143, n, powerOfQ1, hashCode1, reverseHashCode1)) {
                        r = l;
                    }
                }
                lengthEven[i] = 2 * (i - r) + 2;
            }
            else {
                lengthEven[i] = 0;
            }
            if (i != 0 && string.charAt(n-i-1)%32 == string.charAt(n-i)%32) {
                int l = n - i;
                int r = n - 1;
                while (l + 1 < r) {
                    int mid;
                    if ((l + r) % 2 == 0) {
                        mid = (l + r) / 2;
                    } else {
                        mid = (l + r + 1) / 2;
                    }
                    if (isPal(2 * (n - i) - 1 - mid, mid, 99800143, n, powerOfQ1, hashCode1, reverseHashCode1)) {
                        l = mid;
                    } else {
                        r = mid;
                    }
                }
                if (l + 1 == r) {
                    if (isPal(2 * (n - i) - 1 - r, r, 99800143, n, powerOfQ1, hashCode1, reverseHashCode1)) {
                        l = r;
                    }
                }
                lengthEven[n - 1 - i] = 2 * (l - (n - i - 1));
            }else {
                lengthEven[n-i-1] = 0;
            }
        }
        if(n%2 == 1){
            if (n/2 + 1 < n && string.charAt(n/2)%32 == string.charAt(n/2+1)%32) {
                int l = 0;
                int r = n/2;
                while (l + 1 < r) {
                    int mid;
                    if ((l + r) % 2 == 0) {
                        mid = (l + r) / 2;
                    } else {
                        mid = (l + r + 1) / 2;
                    }
                    if (isPal(mid, n - mid, 99800143, n, powerOfQ1, hashCode1, reverseHashCode1)) {
                        r = mid;
                    } else {
                        l = mid;
                    }
                }
                if (l != 0 && l + 1 == r) {
                    if (isPal(l, n - l , 99800143, n, powerOfQ1, hashCode1, reverseHashCode1)) {
                        r = l;
                    }
                }
                lengthEven[n/2] = 2 * (n/2 - r) + 2;
            }
            else {
                lengthEven[n/2] = 0;
            }
        }
        long sum = 0;
        for (int i = 0; i < n; i++){
            sum += lengthEven[i]/2;
            sum += (lengthOdd[i] + 1)/2;
        }
        System.out.println(sum);

    }
}
