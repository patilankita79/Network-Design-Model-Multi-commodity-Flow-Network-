package com.ankita.atn.dijkstra;

import java.util.Comparator;

public class CompareNodes implements Comparator<Node>{

	@Override
	public int compare(Node o1, Node o2) {
		
		return (int) (o1.distance - o2.distance);
	}

}
