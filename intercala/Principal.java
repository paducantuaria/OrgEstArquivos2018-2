package intercala;

import java.io.RandomAccessFile;
import java.util.Random;


public class Principal {

	public static void main(String[] args) throws Exception {
		RandomAccessFile f = new RandomAccessFile("Apoio//cep_ordenado.dat", "r");
		
		RandomAccessFile f1 = new RandomAccessFile("Apoio//cep_separado1.dat", "r");
		RandomAccessFile f2 = new RandomAccessFile("Apoio//cep_separado2.dat", "r");
		
		RandomAccessFile fOrdenado = new RandomAccessFile("Apoio//cep_ordenadofinal.dat", "rw");
		
		int registro = 300;
		
		
		// Separando o arquivo em dois:
		long inicio = 0;
		long fim = (f.length() / registro) - 1;
		
		Endereco e = new Endereco();

		Random random = new Random();
		
		while (inicio <= fim) {	
			f.seek(inicio * registro);
			e.leEndereco(f);
			
			if(random.nextBoolean()) {
				e.escreveEndereco(f1);
			}else {
				e.escreveEndereco(f2);
			}
			inicio++;
		}
		System.out.println("Fim da divisão dos Arquivos");

		
		
		// Ordenação 
		long inicioF1 = 0;
		long fimF1 = (f1.length() / registro) - 1;
		long inicioF2 = 0;
		long fimF2 = (f2.length() / registro) - 1;
		
		
		Endereco e1 = new Endereco();
		Endereco e2 = new Endereco();
		
		while(inicioF1 <= fimF1 || inicioF2 <= fimF2) {
			
			if(inicioF1 <= fimF1) {
				f1.seek(inicioF1 * registro);
				e1.leEndereco(f1);
			}
			if(inicioF2 <= fimF2) {
				f2.seek(inicioF2 * registro);
				e2.leEndereco(f2);
			}
			if(e1.getCep().compareTo(e2.getCep()) < 0) {
				if(inicioF1 <= fimF1) {
					e1.escreveEndereco(fOrdenado);
					e2.escreveEndereco(fOrdenado);
				} else if(inicioF2 <= fimF2){
					e2.escreveEndereco(fOrdenado);
				}
			} else if(e1.getCep().compareTo(e2.getCep()) < 0){
				if(inicioF2 <= fimF2) {
						e2.escreveEndereco(fOrdenado);
						e1.escreveEndereco(fOrdenado);
				} else if(inicioF1 <= fimF1) {
						e1.escreveEndereco(fOrdenado);
					}
			}
			inicioF1++;
			inicioF2++;
			
		}
		System.out.println("Fim da ordenação");		
		
		f.close();
		f1.close();
		f2.close();
		fOrdenado.close();

		
		
		
	}

}
