package hw1;

public class Task2 {
    public static int countDigits(int num) {
        if (num == 0) {
            return 1;
        }

        int res = 0;
        num = Math.abs(num);

        while (num > 0) {
            ++res;
            num /= 10;
        }

        return res;
    }
}
