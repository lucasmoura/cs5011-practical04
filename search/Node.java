package search;

public class Node 
{
	private int row;
	private int column;
	private int cost;
	
	public Node(int row, int column)
	{
		this.row = row;
		this.column = column;
		cost = 0;
	}

	public int getRow()
	{
		return row;
	}

	public void setRow(int row)
	{
		this.row = row;
	}

	public int getColumn()
	{
		return column;
	}

	public void setColumn(int column)
	{
		this.column = column;
	}
	
	public void addCost(int cost)
	{
		this.cost += cost;
	}
	
	public void setCost(int cost)
	{
		this.cost = cost; 
	}
	
	public int getCost()
	{
		return cost;
	}
	

}
