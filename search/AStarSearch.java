package search;

import heuristic.Heuristic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class AStarSearch extends GeneralSearch
{

	private PriorityQueue<Node> nodes;
	private Node targetNode;
	private int heuristic;
	
	private class NodeComparator implements Comparator<Node>
	{
		@Override
		public int compare(Node n1, Node n2)
		{
			
			int relativeCost1 = n1.getCost() + Heuristic.calculateHeuristic(heuristic, n1, targetNode);
			int relativeCost2 = n2.getCost() + Heuristic.calculateHeuristic(heuristic, n2, targetNode);
			
			if(relativeCost1>relativeCost2)
				return 1;
			else if(relativeCost1<relativeCost2)
				return -1;
			else
				
				if(heuristic == 0)
					return 1;
				else
					return 1;
			
		}
		
	}
	
	@Override
	public void init(Node initialLocation, Node targetLocation, int heuristic)
	{
		visitedNodes = new int[17][17];
		Comparator<Node> comparator = new NodeComparator();
		nodes = new PriorityQueue<Node>(100, comparator);
		targetNode = targetLocation;
		this.heuristic = heuristic;
		
		for(int [] row: visitedNodes)
			Arrays.fill(row, 0);
		
		visitedNodes[initialLocation.getRow()][initialLocation.getColumn()] = 1;
		
		nodes.add(initialLocation);
		
	}

	@Override
	public Node getElement()
	{
		return nodes.peek();
	}

	@Override
	public void push(Node node) 
	{
		nodes.add(node);
		
	}

	@Override
	public void pop() 
	{
		nodes.poll();
		
	}

	@Override
	public void markAsVisited(Node node) 
	{
		visitedNodes[node.getRow()][node.getColumn()] = 1;
		
	}

	@Override
	public boolean isVisited(Node node)
	{
		return visitedNodes[node.getRow()][node.getColumn()] == 1? true: false;
	}

	@Override
	public boolean isEmpty() 
	{
		return nodes.isEmpty();
	}

	@Override
	public ArrayList<Node> getAdjacentNodes(Node node, int actualCost)
	{
		ArrayList<Node> nodes = new ArrayList<Node>();
		int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
		int row, column;
		
		//System.out.println("Search: ");
		for(int i =0; i<4; i++)
		{
			row = node.getRow() + directions[i][0];
			column = node.getColumn() + directions[i][1];
			
			Node n = new Node(row, column);
			n.setCost(actualCost+1);
			
//			System.out.println("Row: "+row);
//			System.out.println("Column: "+column);
//			System.out.println("cost: "+n.getCost());
//			System.out.println();
			
			nodes.add(n);
			
		}
		
		return nodes;
	}

}
