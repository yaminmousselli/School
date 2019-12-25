import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * Your implementation of a binary search tree.
 *
 * @author Yamin Mousselli
 * @version 1.0
 */
public class BST<T extends Comparable<? super T>> implements BSTInterface<T> {
    //Any data that extends Comparable or is Comparable and also this BST can
    // store any data that is a subclass of T which is Comparable.
    private BSTNode<T> root;
    private int size;

    /**
     * A no argument constructor that should initialize an empty BST.
     * YOU DO NOT NEED TO IMPLEMENT THIS CONSTRUCTOR!
     */
    public BST() {

    }

    /**
     * Initializes the BST with the data in the Collection. The data in the BST
     * should be added in the same order it is in the Collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public BST(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("You can not have null "
                    + "elements within this Binary Search Tree");
        }
        for (T temp : data) {
            if (temp == null) {
                throw new IllegalArgumentException("You can not have null "
                        + "elements within this Binary Search Tree");
            }
            add(temp);
        }
    }

    /**
     * Helper method to add the data in the correct spot, by comparing nodes
     * with one another.
     * @param data the data to be added
     * @param current the node from where you start to recurse
     * @return returns the node that is to be added
     */
    private BSTNode<T> insert(T data, BSTNode<T> current) {
        BSTNode<T> newNode = new BSTNode<T>(data);
        if (current == null) {
            current = newNode;
            size++;
            return current;
        } else if (data.compareTo(current.getData()) > 0) {
            current.setRight(insert(data, current.getRight()));
        } else if (data.compareTo(current.getData()) < 0) {
            current.setLeft(insert(data, current.getLeft()));
        }
        return current;
    }

    @Override
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("You can not add null "
                    + "elements within this Binary Search Tree");
        }
        root = insert(data, root); //check it the reference is needed
    }
    /**
     * Helper method to locate a node's successor in the case where the node
     * has two children
     * @param current the node to start comparing
     * @param nodeDummy the node which stores the data of the node to be removed
     * @return node in which the successor is located
     */
    private BSTNode<T> findSuccessor(BSTNode<T> current,
                                    BSTNode<T> nodeDummy) {
        if (current.getLeft() == null) {
            nodeDummy.setData(current.getData());
            if (current.getRight() != null) {
                return current.getRight();
            } else {
                return null;
            }
        }   else {
            current.setLeft(findSuccessor(current.getLeft(), nodeDummy));
            return current;
        }
    }
    /**
     * Helper method to remove the node and return the root node with the
     * appropriate pointers everywhere else
     * matches the data parameter passed in
     * @param data the data to search for in the tree.
     * @param current the node to start comparing for the data value that you
     *  are trying to find
     * @param dataDummy the node which contains the data to be returned to
     * the original remove method.
     * @return the data in the tree that you are trying to find and remove
     * @throws java.util.NoSuchElementException if the data is not found
     */
    private BSTNode<T> removeHelper(T data, BSTNode<T> current, BSTNode<T>
            dataDummy) {
        if (current == null) {
            throw new NoSuchElementException("The element that you are trying"
                    + " to remove does not exist in this Binary Search Tree");
        } else if (current.getData().compareTo(data) > 0) {
            current.setLeft(removeHelper(data, current.getLeft(), dataDummy));
        } else if (current.getData().compareTo(data) < 0) {
            current.setRight(removeHelper(data, current.getRight(), dataDummy));
        } else {
            dataDummy.setData(current.getData());
            size--;
            //Returns null if the node doesn't have any children
            if (current.getLeft() == null && current.getRight() == null) {
                return null;
            } else if (current.getLeft() != null && current.getRight()
                    == null) {
                return current.getLeft();
            } else if (current.getRight() != null && current.getLeft()
                    == null) {
                return current.getRight();
            } else {
                //This dummy holds the successor
                BSTNode<T> nodeDummy = new BSTNode<T>(null);
                current.setRight(findSuccessor(current.getRight(),
                        nodeDummy));
                current.setData(nodeDummy.getData());
            }
        }
        return current;
    }

    @Override
    public T remove(T data) {
        BSTNode<T> current = root;
        BSTNode<T> dataDummy = new BSTNode<T>(null);
        if (data == null) {
            throw new IllegalArgumentException("You can not remove null from "
                    + "this Binary Search Tree");
        }
        root = removeHelper(data, current, dataDummy);
        return dataDummy.getData();
    }

    /**
     * Helper method to return the data contained in a node that matches the
     * data paramater passed in
     * @param data the data to search for in the tree.
     * @param current the node to start comparing for the data value that you
     *  are trying to find
     * @return the data in the tree that is equal to the parameter that is
     * passed in
     * @throws java.util.NoSuchElementException if the data is not found
     */
    private T findData(T data, BSTNode<T> current) {
        if (current != null && current.getData().compareTo(data) == 0) {
            return current.getData();
        } else if (current != null && current.getData().compareTo(data) < 0) {
            return findData(data, current.getRight());

        } else if (current != null && current.getData().compareTo(data) > 0) {
            return findData(data, current.getLeft());
        } else {
            throw new NoSuchElementException("The data you are looking for "
                    + "does not exist within the Binary Search Tree");
        }
    }
    @Override
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Null does not exist within "
                    + "this Binary Search Tree, and therefore, you can not "
                    + "retrieve the data");
        }
        if (size == 0) {
            throw new NoSuchElementException("There aren't any elements in "
                    + "the tree and therefore you can not find what you're "
                    + "looking for");
        } else {
            BSTNode<T> current = root;
            return findData(data, current);
        }
    }

    /**
     * Helper method to determine whether the data passed in exists within
     * the tree
     * @param data the data to search for in the tree.
     * @param current the node in which you are checking to see if it
     * contains the data
     * @return whether or not the data exists in the tree.
     */
    private boolean determineContains(T data, BSTNode<T> current) {
        if (current != null && current.getData().compareTo(data) == 0) {
            return true;
        } else if (current != null && current.getData().compareTo(data) < 0) {
            return determineContains(data, current.getRight());
        } else if (current != null && current.getData().compareTo(data) > 0) {
            return determineContains(data, current.getLeft());
        } else {
            return false;
        }
    }

    @Override
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The value, null, is not "
                    + "contained within this Binary Search Tree");
        }
        if (size == 0) {
            return false;
        } else {
            BSTNode<T> current = root;
            return determineContains(data, current);
        }
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Helper method to traverse through the BST and return a List in
     * pre-order traversal order
     * @param current the node in which the pre-order traversal starts with
     * @param bstList the list with length size
     * @return the list in the pre-order traversal order of the BST
     */
    private List<T> preOrderHelper(BSTNode<T> current, List<T> bstList) {
        //PARENT-LEFT-RIGHT
        if (current == null) {
            return bstList;
        }
        bstList.add(current.getData());
        preOrderHelper(current.getLeft(), bstList);
        preOrderHelper(current.getRight(), bstList);
        return bstList;
    }

    @Override
    public List<T> preorder() { //P-L-R
        BSTNode<T> current = root;
        List<T> bstList = new ArrayList<T>(size);
        return preOrderHelper(current, bstList);
    }
    /**
     * Helper method to traverse through the BST and return a List in
     * post-order traversal order
     * @param current the node in which the post-order traversal starts with
     * @param bstList the list with length size
     * @return the list in the post-order traversal order of the BST
     */
    private List<T> postOrderHelper(BSTNode<T> current, List<T> bstList) {
        //LEFT-RIGHT PARENT
        if (current == null) {
            return bstList;
        }
        postOrderHelper(current.getLeft(), bstList);
        postOrderHelper(current.getRight(), bstList);
        bstList.add(current.getData());
        return bstList;
    }

    @Override
    public List<T> postorder() { //L-R-P
        BSTNode<T> current = root;
        List<T> bstList = new ArrayList<T>(size);
        return postOrderHelper(current, bstList);
    }
    /**
     * Helper method to traverse through the BST and return a List in
     * in-order traversal order
     * @param current the node in which the in-order traversal starts with
     * @param bstList the list with length size
     * @return the list in the in-order traversal order of the BST
     */
    private List<T> inOrderHelper(BSTNode<T> current, List<T> bstList) {
        //LEFT-PARENT-RIGHT
        if (current == null) {
            return bstList;
        }
        inOrderHelper(current.getLeft(), bstList);
        bstList.add(current.getData());
        inOrderHelper(current.getRight(), bstList);
        return bstList;
    }

    @Override
    public List<T> inorder() {
        //L-P-R
        BSTNode<T> current = root;
        List<T> bstList = new ArrayList<T>(size);
        return inOrderHelper(current, bstList);
    }

    @Override
    public List<T> levelorder() {
        Queue<BSTNode<T>> tempQueue = new LinkedList<>();
        tempQueue.add(root);
        List<T> bstList = new ArrayList<>(size);
        if (size == 0) {
            return bstList;
        }
        while (tempQueue.size() != 0) {
            BSTNode<T> node = tempQueue.remove();
            bstList.add(node.getData());
            if (node.getLeft() != null) {
                tempQueue.add(node.getLeft());
            }
            if (node.getRight() != null) {
                tempQueue.add(node.getRight());
            }
        }
        return bstList;
    }

    @Override
    public void clear() {
        size = 0;
        root = null;
    }

    /**
     * Helper Method to determine the height of the BST
     * @param node in which you are starting from, the root.
     * @return the height of the tree
     */
    private int calculateHeight(BSTNode<T> node) {
        if (node == null) {
            return -1;
        } else {
            return Math.max(calculateHeight(node.getLeft()),
                    calculateHeight(node.getRight())) + 1;
        }
    }

    @Override
    public int height() {
        return calculateHeight(root);
    }

    @Override
    public BSTNode<T> getRoot() {
        // DO NOT EDIT THIS METHOD!
        return root;
    }
}
