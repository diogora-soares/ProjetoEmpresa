package empresa;

public class Principal {
    public static void main(String[] args) throws ClassNotFoundException {
        /*
        // Inserção de novo funcionário
        Funcionario novoFunc = new Funcionario("Pedro Raul", "pedradas@gmail.com", "F012", "TI");
        if (novoFunc.incluirFuncionario()) {
            System.out.println("Funcionário inserido com sucesso! ID: " + novoFunc.getId());
        } */

        /*
        // Alteração de funcionário já existente (por exemplo, id = 1)
        Funcionario funcExistente = Funcionario.buscarPorId(4);
        if (funcExistente != null) {
            funcExistente.setNome("Quincas Cruz");
            funcExistente.setEmail("quincas@email.com");
            funcExistente.setMatricula("F099");
            funcExistente.setDepartamento("Financeiro");
            if (funcExistente.alterarFuncionario()) {
                System.out.println("Funcionário alterado com sucesso!");
            }
        } else {
            System.out.println("Funcionário com id 4 não encontrado.");
        } */

        
        // Exemplo: excluir funcionário com id 2
        /*Funcionario funcParaExcluir = Funcionario.buscarPorId(2);
        if (funcParaExcluir != null) {
            if (funcParaExcluir.excluirFuncionario()) {
                System.out.println("Funcionário excluído com sucesso!");
            } else {
                System.out.println("Falha ao excluir funcionário.");
            }
        } else {
            System.out.println("Funcionário com id 2 não encontrado para exclusão.");
        }*/

        int idParaConsultar = 1; // ID do funcionário que deseja buscar
        // Chama o método consultaPorId e recebe o objeto Funcionario
        Funcionario func = Funcionario.consultaPorId(idParaConsultar);
        //Funcionario.listarFuncionarios();


    }
}
