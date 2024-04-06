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

    static boolean checkValue(int num) throws Exception {
        if (num >= 1 && num <= 10) return true;
        else throw new Exception("число должно быть больше 1 и меньше 10");
    }

    static boolean isArabic(String inputText) throws Exception {
        return true;
    }

    public static int calc(String inputText) throws Exception {
        int num1 = 0, num2 = 0, result;
        String operator;
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
            twoNumsIsRoman = false;
        } else if ((Roman.isRomanNum(nums[0])) && !(Roman.isRomanNum(nums[1]))) {
            throw new Exception("разные системы исчисления");
        } else if (!(Roman.isRomanNum(nums[0])) && (Roman.isRomanNum(nums[1]))) {
            throw new Exception("разные системы исчисления");
        }




        checkValue(num1);
        checkValue(num2);

        operator = findOperator(inputText); // находим оператор

        result = switch (operator) { // производим вычисление результата
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

        }

        return result;

    }
}

class Roman {
    static String[] romanNums = new String[] {"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "L", "C"};

    public static boolean isRomanNum(String num) {
        for (String romanNum : romanNums) {
            if (num.equals(romanNum)) return true;
        }
        return false;
    }

    public static int convertToArabNum(String num) {
        for (int i = 0; i < romanNums.length; i++) {
            if (romanNums[i].equals(num)) return i;
        }
        return -1;
    }
//    public static String convertToRomanNum(int num) {
//        StringBuilder romanNum = new StringBuilder();
//        for (int i = 0; i < romanNums.length; i++) {
//            for
//        }
//    }
}