package CCE105LabActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);
    // Queue implementation
    static Queue<String> queue = new LinkedList<>();

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n===== PERMUTATION AND COMBINATION =====");
            System.out.println("[1] Add Elements to Queue");
            System.out.println("[2] Display Queue");
            System.out.println("[3] Generate Permutations");
            System.out.println("[4] Generate Combinations");
            System.out.println("[5] Exit");
            System.out.print("Enter choice: ");
            choice = input.nextInt();
            
            switch (choice) {
                case 1:
                    addElements();
                    break;
                case 2:
                    displayQueue();
                    break;
                case 3:
                    generatePermutations();
                    break;
                case 4:
                    generateCombinations();
                    break;
                case 5:
                    System.out.println("Program terminated.");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 5);
    }

    // TODO 1: Accept elements and add them to the Queue
    static void addElements() {
    	
    	// Ask how many elements to add
        // Use queue.add() to insert elements
        System.out.print("How many elements do you want to add? ");
        int count = input.nextInt();
        input.nextLine(); // Consume remaining newline

        for (int i = 0; i < count; i++) {
            System.out.print("Enter element " + (i + 1) + ": ");
            String element = input.nextLine();
            queue.add(element);
        }
        System.out.println(count + " element(s) added successfully.");
    }

    // TODO 2: Display all elements in the Queue
    static void displayQueue() {
    	
    	// Display the contents of the Queue
        if (queue.isEmpty()) {
            System.out.println("The queue is empty.");
            return;
        }
        System.out.println("Current Queue Elements: " + queue);
    }

    // TODO 3: Generate all possible permutations
    static void generatePermutations() {
    	
    	// Ask how many elements should be selected (r)
        // Generate all possible arrangements
        // Display the total number of permutations

        if (queue.isEmpty()) {
            System.out.println("Queue is empty! Please add elements first.");
            return;
        }

        System.out.print("Enter number of elements to select (r): ");
        int r = input.nextInt();

        if (r < 0 || r > queue.size()) {
            System.out.println("Invalid value of r! Must be between 0 and " + queue.size());
            return;
        }

        // Convert queue to array for indexed access
        String[] elements = queue.toArray(new String[0]);
        boolean[] used = new boolean[elements.length];
        int[] totalCount = new int[1]; // Using array to maintain reference inside recursion

        System.out.println("\n--- All Permutations ---");
        permuteHelper(elements, used, new ArrayList<>(), r, totalCount);
        System.out.println("Total number of permutations (P): " + totalCount[0]);
    }

    private static void permuteHelper(String[] elements, boolean[] used, List<String> current, int r, int[] totalCount) {
        if (current.size() == r) {
            System.out.println(current);
            totalCount[0]++;
            return;
        }

        for (int i = 0; i < elements.length; i++) {
            if (!used[i]) {
                used[i] = true;
                current.add(elements[i]);
                
                permuteHelper(elements, used, current, r, totalCount);
                
                current.remove(current.size() - 1); // Backtrack
                used[i] = false;
            }
        }
    }

    // TODO 4: Generate all possible combinations
    static void generateCombinations() {
    	
    	// Ask how many elements should be selected (r)
        // Generate all possible selections
        // Avoid duplicate combinations
        // Display the total number of combinations
    	
        if (queue.isEmpty()) {
            System.out.println("Queue is empty! Please add elements first.");
            return;
        }

        System.out.print("Enter number of elements to select (r): ");
        int r = input.nextInt();

        if (r < 0 || r > queue.size()) {
            System.out.println("Invalid value of r! Must be between 0 and " + queue.size());
            return;
        }

        // Convert queue to array and sort to handle/skip duplicates cleanly
        String[] elements = queue.toArray(new String[0]);
        Arrays.sort(elements);
        int[] totalCount = new int[1];

        System.out.println("\n--- All Combinations ---");
        combineHelper(elements, 0, new ArrayList<>(), r, totalCount);
        System.out.println("Total number of combinations (C): " + totalCount[0]);
    }

    private static void combineHelper(String[] elements, int start, List<String> current, int r, int[] totalCount) {
        if (current.size() == r) {
            System.out.println(current);
            totalCount[0]++;
            return;
        }

        for (int i = start; i < elements.length; i++) {
            // Avoid duplicate combinations if identical elements exist in the input queue
            if (i > start && elements[i].equals(elements[i - 1])) {
                continue;
            }
            
            current.add(elements[i]);
            combineHelper(elements, i + 1, current, r, totalCount);
            current.remove(current.size() - 1); // Backtrack
        }
    }
}
