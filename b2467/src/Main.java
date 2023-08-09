import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static long array[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        array = new long[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            array[i] = Long.parseLong(st.nextToken());
        }
        int ans1 = 0, ans2 = 0;
        long min = Long.MAX_VALUE;
        for(int i=0; i<n-1; i++) {
            int from = i+1;
            int to = n-1;
            while (from <= to) {
                int mid = (from + to) / 2;
                long sum = Math.abs(array[i] + array[mid]);
                if (min > sum) {
                    min = sum;
                    ans1 = i;
                    ans2 = mid;
                }
                if (sum < 0) {
                    from = mid + 1;
                } else { arr[mid]+arr[i]>=0, sum
                    to = mid - 1;
                }
            }
        }
        System.out.println(array[ans1] + " " + array[ans2]);
    }
}

