# Algorithmic-Aspects-of-Telecommunication--Project-1

A basic network design model which a variant of multicommodity flow network.

Project 1
The theme of this project is to implement the basic network design model that is presented in the lecture note entitled “An Application to Network Design”, and experiment with it.
Specific Tasks:
 
1.	Create software that is capable of doing the following:
•	As input, it receives the number of nodes (N), the traffic demand values (bij) between pairs of nodes, and the unit cost values for the potential links (aij).
•	As output, the program generates a network topology, with capacities assigned to the links, according to the studied model, using the shortest path based fast solution method (see at the end of the referred lecture note). The program also computes the total cost of the designed network.
Important notes:
•	Any programming language and operating system can be used, it is your choice.
•	For the shortest path algorithm you may download and utilize any existing software module from the Internet. If you use this opportunity, then include in your documentation a precise reference that tells where the module comes from.
2.	Clearly explain how your program works. It is helpful to use flowchartsfor visualizing the explanation.
3.	Run your program on examples that are generated as explained below.
•	Let the number of nodes be N = 20 in each example.
•	For each example, generate the aij,bij values according to the rules described below. In these rules k is a parameter that will change in the experiments.
–	For generating the bij values, take your 10-digit student ID, and repeat it 2 times, to obtain a 20-digit number. For example, if the ID is 0123456789, then after repetition it becomes 01234567890123456789. Let d1,d2,...,d20 denote the digits in this 20-digit number. Then the value of bij is computed by the formula bij = |di − dj|.
For example, using the above sample ID, the value of b3,7 will be b3,7 = |d3 − d7| = |2 − 6| = 4.
–	For generating the aij values, do the following. For any given i, pick k random indices j1,j2,...,jk, all different from each other and from i. Then set
aij1 = aij2 = ... = aijk = 1,
and set aij = 200, whenever j 6= j1,...,jk. Carry out this independently for every i.
Remark: The effect of this is that for every node i there will be k low cost links going out of the node, the others will have large cost. The shortest path algorithm will try to avoid the high cost links, so it effectively means that we limit the number of links that go out of the node, thus limiting the network density.
•	Run your program with k = 3,4,5...,13. For each run generate new random aij,bij parameters independently.
4.	Show graphically in diagrams the following:
•	How does the total cost of the network depends on k?
•	How does the density of the obtained network depends on k? Here the density is defined as the number of directed edges that are assigned nonzero capacity, divided by the total possible number of directed edges, which is N(N − 1).


