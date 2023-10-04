package hw1;

public class Task5 {
    public static boolean isPalindromeDescendant(int num) {
        if (num / 10 != 0 && String.valueOf(num).length() % 2 == 0) {
            StringBuilder newNum = new StringBuilder();
            String strNum = String.valueOf(num);
            newNum.append(strNum);
            if (newNum.reverse().toString().contentEquals(newNum)) {
                return true;
            }
            newNum.reverse();
            num = Integer.parseInt(newNum.toString());
            newNum = new StringBuilder();

            while (num > 0) {
                newNum.append(num % 10 + (num / 10) % 10);
                num /= 100;
            }
            isPalindromeDescendant(Integer.parseInt(newNum.toString()));
        } else {
            return false;
        }
        return false;
    }
}
