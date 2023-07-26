import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int dp[];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int array[] = new int[n];
        for(int i=0; i<n; i++){
            array[i] = sc.nextInt();
        }
        dp = new int[n];
        dp[0] = array[0];
        int j=0;
        for(int i=1; i<n; i++){
            if(dp[j]<array[i]){
                dp[j+1] = array[i];
                j+=1;
            }else{
                int idx = bS(0,j,array[i]);
                dp[idx] = array[i];
            }
        }
        int cnt = j+1;
        System.out.println(cnt);
    }

    private static int bS(int left, int right, int target) {

        int mid;
        while(left<right){
            mid = (left+right)/2;
            if(dp[mid]<target) left = mid+1;
            else right=mid;
        }
        return right;

    }
}
