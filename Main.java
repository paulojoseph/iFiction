import java.util.HashMap;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
    

    Jogador inicio = new Jogador();
    Jogador ajuda = new Jogador();
		Jogador jogador = new Jogador();

		HashMap<String, Item> itens = Importar.criarItens();
		HashMap<String, Sala> salas = Importar.criarSalas(itens);
		HashMap<String, Pontos> pontos = Importar.criarPontos();

    //instancia o objeto que captura os inputs
		Scanner tc = new Scanner(System.in);

		//System.out.println(salas.get(jogador.getLocal()).getDescricao());

    inicio.comecoJogo();
    ajuda.ajudaJogo();

		while(true) {

			//instancia o objeto que armazena o valor do input
			String input = tc.nextLine();
      //trata o input para minúsculas
			input = input.toLowerCase();
      //instancia o objeto que servirá para armazenar comandos compostos, com mais de uma palavra 
			String[] comando = input.split(" ");

			//if que vai identificara qual comando foi digitado pelo usuário.



        //caso o jogador digite o comando olhar
      
			if (comando[0].equals("olhar")) {
				//caso o jogador deseje olhar um objeto
				if (comando.length >= 2) {
          //cria um objeto string para armazenar o texto digitado pelo jogador
					String temp = "";
					
					for (int i = 1; i < comando.length; i++) {
						temp = temp + comando[i] + " ";
					}
					temp = temp.trim();
					jogador.olhar(temp);
				} else if (comando.length == 1) {
					jogador.olhar(salas);
				} else {
					System.out.println(comando[1] + "não encontrado, tente novamente!");
				}

			} else if (comando[0].equals("ir")) {

				jogador.ir(comando[1], salas);
				System.out.println(salas.get(jogador.getLocal()).getDescricao());

      } else if (comando[0].equals("pegar")) {
				//verifica se o comando PEGAR está com a sintaxe correta

				if (comando.length >= 2) {
          //inicializa o objeto que armazenará o nome do item digitado pelo usuário
					String temp = "";
					//concatena todas as palavras digitadas pelo usuário e armazena no objeto 'temp'
					for (int i = 1; i < comando.length; i++) {
						temp = temp + comando[i] + " ";
					}
					temp = temp.trim();

					jogador.add(temp, salas);

					//verifica se o jogador pode pegar o item
					jogador.verPontos(pontos, "pegar " + temp);

				} else {
					System.out.println(comando[1] + " não existe, tente novamente!");
				}

			} else if (comando[0].equals("norte") || comando[0].equals("sul") || comando[0].equals("leste") || comando[0].equals("oeste")) {

				jogador.ir(comando[0], salas);
				System.out.println(salas.get(jogador.getLocal()).getDescricao());

			} else if (comando.length == 1) {

				if (comando[0].equals("pontuacao")) {

					System.out.println(jogador.getPontos());
				} else if (comando[0].equals("inventario")) { 
					jogador.getInventario();
				} else if (comando[0].equals("sair")) {
					System.out.println("Obrigado por jogar, volte logo!");
					System.exit(0);
				}
			
      } else {
				System.out.println("Comando inválido, tente novamente!");
			}

			//verificar se o jogador possui as credenciais para entrar na próxima sala
			jogador.verPontos(pontos, "visitar " + jogador.getLocal().toLowerCase());

			//se todos os objetivos forem alcaçados
			if (pontos.isEmpty() && jogador.getLocal().equals("Salão Final")) {
				System.out.println("Parabéns, você chegou ao final e salvou a humanidade! ");
				System.out.println("Sua pontuação final foi " + jogador.getPontos());
				System.exit(0);
			} else if (pontos.isEmpty() && !jogador.getLocal().equals("Salão Final")) {

				System.out.println("Você já completou os objetivos mas ainda não chegou no final...");
			}
		}
	}
}
