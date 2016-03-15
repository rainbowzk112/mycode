package com.filetool.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

import com.filetool.util.FileUtil;
import com.filetool.util.LogUtil;
import com.filetool.util.TheGraph;
import com.routesearch.route.Route;

/**
 * 工具入口
 * 
 * @author
 * @since 2016-3-1
 * @version v1.0
 */
/**
 * 程序说明:顶点个数为自动读取,所以初始化为500个节点,不利于优化
 * @author zk
 *
 */
public class Main
{
    public static void main(String[] args)
    {
        if (args.length != 3)
        {
            System.err.println("please input args: graphFilePath, conditionFilePath, resultFilePath");
            return;
        }

        String graphFilePath = args[0];
        String conditionFilePath = args[1];
        String resultFilePath = args[2];

        LogUtil.printLog("Begin");
        
        TheGraph graph=new TheGraph();
        // 读取输入文件
        String graphContent = FileUtil.readTopo(graphFilePath, null,graph);
        graph.Max_nvert=FileUtil.max_vert+1;
        
        graph.init();
        
        System.out.println("Max_nvert:"+graph.nvert);
        String conditionContent = FileUtil.readConditon(conditionFilePath, null);
        
      
        // 功能实现入口
        String resultStr=Route.searchRoute(graphContent, conditionContent,graph);
        
        // 写入输出文件
       // FileUtil.write(resultFilePath, resultStr, false);

        LogUtil.printLog("End");
    }

}
