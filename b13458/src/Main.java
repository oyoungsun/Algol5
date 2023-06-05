import java.util.Scanner;

public class Main { //greedy
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        long count=0;
        int n = sc.nextInt();
        count +=n;
        int a[] = new int[n];
        for(int i=0; i< n; i++){
            a[i] = sc.nextInt();
        }
        int b = sc.nextInt();
        int c = sc.nextInt();
        int i=0;
        while(i<n){
            a[i] -=b;
            if(a[i] > c) {
                int remain = a[i] / c;
                a[i] = a[i] % c;
                count += remain;
                if(a[i]>0) count++;
            }else if(a[i]>0){
                count++;
            }
            i++;
        }
        System.out.println(count);
    }
}
