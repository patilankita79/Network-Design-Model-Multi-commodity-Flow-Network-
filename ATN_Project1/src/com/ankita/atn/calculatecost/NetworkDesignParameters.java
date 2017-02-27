package com.ankita.atn.calculatecost;

import java.util.ArrayList;
import java.util.Collections;

import com.ankita.atn.dijkstra.Graph;

public class NetworkDesignParameters {

	/*
	 * Unit Cost Matrix
	 */
	int a[][];
	
	/*
	 * Traffic Demand Matrix
	 */
	int b[][];
	
	/*
	 * Unit cost of each link via shortest path
	 */
	int unitMinPathCost[][];
	
	/*
	 * Cost of each link via shortest path
	 */
	int minPathCost[][];
	
	/*
	 * Optimum cost of network
	 */
	int z_opt = 0;
	
	static int k;
	
	/*
	 * Number of nodes
	 */
	static int numberOfNodes;
	
	public NetworkDesignParameters(int numberOfNodes, int k) {
		
		NetworkDesignParameters.numberOfNodes = numberOfNodes;
		NetworkDesignParameters.k = k;
		
		unitMinPathCost = new int[numberOfNodes][numberOfNodes];
		minPathCost = new int[numberOfNodes][numberOfNodes];
		
	}
	
	/*
	 * Initializes unit cost for each link
	 */
	public void initializeUnitCost() {
		
		a = new int[numberOfNodes][numberOfNodes];
		for(int i = 0; i < numberOfNodes; i++){
			
			for (int j = 0; j < numberOfNodes; j++) {
				if(i == j)
					a[i][j] = 0;
				else
					a[i][j] = 200;
			}
		}
		
		for(int i = 0; i < numberOfNodes; i++){
			
			ArrayList<Integer> list = new ArrayList<Integer>();
	        for (int m=0; m < numberOfNodes; m++) {
	        	if(i != m)
	        		list.add(new Integer(m));
	        }
	        
	        Collections.shuffle(list);
	        
	        for (int p=0; p < k; p++) {
	            //System.out.println("Random k value for Source "+i+" : "+list.get(p));
	            a[i][list.get(p)] = 1;
	        }
		}
	}
	
	/*
	 * Initializes Traffic Demand for each link
	 */
	public void initializeTrafficDemand() {
		//int studentID1[] = {2, 0, 2, 1, 3, 0};

		int studentID1[] = {2, 0, 2, 1, 3, 0, 6, 7, 0, 9, 2, 0, 2, 1, 3, 0, 6, 7, 0, 9};
		
		b = new int[numberOfNodes][numberOfNodes];
		
		System.out.println("Input Traffic demand matrix bij");
		for(int i = 0; i < b.length; i++) {
			for(int j = 0; j < b.length; j++){
				
				b[i][j] = Math.abs(studentID1[i] - studentID1[j]);			
				//System.out.println("Traffic demand of " + " ("+(i) + " , " + (j) + ") :" + b[i][j]);
				System.out.print(b[i][j] + " ");
				}
			System.out.println();
		}
	}
	
	/*
	 * Calculates total cost for each link based on shortest path
	 */
	public void calculateLinkCost(Graph graph, int source) {
		
		graph.createMinHeap(numberOfNodes, source);
		graph.calculateShortestPath();
		
		
		
		System.out.println();
		System.out.println("Source " + source);
		for(int i = 0; i < numberOfNodes; i++){
			if(source != i) {
				unitMinPathCost[source][i] = graph.printDistanceParent(i);
				minPathCost[source][i] = unitMinPathCost[source][i] * b[source][i];
				
				//System.out.print(unitMinPathCost[source][i]);
				//System.out.println();
				z_opt += minPathCost[source][i];
				
				
				System.out.println("Cost of the link: "+ "("+(+source+ ", "+ i) +"):" + minPathCost[source][i]);
			}
			
			
		}
		System.out.println();
		
		
	}
	
	public void printUnitMinPathCostMatrix(){
		for(int i =0; i<numberOfNodes; i++){
			for(int j=0; j<numberOfNodes; j++){
				System.out.print(unitMinPathCost[i][j]+"  ");
			}
			System.out.println();
		}
	}

	
	/*
	 *  Calculates network density. Network density = (No of directed edges)/(no of nodes)(no of nodes - 1)
	 */
	public int calculateNetworkDensity(){
		int count = 0;
		for(int i =0; i< numberOfNodes; i++){
			for(int j =0; j< numberOfNodes; j++){
				//condition to check unused link
				if (i != j & (unitMinPathCost[i][j] == 0 || (unitMinPathCost[i][j] != 1 & unitMinPathCost[i][j] != 200))){
					count ++;
					
				}
			}
		}
		
		return count;
		
	}
	
	

}
