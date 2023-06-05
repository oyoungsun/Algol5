public class Main {
    public static void main(String[] args) {
        long sum = 0;
        for (int j = 1; j <= 10; j++) {
            for (int i = 10-j; i >0; i--) {
                sum += i;
            }
            for (int i = 0; i <= j; i++) {
                sum += i;
            }
        }
        System.out.println(sum);
    }
}
