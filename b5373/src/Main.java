import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int cube[][];
    //"위", "아래", "L-/R-(앞)" "L+/R+(뒷)" "F-/B-(왼)" "F+/B+(오)"
    static char white[] = {'w','y','r','o','g','b'};
    static char yellow[] = {'y','w','r','o','g','b'};

    static char red[] = {'r','o','y','w','g','b'};
    static char orange[] = {'o','r','y','w','g','b'};
    static char green[] = {'g','b','r','o','y','w'};
    static char blue[] = {'b','g','r','o','y','w'};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //int testcase = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());
        //위 -> 흰 아래 : 노, 앞 빨 뒷 오렌지 왼 초록 오른 파랑
        cube = new int [6][9]; //위 아래 앞 뒤 왼 오

        char temp[] = br.readLine().toCharArray();
        for(int i=0; i<temp.length; i+=3){
            char dir = temp[i];
            char clock = temp[i+1];
            rotation(dir, clock);
        }
        for(int i=0; i<3; i++){
            for(int j=i*3; j<i*3+3; j++){
                System.out.print(cube[0][j]%6);
            }
            System.out.println();
        }


     }

    private static void rotation(char dir, char clock) {
        if(dir=='L'){ //위치만 정함
            if(clock=='-'){
                for(int i=0; i<6; i++){
                    cube[i][0]+=2;
                    cube[i][3]+=2;
                    cube[i][6]+=2;
                }
            }
            else{
                for(int i=0; i<6; i++){
                    cube[i][0]+=3;
                    cube[i][3]+=3;
                    cube[i][6]+=3;
                }
            }
        }else if(dir=='R'){
            if(clock=='-'){
                for(int i=0; i<6; i++){
                    cube[i][2]+=3;
                    cube[i][5]+=3;
                    cube[i][8]+=3;
                }
            }
            else{
                for(int i=0; i<6; i++){
                    cube[i][2]+=2;
                    cube[i][5]+=2;
                    cube[i][8]+=2;
                }
            }
        }else if(dir=='F'){
            if(clock=='-'){
                for(int i=0; i<6; i++){
                    cube[i][0]+=4;
                    cube[i][1]+=4;
                    cube[i][2]+=4;
                }
            }
            else{
                for(int i=0; i<6; i++){
                    cube[i][0]+=5;
                    cube[i][1]+=5;
                    cube[i][2]+=5;
                }
            }
        }else{ //B
            if(clock=='-'){
                for(int i=0; i<6; i++){
                    cube[i][6]+=5;
                    cube[i][7]+=5;
                    cube[i][8]+=5;
                }
            }
            else{
                for(int i=0; i<6; i++){
                    cube[i][6]+=4;
                    cube[i][7]+=4;
                    cube[i][8]+=4;
                }
            }
        }
        setting();
    }

    private static void setting() {
        for(int i=0; i<6; i++){

        }
    }
}
