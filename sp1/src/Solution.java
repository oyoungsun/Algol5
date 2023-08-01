import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

/*
   As the name of the class should be Solution , using Solution.java as the filename is recommended.
   In any case, you can execute your program by running 'java Solution' command.
 */
class Solution {
    static int Answer;
    static int N;

    public static void main(String args[]) throws Exception	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T =Integer.parseInt(br.readLine());
        for(int test_case = 0; test_case < T; test_case++) {
            Answer=0;
            N = Integer.parseInt(br.readLine());
            int origin[] = new int[N];
            int total=0;
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++){
                origin[i] = Integer.parseInt(st.nextToken());
                total+=origin[i];
            }
            int t=0;
            int copy[]=new int[N];
            int zero=0;
            while(t<=total){//
                t++; //시간
                //구슬굴리기
                zero=0;
                for(int i=0; i<N; i++){
                    copy[i] = origin[i];
                    if(copy[i]==0) zero++;
                }
                if(zero==0) {
                    Answer=1;
                    break;
                }
                for(int i=0; i<N; i++){
                    if(origin[i]!=0) {
                        copy[i] -= 1;
                        int next = (i + 1) % N;
                        copy[next] += 1;
                    }
                }
                if(change(origin, copy, t)) {
                    break;
                }
                origin = copy;
                copy = new int[N];
            }
            //상태변화 없을때
            //i - 2*i 칸 대칭인지 확인 N/=2
            //i - 4*i 칸 대칭인지 확인 N/=2
            //i - 8*i 대칭 -> N/=2
            if(zero!=0) {
                for (int i = 0; i < N; i++) {
                    if (origin[i] == 0) zero++;
                }
            }
            if(zero>0) {
                while (N > 1) {
                    if (!symatic(origin)) break;
                    N /= 2;
                }
                Answer=N;
            }
            else Answer=1;

            System.out.println("Case #"+(test_case+1));
            System.out.println(Answer);
        }
    }

    private static boolean symatic(int[] origin) {
        for(int i=0; i<N; i++){
            int next = (i+N/2)%N;
            if(origin[i]==origin[next]) continue;
            else return false;
        }
        return true;

    }

    private static boolean change(int[] origin, int[] copy, int t) {
        if(t==0) return false;
        for(int i=0; i<N; i++){
            int next = (i+1)%N;
            if(origin[i]!=copy[next]) return false;
        }
        return true;
    }
}