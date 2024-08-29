import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class MaxRectangle {
    static long[] findLajan(Long[] tape) {
        int size = tape.length;
        long lajan[] = new long[size];
        long left[] = new long[size];
        long right[] = new long[size];
        left[0] = tape[0];
        for (int i = 1; i < size; i++) {
            left[i] = Math.max(left[i - 1], tape[i]);
        }
        right[size - 1] = tape[size - 1];
        for (int i = size - 2; i >= 0; i--) {
            right[i] = Math.max(right[i + 1], tape[i]);
        }
        for (int i = 0; i < size; i++) {
            lajan[i] = Math.min(left[i], right[i]) - tape[i];
        }
        return lajan;
    }
    static long getMaxArea(long lajan[]) {
        Stack<Integer> stack = new Stack<>();
        long maxArea = 0;
        int tp;
        long areaTop;
        int size = lajan.length;
        int i = 0;
        while (i < size) {
            if (stack.empty() || lajan[stack.peek()] <= lajan[i]) {
                stack.push(i++);
            }
            else {
                tp = stack.peek();
                stack.pop();
                areaTop = lajan[tp] * (stack.empty() ? i : i - stack.peek() - 1);
                if (maxArea < areaTop) {
                    maxArea = areaTop;
                }
            }
        }
        while (stack.empty() == false) {
            tp = stack.peek();
            stack.pop();
            areaTop = lajan[tp] * (stack.empty() ? i : i - stack.peek() - 1);
            if (maxArea < areaTop) {
                maxArea = areaTop;
            }
        }
        return maxArea;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n =  Integer.parseInt(reader.readLine());
        String[] strings = reader.readLine().split(" ");
        Long[] tape = new Long[n];
        for(int i = 0; i < n; i++){
            tape[i] = Long.valueOf(strings[i]);
        }
        System.out.println(getMaxArea(findLajan(tape)));

    }
}
