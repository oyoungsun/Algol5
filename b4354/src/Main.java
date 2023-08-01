import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String temp = "";
        while (!(temp = br.readLine()).equals(".")) {
            char[] list = temp.toCharArray();
            int i = list.length;
            if (i == 0) System.out.println(0);
            else {
                int p[] = findPattern(list);
//            System.out.println(temp);
//            for (int j = 0; j < i; j++) {
//                System.out.print(p[j] + " ");
//            }
//            System.out.println();
                if(i%(i - p[i - 1])!=0){
                    //7%(7-6) = 0
                    System.out.println(1);
                }else {
                    System.out.println(i / (i - p[i - 1]));
                }
            }
        }
    }

    private static int [] findPattern(char[] list) {
        int n = list.length;
        int [] table = new int[n];
        int idx=0;
        for(int i=1; i<n; i++){
            while(idx>0&&list[i] != list[idx]){
                idx = table[idx-1];
            }
            if(list[i]==list[idx]){
                idx+=1;
                table[i]=idx;
            }
        }
        return table;
    }
}
