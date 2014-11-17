package search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class DepthFirstSearch extends GeneralSearch
{
	
	private Stack<Node> nodes;

	@Override
	public void init(Node initialLocation, Node targetLocation, int heuristic)
	{
		visitedNodes = new int[17][17];
		nodes = new Stack<Node>();
		
		for(int [] row: visitedNodes)
			Arrays.fill(row, 0);
		
		visitedNodes[initialLocation.getRow()][initialLocation.getColumn()] = 1;
		
		nodes.push(initialLocation);
		
	}

	@Override
	public Node getElement() 
	{
		return nodes.peek();
	}

	@Override
	public void push(Node node)
	{
		nodes.push(node);
	}

	@Override
	public void pop() 
	{
		nodes.pop();
	}

	@Override
	public void markAsVisited(Node node) 
	{
		visitedNodes[node.getRow()][node.getColumn()] = 1;
		
	}

	@Override
	public boolean isVisited(Node node) 
	{
		return visitedNodes[node.getRow()][node.getColumn()] == 1?true: false;
	}

	@Override
	public boolean isEmpty() 
	{
		return nodes.empty();
	}

	@Override
	public ArrayList<Node> getAdjacentNodes(Node node, int cost)
	{
		ArrayList<Node> nodes = new ArrayList<Node>();
		int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
		int row, column;
		
		for(int i =0; i<directions.length; i++)
		{
			row = node.getRow() + directions[i][0];
			column = node.getColumn() + directions[i][1];
			
			Node n = new Node(row, column);
			nodes.add(n);
			
		}
		
		return nodes;
		
	}

}
