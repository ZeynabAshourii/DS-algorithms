import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Node {
    int data;
    Node parent;
    Node left;
    Node right;
    int color;
}

public class MyRedBlackTree {
    private Node root;
    private Node TNULL;
    public long redNum;
    private void fixInsert(Node k) {
        Node u;
        while (k.parent.color == 1) {
            if (k.parent == k.parent.parent.right) {
                u = k.parent.parent.left;
                if (u.color == 1) {
                    redNum -= u.data;
                    u.color = 0;
                    if(k.parent.color == 1) redNum -= k.parent.data;
                    k.parent.color = 0;
                    if(k.parent.parent.color == 0) redNum += k.parent.parent.data;
                    k.parent.parent.color = 1;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.left) {
                        k = k.parent;
                        rightRotate(k);
                    }
                    if(k.parent.color == 1) redNum -= k.parent.data;
                    k.parent.color = 0;
                    if(k.parent.parent.color == 0) redNum += k.parent.parent.data;
                    k.parent.parent.color = 1;
                    leftRotate(k.parent.parent);
                }
            } else {
                u = k.parent.parent.right;

                if (u.color == 1) {
                    redNum -= u.data;
                    u.color = 0;
                    if(k.parent.color == 1) redNum -= k.parent.data;
                    k.parent.color = 0;
                    if(k.parent.parent.color == 0) redNum += k.parent.parent.data;
                    k.parent.parent.color = 1;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.right) {
                        k = k.parent;
                        leftRotate(k);
                    }
                    if(k.parent.color == 1) redNum -= k.parent.data;
                    k.parent.color = 0;
                    if(k.parent.parent.color == 0) redNum += k.parent.parent.data;
                    k.parent.parent.color = 1;
                    rightRotate(k.parent.parent);
                }
            }
            if (k == root) {
                break;
            }
        }
        if (root.color == 1) redNum -= root.data;
        root.color = 0;
    }

    public MyRedBlackTree() {
        TNULL = new Node();
        TNULL.color = 0;
        TNULL.left = null;
        TNULL.right = null;
        root = TNULL;
    }

    public Node maximum(Node node) {
        while (node.right != TNULL) {
            node = node.right;
        }
        return node;
    }

    public void leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        if (y.left != TNULL) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    public void rightRotate(Node x) {
        Node y = x.left;
        x.left = y.right;
        if (y.right != TNULL) {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.right) {
            x.parent.right = y;
        } else {
            x.parent.left = y;
        }
        y.right = x;
        x.parent = y;
    }

    public void insert(int key) {
        Node node = new Node();
        node.parent = null;
        node.data = key;
        node.left = TNULL;
        node.right = TNULL;
        redNum += node.data;
        node.color = 1;

        Node y = null;
        Node x = this.root;

        while (x != TNULL) {
            y = x;
            if (node.data < x.data) {
                x = x.left;
            } else {
                x = x.right;
            }
        }

        node.parent = y;
        if (y == null) {
            root = node;
        } else if (node.data < y.data) {
            y.left = node;
        } else {
            y.right = node;
        }

        if (node.parent == null) {
            if(node.color == 1) redNum -= node.data;
            node.color = 0;
            return;
        }

        if (node.parent.parent == null) {
            return;
        }

        fixInsert(node);
    }


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n =  Integer.parseInt(reader.readLine());
        String[] strings = reader.readLine().split(" ");
        MyRedBlackTree bst = new MyRedBlackTree();
        for (int i = 0; i < n; i++){
            bst.insert(Integer.parseInt(strings[i]));
            System.out.println(bst.redNum);
        }

    }
}
