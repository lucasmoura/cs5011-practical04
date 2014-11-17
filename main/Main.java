package main;

import grids.GridFactory;

import java.util.ArrayList;
import java.util.Scanner;

import search.AStarSearch;
import search.BestFirstSearch;
import search.BreadthFirstSearch;
import search.DepthFirstSearch;
import search.GeneralSearch;
import search.Graph;
import search.Node;

public class Main 
{
	
	private static Scanner reader;
	private static Graph graph;
	private static ArrayList<Integer> path;
	private static GeneralSearch search;
	private static Node initialLocation;
	private static Node targetLocation;
	private static int[] location;
	private static int[] target;
	private static int heuristic;
	
	private static void printMainMenu()
	{
		System.out.println("Choose one of the following actions: ");
		System.out.println("1 - Run depth-first search algorithm ");
		System.out.println("2 - Run breadth-first search algorithm ");
		System.out.println("3 - Run best-first search algorithm");
		System.out.println("4 - Run A* search");
		System.out.print(": ");
	}
	
	private static int chooseGrid()
	{
		System.out.println("Which grid will be used (1 - 5) ?");
		System.out.print(": ");
		int answer = reader.nextInt();
		
		return answer;
		
	}
	
	private static int chooseHeuristic()
	{
		System.out.println("Which heuristic will be used ?");
		System.out.println("0 - Manhattan Distance heuristic");
		System.out.println("1 - Euclidian Distance heuristic");
		System.out.print(": ");
		int answer = reader.nextInt();
		
		return answer;
	}
	
	private static void setGrid()
	{
		int grid = chooseGrid();
		graph.insertInfo(GridFactory.getInstance().buildGrid(grid));
	}

	public static void main(String args[])
	{
		reader = new Scanner(System.in);
		graph = Graph.getInstance();
		heuristic = 0;
		
		printMainMenu();
		int answer = reader.nextInt();
		
		setGrid();
		
		switch(answer)
		{
		
			case 1:
				
				performDfs();
				break;
				
			case 2:
			
				performBfs();
				break;
				
				
			case 3:
				
				heuristic = chooseHeuristic();
				performBestFirstSearch();
				break;
				
			case 4:
				
				heuristic = chooseHeuristic();
				performAStar();
				break;
		}
			
		
	}
	
	private static void setSearchGraph()
	{
		location = graph.getLocation(2);
		target = graph.getLocation(3);
		
		search.setGraphSearch(graph.getGraph());
		initialLocation = new Node(location[0], location[1]);
		targetLocation = new Node(target[0], target[1]);
	}
	
	private static void printGoal()
	{
		System.out.println("Initial location at: ("+location[0]+", "+location[1]+")");
		System.out.println("Goal location at: ("+target[0]+", "+target[1]+")");
		System.out.println();
	}
	
	private static void performDfs()
	{

		System.out.println("Running depth-first search");
		search = new DepthFirstSearch();
		
		setSearchGraph();
		printGoal();
		
		graph.printGraph();
		search.printGraphNum();
		path = search.search(initialLocation, targetLocation, 0);
		
		graph.printPath(path);
		System.out.println();
		graph.printVisitedNodes();
		
		System.out.println("\nSearch space: "+search.getTotalVisitedNodes());
		System.out.println("Size of path: "+search.getSize());
	}
	
	private static void performBfs()
	{
		System.out.println("Running breadth-first search");
		search = new BreadthFirstSearch();
		
		setSearchGraph();
		printGoal();
		
		search.printGraphNum();
		path = search.search(initialLocation, targetLocation, 0);
		
		graph.printPath(path);
		System.out.println();
		graph.printVisitedNodes();
		
		System.out.println("\nSearch space: "+search.getTotalVisitedNodes());
		System.out.println("Size of path: "+search.getSize());
	}
	
	private static void performBestFirstSearch()
	{
		System.out.println("Running Best-first search");
		search = new BestFirstSearch();
		
		setSearchGraph();
		printGoal();
		
		search.printGraphNum();
		path = search.search(initialLocation, targetLocation, heuristic);
		
		graph.printPath(path);
		System.out.println();
		graph.printVisitedNodes();
		
		System.out.println("\nSearch space: "+search.getTotalVisitedNodes());
		System.out.println("Size of path: "+search.getSize());
	}
	
	private static void performAStar()
	{
		
		System.out.println("Running A* search");
		search = new AStarSearch();
		
		setSearchGraph();
		printGoal();
		
		search.printGraphNum();
		path = search.search(initialLocation, targetLocation, heuristic);

		graph.printPath(path);
		System.out.println();
		graph.printVisitedNodes();
		
		System.out.println("\nSearch space: "+search.getTotalVisitedNodes());
		System.out.println("Size of path: "+search.getSize());
		
	}
	
	
	
}
