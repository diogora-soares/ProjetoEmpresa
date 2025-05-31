package empresa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.Conexao;

// Classe que representa um projeto. 
// Contém métodos para gerenciar projetos no banco de dados. 
public class Projeto {
    private int id; // ID do projeto
    private String nome; // Nome do projeto
    private String descricao; // Descrição do projeto
    private int idFuncionario; // ID do funcionário responsável pelo projeto

    // Construtores

    // Construtor para criar um novo projeto sem ID, pois será gerado pelo banco.
    public Projeto(String nome, String descricao, int idFuncionario) {
        this.nome = nome;
        this.descricao = descricao;
        this.idFuncionario = idFuncionario;
    }

    // Construtor para criar um projeto existente com ID (para alteração/busca).
    public Projeto(int id, String nome, String descricao, int idFuncionario) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.idFuncionario = idFuncionario;
    }

    // CREATE

    // Inclui um novo projeto no banco de dados.
    public boolean incluirProjeto() throws ClassNotFoundException {
        String sql = "INSERT INTO projeto (nome, descricao, id_funcionario) VALUES (?, ?, ?)";
        Connection con = Conexao.conectar();
        try {
            PreparedStatement stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, this.nome);
            stm.setString(2, this.descricao);
            stm.setInt(3, this.idFuncionario);
            stm.executeUpdate();

            try (ResultSet generatedKeys = stm.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    this.id = generatedKeys.getInt(1); // Atualiza o ID do projeto
                }
            }
            return true;
        } catch (SQLException erro) {
            System.err.println("Erro ao inserir projeto: " + erro.getMessage());
            return false;
        }
    }

    // UPDATE

    // Altera os dados de um projeto no banco de dados.
    public boolean alterarProjeto() throws ClassNotFoundException {
        String sql = "UPDATE projeto SET nome = ?, descricao = ?, id_funcionario = ? WHERE id = ?";
        Connection con = Conexao.conectar();
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, this.nome);
            stm.setString(2, this.descricao);
            stm.setInt(3, this.idFuncionario);
            stm.setInt(4, this.id);
            stm.executeUpdate();
            return true;
        } catch (SQLException erro) {
            System.err.println("Erro ao atualizar projeto: " + erro.getMessage());
            return false;
        }
    }

    // DELETE

    // Exclui um projeto do banco de dados.
    public boolean excluirProjeto() throws ClassNotFoundException {
        String sql = "DELETE FROM projeto WHERE id = ?";
        Connection con = Conexao.conectar();
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, this.id);
            stm.executeUpdate();
            return true;
        } catch (SQLException erro) {
            System.err.println("Erro ao excluir projeto: " + erro.getMessage());
            return false;
        }
    }

    // READ - Buscar por ID

    // Busca um projeto pelo ID.
    public static Projeto buscarPorId(int id) throws ClassNotFoundException {
        String sql = "SELECT * FROM projeto WHERE id = ?";
        Connection con = Conexao.conectar();
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return new Projeto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getInt("id_funcionario")
                        );
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar projeto: " + e.getMessage());
        }
        return null;
    }

    // READ - Listar todos os projetos

    // Lista todos os projetos cadastrados.
    public static List<Projeto> listarProjetos() throws ClassNotFoundException {
        List<Projeto> lista = new ArrayList<>();
        String sql = "SELECT * FROM projeto";
        Connection con = Conexao.conectar();
        System.out.println("--------------------------------------");
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Projeto proj = new Projeto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getInt("id_funcionario")
                        );
                lista.add(proj);
                // Exibe informações
                System.out.println("ID: " + proj.getId());
                System.out.println("NOME: " + proj.getNome());
                System.out.println("DESCRIÇÃO: " + proj.getDescricao());
                System.out.println("ID FUNCIONÁRIO: " + proj.getIdFuncionario());
                System.out.println("--------------------------------------");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar projetos: " + e.getMessage());
        }
        return lista;
    }

    // READ - Consulta por ID com impressão

    // Consulta um projeto pelo ID e imprime seus dados.
    public static Projeto consultaPorId(int id) throws ClassNotFoundException {
        Projeto proj = buscarPorId(id);
        if (proj != null) {
            System.out.println("Projeto encontrado:");
            System.out.println("ID: " + proj.getId());
            System.out.println("Nome: " + proj.getNome());
            System.out.println("Descrição: " + proj.getDescricao());
            System.out.println("ID Funcionário: " + proj.getIdFuncionario());
            return proj;
        }
        System.err.println("Projeto com id " + id + " não encontrado.");
        return null;
    }

    // Getters e Setters

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }
}