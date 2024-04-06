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

    public static int calc(String inputText) throws Exception {
        int num1, num2, result;
        String operator;

        inputText = inputText.replaceAll(" ", ""); // удаляем все пробелы

        String[] nums = inputText.split("[+\\-*/]"); // делим введенную строку регулярным выражением
        if (nums.length != 2) {
            throw new Exception("должно быть два числа и оператор (+, -, *, /)");
        }

        num1 = Integer.parseInt(nums[0]); // находим первое число
        num2 = Integer.parseInt(nums[1]); // находим второе число
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

        return result;

    }
}