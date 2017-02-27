package com.ankita.atn.dijkstra;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Graph {

	/*
	 * This map stores the Linked list for each node in the graph
	 */
	Map<Integer, List> map = new HashMap<Integer, List>();
	
	/*
	 * This map stores the 'visited' status of each node in the graph 
	 */
	Map<Integer, Boolean> visited = new HashMap<Integer, Boolean>();
	
	/*
	 * This is a Min-Heap
	 */
	PriorityQueue<Node> queue;
	
	/*
	 * This set holds the nodes already traced i.e. whose shortest path is found
	 */
	//Set<Node> tracedNode = new LinkedHashSet<Node>();
	Set<Node> tracedNode;
	
	/*
	 * This array is used to store parent of each node
	 */
	int parent[] = new int[20];
	
	/*
	 * This method returns the parent array
	 */
	public int[] getParent() {
		return parent;
	}

	
	public PriorityQueue<Node> getQueue() {
		return queue;
	}


	/*
	 * This method adds node to the graph, creates a Linked List for each node and sets the 'visited' status to false
	 */
	public void addNode(Node n){
		List list = new List(n);
		map.put(n.nodeValue, list);
		visited.put(n.nodeValue, false);
	}  
	
	/*
	 * This method adds the weighted edge between 2 nodes in the undirected graph
	 */
	public void add_Edge(int v1, int v2, int weight) {
		List listOne = map.get(v1);
		Node headOne = listOne.head;
		while(headOne.next != null) {
			headOne = headOne.next;
		}
		Node nodeOne = new Node();
		nodeOne.nodeValue = v2;
		nodeOne.distance = weight;
		headOne.next = nodeOne;
		
		/*List listTwo = map.get(v2);
		Node headTwo = listTwo.head;
		while(headTwo.next != null) {
			headTwo = headTwo.next;
		}
		Node nodeTwo = new Node();
		nodeTwo.nodeValue = v1;
		nodeTwo.distance = weight;
		headTwo.next = nodeTwo;*/
	}
	
	/*
	 * This method creates and initializes a Min-Heap
	 */
	public void createMinHeap(int size, int start) {
		Comparator<Node> comparator = new CompareNodes();
		queue = new PriorityQueue<Node>(size, comparator);
		for(Integer i : map.keySet()) {
			if(i == start)
				queue.add(new Node(i, 0));
			else
				queue.add(new Node(i, Integer.MAX_VALUE));
		}
	}
	
	/*
	 * This method calculates the shortest path from source node
	 */
	public void calculateShortestPath() {
		tracedNode = new LinkedHashSet<>();
		while(!queue.isEmpty()) {
			Node start = queue.remove();
			tracedNode.add(start);
			
			//System.out.println("Start value: " + start.nodeValue + " Start distance: " + start.distance);
			
			List list = map.get(start.nodeValue);
			Node listNode = list.head.next;
			
			while(listNode != null) {
				Node heapNode = locateHeapNode(listNode);
				if((heapNode != null) && ((start.distance + listNode.distance) < heapNode.distance)) {
					queue.remove(heapNode);
					heapNode.distance = start.distance + listNode.distance;
					heapNode.parent = start;
					queue.add(heapNode);
					parent[heapNode.nodeValue] = heapNode.parent.nodeValue;
				}
				listNode = listNode.next;
			}
			
			//System.out.println("Heap after extracting source with value " + start.nodeValue);
			//printHeap();
		}
	}
	
	/*
	 * This method locates the node in the heap
	 */
	public Node locateHeapNode(Node nodeToFind) {
		Iterator<Node> itr = queue.iterator();
		while(itr.hasNext()) {
			Node n = itr.next();
			if(n.nodeValue == nodeToFind.nodeValue) {
				return n;
			}
		}
		return null;
	}
	
	/*
	 * This method prints the weighted graph
	 */
	public void printGraph() {
		for (List list : map.values()) {
			Node itrOne = list.head;
			System.out.print("List for node " + itrOne.nodeValue + ": ");
			while(itrOne.next != null) {
				System.out.print(itrOne.nodeValue + "->");
				itrOne = itrOne.next;
			}
			System.out.print(itrOne.nodeValue);
			System.out.print("  :  ");
			
			Node itrTwo = list.head.next;
			while(itrTwo != null) {
				System.out.print(itrTwo.distance + " ");
				itrTwo = itrTwo.next;
			}
			System.out.println();
			System.out.println();
		}
	}
	
	/*
	 * This method prints the priority queue	
	 */
	public void printHeap() {
		if(queue.size() != 0) {
			System.out.println("Priority Queue:");
			for (Node node : queue) {
				System.out.println(node.nodeValue + " " + node.distance);
			}
		} else{
			System.out.println("Heap is empty");
		}
	}
	
	/*
	 * This method prints each node, its distance from the source and and its parent
	 */
	public void printDistanceParent() {
		Iterator<Node> itr = tracedNode.iterator();
		while(itr.hasNext()) {
			Node n = itr.next();
			if(n.parent != null) {
				System.out.println("Distance of node " + n.nodeValue + ": "+ n.distance);
			} else {
				System.out.println("Distance of node " + n.nodeValue + ": " + n.distance);
			}
		}	
	}
	
	
	public int printDistanceParent(int destination) {
		Node n = null;
		Iterator<Node> itr = tracedNode.iterator();
		while(itr.hasNext()) {
			n = itr.next();
			if(n.parent != null && n.nodeValue == destination) {
				System.out.println("Distance of node " + n.nodeValue + ": "+ n.distance);
				break;
				
			}
		}
		return n.distance;
		
	}
	/*
	 * This method prints the shortest path for each node
	 */
	public void printPath(int parent[], int j) {
		if(parent[j] == 0) {
			System.out.print(j + " ");
			return;
		}
		printPath(parent, parent[j]);
		System.out.print(j + " ");
	}
}
