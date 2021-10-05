public class Pontos {

  //tipo de ação que dará a pontuação e ficará armazenada no txt
	private String acao_; 
  //nome do item, caso haja
	private String item_;
  //nome do objeto, caso haja
	private String objeto_;
  //sala onde a ação ocorreu ou item foi encontrado
	private String local_;
  //quantidade de pontos conquistados pela ação
	private int pontos_;

	public Pontos(String acao, String item, String objeto, String local, int pontos) {
		acao_ = acao;
		item_ = item;
		local_ = local;
    objeto_ = objeto;
		pontos_ = pontos;
	}

	public String getAcao() {
		return acao_;
	}

	public String getItem() {
		return item_;
	}

	public String getObjeto() {
		return objeto_;
	}
  
	public String getLocal() {
		return local_;
	}

	public int getPontos() {
		return pontos_;
	}

}
