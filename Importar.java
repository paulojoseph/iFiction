import java.util.HashMap;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Importar {

	public static HashMap<String, Sala> criarSalas(HashMap<String, Item> itens, HashMap<String, Objeto> objetos) {
    
		try {

      //inicializa o objeto leitor, utilizando a classe BufferedReader para capturar a informação presente no arquivo Salas.txt | Assim fica mais fácil compilar a informação em um arquivo externo e apenas fazer a importação aqui
			BufferedReader leitor = new BufferedReader(new FileReader("Salas.txt"));

      //cria um HashMap para armazenar as informações das Salas
			HashMap<String, Sala> Salas = new HashMap<String, Sala>();

      //objeto que servirá de guia para ler uma linha específica no arquivo .txt
			String linha = leitor.readLine();

      //loop parar ler as linhas do arquivo txt
			while (linha != null) {

				String nome = linha;
				linha = leitor.readLine();

        //divide as informações das fronteiras do txt por vírgula
				String[] fronteira = linha.split(",");
				for (int i = 0; i < fronteira.length; i++) {
          
					fronteira[i] = fronteira[i].trim();
				}

				linha = leitor.readLine();
				String descricao = "";

        //loop para ler até encontrar o comando FIM no arquivo txt
				while (!linha.equals("FIM")) { 
          //todas as linhas após a informação da fronteira serão adicionadas ao objeto 'descricao' e fazem parte da descrição da sala
					descricao = descricao + linha + '\n';
					linha = leitor.readLine();
				}

				//adiciona a sala ao HashMap
				Salas.put(nome, new Sala(nome, descricao, fronteira, itens, objetos));

				linha = leitor.readLine();

			}

			return Salas;

		} catch (IOException e) {
      //tratamento de erro para saber se o arquivo teve algum problema no carregamento
			System.out.println("O arquivo Salas.txt não pode ser encontrado, tente novamente!");
		}
		return null;
	}

  //cria um HashMap para armazenar as informações dos Itens
	public static HashMap<String, Item> criarItens() {
		try {

			BufferedReader leitor = new BufferedReader(new FileReader("Itens.txt"));
			String linha = leitor.readLine();
			HashMap<String, Item> itens = new HashMap<String, Item>();

			while (linha != null) {

				String nome = linha;
				nome = nome.toLowerCase();
				linha = leitor.readLine();
				String local = linha.trim();
				linha = leitor.readLine();
				String descricao = "";

				while (!linha.equals("FIM")) {

					descricao = descricao + linha + '\n';
					linha = leitor.readLine();
				}

				itens.put(nome, new Item(nome, descricao, local));

				linha = leitor.readLine(); 

			}

			return itens;
		} catch (IOException e) {
			System.out.println("O arquivo Itens.txt não pode ser encontrado, tente novamente!");
		}
		return null;
	}

  //cria um HashMap para armazenar as informações dos Objetos
	public static HashMap<String, Objeto> criarObjetos() {
		try {

			BufferedReader leitor = new BufferedReader(new FileReader("Objetos.txt"));
			String linha = leitor.readLine();
			HashMap<String, Objeto> objetos = new HashMap<String, Objeto>();

			while (linha != null) {

				String nome = linha;
				nome = nome.toLowerCase();
				linha = leitor.readLine();

				String local = linha.trim();
				linha = leitor.readLine();
        
				String descricao = "";

				while (!linha.equals("FIM")) {

					descricao = descricao + linha + '\n';
					linha = leitor.readLine();
				}

				objetos.put(nome, new Objeto(nome, descricao, local));

				linha = leitor.readLine(); 

			}

			return objetos;
		} catch (IOException e) {
			System.out.println("O arquivo Objetos.txt não pode ser encontrado, tente novamente!");
		}
		return null;
	}


  //cria um HashMap para armazenar as informações dos Pontos
	public static HashMap<String, Pontos> criarPontos() {
		try {
			BufferedReader leitor = new BufferedReader(new FileReader("Pontos.txt"));
			String linha = leitor.readLine();
			HashMap<String, Pontos> pontos = new HashMap<String, Pontos>();

			while (linha != null) {
        
				String[] temp = linha.split(",");

        //loop para o array
				for (int i = 0; i < temp.length; i++) {

					//ajusta as informações
					temp[i] = temp[i].trim();
				}

				String id = "";
        //verifica no início do Pontos.txt se a primeira descrição se refere a visitar aluma sala. Caso verdadeiro, é necessário adicionar a pontuação informada
				if (temp[0].equals("IR")) {
					//id vai armazenar a informação da sala acessada e pontuação
					id = temp[0] + " " + temp[3];
				} else {
					//id vai armazenar a informação por pegar algum item
					id = temp[0] + " " + temp[1];
				}
				id = id.toLowerCase();

				//adiciona os pontos e id à HashMap
				pontos.put(id, new Pontos(temp[0], temp[1], temp[2], temp[3], Integer.parseInt(temp[4])));

				linha = leitor.readLine();
			}
			return pontos;
		} catch (IOException e) {
			System.out.println("O arquivo Pontos.txt não pode ser acessado, tente novamente!");
		}
		return null;
	}

}
