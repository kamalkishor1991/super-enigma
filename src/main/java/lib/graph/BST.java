package lib.lib.graph;

public class BST<E extends Comparable<E>> {
    private Node<E> root;

    public BST() {

    }

    public void add(E d) {
        if (root == null) {
            root = new Node<E>(d);
            root.h = 0;
        } else {
            Node<E> newNode = new Node<E>(d);
            addNode(root, newNode);

        }
    }

    private Node<E> getMinFromNode(Node<E> root) {
        if (root.left == null) return root;
        else return getMinFromNode(root.left);
    }

    private Node<E> getMaxFromNode(Node<E> root) {
        if (root.right == null) return root;
        else return getMaxFromNode(root.right);
    }




    private Node<E> addNode(Node<E> r, Node<E> v) {
        //System.out.println(r);
        if (v.compareTo(r) < 0) { //left
            if (r.left == null) {
                r.left = v;
                v.parent = r;
                return r;
            } else {
                return addNode(r.left, v);
            }
        } else {
            if (r.right == null) {
                r.right = v;
                v.parent = r;
                return r;
            } else {
                return addNode(r.right, v);
            }
        }
    }

    public boolean search(E d) {
        return search(root, d)  == null ? false : true;
    }

    private Node<E> search(Node<E> root, E e) {
        if (root == null) return null;
        if (root.data.equals(e)) {
            return root;
        } else {
            if (root.data.compareTo(e) > 0) {
                return search(root.left, e);
            } else {
                return search(root.right, e);
            }
        }
    }

    public void delete(E e) {

    }

    private Node<E> getSuccessorNode(Node<E> v) {
        Node<E> t = v;
        if (v == null) return null;
        Node<E> max = null;
        if (t.right != null) {
            max = getMinFromNode(t.right);
        } else if (t.parent != null) {
            while (t.parent != null && t.parent.right == t) {
                t = t.parent;
            }
            max = t.parent;
        }
        return max;
    }

    private Node<E> getPredecessorNode(Node<E> v) {
        Node<E> t = v;
        if (v == null) return null;
        Node<E> min = null;
        if (t.left != null) {
            min = getMaxFromNode(t.left);
        } else if (t.parent != null) {
            while (t.parent != null && t.parent.left == t) {
                t = t.parent;
            }
            min = t.parent;
        }
        return min;
    }


    public E successor(E e) {
        Node<E> n = getSuccessorNode(search(root, e));
        return n != null ? n.data : null;
    }
    //test..
    public E predecessor(E e) {
        Node<E> min = getPredecessorNode(search(root, e));
        return min != null ? min.data : null;
    }

    @Override
    public String toString() {
        return root.toString();
    };

    public String inOrder() {
        StringBuilder sb = new StringBuilder();
        inOrder(root, sb);
        return sb.toString();
    }

    private void inOrder(Node<E> node, StringBuilder sb) {
        if (node == null) return;
        else {
            inOrder(node.left, sb);
            sb.append(node.data.toString() + ",");
            inOrder(node.right, sb);
        }
    }


    public String preOrder() {
        StringBuilder sb = new StringBuilder();
        preOrder(root, sb);
        return sb.toString();
    }

    private void preOrder(Node<E> node, StringBuilder sb) {
        if (node == null) return;
        else {
            sb.append(node.data.toString() + ",");
            preOrder(node.left, sb);
            preOrder(node.right, sb);
        }
    }

    public String postOrder() {
        StringBuilder sb = new StringBuilder();
        postOrder(root, sb);
        return sb.toString();
    }

    private void postOrder(Node<E> node, StringBuilder sb) {
        if (node == null) return;
        else {
            postOrder(node.left, sb);
            postOrder(node.right, sb);
            sb.append(node.data.toString() + ",");
        }
    }


    private static class Node<E extends Comparable<E>> implements Comparable<Node<E>>{
        Node(E e) {
            this.data = e;
        }
        E data;
        Node left;
        Node right;
        Node parent;
        int h = 0;


        @Override
        public int compareTo(Node<E> o) {
            //System.out.println(this + "-------" + o + "," + this.data.compareTo(o.data));
            return this.data.compareTo(o.data);
        }

        @Override
        public String toString() {
            return "{ data:" + data + ", Left: { " + left + " }, Right: { " + right + "}" + ", Height:" + h + " }";
        }
    }
}
