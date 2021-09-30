import java.util.HashMap;
import java.util.Map;

public class Jogador {

  //captura o nome da sala onde o jogador está
	private static String local_;
  //inventário
	private static Map<String, Item> inventory_ = new HashMap<String, Item>();
  //pontuação
	private static int pontos_ = 0;

 //seta o jogador para o cenário inicial
	public Jogador() {
		local_ = "Salão Inicial";
	}

  //cabeçalho do jogo
  public void comecoJogo() {

  System.out.println("\n\n\t\t\t\t\t\t-*- O INÍCIO -*-\n\n");

  System.out.println("... Você abre os olhos, acordando de um longo e profundo sono. Ainda um pouco atordodado, procura entender o que está acontecendo, sem saber por quanto tempo esteve dormindo ...\n\n");

  System.out.println("... Você está em uma cápsula, que provavelmente se abriu um pouco antes do seu despertar. Na lateral, um aviso denuncia: 'CRYONICS'. Tudo fica mais claro agora, você participou de um experimento de criogenia(criônica). Ao sair da cápsula, em meio à penumbra, você identifica o que parece ser um laboratório, muito bagunçado, com vários papeis espalhados pelo chão, uma janela e uma porta(com acesso por cartão)...");

  }
  //tela de ajuda
  public void ajudaJogo() {

    System.out.println("\n\n\t\t\t\t\t\t--- AJUDA ---\n");

    System.out.println("Para auxiliá-lo em sua jornada, os seguintes comandos podem ser executados:\n");

    System.out.println("\tOLHAR: Com esse comando você pode ter uma visão geral do ambiente, dos objetos ao redor e possíveis saídas.\n\n"
    + "\tIR: Comando utilizado para se movimentar até outros ambientes, caso seja possível. Você pode digitar 'IR + Norte' ou apenas 'Norte'. As direções possíveis são 'Norte, Sul, Leste e Oeste'\n\n"
    + "\tPEGAR: Itens estão espalhados pelos ambientes, alguns deles cruciais para a continuidade da sua jornada. Examine sempre os locais e digite 'PEGAR' + 'nome do item' para adicioná-lo ao inventário.\n");

  }

  //cria o método de mover
	public void ir(String direcao, HashMap<String, Sala> salas) {

    //trata o texto para minúsculas
		direcao = direcao.toLowerCase();
    //pega a informação da sala atual
		Sala atual = salas.get(local_);

    //armazena a informação das outras salas que fazem fronteiras com a atual
		String[] temp = atual.getFronteiras();

    switch(direcao) {
    
      case "norte":

        if (!temp[0].equals("-")) {

          local_ = temp[0];
        } else {
          System.out.println("Não há um caminho disponível nessa direção. Tente novamente!");
        }
        break;

      case "sul":

        if (!temp[1].equals("-")) {

          local_ = temp[1];
        } else {
          System.out.println("Não há um caminho disponível nessa direção. Tente novamente!");
        }
        break;

      case "leste":

        if (!temp[2].equals("-")) {

          local_ = temp[2];
        } else {
          System.out.println("Não há um caminho disponível nessa direção. Tente novamente!");
        }
        break;

      case "oeste":

        if (!temp[3].equals("-")) {

          local_ = temp[3];
        } else {
          System.out.println("Não há um caminho disponível nessa direção. Tente novamente!");
        }
        break;
      
      default:

        System.out.println("Não há um caminho disponível nessa direção. Tente novamente!");
		  }
	}

  //método que adiciona item ao inventário
	public void add(String item, HashMap<String, Sala> salas) {

		//verifica se o objeto está na sua localização atual
		Sala atual = salas.get(local_);

		Item temp = null;

		if (atual.getInventario().containsKey(item)) { 

			temp = atual.getInventario().get(item);

			atual.getInventario().remove(item);
			inventory_.put(temp.getNome(), temp);
			System.out.println(item + " adicionado ao inventário");
		} else {
			System.out.println(item + " não está na sala atual");
		}
	}

  //método que captura localização atual do jogador
	public String getLocal() {
		return local_;
	}

  //método que lista os itens do inventário do jogador
	public void getInventario() {
		if (inventory_.isEmpty()) {

			System.out.println("O inventário está vazio");
		} else {
			for (Map.Entry<String, Item> elt : inventory_.entrySet()) {
				System.out.println(elt.getKey());
			}
		}
	}

  //método que descreve um item do inventário
	public void olhar(String item) {
    System.out.println(inventory_.get(item).getDescricao());
	}

  //método que descreve a sala atual
	public void olhar(HashMap<String, Sala> salas) {
		salas.get(local_).olhar();
	}

  //método que captura a pontuação atual
	public int getPontos() {
		return pontos_;
	}
	
	public void verPontos(HashMap<String, Pontos> pontos, String id) {
		if (pontos.containsKey(id)) { 

			pontos_ = pontos_ + pontos.get(id).getPontos();
			pontos.remove(id);
		}
	}

}
