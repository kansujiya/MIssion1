package MergeInterval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

class Interval {
    int start;
    int end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

public class MergeInterval {
    public static void main(String[] args) {
        //1. Merge interval
        List<Interval> input = new ArrayList<>();
        input.add(new Interval(1,4));
        input.add(new Interval(2,5));
        input.add(new Interval(7,9));
        for(Interval interval: mergeInterval(input)) {
            //System.out.println("[" + interval.start + "," + interval.end + "]");
        }

        //2.insert interval
        List<Interval> input1 = new ArrayList<>();
        input1.add(new Interval(1,3));
        input1.add(new Interval(5,7));
        input1.add(new Interval(8,12));
        for(Interval interval: insertInterval(input1, new Interval(4, 6))) {
            System.out.println("[" + interval.start + "," + interval.end + "]");
        }

        for(Interval interval: insertInterval(input1, new Interval(4, 10))) {
            System.out.println("[" + interval.start + "," + interval.end + "]");
        }

        //3. Intervals Intersection
        List<Interval> input2 = new ArrayList<>();
        input2.add(new Interval(1,3));
        input2.add(new Interval(5,6));
        input2.add(new Interval(7,9));

        List<Interval> input3 = new ArrayList<>();
        input3.add(new Interval(2,3));
        input3.add(new Interval(5,7));

        for(Interval interval: intervalIntersection(input2, input3)) {
            System.out.println("[" + interval.start + "," + interval.end + "]");
        }

    }

    public static List<Interval> mergeInterval(List<Interval> intervals) {
        //Sort the interval by start time

        Collections.sort(intervals, (a,b) -> Integer.compare(a.start, b.start));

        List<Interval> result = new ArrayList<Interval>();

        Iterator<Interval> iterator = intervals.iterator();

        Interval interval = iterator.next();
        int start = interval.start;
        int end = interval.end;

        while (iterator.hasNext()) {
            interval = iterator.next();

            if(interval.start <= end) { // compare two interval start & end, if second interval end is less than or equal first end than its overlap
                end = Math.max(end, interval.end);
            } else {
                result.add(new Interval(start, end));
                start = interval.start;
                end = interval.end;
            }
        }
        result.add(new Interval(start, end));
        return result;
    }

    //Insert Interval
    //Given a list of non-overlapping intervals sorted by their start time, insert a given interval at the correct position and merge all necessary intervals to produce a list that has only mutually exclusive intervals.
    //Input: Intervals=[[1,3], [5,7], [8,12]], New Interval=[4,6]
    //Output: [[1,3], [4,7], [8,12]]
    //Explanation: After insertion, since [4,6] overlaps with [5,7], we merged them into one [4,7].

    //Input: Intervals=[[1,3], [5,7], [8,12]], New Interval=[4,10]
    //Output: [[1,3], [4,12]]
    //Explanation: After insertion, since [4,10] overlaps with [5,7] & [8,12], we merged them into [4,12].

    public static List<Interval> insertInterval(List<Interval> intervals, Interval newInterval) {

        List<Interval> result= new ArrayList<>();

        int i = 0;

        //add all interval till new interval doesn't overlap
        while(i < intervals.size() && intervals.get(i).end < newInterval.start) {
            result.add(intervals.get(i));
            i++;
        }

        //check new interval overlap & update new interval start & end time // Take picture of overlap merge algo
        while(i < intervals.size() && intervals.get(i).start <= newInterval.end) {
            newInterval.start = Math.min(newInterval.start, intervals.get(i).start);
            newInterval.end = Math.max(newInterval.end, intervals.get(i).end);
            i++;
        }

        //add new updated interval to result
        result.add(newInterval);

        //add remaining interval to result
        while(i < intervals.size()) {
            result.add(intervals.get(i));
            i++;
        }


        return result;
    }

    //Intervals Intersection
    //Given two lists of intervals, find the intersection of these two lists. Each list consists of disjoint intervals sorted on their start time.
    //Input: arr1=[[1, 3], [5, 6], [7, 9]], arr2=[[2, 3], [5, 7]]
    //Output: [2, 3], [5, 6], [7, 7]
    //Explanation: The output list contains the common intervals between the two lists.

    //Input: arr1=[[1, 3], [5, 7], [9, 12]], arr2=[[5, 10]]
    //Output: [5, 7], [9, 10]
    //Explanation: The output list contains the common intervals between the two lists.

    //Think intersection means, what's the full overlap that will max start time & min end time between two intervals
    public static List<Interval> intervalIntersection(List<Interval> interval1, List<Interval> interval2) {
        List<Interval> result = new ArrayList<>();
        int i = 0;
        int j = 0;

        while(i < interval1.size() && j < interval2.size()) {
            //check if both interval intersect
            //check if one of the interval start time lies in another interval

            if((interval1.get(i).start >= interval2.get(j).start && interval1.get(i).start <= interval2.get(j).end) ||
            (interval2.get(j).start >= interval1.get(i).start && interval2.get(j).start <= interval1.get(i).end)) {
                result.add(new Interval(Math.max(interval1.get(i).start, interval2.get(j).start), Math.min(interval1.get(i).end, interval2.get(j).end)));
            }

            //move to next interval which ending first
            if(interval1.get(i).end < interval2.get(j).end) {
                i++;
            } else {
                j++;
            }
        }

        return result;
    }
}
