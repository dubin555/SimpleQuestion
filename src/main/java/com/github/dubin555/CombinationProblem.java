package com.github.dubin555;

import java.util.*;

public class CombinationProblem {
    // better to use List<List<String>> as a parameter type and return type
    // Time Complex: O(M^2*N^2), Space Complex: O(M*N), M: how many element in the input, N: how many element in one single M
    public List<List<String>> getSetCombination(List<List<String>> input) {
        if (input == null || input.size() == 0) return null;
        // use bfs to iterate
        Queue<List<String>> queue = new LinkedList<>();
        // init with an empty element
        queue.offer(new ArrayList<>());
        for (int i = 0; i < input.size(); i++) {
            List<String> list = input.get(i);
            // pop out all the previous element from queue
            int size = queue.size();
            for (int j = 0; j < size; j++) {
                List<String> prev = queue.poll();
                for (String s: list) {
                    // deep copy
                    List<String> cur = new ArrayList<>(prev);
                    cur.add(s);
                    queue.offer(cur);
                }
            }
        }
        List<List<String>> combination = new ArrayList<>();
        // queue --> list
        while (!queue.isEmpty()) {
            combination.add(queue.poll());
        }
        return combination;
    }

    private static void prettyPrintListOfList(List<List<String>> list) {
        if (list == null) return;
        for (List<String> l: list) {
            prettyPrintList(l);
        }
    }

    private static void prettyPrintList(List<String> list) {
        if (list == null || list.size() == 0) return;
        for (int i = 0; i < list.size() - 1; i++) {
            System.out.print(list.get(i) + " -> ");
        }
        System.out.print(list.get(list.size() - 1) + "\n");
    }

    public static void main(String[] args) {
        List<List<String>> input = new ArrayList<>();
        input.add(Arrays.asList("a", "b", "c"));
        input.add(Arrays.asList("1", "2", "3", "4", "5", "6"));
        input.add(Arrays.asList("A", "B", "C", "D"));
        CombinationProblem problem = new CombinationProblem();
        List<List<String>> combination = problem.getSetCombination(input);
        prettyPrintListOfList(combination);
        /**
         * return
         * a -> 1 -> A
         * a -> 1 -> B
         * a -> 1 -> C
         * a -> 1 -> D
         * a -> 2 -> A
         * a -> 2 -> B
         * a -> 2 -> C
         * a -> 2 -> D
         * a -> 3 -> A
         * a -> 3 -> B
         * a -> 3 -> C
         * a -> 3 -> D
         * a -> 4 -> A
         * a -> 4 -> B
         * a -> 4 -> C
         * a -> 4 -> D
         * a -> 5 -> A
         * a -> 5 -> B
         * a -> 5 -> C
         * a -> 5 -> D
         * a -> 6 -> A
         * a -> 6 -> B
         * a -> 6 -> C
         * a -> 6 -> D
         * b -> 1 -> A
         * b -> 1 -> B
         * b -> 1 -> C
         * b -> 1 -> D
         * b -> 2 -> A
         * b -> 2 -> B
         * b -> 2 -> C
         * b -> 2 -> D
         * b -> 3 -> A
         * b -> 3 -> B
         * b -> 3 -> C
         * b -> 3 -> D
         * b -> 4 -> A
         * b -> 4 -> B
         * b -> 4 -> C
         * b -> 4 -> D
         * b -> 5 -> A
         * b -> 5 -> B
         * b -> 5 -> C
         * b -> 5 -> D
         * b -> 6 -> A
         * b -> 6 -> B
         * b -> 6 -> C
         * b -> 6 -> D
         * c -> 1 -> A
         * c -> 1 -> B
         * c -> 1 -> C
         * c -> 1 -> D
         * c -> 2 -> A
         * c -> 2 -> B
         * c -> 2 -> C
         * c -> 2 -> D
         * c -> 3 -> A
         * c -> 3 -> B
         * c -> 3 -> C
         * c -> 3 -> D
         * c -> 4 -> A
         * c -> 4 -> B
         * c -> 4 -> C
         * c -> 4 -> D
         * c -> 5 -> A
         * c -> 5 -> B
         * c -> 5 -> C
         * c -> 5 -> D
         * c -> 6 -> A
         * c -> 6 -> B
         * c -> 6 -> C
         * c -> 6 -> D
         */
    }

}
