package BST;

public class BST {

    private Node root;

    public void insert(int value) {
        root = insertRecursion(root, value);
    }

    private Node insertRecursion(Node root, int value) {
        /* If the tree is empty,5
           return a new node */
        if (root == null) {
            return new Node(value);
        }

        if (root.data > value) {
            root.left = insertRecursion(root.left, value);
        } else if (root.data < value) {
            root.right = insertRecursion(root.right, value);
        }

        // value is already exists, return the (unchanged) node
        return root;
    }

    public int getNodeCount(Node root) {
        if (root != null) {
            return getNodeCount(root.left) + getNodeCount(root.right) + 1;
        }
        return 0;
    }

    public void printValues(Node root) {
        if (root != null) {
            printValues(root.left);
            System.out.print(" " + root.data);
            printValues(root.right);
        }
    }

    public void deleteTree() {
        // In Java automatic garbage collection
        // happens, so we can simply make root
        // null to delete the tree
        root = null;
    }

    /*
    returns true if given value exists in the tree
    */
    public boolean isInTree(Node root, int value) {
        if (root == null) return false;

        if (root.data > value) {
            return isInTree(root.left, value);
        } else if (root.data < value) {
            return isInTree(root.right, value);
        }
        return true;
    }


    public int getHeight(Node root) {
        if (root == null) return 0;

        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }

    public int getMin(Node root) {
        while (root.left != null) {
            root = root.left;
        }
        return root.data;
    }

    public int getMax(Node root) {
        while (root.right != null) {
            root = root.right;
        }
        return root.data;
    }

    public boolean isBinarySearchTree(Node root) {
        if (root == null) return true;

        if (root.left != null && maxValue(root.left) > root.data) {
            return false;
        }

        if (root.right != null && minValue(root.right) < root.data) {
            return false;
        }

        return isBinarySearchTree(root.left) && isBinarySearchTree(root.right);
    }

    private int minValue(Node root) {
        if (root == null) return Integer.MAX_VALUE;
        return Math.min(root.data, Math.min(minValue(root.left), minValue(root.right)));
    }

    private int maxValue(Node root) {
        if (root == null) return Integer.MIN_VALUE;
        return Math.max(root.data, Math.max(maxValue(root.left), maxValue(root.right)));
    }

    public void deleteValue(int value) {
        root = deleteRecursion(root, value);
    }

    private Node deleteRecursion(Node root, int value) {
        if (root == null) return null;

        if (value < root.data) {
            root.left = deleteRecursion(root.left, value);
        } else if (value > root.data) {
            root.right = deleteRecursion(root.right, value);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            root.data = getMin(root.right);
            root.right = deleteRecursion(root.right, root.data);
        }
        return root;
    }

    public static void main(String[] args) {
        // create a binary search tree
        BST bst = new BST();
        bst.insert(6);
        bst.insert(4);
        bst.insert(8);
        bst.insert(3);
        bst.insert(5);
        bst.insert(7);
        bst.insert(9);
        bst.deleteValue(8);
        bst.printValues(bst.root);

        System.out.println("\nThe number of nodes is: " + bst.getNodeCount(bst.root));
        System.out.println("Is number 1 in the tree? " + bst.isInTree(bst.root, 1));
        System.out.println("Is number 7 in the tree? " + bst.isInTree(bst.root, 7));
        System.out.println("The height of the tree is: " + bst.getHeight(bst.root));
        System.out.println("The min value is: " + bst.getMin(bst.root));
        System.out.println("The max value is: " + bst.getMax(bst.root));
        System.out.println("Check this tree is a binary search tree: " + bst.isBinarySearchTree(bst.root));
    }
}
