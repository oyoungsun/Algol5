import java.util.Stack;

class node{
    int num;
    int depth;
    public node(int num, int depth){
        this.num = num;
        this.depth = depth;
    }
}
public class Main {
    public static void main(String[] args) {
        int temp[] = solution(new long[]{15});
        for (int i=0; i<temp.length; i++) {
            System.out.println(temp[i]);
        }
    }

    public static int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            String binary = ItoB(numbers[i]); //이진수로 만들기
            boolean suc = makeTree(binary);//트리로 만들고 검사
            if (suc) answer[i] = 1;
            else answer[i] = 0;
        }

        return answer;
    }

    public static String ItoB(long number) {
        long r = 1;
        StringBuilder sb = new StringBuilder();
        while (number > r) {
            r *= 2;
        }
        r /= 2;
        while (number >= 0 && r > 0) {
            if ((number - r) >= 0) {
                number -= r;
                sb.append("1");
            } else {
                sb.append("0");
            }
            r /= 2;
        }
        return sb.toString();
    }


    public static boolean makeTree(String binary) {
        int depth = (int) (Math.log(binary.length()) / Math.log(2));
        int nodes = (int) (Math.pow(2, depth)-1);
        if(nodes<binary.length()){
            depth+=1;
            nodes = (int) (Math.pow(2, depth)-1);
        }
        int diff = nodes-binary.length();
        char[] temp = new char[nodes+1];
        int n = temp.length;
        boolean visit[] = new boolean[n];
        visit[0] = true;
        for(int i=0; i<diff+1; i++){
            temp[i]='0';
        }
        for (int i = diff+1; i < temp.length; i++) {
            temp[i] = binary.charAt(i - diff-1);
        }
        return checkO(temp, n, visit);



    }
    private static boolean checkO(char[] temp, int n, boolean[] visit) {
        int root = n / 2;
        if (n % 2 == 0) ;//맨앞에 0추가
        Stack<node> s = new Stack();
        int depth = (int) (Math.log(n) / Math.log(2));
        s.push(new node(root, depth - 1));

        while (!s.isEmpty()) {
            node parent = s.pop();
            visit[parent.num] = true;
            if (temp[parent.num] == '1') {
                if (parent.num - parent.depth >= 0) {
                    if (!visit[parent.num - parent.depth]) {
                        visit[parent.num - parent.depth] = true;
                        s.push(new node(parent.num - parent.depth, parent.depth - 1));
                    }
                }
                if (parent.num + parent.depth < n) {
                    if (!visit[parent.num + parent.depth]) {
                        visit[parent.num + parent.depth] = true;
                        s.push(new node(parent.num + parent.depth, parent.depth - 1));
                    }
                }
            } else {
                if (temp[parent.num + parent.depth] == '1' || temp[parent.num - parent.depth] == '1') {
                    return false;
                }
            }
        }
        return true;
    }
}
