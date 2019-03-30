package graphtheoryjava;

import graphtheoryjava.util.Leitura;
import graphtheoryjava.util.Grafo;
import java.io.IOException;
import java.util.Scanner;
import graphtheoryjava.util.info.Coloracao;
import graphtheoryjava.util.info.Info;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {

		try {
			System.out.println("Arquivo Grafo para Coloracao");

			Texto t = new Texto();
			int opcao;
			String nomeArquivo;
			
			System.out.println("Opcoes de Grafos:");
                        System.out.println("0: toy.txt\n1: queen8_8.txt\n2: miles1000.txt\n3: mulsol.i.1.txt\n4: school1.txt\n5:DSJC1000.5.txt\n");
			Scanner entrada = new Scanner(System.in);
			opcao = entrada.nextInt();
			nomeArquivo = t.Txt(opcao);
			while (nomeArquivo == null) {
                            System.out.println("\n\nGrafo nao existente!\nDigite uma opcao valida\n\n");
                            opcao = entrada.nextInt();
                            nomeArquivo = t.Txt(opcao);
                        }
			
			Leitura leitura = new Leitura();

			Grafo grafo = leitura.leArquivo(nomeArquivo);
			Info F = new Info(grafo);
			Coloracao C = new Coloracao(grafo);
                        
                        ArrayList<Integer> cor = C.Coloracao(grafo);
                        System.out.println("cores:");
                        for(int i=0;i<cor.size();i++){
                            System.out.print(cor.get(i)+" ");
                        }
                        int maior = cor.get(0);
                        for(int i = 0; i < cor.size(); i++){
                            if(maior < cor.get(i)){
                            maior = cor.get(i);
                            }
                        }
                        maior++;
                        
                        System.out.println("\nQuantidade de cores: "+ maior);
                        ArrayList<Integer> copia = new ArrayList();
                        
                        for (int i = 0; i < 5; i++) {
                        System.out.println("\n\n"+ i + "° Renfinamento: ");
                        copia.clear();
                        copia.addAll(cor);
                        copia = C.Refinamento(copia, grafo);

                        System.out.println("cores:");
                        for (int j = 0; j < copia.size(); j++) {
                            System.out.print(copia.get(j) + " ");
                         }

                        maior = copia.get(0);
                        for (int y = 0; y < copia.size(); y++) {
                            if (maior < copia.get(y)) {
                             maior = copia.get(y);
                            }
                        }
                        maior++;
                        System.out.printf("\nQuantidade de cores: " + maior);
                        }

		} catch (IOException e) {
		}
	}
}