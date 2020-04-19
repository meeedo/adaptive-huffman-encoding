package com.company;

public class Node
{


    Node left , right , parent;
    char symbole;
    String symbolecode;
    int number ;
    int count = 0;

    Node()
    {
        right = left = parent = null;
    }
    //Root
    Node(String symbolecode , int count , int number)
    {
        right = left = parent = null;
        this.symbolecode = symbolecode;
        this.count = count;
        this.number = number;
    }

}


