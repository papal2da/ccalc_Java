import java.lang.reflect.Array;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.*;

//import static javax.swing.text.rtf.RTFAttributes.BooleanAttribute.True;

public class Main {
    public static void main(String[] args) throws RuntimeException {

        int seq1 = 0;
        int seq2 = 0;
        Scanner in = new Scanner(System.in);
        String stroka = in.nextLine();
        char[] znak1 = {'+', '-', '/', '*'};
        int symb_seq = 0;
        for (char m : znak1) {
            for (int k = 0; k < stroka.length(); k++) {
                if (m == stroka.charAt(k)) {
                    symb_seq += 1;
                }
            }
        }
        if (symb_seq > 1) {
            throw new RuntimeException("Неверный формат введенного выражения!");
        }


        in.close();
        String[] str = new String[2];                            // создание массива для подстрок(введенных чисел)
        int sch = 0;
        for (String s : stroka.split("\\+|-|\\*|/|\\s")) {      // разбиваем строку на массив из подстрок sch - счетчик
            if (sch > 1){
                throw new RuntimeException("Неверный формат введенного выражения!");          // проверка формата в том случае, когда введено более 2 чисел
            }
            str[sch] = s;
            sch += 1;
            }
        if (sch == 1){
            throw new RuntimeException("Неверный формат введенного выражения!");     // проверка, что введено два числа
        }

        boolean checked_num1 = romanInts.checkRoman(str[0]);        // проверяем,являются ли числа римскими
        boolean checked_num2 = romanInts.checkRoman(str[1]);        // подаем в метод чекРоман поочередно
        int[] str_romanresult = new int[2];                         // задание массива для хранения сконвертированных чисел
        if (checked_num1 == true) {
            if (checked_num2 == true) {
                seq1 = romanInts.romanToInt(str[0]);
                seq2 = romanInts.romanToInt(str[1]);
            } else {
                throw new RuntimeException("Несоответствие систем счисления!");
            }
        } else if (checked_num2 == false) {
             try {
                 seq1 = Integer.parseInt(str[0]);                               // преобразование каждого из чисел
                 seq2 = Integer.parseInt(str[1]);
             }catch (RuntimeException e){                                  // хранящихся в массиве в виде строк
                 throw new RuntimeException("Неверный формат введенного выражения!");
             }

        }else {
            throw new RuntimeException("Несоответствие систем счисления!");     // проверка на то что число целочисленного типа
        }

        if (seq1>10 | seq2>10){
            throw new RuntimeException("Введнное число больше 10!");      // проверка что ни одно из чисел не больше 10
        }

        String[] znak = {"+", "-", "/", "*"};                       // в этом блоке
            char zn = 0;                                              //    находим
            for (String l : znak) {                                      //    знак
                int ch = stroka.indexOf(l);                           //  знак хранится в zn
                if (ch > -1) {
                    zn = stroka.charAt(ch);
                }
            }

        String znk = String.valueOf(zn);                                 // в этом блоке выполняется действие в заисимости
        int result = 0;                                                  //       от
        switch (znk) {                                                    // определенного нами знака
            case "+":
                result = seq1 + seq2;
                break;
            case "-":
                result = seq1 - seq2;
                break;
            case "*":
                result = seq1 * seq2;
                break;
            case "/":
                result = seq1 / seq2;
                break;
        }


        String[] arr_Romans = {"M", "CM", "D", "CD","C","XC","L","XL","X","IX","V","IV","I"};
        int[] arr_Arabians = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String result_Roman = "";
        if (checked_num2 == false && checked_num2 == false){
            System.out.print(result);
        }else {
            for (int i = 0; i<arr_Romans.length; i++){
                while (result >=arr_Arabians[i]){
                    result_Roman += arr_Romans[i];
                    result -= arr_Arabians[i];
                }
            }
            System.out.print(result_Roman);
        }

    }
    public static class romanInts {                                     // создание класса ,работающего
        static char pyat = 'V';                                         // с римскими числами
        static char ed = 'I';
        static char des = 'X';


        public static boolean checkRoman(String symb1) {                                                  //проверяет первый элемент поступившей в аргумент строки
            if (symb1.charAt(0) == pyat | symb1.charAt(0) == des | symb1.charAt(0) == ed) {                    // (на вход подаем поочередно наши числа)
                return true;                                                                             // true- число римское
            } else {                                                                                       // false - число не римское
                return false;
            }
        }

        public static Integer romanToInt(String romanString) {                                // блок преобразования
            int rez = 0;                                                                      // римского представления
            int predNum = 0;                                                                  // числа
            for (int i = romanString.length() - 1; i >= 0; i--) {                             // в арабское
                int tekNum = romanChartoInt(romanString.charAt(i));
                if (tekNum < predNum) {
                    rez -= tekNum;
                } else {
                    rez += tekNum;
                }
                predNum = tekNum;

            }
            return rez;
        }

        private static int romanChartoInt(char symb) {
            int s = 0;                                                                       // преобразование поданного на вход
            if (symb == pyat) {                                                         // символа в цифру
                s = 5;                                                               //
            }else if (symb == des) {
                s = 10;
            } else if (symb == ed) {
                s = 1;
            }
            return s;
        }
    }
}