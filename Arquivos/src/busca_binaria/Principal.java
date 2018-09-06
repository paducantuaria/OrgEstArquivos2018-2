package busca_binaria;

import java.io.RandomAccessFile;
import java.util.Scanner;

public class Principal {

	public static void main(String[] args) throws Exception {
		
		RandomAccessFile f = new RandomAccessFile("Apoio//cep_ordenado.dat", "r");
		Scanner scan = new Scanner(System.in);
		System.out.println("Digite o CEP que deseja buscar: ");
		String cep = scan.nextLine();
		scan.close();
		buscaBinariaCEP(f, cep);		
	}

	public static void imprimeEndereco(Endereco e, long passos) {
		System.out.println("Endereço Completo:");
		System.out.println(e.getLogradouro());
		System.out.println(e.getBairro());
		System.out.println(e.getCidade());
		System.out.println(e.getEstado());
		System.out.println(e.getSigla());
		System.out.println(e.getCep());
		System.out.println();
		System.out.println(passos + " passos");
	}
	
	public static void buscaBinariaCEP(RandomAccessFile f, String cep) throws Exception {
		
		Endereco e = new Endereco();
		long inicio = 0;
		long meio = 0;
		long fim = (f.length() / e.getTamanhoRegistro()) - 1;
		long passos = 0;

		while (inicio <= fim) {
			passos++;
			meio = (inicio + fim) / 2;
			f.seek(meio * e.getTamanhoRegistro());
			e.leEndereco(f);

			if (e.getCep().equals(cep)) {
				imprimeEndereco(e, passos);
				break;
			} else if (e.getCep().compareTo(cep) > 0) {
				fim = meio - 1;
			} else {
				inicio = meio + 1;
			}
		}
		if (inicio > fim) {
			System.out.println("Não foi possível encontrar o endereço buscado.");
			System.out.println(passos);
		}
		f.close();
	}
}
