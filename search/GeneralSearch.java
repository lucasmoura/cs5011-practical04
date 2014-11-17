package search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import util.Util;

public abstract class GeneralSearch 
{
	
	protected int [][] graphSearch;
	protected ArrayList<Integer> pathToLocation;
	protected int[][] visitedNodes;
	int size = 0;
	
	public abstract void init(Node initialLocation, Node targetLocation, int heuristic);
	public abstract Node getElement();
	public abstract void push(Node node);
	public abstract void pop();
	public abstract void markAsVisited(Node node);
	public abstract boolean isVisited(Node node);
	public abstract boolean isEmpty();
	public abstract ArrayList<Node> getAdjacentNodes(Node node, int actualCost);
	
	public void setGraphSearch(int[][] graphSearch)
	{
		this.graphSearch = graphSearch;
	}
	
	public final ArrayList<Integer> search(Node initialLocation, Node targetLocation, int heuristic)
	{
		init(initialLocation, targetLocation, heuristic);
		int find = 0;
		Graph.getInstance().initVisitedNodes();

		HashMap<Integer, Integer> path = new HashMap<Integer, Integer>();
		pathToLocation = new ArrayList<Integer>();
		
		while(!isEmpty())
		{
			Node node = getElement();
			int cost = node.getCost();
			Graph.getInstance().setVisitedNode(node.getRow(), node.getColumn());
			
			System.out.println("Current node\nrow:  "+node.getRow()+" column: "+node.getColumn()+"\n" +
					"Num: "+Util.getNum(node)+"\n");
			pop();
			
			if(node.getRow() == targetLocation.getRow() && node.getColumn() == targetLocation.getColumn())
			{
				find = 1;
				break;
			}
			
			ArrayList<Node> nodes = getAdjacentNodes(node, cost);
			//System.out.println();
			
			for(int i =0; i<nodes.size(); i++)
			{
				if(!isVisited(nodes.get(i)) && graphSearch[nodes.get(i).getRow()][nodes.get(i).getColumn()] != 1)
				{
					push(nodes.get(i));
					markAsVisited(nodes.get(i));
					path.put(Util.getNum(nodes.get(i)), Util.getNum(node));
				}
				else
					continue;
			}
			
		}
		
		if(find == 1)
		{
			Integer target = Util.getNum(targetLocation);
			
			while(target != null)
			{
				pathToLocation.add(target);
				target = path.get(target);
			}
		}
		
		size = pathToLocation.size();
		Collections.reverse(pathToLocation);
		
		return pathToLocation;
		
	}
	
	public int getLocation(int num)
	{	
		return 0;
	}
	
	public void printGraphNum()
	{
		for(int i =0; i<17; i++)
		{
			for(int j =0; j<17; j++)
			{
				if(graphSearch[i][j] != -1)
					System.out.print(" ");
				
				System.out.print(Util.getNum(new Node(i, j)));
				
				if(j != 16)
					System.out.print("\t");
			}
			
			System.out.println();
		}
	}

	public int getSize() 
	{
		return size;
	}
	public void setSize(int size) 
	{
		this.size = size;
	}
	
	public int getTotalVisitedNodes() 
	{
		int size = 0;
		
		for(int i =0; i<visitedNodes.length; i++)
			for(int j =0; j<visitedNodes.length; j++)
				size+= visitedNodes[i][j];
		
		return size;
	}
	
	

}
