
public class BinaryTree 
{
  /*
    inserting new parentNode to tree.
    binary tree set the smaller nodeValue on left and the
    bigger nodeValue on the right
    parent TreeNode in the first case will be root node.
   */
    public void insertNode(TreeNode parentNode, char nodeValue) {
      if (nodeValue < parentNode.nodeValue) 
      {
            if (parentNode.leftReference != null) 
            {
                 // Go more depth to left.
                insertNode(parentNode.leftReference, nodeValue);
            } 
            else 
            {
                System.out.println("  LEFT:  new node value  " + nodeValue + ", its root  " + parentNode.nodeValue);
                parentNode.leftReference = new TreeNode(nodeValue);
            }
        } 
         else if (nodeValue > parentNode.nodeValue) {
            if (parentNode.rightReference != null) 
            {
                // Go more depth to right
                insertNode(parentNode.rightReference, nodeValue);
            } 
            else 
            {
                System.out.println("  Right: new node value  " + nodeValue + ", its root " + parentNode.nodeValue);
                parentNode.rightReference = new TreeNode(nodeValue);
            }
        }
    }

    public void printTree(TreeNode node)//recursivly printing the content of the tree in the order of LVR.
    {
        if (node != null) 
        {
          printTree(node.leftReference);//print left
          System.out.println(node.nodeValue + " ");//print value
          printTree(node.rightReference);//print right
        }
    }
}