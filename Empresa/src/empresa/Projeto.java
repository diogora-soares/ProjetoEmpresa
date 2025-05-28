package empresa;

public class Projeto {
    private int    id;
    private String nome;
    private String descricao;
    private int    idFuncionario;

    public Projeto() {}

    public Projeto(int id, String nome, String descricao, int idFuncionario) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.idFuncionario = idFuncionario;
    }

    // Getters e Setters
}
