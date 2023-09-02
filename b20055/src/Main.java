import java.util.Scanner;

public class Main {
    static int array[];
    static int n;
    static int k;
    //0위치(올리는)를 계속 +1 % 2n으로 기록하면?
    public static void main(String[] args) {
        //1. 회전하기 &내리기
        //2. 로봇 한칸 이동하기(순서대로No로봇, 내구도1이상) & 내리기
        //3. 로봇 올리기
        //4. 종료검사, 0인 벨트 세기
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        array = new int[2*n];
        boolean robot[] = new boolean[n];
        for(int i=0; i<2*n; i++){
            array[i] = sc.nextInt();
        }
        int cnt=0;
        //로봇 올리기
        while(!terminate()){
            int temp = array[array.length - 1]; // 1. 벨트 한 칸 회전
            for (int i = array.length - 1; i > 0; i--) {
                array[i] = array[i - 1];
            }
            array[0] = temp;

            for (int i = robot.length - 1; i > 0; i--) {    // 로봇도 벨트와 같이 회전
                robot[i] = robot[i - 1];
            }
            robot[0] = false;

            robot[n - 1] = false;
            for (int i = n - 1; i > 0; i--) {   // 2. 로봇 이동가능하면 이동
                if (robot[i - 1] && !robot[i] && array[i] >= 1) {
                    robot[i] = true;
                    robot[i - 1] = false;
                    array[i]--;
                }
            }
            if(array[0]>0){
                robot[0]=true;
                array[0]--;
            }
            cnt++;

        }
        System.out.println(cnt);


    }

    private static boolean terminate() {
        int cnt=0;
        for(int i=0; i<2*n; i++){
            if(array[i]==0){
                cnt++;
            }
            if(cnt>=k) return true;
        }
        return false;
    }
}
