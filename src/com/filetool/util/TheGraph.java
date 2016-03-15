package com.filetool.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class Distpar
{
	public int distance;
	public int parent;
	public Distpar(int distance,int parent)
	{
		this.distance=distance;
		this.parent=parent;
	}
}
public class TheGraph
{
	public TheVertex[] vertexlist;
	public int nvert;
	public int[][] adjMat;
	public int[][] linkMat;
	public  int Max_nvert;
	public int Max_Number=500;
	public final int INFINITY=10000;
    public int nTree;
	
	public Distpar[] spath;
	public int currentvertex;
	public int startTocurrent;
	
	
	public List<Integer> dfsList=new ArrayList<Integer>();
	public TheGraph()
	{
		adjMat=new int[Max_Number][Max_Number];
		for(int i=0;i<Max_Number;i++)
			for(int j=0;j<Max_Number;j++)
				adjMat[i][j]=INFINITY;
		linkMat=new int[Max_Number][Max_Number];
	}
	public void init()
	{
		nvert=0;
		vertexlist=new TheVertex[Max_nvert];
		
		for(int i=0;i<Max_nvert;i++)
			addVertex(i);
	}
	public void dfs(int begin)
	{
		vertexlist[begin].isVisible=true;
		Stack<Integer> st=new Stack<Integer>();
		dfsList.add(begin);
		st.push(begin);
		   
		   while(!st.isEmpty())
		   {
			   int v=getAdjUnvisibleVertex(st.peek());
			   if(v==-1)
			   {
				   st.pop();
				   
			   }
			   else
			   {
				   st.push(v);
				   dfsList.add(v);
				   vertexlist[v].isVisible=true;
				   
			   }
		   }
//		   for(int j=0;j<nvert;j++)
//			   vertexlist[j].isVisible=false;
	   }
	
	public int getAdjUnvisibleVertex(int v)
	{
		   for(int j=0;j<nvert;j++)
		   {
			   if(adjMat[v][j]!=0 && vertexlist[j].isVisible==false)
				   return j;
		   }
		   return -1;
	}
	public void addVertex(int label)
	{
		vertexlist[nvert++]=new TheVertex(label);
	}
	
	public void addEdge(int start,int end,int weight,int link)
	{
		adjMat[start][end]=weight;
		linkMat[start][end]=link;
	}
	public int displayVertex(int i)
	{
		return vertexlist[i].label;
	}
	public void path(int startTree,int end)
	{
		
		nTree=1;
		
		vertexlist[startTree].isIntree=true;
		
		for(int j=0;j<nvert;j++)
		{
			int temp=adjMat[startTree][j];
			spath[j]=new Distpar(temp,startTree);
		}
		while(nTree<nvert)
		{
			int minindex=getMin();
			int mindis=spath[minindex].distance;
			
			if(mindis==INFINITY)
			{
				System.out.println("can't reach");
				break;
			}
			else
			{
				currentvertex=minindex;
				startTocurrent=mindis;
			}
			vertexlist[minindex].isIntree=true;
			
			nTree++;
			adjust_path();
			
		}
	}
	public void adjust_path()
	{
		for(int j=1;j<nvert;j++)
		{
			if(!vertexlist[j].isIntree)
			{
				int a=startTocurrent+adjMat[currentvertex][j];
				if(a<spath[j].distance)
				{
					spath[j].distance=a;
					spath[j].parent=currentvertex;
				}
					
			}
		}
	}
	public List<Integer> pathList(int start,int des)
	{
		Stack<Integer> st=new Stack<Integer>();
		List<Integer> list=new ArrayList<Integer>();
		st.push(des);
		
		int temp=spath[des].parent;
		while(temp!=start)
		{
			st.push(temp);
			temp=spath[temp].parent;
		}
		st.push(start);
		while(!st.isEmpty())
		{
			int a=st.pop();
			list.add(a);
		}
		return list;	
//		for(int j=0;j<nTree;j++)
//		{
//			
//			if(spath[j].distance==INFINITY)
//				System.out.println("inf");
//			else
//				System.out.println(spath[j].distance);
//			
//			int parent=vertexlist[spath[j].parent].label;
//			System.out.println("("+parent+")");
//		}
	}
	public int getMin()
	{
		int min=INFINITY;
		int indexMin=0;
		for(int j=1;j<nvert;j++)
		{
			if(!vertexlist[j].isIntree)
			{
				if(spath[j].distance<min)
				{
					min=spath[j].distance;
				    indexMin=j;
				}
				
			}
				
		}
		
		
		
		return indexMin;
	}
}
