import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HeapDisplacement {
    static int maxHeapify(Long[] A, int index){
        int count = 0;
        int left = 2*index;
        int right = 2*index + 1;
        int largest = index;
        if (left <= A.length && A[left - 1] > A[largest - 1]){
            largest = left;
        }
        if (right <= A.length && A[right - 1] > A[largest - 1]){
            largest = right;
        }
        if (largest != index){
            long temp = A[index - 1];
            A[index - 1] = A[largest - 1];
            A[largest - 1] = temp;
            count++;
            count += maxHeapify(A , largest);
        }
        return count;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n =  Integer.parseInt(reader.readLine());
        String[] strings = reader.readLine().split(" ");
        Long[] A = new Long[n];
        for(int i = 0; i < n; i++){
            A[i] = Long.valueOf(strings[i]);
        }
        int count = 0;
        for (int i = n/2 ; i >= 1; i --){
            count += maxHeapify(A , i);
        }
        System.out.println(count);
    }
}
