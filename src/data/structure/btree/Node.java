package data.structure.btree;

import data.db.Game;

import java.util.LinkedList;

public class Node {
    private int order;
    private LinkedList <Game> games;
    private LinkedList <Node> nodes;

    public LinkedList<Game> getGames() {
        return games;
    }
    public void setGames(LinkedList<Game> games) {
        this.games = games;
    }
    public LinkedList<Node> getNodes() {
        return nodes;
    }
    public void setNodes(LinkedList<Node> nodes) {
        this.nodes = nodes;
    }
    public Node(int order){

    }



}

