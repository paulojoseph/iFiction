public class Item {
  
	private String nome_;
	private String descricao_;
	private String local_; 

	public Item (String nome, String descricao, String local) {
		nome_ = nome;
		descricao_ = descricao;
		local_ = local;
	}
	
	public String getNome () {
		return nome_;
	}

	public String getDescricao () {
		return descricao_;
	}

	public String getLocal () {
		return local_;
	}
	
  public void olhar() {
		System.out.println(descricao_);
	}

}
