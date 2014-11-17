package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import search.AStarSearch;
import search.GeneralSearch;
import search.Node;

public class AStarTest 
{

	private GeneralSearch search;
	
	@Before
	public void setUp() throws Exception 
	{
		search = new AStarSearch();
	}

	@After
	public void tearDown() throws Exception
	{
		
	}

	@Test
	public void priorityQueueManhattanOrderTest()
	{
		
		Node initialLocation = new Node(2,2);
		ArrayList<Node> nodes = new ArrayList<Node>();
		
		Node n1 = new Node(2, 3);
		Node n2 = new Node(5, 6);
		Node n3 = new Node(2, 8);
		Node n4 = new Node(10, 6);
		
		Node targetLocation = new Node(12, 6);
		
		int heuristic = 0;
		
		search.init(initialLocation, targetLocation, heuristic);
		
		n1.setCost(1);
		n2.setCost(2);
		n3.setCost(4);
		n4.setCost(6);
		
		search.push(n1);
		search.push(n2);
		search.push(n3);
		search.push(n4);
		
		//nodes.add(initialLocation);
		nodes.add(n4);
		nodes.add(n2);
		nodes.add(initialLocation);
		nodes.add(n1);
		nodes.add(n3);
		
		
		for(int i =0; i<nodes.size(); i++)
		{
			Node node1 = nodes.get(i);
			Node node2 = search.getElement();
			
			search.pop();
			
			assertEquals(node1.getRow(), node2.getRow());
			assertEquals(node1.getColumn(), node2.getColumn());
		}
		
	}
	
	@Test
	public void priorityQueueEuclidianOrderTest()
	{
		
		Node initialLocation = new Node(2,2);
		ArrayList<Node> nodes = new ArrayList<Node>();
		
		Node n1 = new Node(4, 3);
		Node n2 = new Node(8, 6);
		Node n3 = new Node(5, 8);
		Node n4 = new Node(10, 6);
		
		Node targetLocation = new Node(12, 6);
		
		int heuristic = 1;
		
		search.init(initialLocation, targetLocation, heuristic);
		
		n1.setCost(1);
		n2.setCost(2);
		n3.setCost(4);
		n4.setCost(6);
		
		search.push(n1);
		search.push(n2);
		search.push(n3);
		search.push(n4);
		
		nodes.add(n2);
		nodes.add(n4);
		nodes.add(n1);
		nodes.add(initialLocation);
		nodes.add(n3);
		
		
		for(int i =0; i<nodes.size(); i++)
		{
			Node node1 = nodes.get(i);
			Node node2 = search.getElement();
			
			search.pop();
			
			assertEquals(node1.getRow(), node2.getRow());
			assertEquals(node1.getColumn(), node2.getColumn());
		}
		
	}
	
	@Test
	public void testGetAdjacentNodes()
	{
		Node initialPosition = new Node(5, 8);
		
		Node n1 = new Node(5, 7);
		Node n2 = new Node(5, 9);
		Node n3 = new Node(4, 8);
		Node n4 = new Node(6, 8);
		
		ArrayList<Node> expectedNodes = new ArrayList<Node>();
		expectedNodes.add(n3);
		expectedNodes.add(n2);
		expectedNodes.add(n4);
		expectedNodes.add(n1);
		
		ArrayList<Node> actualNodes = search.getAdjacentNodes(initialPosition, 0);
		
		for(int i =0; i<actualNodes.size(); i++)
		{
			Node node1 = expectedNodes.get(i);
			Node node2 = actualNodes.get(i);
			
			assertEquals(node1.getRow(), node2.getRow());
			assertEquals(node1.getColumn(), node2.getColumn());
		}
		
		
	}

}
