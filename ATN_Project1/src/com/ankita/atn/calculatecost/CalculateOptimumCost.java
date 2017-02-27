package com.ankita.atn.calculatecost;

import java.util.Scanner;
import com.ankita.atn.dijkstra.Graph;
import com.ankita.atn.dijkstra.Node;

public class CalculateOptimumCost {
	
	
	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Please enter number of nodes N: ");
		int numberOfNodes = sc.nextInt();
		
		System.out.println("Please enter k: ");
		int k = sc.nextInt();
		
		//Create Graph
		Graph graph = new Graph();
		Node v[] = new Node[numberOfNodes];
		for(int i =0; i < v.length; i++){
			v[i] = new Node(i);
			graph.addNode(v[i]);
		}
		
		NetworkDesignParameters networkParam = new NetworkDesignParameters(numberOfNodes, k);
		
		//Initialize aij
		networkParam.initializeUnitCost();
		
		for(int i = 0; i < numberOfNodes; i++)
			for(int j = 0; j < numberOfNodes; j++){
				
				graph.add_Edge(v[i].nodeValue, v[j].nodeValue, networkParam.a[i][j]);
			}
		 
		System.out.println();
		
		System.out.println("Adjacency List representation of graph with:	aij");
		graph.printGraph();
		
		//Initialize bij
		networkParam.initializeTrafficDemand();
		
		System.out.println();
		System.out.println("Dijkstra's Algorithm Results:");
		
		//Calculate cost of each link
		for(int i = 0; i < v.length; i++){
			networkParam.calculateLinkCost(graph, v[i].nodeValue);
		}
		
		/*
		 * Following function prints the matrix which stores the minimum cost from the source.
		 */
		System.out.println("Minimum Unit Path Cost Matrix - based on shortest paths");
		networkParam.printUnitMinPathCostMatrix();
		
		
		
		//Calculate the network density.
		
		/*
		 *  Network Density = (No of directed Edges) / (Possible number of directed edges)
		 */
		float networkDensity = 0; 
		
		/*
		 *  Unused links can be: (1) A link having no direct edge 
		 *                       (2) A link having zero capacity.
		 */
		int unUsedLinks = networkParam.calculateNetworkDensity();
		
		/*
		 * Total links  = Possible number of directed edges
		 */
		int totalLinks = (numberOfNodes)*(numberOfNodes - 1);
		
		/*
		 * No of directed edges = Edges with non-zero assigned capacity
		 */
		networkDensity = (float)(totalLinks - unUsedLinks)/(totalLinks);
		
		System.out.println("Total Links = " + totalLinks);
		System.out.println("Unused Links = " + unUsedLinks);
		System.out.println("Used Links = " + (totalLinks - unUsedLinks));

		System.out.println("Network Density = " + networkDensity);
		
		System.out.println("Optimal Cost of the network = " + networkParam.z_opt);
	
	}

}
