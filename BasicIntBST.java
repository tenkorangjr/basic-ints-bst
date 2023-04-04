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

    // this is the head
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
                // save previous balance factor to edit later
                int temp = curNode.left.balanceFactor;
                add(val, curNode.left);
                if (curNode.left.balanceFactor < temp && curNode.left.balanceFactor < 0) {
                    curNode.balanceFactor -= 1;
                }

                // if statement goes after add
            }
        } else {
            if (curNode.right == null) {
                curNode.balanceFactor += 1;
                curNode.right = new Node(val);
            } else {
                int temp = curNode.right.balanceFactor;
                add(val, curNode.right);
                if (curNode.right.balanceFactor > temp && curNode.right.balanceFactor > 0) {
                    curNode.balanceFactor += 1;
                }
            }
        }
    }

    private class BSTIterator implements Iterator<Integer> {
        Stack<Node> stack;
        Node curNode;

        public BSTIterator() {
            stack = new Stack<Node>();
            Node curNode = root;
            while (curNode != null) {
                stack.push(curNode);
                curNode = curNode.left;
            }
        }

        @Override
        public boolean hasNext() {
            if (stack.size() == 0) {
                return false;
            }
            return true;
        }

        @Override
        public Integer next() {
            // return top item off of stack
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
        BasicIntBST ints = new BasicIntBST();
        for (int i = 0; i < 10; i++) {
            ints.add((int) (Math.random() * 10));
            System.out.println(ints);
        }

        for (int num : ints) {
            System.out.println(num);
        }
        // BasicIntBST ints = new BasicIntBST();
        // Node n = new Node(4);
        // System.out.println("4's balance factor" + n.balanceFactor);
        // // ints.add(2, n);
        // // ints.add(1, n);
        // ints.add(3, n);
        // ints.add(6, n);
        // // ints.add(9, n);
        // // ints.add(5, n);

        // System.out.println("2's balance factor" + n.left.balanceFactor);
        // System.out.println("4's balance factor" + n.balanceFactor);
        // System.out.println("6's balance factor" + n.right.balanceFactor);

    }
}