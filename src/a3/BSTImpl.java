package a3;

public class BSTImpl implements BST {

    private Node root;
    private int size;

    public BSTImpl() {
        root = null;
        size = 0;
    }

    public BSTImpl(String s) {
        root = new NodeImpl(s);
        size = 1;
    }

    // The implementation of "height" is given to you below
    // it is here to illustrate for you how to set up
    // the method implementation as recursion.
    // You should follow this pattern for implementing the other recursive methods
    // by adding your own private recursive methods

    @Override
    public int height() { // public interface method signature
        // this method is the public interface method
        // it is not recursive, but it calls a recursive
        // private method and passes it access to the tree cells
        return height_recursive(this.root);
    }
    private int height_recursive(Node c) {
        // inner method with different signature
        // this helper method uses recursion to traverse
        // and process the recursive structure of the tree of cells
        if (c==null) return -1;
        int lht = height_recursive(c.getLeft());
        int rht = height_recursive(c.getRight());
        return Math.max(lht,rht) + 1;
    }

    @Override
    public Node getRoot() {
        return this.root;
    }

    @Override
    public String insert(String value) {
        if (this.root = null) {
                this.root = value;
            } else this.insert_r(value, this.root);
        return value;
    }

    private Node insert_r(String k, Node c) {
        int cflag = k.compareTo(c.getValue());

            if (cflag<0) { // k is smaller than node
                c.setLeft(insert_r(k,c.getLeft()));
                return c;
            }
            else
            if (cflag>0) { // k is larger than node
                c.setRight(insert_r(k,c.getRight()));
                return c;
            }
            else //found the node, now insert value
            {if (c.getLeft()==null && c.getRight()==null) { c.setValue(k); }
            }
            size ++;
                return c;

            };

    // remove implementation given to you, do NOT change
    @Override
    public void remove(String value) {
        remove_r(value,this.root);
        size--;
    }
    private Node remove_r(String k, Node c) {
        if (c==null) return null; // item not found, nothing to do

        // now we know we have a real node to examine
        int cflag = k.compareTo(c.getValue());

        if (cflag<0) { // k is smaller than node
            c.setLeft(remove_r(k,c.getLeft()));
            return c;
        }
        else
        if (cflag>0) { // k is larger than node
            c.setRight(remove_r(k,c.getRight()));
            return c;
        }
        else //cflag==0
        { // found it... now figure out how to rearrange

            // cases
            if (c.getLeft()==null && c.getRight()==null) { c = null; } // leaf
            else if (c.getLeft()==null && c.getRight()!=null) { c = c.getRight(); } // RC only
            else if (c.getLeft()!=null && c.getRight()==null) { c = c.getLeft(); } // LC only
            else { // 2 children
                Node max = maxCell(c.getLeft());
                c.setValue(max.getValue());
                c.setLeft(remove_r(c.getValue(), c.getLeft()));   // recurse
            }
            return c;
        }

    }

    private Node maxCell(Node c) { // this is used in remove too
        if (c.getRight()==null) return c;
        return maxCell(c.getRight());
    }

    @Override
    public boolean isFull() {
        Node c = this.root;
        Boolean check = true;
        if (c.getLeft()==null && c.getRight()!=null) { check = false; }
            else if (c.getLeft()!=null && c.getRight()==null) { check = false; }
            else if (c.getLeft()!=null && c.getRight()!=null) { check = true; }
        return check;
    }

    @Override
    public String findMin() {
        return "hello";
    }
        Node c = this.root;
        String n = c.getLeft();

        int compare = n.compareTo(c.getValue());

        if (cflag<0) { // k is smaller than node
            c.setLeft(remove_r(n,c.getLeft()));
            return c;
        }
        else
        if (cflag>0) { // k is larger than node
            c.setRight(remove_r(n,c.getRight()));
            return c;
        }
        else //cflag==0
        { // found it... now figure out how to rearrange

            // cases
            if (c.getLeft()==null && c.getRight()==null) { c = null; } // leaf
            else if (c.getLeft()==null && c.getRight()!=null) { c = c.getRight(); } // RC only
            else if (c.getLeft()!=null && c.getRight()==null) { c = c.getLeft(); } // LC only
            else { // 2 children
                Node max = maxCell(c.getLeft());
                c.setValue(max.getValue());
                c.setLeft(remove_r(c.getValue(), c.getLeft()));   // recurse
            }
            return c;

        return null;
    }

    @Override
    public String findMax() {
        return null;
    }

    @Override
    public boolean contains(String s) {
        return contains_r(s, this.root);
    }
    private boolean contains_r(String s, Node c) {
        int compare = s.compareTo(c.getValue());
        if (compare < 0) { // s is smaller than node
            c.setLeft(contains_r(s, c.getLeft()));
            return false;
        } else if (compare > 0) { // s is larger than node
            c.setRight(contains_r(s, c.getRight()));
            return false;
        }
        if (compare == 0) { //found it... now return the answer
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Node get(String s) {
        return get_r(s,this.root);
    };

    private Node get_r(String s, Node c) {
    int compare = s.compareTo(c.getValue());
        if (compare<0) { // s is smaller than node
            c.setLeft(get_r(s,c.getLeft()));
            return c;
        }
        else
        if (compare>0) { // s is larger than node
            c.setRight(get_r(s,c.getRight()));
            return c;
        }
        if (compare == 0)
        { //found it... now return the node
            return c;
        }
        else
        { return null;
        }
    }

    @Override
    public int size() {
        return this.size;
    }
}

