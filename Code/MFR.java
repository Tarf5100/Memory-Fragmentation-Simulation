package com.mycompany.os2;


import java.util.*;

class MemoryBlock {
    int blockSize;
    int startAddress;
    int endAddress;
    boolean isAllocated;
    String processID;
    int internalFragmentation;

    public MemoryBlock(int blockSize, int startAddress) {
        this.blockSize = blockSize;
        this.startAddress = startAddress;
        this.endAddress = startAddress + blockSize - 1;
        this.isAllocated = false;
        this.processID = "Null";
        this.internalFragmentation = 0;
    }

    public void printInfo(int index) {
        System.out.printf("Block%-2d\t%-8d\t%5d-%-7d\t%-10s\t%-10s\t%d\n",
                index, blockSize, startAddress, endAddress,
                isAllocated ? "allocated" : "free",
                processID, internalFragmentation);
    }
}

public class MFR {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Memory Initialization
        System.out.print("Enter the total number of blocks: ");
        int M = scanner.nextInt();

        int[] sizes = new int[M];
        System.out.println("Enter the size of each block in KB:");
        for (int i = 0; i < M; i++) {
            System.out.print("Block " + i + ": ");
            sizes[i] = scanner.nextInt();
        }

        MemoryBlock[] memory = new MemoryBlock[M];
        int currentAddress = 0;

        for (int i = 0; i < M; i++) {
            memory[i] = new MemoryBlock(sizes[i], currentAddress);
            currentAddress += sizes[i];
        }

        System.out.print("Enter allocation strategy (1 for First-Fit, 2 for Best-Fit, 3 for Worst-Fit): ");
        int strategy = scanner.nextInt();

        System.out.println("\nMemory blocks are created…");

        while (true) {
            // Menu
            System.out.println("\n============================================");
            System.out.println("1) Allocate memory blocks");
            System.out.println("2) Deallocate memory blocks");
            System.out.println("3) Print memory status report");
            System.out.println("4) Exit");
            System.out.println("============================================");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                System.out.print("Enter the process ID and size of process (e.g. P1 150): ");
                String processId = scanner.next();
                int processSize = scanner.nextInt();
                boolean allocated = false;

                // Task 2: Allocation Strategy
                switch (strategy) {
                    case 1:
                        allocated = firstFit(memory, processId, processSize);
                        break;
                    case 2:
                        allocated = bestFit(memory, processId, processSize);
                        break;
                    case 3:
                        allocated = worstFit(memory, processId, processSize);
                        break;
                    default:
                        System.out.println("Invalid allocation strategy.");
                        break;
                }

                if (!allocated) {
                    System.out.println("Allocation failed: Not enough space.");
                }

            } else if (choice == 2) {
                System.out.print("Enter the process ID to deallocate: ");
                String processId = scanner.next();
                boolean found = false;

                for (MemoryBlock block : memory) {
                    if (block.isAllocated && block.processID.equals(processId)) {
                        block.isAllocated = false;
                        block.processID = "Null";
                        block.internalFragmentation = 0;
                        found = true;
                        System.out.println("Process " + processId + " deallocated.");
                        break;
                    }
                }

                if (!found) {
                    System.out.println("Process ID not found.");
                }

            } else if (choice == 3) {
              
                System.out.println("\n===========================================================================");
                System.out.println("Block#\tSize     \tStart-End \tStatus     \tProcessID  \tInternalFragmentation");
                System.out.println("===========================================================================");
                for (int i = 0; i < M; i++) {
                    memory[i].printInfo(i);
                }

                int totalInternalFragmentation = 0;
                for (MemoryBlock block : memory) {
                    if (block.isAllocated) {
                        totalInternalFragmentation += block.internalFragmentation;
                    }
                }
                System.out.println("===========================================================================");
                System.out.println("Total Internal Fragmentation: " + totalInternalFragmentation + " KB");

            } else if (choice == 4) {
                System.out.println("Exiting simulation...");
                break;

            } else {
                System.out.println("Invalid choice. Please select between 1–4.");
            }
        }

        scanner.close();
    }

    // ---------- Allocation Strategies ------------------

    public static boolean firstFit(MemoryBlock[] memory, String processId, int processSize) {
        for (MemoryBlock block : memory) {
            if (!block.isAllocated && block.blockSize >= processSize) {
                allocate(block, processId, processSize);
                return true;
            }
        }
        return false;
    }

    public static boolean bestFit(MemoryBlock[] memory, String processId, int processSize) {
        MemoryBlock bestBlock = null;
        for (MemoryBlock block : memory) {
            if (!block.isAllocated && block.blockSize >= processSize) {
                if (bestBlock == null || block.blockSize < bestBlock.blockSize) {
                    bestBlock = block;
                }
            }
        }
        if (bestBlock != null) {
            allocate(bestBlock, processId, processSize);
            return true;
        }
        return false;
    }

    public static boolean worstFit(MemoryBlock[] memory, String processId, int processSize) {
        MemoryBlock worstBlock = null;
        for (MemoryBlock block : memory) {
            if (!block.isAllocated && block.blockSize >= processSize) {
                if (worstBlock == null || block.blockSize > worstBlock.blockSize) {
                    worstBlock = block;
                }
            }
        }
        if (worstBlock != null) {
            allocate(worstBlock, processId, processSize);
            return true;
        }
        return false;
    }

    public static void allocate(MemoryBlock block, String processId, int processSize) {
        block.isAllocated = true;
        block.processID = processId;
        block.internalFragmentation = block.blockSize - processSize;
        System.out.println(processId + " allocated at address: " + block.startAddress +
                ", internal fragmentation: " + block.internalFragmentation + " KB");
    }
}
