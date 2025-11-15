import java.util.*;

public class OptimalPageReplacement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of pages: ");
        int n = sc.nextInt();

        int[] pages = new int[n];
        System.out.println("Enter page reference string: ");
        for (int i = 0; i < n; i++) {
            pages[i] = sc.nextInt();
        }

        System.out.print("Enter number of frames: ");
        int f = sc.nextInt();

        int[] frames = new int[f];
        Arrays.fill(frames, -1);

        int pageFaults = 0;

        System.out.println("\nPage\tFrames\t\tFault/Hit");

        for (int i = 0; i < n; i++) {
            int page = pages[i];
            boolean hit = false;

            // Check if page is already in frame
            for (int j = 0; j < f; j++) {
                if (frames[j] == page) {
                    hit = true;
                    break;
                }
            }

            // If not found (Page Fault)
            if (!hit) {
                int replaceIndex = -1;

                // Check for empty frame
                for (int j = 0; j < f; j++) {
                    if (frames[j] == -1) {
                        replaceIndex = j;
                        break;
                    }
                }

                // If no empty frame, find the page to replace optimally
                if (repIndex == -1) {
                    int farthest = i + 1;
                    int indexToReplace = -1;

                    for (int j = 0; j < f; j++) {
                        int k;
                        for (k = i + 1; k < n; k++) {
                            if (frames[j] == pages[k]) {
                                if (k > farthest) {
                                    farthest = k;
                                    indexToReplace = j;
                                }
                                break;
                            }
                        }
                        // If page not found in future, replace it immediately
                        if (k == n) {
                            indexToReplace = j;
                            break;
                        }
                    }

                    // If all pages will be used again, replace farthest one
                    if (indexToReplace == -1) {
                        indexToReplace = 0;
                    }

                    replaceIndex = indexToReplace;
                }

                frames[replaceIndex] = page;
                pageFaults++;
            }

            // Print current frame status
            System.out.print(page + "\t");
            for (int j = 0; j < f; j++) {
                if (frames[j] != -1)
                    System.out.print(frames[j] + " ");
                else
                    System.out.print("- ");
            }

            if (!hit)
                System.out.print("\t\tFault");
            else
                System.out.print("\t\tHit");

            System.out.println();
        }

        System.out.println("\nTotal Page Faults = " + pageFaults);
        sc.close();
    }
}
