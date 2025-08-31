package medium;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
#1229
Problem Description:
The problem is about finding a mutual meeting time slot between two people given their individual schedules
and a required meeting duration.
Each person's schedule is represented by a list of non-overlapping time slots where a time slot is an array [start, end]
showing availability from start to end.
The goal is to find the earliest starting time slot that is available in both schedules and lasts at least
for the given duration.
If there's no such common time slot, we return an empty array.

RU:
Условие задачи:
Необходимо найти общий временной слот для встречи двух людей, учитывая их расписания и требуемую длительность встречи.
Расписание каждого человека представлено списком непересекающихся временных интервалов.
Нужно найти самый ранний доступный слот, который удовлетворяет всем условиям.

Алгоритм решения:
- сортировка слотов обоих расписаний по времени начала;
- использовать два указателя для обхода расписаний: i - для первого расписания, j - для второго
- поиск интервалов: для каждой пары интервалов из разных расписаний найти пересечение
- сравнить длительность пересечений с продолжительностью планируемой встречи
- проверка условий: если найден подходящий слот, вернуть его, если нет - перейти к следующему пересечению
- если подходящие слоты не найдены - вернуть пустой массив

 */
public class MeetingScheduler {
    public List<Integer> findMeetingTime(int[][] slots1, int[][] slots2, int duration) {
        // sort the time slots of each user
        Arrays.sort(slots1, (a, b) -> a[0] - b[0]);
        Arrays.sort(slots2, (a, b) -> a[0] - b[0]);

        // create Index to navigate over 1st and 2nd person time slots
        int i = 0;
        int j = 0;

        // total number of slots for each user
        int slotsCount1 = slots1.length;
        int slotsCount2 = slots2.length;

        // iterate throw both sets of slots
        while (i < slotsCount1 && j < slotsCount2) {
            // calculate the overlap start time
            int overlapStart = Math.max(slots1[i][0], slots2[j][0]);
            // calculate the overlap end time
            int overlapEnd = Math.min(slots1[i][1], slots2[j][1]);

            // check is overlapping duration is at least the required duration
            if (overlapEnd - overlapStart >= duration) {
                return Arrays.asList(overlapStart, overlapStart + duration);
            }

            // move to the next slot in the list that has an earlier end time
            if (slots1[i][1] < slots2[j][1]) {
                i++;
            } else {
                j++;
            }
        }
        // return empty list if common slot not found
        return Collections.emptyList();
    }

    public static void main(String[] args) {
        MeetingScheduler meetingScheduler = new MeetingScheduler();
        int[][] slots1 = {{10, 50}, {140, 210}, {60, 120}};
        int[][] slots2 = {{0, 15}, {80, 100}, {25, 50}, {60, 70}};
        int duration = 8;

        List<Integer> result = meetingScheduler.findMeetingTime(slots1, slots2, duration);
        System.out.println(result);
    }
}
