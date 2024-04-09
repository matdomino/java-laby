public class Calculations {
    public static long silnia(int num) {
        long result = 1;

        for (int i = 1; i <= num; i++) {
            result *= i;
        }

        return result;
    }

    public static long rangeSum(int start, int stop) {
        long result = 0;

        for (int i = start; i <= stop; i++) {
            result += i;
        }

        return result;
    }
}
