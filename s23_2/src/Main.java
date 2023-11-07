import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

class sushi{
    int pos;
    public sushi(int pos){
        this.pos = pos;
    }
}

class customer{
    String name;
    boolean eatenAll;
    int x;
    public customer(String name, boolean eatenAll, int x){
        this.name = name;
        this.eatenAll = eatenAll;
        this.x = x;
    }
}
public class Main {
    static HashMap<String, HashMap<Long, Integer>> sushis = new HashMap<>();
    static HashMap<String, Integer> remain = new HashMap<>();
    static List<String> namelist = new ArrayList();
    static List<customer> visitList = new ArrayList<>(); //customer
    //이름, 나머지 먹어야 할 개수
    static long L;
    static int M;
    // <이름, <위치x, 개수>
    // -> n 개 만큼 먹으면 true 처리
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Long.parseLong(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        long k=1;
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            long t = Long.parseLong(st.nextToken());
            roundTable(t, t-k); //k-i회 테이블 돌린다.
            k = t-1;
            if(type == 100){
                int x = Integer.parseInt(st.nextToken());
                String name = st.nextToken();
                if(!namelist.contains(name)) namelist.add(name);
                make_sushi(t, x, name);
                for(customer c : visitList) {
                    if (remain.containsKey(c.name))
                        visit_cos(t, c.x, c.name, remain.get(c.name));
                }
            }else if(type == 200){
                int x = Integer.parseInt(st.nextToken());
                String name = st.nextToken();
                if(!namelist.contains(name)) namelist.add(name);
                visitList.add(new customer(name, false, x));
                int eatCount = Integer.parseInt(st.nextToken());
                visit_cos(t, x, name, eatCount);
                for(customer c : visitList) {
                    if (remain.containsKey(c.name))
                        visit_cos(t, c.x, c.name, remain.get(c.name));
                }
            }else{
                for(customer c : visitList) {
                    if (remain.containsKey(c.name))
                        visit_cos(t, c.x, c.name, remain.get(c.name));
                }
                picture(t);
            }
            k++;
        }

    }

    private static void roundTable(long t, long cnt) {
        //매 턴마다 테이블을 돌린다.
        for(String name : namelist){
            if(sushis.containsKey(name)){ //해당 스시가 존재 여부 확인
                HashMap<Long, Integer> sushi = sushis.get(name);
                HashMap<Long, Integer> temp = new HashMap<>();
                for(long x=0; x<L; x++){
                    if(sushi.containsKey(x)){
                        int sCnt = sushi.get(x);
                        long idx = (x + cnt) % L; // 새로운 위치
                        temp.put(idx, sCnt);
                    }
                }
                sushis.put(name, temp);
            }
        }
    }

    public static void make_sushi(long t, long x, String name) {
        //해당 네임에 초밥 위치, 개수 1을 추가한다.
        if(sushis.containsKey(name)){
            HashMap<Long, Integer> sushi = sushis.get(name);
            if(sushi.containsKey(x)){ //해당 위치에 이미 해당이름 스시 있으면
                int count = sushi.get(x);
                sushi.put(x, count+1); // 대체
            }else{ //해당 이름 스시X -> 1개 추가
                sushi.put(x, 1);
            }
        }else{ //이 이름 처음 추가
            HashMap<Long, Integer> temp = new HashMap<>();
            temp.put(x, 1);
            sushis.put(name, temp);
        }
    }

    public static void visit_cos(long t, long x, String name, int eatCount) { // 손님자리 x
        if(sushis.containsKey(name)){ //자기 이름 스시 중에서 탐색한다. 없으면 못먹는다.
            HashMap<Long, Integer> map = sushis.get(name); // 자기이름 스시 리스트
            if(map.containsKey(x)){ //x자리에 자기 이름 스시 있으면 찾아서 먹기
                int count = map.get(x); // 스시 개수
                if(count == eatCount){
                    map.remove(x); // 다 먹어치운다.
                    Optional<customer> eaten = Optional.ofNullable(
                            visitList.stream().filter(cus -> cus.name.equals(name)).findFirst().orElse(null));
                    eaten.ifPresent(e -> e.eatenAll=true); //해당 이름의 사람을 다먹었다 처리함
                } else if (count < eatCount) { //다 먹어치워도 남아있는다.
                    map.remove(x); //스시는 다 먹어치움
                    remain.put(name, eatCount-count); // 남은 양 넣는다.
                }else{ //다 먹어치워도 테이블에 남는다.
                    map.put(x, count-eatCount);
                    Optional<customer> eaten = Optional.ofNullable(
                            visitList.stream().filter(cus -> cus.name.equals(name)).findFirst().orElse(null));
                    eaten.ifPresent(e -> e.eatenAll=true); //해당 이름의 사람을 다먹었다 처리함
                }
            }
            else{ //샘이 아무것도 못먹을 떄
                remain.put(name, eatCount);
            }
        }
    }

    public static void picture(long t) {
        long sCnt=0;
        long pCnt=0;
        for(String name : namelist){
            if(sushis.containsKey(name)){ //해당 스시가 존재 여부 확인
                HashMap<Long, Integer> sushi = sushis.get(name);
                for(long x=0; x<L; x++){
                    if(sushi.containsKey(x)){
                        sCnt += sushi.get(x); //이름별 모든 테이블의 스시를 센다
                    }
                }
            }
        }
        pCnt = visitList.stream().filter(v -> v.eatenAll==false).collect(Collectors.counting());
        System.out.printf("%d %d\n", pCnt, sCnt);
    }
}