package myset;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyHashSetChaining<E> implements MySet<E> {
    // The number of elements in the set
    private int size = 0;

    // Hash table is an array with each cell that is a linked list
    private Node<E>[] table;

    public MyHashSetChaining(int bucketsLength) {
        table = (Node<E>[])new Node[bucketsLength];
        size = 0;
    }

    /** Hash function */
    private int hash(int hashCode) {
        if (hashCode < 0) {
            hashCode = -hashCode;
        }
        return hashCode % table.length;
    }

    @Override /** Remove all elements from this set */
    //Made this myself
    public void clear() {
        for (int i = 0; i < size; i++){
            table[i].next = null;
        }
    }

    @Override /** Return true if the element is in the set */
    public boolean contains(E e) {
        int bucketIndex = hash(e.hashCode());
        Node<E> current = table[bucketIndex];
        boolean found = false;
        while (!found && current != null) {
            if (current.data.equals(e)) {
                found = true;
            } else {
                current = current.next;
            }
        }
        return found;
    }

    @Override /** Add an element to the set */
    public boolean add(E e) {
        int bucketIndex = hash(e.hashCode());
        Node<E> current = table[bucketIndex];
        boolean found = false;
        while (!found && current != null) {
            if (current.data.equals(e)) {
                found = true;
                // Already in the set
            } else {
                current = current.next;
            }
        }
        if (!found) {
            if (((size * 1.0) / table.length) > 0.75){
                rehash();
            }
            Node newNode = new Node();
            newNode.data = e;
            newNode.next = table[bucketIndex];
            table[bucketIndex] = newNode;
            size++;
        }
        return !found;


    }

    /** Rehash the table */
    public void rehash(){
        //kan laves lidt smartere.
        //tempList kan bare referere til referencen for den gamle, lille tabel
        //elementerne kan da bare kopieres over i den nye tabel med klassens add()-metode
        List<Node<E>> tempList = new ArrayList<Node<E>>();
        size = 0;
        table = (Node<E>[]) new Node[(2 * table.length + 1)];

        for (int i = 0; i < table.length; i++){
            if (tempList.get(i) != null) {
                Node<E> current = table[i];
                while (current != null) {
                    tempList.add(current);
                    current = current.next;
                }
            }
        }

        for (Node<E> n : tempList){
            add(n.data);
        }
    }

    @Override /** Remove the element from the set */
    //Made this myself
    public boolean remove(E e) {
        //Jeg ved, at elementet ligger i den bucket, som svarer til hashing af dens hashCode
        //Det kan erstatte det yderste loop
        int bucketIndex = hash(e.hashCode());
        boolean found = false;
        //looper igennem hver bucket i table
        //for (int i = 0; i < size; i++){
            Node<E> prevNode = null;
            Node<E> currentNode = table[bucketIndex];

            if (currentNode == null){
                found = false;
            }
            if (currentNode.data.equals(e)){
                table[bucketIndex] = currentNode.next;
                found = true;
            }

            //i hver bucket leder jeg efter mit element.
            //jeg gemmer nuværende og forrige element, så jeg kan referere udenom
            //nuværende element, hvis det er det, jeg vil slette.
            //hvis jeg finder elementet sletter jeg det alt efter om det først, sidst eller midt i.
            //ellers går jeg videre til næste element og opdaterer prev og current herefter
            while (!found && currentNode != null){
                if(currentNode.data.equals(e)){
                    found = true;
                    size--;
                    prevNode.next = currentNode.next;
                }
                else{
                    prevNode = currentNode;
                    currentNode = currentNode.next;
                }
            }
        //}
        return found;
    }

    @Override /** Return true if the set contains no elements */
    public boolean isEmpty() {
        return size == 0;
    }

    @Override /** Return the number of elements in the set */
    public int size() {
        return size;
    }

    // method only for test purpose
    void writeOut() {
        for (int i = 0; i < table.length; i++) {
            Node<E> temp = table[i];
            if (temp != null) {
                System.out.print(i + "\t");
                while (temp != null) {
                    System.out.print(temp.data + "\t");
                    temp = temp.next;
                }
                System.out.println();
            }
        }
    }
   private class Node<E>{
        public E data;
        public Node<E> next;
    }

}
