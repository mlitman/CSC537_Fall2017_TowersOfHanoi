package com.example.awesomefat.csc537_fall2017_towersofhanoi;

/**
 * Created by awesomefat on 11/7/17.
 */

public class Stack
{
    private Node top;

    public Stack()
    {
        this.top = null;
    }

    public void push(String payload)
    {
        Node n = new Node(payload);
        if(this.top == null)
        {
            this.top = n;
        }
        else
        {
            n.setNextNode(this.top);
            this.top = n;
        }
    }

    public String pop()
    {
        Node nodeToReturn = this.top;

        if(this.top != null)
        {
            this.top = this.top.getNextNode();
            nodeToReturn.setNextNode(null);
        }
        return nodeToReturn.getPayload();
    }

    public String peek()
    {
        if(this.top == null)
        {
            return "Empty Stack";
        }
        else
        {
            return this.top.getPayload();
        }
    }
}
