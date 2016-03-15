/**
 * 实现代码文件
 * 
 * @author XXX
 * @since 2016-3-4
 * @version V1.0
 */
package com.routesearch.route;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;



import com.filetool.util.FileUtil;
import com.filetool.util.TheGraph;


public final class Route
{
    /**
     * 你需要完成功能的入口
     * 
     * @author XXX
     * @since 2016-3-4
     * @version V1
     */
	
	static String sourceid;
	static String destinationid;
	static String conditionVex;
	static TheGraph mygraph;
	static int minIndex;
	static TheGraph newgraph;
    public static String searchRoute(String graphContent, String condition,TheGraph graph)
    {
    	 mygraph=graph;
         String[] conditionlist=condition.split("\\,");
         sourceid=conditionlist[0];
         destinationid=conditionlist[1];
         conditionVex=conditionlist[2];
         String[] vex=conditionVex.split("\\|");
          
         List<Integer> conditionvexlist=new ArrayList<Integer>();
          for(int i=0;i<vex.length;i++)
        	  conditionvexlist.add(Integer.parseInt(vex[i].trim()));
          //先进行深度优先搜索，找出起始点可以到达的点的集合构建新图
          graph.dfs(Integer.parseInt(sourceid));
         List<Integer> dfsList= graph.dfsList;
         //先不进行，先处理其他问题
         
          
          
          
          
          
          
          
          
          
          
          
         AllPath path=new AllPath(graph,Integer.parseInt(sourceid),Integer.parseInt(destinationid),conditionvexlist);
         
         
         //先判断起点到条件集合的连通状况
         int num=0;
         for(int i=0;i<conditionvexlist.size();i++)
         {
        	 
        	 if(path.isconnectable(Integer.parseInt(sourceid), conditionvexlist.get(i)))
        		 num++;
         }
         System.out.println(num);
         System.out.println(conditionvexlist.size());
         
         
//         List<List<Integer>> set=path.getResult();
//         if(set.size()==0)
//         {
//        	 System.out.println("没有路径");
//        	 return null;
//         }
//        	 
//         
//         int resultWeight=getMinWeight(set);
//         List<Integer> finalList=new ArrayList<Integer>();
//         
//         finalList=set.get(minIndex);
//         String res=listToResult(finalList);
         
          return null;
    }
   public static String listToResult(List<Integer> list)
   {
	   StringBuffer sb=new StringBuffer();
       for(int i=0;i<list.size()-1;i++)
       {
      	 int linkid=mygraph.linkMat[list.get(i)][list.get(i+1)];
      	 sb.append(linkid);
      	 if(i==list.size()-2)
      		 break;
      	 sb.append("|");
       }
       System.out.println(sb);
       return sb.toString();
   }
   public static int getMinWeight(List<List<Integer>> set)
   {
	   int min=theTotalWeight(set.get(0));
	   int temp;
	   for(int i=1;i<set.size();i++)
	   {
		   temp=theTotalWeight(set.get(i));
		   if(temp<min)
		   {
			   min=temp;
			   minIndex=i;
		   }
			   
	   }
	   return min;
   }
   public static int theTotalWeight(List<Integer> list)
   {
	   int weight=0;
	   for(int i=0;i<list.size()-1;i++)
	   {
		   weight+=mygraph.adjMat[list.get(i)][list.get(i+1)];
	   }
	   
	   return weight;
   }
}

