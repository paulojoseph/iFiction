import java.util.HashMap;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
    

    Jogador inicio = new Jogador();
    Jogador ajuda = new Jogador();
		Jogador jogador = new Jogador();

		HashMap<String, Item> itens = Importar.criarItens();
		HashMap<String, Objeto> objetos = Importar.criarObjetos();
		HashMap<String, Sala> salas = Importar.criarSalas(itens, objetos);
		HashMap<String, Pontos> pontos = Importar.criarPontos();

    //instancia o objeto que captura os inputs
		Scanner tc = new Scanner(System.in);

		//System.out.println(salas.get(jogador.getLocal()).getDescricao());

    inicio.comecoJogo();

		while(true) {

			//instancia o objeto que armazena o valor do input
			String input = tc.nextLine();
      //trata o input para minúsculas
			input = input.toLowerCase();
      //instancia o objeto que servirá para armazenar comandos compostos, com mais de uma palavra 
			String[] comando = input.split(" ");

			//if que vai identificara qual comando foi digitado pelo usuário.



        //caso o jogador digite o comando ver
      
			if (comando[0].equals("ver")) {
				//caso o jogador deseje ver um objeto
				if (comando.length >= 2) {
          //cria um objeto string para armazenar o texto digitado pelo jogador
					String temp = "";
					
					for (int i = 1; i < comando.length; i++) {
						temp = temp + comando[i] + " ";
					}

          temp = temp.trim();
        
          try {
            jogador.ver(temp);
          } catch(Exception e) {
          }
          
          try {
            jogador.verObj(temp);
          } catch(Exception e) {
            System.out.println("\nNão existe nada com esse nome para ser visto\n");
          }

				} else if (comando.length == 1) {
					jogador.ver(salas);
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

					jogador.addItem(temp, salas);
          jogador.addObjeto(temp, salas);

					//verifica se o jogador pode pegar o item
					jogador.verPontos(pontos, "pegar " + temp);

				} else {
					System.out.println(comando[1] + " não existe, tente novamente!");
				}

			} else if (comando[0].equals("usar")) { // jogador wants to usar
				// making sure input has comando and item
				if (comando.length >= 2) {
					String temp = "";
					// create string of object name
					for (int i = 1; i < comando.length; i++) {
						temp = temp + comando[i] + " ";
					}
					temp = temp.trim();
					jogador.usar(temp, salas);

					// check if we meet goal for dropping object
					//first check if we would be dropping in correct room...
					if (pontos.get("usar " + temp).getLocal().equals(jogador.getLocal())) {
						//if so...
						jogador.verPontos(pontos, "usar " + temp);
					}
        }
			} else if (comando[0].equals("norte") || comando[0].equals("sul") || comando[0].equals("leste") || comando[0].equals("oeste")) {

        if(jogador.getPontos() < 20 && jogador.getLocal().equals("Lab") && comando[0].equals("norte")) {

          System.out.println("\nVocê não possui autorização para utilizar a porta!\n");
          
        } else {
				jogador.ir(comando[0], salas);
				System.out.println(salas.get(jogador.getLocal()).getDescricao());
        }

			} else if (comando.length == 1) {

				if (comando[0].equals("pontuacao")) {

					System.out.println(jogador.getPontos());

				} else if (comando[0].equals("inventario")) { 
					jogador.getInventario();

				} else if (comando[0].equals("sair")) {
					System.out.println("Obrigado por jogar, volte logo!");
					System.exit(0);
				} else if (comando[0].equals("ajuda")) {
          ajuda.ajudaJogo();
      } else {
				System.out.println("Comando inválido, tente novamente!");
			}

			//verificar se o jogador possui as credenciais para entrar na próxima sala
			jogador.verPontos(pontos, "ir " + jogador.getLocal().toLowerCase());

			//se todos os objetivos forem alcaçados
			if (pontos.isEmpty() && jogador.getLocal().equals("Corredor")) {
				System.out.println("Parabéns, você chegou ao final e salvou a humanidade! ");
				System.out.println("Sua pontuação final foi " + jogador.getPontos());
				System.exit(0);
			} else if (pontos.isEmpty() && !jogador.getLocal().equals("Lab")) {

				System.out.println("Você já completou os objetivos mas ainda não chegou no final...");
			}
		}
	}
}
}
