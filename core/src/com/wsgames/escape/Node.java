package com.wsgames.escape;

import com.badlogic.gdx.math.MathUtils;

/**
 * Created by wietze on 7/17/2017.
 */

public class Node {

    // TODO change these names to something more appropriate

    // Distance from this node to target node
    float hValue;

    // distance form this node to starting node
    float gValue;
    float fValue;

    int x;
    int y;

    private Node parent;


    public Node(int x, int y){
        this.x = x;
        this.y = y;
    }


    // Only calculate this for the nodes that are not walls
    public void calcHeuristic(Node target){

        // TODO play around with different d values
        float d = 1;

        // TODO this is manhattan distance, try other heuristics later
        this.hValue = d* (Math.abs(this.x - target.x) + Math.abs(this.y - target.y));
    }


    public void calcG(Node current){
        //change this to somethings else later?
        this.gValue = Math.abs(this.x - current.x) + Math.abs(this.y - current.y);
    }

    public void calcF(){
        this.fValue = hValue + gValue;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
    public Node getParent(){
        return parent;
    }


}
