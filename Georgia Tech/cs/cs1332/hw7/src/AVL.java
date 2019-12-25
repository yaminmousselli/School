import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * Your implementation of an AVL Tree.
 *
 * @author Yamin Mousselli
 * @version 1.0
 */
public class AVL<T extends Comparable<? super T>> implements AVLInterface<T> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private AVLNode<T> root;
    private int size;

    /**
     * A no argument constructor that should initialize an empty AVL tree.
     * DO NOT IMPLEMENT THIS CONSTRUCTOR!
     */
    public AVL() {
    }

    /**
     * Initializes the AVL tree with the data in the Collection. The data
     * should be added in the same order it is in the Collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public AVL(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("You can not insert any null "
                    + "data to this data structure.");
        }
        for (T temp : data) {
            if (temp == null) {
                throw new IllegalArgumentException("You can not have any null"
                        + " elements in this AVL Tree");
            }
            add(temp);
        }
    }

    /**
     * Helper method to check for the balance factors and height of the tree
     * after adding or removing nodes to recalculate height and balance
     * factors to see if any rotations are required.
     * @param current the node to start checking for balance factors and
     * height and to update accordingly
     */
    private void calcBFAndHeight(AVLNode<T> current) {
        if (current.getLeft() == null) {
            if (current.getRight() == null) {
                current.setHeight(0);
                current.setBalanceFactor(0);
            } else {
                current.setHeight(current.getRight().getHeight() + 1);
                //change
                current.setBalanceFactor(-1 - current.getRight().getHeight());
            }
        } else {
            if (current.getRight() == null) {
                current.setHeight(current.getLeft().getHeight() + 1);
                current.setBalanceFactor(current.getLeft().getHeight() - (-1));
            } else {
                current.setHeight((Math.max(current.getLeft().getHeight(),
                        current.getRight().getHeight()) + 1));
                current.setBalanceFactor(current.getLeft().getHeight()
                        - current.getRight().getHeight());
            }
        }
    }
     /*
    private void calcBFAndHeight(AVLNode<T> node) {
        node.height = Math.max(easyHeight(node.left), easyHeight(node.right));
        node.balanceFactor = easyHeight(node.left) - easyHeight(node.right);
    }

    private int easyHeight(AVLNode<T> node) {
        if (node == null) {
            return -1;
        }
        return node.height;
    }
     */
    /**
     * Helper method to check the balance factor from a specific node to
     * determine if any rotations need to occur for the AVL to be balanced.
     * @param current the node to begin the check with balance factors in
     * order to determine the type of rotation needed
     * @return the new root of the subtree after rotations
     */
    private AVLNode<T> checkRotation(AVLNode<T> current) {
        if (current.getBalanceFactor() > 1) {
            if (current.getLeft().getBalanceFactor() < 0) {
                return leftRightRotation(current);
            } else {
                return rightRotation(current);
            }
        } else if (current.getBalanceFactor() < -1) {
            if (current.getRight().getBalanceFactor() > 0) {
                return rightLeftRotation(current);
            } else {
                return leftRotation(current);
            }
        }
        return current;
    }
    /**
     * Helper method to perform a right rotation
     * @param current to start performing the right rotation on this node
     * @return the new root of the subtree after performing the right rotation
     */
    private AVLNode<T> rightRotation(AVLNode<T> current) {
        AVLNode<T> newRoot = current.getLeft();
        AVLNode<T> rightChild = current.getLeft().getRight();
        newRoot.setRight(current);
        current.setLeft(rightChild);
        calcBFAndHeight(current);
        calcBFAndHeight(newRoot);
        return newRoot;
    }
    /**
     * Helper method to perform a right-left rotation
     * @param current to start performing the right-left rotation on this
     * node
     * @return the new root of the subtree after performing the right-left
     * rotation
     */
    private AVLNode<T> rightLeftRotation(AVLNode<T> current) {
        AVLNode<T> rightChild = current.getRight();
        rightChild = rightRotation(rightChild);
        current.setRight(rightChild);
        AVLNode<T> newRoot = rightChild;
        AVLNode<T> leftChild = newRoot.getLeft();
        newRoot.setLeft(current);
        current.setRight(leftChild);
        calcBFAndHeight(current);
        calcBFAndHeight(newRoot);
        return newRoot;
    }
    /**
     * Helper method to perform a left rotation
     * @param current to start performing the left rotation on this node
     * @return the new root of the subtree after performing the left
     * rotation
     */
    private AVLNode<T> leftRotation(AVLNode<T> current) {
        AVLNode<T> newRoot = current.getRight();
        AVLNode<T> leftChild = current.getRight().getLeft();
        newRoot.setLeft(current);
        current.setRight(leftChild);
        calcBFAndHeight(current);
        calcBFAndHeight(newRoot);
        return newRoot;
    }

    /**
     * Helper method to perform a left-right rotation
     * @param current to start performing the left-right rotation on this
     * node
     * @return the new root of the subtree after performing the left-right
     * rotation
     */
    private AVLNode<T> leftRightRotation(AVLNode<T> current) {
        AVLNode<T> child = current.getLeft();
        child = leftRotation(child);
        current.setLeft(child);
        AVLNode<T> newRoot = child;
        AVLNode<T> rightChild = newRoot.getRight();
        newRoot.setRight(current);
        current.setLeft(rightChild);
        calcBFAndHeight(current);
        calcBFAndHeight(newRoot);
        return newRoot;
    }
    /**
     * Helper method to add the data in the correct spot, by comparing nodes
     * with one another.
     * @param data the data to be added
     * @param current the node from where you start to recurse
     * @return returns the node that is to be added
     */
    private AVLNode<T> insert(T data, AVLNode<T> current) {
        if (current == null) {
            AVLNode<T> newNode = new AVLNode<T>(data);
            current = newNode;
            size++;
            return current;
        } else if (data.compareTo(current.getData()) > 0) {
            current.setRight(insert(data, current.getRight()));
        } else if (data.compareTo(current.getData()) < 0) {
            current.setLeft(insert(data, current.getLeft()));
        }
        //update height and bf and then rotate
        calcBFAndHeight(current);
        return checkRotation(current);
    }
    @Override
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("You can not add null "
                    + "elements within this AVL Tree");
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
    private AVLNode<T> findSuccessor(AVLNode<T> current, AVLNode<T> nodeDummy) {
        if (current.getLeft() == null) {
            nodeDummy.setData(current.getData());
            return current.getRight();
        } else {
            current.setLeft(findSuccessor(current.getLeft(), nodeDummy));
            calcBFAndHeight(current);
            return checkRotation(current);
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
    private AVLNode<T> removeHelper(T data, AVLNode<T> current, AVLNode<T>
            dataDummy) {
        if (current == null) {
            throw new NoSuchElementException("The element that you are trying"
                    + " to remove does not exist in this AVL Tree");
        } else if (current.getData().compareTo(data) > 0) {
            current.setLeft(removeHelper(data, current.getLeft(), dataDummy));
            calcBFAndHeight(current);
            current = checkRotation(current);
        } else if (current.getData().compareTo(data) < 0) {
            current.setRight(removeHelper(data, current.getRight(),
                    dataDummy));
            calcBFAndHeight(current);
            current = checkRotation(current);
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
                AVLNode<T> nodeDummy = new AVLNode<T>(null);
                current.setRight(findSuccessor(current.getRight(),
                        nodeDummy));
                current.setData(nodeDummy.getData());
            }
        }
        return current;
    }
    @Override
    public T remove(T data) {
        AVLNode<T> current = root;
        AVLNode<T> dataDummy = new AVLNode<T>(null);
        if (data == null) {
            throw new IllegalArgumentException("You can not remove null from "
                    + "this AVL Tree");
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
    private T findData(T data, AVLNode<T> current) {
        if (current != null && current.getData().compareTo(data) == 0) {
            return current.getData();
        } else if (current != null && current.getData().compareTo(data) < 0) {
            return findData(data, current.getRight());

        } else if (current != null && current.getData().compareTo(data) > 0) {
            return findData(data, current.getLeft());
        } else {
            throw new NoSuchElementException("The data you are looking for "
                    + "does not exist within the AVL Tree");
        }
    }

    @Override
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Null does not exist within "
                    + "this AVL Tree, and therefore, you can not "
                    + "retrieve the data");
        }
        if (size == 0) {
            throw new NoSuchElementException("There aren't any elements in "
                    + "the tree and therefore you can not find what you're "
                    + "looking for");
        } else {
            AVLNode<T> current = root;
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
    private boolean determineContains(T data, AVLNode<T> current) {
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
                    + "contained within this AVL Tree");
        }
        if (size == 0) {
            return false;
        } else {
            AVLNode<T> current = root;
            return determineContains(data, current);
        }
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Helper method to traverse through the AVL and return a List in
     * pre-order traversal order
     * @param current the node in which the pre-order traversal starts with
     * @param avlList the list with length size
     * @return the list in the pre-order traversal order of the AVL
     */
    private List<T> preOrderHelper(AVLNode<T> current, List<T> avlList) {
        //PARENT-LEFT-RIGHT
        if (current == null) {
            return avlList;
        }
        avlList.add(current.getData());
        preOrderHelper(current.getLeft(), avlList);
        preOrderHelper(current.getRight(), avlList);
        return avlList;
    }

    @Override
    public List<T> preorder() {
        AVLNode<T> current = root;
        List<T> avlList = new ArrayList<T>(size);
        return preOrderHelper(current, avlList);
    }

    /**
     * Helper method to traverse through the AVL and return a List in
     * post-order traversal order
     * @param current the node in which the post-order traversal starts with
     * @param avlList the list with length size
     * @return the list in the post-order traversal order of the AVL
     */
    private List<T> postOrderHelper(AVLNode<T> current, List<T> avlList) {
        //LEFT-RIGHT PARENT
        if (current == null) {
            return avlList;
        }
        postOrderHelper(current.getLeft(), avlList);
        postOrderHelper(current.getRight(), avlList);
        avlList.add(current.getData());
        return avlList;
    }

    @Override
    public List<T> postorder() {
        AVLNode<T> current = root;
        List<T> avlList = new ArrayList<T>(size);
        return postOrderHelper(current, avlList);
    }

    /**
     * Helper method to traverse through the AVL and return a List in
     * in-order traversal order
     * @param current the node in which the in-order traversal starts with
     * @param avlList the list with length size
     * @return the list in the in-order traversal order of the AVL
     */
    private List<T> inOrderHelper(AVLNode<T> current, List<T> avlList) {
        //LEFT-PARENT-RIGHT
        if (current == null) {
            return avlList;
        }
        inOrderHelper(current.getLeft(), avlList);
        avlList.add(current.getData());
        inOrderHelper(current.getRight(), avlList);
        return avlList;
    }

    @Override
    public List<T> inorder() {
        //L-P-R
        AVLNode<T> current = root;
        List<T> avlList = new ArrayList<T>(size);
        return inOrderHelper(current, avlList);
    }

    @Override
    public List<T> levelorder() {
        Queue<AVLNode<T>> tempQueue = new LinkedList<>();
        tempQueue.add(root);
        List<T> avlList = new ArrayList<>(size);
        if (size == 0) {
            return avlList;
        }
        while (tempQueue.size() != 0) {
            AVLNode<T> node = tempQueue.remove();
            avlList.add(node.getData());
            if (node.getLeft() != null) {
                tempQueue.add(node.getLeft());
            }
            if (node.getRight() != null) {
                tempQueue.add(node.getRight());
            }
        }
        return avlList;
    }

    @Override
    public void clear() {
        size = 0;
        root = null;
    }

    @Override
    public int height() {
        if (size == 0) {
            return -1;
        }
        return root.getHeight();
    }

    @Override
    public AVLNode<T> getRoot() {
        // DO NOT EDIT THIS METHOD!
        return root;
    }
}
