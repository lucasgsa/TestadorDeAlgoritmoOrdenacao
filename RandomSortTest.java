package tests;

import java.util.Arrays;
import java.util.Random;

import org.junit.Assert;

import sorting.AbstractSorting;
import sorting.simpleSorting.BubbleSort;
import sorting.simpleSorting.InsertionSort;
import sorting.simpleSorting.SelectionSort;
import sorting.variationsOfBubblesort.BidirectionalBubbleSort;
import sorting.variationsOfBubblesort.RecursiveBubbleSort;
import sorting.variationsOfSelectionsort.RecursiveSelectionSort;


/**
 * Algoritmo criado com intuito de testar automaticamente de forma aleat�ria meus algoritmos de ordena��o
 * para o R01 de LEDA-UFCG.
 * @author github.com/lucasgsa
 *
 */
public class RandomSortTest {
	
	public static void getSorted(Integer[] array,int leftIndex, int rightIndex) {
		AbstractSorting<Integer> sorteador = new BubbleSort<>();
		sorteador.sort(array, leftIndex, rightIndex);
	}
	
	public static void main(String[] args) {
		
		int quantidadeTestesPorN = 2;
		int tamanhoMaximoN = 100;
		int quantidadeTestesFeitos = randomizadorDeTestes(quantidadeTestesPorN, tamanhoMaximoN);
		System.out.println("Quantidade de arrays aleat�rios ordenados com sucesso: "+quantidadeTestesFeitos);
	}
	
	public static int randomizadorDeTestes(int quantidadeTestesPorN, int tamanhoMaximoN) {
		int quantidadeTestesFeitos = 0;
		for (int i = 0; i <= tamanhoMaximoN; i++) {
			for (int j = 0; j < quantidadeTestesPorN; j++) {
				if (!testar(i)) {
					return quantidadeTestesFeitos;
				};
				System.out.println("Tamanho atual do array:"+(i)+" x"+(j+1));
				quantidadeTestesFeitos += 1;
			}
		}
		return quantidadeTestesFeitos;
	}
	
	public static boolean testar(int size) {
		Integer[] array = getArrayAleatorioInteger(size);
		for (int i = 0; i < size; i++) {
			for (int j = i; j < size; j++) {
				Integer[] arrayCopia1 = Arrays.copyOf(array, array.length);
				Integer[] arrayCopia2 = Arrays.copyOf(array, array.length);
				getSorted(arrayCopia1, i, j);
				Arrays.sort(arrayCopia2, i, j+1);
				
				try {
					Assert.assertArrayEquals(arrayCopia1, arrayCopia2);
				}
				catch (AssertionError e) {
					System.out.println(e);
					System.out.println("O array n�o foi ordenado corretamente!");
					System.out.println(Arrays.toString(array));
					return false;
				}
			}
		}
		return true;
	}	
	
	public static Integer[] getArrayAleatorioInteger(int size) {
		Random randomer = new Random();
		Integer[] array = new Integer[size];
		for (int i = 0; i < array.length; i++) {
			array[i] = randomer.nextInt(101);
		}
		return array;
	}
	
	/**
	 * Retorna um inteiro aleat�rio de 0(incluso) at� o par�metro(incluso).
	 * @param bound
	 * @return
	 */
	public static int getRandomInt(int bound) {
		Random randomer = new Random();
		return randomer.nextInt(bound-1);
	}
}
