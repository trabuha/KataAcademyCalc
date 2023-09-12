import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner console = new Scanner(System.in);
        System.out.println("Введите выражение: ");
        String input = console.nextLine();
        System.out.println(calc(input));
    }


    //Ищем символ операции в строке и возвращаем этот символ в String
    static String findOper(String keyOp) {
        if (keyOp.contains("*")) return "*";
        else if (keyOp.contains("/")) return "/";
        else if (keyOp.contains("+")) return "+";
        else if (keyOp.contains("-")) return "-";
        else return null;
    }


    //Производим саму операцию с операндами и возвращаем результат в int
    static int calculateResult(int x, int y, String operation) {
        if (operation.equals("*")) return x * y;
        else if (operation.equals("/")) return x / y;
        else if (operation.equals("+")) return x + y;
        else return x - y;
    }


    public static String calc(String input) throws Exception {
        String result;
        boolean realRom;
        int a, b;
        if (input.contains(" ")) {
            String[] operandsAndOperator = input.split(" ");
            if (operandsAndOperator.length != 3) {
                throw new Exception("Формат математической операции не удовлетворяет условию задания");
            } else if (Convert.realRom(operandsAndOperator[0]) && Convert.realRom(operandsAndOperator[2])) {
                a = Convert.convertRomanianToArabian(operandsAndOperator[0]);
                b = Convert.convertRomanianToArabian(operandsAndOperator[2]);
                realRom = true;
            } else if (!Convert.realRom(operandsAndOperator[0]) && !Convert.realRom(operandsAndOperator[2])) {
                a = Integer.parseInt(operandsAndOperator[0]);
                b = Integer.parseInt(operandsAndOperator[2]);
                realRom = false;
            } else {
                throw new Exception("Одновременно можно использовать одинаковые системы счисления");
            }

        } else {
            String[] operandsAndOperator = input.split("[+\\-*/]");
            if (operandsAndOperator.length != 2) {
                throw new Exception("Формат математической операции не удовлетворяет условию задания");
            } else if (Convert.realRom(operandsAndOperator[0]) && Convert.realRom(operandsAndOperator[1])) {
                a = Convert.convertRomanianToArabian(operandsAndOperator[0]);
                b = Convert.convertRomanianToArabian(operandsAndOperator[1]);
                realRom = true;
            } else if (!Convert.realRom(operandsAndOperator[0]) && !Convert.realRom(operandsAndOperator[1])) {
                a = Integer.parseInt(operandsAndOperator[0]);
                b = Integer.parseInt(operandsAndOperator[1]);
                realRom = false;
            } else {
                throw new Exception("Одновременно можно использовать одинаковые системы счисления");
            }
        }

        //Ищем символ операции в введенной строке и выбрасываем исключение, если не содержит одну из нужных.
        String operation = findOper(input);
        if (operation == null) throw new Exception("Используйте одну из следующих операций: * / + -");

        //Проверяем, что числа <= 10
        if (a > 10 || b > 10) {
            throw new Exception("Числа должны быть от 1 до 10");
        }


        int arabResult = calculateResult(a, b, operation);


        if (realRom) {
            if (arabResult <= 0) {
                throw new Exception("В римской системе нет отрицательных чисел");
            }
            result = Convert.convertArabianToRomanian(arabResult);
        } else {
            result = String.valueOf(arabResult);
        }
        return result;
    }
}



class Convert {
    static String[] rom = new String[]{"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};

    //Проверяет - является ли переданный символ - римской цифрой
    public static boolean realRom(String k) {
        for (int i = 0; i < rom.length; i++) {
            if (k.equals(rom[i])) {
                return true;
            }
        }
        return false;
    }

    //Конвертирует римские в арабские
    public static int convertRomanianToArabian(String romSt) {
        for (int i = 0; i < rom.length; i++) {
            if (romSt.equals(rom[i])) {
                return i;
            }
        }
        return -1;
    }

    //Конвертирует арабские в римские
    public static String convertArabianToRomanian(int arabI) {
        return rom[arabI];
    }
}