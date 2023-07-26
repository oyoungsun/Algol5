import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int map[][] = new int[10][10];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i=0; i<10; i++){
            String temp = br.readLine();
            char [] input = temp.toCharArray();
            for(int j=0; j<10; j++){
                if(input[j]=='#') map[i][j]=0;
                else map[i][j]=1;
            }

        }
    }
}
