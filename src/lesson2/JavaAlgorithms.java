package lesson2;

import kotlin.NotImplementedError;
import kotlin.Pair;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;

import java.util.Set;

import static java.lang.Math.sqrt;

@SuppressWarnings("unused")
public class JavaAlgorithms {
    /**
     * Получение наибольшей прибыли (она же -- поиск максимального подмассива)
     * Простая
     *
     * Во входном файле с именем inputName перечислены цены на акции компании в различные (возрастающие) моменты времени
     * (каждая цена идёт с новой строки). Цена -- это целое положительное число. Пример:
     *
     * 201
     * 196
     * 190
     * 198
     * 187
     * 194
     * 193
     * 185
     *
     * Выбрать два момента времени, первый из них для покупки акций, а второй для продажи, с тем, чтобы разница
     * между ценой продажи и ценой покупки была максимально большой. Второй момент должен быть раньше первого.
     * Вернуть пару из двух моментов.
     * Каждый момент обозначается целым числом -- номер строки во входном файле, нумерация с единицы.
     * Например, для приведённого выше файла результат должен быть Pair(3, 4)
     *
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public Pair<Integer, Integer> optimizeBuyAndSell(String inputName) {
        throw new NotImplementedError();
    }

    /**
     * Задача Иосифа Флафия.
     * Простая
     *
     * Образовав круг, стоят menNumber человек, пронумерованных от 1 до menNumber.
     *
     * 1 2 3
     * 8   4
     * 7 6 5
     *
     * Мы считаем от 1 до choiceInterval (например, до 5), начиная с 1-го человека по кругу.
     * Человек, на котором остановился счёт, выбывает.
     *
     * 1 2 3
     * 8   4
     * 7 6 х
     *
     * Далее счёт продолжается со следующего человека, также от 1 до choiceInterval.
     * Выбывшие при счёте пропускаются, и человек, на котором остановился счёт, выбывает.
     *
     * 1 х 3
     * 8   4
     * 7 6 Х
     *
     * Процедура повторяется, пока не останется один человек. Требуется вернуть его номер (в данном случае 3).
     *
     * 1 Х 3
     * х   4
     * 7 6 Х
     *
     * 1 Х 3
     * Х   4
     * х 6 Х
     *
     * х Х 3
     * Х   4
     * Х 6 Х
     *
     * Х Х 3
     * Х   х
     * Х 6 Х
     *
     * Х Х 3
     * Х   Х
     * Х х Х
     *
     * Общий комментарий: решение из Википедии для этой задачи принимается,
     * но приветствуется попытка решить её самостоятельно.
     */
    static public int josephTask(int menNumber, int choiceInterval) { // Закоментированная часть - моя тщетная попытка сделать некостыльный нормальный алгоритм))))0
        // После решил воспользоваться помощью интернетов И сдеалал со сложностью O(n)
//        if (menNumber == 1) {
//            return 1;
//        }
//
//        if (choiceInterval == 1) {
//            return menNumber;
//        }
//
//        int deleteNumberCorrection = 0;
//        int[] array = new int[menNumber];
//
//        for (int i = 0; i < menNumber; i++) {
//            array[i] += i + 1;
//        }
//
//        array[choiceInterval - 1] = 0;
//        int deleteNumber = choiceInterval;
//
//        int count = 1;
//
//        for (int i = 1; i < menNumber - 1; i++) {
//
//            while (count != choiceInterval) {
//                if (array[deleteNumber] != 0) {
//                    count++;
//                    deleteNumber++;
//                } else {
//                    deleteNumber++;
//                }
//                deleteNumber %= menNumber;
//            }
//            if (deleteNumber - deleteNumberCorrection == -1) {
//                array[menNumber - 1] = 0;
//            } else {
//                array[deleteNumber - deleteNumberCorrection] = 0;
//            }
//
//            if (array[(deleteNumber - deleteNumberCorrection + 1) % menNumber ] == 0 ||
//                    array[(deleteNumber - deleteNumberCorrection + choiceInterval) % menNumber ] == 0) {
//                count = 0;
//                deleteNumberCorrection = 1;
//            } else {
//                count = 1;
//                deleteNumberCorrection = 0;
//            }
//        }
//        for (int number : array) {
//            if (number != 0) {
//                return number;
//            }
//        }
//        return 0;


        int result = 0;
        if (choiceInterval == 1) {
            return menNumber;
        }
        for (int i = 1; i <= menNumber; i++) {
            result = (result + choiceInterval) % i;
        }
        return result + 1;
    }

    /**
     * Наибольшая общая подстрока.
     * Средняя
     *
     * Дано две строки, например ОБСЕРВАТОРИЯ и КОНСЕРВАТОРЫ.
     * Найти их самую длинную общую подстроку -- в примере это СЕРВАТОР.
     * Если общих подстрок нет, вернуть пустую строку.
     * При сравнении подстрок, регистр символов *имеет* значение.
     * Если имеется несколько самых длинных общих подстрок одной длины,
     * вернуть ту из них, которая встречается раньше в строке first.
     */
    static public String longestCommonSubstring(String first, String second) { //O(first^2)
        String minString;
        String otherString;
//        if (first.length() < second.length()) {
//            minString = first;
//            otherString = second;
//        } else {
//            minString = second;
//            otherString = first;
//        }
        String maxString = "";
        StringBuilder currentString = new StringBuilder();
        for (int i = 0; i < first.length(); i++) {
            currentString.append(first.charAt(i));
            for (int j = 0; j < currentString.length(); j ++) {
                if (!second.contains(currentString)) {
                    currentString.deleteCharAt(0);
                } else {
                    break;
                }
            }
            if (currentString.length() > maxString.length()) {
                maxString = currentString.toString();
            }
        }

        return maxString;
    }

    /**
     * Число простых чисел в интервале
     * Простая
     *
     * Рассчитать количество простых чисел в интервале от 1 до limit (включительно).
     * Если limit <= 1, вернуть результат 0.
     *
     * Справка: простым считается число, которое делится нацело только на 1 и на себя.
     * Единица простым числом не считается.
     */
    static public int calcPrimesNumber(int limit) { // O(n^(3/2))
        if (limit <= 1) {
            return 0;
        }
        if (limit == 2) {
            return 1;
        }

        int count = 1;

        for (int i = 3; i < limit; i += 2) {
            if (isPrime(i)) {
                count++;
            }
        }
        return count;
    }

    private static boolean isPrime(int number) {
        for (int i = 3; i <= sqrt(number); i += 2) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Балда
     * Сложная
     *
     * В файле с именем inputName задана матрица из букв в следующем формате
     * (отдельные буквы в ряду разделены пробелами):
     *
     * И Т Ы Н
     * К Р А Н
     * А К В А
     *
     * В аргументе words содержится множество слов для поиска, например,
     * ТРАВА, КРАН, АКВА, НАРТЫ, РАК.
     *
     * Попытаться найти каждое из слов в матрице букв, используя правила игры БАЛДА,
     * и вернуть множество найденных слов. В данном случае:
     * ТРАВА, КРАН, АКВА, НАРТЫ
     *
     * И т Ы Н     И т ы Н
     * К р а Н     К р а н
     * А К в а     А К В А
     *
     * Все слова и буквы -- русские или английские, прописные.
     * В файле буквы разделены пробелами, строки -- переносами строк.
     * Остальные символы ни в файле, ни в словах не допускаются.
     */
    static public Set<String> baldaSearcher(String inputName, Set<String> words)  { // O(n^3)
        Set<String> foundedWords = new HashSet<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(inputName), StandardCharsets.UTF_8));
            String string;
            ArrayList<String> listOfInputStrings = new ArrayList<>();
            while((string = bufferedReader.readLine()) != null) {
                listOfInputStrings.add(string);
            }
            int strings = listOfInputStrings.get(0).split(" ").length;
            int columns = listOfInputStrings.size();
            String[][] letters = new String[strings][columns];
            for (int j = 0; j < columns; j++) {
                for (int i = 0; i < strings; i++) {
                    letters[i][j] = listOfInputStrings.get(j).split(" ")[i].trim();

                }
            }
            int count = 1;
            boolean check = false;
            for (String word : words) {
                for (int i = 0; i < strings; i++) {
                    for (int j = 0; j < columns; j++) {
                        if (letters[i][j].charAt(0) == word.charAt(0)) {
                            letters[i][j] = letters[i][j].toLowerCase();

                            searchingWords(letters, i, j, strings, columns, word, count, foundedWords);
                            letters[i][j] = letters[i][j].toUpperCase();
                            if (foundedWords.contains(word)) {
                                check = true;
                                break;
                            }
                        }
                    }
                    if (check) {
                        check = false;
                        break;
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return foundedWords;
    }



    private static void searchingWords(String[][] letters,
                                      int i, int j, int strings, int columns, String word, int count, Set<String> set) {

        if (count == word.length()) {
            writeToSet(word, set);
        } else {
            if (j != columns - 1 && letters[i][j + 1].charAt(0) == word.charAt(count)) {
                letters[i][j + 1] = letters[i][j + 1].toLowerCase();
                count++;
                searchingWords(letters, i, j + 1, strings, columns, word, count, set);
                count--;
                letters[i][j + 1] = letters[i][j + 1].toUpperCase();
            } if (j != 0 && letters[i][j - 1].charAt(0) == word.charAt(count)) {
                letters[i][j - 1] = letters[i][j - 1].toLowerCase();
                count++;
                searchingWords(letters, i, j - 1, strings, columns, word, count, set);
                count--;
                letters[i][j - 1] = letters[i][j - 1].toUpperCase();
            } if (i != 0 && letters[i - 1][j].charAt(0) == word.charAt(count)) {
                letters[i - 1][j] = letters[i - 1][j].toLowerCase();
                count++;
                searchingWords(letters, i - 1, j, strings, columns, word, count, set);
                count--;
                letters[i - 1][j] = letters[i - 1][j].toUpperCase();
            } if (i != strings - 1 && letters[i + 1][j].charAt(0) == word.charAt(count)) {
                letters[i + 1][j] = letters[i + 1][j].toLowerCase();
                count++;
                searchingWords(letters, i + 1, j, strings, columns, word, count, set);
                count--;
                letters[i + 1][j] = letters[i + 1][j].toUpperCase();
            }
        }
    }

    private static void writeToSet(String word, Set<String> set) {
        set.add(word);
    }
}
