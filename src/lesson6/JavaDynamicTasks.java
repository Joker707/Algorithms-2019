package lesson6;

import kotlin.NotImplementedError;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class JavaDynamicTasks {
    /**
     * Наибольшая общая подпоследовательность.
     * Средняя
     *
     * Дано две строки, например "nematode knowledge" и "empty bottle".
     * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
     * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
     * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
     * Если общей подпоследовательности нет, вернуть пустую строку.
     * Если есть несколько самых длинных общих подпоследовательностей, вернуть любую из них.
     * При сравнении подстрок, регистр символов *имеет* значение.
     *
     * Сложность O(first.length * second.length)
     * Затраты по памяти O(first.length * second.length)
     */
    public static String longestCommonSubSequence(String first, String second) {
        if (first.length() == 0 || second.length() == 0)
            return "";

        int[][] table = new int[first.length() + 1][second.length() + 1];

        for (int i = 1; i <= first.length(); i++) {
            for (int j = 1; j <= second.length(); j++) {

                table[i][j] = first.charAt(i - 1) == second.charAt(j - 1)
                        ? table[i - 1][j - 1] + 1 : Math.max(table[i - 1][j], table[i][j - 1]);
            }
        }
        StringBuilder result = new StringBuilder();
        int fl = first.length();
        int sl = second.length();

        while (fl != 0 && sl != 0) {
            if (first.charAt(fl - 1) == second.charAt(sl - 1)) {
                result.append(first.charAt(fl - 1));
                fl--;
                sl--;
            }
            else
            if (table[fl][sl - 1] > table[fl - 1][sl]) sl--;
            else
            if (table[fl][sl - 1] < table[fl - 1][sl]) fl--;
            else fl--;
        }

        return result.reverse().toString();
    }

    /**
     * Наибольшая возрастающая подпоследовательность
     * Сложная
     *
     * Дан список целых чисел, например, [2 8 5 9 12 6].
     * Найти в нём самую длинную возрастающую подпоследовательность.
     * Элементы подпоследовательности не обязаны идти подряд,
     * но должны быть расположены в исходном списке в том же порядке.
     * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
     * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
     * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
     *
     * Сложность O(list.size * (list.size + 1) / 2)
     * Затраты по памяти O(list.size)
     *
     */
    public static List<Integer> longestIncreasingSubSequence(List<Integer> list) {

        if (list.size() == 0 || list.size() == 1) {
            return list;
        }

        int max = 0;

        int[] size = new int[list.size()];
        int[] index = new int[list.size()];

        for (int i = 0; i < list.size(); ++i) {
            size[i] = 1;
            index[i] = -1;
            for (int j = 0; j < i; ++j) {
                if (list.get(j) < list.get(i)) {
                    if (1 + size[j] > size[i]) {
                        size[i] = 1 + size[j];
                        index[i] = j;
                        if (size[max] < size[i]) {
                            max = i;
                        }
                    }
                }
            }
        }
        int count = size[max];
        int[] result = new int[count];
        int i = max;
        while (i != -1) {
            result[--count] = list.get(i);
            i = index[i];
        }
        List answer = new ArrayList<Integer>();
        for (int resultPart : result) {
            answer.add(resultPart);
        }
        return answer;

    }

    /**
     * Самый короткий маршрут на прямоугольном поле.
     * Средняя
     *
     * В файле с именем inputName задано прямоугольное поле:
     *
     * 0 2 3 2 4 1
     * 1 5 3 4 6 2
     * 2 6 2 5 1 3
     * 1 4 3 2 6 2
     * 4 2 3 1 5 0
     *
     * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
     * В каждой клетке записано некоторое натуральное число или нуль.
     * Необходимо попасть из верхней левой клетки в правую нижнюю.
     * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
     * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
     *
     * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
     */
    public static int shortestPathOnField(String inputName) {
        throw new NotImplementedError();
    }

    // Задачу "Максимальное независимое множество вершин в графе без циклов"
    // смотрите в уроке 5
}
