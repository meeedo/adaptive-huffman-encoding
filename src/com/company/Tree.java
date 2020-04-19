package com.company;

import java.util.ArrayList;

public class Tree {

    ArrayList<Character> show =new ArrayList<Character>();
    ArrayList<String> symbolearray = new ArrayList<String>();
    ArrayList<Node> Nodes = new ArrayList<Node>();
    Node tmp = new Node();
    String output = "";
//////////////////////////////////////////////////////////////////////////////////////////////
    public Node get()
    {
        return tmp;
    }
///////////////////////////////////////////////////////////////////////////////////////////////
    // this constractor for make decinary with 128 char
    Tree()
    {
        for ( int i = 0 ; i < 128 ; i++){
            String code=Integer.toBinaryString(i);
            while(code.length()<7)
                code='0'+code;
            symbolearray.add( code);
        }
    }

///////////////////////////////////////////////////////////////////////////////////////////////////
    // this to get the node and search if its root or not
    public void getNode(char search , Node root)
    {
        if(show.contains(search))
        {
            if(root != null)
            {
                if(root.symbole == search) tmp = root;
                getNode(search , root.left);
                getNode(search , root.right);
            }
        }
        else
        {
            if(root != null)
            {
                if (root.count == 0) tmp = root;
                getNode(search, root.left);
                getNode(search, root.right);
            }
        }
    }
/////////////////////////////////////////////////////////////////////////////////////////
    public void update(char symbole , Node node , Node root , boolean check)
    {
    Tree tree = new Tree();
    if (!show.contains(symbole)) {
        int index = symbole;
        if (check) {
            output += node.symbolecode + symbolearray.get(index);
        }
        // CHECK FOR NYT TO ADD
        Node tmp = node;
        node.count = 1;
        node.symbole = '$';
        node.left = new Node();
        node.right = new Node();

        while(tmp.number != 100)
        {
            tmp = tmp.parent;
            tmp.count += 1;
        }

        //to apdate the  Right Node to add
        node.right.symbole = symbole;
        node.right.count = 1;
        node.right.parent = node;
        node.right.number = node.right.parent.number - 1;
        node.right.symbolecode = node.right.parent.symbolecode + "1";

        //to update the Left Node to add
        node.left.symbole = '^';
        node.left.parent = node;
        node.left.symbolecode = node.left.parent.symbolecode + "0";
        node.left.number = node.right.number - 1;
        node.left.count = 0;

        show.add(symbole);
    }
    else
        {
            Node tmp2 = node;
            if(check)
            {
                output+=node.symbolecode;
            }

            node.count+=1;
            tree.addc(root);
            boolean chec =tree.swapup();

            for (int i = tmp2.number; i < 100; i++)
            {
                tmp2 = node.parent;
                tmp2.count += 1;
            }
        }
    tree.swaplevel(root);

    }
/////////////////////////////////////////////////////////////////////////////////////////////
    public void swaplevel(Node root)
    {
        if(root.left.count > root.right.count)
        {
            // Swap
            Node temp = root.right.parent.left;
            root.right.parent.left = root.right.parent.right;
            root.right.parent.right = temp;

            // update  Number  after update the tree
            int number;
            number = root.left.number;
            root.left.number = root.right.number;
            root.right.number = number;

            //Update Code after update the tree
            updateCode(root);
        }
        if(root.left.symbole == '$') swaplevel(root.left);
        else if (root.right.symbole == '$') swaplevel(root.right);

    }
//////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean swapup()
    {
        boolean first,second;
        first = second = false;
        for (int i = 0 ; i < Nodes.size() ; i++)
        {
            for(int j = 0 ; j < Nodes.size() ; j++)
            {
                if (i != j)
                {
                    if (Nodes.get(i).count > Nodes.get(j).count && Nodes.get(i).number < Nodes.get(j).number)
                    {
                        //CHECK IF( RIGHT > LEFT )OR (LEFT > RIGHT )
                        if(Nodes.get(i) == Nodes.get(i).parent.right) first = true;
                        if(Nodes.get(j) == Nodes.get(j).parent.right) second = true;
                        ///////////////////////////////////////////////////////////////////////////////////
                        // SWAP THE NODES TO RIGHT OR TO LEFT
                        if(first)
                        {
                            Nodes.get(i).parent.right = Nodes.get(j);
                        }
                        else
                        {
                            Nodes.get(i).parent.left = Nodes.get(j);
                        }
                        if(second)
                        {
                            Nodes.get(j).parent.right = Nodes.get(i);
                        }
                        else
                        {
                            Nodes.get(j).parent.left = Nodes.get(i);
                        }
                        ////////////////////////////////////////////////////////////////////////////////////
                        //updaet and change Parent
                        Node firstParent , secondParent;
                        firstParent = Nodes.get(i).parent;
                        secondParent = Nodes.get(j).parent;

                        Nodes.get(i).parent = secondParent;
                        Nodes.get(j).parent = firstParent;

                        //update Number after swap
                        int number;
                        number = Nodes.get(i).number;
                        Nodes.get(i).number = Nodes.get(j).number;
                        Nodes.get(j).number = number;

                        // update Code after swap
                        String copy = Nodes.get(i).symbolecode;
                        Nodes.get(i).symbolecode = Nodes.get(j).symbolecode;
                        Nodes.get(j).symbolecode = copy;

                        //Remove All nodes to start again
                        Nodes.removeAll(Nodes);
                        return true;
                    }
                }
            }
        }
        Nodes.removeAll(Nodes);
        return false;
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////
    // add new node in compression
    public void addc(Node root)
    {
        if(root != null) {
            if (root.symbole != '$' && root.symbole != '^') {
                Nodes.add(root);
            }
            addc(root.left);
            addc(root.right);
        }
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////
    // add new node in decompression
    public void adddec(Node root)
    {
        if(root == null) return;
        if (root.symbole != '$')  Nodes.add(root);
        adddec(root.left);
        adddec(root.right);
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public char checkif(String symbolecode , Node root)
    {
        for(int i= 0 ; i < symbolecode.length() ; i++)
        {
            if(symbolecode.charAt(i) == '0') root = root.left;
            else if(symbolecode.charAt(i) == '1') root = root.right;
        }
        return root.symbole;
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // to update the code
    public void updateCode(Node current)
    {
        current.left.symbolecode = current.symbolecode + "0";
        current.right.symbolecode = current.symbolecode + "1";
        if(current.left.symbole == '*') updateCode(current.left);
        else if(current.right.symbole == '*') updateCode(current.right);
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // print the result (output) on the terminal
    public void printoutput()
    {
        System.out.println(output);
    }
    public String getOutput()
    {
        return output;
    }
}



