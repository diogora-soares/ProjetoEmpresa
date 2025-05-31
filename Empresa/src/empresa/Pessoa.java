package empresa;

// Classe abstrata que representa uma pessoa.
// Contém atributos comuns a todas as pessoas, como ID, nome e email.

public abstract class Pessoa {
    private int id; // ID da pessoa
    private String nome; // Nome da pessoa
    private String email; // Email da pessoa

    // Construtor sem ID, pois será gerado pelo banco.
    public Pessoa(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    // Construtor com ID (para alteração/busca).
    public Pessoa(int id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    // Getters e Setters

    public int getId() {
        return id;
    }

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