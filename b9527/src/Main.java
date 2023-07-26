import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long A = sc.nextLong();
        long B = sc.nextLong();
        int minDepth = ItoB(A);
        int maxDepth = ItoB(B);
        int bit[] = new int[maxDepth];
        int bit2[] = new int[minDepth];

        long ans = dp(B, maxDepth);
        long ans2 = dp(A-1, minDepth);
        //int ans = bitMask(B, 0, maxDepth, 0, bit);
        //int ans2 = bitMask(A-1, 0, minDepth, 0, bit2);
        System.out.println(ans2);

    }

    private static long dp(long B, int depth){
        int dp[] = new int[depth];
        dp[0] = 1;

        //나를 1로 가정 -> 내가 1인 경우가 (int) Math.pow(2, i )개 있음.
        for(int i=1; i<depth-1; i++){
            dp[i] += (int) Math.pow(2, i );
            //내바로 뒤는 0
            if(i-2>=0) {
                dp[i] += dp[i - 2];
            }
            //내바로 뒤는 1
            if(i-1>=0) {
                dp[i] += dp[i - 1];
            }
        }
        //마지막 depth-1번째 칸 :
        int[] origin = ItoBi(B, depth);
        //마지막은 0 last i
        int brk=0;
        //마지막 본인의 1 cnt
        int last =0;
        for(int i=0; i<depth; i++) {

            if (origin[i] == 1) {
                brk = depth-i-1;
                last++;
            }
        }

        long sum=0;

        for (int i = 0; i < depth; i++) {
            sum += dp[i];
        }
        //B가 2의 제곱수가 아니라면
        if(!gcd(B,2)) {
            //본인brk를 0으로 가정한 뒤
            //본인 자리수 뒤에꺼(brk-1) 을 0 (i-1)이라 가정 -> dp[brk-2]+pow (2^자리수)
            if(brk-2>=0) {
                dp[depth - 1] += dp[brk - 2] + (int) Math.pow(2, brk - 1);
            }
            //brk-1을 1이라 가정
            if(brk-1>0) {
                dp[depth - 1] += dp[brk - 1] + (int) Math.pow(2, brk - 1);
            }
            if(brk-1==0) {
                dp[depth - 1] += dp[brk - 1];
            }
            sum += last;
        }


        return sum;
    }
    static boolean gcd(long B, long A){
        int r=1;
        while(B>r){
            r*=2;
        }
        if(B==r) return true;
        return false;
    }
    static int[] ItoBi(long d, int depth){ //자리수 구해줌
        int [] total = new int[depth];
        int i=0;
        long r = (long) Math.pow(2, depth-1);
        while(d>=0&&i<depth){
            if(d-r>=0){
                total[i]=1;
                d-=r;
            }
            i++;
            r/=2;
        }
        return total;
    }


    static int ItoB(long d){ //자리수 구해줌
        long r = 1;
        int cnt=0;
        while(d>=r){
            r*=2;
            cnt++;
        }
        return cnt;

    }
}
