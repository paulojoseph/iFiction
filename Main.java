import java.util.*;

public class Main {

	public static void main(String[] args) {
    

    Jogador inicio = new Jogador();
    Jogador ajuda = new Jogador();
		Jogador jogador = new Jogador();

		HashMap<String, Item> itens = Importar.criarItens();
		HashMap<String, Sala> salas = Importar.criarSalas(itens);
		HashMap<String, Score> scores = Importar.criarPontuação();

    //instancia o objeto que captura os inputs
		Scanner tc = new Scanner(System.in);

		//System.out.println(salas.get(jogador.getLocation()).getDescription());

    inicio.comecoJogo();
    ajuda.ajudaJogo();

		while(true) {

			//instancia o objeto que armazena o valor do input
			String input = tc.nextLine();
      //trata o input para minúsculas
			input = input.toLowerCase();
      //instancia o objeto que servirá para armazenar comandos compostos, com mais de uma palavra 
			String[] comando = input.split(" ");

			//condicional que verifica se o comando é PEGAR
			if (comando[0].equals("pegar")) {
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
					jogador.checkScores(scores, "pegar " + temp);

				} else {
					System.out.println(comando[1] + " não existe, tente novamente!");
				}

        //caso o jogador digite o comando olhar
			} else if (comando[0].equals("olhar")) {
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
				System.out.println(salas.get(jogador.getLocation()).getDescription());
			} else if (comando[0].equals("norte") || comando[0].equals("sul") || comando[0].equals("leste")
					|| comando[0].equals("oeste")) {

				jogador.ir(comando[0], salas);
				System.out.println(salas.get(jogador.getLocation()).getDescription());
			} else if (comando.length == 1) {

				if (comando[0].equals("pontuacao")) {

					System.out.println(jogador.getScore());
				} else if (comando[0].equals("inventario")) { 
					jogador.getInventory();
				} else if (comando[0].equals("sair")) {
					System.out.println("Obrigado por jogar, volte logo!");
					System.exit(0);
				}
			} else {
				System.out.println("Comando inválido, tente novamente!");
			}

			//verificar se o jogador possui as credenciais para entrar na próxima sala
			jogador.checkScores(scores, "visitar " + jogador.getLocation().toLowerCase());

			//se todos os objetivos forem alcaçados
			if (scores.isEmpty() && jogador.getLocation().equals("Salão Final")) {
				System.out.println("Parabéns, você chegou ao final e salvou a humanidade! ");
				System.out.println("Sua pontuação final foi " + jogador.getScore());
				System.exit(0);
			} else if (scores.isEmpty() && !jogador.getLocation().equals("Salão Final")) {

				System.out.println("Você já completou os objetivos mas ainda não chegou no final...");
			}
		}
	}
}
