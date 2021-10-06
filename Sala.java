import java.util.HashMap;
import java.util.Map;

public class Sala {

  //nome da sala
	private String nome_;
  //nome das salas adjacentes
	private String[] fronteira_;
  //descrição
	private String descricao_;
  //itens da sala
	private HashMap<String, Item> itens_ = new HashMap<String, Item>();
	private HashMap<String, Objeto> objetos_ = new HashMap<String, Objeto>();


	public Sala(String nome, String descricao, String[] fronteira, HashMap<String, Item> itens, HashMap<String, Objeto> objetos) {
		nome_ = nome;
    fronteira_ = fronteira;
		descricao_ = descricao;

		setItens(itens);
    setObjetos(objetos);
	}

	public void setItens(HashMap<String, Item> itens) {
		for (Map.Entry<String, Item> i : itens.entrySet()) { //itera a lista de itens
			//se o item pertence à sala
			if (i.getValue().getLocal().equals(nome_)) {
				//adiciona o item à sala
				itens_.put(i.getKey(), i.getValue());
			}
		}
	}

	public void setObjetos(HashMap<String, Objeto> objetos) {
		for (Map.Entry<String, Objeto> j : objetos.entrySet()) { //itera a lista de itens
			//se o item pertence à sala
			if (j.getValue().getLocal().equals(nome_)) {
				//adiciona o item à sala
				objetos_.put(j.getKey(), j.getValue());

        //preciso fazer um método pra acessar corretamente essa variável
        Jogador.objeto_.put(j.getKey(), j.getValue());

			}
		}
	}

	public void ver() {
		System.out.println(descricao_);

		System.out.println("Há saídas disponíveis em... ");

		if (!fronteira_[0].equals("-")) {

			System.out.print("| NORTE |");
		}

		if (!fronteira_[1].equals("-")) {

			System.out.print("| SUL |");
		}

		if (!fronteira_[2].equals("-")) {

			System.out.print("| LESTE |");
		}

		if (!fronteira_[3].equals("-")) {

			System.out.print("| OESTE |");
		}

		System.out.println();
		System.out.println();

		if (itens_.isEmpty()) {
			System.out.println("Não há itens na sala.");
		} else {
			System.out.println("Há itens nessa sala.");
		}

	}

  //captura nome da sala
	public String getNome() {
		return nome_;
	}

  //captura salas adjacentes
	public String[] getFronteiras() {
		return fronteira_;
	}
  
  //captura descrição da sala
	public String getDescricao() {
		return descricao_;
	}

  //captura informação de itens da sala
	public HashMap<String, Item> getInventario() {
		return itens_;
	}

  //captura informação de objetos da sala
	public HashMap<String, Objeto> getObjetos() {
		return objetos_;
	}

}
