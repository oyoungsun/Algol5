import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    static int map[][] = new int[9][9];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                map[i][j] = sc.nextInt();
            }
        }

//            //백트래킹, 조합 구현
        dfs(0, 0);


    }

    private static void dfs(int row, int col) {
        if (col == 9) {
            dfs(row + 1, 0);
            return;
        }
        if (row == 9) {
            //해당 조합이 검사해서 타당하면, 출력함
            StringBuilder sb = new StringBuilder();
            //출력
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sb.append(map[i][j]).append(' ');
                }
                sb.append("\n");
            }
            System.out.println(sb);
            System.exit(0);
        }

        if (map[row][col] == 0) {
            for (int i = 1; i <= 9; i++) {
                if (test(row, col, i)) {
                    map[row][col] = i;
                    dfs(row, col + 1);
                }
            }
            map[row][col] = 0;
            return;
        }
        dfs(row, col+1);

    }


    private static boolean test(int row, int col, int visit) {

        //세로 검사

        for (int i = 0; i < 9; i++) {
            if (map[row][i] == visit) return false;
        }

        //가로 검사
        for (int i = 0; i < 9; i++) {
            if (map[i][col] == visit) return false;
        }
        //정사각형 검사
        int zx = (row / 3) * 3;
        int zy = (col / 3) * 3;
        for (int i = zx; i < zx + 3; i++) {
            for (int j = zy; j < zy + 3; j++) {
                if (map[i][j] == visit) return false;
            }
        }

        return true;
    }

}




