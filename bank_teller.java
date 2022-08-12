/**
 *
 * @author Kevin
 */
import java.util.*;

public class bank_teller {

    int safeSequence[] = new int[5];

    public static void main(String[] args) {
        int col = 3, row = 5, k, j, i;// Initializing Parameters
        boolean denied = false;
        String[] strArray;
        int resour[] = new int[col];
        boolean done = false;
        int need[][] = new int[row][col];
        boolean allocproc[] = new boolean[row];
        List<Integer> order = new ArrayList<>();
        int max[][] = {{7, 5, 3},   // Created matrixes
        {3, 2, 2},
        {9, 0, 2},
        {2, 2, 2},
        {4, 3, 3}};

        int allocation[][] = {{0, 1, 0},
        {2, 0, 0},
        {3, 0, 2},
        {2, 1, 1},
        {0, 0, 2}};

        int available[] = {3, 3, 2};
        int work[] = Arrays.copyOf(available, col);

        for (i = 0; i < row; i++) {
            for (j = 0; j < col; j++) {// Max - Allocation creates the Need matrix
                need[i][j] = max[i][j] - allocation[i][j];
            }
        }
        System.out.println("Max Matrix:");
        for (i = 0; i < row; i++) {
            for (j = 0; j < col; j++) {
                System.out.print(max[i][j] + " ");

            }
            System.out.println();
        }
        System.out.println("Allocation Matrix:");
        for (i = 0; i < row; i++) {
            for (j = 0; j < col; j++) {
                System.out.print(allocation[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("Available Array:");
        for (i = 0; i < available.length; i++) {
            System.out.print(available[i] + " ");
        }
        System.out.println();

        System.out.println("Need Matrix:");
        for (i = 0; i < row; i++) {
            for (j = 0; j < col; j++) {
                System.out.print(need[i][j] + " ");
            }
            System.out.println();
        }
        while (done != true) {// The safety alogrithm is applied
            for (i = 0; i < row; i++) {
                int check = 0;
                for (j = 0; j < col; j++) {
                    if (need[i][j] <= work[j]) {
                        check += 1;
                        if (check == col && allocproc[i] == false) {
                            for (k = 0; k < col; k++) {
                                work[k] += allocation[i][k];
                            }
                            allocproc[i] = true;
                            order.add(i);

                        }

                    }
                }

            }
            if (order.size() == row) {
                done = true;
            }
        }
        System.out.print("The system is in a safe state since the sequence ");
        System.out.print(order + " satisfies safety criteria");

        int procid;
        String resource;// Allows user to input a test
        try (Scanner in = new Scanner(System.in)) {
            System.out.println("\nEnter a process id 0-4:");
            procid = in.nextInt();
            System.out.println("Enter a request like: 1 0 2");
            in.nextLine();
            resource = in.nextLine();
        }
        strArray = resource.split(" ");
        for (i = 0; i < strArray.length; i++) {
            resour[i] = Integer.parseInt(strArray[i]);
        }
        for (i = 0; i < row; i++) {
            allocproc[i] = false;
        }
        done = false;
        order.clear();//Checking if the inputs are valid
        for (j = 0; j < col; j++) {
            available[j] -= resour[j];
            if (available[j] < 0) {
                denied = true;
            }
            allocation[procid][j] += resour[j];
        }
        work = Arrays.copyOf(available, col);// Work array stores a copy
        for (i = 0; i < row; i++) {
            for (j = 0; j < col; j++) {
                need[i][j] = max[i][j] - allocation[i][j];
            }
        }
        if (denied == true) {
            System.out.println("Not enough available Resources");
            System.out.println("Available Array:");
            for (i = 0; i < available.length; i++) {
                System.out.print(available[i] + " ");
            }
            System.out.println();

            System.out.println("Need Matrix:");
            for (i = 0; i < row; i++) {
                for (j = 0; j < col; j++) {
                    System.out.print(need[i][j] + " ");
                }
                System.out.println();
            }
            System.exit(0);
        }
        int change = 0;// The safety alogrithm is applied
        int update = 0;
        while (done != true) {
            update += 1;
            for (i = 0; i < row; i++) {
                int check = 0;
                for (j = 0; j < col; j++) {
                    if (need[i][j] <= work[j]) {
                        check += 1;
                        if (check == col && allocproc[i] == false) {
                            for (k = 0; k < col; k++) {
                                work[k] += allocation[i][k];
                            }
                            allocproc[i] = true;
                            order.add(i);
                            change += 1;

                        }

                    }
                }

            }
            if (order.size() == row) {
                done = true;
            }
            if (update > change) {
                System.out.println("The request can not be granted because it does not satisfy the safety algorithm, since it leads to deadlock.");
                System.out.println("Available Array:");
                for (i = 0; i < available.length; i++) {
                    System.out.print(available[i] + " ");
                }
                System.out.println();

                System.out.println("Need Matrix:");
                for (i = 0; i < row; i++) {
                    for (j = 0; j < col; j++) {
                        System.out.print(need[i][j] + " ");
                    }
                    System.out.println();
                }
                System.exit(0);
            }

        }
        System.out.println("Max Matrix:");
        for (i = 0; i < row; i++) {
            for (j = 0; j < col; j++) {
                System.out.print(max[i][j] + " ");

            }
            System.out.println();
        }
        System.out.println("Allocation Matrix:");
        for (i = 0; i < row; i++) {
            for (j = 0; j < col; j++) {
                System.out.print(allocation[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("Available Array:");
        for (i = 0; i < available.length; i++) {
            System.out.print(available[i] + " ");
        }
        System.out.println();

        System.out.println("Need Matrix:");
        for (i = 0; i < row; i++) {
            for (j = 0; j < col; j++) {
                System.out.print(need[i][j] + " ");
            }
            System.out.println();
        }

        System.out.print("The system is in a safe state since the sequence ");
        System.out.print(order + " satisfies safety criteria");

    }
}
