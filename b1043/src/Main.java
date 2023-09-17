import java.util.*;


public class Main {
    static boolean people[];
    static ArrayList<Integer> party[];
    static int m;
    static Queue<Integer> known = new LinkedList<>();
    static boolean mcount[];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        people = new boolean[n+1];
        party = new ArrayList[m];
        mcount = new boolean[m+1];
        int trueP = sc.nextInt();
        for(int i=0; i<trueP; i++){
             int tP=sc.nextInt();
             people[tP] = true;
             known.add(tP);
        }
        for(int i=0; i<m; i++){
            int partyN = sc.nextInt();
            party[i] = new ArrayList<>();
            for(int j=0; j<partyN; j++){
                int temp = sc.nextInt();
                party[i].add(temp);
            }
        }
        //dfs..?
        while(!known.isEmpty()){
            int know = known.poll();
            for(int i=0; i<m; i++){
                if(party[i].contains(know)){
                    mcount[i]=true;
                    for(int p : party[i]){
                        if(people[p]) continue;
                        people[p] = true;
                        known.add(p);
                    }
                }
            }
        }
        int cnt=0;
        for(int i=0; i<m; i++){
            if(!mcount[i]) cnt++;
        }
        System.out.println(cnt);
    }
}
