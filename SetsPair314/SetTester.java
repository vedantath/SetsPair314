/*  Student information for assignment:
 *
 *  On OUR honor, Vedant Athale and Shruthik Alle,
 *  this programming assignment is OUR own work
 *  and WE have not provided this code to any other student.
 *
 *  Number of slip days used:
 *
 *  Student 1 (Student whose turnin account is being used)
 *  UTEID: vba252
 *  email address: vedant.athale@gmail.com
 *  Grader name:
 *  Section number:
 *
 *  Student 2
 *  UTEID: SA59576
 *  email address: shruthik.alle@utexas.edu
 */

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.*;

/*
 * CS 314 Students, put your results to the experiments and answers to questions
 * here: CS314 Students, why is it unwise to implement all three of the
 * intersection, union, and difference methods in the AbstractSet class:
 * It would be unwise to implement all three of the intersection, union, and difference methods
 * in the AbstractSet class because the implementation of these methods can vary depending on the
 *  specific type of set. Rather we can take advantage of the SortedSet's sorted nature in its
 * own implementation of these method to make them more efficient and optimized.
 */

public class SetTester {

    public static void main(String[] args) {

        //AbstractSet methods test cases
        AbstractSet<Integer> absSet1 = new UnsortedSet<>();
        AbstractSet<Integer> absSet2 = new UnsortedSet<>();

        absSet1.add(1);
        absSet1.add(2);
        absSet1.add(3);
        absSet1.add(4);

        // AbstractSet contains
        boolean expected = false;
        boolean actual = absSet1.contains(-5);
        if (actual == expected) {
            System.out.println("Passed test 1: AbstractSet contains");
        } else {
            System.out.println("Failed test 1: AbstractSet contains\tExpected: " + expected + "\tActual: " + actual);
        }

        expected = true;
        actual = absSet1.contains(3);
        if (actual == expected) {
            System.out.println("Passed test 2: AbstractSet contains");
        } else {
            System.out.println("Failed test 2: AbstractSet contains\tExpected: " + expected + "\tActual: " + actual);
        }

        // AbstractSet containsAll
        absSet2.add(4);
        absSet2.add(2);
        absSet2.add(3);
        expected = true;
        actual = absSet1.containsAll(absSet2);
        if (actual == expected) {
            System.out.println("Passed test 3: AbstractSet containsAll");
        } else {
            System.out.println("Failed test 3: AbstractSet containsAll\tExpected: " + expected + "\tActual: " + actual);
        }

        absSet2.add(10);
        expected = false;
        actual = absSet1.containsAll(absSet2);
        if (actual == expected) {
            System.out.println("Passed test 4: AbstractSet containsAll");
        } else {
            System.out.println("Failed test 4: AbstractSet containsAll\tExpected: " + expected + "\tActual: " + actual);
        }

        // AbstractSet equals
        expected = false;
        actual = absSet1.equals(absSet2);
        if (actual == expected) {
            System.out.println("Passed test 5: AbstractSet equals");
        } else {
            System.out.println("Failed test 5: AbstractSet equals\tExpected: " + expected + "\tActual: " + actual);
        }

        AbstractSet<Integer> absSet3 = new UnsortedSet<>();
        absSet3.add(1);
        absSet3.add(2);
        absSet3.add(3);
        absSet3.add(4);
        expected = true;
        actual = absSet1.equals(absSet3);
        if (actual == expected) {
            System.out.println("Passed test 6: AbstractSet equals");
        } else {
            System.out.println("Failed test 6: AbstractSet equals\tExpected: " + expected + "\tActual: " + actual);
        }

        // AbstractSet remove
        expected = true;
        int removeNum = 3;
        actual = absSet1.remove(removeNum);
        if (actual == expected) {
            System.out.println("Passed test 7: AbstractSet remove\t"+absSet3.toString()+"  remove" +
                    " "+removeNum+" --> "+absSet1.toString());
        } else {
            System.out.println("Failed test 7: AbstractSet remove\tExpected: " + expected + "\tActual: " + actual);
            System.out.println(absSet3.toString()+"  remove "+removeNum+" --> "+absSet1.toString());
        }

        expected = false;
        removeNum = 20;
        actual = absSet1.remove(removeNum);
        if (actual == expected) {
            System.out.println("Passed test 8: AbstractSet remove when element not present");
        } else {
            System.out.println("Failed test 8: AbstractSet remove\tExpected: " + expected + "\tActual: " + actual);
        }

        // AbstractSet clear
        absSet1.clear();
        if (absSet1.toString().equals("()")) {
            System.out.println("Passed test 9: AbstractSet clear");
        } else {
            System.out.println("Failed test 9: AbstractSet clear\tExpected: ()\tActual: " + absSet1.toString());
        }

        // AbstractSet size
        if (absSet1.size() == 0) {
            System.out.println("Passed test 10: AbstractSet size");
        } else {
            System.out.println("Failed test 10: AbstractSet size\tExpected: 0\tActual: " + absSet1.size());
        }

        if (absSet3.size() == 4) {
            System.out.println("Passed test 11: AbstractSet size");
        } else {
            System.out.println("Failed test 11: AbstractSet size\tExpected: 4\tActual: " + absSet3.size());
        }

        // AbstractSet toString
        if (absSet3.toString().equals("(1, 2, 3, 4)")) {
            System.out.println("Passed test 12: AbstractSet toString");
        } else {
            System.out.println("Failed test 12: AbstractSet toString\tExpected: (1, 2, 3, 4)\tActual: " + absSet3.toString());
        }

        //AbstractSet union
        System.out.println("absSet1: "+absSet1.toString()+"\nabsSet3: "+absSet3.toString());
        AbstractSet<Integer> expectedRes = (AbstractSet<Integer>) absSet1.union(absSet3);
        if (expectedRes.toString().equals("(1, 2, 3, 4)")) {
            System.out.println("Passed test 13: AbstractSet union");
        } else {
            System.out.println("Failed test 13: AbstractSet union\tExpected: ()\tActual: " + expectedRes.toString());
        }

        absSet1.add(1);
        absSet1.add(3);
        absSet1.add(5);
        absSet1.add(7);
        System.out.println("absSet1: "+absSet1.toString()+"\nabsSet3: "+absSet3.toString());
        expectedRes = (AbstractSet<Integer>) absSet1.union(absSet3);
        if (expectedRes.toString().equals("(1, 2, 3, 4, 5, 7)")) {
            System.out.println("Passed test 14: AbstractSet union");
        } else {
            System.out.println("Failed test 14: AbstractSet union\tExpected: (1, 3)\tActual: " + expectedRes.toString());
        }

        System.out.println("-----------------------------------------------------");

        //UnsortedSet methods test cases
        ISet<Integer> unsortedSet1 = new UnsortedSet<>();
        ISet<Integer> unsortedSet2 = new UnsortedSet<>();
        //UnsortedSet add
        unsortedSet1.add(1);
        if (unsortedSet1.contains(1)) {
            System.out.println("Passed test 15: UnsortedSet add");
        } else {
            System.out.println("Failed test 15: UnsortedSet add\tExpected: true\tActual: " + unsortedSet1.contains(1));
        }

        unsortedSet2.add(2);
        unsortedSet2.add(3);

        //UnsortedSet addAll
        unsortedSet1.addAll(unsortedSet2);
        ISet<Integer> unsortedExpected = new UnsortedSet<>();
        unsortedExpected.add(1);
        unsortedExpected.add(2);
        unsortedExpected.add(3);
        if (unsortedExpected.equals(unsortedSet1)) {
            System.out.println("Passed test 16: UnsortedSet addAll");
        } else {
            System.out.println("Failed test 16: UnsortedSet addAll\tExpected: (1, 2, 3)\tActual: " + unsortedSet1.toString());
        }

        //UnsortedSet size
        if (unsortedSet1.size() == 3) {
            System.out.println("Passed test 17: UnsortedSet size");
        } else {
            System.out.println("Failed test 17: UnsortedSet size\tExpected: 3\tActual: " + unsortedSet1.size());
        }

        //UnsortedSet intersection
        unsortedSet1.clear();
        unsortedSet2.clear();
        unsortedSet1.add(0);
        unsortedSet1.add(1);
        unsortedSet1.add(2);
        unsortedSet1.add(3);
        unsortedSet2.add(-3);
        unsortedSet2.add(0);
        unsortedSet2.add(3);
        unsortedSet2.add(6);
        expectedRes.clear();
        expectedRes.add(0);
        expectedRes.add(3);
        if (expectedRes.equals(unsortedSet1.intersection(unsortedSet2))) {
            System.out.println("Passed test 18: UnsortedSet intersection");
        } else {
            System.out.println("Failed test 18: UnsortedSet intersection\tExpected: (0, 3)\tActual: " + unsortedSet1.intersection(unsortedSet2).toString());
        }

        //UnsortedSet difference --> (0,1,2,3) - (-3,0,3,6) = (1,2)
        expectedRes.clear();
        expectedRes.add(1);
        expectedRes.add(2);
        if (expectedRes.equals(unsortedSet1.difference(unsortedSet2))) {
            System.out.println("Passed test 19: UnsortedSet difference");
        } else {
            System.out.println("Failed test 19: UnsortedSet difference\tExpected: (1, 2)\tActual: " + unsortedSet1.difference(unsortedSet2).toString());
        }

        //UnsortedSet iterator
        Iterator<Integer> it = unsortedSet1.iterator();
        ArrayList<Integer> list = new ArrayList<>();
        while(it.hasNext()) {
            list.add(it.next());
        }
        if (list.toString().equals("[0, 1, 2, 3]")) {
            System.out.println("Passed test 20: UnsortedSet iterator");
        } else {
            System.out.println("Failed test 20: UnsortedSet iterator\tExpected: [0, 1, 2, 3]\tActual: " + list.toString());
        }

        //SortedSet methods
        ISet<String> sortedSet1 = new SortedSet<>();
        ISet<String> sortedSet2 = new SortedSet<>();
        //sorted set copy constructor
        sortedSet1.add("A");
        sortedSet1.add("B");
        sortedSet1.add("C");
        ISet<String> sortedSet3 = new SortedSet<String>(sortedSet1);
        if (sortedSet1.equals(sortedSet3)) {
            System.out.println("Passed test 21: SortedSet copy constructor");
        } else {
            System.out.println("Failed test 21: SortedSet copy constructor\tExpected: (A, B, C)\tActual: " + sortedSet3.toString());
        }

       // SortedSet add
        sortedSet1.add("D");
        if (sortedSet1.toString().equals("(A, B, C, D)")) {
            System.out.println("Passed test 22: SortedSet add");
        } else {
            System.out.println("Failed test 22: SortedSet add\tExpected: true\tActual: " + sortedSet1.toString());
        }

        //SortedSet addAll
        sortedSet2.add("E");
        sortedSet2.add("F");
        sortedSet1.addAll(sortedSet2);
        if (sortedSet1.toString().equals("(A, B, C, D, E, F)")) {
            System.out.println("Passed test 23: SortedSet addAll");
        } else {
            System.out.println("Failed test 23: SortedSet addAll\tExpected: (A, B, C, D, E, F)\tActual: " + sortedSet1.toString());
        }






//        unsortedSet1.add(2);
//        unsortedSet1.add(3);
//
//        unsortedSet2.add(3);
//        unsortedSet2.add(4);
//        unsortedSet2.add(5);
//        ISet<Integer> unsortedRes = new UnsortedSet<>();
//        unsortedRes.add(1);
//        unsortedRes.add(2);
//        unsortedRes.add(3);
//        unsortedRes.add(4);
//        unsortedRes.add(5);
//        if (unsortedRes.equals(unsortedSet1.union(unsortedSet2))) {
//            System.out.println("Passed test 16: UnsortedSet union");
//        } else {
//            System.out.println("Failed test 16: UnsortedSet union\tExpected: (1, 2, 3, 4)" +
//                    "\tActual: " + unsortedSet1.union(unsortedSet2).toString());
//        }







        // CS314 Students. Uncomment this section when ready to
        // run your experiments
//         try {
//         UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//         }
//         catch(Exception e) {
//         System.out.println("Unable to change look and feel");
//         }
//         Scanner sc = new Scanner(System.in);
//         String response = "";
//         do {
//         largeTest();
//         System.out.print("Another file? Enter y to do another file: ");
//         response = sc.next();
//         } while( response != null && response.length() > 0
//         && response.substring(0,1).equalsIgnoreCase("y") );

    }

    // print out results of test
    private static <E> void showTestResults(boolean actualResult, boolean expectedResult,
            int testNumber, ISet<E> set1, ISet<E> set2, String testDescription) {
        if (actualResult == expectedResult) {
            System.out.println("Passed test " + testNumber);
        } else {
            System.out.print("Failed test ");
            System.out.println(testNumber + ": " + testDescription);
            System.out.println("Expected result: " + expectedResult);
            System.out.println("Actual result  : " + actualResult);
            System.out.println("Set 1: " + set1);
            if (set2 != null) {
                System.out.println("Set 2: " + set2);
            }
        }

    }

    /*
     * Method asks user for file and compares run times to add words from file
     * to various sets, including CS314 UnsortedSet and SortedSet and Java's
     * TreeSet and HashSet.
     */
    private static void largeTest() {
        System.out.println();
        System.out.println("Opening Window to select file. "
                + "You may have to minimize other windows.");
        String text = convertFileToString();
        Scanner keyboard = new Scanner(System.in);
        System.out.println();
        System.out.println("***** CS314 SortedSet: *****");
        processTextCS314Sets(new SortedSet<String>(), text, keyboard);
        System.out.println("****** CS314 UnsortedSet: *****");
        processTextCS314Sets(new UnsortedSet<String>(), text, keyboard);
        System.out.println("***** Java HashSet ******");
        processTextJavaSets(new HashSet<String>(), text, keyboard);
        System.out.println("***** Java TreeSet ******");
        processTextJavaSets(new TreeSet<String>(), text, keyboard);
    }

    /*
     * pre: set != null, text != null Method to add all words in text to the
     * given set. Words are delimited by white space. This version for CS314
     * sets.
     */
    private static void processTextCS314Sets(ISet<String> set, String text, Scanner keyboard) {
        Stopwatch s = new Stopwatch();
        Scanner sc = new Scanner(text);
        int totalWords = 0;
        s.start();
        while (sc.hasNext()) {
            totalWords++;
            set.add(sc.next());
        }
        s.stop();

        showResultsAndWords(set, s, totalWords, set.size(), keyboard);
    }

    /*
     * pre: set != null, text != null Method to add all words in text to the
     * given set. Words are delimited by white space. This version for Java
     * Sets.
     */
    private static void processTextJavaSets(Set<String> set, String text,
            Scanner keyboard) {
        Stopwatch s = new Stopwatch();
        Scanner sc = new Scanner(text);
        int totalWords = 0;
        s.start();
        while (sc.hasNext()) {
            totalWords++;
            set.add(sc.next());
        }
        s.stop();
        sc.close();

        showResultsAndWords(set, s, totalWords, set.size(), keyboard);
    }

    /*
     * Show results of add words to given set.
     */
    private static <E> void showResultsAndWords(Iterable<E> set, Stopwatch s, int totalWords,
            int setSize, Scanner keyboard) {

        System.out.println("Time to add the elements in the text to this set: " + s.toString());
        System.out.println("Total number of words in text including duplicates: " + totalWords);
        System.out.println("Number of distinct words in this text " + setSize);

        System.out.print("Enter y to see the contents of this set: ");
        String response = keyboard.next();

        if (response != null && response.length() > 0
                && response.substring(0, 1).equalsIgnoreCase("y")) {
            for (Object o : set) {
                System.out.println(o);
            }
        }
        System.out.println();
    }

    /*
     * Ask user to pick a file via a file choosing window and convert that file
     * to a String. Since we are evaluating the file with many sets convert to
     * string once instead of reading through file multiple times.
     */
    private static String convertFileToString() {
        // create a GUI window to pick the text to evaluate
        JFileChooser chooser = new JFileChooser(".");
        StringBuilder text = new StringBuilder();
        int retval = chooser.showOpenDialog(null);

        chooser.grabFocus();

        // read in the file
        if (retval == JFileChooser.APPROVE_OPTION) {
            File source = chooser.getSelectedFile();
            Scanner s = null;
            try {
                s = new Scanner(new FileReader(source));

                while (s.hasNextLine()) {
                    text.append(s.nextLine());
                    text.append(" ");
                }

                s.close();
            } catch (IOException e) {
                System.out.println("An error occured while trying to read from the file: " + e);
            } finally {
                if (s != null) {
                    s.close();
                }
            }
        }

        return text.toString();
    }
}