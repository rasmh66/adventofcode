package puzzels.jaar2022;

import java.util.ArrayList;
import java.util.List;

public class Node<T> {
    private List<Node<T>> children = new ArrayList<Node<T>>();


    private Node<T> parent = null;
    private T data = null;

    public Node(T data) {
        this.data = data;
    }

    public Node(T data, Node<T> parent) {
        this.data = data;
        this.parent = parent;
    }

    public List<Node<T>> getChildren() {
        return children;
    }

    public void setParent(Node<T> parent) {
//        parent.addChild(this);
        this.parent = parent;
    }

    public Node<T> getParent() {
        return parent;
    }

    public void addChild(T data) {
        Node<T> child = new Node<T>(data);
        child.setParent(this);
        this.children.add(child);
    }

    public void addChild(Node<T> child) {
        child.setParent(this);
        this.children.add(child);
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isRoot() {
        return (this.parent == null);
    }

    public boolean isLeaf() {
        return this.children.size() == 0;
    }

    public void removeParent() {
        this.parent = null;
    }

    public void printNode(int indent) {
//        String form = String.format("%%%ds", indent);
        System.out.printf("%d %s%n",indent ,this.data.toString());
        for (Node<T> child :
                this.getChildren()) {
            child.printNode(indent++);
        }
    }

    @Override
    public String toString() {
        return data.toString() + " " + children.size();
    }

    public void verhoog(Integer aantal) {
        T dat = getData();
        if (dat.getClass() == Iod.class) {
            ((Iod)dat).verhoog(aantal);
        }
        if (getParent() != null) {
            getParent().verhoog(aantal);
        }
    }
}