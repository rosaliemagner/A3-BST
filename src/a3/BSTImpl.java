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
        if (this.root == null) {
                this.root = new NodeImpl(value);
            } else this.insert_r(value, this.root);
        return value;
    }

    private Node insert_r(String k, Node c) {
        int cflag = k.compareTo(c.getValue());

            if (cflag<0) { // k is smaller than node
                if (c.getLeft() == null) {
                    c.setLeft(new NodeImpl(k));
                } else {
                    insert_r(k, c.getLeft());
                }
            }
            else
            if (cflag>0) { // k is larger than node
                if (c.getRight() == null) {
                    c.setRight(new NodeImpl(k));
                } else {
                    insert_r(k,c.getRight());
                }
                return c;
            }

            size ++;
                return c;

            }

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
            else if (c.getLeft()==null && c.getRight()==null) { check = true; }
            else if (c.getLeft()!=null && c.getRight()!=null) { check = isFull_r(c.getLeft(), c.getRight());}
        return check;
    }

    private boolean isFull_r(Node c, Node d) {
        Boolean checknode = true;
        if (c.getLeft()==null && c.getRight()!=null) { checknode = false; }
        else if (c.getLeft()!=null && c.getRight()==null) { checknode = false; }
        else if (c.getLeft()==null && c.getRight()==null) { checknode = true; }
        else if (c.getLeft()!=null && c.getRight()!=null) { checknode = isFull_r(c.getLeft(), c.getRight());}
        if (d.getLeft()==null && d.getRight()!=null) { checknode = false; }
        else if (d.getLeft()!=null && d.getRight()==null) { checknode = false; }
        else if (d.getLeft()==null && d.getRight()==null) { checknode = true; }
        else if (d.getLeft()!=null && d.getRight()!=null) { checknode = isFull_r(d.getLeft(), d.getRight());}
        return checknode;
    }


    @Override
    public String findMin() {
        Node c = this.root;
        if (this.root == null) {
            return null;
        } else {
             return findMin_r(c).getValue();}
    }

    private Node findMin_r(Node c) {
        return this.root;
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

            return false;
        } else if (compare > 0) { // s is larger than node

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
        if (this.root == null) {
            return null;
        } else
        return get_r(s,this.root);
    };

    private Node get_r(String s, Node c) {
        int compare = s.compareTo(c.getValue());
        if (compare < 0) { // s is smaller than node
            if (c.getLeft() == null) {
                return null;
            } else {
                get_r(s, c.getLeft());
                return c;
            }
        }
        if (compare > 0) { // s is larger than node
            if (c.getRight() == null) {
                return null;
            } else {
                get_r(s, c.getRight());
                return c;
            }
        }
        return c;
    }

    @Override
    public int size() {
        return this.size;
    }
}
