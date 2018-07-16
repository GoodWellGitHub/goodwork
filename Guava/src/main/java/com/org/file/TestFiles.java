package com.org.file;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;

/**
 * Created by admin on 2018/7/16.
 */
public class TestFiles {
    File file=new File("src/main/resources/file1.txt");
    @Test
    public void testReader() throws Exception{
        BufferedReader bufferedReader= Files.newReader(file, Charsets.UTF_8);
        System.out.println(bufferedReader.readLine());
    }

    @Test
    public void testWriter() throws Exception{
        File file2=new File("src/main/resources/file2.txt");
        if(!file2.exists()){
           file2.createNewFile();
        }
        BufferedWriter bufferedWriter=Files.newWriter(file2,Charsets.UTF_8);
        bufferedWriter.write("新写入第一行数据");
        bufferedWriter.newLine();
        bufferedWriter.write("写入第二行数据");
        bufferedWriter.flush();

    }
    @Test
    public void testCopy(){


    }
}
