package dictionaryelev;

import com.sun.source.tree.ParenthesizedPatternTree;

public class DictionaryBST<K extends Comparable<K>, V> implements
Dictionary<K, V> {

	private Node root;

	public DictionaryBST() {
		root = null;
	}

	@Override
	public V get(K key) {
		Node current = root;
		V value = null;
		boolean found = false;
		while (current != null && !found){
			if (current.key.compareTo(key) < 0){
				current = current.left;
			}
			else if(current.key.compareTo(key) > 0){
				current = current.right;
			}
			else{
				value = current.value;
				found = true;
			}
		}
		return value;
	}

	private Node find(K key) {
		Node current = root;
		boolean found = false;
		while (!found && current != null) {
			int d = current.key.compareTo(key);
			if (d == 0) {
				found = true;
			} else if (d > 0) {
				current = current.left;
			} else {
				current = current.right;
			}
		}
		if (found) {
			return current;
		} else {
			return null;
		}

	}

	@Override
	public boolean isEmpty() {
		// TODO
		return false;
	}

	@Override
	public V put(K key, V value) {
		Node nodeToPut = new Node(key, value);

		if(root == null){
			root = nodeToPut;
		}
		else{
			Node parent = null;
			Node current = root;
			while (current != null){
				if(current.key.compareTo(nodeToPut.key) < 0){
					parent = current;
					current = current.left;
				}
				else if(current.key.compareTo(nodeToPut.key) > 0){
					parent = current;
					current = current.right;
				}
				else{
					V oldValue = current.value;
					current.value = nodeToPut.value;
					return oldValue;
				}
			}

			if(parent.key.compareTo(nodeToPut.key) < 0){
				parent.left = nodeToPut;
			}
			else{
				parent.right = nodeToPut;
			}
		}

		//size++;
		return nodeToPut.value;
	}

	@Override
	public V remove(K key) {
		Node parent = null;
		Node current = root;
		boolean found = false;
		V removedValue = null;

		while(current != null && !found){
			if (current.key.compareTo(key) < 0){
				parent = current;
				current = current.left;
			}
			else if(current.key.compareTo(key) > 0){
				parent = current;
				current = current.right;
			}
			else{
				found = true;
			}
		}

		if(found){
			//Current has no left child
			if(current.left == null){
				if (parent == null){
					root = current.right;
				}
				else{
					if (current.key.compareTo(key) < 0){
						parent.left = current.right;
					}
					else{
						parent.right = current.right;
					}
				}
			}
			//The current node has a left child
			else{
				Node parentOfRightMost = current;
				Node rightMost = current.left;

				while (rightMost.right != null){
					parentOfRightMost = rightMost;
					rightMost = rightMost.right;
				}

				removedValue = current.value;
				current.key = rightMost.key;
				//values mangler at blive flyttet med over

				if (parentOfRightMost.right == rightMost){
					parentOfRightMost.right = rightMost.left;
				}
				else{
					parentOfRightMost.left = rightMost.left;
				}
			}
			//size--
		}
		return removedValue;
	}

	@Override
	public int size() {
		// TODO
		return -1;
	}

	private class Node {
		private K key;
		private V value;
		private Node left;
		private Node right;

		public Node(K key, V value) {
			this.key = key;
			this.value = value;
			this.left = null;
			this.right = null;
		}


	}

}
