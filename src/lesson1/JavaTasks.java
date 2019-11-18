package lesson1;

import kotlin.NotImplementedError;

import java.io.*;
import java.util.ArrayList;

@SuppressWarnings("unused")
public class JavaTasks {
    /**
     * Сортировка времён
     *
     * Простая
     * (Модифицированная задача с сайта acmp.ru)
     *
     * Во входном файле с именем inputName содержатся моменты времени в формате ЧЧ:ММ:СС AM/PM,
     * каждый на отдельной строке. См. статью википедии "12-часовой формат времени".
     *
     * Пример:
     *
     * 01:15:19 PM
     * 07:26:57 AM
     * 10:00:03 AM
     * 07:56:14 PM
     * 01:15:19 PM
     * 12:40:31 AM
     *
     * Отсортировать моменты времени по возрастанию и вывести их в выходной файл с именем outputName,
     * сохраняя формат ЧЧ:ММ:СС AM/PM. Одинаковые моменты времени выводить друг за другом. Пример:
     *
     * 12:40:31 AM
     * 07:26:57 AM
     * 10:00:03 AM
     * 01:15:19 PM
     * 01:15:19 PM
     * 07:56:14 PM
     *
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public void sortTimes(String inputName, String outputName) {
        throw new NotImplementedError();
    }

    /**
     * Сортировка адресов
     *
     * Средняя
     *
     * Во входном файле с именем inputName содержатся фамилии и имена жителей города с указанием улицы и номера дома,
     * где они прописаны. Пример:
     *
     * Петров Иван - Железнодорожная 3
     * Сидоров Петр - Садовая 5
     * Иванов Алексей - Железнодорожная 7
     * Сидорова Мария - Садовая 5
     * Иванов Михаил - Железнодорожная 7
     *
     * Людей в городе может быть до миллиона.
     *
     * Вывести записи в выходной файл outputName,
     * упорядоченными по названию улицы (по алфавиту) и номеру дома (по возрастанию).
     * Людей, живущих в одном доме, выводить через запятую по алфавиту (вначале по фамилии, потом по имени). Пример:
     *
     * Железнодорожная 3 - Петров Иван
     * Железнодорожная 7 - Иванов Алексей, Иванов Михаил
     * Садовая 5 - Сидоров Петр, Сидорова Мария
     *
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public void sortAddresses(String inputName, String outputName) {
        throw new NotImplementedError();
    }

    /**
     * Сортировка температур
     *
     * Средняя
     * (Модифицированная задача с сайта acmp.ru)
     *
     * Во входном файле заданы температуры различных участков абстрактной планеты с точностью до десятых градуса.
     * Температуры могут изменяться в диапазоне от -273.0 до +500.0.
     * Например:
     *
     * 24.7
     * -12.6
     * 121.3
     * -98.4
     * 99.5
     * -12.6
     * 11.0
     *
     * Количество строк в файле может достигать ста миллионов.
     * Вывести строки в выходной файл, отсортировав их по возрастанию температуры.
     * Повторяющиеся строки сохранить. Например:
     *
     * -98.4
     * -12.6
     * -12.6
     * 11.0
     * 24.7
     * 99.5
     * 121.3
     */
    static public void sortTemperatures(String inputName, String outputName) { // сортировка подсчетом O(n)
        int[] numCounts = new int[7731];
        int[] outputArray = new int[0];
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputName))) {
            ArrayList<Integer> inputTemperatures = new ArrayList<>();
            String string;
            while ((string = bufferedReader.readLine()) != null) {
                if (Double.parseDouble(string) < -273.0
                        || Double.parseDouble(string) > 500.0) {
                    throw new IllegalArgumentException();
                }
                inputTemperatures.add((int)(Double.parseDouble(string) * 10));
            }
            int[] inputArray = new int[inputTemperatures.size()];
            for (int i = 0; i < inputTemperatures.size(); i++) {
                inputArray[i] = inputTemperatures.get(i);
            }
            for (int i = 0; i < inputArray.length; i++) {
                numCounts[inputArray[i] + 2730]++;
//                you should finish this method with int instead of double
//                you ain't got time!!!!!!!!!!
            }
//            for (int j = 1; j < 7731; j++) {
//                count[j] = count[j] + count[j - 1];
//            }
            outputArray = new int[inputArray.length];
            int currentSortedIndex = 0;
//            for (int i = inputTemperatures.size() - 1; i > 0; i--) {
//                count[inputTemperatures.get(i) + 2730] = count[inputTemperatures.get(i) + 2730] - 1;
//                outputArray[count[inputTemperatures.get(i) + 2730]] = inputTemperatures.get(i) + 2730;
//            }
            for (int n = 0; n < numCounts.length; n++) {
                int count = numCounts[n];
                for (int k = 0; k < count; k++) {
                    outputArray[currentSortedIndex] = n - 2730;
                    currentSortedIndex++;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileWriter fileWriter = new FileWriter(outputName, false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (int i = 0; i < outputArray.length; i++) {
                bufferedWriter.write((double)outputArray[i] / 10.0 + "\n");
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Сортировка последовательности
     *
     * Средняя
     * (Задача взята с сайта acmp.ru)
     *
     * В файле задана последовательность из n целых положительных чисел, каждое в своей строке, например:
     *
     * 1
     * 2
     * 3
     * 2
     * 3
     * 1
     * 2
     *
     * Необходимо найти число, которое встречается в этой последовательности наибольшее количество раз,
     * а если таких чисел несколько, то найти минимальное из них,
     * и после этого переместить все такие числа в конец заданной последовательности.
     * Порядок расположения остальных чисел должен остаться без изменения.
     *
     * 1
     * 3
     * 3
     * 1
     * 2
     * 2
     * 2
     */
    static public void sortSequence(String inputName, String outputName) { // решил с помощью задумки сортировки подсчетом со сложностью O(n)
        int maxNumber = 0;
        int maxNumberName = 0;
        int maxNumberCounter = 0;
        int[] outputArray = new int[0];
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(inputName));
            ArrayList<Integer> inputNumbers = new ArrayList<>();
            String string;
            while ((string = bufferedReader.readLine()) != null) {
                inputNumbers.add(Integer.parseInt(string));
                if (Integer.parseInt(string) > maxNumber) {
                    maxNumber = Integer.parseInt(string);
                }
            }
            int[] numCounts = new int[maxNumber + 1];
            for (int i = 0; i < inputNumbers.size(); i++) {
                numCounts[inputNumbers.get(i)]++;
            }
            for (int j = 0; j < maxNumber; j++) {
                if (numCounts[j + 1] > maxNumberCounter) {
                    maxNumberCounter = numCounts[j + 1];
                    maxNumberName = j + 1;
                }
            }
            outputArray = new int[inputNumbers.size()];
            int index = 0;
            for (int k = 0; k < inputNumbers.size(); k++) {
                if (inputNumbers.get(k) != maxNumberName) {
                    outputArray[index] = inputNumbers.get(k);
                    index++;
                }
            }
            for (int n = inputNumbers.size() - maxNumberCounter; n < inputNumbers.size(); n++) {
                outputArray[n] = maxNumberName;
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileWriter fileWriter = new FileWriter(outputName, false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (int i = 0; i < outputArray.length; i++) {
                bufferedWriter.write(outputArray[i] + "\n");
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Соединить два отсортированных массива в один
     *
     * Простая
     *
     * Задан отсортированный массив first и второй массив second,
     * первые first.size ячеек которого содержат null, а остальные ячейки также отсортированы.
     * Соединить оба массива в массиве second так, чтобы он оказался отсортирован. Пример:
     *
     * first = [4 9 15 20 28]
     * second = [null null null null null 1 3 9 13 18 23]
     *
     * Результат: second = [1 3 4 9 9 13 15 20 23 28]
     */
    static <T extends Comparable<T>> void mergeArrays(T[] first, T[] second) {
        throw new NotImplementedError();
    }
}
