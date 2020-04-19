package com.company;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here

        String read , input = "";
        int choose;
        Scanner cin = new Scanner(System.in);

        operation c = new operation();
        operation d = new operation();
        Tree tree = new Tree();

        /// this th read input from file
        File file = new File("C:\\Users\\RS3\\Desktop\\file.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));
        BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\RS3\\Desktop\\file2.txt"));

        while ((read = br.readLine()) != null){input += read;}

        System.out.println(" ENTER"+ "\n"+" 1- TO COMPRESSION " + "\n" + " 2- TO DECOMPRESSION"+"\n");
        choose = cin.nextInt();
        if(choose == 1)
        {
            String result = c.Compression(input);
            System.out.println("Compression code : " + result);
            writer.write(result);
            writer.close();
        }
        else
        {
            String result = d.Decompression(input);
            System.out.println("Decompression code : " + result);
            writer.write(result);
            writer.close();
        }
    }
}
