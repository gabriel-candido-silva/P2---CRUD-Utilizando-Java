import Login.Conexao;
import Login.TelaLogin;

public class Main {
    public static void main(String[] args) {
        // Teste inicial de conexão com o banco de dados
        Conexao c = new Conexao();
        c.abrirConexao();

        try {
            Thread.sleep(2000); // Aguarda brevemente e fecha a conexão de teste
            c.fecharConexao();
        } catch (InterruptedException ex) {
            System.out.println("Houve algum problema ao fechar a conexão: " + ex.getMessage());
        }

        // Iniciando a tela de login
        TelaLogin t = new TelaLogin();
        t.abreTela();
    }
}
