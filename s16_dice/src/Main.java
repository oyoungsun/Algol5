import java.io.*;
import java.util.*;

public class Main {
    static int map[][];

    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        int dice[] = new int[]{0, 0, 0, 0, 0, 0, 0};
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int now[] = new int[2];
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        now[0] = Integer.parseInt(st.nextToken());
        now[1] = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //명령어 파싱 
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            int commend = Integer.parseInt(st.nextToken());
            String dir = parsing(commend);
            int[] next = nextMove(dir, now);
            if (next[0] < 0 || next[1] < 0 || next[0] >= n || next[1] >= m) {
                continue; //무시
            }
            dice = roll(dice, dir); // 다이스 굴림
            dice[1] = touch(dice[1], next[0], next[1]); //바닥면 복사
            System.out.println(upper(dice));
            now = next;
        }
    }

    public static int touch(int below, int i, int j) { //바닥면의 숫자, map을 보고 새로운 바닥 숫자를 return.
        // 칸의 수가 0이면
        //주사위 바닥면->칸으로 복사
        if (map[i][j] == 0) {
            map[i][j] = below;
            return below;
        }
        //칸의 수 !=0
        //칸의 수 -> 주사위 바닥면 이동, 칸 = 0 대입    
        else {
            int temp = map[i][j];
            map[i][j] = 0;
            return temp;
        }
    }

    public static int upper(int dice[]) {
        return dice[6];
    }

    public static String parsing(int dir) { //바닥1로 sort후 바닥값 주면 방향대로 한칸 굴린 다음 바닥값 반환
        switch (dir) {
            case 1:
                return "동";
            case 2:
                return "서";
            case 3:
                return "북";
            case 4:
                return "남";
            default:
                return "";
        }
    }


    public static int[] roll(int dice[], String dir) { //방향에 따라 dice 재정렬
        switch (dir) {
            case "북":
                return new int[]{dice[0], dice[3], dice[2], dice[6], dice[1], dice[5], dice[4]};
            case "동":
                return new int[]{dice[0], dice[5], dice[1], dice[3], dice[4], dice[6], dice[2]};
            case "남":
                return new int[]{dice[0], dice[4], dice[2], dice[1], dice[6], dice[5], dice[3]};
            case "서":
                return new int[]{dice[0], dice[2], dice[6], dice[3], dice[4], dice[1], dice[5]};
            default:
                return dice;
        }
    }

    public static int[] nextMove(String dir, int[] now) { //바닥1로 sort후 바닥값 주면 방향대로 한칸 굴린 다음 바닥값 반환
        switch (dir) {
            case "북":
                return new int[]{now[0] - 1, now[1]};
            case "동":
                return new int[]{now[0], now[1] + 1};
            case "남":
                return new int[]{now[0] + 1, now[1]};
            case "서":
                return new int[]{now[0], now[1] - 1};
            default: return now;
        }
    }
}