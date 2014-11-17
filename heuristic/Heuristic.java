package heuristic;

import search.Node;

public class Heuristic
{
	
	private static final int MANHATTAN_DISTANCE = 0;
	private static final int EUCLIDIAN_DISTANCE = 1;
	
	public static int calculateHeuristic(int heuristic, Node actualNode, Node targetNode)
	{
		
		switch(heuristic)
		{
			case MANHATTAN_DISTANCE:
				return calculateManhattanDistance(actualNode, targetNode);
				
			case EUCLIDIAN_DISTANCE:
				return calculateEuclidianDistance(actualNode, targetNode);
				
			default:
				return -1;
		}
		
	}

	private static int calculateManhattanDistance(Node actualNode, Node targetNode) 
	{
		//Distance for traveling from one vertex in the grid to another
		int distance = 1;
		
		int dx = Math.abs(actualNode.getRow() - targetNode.getRow());
		int dy = Math.abs(actualNode.getColumn() - targetNode.getColumn());
		
		return distance * (dx+dy);
	}
	
	private static int calculateEuclidianDistance(Node actualNode, Node targetNode)
	{
//		int distance = 1;
//		
//		int dx = actualNode.getRow() - targetNode.getRow();
//		dx *= dx;
//		
//		int dy = actualNode.getColumn() - targetNode.getColumn();
//		dy *= dy;
		
		return (int)Math.sqrt(Math.pow((actualNode.getRow() - targetNode.getRow()), 2) +
					Math.pow((actualNode.getColumn() - targetNode.getColumn()), 2));//distance*(int)Math.sqrt(dx+dy);
	}
	
	

}
