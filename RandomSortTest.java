package tests;

import java.util.Arrays;
import java.util.Random;


/**
 * Algoritmo criado com intuito de testar automaticamente de forma aleat�ria meus algoritmos de ordena��o
 * para os roteiros de LEDA-UFCG.
 * N�o s�o testadas entradas inv�lidas para left e right index!
 * @author github.com/lucasgsa
 *
 */
public class RandomSortTest {
	
	static boolean testarNegativos = true;
	static int rangeRandom = 101;
	static int quantidadeTestesPorN = 5;
	static int tamanhoMaximoN = 200;
	
	public static void getSorted(Integer[] array,int leftIndex, int rightIndex) {
		// Instancie o algoritmo aqui.
		AbstractSorting<Integer> sorteador = null;
		sorteador.sort(array, leftIndex, rightIndex);
	}
	
	public static void main(String[] args) {
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
				try {
					getSorted(arrayCopia1, i, j);
				}
				catch (Exception e) {
					System.out.println("Ocorreu um erro ao tentar ordenar o array.");
					System.out.println("Saida: "+Arrays.toString(arrayCopia1));
					System.out.println("Esperado: "+Arrays.toString(arrayCopia2));
					System.out.println("De leftIndex: "+i+" At� rightIndex(Incluso): "+j);
					return false;
				}
				Arrays.sort(arrayCopia2, i, j+1);
				if (!Arrays.equals(arrayCopia1, arrayCopia2)){
					System.out.println("O array n�o foi ordenado corretamente!");
					System.out.println("Saida: "+Arrays.toString(arrayCopia1));
					System.out.println("Esperado: "+Arrays.toString(arrayCopia2));
					System.out.println("De leftIndex: "+i+" At� rightIndex(Incluso): "+j);
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
			if (!testarNegativos || Math.random() < 0.5) {
				array[i] = randomer.nextInt(rangeRandom);
			}
			else {
				array[i] = randomer.nextInt(rangeRandom)*-1;
			}
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
