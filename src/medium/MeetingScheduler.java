package medium;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
#1229 Meeting Scheduler

Given the availability time slots arrays slots1 and slots2 of two people and a meeting duration duration,
return the earliest time slot that works for both of them and is of duration duration.
If there is no common time slot that satisfies the requirements, return an empty array.
The format of a time slot is an array of two elements [start, end] representing an inclusive time range from start to end.
It is guaranteed that no two availability slots of the same person intersect with each other.
That is, for any two time slots [start1, end1] and [start2, end2] of the same person, either start1 > end2 or start2 > end1.

Example 1:
Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 8
Output: [60,68]

Example 2:
Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 12
Output: []
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