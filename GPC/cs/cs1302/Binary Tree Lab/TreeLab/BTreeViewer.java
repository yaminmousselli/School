/*
 Name: Yamin Mousselli
 Teacher: William Gregory Johnson
 Date: 4.14.2016
 Purpose:
*/
public class BTreeViewer 
{
  
  public static void main(String[] args) 
  {
    BinaryTree BT = new BinaryTree();
    TreeNode rootnode = new TreeNode('*');
    System.out.println("  ROOT node created value: " + rootnode.nodeValue);
            
    //BT.insertNode... a+b * x-z
    //BT.insertNode(rootnode);
    BT.insertNode(rootnode, 'a');
    BT.insertNode(rootnode, '+');
    BT.insertNode(rootnode, 'b');
    BT.insertNode(rootnode, 'x');
    BT.insertNode(rootnode, '-');
    BT.insertNode(rootnode, 'z');
    
    System.out.println("print the contents of  tree in  binary tree order");
    BT.printTree(rootnode);
    System.out.println(" ");
    
  }
  
}