import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите математическую операцию (например 1 + 2): ");
        String inputText = scanner.nextLine();
        System.out.println("Результат: " + calc(inputText));

    }

    static String findOperator(String inputText) throws Exception {
        if (inputText.contains("+")) return "+";
        else if (inputText.contains("-")) return "-";
        else if (inputText.contains("*")) return "*";
        else if (inputText.contains("/")) return "/";
        else throw new Exception("неверный оператор");
    }

    static void checkValue(int num) throws Exception {
        if ((num < 1) || (num > 10)) {
            throw new Exception("вводимые числа не могут быть меньше 1 и больше 10");
        }
    }

    public static String calc(String inputText) throws Exception {
        int num1 = 0, num2 = 0, resultArabic;
        String operator, result;
        boolean twoNumsIsRoman = false;

        inputText = inputText.replaceAll(" ", ""); // удаляем все пробелы

        String[] nums = inputText.split("[+\\-*/]"); // делим введенную строку регулярным выражением
        if (nums.length != 2) {
            throw new Exception("должно быть два числа и оператор (+, -, *, /)");
        }

        if ((Roman.isRomanNum(nums[0])) && (Roman.isRomanNum(nums[1]))) {
            num1 = Roman.convertToArabNum(nums[0]);
            num2 = Roman.convertToArabNum(nums[1]);
            twoNumsIsRoman = true;
        } else if (!(Roman.isRomanNum(nums[0])) && !(Roman.isRomanNum(nums[1]))) {
            num1 = Integer.parseInt(nums[0]); // находим первое число
            num2 = Integer.parseInt(nums[1]); // находим второе число
        } else if ((Roman.isRomanNum(nums[0])) && !(Roman.isRomanNum(nums[1]))) {
            throw new Exception("разные системы исчисления");
        } else if (!(Roman.isRomanNum(nums[0])) && (Roman.isRomanNum(nums[1]))) {
            throw new Exception("разные системы исчисления");
        }

        checkValue(num1);
        checkValue(num2);

        operator = findOperator(inputText); // находим оператор


        resultArabic = switch (operator) { // производим вычисление результата
            case "+" -> num1 + num2;
            case "-" -> num1 - num2;
            case "*" -> num1 * num2;
            case "/" -> {
                if (num2 == 0) {
                    throw new Exception("деление на ноль");
                }
                yield num1 / num2;
            }
            default -> throw new Exception("неверная математическая операция");
        };

        if (twoNumsIsRoman) {
            result = Roman.convertToRomanNum(resultArabic);
            return result;
        }
        result = Integer.toString(resultArabic);
        return result;

    }
}

class Roman {
    static String[] romanNums = new String[] {"C", "L", "X", "IX", "VIII", "VII", "VI", "V", "IV", "III", "II", "I"};
    static int[] arabicNums = new int[] {100, 50, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};

    public static boolean isRomanNum(String num) {
        for (String romanNum : romanNums) {
            if (num.equals(romanNum)) return true;
        }
        return false;
    }

    public static int convertToArabNum(String num) {
        for (int i = 0; i < romanNums.length; i++) {
            if (romanNums[i].equals(num)) {
                return arabicNums[i];
            }
        }
        return -1;
    }
    public static String convertToRomanNum(int num) {
    StringBuilder romanNum = new StringBuilder();
    for (int i = 0; i < arabicNums.length; i++) {
        while (num >= arabicNums[i]) {
            romanNum.append(romanNums[i]);
            num -= arabicNums[i];
        }
    }
    return romanNum.toString();
    }
}