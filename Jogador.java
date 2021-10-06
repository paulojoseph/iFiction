import java.util.HashMap;
import java.util.Map;

public class Jogador {

  //captura o nome da sala onde o jogador está
	public static String local_;
  //inventário
	public static Map<String, Item> inventario_ = new HashMap<String, Item>();
  public static Map<String, Objeto> objeto_ = new HashMap<String, Objeto>();
  //pontuação
	private static int pontos_ = 0;

 /*seta o jogador para o cenário inicial
  *** LEMBRAR SEMPRE ATUALIZAR CASO A SALA INICIAL MUDE DE NOME ***/
	public Jogador() {
		local_ = "lab";
	}

  //cabeçalho do jogo
  public void comecoJogo() {

  System.out.println("\n\n\t\t\t\t\t-*- O INÍCIO -*-\n\n");

  System.out.println("... Você abre os olhos, acordando de um longo e profundo sono. Ainda um pouco atordodado, procura entender o que está acontecendo, sem saber por quanto tempo esteve dormindo ...\n\n");

  System.out.println("... Você está em uma cápsula, que provavelmente se abriu um pouco antes do seu despertar. Na lateral, um aviso denuncia: 'CRYONICS'. Tudo fica mais claro agora, você participou de um experimento de criogenia(criônica). Ao sair da cápsula, em meio à penumbra, você identifica o que parece ser um laboratório, muito bagunçado, com vários papeis espalhados pelo chão, um crachá, uma janela e uma porta ao norte...\n");

  System.out.println("<<< Digite 'ajuda' a qualquer momento para exibir os principais comandos >>>\n");

  }
  //tela de ajuda
  public void ajudaJogo() {

    System.out.println("\n\n\t\t\t\t\t\t--- AJUDA ---\n");

    System.out.println("Para auxiliá-lo em sua jornada, os seguintes comandos podem ser executados:\n");

    System.out.println(""
    + "\tVER: Com esse comando você pode ter uma visão geral do ambiente, dos objetos ao redor e possíveis saídas.\n\n"
    + "\tIR: Comando utilizado para se movimentar até outros ambientes, caso seja possível. Você pode digitar 'IR + Norte' ou apenas 'norte', 'sul', 'leste' e 'oeste'.\n\n"
    + "\tPEGAR: Itens estão espalhados pelos ambientes, alguns deles são cruciais para a continuidade da sua jornada. Examine sempre os locais e digite 'PEGAR' + 'nome do item' para adicioná-lo ao inventário.\n\n"
    + "\tUSAR: Utilizar itens que estejam em seu inventário para executar ações em ambientes específicos.\n");

  }

  //cria o método de mover
	public void ir(String direcao, HashMap<String, Sala> salas) {

    //trata o texto para minúsculas
		direcao = direcao.toLowerCase();
    //pega a informação da sala atual
		Sala atual = salas.get(local_);

    //armazena a informação das outras salas que fazem fronteiras com a atual
		String[] temp = atual.getFronteiras();

    if(direcao.equals("norte") || direcao.equals("n")) {
      if (!temp[0].equals("-")) {
          local_ = temp[0];
      } else {
            msgErroFronteira();
      }
        
    } else if(direcao.equals("sul") || direcao.equals("s")) {
        if (!temp[1].equals("-")) {
          local_ = temp[1];
      } else {
          msgErroFronteira();
      }
        
    } else if(direcao.equals("leste") || direcao.equals("l")) {
        if (!temp[2].equals("-")) {
          local_ = temp[2];
        } else {
          msgErroFronteira();
        }

    } else if(direcao.equals("oeste") || direcao.equals("o")) {
        if (!temp[3].equals("-")) {
          local_ = temp[3];
        } else {
          msgErroFronteira();
        }
      
      } else {
        msgErroFronteira();
		  }
	}

  //método que adiciona item ao inventário
	public void addItem(String item, HashMap<String, Sala> salas) {

		//verifica se o objeto está na sua localização atual
		Sala atual = salas.get(local_);

		Item temp = null;

		if (atual.getInventario().containsKey(item)) { 

			temp = atual.getInventario().get(item);

			atual.getInventario().remove(item);
			inventario_.put(temp.getNome(), temp);
			System.out.println(item + " adicionado ao inventário");
		} else {
			System.out.println(item + " não está na sala atual");
		}
	}

	public void addObjeto(String objeto, HashMap<String, Sala> salas) {

		//verifica se o objeto está na sua localização atual
		Sala atual = salas.get(local_);

		Objeto temp = null;

    //Sala testeObj = atual.getObjetos().keySet();

		if (atual.getObjetos().containsKey(objeto)) {

      temp = atual.getObjetos().get(objeto);

      atual.getObjetos().remove(objeto);
      objeto_.put(temp.getNome(), temp);

		}
	}

	public void usar(String item, HashMap<String, Sala> salas) {
		Item temp = null;

		if (inventario_.containsKey(item)) { //verifica se o item está no inventário
			temp = inventario_.get(item);
			inventario_.remove(item);

			Sala atual = salas.get(local_);

			atual.getInventario().put(temp.getNome(), temp);
			System.out.println("\n"+ item + " foi utilizado!\n");
		} else { 
			System.out.println("\nDesculpe, " + item + " não está no seu inventário.\n");
		}
	}

  //método que captura localização atual do jogador
	public String getLocal() {
		return local_;
	}

  //método que lista os itens do inventário do jogador
	public void getInventario() {
		if (inventario_.isEmpty()) {

			System.out.println("\nO inventário está vazio\n");
		} else {
			for (Map.Entry<String, Item> x : inventario_.entrySet()) {
				System.out.println(x.getKey());
			}
		}
	}

  //método que lista os itens do inventário do jogador
	public void getObjetos() {
		if (objeto_.isEmpty()) {

			System.out.println("\nOs objetos estão vazios\n");
		} else {
			for (Map.Entry<String, Objeto> y : objeto_.entrySet()) {
				System.out.println(y.getKey());
			}
		}
	}

  //método que descreve um item do inventário
	public void ver(String item) {
    System.out.println(inventario_.get(item).getDescricao());
	}

  //método que descreve um objeto do inventário
	public void verObj(String objeto) {
    System.out.println(objeto_.get(objeto).getDescricao());
	}

  //método que descreve a sala atual
	public void ver(HashMap<String, Sala> salas) {
		salas.get(local_).ver();
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
  public void msgErroFronteira() {
    System.out.println("\nNão há um caminho disponível nessa direção. Tente novamente!\n");
    
  }

}
