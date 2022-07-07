public class Main {

    public static void main(String[] args) {
        final String ORIGIN_STRING = "I wish you a good day!";
        System.out.println(ORIGIN_STRING);
        System.out.println(Reverse.String(ORIGIN_STRING));

        long startTime = System.nanoTime();
        for (int i = 1; i <= 100_000; i++) {
            Reverse.String(ORIGIN_STRING);
            if (i == 1000) {
                System.out.println((System.nanoTime() - startTime) / 1000000);
            }
            if (i == 10_000) {
                System.out.println((System.nanoTime() - startTime) / 1000000);
            }
            if (i == 100_000) {
                System.out.println((System.nanoTime() - startTime) / 1000000);
            }
        }
    }
}
