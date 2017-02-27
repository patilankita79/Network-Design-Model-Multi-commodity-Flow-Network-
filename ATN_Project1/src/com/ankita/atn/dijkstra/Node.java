package com.ankita.atn.dijkstra;

public class Node {

    public int nodeValue;
    public int distance;
	public Node next;
	public Node parent;
	
	public Node() {
		
	}
	
	public Node(int value) {
		this.nodeValue = value;
	}
	
	public Node(int nodeValue, int distance) {
		this.nodeValue = nodeValue;
		this.distance = distance;
	}
}
