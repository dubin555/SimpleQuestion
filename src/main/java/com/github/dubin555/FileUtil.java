package com.github.dubin555;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileUtil {
    public static List<String> getStringListFromFile(String filename) {
        URL url = Resources.getResource(filename);
        String s;
        try {
            s = Resources.toString(url, Charsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
        return Arrays.asList(s.split("\n"));
    }

    public static void main(String[] args) {
        List<String> strs = FileUtil.getStringListFromFile("dict.txt");
        for (String s: strs) {
            System.out.println(s);
        }
    }

}
