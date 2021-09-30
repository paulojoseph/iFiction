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

	public Sala(String nome, String descricao, String[] fronteira, HashMap<String, Item> itens) {
		nome_ = nome;
		descricao_ = descricao;
		fronteira_ = fronteira;
		
		setItens(itens);
	}

	public void setItens(HashMap<String, Item> itens) {
		for (Map.Entry<String, Item> elt : itens.entrySet()) { //itera a lista de itens
			//se o item pertence à sala
			if (elt.getValue().getLocal().equals(nome_)) {
				//adiciona o item à sala
				itens_.put(elt.getKey(), elt.getValue());
			}
		}
	}

	public void olhar() {
		System.out.println(descricao_);

		System.out.println("Pode sair para... ");

		if (!fronteira_[0].equals("-")) {

			System.out.print("NORTE, ");
		}

		if (!fronteira_[1].equals("-")) {

			System.out.print("SUL, ");
		}

		if (!fronteira_[2].equals("-")) {

			System.out.print("LESTE, ");
		}

		if (!fronteira_[3].equals("-")) {

			System.out.print("OESTE, ");
		}

		System.out.println();
		System.out.println();

		if (itens_.isEmpty()) {

			System.out.println("Não há itens na sala.");
      
		} else {
			for (Map.Entry<String, Item> elt : itens_.entrySet()) {

				System.out.print(elt.getKey() + ", ");
			}
			System.out.println(" estão na sala.");
		}

	}

  //captura salas adjacentes
	public String[] getFronteiras() {
		return fronteira_;
	}

  //captura nome da sala
	public String getNome() {
		return nome_;
	}

  //captura informação de itens da sala
	public HashMap<String, Item> getInventario() {
		return itens_;
	}

  //captura descrição da sala
	public String getDescricao() {
		return descricao_;
	}
		
}
