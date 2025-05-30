package empresa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import util.Conexao;

public class Funcionario extends Pessoa {
    private String matricula;
    private String departamento;

    public Funcionario(String nome, String email, String matricula, String departamento) {
        super(nome, email);
        this.matricula = matricula;
        this.departamento = departamento;
    }

    public Funcionario(int id, String nome, String email, String matricula, String departamento) {
        super(id, nome, email);
        this.matricula = matricula;
        this.departamento = departamento;
    }

    public boolean incluirFuncionario() throws ClassNotFoundException {
        String sqlIPessoa = "INSERT INTO pessoa (nome, email) VALUES (?, ?)";
        String sqlIFuncionario = "INSERT INTO funcionario (id, matricula, departamento) VALUES (?, ?, ?)";
        Connection con = Conexao.conectar();
        try {
            // Inserir Pessoa
            PreparedStatement stm = con.prepareStatement(sqlIPessoa, Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, super.getNome());
            stm.setString(2, super.getEmail());
            stm.executeUpdate();

            try (ResultSet generatedKeys = stm.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    super.setId(generatedKeys.getInt(1)); // Atualiza ID na superclasse
                }
            }

            // Inserir Funcionario
            stm = con.prepareStatement(sqlIFuncionario);
            stm.setInt(1, super.getId());
            stm.setString(2, this.matricula);
            stm.setString(3, this.departamento);
            stm.executeUpdate();
            return true;
        } catch (SQLException erro) {
            System.err.println("Ocorreu um erro na inserção - " + erro.getMessage());
            return false;
        }
    }

    public boolean alterarFuncionario() throws ClassNotFoundException {
        String sqlAPessoa = "UPDATE pessoa SET nome = ?, email = ? WHERE id = ?";
        String sqlAFuncionario = "UPDATE funcionario SET matricula = ?, departamento = ? WHERE id = ?";
        Connection con = Conexao.conectar();

        try {
            // Alterar Pessoa
            PreparedStatement stm = con.prepareStatement(sqlAPessoa);
            stm.setString(1, super.getNome());
            stm.setString(2, super.getEmail());
            stm.setInt(3, super.getId());
            stm.executeUpdate();

            // Alterar Funcionario
            stm = con.prepareStatement(sqlAFuncionario);
            stm.setString(1, this.matricula);
            stm.setString(2, this.departamento);
            stm.setInt(3, super.getId());
            stm.executeUpdate();
            return true;
        } catch (SQLException erro) {
            System.err.println("Ocorreu um erro na atualização - " + erro.getMessage());
            return false;
        }
    }

    public boolean excluirFuncionario() throws ClassNotFoundException {
        String sqlEProjeto = "DELETE FROM projeto WHERE id_funcionario = ?";
        String sqlEFuncionario = "DELETE FROM funcionario WHERE id = ?";
        String sqlEPessoa = "DELETE FROM pessoa WHERE id = ?";

        Connection con = Conexao.conectar();

        try {
            con.setAutoCommit(false); // Inicia transação

            // Excluir projetos vinculados ao funcionário
            PreparedStatement stm = con.prepareStatement(sqlEProjeto);
            stm.setInt(1, super.getId());
            stm.executeUpdate();

            // Excluir funcionário
            stm = con.prepareStatement(sqlEFuncionario);
            stm.setInt(1, super.getId());
            stm.executeUpdate();

            // Excluir pessoa
            stm = con.prepareStatement(sqlEPessoa);
            stm.setInt(1, super.getId());
            stm.executeUpdate();

            con.commit(); // Confirma transação
            return true;
        } catch (SQLException erro) {
            try {
                if (con != null) {
                    con.rollback(); // Reverte alterações em caso de erro
                }
            } catch (SQLException ex) {
                System.err.println("Erro ao fazer rollback: " + ex.getMessage());
            }
            System.err.println("Ocorreu um erro na Exclusão - " + erro.getMessage());
            return false;
        } finally {
            try {
                if (con != null) {
                    con.setAutoCommit(true);
                    con.close();
                }
            } catch (SQLException ex) {
                System.err.println("Erro ao fechar conexão: " + ex.getMessage());
            }
        }
    }

    // Buscar funcionário por id
    public static Funcionario buscarPorId(int id) throws ClassNotFoundException {
        String sql = "SELECT p.nome, p.email, f.matricula, f.departamento " +
                     "FROM pessoa p JOIN funcionario f ON p.id = f.id WHERE p.id = ?";
        Connection con = Conexao.conectar();
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return new Funcionario(
                    id,
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("matricula"),
                    rs.getString("departamento")
                );
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar funcionário: " + e.getMessage());
        }
        return null;
    }

    public static List<Funcionario> listarFuncionarios() throws ClassNotFoundException{
        List<Funcionario> listFunc = new ArrayList<>();
        Connection con = Conexao.conectar();
        
        String sql = "SELECT";
        sql += " p.id, ";
        sql += " p.nome, ";
        sql += " p.email, ";
        sql += " f.matricula, ";
        sql += " f.departamento ";
        sql += " FROM pessoa p  ";
        sql += " INNER JOIN funcionario f ";
        sql += " ON p.id = f.id ";
        System.out.println("--------------------------------------");
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("NOME: " + rs.getString("nome"));
                System.out.println("EMAIL: " + rs.getString("email"));
                System.out.println("MATRICULA: " + rs.getString("matricula"));
                System.out.println("DEPARTAMENTO: " + rs.getString("departamento"));
                System.out.println("-   -------------------------------------");
            }
        } catch (Exception e) {
            System.err.println("Erro ao listar Funcionario " + e.getMessage());
        }
        
        return listFunc;
    }

    public static Funcionario consultaPorId(int id) throws ClassNotFoundException {
        String sql = "SELECT p.nome, p.email, f.matricula, f.departamento " +
                     "FROM pessoa p JOIN funcionario f ON p.id = f.id WHERE p.id = ?";
        Connection con = Conexao.conectar();
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Funcionario func = new Funcionario(
                    id,
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("matricula"),
                    rs.getString("departamento")
                );
                // Imprime os dados do funcionário aqui
                System.out.println("Funcionário encontrado:");
                System.out.println("ID: " + func.getId());
                System.out.println("Nome: " + func.getNome());
                System.out.println("Email: " + func.getEmail());
                System.out.println("Matrícula: " + func.getMatricula());
                System.out.println("Departamento: " + func.getDepartamento());
                return func;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar funcionário: " + e.getMessage());
        }
        System.out.println("Funcionário com id " + id + " não encontrado.");
        return null;
    }


    // Getters e Setters
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
}