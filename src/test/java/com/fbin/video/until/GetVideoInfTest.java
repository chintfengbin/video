package com.fbin.video.until;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GetVideoInfTest {

    @Test
    public void test(){
        System.out.println(GetVideoInf.getInfo("E:\\upload\\znnn\\sliver20200903133920717.mp4"));
    }

    @Test
    public void test1(){
        ConvertVideo.convertVideo("E:\\upload\\znnn\\sliver20200903133920717.wmv","E:\\upload\\znnn\\");
    }

    @Test
    public void test2(){
        File file = new File("E:\\upload\\znnn\\sliver20200903133920717.wmv");
        String fileName = file.getName().substring(0,file.getName().lastIndexOf("."));
        System.out.println(fileName);
    }
}