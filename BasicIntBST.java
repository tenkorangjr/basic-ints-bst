import java.util.Iterator;
import java.util.Stack;

public class BasicIntBST implements Iterable<Integer> {

    private static class Node {
        Node left, right;
        int val, balanceFactor;

        public Node(int val) {
            this.val = val;
        }
    }

    Node root;

    public void add(int val) {
        if (root == null) {
            root = new Node(val);
        } else {
            add(val, root);
        }
    }

    /**
     * 
     * @param val
     * @param curNode
     */
    private void add(int val, Node curNode) {
        if (val < curNode.val) {
            if (curNode.left == null) {
                curNode.balanceFactor -= 1;
                curNode.left = new Node(val);
            } else {
                int prevBalanceFactor = curNode.left.balanceFactor;
                add(val, curNode.left);
                if (curNode.left.balanceFactor < prevBalanceFactor && curNode.left.balanceFactor < 0) {
                    curNode.balanceFactor -= 1;
                }
            }
        } else {
            if (curNode.right == null) {
                curNode.balanceFactor += 1;
                curNode.right = new Node(val);
            } else {
                int prevBalanceFactor = curNode.right.balanceFactor;
                add(val, curNode.right);
                if (curNode.right.balanceFactor > prevBalanceFactor && curNode.right.balanceFactor > 0) {
                    curNode.balanceFactor += 1;
                }
            }
        }
    }

    private class BSTIterator implements Iterator<Integer> {
        Stack<Node> stack;

        public BSTIterator() {
            /*
             * Constructor that initialises stack to store the left nodes
             */
            stack = new Stack<Node>();
            Node curNode = root;
            while (curNode != null) {
                stack.push(curNode);
                curNode = curNode.left;
            }
        }

        @Override
        public boolean hasNext() {
            /*
             * Check if there is a next for the iterator
             */

            if (stack.size() == 0) {
                return false;
            }
            return true;
        }

        @Override
        public Integer next() {
            /*
             * Return the next item in the tree
             */
            Node popped = stack.pop();
            Node cur = popped.right;
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            return popped.val;
        }

    }

    public Iterator<Integer> iterator() {

        return new BSTIterator();
    }

    /**
     * This method returns a String representing the BST as a sorted list.
     * Requires BSTIterator to be properly implemented to function correctly.
     */
    public String toString() {
        if (root == null)
            return "[]";

        Iterator<Integer> iter = iterator();
        String out = "[" + iter.next();
        while (iter.hasNext())
            out += ", " + iter.next();
        return out + "]";
    }

    public static void main(String[] args) {
        BasicIntBST bst = new BasicIntBST();
        for (int i = 0; i < 10; i++) {
            bst.add(i * 2);
            System.out.println(bst);
        }

        for (int num : bst) {
            System.out.println(num);
        }

    }
}