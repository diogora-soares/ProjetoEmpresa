package empresa;

public abstract class Pessoa {
	private int id;
	private String nome;
	private String email;

	// Construtor sem id, pois será gerado pelo banco
	public Pessoa(String nome, String email) {
		this.nome = nome;
		this.email = email;
	}

	  // Construtor com id (para alteração/busca)
	public Pessoa(int id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

	// Getters e Setters
	public int getId() {
		return id;
	}

	// Setter para atualizar o id gerado pelo banco
	public void setId(int id) {
		this.id = id;
	}
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
