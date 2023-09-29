package data.structure.btree;

import data.db.Game;

public class BTree {
    private Node root;
    private int order;

    public Node getRoot() {
        return root;
    }
    public void setRoot(Node root) {
        this.root = root;
    }
    public BTree(int order){
        root = null;
        this.order = order;
    }

    public void insert(Game game){
        root = (game, root);
    }

    private Node insert(Game game, Node root){
        if(root == null){
            //root = new Node();
        }



        return root;
    }

}
