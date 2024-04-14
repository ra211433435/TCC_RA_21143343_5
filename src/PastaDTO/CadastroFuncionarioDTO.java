package PastaDTO;

public class CadastroFuncionarioDTO {
	
	private String cadastro_funcionarioempresa;
	private String cadastro_funcionarionome;
	private String cadastro_funcionariocargo;
	private int pesquisar_idfuncionario;
	private String pesquisar_nomefuncionario;
	
	public String getCadastro_funcionarioempresa() {
		return cadastro_funcionarioempresa;
	}
	public void setCadastro_funcionarioempresa(String cadastro_funcionarioempresa) {
		this.cadastro_funcionarioempresa = cadastro_funcionarioempresa;
	}
	public String getCadastro_funcionarionome() {
		return cadastro_funcionarionome;
	}
	public void setCadastro_funcionarionome(String cadastro_funcionarionome) {
		this.cadastro_funcionarionome = cadastro_funcionarionome;
	}
	public String getCadastro_funcionariocargo() {
		return cadastro_funcionariocargo;
	}
	public void setCadastro_funcionariocargo(String cadastro_funcionariocargo) {
		this.cadastro_funcionariocargo = cadastro_funcionariocargo;
}
	public int getPesquisar_idfuncionario() {
		return pesquisar_idfuncionario;
	}
	public void setPesquisar_idfuncionario(int pesquisar_idfuncionario) {
		this.pesquisar_idfuncionario = pesquisar_idfuncionario;
	}
	public String getPesquisar_nomefuncionario() {
		return pesquisar_nomefuncionario;
	}
	public void setPesquisar_nomefuncionario(String pesquisar_nomefuncionario) {
		this.pesquisar_nomefuncionario = pesquisar_nomefuncionario;
	}
}