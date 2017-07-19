package com.wsgames.escape;

import java.util.ArrayList;

/**
 * Created by wietze on 7/17/2017.
 */

public class PathFind {
    ArrayList<Node> open;
    ArrayList<Node> closed;

    private Node current;
    private Node target;
    private Node start;

    int mapWidth = 15;
    int mapHeight = 25;
    Node[][] map;

    public PathFind(){
        open = new ArrayList<Node>();
        closed = new ArrayList<Node>();

        map = new Node[mapWidth][mapHeight];
        initMap();
    }


    private void initMap(){
        for (int x = 0; x < mapWidth; x++) {
            for (int y = 0; y < mapHeight; y++) {
                map[x][y] = new Node(x,y);

            }

        }
    }

    // This has to be set before starting to finds paths
    public void setTarget(Node target){
        this.target = target;
        map[target.x][target.y] = this.target;
    }

    // This has to be set before starting to finds paths
    public void setStart(Node start){
        this.start = start;
        this.current = start;
        map[current.x][current.y] = this.current;
        open.add(this.current);
    }


    // this has to be done before starting to finds paths
    public void calcCost(){
        for (int x = 0; x < mapWidth; x++) {
            for (int y = 0; y < mapHeight; y++) {
                map[x][y].calcHeuristic(target);
                map[x][y].calcG(current);
                map[x][y].calcF();
            }

        }
    }

    // TODO check if open will ever be empty!
    // this will loop forever if target isn't found?
    public ArrayList<Node> findPath(){
        if(current==null)
            return null;
        if(target == null)
            return null;


        do{

            current = getLowestF();
            open.remove(current);
            closed.add(current);

            for(Node n :getNeighbors(current)){
                System.out.println(n.x + ", " + n.y);
                 // target is found!


                float cost = current.gValue + getMovementCost(n);

                if(open.contains(n)){
                    if(cost < n.gValue){
                        open.remove(n);
                        // not sure if i have to do this
                       // n.setParent(current);
                    }
                }

                // This should not happen if you have an consistent admissible heuristic
                /*if(closed.contains(n)){
                    if(cost < n.gValue){
                        closed.remove(n);
                    }
                }*/


                // if neighbor is not in open or closed
                if(!(open.contains(n)) && !(closed.contains(n))){
                    open.add(n);
                    n.setParent(current);
                }

            }
        }while(!(current.equals(target)));

        // target is found!
        ArrayList<Node> res = new ArrayList<Node>();
        Node backTrace = current;
        do{
            res.add(backTrace);
            backTrace = backTrace.getParent();
        }while(!backTrace.equals(start));

        return res;

    }

    public ArrayList<Node> getNeighbors(Node node){
        ArrayList<Node> temp  = new ArrayList<Node>();


        //north
        if((node.y-1 > 0)){
           temp.add(map[node.x][node.y-1]);
        }

        //south
        if((node.y+1 < mapHeight)){
           temp.add(map[node.x][node.y+1]);
        }

        //west
        if(node.x-1 > 0){
            temp.add(map[node.x-1][node.y]);
        }

        //east
        if(node.x+1 < mapWidth){
            temp.add(map[node.x+1][node.y]);
        }
        return temp;
    }


    public Node getLowestF(){
        // doesn't run if open is empty?
        Node lowest = open.get(0);
        for(Node n : open){
            if(n.fValue < lowest.fValue)
                lowest = n;
        }
        return lowest;
    }

    public float getMovementCost(Node n){
        return Math.abs(current.x - n.x) + Math.abs(current.y - n.y);
    }
}
