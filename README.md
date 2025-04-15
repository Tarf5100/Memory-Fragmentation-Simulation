<body>
    <h1>Memory Fragmentation Simulation</h1>
    <h2>Project Overview</h2>
    <p>
        This project simulates memory management using a fixed-size partition scheme. It performs memory allocation, deallocation, 
        and status reporting for memory blocks. The simulation supports three memory allocation strategies: <strong>First-Fit</strong>, 
        <strong>Best-Fit</strong>, and <strong>Worst-Fit</strong>. The program manages the fragmentation of memory and calculates 
        internal fragmentation.
    </p>
    <h2>Requirements</h2>
    <ul>
        <li><strong>Programming Language:</strong> Java</li>
        <li><strong>Data Structures:</strong> Linked lists or arrays to represent memory blocks.</li>
        <li><strong>Algorithms:</strong> Implement algorithms for:
            <ul>
                <li>First-Fit memory allocation</li>
                <li>Best-Fit memory allocation</li>
                <li>Worst-Fit memory allocation</li>
            </ul>
        </li>
        <li><strong>Input/Output:</strong> The program interacts with the user through a text-based interface to perform tasks like allocating and deallocating memory and generating reports.</li>
    </ul>
    <h2>Key Features</h2>
    <ul>
        <li><strong>Memory Initialization:</strong> The user inputs the number of memory blocks and their sizes in KB. The program initializes each memory block with attributes like block size, starting and ending addresses, status (allocated or free), and the process ID currently occupying the block (or "Null" when free).</li>
        <li><strong>Allocation Strategies:</strong>
            <ul>
                <li><strong>First-Fit:</strong> Allocates the first available block that fits the process size.</li>
                <li><strong>Best-Fit:</strong> Allocates the smallest available block that fits the process size.</li>
                <li><strong>Worst-Fit:</strong> Allocates the largest available block.</li>
            </ul>
        </li>
        <li><strong>Fragmentation Measurement:</strong> The program calculates internal fragmentation for each allocation and deallocation.</li>
    </ul>
    <h2>Simulation Menu</h2>
    <p>The program prompts the user for input and provides a menu with the following options:</p>
    <ol>
        <li><strong>Allocate memory blocks:</strong> User enters the process ID and size of the process. The program allocates memory using the selected allocation strategy and calculates internal fragmentation.</li>
        <li><strong>Deallocate memory blocks:</strong> User enters the process ID to release memory.</li>
        <li><strong>Print memory status report:</strong> Displays a detailed report of the memory blocks, including block number, size, address range, allocation status, process ID, and internal fragmentation.</li>
        <li><strong>Exit:</strong> Terminates the simulation.</li>
    </ol>
    <h2>Example Output</h2>
   <pre>
Enter the total number of blocks: 4  
Enter the size of each block in KB: 300 200 100 400  
Enter allocation strategy (1 for first-fit, 2 for best-fit, 3 for worst-fit):  2 
Memory blocks are created…  
Memory blocks: 
=======================================================
Block#      size                start-end      status 
=======================================================
Block0      300                 0-299           free 
Block1      200                 300-499         free 
Block2      100                 500-599         free 
Block3      400                 600-999         free 
======================================================
1) Allocates memory blocks 
2) De-allocates memory blocks 
3) Print report about the current state of memory and internal Fragmentation 
4) Exit 
============================================ 
Enter your choice: 1 
Enter the process ID and size of process:  P1  60 
P1 Allocated at address 500, and the internal fragmentation is 40 
============================================ 
1) Allocates memory blocks 
2) De-allocates memory blocks 
3) Print report about the current state of memory and internal Fragmentation 
4) Exit 
============================================ 
Enter your choice: 1 
Enter the process ID and size of process:  P2 150 
P1 Allocated at address: 300, and the internal fragmentation is 50 
============================================ 
1) Allocates memory blocks 
2) De-allocates memory blocks 
3) Print report about the current state of memory and internal Fragmentation 
4) Exit 
=========================================== 
Enter your choice: 3 
Memory blocks: 
===============================================================================================
Block#     size          start-end        status         ProcessID       InternalFragmentation 
===============================================================================================
Block0      300            0-299            free            Null                    0 
Block1      200            300-499        allocated          P2                     50 
Block2      100            500-599        allocated          P1                     40 
Block3      400            600-999          free             Null                    0 
==============================================================================================
</pr>
</body>

