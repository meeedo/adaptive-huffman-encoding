package com.company;

public class operation {
    public String Compression(String input)
    {
        Node root1 = new Node("" , 0 , 100);
        Node currentnode = root1;
        Tree tree1 = new Tree();

        for(int i=0 ; i<input.length() ; i++)
            {
                tree1.getNode(input.charAt(i) , root1);
                Node get = tree1.get();
                tree1.update(input.charAt(i) , get , root1 , true);
            }
            return tree1.getOutput();
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public String Decompression(String input) {
        String code = "";
        Node root2 = new Node("", 0, 100);
        Tree tree2 = new Tree();
        boolean check = true;
        String output2 = "";
        int num;

        for (int i = 0; i < input.length(); i++) {
            //back to the Short Code Table
           // System.out.println("length for loop");
            if (check) {
                while (!tree2.symbolearray.contains(code)) {
                    code += input.charAt(i);
                  //  System.out.println("first while index");
                    i += 1;
                }
                check = false;
                num = Integer.parseInt(code, 2);
                //System.out.println("Symbol : " + (char) num);
                tree2.getNode((char) num, root2);
                Node get = tree2.get();
                tree2.update((char) num, get, root2, false);
                output2 += (char) num;
            }
            //-------------
            code = "";
            boolean Break = false;
            tree2.adddec(root2);

            while (!Break) {
                if (i != input.length()) {
                    code += input.charAt(i);
                   // System.out.println("second while index");
                    i += 1;
                }

                for (int j = 0; j < tree2.Nodes.size(); j++) {
                    if (tree2.Nodes.get(j).symbolecode.equals(code)) {
                      //  System.out.println("check code for loop");
                        Break = true;
                    }
                }

            }
            char c = tree2.checkif(code, root2);
            if (c == '^') {
                check = true;
                System.out.println(" nyt ");
                System.out.println(output2);
            } else {
                System.out.println(" not nyt");
                tree2.getNode(c, root2);
                Node get = tree2.get();
                tree2.update(c, get, root2, false);
                output2 += c;
                System.out.println(output2);
            }
            code = "";
            i -= 1;
            tree2.Nodes.removeAll(tree2.Nodes);
        }
        print(output2);
        return output2;
    }
/////////////////////////////////////////////////////////////////////////////////
    public void print(String output2)
    {
        System.out.println(output2);
    }



}
