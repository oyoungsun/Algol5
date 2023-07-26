import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int N;
    static int[] arr;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        arr = new int[N];
        for(int i=0; i<N; i++){
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);
        int total=0;
        int [] dp = new int[N];
        int sum=arr[0];
        dp[0]= arr[0];
        for(int i=1; i<N; i++){
            sum += arr[i];
            dp[i] = sum;
        }
        for(int i=1; i<N; i++){
            total += dp[i];
        }
        System.out.println(total);
    }
}
