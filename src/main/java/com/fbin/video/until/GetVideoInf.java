package com.fbin.video.until;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class GetVideoInf {

    /*
     * videoPath 要获取信息的文件地址
     * return 返回一个map类型的info，key：{time视频时长}{pixel分辨率}
     * */
    public static Map<String,String> getInfo(String videoPath) {
        //ffmepg工具地址
        String ffmpegPath = "D:\\FFmpeg\\ffmpeg\\ffmpeg.exe";
        //视频文件地址

        //将信息存储在列表
        Map<String,String> infos = new HashMap<>();
//        拼接cmd命令语句
        StringBuffer buffer = new StringBuffer();
        buffer.append(ffmpegPath);
        //注意要保留单词之间有空格
        buffer.append(" -i ");
        buffer.append(videoPath);
//        执行命令语句并返回执行结果
        try {
            Process process = Runtime.getRuntime().exec(buffer.toString());
            InputStream in = process.getErrorStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line ;
            while((line=br.readLine())!=null) {
//                System.out.println("------"+line+"------");
                if(line.trim().startsWith("Duration:")){
                    //根据字符匹配进行切割
//                    System.out.println("视频时间 = " + line.trim().substring(10,line.trim().indexOf(",")));
                    infos.put("time",line.trim().substring(10,line.trim().indexOf(","))) ;
                }
                //一般包含yuv420p的行就包含分辨率
                if(line.contains("yuv420p")){
                    //根据
                    String definition = line.split(",")[2];
                    definition = definition.trim().split(" ")[0];
//                    System.out.println("分辨率 = " + definition);
                    infos.put("pixel",definition);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return infos;
    }

}
