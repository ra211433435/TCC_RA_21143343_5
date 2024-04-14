package PastaDTO;

public class UsuarioDTO {
	
		public int getId_Usuario() {
		return id_Usuario;
	}
	public void setId_Usuario(int id_Usuario) {
		this.id_Usuario = id_Usuario;
	}
	public String getNome_usuario() {
		return usuario;
	}
	public void setNome_usuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha_usuario() {
		return senha;
	}
	public void setSenha_usuario(String senha) {
		this.senha = senha;
	}
		private int id_Usuario;
	    private String usuario, senha;

	    
}
