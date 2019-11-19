package lesson3;

import kotlin.NotImplementedError;
import kotlin.Pair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

// Attention: comparable supported but comparator is not
public class BinaryTree<T extends Comparable<T>> extends AbstractSet<T> implements CheckableSortedSet<T> {

    private static class Node<T> {
        final T value;

        Node<T> left = null;

        Node<T> right = null;


        Node(T value) {
            this.value = value;
        }
    }

    private Node<T> root = null;

    private int size = 0;

    @Override
    public boolean add(T t) {
        Node<T> closest = find(t);
        int comparison = closest == null ? -1 : t.compareTo(closest.value);
        if (comparison == 0) {
            return false;
        }
        Node<T> newNode = new Node<>(t);
        if (closest == null) {
            root = newNode;
        }
        else if (comparison < 0) {
            assert closest.left == null;
            closest.left = newNode;
        }
        else {
            assert closest.right == null;
            closest.right = newNode;
        }
        size++;
        return true;
    }

    public boolean checkInvariant() {
        return root == null || checkInvariant(root);
    }

    public int height() {
        return height(root);
    }

    private boolean checkInvariant(Node<T> node) {
        Node<T> left = node.left;
        if (left != null && (left.value.compareTo(node.value) >= 0 || !checkInvariant(left))) return false;
        Node<T> right = node.right;
        return right == null || right.value.compareTo(node.value) > 0 && checkInvariant(right);
    }

    private int height(Node<T> node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    /**
     * Удаление элемента в дереве
     * Средняя
     */
    //Затраты по памяти-O(1)
    //Трудоёмкость-O(height)
    @Override
    public boolean remove(Object o) {
        @SuppressWarnings("unchecked")
        T t = (T) o;
        Pair<Node<T>, Node<T>> pairOfNodes = findWithParent(t);
        Node<T> currentNode;
        Node<T> parent;
        int comparison;
        if (pairOfNodes != null) {
            currentNode = pairOfNodes.component1();
            parent = pairOfNodes.component2();

        } else {
            return false;
        }
        if (root == null) {
            return false;
        }
        if (parent == null) {
            if (currentNode.left == null && currentNode.right == null) {
                root = null;
                size--;
                return true;
            } else if (currentNode.left != null && currentNode.right == null) {
                root = currentNode.left;
                size--;
                return true;
            } else if (currentNode.left == null) {
                root = currentNode.right;
                size--;
                return true;
            } else {
                Pair<Node<T>, Node<T>> minPair = findWithParent(minNode(currentNode.right).value);
                Node<T> minNode = minPair.component1();
                Node<T> minParent = minPair.component2();
                if (minNode == currentNode.right) {
                    minNode.left = root.left;
                    root = minNode;
                } else {
                    if (minNode.right != null) {
                        minParent.left = minNode.right;
                    } else {
                        minParent.left = null;
                    }
                    minNode.left = root.left;
                    root = minNode;
                    root.right = currentNode.right;
                }
                size--;
                return true;
            }
        } else {
            comparison = currentNode.value.compareTo(parent.value);
            if (currentNode.left == null && currentNode.right == null) {
                if (comparison > 0) {
                    parent.right = null;
                } else {
                    parent.left = null;
                }
                size--;
                return true;
            } else if (currentNode.left != null && currentNode.right == null) {
                if (comparison > 0) {
                    parent.right = currentNode.left;
                } else {
                    parent.left = currentNode.left;
                }
                size--;
                return true;
            } else if (currentNode.left == null) {
                if (comparison > 0) {
                    parent.right = currentNode.right;
                } else {
                    parent.left = currentNode.right;
                }
                size--;
                return true;
            } else {
                Pair<Node<T>, Node<T>> minPair = findWithParent(minNode(currentNode.right).value);
                Node<T> minNode = minPair.component1();
                Node<T> minParent = minPair.component2();
                if (minNode == currentNode.right) {
                    if (comparison < 0) {
                        minNode.left = currentNode.left;
                        parent.left = minNode;
                    } else {
                        minNode.left = currentNode.left;
                        parent.right = minNode;
                    }
                    size--;
                    return true;
                } else {
                    if (minNode.right != null) {
                        minParent.left = minNode.right;
                    } else {
                        minParent.left = null;
                    }
                    if (comparison < 0) {
                        parent.left = minNode;
                        parent.left.right = currentNode.right;
                        parent.left.left = currentNode.left;
                        size--;
                        return true;
                    } else {
                        parent.right = minNode;
                        parent.right.right = currentNode.right;
                        parent.right.left = currentNode.left;
                        size--;
                        return true;
                    }
                }
            }
        }
    }


    private Pair<Node<T>, Node<T>> findWithParent(T value) {
        Node<T> node = find(value);
        if (node == root) {
            return new Pair<>(node, null);
        }
        Node<T> currentNode = root;
        Node<T> parent = currentNode;
        int comparison = value.compareTo(currentNode.value);
        while (comparison != 0) {
            if (comparison < 0) {
                if (currentNode.left != null) {
                    parent = currentNode;
                    currentNode = currentNode.left;
                    comparison = value.compareTo(currentNode.value);
                } else {
                    return null;
                }
            } else {
                if (currentNode.right != null) {
                    parent = currentNode;
                    currentNode = currentNode.right;
                    comparison = value.compareTo(currentNode.value);
                } else {
                    return null;
                }
            }
        }
        return new Pair<>(currentNode, parent);
    }



    private Node<T> minNode(Node<T> currentNode) {
        while (currentNode.left != null) {
            currentNode = currentNode.left;
        }
        return currentNode;
    }



    @Override
    public boolean contains(Object o) {
        @SuppressWarnings("unchecked")
        T t = (T) o;
        Node<T> closest = find(t);
        return closest != null && t.compareTo(closest.value) == 0;
    }

    private Node<T> find(T value) {
        if (root == null) return null;
        return find(root, value);
    }

    private Node<T> find(Node<T> start, T value) {
        int comparison = value.compareTo(start.value);
        if (comparison == 0) {
            return start;
        }
        else if (comparison < 0) {
            if (start.left == null) return start;
            return find(start.left, value);
        }
        else {
            if (start.right == null) return start;
            return find(start.right, value);
        }
    }

    public class BinaryTreeIterator implements Iterator<T> {
        Stack<Node> stack;
        T result = null;

        private BinaryTreeIterator() {
            stack = new Stack<Node>();
            Node<T> node = root;
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        /**
         * Проверка наличия следующего элемента
         * Средняя
         */
//        Сложность O(1)
//        Затраты по памяти O(1)
        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        /**
         * Поиск следующего элемента
         * Средняя
         */
//        Сложность O(n) , где n - количество узлов дерева
//        Затраты по памяти O(1)
        @Override
        public T next() {
            Node<T> node = stack.pop();
            result = node.value;
            if (node.right != null) {
                node = node.right;
                while (node != null) {
                    stack.push(node);
                    node = node.left;
                }
            }
            return result;
        }


        /**
         * Удаление следующего элемента
         * Сложная
         */
//        Сложность - O(height)
//        Затраты по памяти - O(1)
        @Override
        public void remove() {
            BinaryTree.this.remove(result);
        }
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new BinaryTreeIterator();
    }

    @Override
    public int size() {
        return size;
    }


    @Nullable
    @Override
    public Comparator<? super T> comparator() {
        return null;
    }

    /**
     * Для этой задачи нет тестов (есть только заготовка subSetTest), но её тоже можно решить и их написать
     * Очень сложная
     */

    class subSetTree extends BinaryTree<T> {
        private BinaryTree<T> tree;
        private T upperLimit;
        private T lowerLimit;

        subSetTree(BinaryTree<T> binaryTree, T down, T up) {
            tree = binaryTree;
            upperLimit = up;
            lowerLimit = down;
        }


        private boolean inSubSet(T element) {
            if (lowerLimit != null && upperLimit != null) {
                return element.compareTo(lowerLimit) >= 0 && element.compareTo(upperLimit) < 0;
            } else if (upperLimit != null && element.compareTo(upperLimit) < 0) {
                return true;
            } else {
                return lowerLimit != null && element.compareTo(lowerLimit) >= 0;
            }
        }


        public boolean add(T element) {
            if (inSubSet(element)) {
                tree.add(element);
                return true;
            } else {
                throw new IllegalArgumentException();
            }
        }


        public boolean contains(Object o) {
            if (inSubSet((T) o)) {
                return tree.contains(o);
            }
            return false;
        }


        public boolean remove(Object o) {
            if (contains(o)) {
                return tree.remove(o);
            } else {
                throw new IllegalArgumentException();
            }
        }


        public int size() {
            int size = 0;
            for (T element : tree) {
                if (inSubSet(element)) {
                    size++;
                }
            }
            return size;
        }





    }

    //        Сложность - O(n)
    //        Затраты по памяти - O(logn) , где n - количество узлов дерева
    @NotNull
    @Override
    public SortedSet<T> subSet(T fromElement, T toElement) {
        return new subSetTree(this, fromElement, toElement);
    }

    /**
     * Найти множество всех элементов меньше заданного
     * Сложная
     */
    //        Сложность - O(n)
    //        Затраты по памяти - O(logn) , где n - количество узлов дерева
    @NotNull
    @Override
    public SortedSet<T> headSet(T toElement) {
        return new subSetTree(this, null, toElement);
    }

    /**
     * Найти множество всех элементов больше или равных заданного
     * Сложная
     */
    //        Сложность - O(n)
    //        Затраты по памяти - O(logn) , где n - количество узлов дерева
    @NotNull
    @Override
    public SortedSet<T> tailSet(T fromElement) {
        return new subSetTree(this, fromElement, null);
    }

    @Override
    public T first() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.value;
    }

    @Override
    public T last() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.value;
    }
}
