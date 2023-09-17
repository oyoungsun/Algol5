import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int dpMin[][];
    static int dpMax[][];

    static int n;
    static int array[][];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        array = new int[n+1][3];
        dpMin = new int[n+1][3];
        dpMax = new int[n+1][3];

        for(int i=1; i<=n; i++){
            array[i][0] = sc.nextInt();
            array[i][1] = sc.nextInt();
            array[i][2] = sc.nextInt();
            Arrays.fill(dpMin[i], Integer.MAX_VALUE);
        }
        dpMin[0][0] = dpMin[0][1]= dpMin[0][2] = 0;

        for(int i=1; i<=n; i++){
            //j=0
            dpMax[i][0] = Math.max(dpMax[i][0], dpMax[i-1][0]+array[i][0]);
            dpMax[i][0] = Math.max(dpMax[i][0], dpMax[i-1][1]+array[i][0]);

            dpMin[i][0] = Math.min(dpMin[i][0], dpMin[i-1][0]+array[i][0]);
            dpMin[i][0] = Math.min(dpMin[i][0], dpMin[i-1][1]+array[i][0]);
            //j=1
            dpMax[i][1] = Math.max(dpMax[i][1], dpMax[i-1][0]+array[i][1]);
            dpMax[i][1] = Math.max(dpMax[i][1], dpMax[i-1][1]+array[i][1]);
            dpMax[i][1] = Math.max(dpMax[i][1], dpMax[i-1][2]+array[i][1]);

            dpMin[i][1] = Math.min(dpMin[i][1], dpMin[i-1][0]+array[i][1]);
            dpMin[i][1] = Math.min(dpMin[i][1], dpMin[i-1][1]+array[i][1]);
            dpMin[i][1] = Math.min(dpMin[i][1], dpMin[i-1][2]+array[i][1]);
            //j=2
            dpMax[i][2] = Math.max(dpMax[i][2], dpMax[i-1][1]+array[i][2]);
            dpMax[i][2] = Math.max(dpMax[i][2], dpMax[i-1][2]+array[i][2]);
            dpMin[i][2] = Math.min(dpMin[i][2], dpMin[i-1][1]+array[i][2]);
            dpMin[i][2] = Math.min(dpMin[i][2], dpMin[i-1][2]+array[i][2]);
         }
        System.out.print(Math.max(dpMax[n][0], Math.max(dpMax[n][2],dpMax[n][1]))+" ");

        System.out.println(Math.min(dpMin[n][0], Math.min(dpMin[n][2],dpMin[n][1])));
    }
}
