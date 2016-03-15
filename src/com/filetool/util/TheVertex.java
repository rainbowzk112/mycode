package com.filetool.util;

import java.util.List;

public class TheVertex
{
	
	public  List<Integer> theVisitedlist;//保存相邻点集合，若被访问过，为1，为被访问，为0
	public boolean isVisible;
	public int label;
	public boolean isIntree;
	public TheVertex(int label)
	{
		this.label=label;
		isVisible=false;
		isIntree=false;
	}
	public List<Integer> getVisitedlist()
	{
		return theVisitedlist;
	}

	public void setVisitlist(List<Integer> list)
	{
		this.theVisitedlist=list;
	}
	public void setVisit(int j)
	{
		this.theVisitedlist.set(j, 1);
	}
	
}
