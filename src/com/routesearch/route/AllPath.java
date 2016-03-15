package com.routesearch.route;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.filetool.util.TheGraph;


public class AllPath
{
	   private TheGraph graph;
	   private int start;
	   private int end;
	   private Stack<Integer> theStack;
	   private List<Integer> templist;
	   private List<Integer> conditionlist;  
	   public AllPath(TheGraph graph,int start,int end,List<Integer> list)
	   {
		   this.graph=graph;
		   this.start=start;
		   this.end=end;
		   theStack=new Stack<Integer>();
		   conditionlist=list;
	   }
	   public boolean isconnectable(int start,int end)
	   {
		    List<Integer> connectablelist=new ArrayList<Integer>();
		    List<Integer> visitedlist=new ArrayList<Integer>();
		    connectablelist.add(start);
		    while(!connectablelist.isEmpty())
		    {
			    	for(int i=0;i<graph.Max_nvert;i++)
			      {
			    	if(graph.adjMat[start][i]!=10000  && !visitedlist.contains(i))//判断之前访问过的就不再加入了，防止形成回路
			    	{
			    		connectablelist.add(i);
			    		
			    	}
			    	
			      }
			     if(connectablelist.contains(end))
			    	 return true;
			     else
			     {
			    	 visitedlist.add(connectablelist.get(0));
			    	 connectablelist.remove(0);
			    	 if(!connectablelist.isEmpty())
			    		 start=connectablelist.get(0);
			     }
		    }
		    
		    return false;		
	   }
	   public List<List<Integer>> getResult()
	   {
		    
				   
		   if(!isconnectable(start, end))
		   {
			   System.out.println("没有通路");
			   return null;
		   }
			   
		   else
		   {
			   for(int j=0;j<graph.Max_nvert;j++)
			   {
				   templist=new ArrayList<Integer>();
				   
				   for(int i=0;i<graph.Max_nvert;i++)
				   {
					   
					   templist.add(0);
				   }
				   
				   graph.vertexlist[j].setVisitlist(templist);
			   }
			  return findPath(start, end);
		   }
			   
	   }
	   public List<List<Integer>> findPath(int start,int end)
	   {
		   List<Integer> templist;
		   theStack.push(start);
		   List<List<Integer>> resultSet=new ArrayList<List<Integer>>();
		   while(!theStack.isEmpty())
		   {
			   
			   int v=getAdjunvisitedVertex(theStack.peek());
			  
			   if(v==-1)
			   {
				   templist=new ArrayList<Integer>();
				   for(int i=0;i<graph.Max_nvert;i++)
				   {
					   templist.add(0);
				   }
				   graph.vertexlist[theStack.peek()].setVisitlist(templist);
				   theStack.pop();
			   }
			   else
				   theStack.push(v);
			   
			   if(!theStack.isEmpty()&& end==theStack.peek())
			   {
				   if(resultList(theStack).containsAll(conditionlist))
				   {
					   resultSet.add(resultList(theStack));
				   }
//				   printstack(theStack);
//				   System.out.println();
				   theStack.pop();
			   }
			     
		   }
		   return resultSet;
	   }
	   public void printstack(Stack<Integer> stack)
	   {
		   for(Integer integer:stack)
		   {
			   System.out.print(graph.displayVertex(integer));
			   
			   if(integer!=stack.peek())
				   System.out.print("-->");
		   }
		     
			
	   }

	   public List<Integer> resultList(Stack<Integer> stack)
	   {
		   List<Integer> list=new ArrayList<Integer>();
		   for(Integer integer:stack)
		   {
			  // System.out.println(graph.displayVertex(integer));
			   list.add(integer);
			   
		   }
		     
			return list;
	   }
	   public int getAdjunvisitedVertex(int v)
	   {
		   List<Integer> list=graph.vertexlist[v].getVisitedlist();
		   
		   for(int i=0;i<graph.Max_nvert;i++)
		   {
			   if(graph.adjMat[v][i]!=0 && list.get(i)==0 && !theStack.contains(i) )
			   {
				   graph.vertexlist[v].setVisit(i);
				   return i;
			   }
		   }
		   
		   return -1;
	   }
	}
