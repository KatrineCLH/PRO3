import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        BST<Integer> tree = new BST<>();
        tree.insert(45);
        tree.insert(22);
        tree.insert(77);
        tree.insert(11);
        tree.insert(30);
        tree.insert(15);
        tree.insert(25);
        tree.insert(90);
        tree.insert(88);

        // tree.preorder();
        // tree.postorder();
        //tree.inorder();

        //int hej = tree.height();
        //System.out.println(hej);


        /*
        for (int i = 0; i < 8; i++){
            System.out.println(tree.removeMin());
        }
        System.out.println();

        tree.inorder();

         */


        System.out.println(tree.greaterThan(77));
    }
}
