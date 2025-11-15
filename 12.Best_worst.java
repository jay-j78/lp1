import java.util.Scanner;

public class MemoryAllocation {

    // ---------- BEST FIT ----------
    static void bestFit(int blockSize[], int m, int processSize[], int n) {
        int allocation[] = new int[n]; // Stores block index for each process
        for (int i = 0; i < n; i++)
            allocation[i] = -1; // Initialize all as unallocated

        for (int i = 0; i < n; i++) {
            int bestIdx = -1;
            for (int j = 0; j < m; j++) {
                if (blockSize[j] >= processSize[i]) {
                    if (bestIdx == -1 || blockSize[j] < blockSize[bestIdx])
                        bestIdx = j;
                }
            }

            if (bestIdx != -1) {
                allocation[i] = bestIdx;
                blockSize[bestIdx] -= processSize[i];
            }
        }

        System.out.println("\nBest Fit Allocation:");
        System.out.println("Process No.\tProcess Size\tBlock No.");
        for (int i = 0; i < n; i++) {
            System.out.print((i + 1) + "\t\t" + processSize[i] + "\t\t");
            if (allocation[i] != -1)
                System.out.println(allocation[i] + 1);
            else
                System.out.println("Not Allocated");
        }
    }

    // ---------- WORST FIT ----------
    static void worstFit(int blockSize[], int m, int processSize[], int n) {
        int allocation[] = new int[n];
        for (int i = 0; i < n; i++)
            allocation[i] = -1;

        for (int i = 0; i < n; i++) {
            int worstIdx = -1;
            for (int j = 0; j < m; j++) {
                if (blockSize[j] >= processSize[i]) {
                    if (worstIdx == -1 || blockSize[j] > blockSize[worstIdx])
                        worstIdx = j;
                }
            }

            if (worstIdx != -1) {
                allocation[i] = worstIdx;
                blockSize[worstIdx] -= processSize[i];
            }
        }

        System.out.println("\nWorst Fit Allocation:");
        System.out.println("Process No.\tProcess Size\tBlock No.");
        for (int i = 0; i < n; i++) {
            System.out.print((i + 1) + "\t\t" + processSize[i] + "\t\t");
            if (allocation[i] != -1)
                System.out.println(allocation[i] + 1);
            else
                System.out.println("Not Allocated");
        }
    }

    // ---------- MAIN ----------
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of memory blocks: ");
        int m = sc.nextInt();
        int[] blockSize = new int[m];
        System.out.println("Enter sizes of memory blocks:");
        for (int i = 0; i < m; i++)
            blockSize[i] = sc.nextInt();

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();
        int[] processSize = new int[n];
        System.out.println("Enter sizes of processes:");
        for (int i = 0; i < n; i++)
            processSize[i] = sc.nextInt();

        // Make copies since arrays are modified during allocation
        int[] blockCopy1 = blockSize.clone();
        int[] blockCopy2 = blockSize.clone();

        bestFit(blockCopy1, m, processSize, n);
        worstFit(blockCopy2, m, processSize, n);
    }
}
