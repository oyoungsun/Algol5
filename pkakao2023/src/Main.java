import java.util.*;
class node implements Comparable<node> {
    int idx;
    int depth;
    public node(int idx, int depth){
        this.idx=idx;
        this.depth=depth;
    }

    @Override
    public int compareTo(node o) {

        return Math.abs(this.idx);

    }
}
class Solution {
    static int max=0;
    static List<Integer> exp = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        solution(n);
    }
    public static int solution(int storey) {
        int temp = storey;
        int d=0;
        while(temp>10){
            temp = temp%10;
            d++;
        }
        for(int i=0; i<=d+2; i++){
            exp.add( (int)Math.pow(10, i));
            exp.add( (int)Math.pow(10, i)* -1);
        }
        dfs(storey, 0);
        System.out.println(max);
        int answer = max;
        return answer;
    }
    public static void dfs(int start, int end){
        Set<Integer> set = new HashSet<>(); //방문 검사
        PriorityQueue<node> stack = new PriorityQueue<>();
        stack.add(new node(start,0));
        set.add(start);

        while(!stack.isEmpty()){
            node now = stack.poll();
            if(now.idx==end){
                max = Math.max(now.depth, max);
                return;
            }
            for(int i : exp){
                int next = now.idx+i;
                if(set.contains(next)) continue;
                stack.add(new node(next, now.depth+1));
                set.add(next);
            }
        }
    }
}