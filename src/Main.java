import model.Conexao;

import java.sql.SQLException;

void main() {
    Conexao c = new Conexao();

    c.abrirConexao();

    try {
        Thread.sleep(4000); // definimos um tempo para que o programa espere para fechar a conexão
        c.fecharConexao();
    } catch (InterruptedException ex) {
        System.out.println("Houve algum problema ao fechar a conexão" + ex.getMessage());

    }

    // iniciando tela de login
    TelaLogin t = new TelaLogin();
    t.abrirTela();
}
