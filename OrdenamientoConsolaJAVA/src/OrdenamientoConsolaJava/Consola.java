package OrdenamientoConsolaJava;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;
import java.util.Collections;
import java.util.Dictionary;
import java.util.HashMap;


public class Consola {
	public interface ISortAlgorithm<T> {
	    List<T> sort(List<T> input, Comparator<T> comparator);
	}


	public static class ShellSort<T extends Comparable<T>> implements ISortAlgorithm<T> {
	    @Override
	    public List<T> sort(List<T> input, Comparator<T> comparator) {
	        int n = input.size();
	        int gap = n / 2;

	        while (gap > 0) {
	            for (int i = gap; i < n; i++) {
	                T temp = input.get(i);
	                int j = i;

	                while (j >= gap && comparator.compare(input.get(j - gap), temp) > 0) {
	                    input.set(j, input.get(j - gap));
	                    j -= gap;
	                }

	                input.set(j, temp);
	            }

	            gap /= 2;
	        }

	        return input;
	    }
	}

	public static class SelectionSort<T extends Comparable<T>> implements ISortAlgorithm<T> {
	    @Override
	    public List<T> sort(List<T> input, Comparator<T> comparator) {
	        int n = input.size();

	        for (int i = 0; i < n - 1; i++) {
	            int minIndex = i;

	            for (int j = i + 1; j < n; j++) {
	                if (comparator.compare(input.get(j), input.get(minIndex)) < 0) {
	                    minIndex = j;
	                }
	            }

	            // Swap elements
	            T temp = input.get(i);
	            input.set(i, input.get(minIndex));
	            input.set(minIndex, temp);
	        }

	        return input;
	    }
	}
	public static class NaturalMergeSort<T extends Comparable<T>> implements ISortAlgorithm<T> {
        @Override
        public List<T> sort(List<T> input, Comparator<T> comparator) {
            List<List<T>> runs = identifyRuns(input, comparator);

            while (runs.size() > 1) {
                List<T> result = new ArrayList<>();
                int i = 0;

                while (i < runs.size() - 1) {
                    result.addAll(merge(runs.get(i), runs.get(i + 1), comparator));
                    i += 2;
                }

                if (i == runs.size() - 1) {
                    result.addAll(runs.get(i));
                }

                runs = identifyRuns(result, comparator);
            }

            return runs.get(0);
        }

        private List<List<T>> identifyRuns(List<T> input, Comparator<T> comparator) {
            List<List<T>> runs = new ArrayList<>();
            List<T> currentRun = new ArrayList<>();
            currentRun.add(input.get(0));

            for (int i = 1; i < input.size(); i++) {
                if (comparator.compare(input.get(i), input.get(i - 1)) >= 0) {
                    currentRun.add(input.get(i));
                } else {
                    runs.add(currentRun);
                    currentRun = new ArrayList<>();
                    currentRun.add(input.get(i));
                }
            }

            runs.add(currentRun);
            return runs;
        }

        private List<T> merge(List<T> left, List<T> right, Comparator<T> comparator) {
            List<T> result = new ArrayList<>();
            int leftIndex = 0, rightIndex = 0;

            while (leftIndex < left.size() && rightIndex < right.size()) {
                if (comparator.compare(left.get(leftIndex), right.get(rightIndex)) <= 0) {
                    result.add(left.get(leftIndex));
                    leftIndex++;
                } else {
                    result.add(right.get(rightIndex));
                    rightIndex++;
                }
            }

            while (leftIndex < left.size()) {
                result.add(left.get(leftIndex));
                leftIndex++;
            }

            while (rightIndex < right.size()) {
                result.add(right.get(rightIndex));
                rightIndex++;
            }

            return result;
        }
    }
	// Implementa los demás algoritmos de ordenamiento de manera similar.
	public static class StraightMergeSort<T> implements ISortAlgorithm<T> {
	    @Override
	    public List<T> sort(List<T> input, Comparator<T> comparator) {
	        return mergeSort(input, comparator);
	    }

	    private List<T> mergeSort(List<T> input, Comparator<T> comparator) {
	        if (input.size() <= 1) {
	            return input;
	        }

	        int middle = input.size() / 2;
	        List<T> left = input.subList(0, middle);
	        List<T> right = input.subList(middle, input.size());

	        left = mergeSort(left, comparator);
	        right = mergeSort(right, comparator);

	        return merge(left, right, comparator);
	    }

	    private List<T> merge(List<T> left, List<T> right, Comparator<T> comparator) {
	        List<T> result = new java.util.ArrayList<>();
	        int leftIndex = 0, rightIndex = 0;

	        while (leftIndex < left.size() && rightIndex < right.size()) {
	            if (comparator.compare(left.get(leftIndex), right.get(rightIndex)) <= 0) {
	                result.add(left.get(leftIndex));
	                leftIndex++;
	            } else {
	                result.add(right.get(rightIndex));
	                rightIndex++;
	            }
	        }

	        result.addAll(left.subList(leftIndex, left.size()));
	        result.addAll(right.subList(rightIndex, right.size()));

	        return result;
	    }
	}
	public static class HeapSort<T> implements ISortAlgorithm<T> {
	    @Override
	    public List<T> sort(List<T> input, Comparator<T> comparator) {
	  
	        int n = input.size();

	        for (int i = n / 2 - 1; i >= 0; i--) {
	            Heapify(input, n, i, comparator);
	        }

	        for (int i = n - 1; i >= 0; i--) {
	            T temp = input.get(0);
	            input.set(0, input.get(i));
	            input.set(i, temp);

	            Heapify(input, i, 0, comparator);
	        }

	        return input;
	    }

	    private void Heapify(List<T> arr, int n, int i, Comparator<T> comparator) {
	        int largest = i;
	        int left = 2 * i + 1;
	        int right = 2 * i + 2;

	        if (left < n && comparator.compare(arr.get(left), arr.get(largest)) > 0) {
	            largest = left;
	        }

	        if (right < n && comparator.compare(arr.get(right), arr.get(largest)) > 0) {
	            largest = right;
	        }

	        if (largest != i) {
	            T swap = arr.get(i);
	            arr.set(i, arr.get(largest));
	            arr.set(largest, swap);

	            Heapify(arr, n, largest, comparator);
	        }
	    }
	}
	public static class QuickSort<T> implements ISortAlgorithm<T> {
	    @Override
	    public List<T> sort(List<T> input, Comparator<T> comparator) {
	
	        QuickSortRecursive(input, 0, input.size() - 1, comparator);
	        return input;
	    }

	    private void QuickSortRecursive(List<T> arr, int low, int high, Comparator<T> comparator) {
	        if (low < high) {
	            int partitionIndex = Partition(arr, low, high, comparator);

	            QuickSortRecursive(arr, low, partitionIndex - 1, comparator);
	            QuickSortRecursive(arr, partitionIndex + 1, high, comparator);
	        }
	    }

	    private int Partition(List<T> arr, int low, int high, Comparator<T> comparator) {
	        T pivot = arr.get(high);
	        int i = low - 1;

	        for (int j = low; j < high; j++) {
	            if (comparator.compare(arr.get(j), pivot) < 0) {
	                i++;
	                T temp = arr.get(i);
	                arr.set(i, arr.get(j));
	                arr.set(j, temp);
	            }
	        }

	        T temp2 = arr.get(i + 1);
	        arr.set(i + 1, arr.get(high));
	        arr.set(high, temp2);

	        return i + 1;
	    }
	}	
	public static class BubbleSort<T> implements ISortAlgorithm<T> {
	    @Override
	    public List<T> sort(List<T> input, Comparator<T> comparator) {
	
	        int n = input.size();

	        for (int i = 0; i < n - 1; i++) {
	            for (int j = 0; j < n - i - 1; j++) {
	                if (comparator.compare(input.get(j), input.get(j + 1)) > 0) {
	                    T temp = input.get(j);
	                    input.set(j, input.get(j + 1));
	                    input.set(j + 1, temp);
	                }
	            }
	        }

	        return input;
	    }
	}
	public static class CocktailSort<T> implements ISortAlgorithm<T> {
	    @Override
	    public List<T> sort(List<T> input, Comparator<T> comparator) {
	
	        int n = input.size();
	        boolean swapped;

	        do {
	            swapped = false;

	            for (int i = 0; i < n - 1; i++) {
	                if (comparator.compare(input.get(i), input.get(i + 1)) > 0) {
	                    T temp = input.get(i);
	                    input.set(i, input.get(i + 1));
	                    input.set(i + 1, temp);
	                    swapped = true;
	                }
	            }

	            if (!swapped)
	                break;

	            swapped = false;

	            for (int i = n - 2; i >= 0; i--) {
	                if (comparator.compare(input.get(i), input.get(i + 1)) > 0) {
	                    T temp = input.get(i);
	                    input.set(i, input.get(i + 1));
	                    input.set(i + 1, temp);
	                    swapped = true;
	                }
	            }

	        } while (swapped);

	        return input;
	    }
	}
	public static class InsertionSort<T> implements ISortAlgorithm<T> {
	    @Override
	    public List<T> sort(List<T> input, Comparator<T> comparator) {
	
	        int n = input.size();

	        for (int i = 1; i < n; i++) {
	            T key = input.get(i);
	            int j = i - 1;

	            while (j >= 0 && comparator.compare(input.get(j), key) > 0) {
	                input.set(j + 1, input.get(j));
	                j = j - 1;
	            }

	            input.set(j + 1, key);
	        }

	        return input;
	    }
	}
	public static class BucketSort<T> implements ISortAlgorithm<T> {
	    @Override
	    public List<T> sort(List<T> input, Comparator<T> comparator) {
	
	        if (input.isEmpty())
	            return input;

	        // Encuentra el valor máximo y mínimo en la lista
	        T minValue = input.get(0);
	        T maxValue = input.get(0);

	        for (T item : input) {
	            if (comparator.compare(item, minValue) < 0)
	                minValue = item;

	            if (comparator.compare(item, maxValue) > 0)
	                maxValue = item;
	        }

	        // Inicializa los baldes
	        List<List<T>> buckets = new ArrayList<>();

	        // Crea los baldes
	        for (int i = 0; i < input.size(); i++) {
	            buckets.add(new ArrayList<>());
	        }

	        // Distribuye los elementos en los baldes
	        for (T item : input) {
	            int bucketIndex = input.indexOf(item);
	            buckets.get(bucketIndex).add(item);
	        }

	        // Ordena cada balde e inserta los elementos ordenados en la lista final
	        List<T> sortedList = new ArrayList<>();
	        for (List<T> bucket : buckets) {
	            if (!bucket.isEmpty()) {
	                bucket.sort(comparator);
	                sortedList.addAll(bucket);
	            }
	        }

	        return sortedList;
	    }
	}
	public static class CountingSort<T> implements ISortAlgorithm<T> {
	    @Override
	    public List<T> sort(List<T> input, Comparator<T> comparator) {
	

	        // Encuentra el valor máximo en la lista
	        T maxVal = input.get(0);
	        for (T item : input) {
	            if (comparator.compare(item, maxVal) > 0) {
	                maxVal = item;
	            }
	        }

	        // Crea un diccionario para contar la frecuencia de cada elemento
	        Map<T, Integer> count = new HashMap<T, Integer>();

	        // Llena el diccionario de conteo
	        for (T item : input) {
	            count.put(item, count.getOrDefault(item, 0) + 1);
	        }

	        // Reconstruye el array ordenado utilizando el diccionario de conteo
	        List<T> sortedList = new ArrayList<>();
	        for (Map.Entry<T, Integer> entry : count.entrySet()) {
	            for (int j = 0; j < entry.getValue(); j++) {
	                sortedList.add(entry.getKey());
	            }
	        }

	        return sortedList;
	    }
	}	
	public static class MergeSort<T> implements ISortAlgorithm<T> {
	    @Override
	    public List<T> sort(List<T> input, Comparator<T> comparator) {
	
	        if (input.size() <= 1) {
	            return input;
	        }

	        int middle = input.size() / 2;
	        List<T> left = input.subList(0, middle);
	        List<T> right = input.subList(middle, input.size());

	        left = sort(left, comparator);
	        right = sort(right, comparator);

	        return merge(left, right, comparator);
	    }

	    private List<T> merge(List<T> left, List<T> right, Comparator<T> comparator) {
	        List<T> result = new ArrayList<>();
	        int leftIndex = 0, rightIndex = 0;

	        while (leftIndex < left.size() && rightIndex < right.size()) {
	            if (comparator.compare(left.get(leftIndex), right.get(rightIndex)) <= 0) {
	                result.add(left.get(leftIndex));
	                leftIndex++;
	            } else {
	                result.add(right.get(rightIndex));
	                rightIndex++;
	            }
	        }

	        result.addAll(left.subList(leftIndex, left.size()));
	        result.addAll(right.subList(rightIndex, right.size()));

	        return result;
	    }
	}

	
	public class BinaryTreeSort<TNode extends Comparable<TNode>> implements ISortAlgorithm<TNode> {
	    private Node root;

	    @Override
	    public List<TNode> sort(List<TNode> input, Comparator<TNode> comparator) {
	        for (TNode item : input) {
	            root = insertRec(root, item);
	        }

	        List<TNode> sortedList = new ArrayList<>();
	        inOrderTraversalRec(root, sortedList::add);

	        return sortedList;
	    }

	    private Node insertRec(Node root, TNode value) {
	        if (root == null) {
	            return new Node(value);
	        }

	        if (value.compareTo(root.value) < 0) {
	            root.left = insertRec(root.left, value);
	        } else if (value.compareTo(root.value) > 0) {
	            root.right = insertRec(root.right, value);
	        }

	        return root;
	    }

	    private void inOrderTraversalRec(Node root, java.util.function.Consumer<TNode> action) {
	        if (root != null) {
	            inOrderTraversalRec(root.left, action);
	            action.accept(root.value);
	            inOrderTraversalRec(root.right, action);
	        }
	    }

	    private class Node {
	        private TNode value;
	        private Node left;
	        private Node right;

	        private Node(TNode value) {
	            this.value = value;
	        }
	    }
	}
	public static class RadixSort<T> implements ISortAlgorithm<T> {
	    @Override
	    public List<T> sort(List<T> input, Comparator<T> comparator) {
		        int maxLevelLength = getMaxLevelLength(input);

	        for (int digitPlace = 1; digitPlace <= maxLevelLength; digitPlace++) {
	            countingSortByLevel(input, digitPlace, comparator);
	        }

	        return input;
	    }

	    private void countingSortByLevel(List<T> input, int digitPlace, Comparator<T> comparator) {
	        int n = input.size();

	        Map<Integer, List<T>> count = new HashMap<>();

	        for (T item : input) {
	            int level = getLevel(item, digitPlace, comparator);
	            count.computeIfAbsent(level, k -> new ArrayList<>()).add(item);
	        }

	        List<T> sortedList = new ArrayList<>();
	        for (Map.Entry<Integer, List<T>> entry : count.entrySet()) {
	            entry.getValue().sort(comparator);
	            sortedList.addAll(entry.getValue());
	        }

	        for (int i = 0; i < n; i++) {
	            input.set(i, sortedList.get(i));
	        }
	    }

	    private int getMaxLevelLength(List<T> input) {
	        int maxLevelLength = 0;

	        for (T item : input) {
	            int levelLength = getLevelLength(item);
	            if (levelLength > maxLevelLength) {
	                maxLevelLength = levelLength;
	            }
	        }

	        return maxLevelLength;
	    }

	    private int getLevelLength(T item) {
	        return item.toString().length();
	    }

	    private int getLevel(T item, int digitPlace, Comparator<T> comparator) {
	        // Your logic to extract the digit at the specified place using comparator
	        // ...

	        return 0;
	    }
	}
	public static class GnomeSort<T> implements ISortAlgorithm<T> {
	    @Override
	    public List<T> sort(List<T> input, Comparator<T> comparator) {
	
	        int n = input.size();
	        int index = 0;

	        while (index < n) {
	            if (index == 0)
	                index++;

	            if (comparator.compare(input.get(index), input.get(index - 1)) >= 0)
	                index++;
	            else {
	                swap(input, index, index - 1);
	                index--;
	            }
	        }

	        return input;
	    }

	    private void swap(List<T> input, int i, int j) {
	        T temp = input.get(i);
	        input.set(i, input.get(j));
	        input.set(j, temp);
	    }
	}


	
	
	public class ArmyManagement {
	    public static void main(String[] args) {
	        boolean continuar = true;
	        Scanner scanner = new Scanner(System.in);

	        do {
	            Game.runGame();
	            System.out.println("¿Deseas usar otro? (y/n)");
	            String respuesta = scanner.nextLine().toLowerCase();

	            if (respuesta.equals("n")) {
	                continuar = false;
	            } else if (!respuesta.equals("y")) {
	                System.out.println("Por favor, escribe 'y' para sí o 'n' para no.");
	            }

	        } while (continuar);

	        // Close the Scanner to avoid the resource leak
	        scanner.close();
	    }
	}

	class Game {
	    public static void runGame() {
	        List<Unit> army = generateArmy();

	        System.out.println("Ejército Original:");
	        printArmy(army);

	        ISortAlgorithm<Unit> sortingAlgorithm = null;

	        while (sortingAlgorithm == null) {
	        	 System.out.println("Elige un algoritmo de ordenamiento:");
	        	    System.out.println("1. Shell Sort");
	        	    System.out.println("2. Selection Sort");
	        	    System.out.println("3. Straight Merge Sort");
	        	    System.out.println("4. Heap Sort");
	        	    System.out.println("5. Quick Sort");
	        	    System.out.println("6. Bubble Sort");
	        	    System.out.println("7. Cocktail Sort");
	        	    System.out.println("8. Insertion Sort");
	        	    System.out.println("9. Bucket Sort");
	        	    System.out.println("10. Counting Sort");
	        	    System.out.println("11. Merge Sort");
	        	    System.out.println("12. Binary Tree Sort");
	        	    System.out.println("13. Radix Sort");
	        	    System.out.println("14. Gnome Sort");

	            Scanner scanner = new Scanner(System.in);
	            if (scanner.hasNextInt()) {
	                int choice = scanner.nextInt();

	                if (choice >= 1 && choice <= 14) { // Actualiza con las opciones reales
	                    sortingAlgorithm = getSortingAlgorithm(choice);

	                    if (sortingAlgorithm != null) {
	                        army = sortingAlgorithm.sort(army, Comparator.naturalOrder());

	                        System.out.println("\nEjército Ordenado:");
	                        printArmy(army);
	                    } else {
	                        System.out.println("Opción no válida.");
	                    }
	                } else {
	                    System.out.println("Por favor, ingresa una opción válida (1-2).");
	                }
	            } else {
	                System.out.println("Entrada inválida. Ingresa un número entero válido.");
	            }
	        }
	    }

	    static ISortAlgorithm<Unit> getSortingAlgorithm(int choice) {
	        switch (choice) {
	            case 1:
	                return new ShellSort<>();
	            case 2:
	                return new SelectionSort<>();            
	            	 case 3:
	                return new StraightMergeSort<>();
	            case 4:
	                return new HeapSort<>();
	            case 5:
	                return new QuickSort<>();
	            case 6:
	                return new BubbleSort<>();
	            case 7:
	                return new CocktailSort<>();
	            case 8:
	                return new InsertionSort<>();
	            case 9:
	                return new BucketSort<>();
	            case 10:
	                return new CountingSort<>();
	            case 11:
	                return new MergeSort<>();
	            case 12:
	                Consola consolaInstance = new Consola(); // Crea una instancia de Consola
	                return consolaInstance.new BinaryTreeSort<>(); // Crea una instancia de BinaryTreeSort usando la instancia de Consola

	            case 13:
	                return new RadixSort<>();
	            case 14:
	                return new GnomeSort<>();
	            // Add more cases for additional sorting algorithms as needed
	            default:
	                return null;
	        }
	    }


	    static void printArmy(List<Unit> army) {
	        for (Unit unit : army) {
	            System.out.println(unit);
	        }
	    }
	    static List<Unit> generateArmy() {
	        Random random = new Random();
	        List<Unit> army = new ArrayList<>();
	        Consola consola = new Consola(); // Crea una instancia de Consola
	        int numUnits = 5;

	        for (int i = 0; i < numUnits; i++) {
	            army.add(consola.new Unit("Unit" + (i + 1), random.nextInt(10) + 1, random.nextInt(21) + 10, random.nextInt(16) + 5));
	        }

	        return army;
	    }
	}

	class Unit implements Comparable<Unit> {
	    private String name;
	    private int level;
	    private int attackPower;
	    private int speed;

	    public Unit(String name, int level, int attackPower, int speed) {
	        this.name = name;
	        this.level = level;
	        this.attackPower = attackPower;
	        this.speed = speed;
	    }

	    @Override
	    public int compareTo(Unit other) {
	
	        int levelComparison = Integer.compare(this.level, other.level);
	        if (levelComparison != 0) {
	            return levelComparison;
	        }

	        return Integer.compare(this.speed, other.speed);
	    }

	    @Override
	    public String toString() {
	        return name + " (Nivel: " + level + ", Ataque: " + attackPower + ", Velocidad: " + speed + ")";
	    }
	}
}
