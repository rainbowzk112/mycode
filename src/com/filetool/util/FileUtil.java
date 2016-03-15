package com.filetool.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public final class FileUtil
{
    /** 
     * 读取文件并按行输出
     * @param filePath
     * @param spec 允许解析的最大行数， spec==null时，解析所有行
     * @return
     * @author
     * @since 2016-3-1
     */
	
	
	public static int max_vert=0;
	public static List<String[]> topoinf=new ArrayList<String[]>();
    public static String readTopo(final String filePath, final Integer spec,TheGraph graph)
    {
    	
        File file = new File(filePath);
        // 当文件不存在或者不可读时
        if ((!isFileExists(file)) || (!file.canRead()))
        {
            System.out.println("file [" + filePath + "] is not exist or cannot read!!!");
            return null;
        }

        BufferedReader br = null;
        FileReader fb = null;
        StringBuffer sb = new StringBuffer();
        try
        {
            fb = new FileReader(file);
            br = new BufferedReader(fb);

            String str = null;
            int index = 0;
            String linkid=null;
            String sourceid=null;
            String destination=null;
            String cost=null;
            String[] result=null;
            
            		
            while (((spec == null) || index++ < spec) && (str = br.readLine()) != null)
            {
                sb.append(str + "\n");
                result=str.split("\\,");
                topoinf.add(result);
                linkid=result[0];
               
                sourceid=result[1];
                destination=result[2];
                cost=result[3];
                int sourceint=Integer.parseInt(sourceid);
                int desint=Integer.parseInt(destination);
                if(sourceint>max_vert)
                	max_vert=sourceint;
                if(desint>max_vert)
                	max_vert=desint;
               graph.addEdge(sourceint, desint,
            		   Integer.parseInt(cost),Integer.parseInt(linkid)); 
               
                 
                
                //System.out.println(str);

            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            closeQuietly(br);
            closeQuietly(fb);
        }

        return sb.toString();
    }
    public static String readConditon(final String filePath, final Integer spec)
    {
    	File file = new File(filePath);
    	// 当文件不存在或者不可读时
    	if ((!isFileExists(file)) || (!file.canRead()))
    	{
    		System.out.println("file [" + filePath + "] is not exist or cannot read!!!");
    		return null;
    	}
    	
    	BufferedReader br = null;
    	FileReader fb = null;
    	StringBuffer sb = new StringBuffer();
    	try
    	{
    		fb = new FileReader(file);
    		br = new BufferedReader(fb);
    		
    		String str = null;
    		int index = 0;
    		String sourceid;
    		String destinationid;
    		while (((spec == null) || index++ < spec) && (str = br.readLine()) != null)
    		{
    			sb.append(str + "\n");
    			
    			//System.out.println(str);
    			
    		}
    	}
    	catch (IOException e)
    	{
    		e.printStackTrace();
    	}
    	finally
    	{
    		closeQuietly(br);
    		closeQuietly(fb);
    	}
    	
    	return sb.toString();
    }
    /** 
     * 写文件
     * @param filePath 输出文件路径
     * @param content 要写入的内容
     * @param append 是否追加
     * @return
     * @author s00274007
     * @since 2016-3-1
     */
    
    public static int write(final String filePath, final String content, final boolean append)
    {
        File file = new File(filePath);
        if (content == null)
        {
            System.out.println("file [" + filePath + "] invalid!!!");
            return 0;
        }

        // 当文件存在但不可写时
        if (isFileExists(file) && (!file.canRead()))
        {
            return 0;
        }

        FileWriter fw = null;
        BufferedWriter bw = null;
        try
        {
            if (!isFileExists(file))
            {
                file.createNewFile();
            }

            fw = new FileWriter(file, append);
            bw = new BufferedWriter(fw);

            bw.write(content);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return 0;
        }
        finally
        {
            closeQuietly(bw);
            closeQuietly(fw);
        }

        return 1;
    }

    private static void closeQuietly(Closeable closeable)
    {
        try
        {
            if (closeable != null)
            {
                closeable.close();
            }
        }
        catch (IOException e)
        {
        }
    }

    private static boolean isFileExists(final File file)
    {
        if (file.exists() && file.isFile())
        {
            return true;
        }

        return false;
    }

}

