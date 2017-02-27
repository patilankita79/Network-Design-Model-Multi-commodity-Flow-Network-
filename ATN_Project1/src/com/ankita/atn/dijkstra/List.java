package com.ankita.atn.dijkstra;

public class List {

	/*
	 * This is head node for the Linked List
	 */
	Node head;
	
	public List() {
	}
	
	public List(Node v) {
		head = v;
	}
	
	/*
	 * This method adds a node to the queue
	 */
	public void push(int num) {
		Node temp = head;
		Node n = new Node(num);
		if(head == null) {                         //If List is empty
			head = n;
			head.next = null;
			return;
		}
		while(temp.next != null) {
			temp = temp.next;						//If List is not empty
		}
		temp.next = n;
		n.next = null;
	}
		
	/*
	 * This method pops the head of the queue
	 */
	public Node poll() {
		Node temp = head;
		if(head == null)
			return head;
		head = head.next;
		return temp;
	}
	
	/*
	 * This method calculates the size of the list
	 */
	public int getSize() {
		Node temp = head;
		int count = 0;
		while(temp != null){
			count++;
			temp = temp.next;
		}
		return count;
	}
}
