package com.company;

import java.io.File;
import java.io.*;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Filer {
    private FileReader fileReader;
    private FileWriter fileWriter;

    private StringBuffer buffer;
    private Map<Character, Integer> map;

    public Filer(String input_file_name, String output_file_name) {
        this.buffer = new StringBuffer();
        this.map = new HashMap<Character, Integer>();
        input_file_name = System.getProperty("user.dir") + "/data/" + input_file_name;
        output_file_name = System.getProperty("user.dir") + "/data/" + output_file_name;
        System.out.println(input_file_name + '\n' + output_file_name);
        try {
            this.fileReader = new FileReader(input_file_name);
            this.fileWriter = new FileWriter(output_file_name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readToBuffer() {
        int tmp = 0;
        while (true) {
            try {
                if ((tmp = this.fileReader.read()) == -1) {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.buffer.append((char) tmp);
        }
    }

    public void countCharacters() {
        for (char c = 'A'; c <= 'Z'; ++c) {
            this.map.put(c, 0);
            this.map.put((char)(c+32), 0);
        }
        char tmp;
        for (int i = 0; i < this.buffer.length(); ++i) {
            tmp = this.buffer.charAt(i);
            if (tmp >= 'a' && tmp <= 'z' || tmp >= 'A' && tmp <= 'Z') {
                this.map.put(
                        this.buffer.charAt(i),
                        this.map.get(this.buffer.charAt(i)) + 1
                );
            }
        }
        /*
        for (Map.Entry<Character, Integer> entry : this.map.entrySet()) {
            if (entry.getValue() != 0) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }
         */
    }

    public void writeToOutFile() {
        for (Map.Entry<Character, Integer> entry : this.map.entrySet()) {
            if (entry.getValue() > 0) {
                try {
                    this.fileWriter.write(entry.getKey().toString());
                    this.fileWriter.write(": " + entry.getValue() + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            this.fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToOutfileWithoutCases() {
        for (char c = 'A'; c <= 'Z'; ++c) {
            this.map.put(c,
                    this.map.get(c) + this.map.get((char) (c+32)));
            this.map.remove((char) (c+32));
        }
        this.writeToOutFile();
    }
}
