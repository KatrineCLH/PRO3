

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class BST<E> implements Tree<E> {
    protected TreeNode<E> root;
    protected int size = 0;
    protected Comparator<E> c;

    /**
     * Create a default BST with a natural order comparator
     */
    public BST() {
        this.c = (e1, e2) -> ((Comparable<E>) e1).compareTo(e2);
    }

    /**
     * Create a BST with a specified comparator
     */
    public BST(java.util.Comparator<E> c) {
        this.c = c;
    }

    /**
     * Create a binary tree from an array of objects
     */
    public BST(E[] objects) {
        this.c = (e1, e2) -> ((Comparable<E>) e1).compareTo(e2);
        for (int i = 0; i < objects.length; i++)
            insert(objects[i]);
    }

    @Override
    /** Returns true if the element is in the tree */
    public boolean search(E e) {
        TreeNode<E> current = root; // Start from the root

        boolean found = false;
        while (current != null && !found) {
            if (c.compare(e, current.element) < 0) {
                current = current.left;
            } else if (c.compare(e, current.element) > 0) {
                current = current.right;
            } else // element matches current.element
                found = true; // Element is found
        }

        return found;
    }

    @Override
    /** Insert element e into the binary tree
     * Return true if the element is inserted successfully */
    public boolean insert(E e) {
        boolean inserted = true;
        if (root == null)
            root = createNewNode(e); // Create a new root
        else {
            // Locate the parent node
            TreeNode<E> parent = null;
            TreeNode<E> current = root;
            while (current != null && inserted)
                if (c.compare(e, current.element) < 0) {
                    parent = current;
                    current = current.left;
                } else if (c.compare(e, current.element) > 0) {
                    parent = current;
                    current = current.right;
                } else
                    inserted = false; // Duplicate node not inserted

            // Create the new node and attach it to the parent node
            if (c.compare(e, parent.element) < 0)
                parent.left = createNewNode(e);
            else
                parent.right = createNewNode(e);
        }

        size++;
        return inserted; // Element inserted successfully
    }

    protected TreeNode<E> createNewNode(E e) {
        return new TreeNode<>(e);
    }

    @Override
    /** Inorder traversal from the root */
    public void inorder() {
        inorder(root);

    }

    private void inorder(TreeNode<E> node) {
        if (root != null) {
            if (node.left != null) {
                inorder(node.left);
            }
            System.out.println(node.element);
            if (node.right != null) {
                inorder(node.right);
            }
        }
    }



    @Override
    /** Postorder traversal from the root */
    public void postorder() {
        postorder(root);
    }
    private void postorder(TreeNode<E> node) {
        if (root != null) {
            if (node.left != null) {
                postorder(node.left);

            }
            if (node.right != null) {
                postorder(node.right);

            }
            System.out.println(node.element);
        }
    }



    @Override
    /** Preorder traversal from the root */
    public void preorder() {
        preorder(root);
    }
    private void preorder(TreeNode<E> node) {
        if (root != null) {
            System.out.println(node.element);
            if (node.left != null) {
                preorder(node.left);
            }
            if (node.right != null) {
                preorder(node.right);
            }
        }
    }


    /**
     * This inner class is static, because it does not access
     * any instance members defined in its outer class
     */
    public static class TreeNode<E> {
        protected E element;
        protected TreeNode<E> left;
        protected TreeNode<E> right;

        public TreeNode(E e) {
            element = e;
        }
    }

    @Override
    /** Get the number of nodes in the tree */
    public int getSize() {
        return size;
    }

    /**
     * Returns the root of the tree
     */
    public TreeNode<E> getRoot() {
        return root;
    }


    @Override
    /** Delete an element from the binary tree.
     * Return true if the element is deleted successfully
     * Return false if the element is not in the tree */
    public boolean delete(E e) {
        // Locate the node to be deleted and also locate its parent node
        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        boolean found = false;
        while (current != null && !found) {
            if (c.compare(e, current.element) < 0) {
                parent = current;
                current = current.left;
            } else if (c.compare(e, current.element) > 0) {
                parent = current;
                current = current.right;
            } else
                found = true; // Element is in the tree pointed at by current
        }

        if (found) {
            // Case 1: current has no left child
            if (current.left == null) {
                // Connect the parent with the right child of the current node
                if (parent == null) {
                    root = current.right;
                } else {
                    if (c.compare(e, parent.element) < 0)
                        parent.left = current.right;
                    else
                        parent.right = current.right;
                }
            } else {
                // Case 2: The current node has a left child
                // Locate the rightmost node in the left subtree of
                // the current node and also its parent
                TreeNode<E> parentOfRightMost = current;
                TreeNode<E> rightMost = current.left;

                while (rightMost.right != null) {
                    parentOfRightMost = rightMost;
                    rightMost = rightMost.right; // Keep going to the right
                }

                // Replace the element in current by the element in rightMost
                current.element = rightMost.element;

                // Eliminate rightmost node
                if (parentOfRightMost.right == rightMost)
                    parentOfRightMost.right = rightMost.left;
                else
                    // Special case: parentOfRightMost == current
                    parentOfRightMost.left = rightMost.left;
            }
            size--; // Reduce the size of the tree
        }
        return found; // Element deleted successfully
    }

//
    //-------------------------------------------------------------------

    public boolean isLeaf(TreeNode<E> node) {
        boolean leaf = false;
        if (node.left == null && node.right == null) {
            leaf = true;
        }
        return leaf;
    }

    public boolean isInternal(TreeNode<E> node) {
        return !isLeaf(node);
    }

    public int height() {
        return height(root, 0);
    }
    private int height(TreeNode<E> node, int counter){
        if (root != null) {
            if(node.left != null) {
                counter++;
                height(node.left, counter);
            }
            else if(node.right != null){
                counter++;
                height(node.right, counter);
            }
            else{
                counter++;
            }

        }
        return counter;
    }

    private int heightVictor(TreeNode<E> node){
        int result = 0;

        int leftHeight = 0;
        int rightHeight = 0;

        if(node!=null){
            result++;

            leftHeight = heightVictor(node.left);
            rightHeight = heightVictor(node.right);

            result += (leftHeight > rightHeight ? leftHeight : rightHeight);
        }
        return result;
    }
    public E findMax(){return findMax(root);}
    private E findMax(TreeNode<E> node){
        //virker ikke på tomme træer
        //kan også laves iterativt
        return (node.right == null ? node.element : findMax(node.right));
    }
    public E findMin(){return findMin(root);}
    private E findMin(TreeNode<E> node){
        //virker ikke på tomme træer
        //kan også laves iterativt
        return (node.left == null ? node.element : findMin(node.left));
    }


    //Lektion 3
    public E removeMin(){
        TreeNode<E> current = root;
        TreeNode<E> parent = null;

        if(root == null){
            return null;
        }
        else {
            while (current.left != null) {
                parent = current;
                current = current.left;
            }

            if (parent == null) {
                root = root.right;
            } else {
                parent.left = current.right;
            }
            size--;
            return current.element;
        }
    }

    public E removeMax(){
        TreeNode<E> current = root;
        TreeNode<E> parent = null;

        if(root == null){
            return null;
        }
        else {

            while (current.right != null) {
                parent = current;
                current = current.right;
            }

            if (parent == null) {
                root = root.left;
            } else {
                parent.right = current.left;
            }
            size--;
            return current.element;
        }
    }

    private void greaterThan(ArrayList<E> list, TreeNode<E> node, E element){
        if (node == null){
            return;
        }

        int compareResult = c.compare(element, node. element);

        if (compareResult < 0){
            list.add(element);
            greaterThan(list, node.left, element);
            greaterThan(list, node.right, element);
        }
        else{
            greaterThan(list, node.right, element);
        }

        //---------------------------------------------

        if (c.compare(element, node.element) < 0){
            list.add(node.element);
            greaterThan(list, node.left, element);
            greaterThan(list, node.right, element);
        }
        else if(c.compare(element, node.element) > 0){
            list.add(node.element);
            greaterThan(list, node.right, element);
        }
        else{
            greaterThan(list, node.right,element);
        }
    }

    public ArrayList<E> greaterThan(E element){
        ArrayList<E> list = new ArrayList<>();
        greaterThan(list, root, element);
        return list;
    }

    private void preorderhihi(TreeNode<E> node) {
        if (root != null) {
            System.out.println(node.element);
            if (node.left != null) {
                preorder(node.left);
            }
            if (node.right != null) {
                preorder(node.right);
            }
        }
    }

}
