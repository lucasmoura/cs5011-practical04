package util;

import search.Node;

public class Util 
{
	
	public static Integer getNum(Node node)
	{
		return (node.getColumn()+1)+node.getRow()*17;
	}

}
