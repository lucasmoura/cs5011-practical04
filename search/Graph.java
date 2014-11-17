package search;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import util.Util;


public class Graph 
{
	
	private int [][] graphLayout;
	private char [][] visitedNodes;
	private HashMap<Integer, Node> nodeNumbers;
	
	private static Graph instance = null;
	
	public static Graph getInstance()
	{
		if(instance == null)
			instance = new Graph();
		
		return instance;
	}
	
	private Graph()
	{
		graphLayout = new int[17][17];
		visitedNodes = new char[17][17];
		nodeNumbers = new HashMap<Integer, Node>();
		initLayout();
		initNodeNumbers();
	}
	
	public void printGraph()
	{
		for(int i =0; i<17; i++)
		{
			for(int j =0; j<17; j++)
			{
				if(graphLayout[i][j] != -1)
					System.out.print(" ");
				
				System.out.print(graphLayout[i][j]);
				
				if(j != 16)
					System.out.print("\t");
			}
			
			System.out.println();
		}
		
	}
	
	private void initNodeNumbers()
	{
		for(int i =0; i<17; i++)
		{
			for(int j = 0; j<17; j++)
			{
				Node n = new Node(i, j);
				nodeNumbers.put(Util.getNum(n), n);
			}
		}
	}
	
	private void initLayout()
	{
		
		for(int[] row: graphLayout)
			Arrays.fill(row, 0);
		
		createSentinel();		
		
	}
	
	private void createSentinel()
	{
		Arrays.fill(graphLayout[0], 1);
		Arrays.fill(graphLayout[16], 1);
		
		for(int i = 1; i<16; i++)
		{
			for(int j =0; j<=16; j++)
			{
				if(j==0 || j ==16)
					graphLayout[i][j] = 1;
			}
		}
	}
	
	public void insertInfo(int [][] graph)
	{
		for(int i =0; i<15; i++)
			for(int j =0; j<15; j++)
				graphLayout[i+1][j+1] = graph[i][j];
		
	}
	
	public int[] getLocation(int target)
	{
		int []location = new int[2];
		location[0] = location[1] = -1;
		
		for(int i =0; i<17; i++)
		{
			for(int j=0; j<17; j++)
			{
				if(graphLayout[i][j] == target)
				{
					location[0] = i;
					location[1] = j;
					return location;
				}
			}
		}
		
		return location;
	}
	
	public void printPath(ArrayList<Integer> path)
	{
		
		System.out.print("Path: [ ");
		for(int i =0; i<path.size(); i++)
		{
			System.out.print(path.get(i));
			
			if(i != path.size()-1)
				System.out.print(", ");
		}
		System.out.print(" ]\n\n");
		
		char[][] tempGraph = new char[graphLayout.length][graphLayout.length];
		
		for(int i =0; i<graphLayout.length; i++)
		{
			for(int j = 0; j<graphLayout.length; j++)
			{
				if(graphLayout[i][j] != -1)
					tempGraph[i][j] = (char)('0'+graphLayout[i][j]);
				else
					tempGraph[i][j] = '*';
			}
		}
		
		for(int i =0; i<path.size(); i++)
		{
			Node n = nodeNumbers.get(path.get(i));
			//System.out.println("Number: "+path.get(i)+"Row: "+n.getRow() +", Column: "+n.getColumn());
			tempGraph[n.getRow()][n.getColumn()] = 'X';
		}
		
		for(int i = 1; i<16; i++)
		{
			for(int j = 1; j<16; j++)
			{
				
				System.out.print(tempGraph[i][j]+" ");
				
				if(j != 16)
					System.out.print("\t");
			}
			
			System.out.println();
		}
		
	}
	
	public void initVisitedNodes()
	{
		
		for(int i =0; i<graphLayout.length; i++)
			for(int j = 0; j<graphLayout.length; j++)
					visitedNodes[i][j] = (char)('0'+graphLayout[i][j]);
				
	}
	
	public void setVisitedNode(int row, int column)
	{
		
		visitedNodes[row][column] = '*';
		
	}
	
	public void printVisitedNodes()
	{
		for(int i = 1; i<16; i++)
		{
			for(int j = 1; j<16; j++)
			{
				
				System.out.print(visitedNodes[i][j]+" ");
				
				if(j != 16)
					System.out.print("\t");
			}
			
			System.out.println();
		}
	}
	
	public int[][] getGraph()
	{
		return graphLayout;
	}

}
