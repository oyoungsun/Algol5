import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int map[][];
    static int MAX_MOVE = 5;
    static int dir[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static int[] moveD = new int[MAX_MOVE];
    static int MAX_BLOCK = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        bruteforce(0);
        System.out.println(MAX_BLOCK);
    }

    private static void bruteforce(int depth) {
        // : 모든 조합을 만드는 역할
        if (depth == MAX_MOVE) {
            findMax();
            return;
        }
        int copy[][] = new int[n][n];
        for (int i = 0; i < n; i++) {
            copy[i] = map[i].clone();
        }
        for (int d = 0; d < 4; d++) {
            do2048(d);
            bruteforce(depth + 1);
            for (int i = 0; i < n; i++) {
                map[i] = copy[i].clone();
            }
        }
    }

    private static void do2048(int dir) {
        int srtx, srty;
        if (dir == 0) {
            for (int i = 0; i < n; i++) {//y, 모든 열에 대해
                int index = n - 1;
                int block = 0;
                for (int j = n - 1; j >= 0; j--) {
                    if (map[i][j] != 0) {
                        if (block == map[i][j]) {
                            map[i][index + 1] = block * 2;
                            map[i][j] = 0;
                            block = 0;
                        }
                    } else {
                        block = map[i][j];
                        map[i][j] = 0;
                        map[i][index] = block;
                        index--;
                    }
                }
            }
        } else if (dir == 1) {
            for (int i = 0; i < n; i++) {
                int index = n - 1;
                int block = 0;
                for (int j = n - 1; j >= 0; j--) {
                    if (map[j][i] != 0) {
                        if (block == map[j][i]) {
                            map[index + 1][i] = block * 2;
                            block = 0;
                            map[j][i] = 0;
                        } else {
                            block = map[j][i];
                            map[j][i] = 0;
                            map[index][i] = block;
                            index--;
                        }
                    }
                }
            }
        } else if (dir == 2) {
            for (int i = 0; i < n; i++) {
                int index = 0;
                int block = 0;
                for (int j = 0; j < n; j++) {
                    if (map[i][j] != 0) {
                        if (block == map[i][j]) {
                            map[i][index - 1] = block * 2;
                            block = 0;
                            map[i][j] = 0;
                        } else {
                            block = map[i][j];
                            map[i][j] = 0;
                            map[i][index] = block;
                            index++;
                        }
                    }
                }
            }
        } else {
            for (int i = 0; i < n; i++) {
                int index = 0;
                int block = 0;
                for (int j = 0; j < n; j++) {
                    if (map[j][i] != 0) {
                        if (block == map[j][i]) {
                            map[index - 1][i] = block * 2;
                            block = 0;
                            map[j][i] = 0;
                        } else {
                            block = map[j][i];
                            map[j][i] = 0;
                            map[index][i] = block;
                            index++;
                        }
                    }
                }
            }
        }
    }


    private static void findMax() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                MAX_BLOCK = Math.max(MAX_BLOCK, map[i][j]);
            }
        }
    }
}




