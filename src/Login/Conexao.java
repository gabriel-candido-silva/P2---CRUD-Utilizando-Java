package Login;

import java.sql.Connection;    // Conexão com o banco de dados
import java.sql.DriverManager; // Driver de conexão, em nosso caso MySQL
import java.sql.ResultSet;     // Resultados das operações em banco de dados
import java.sql.SQLException;  // Acesso de exceções (erros)
import java.sql.Statement;     // Interpretação dos comandos SQL - CRUD

public class Conexao {
    // Atributos de conexão ligados às bibliotecas importadas
    public Connection con = null;
    public Statement stmt = null;
    public ResultSet resultset = null;

    // Atributos de conexão
    private final String servidor = "jdbc:mysql://127.0.0.1:3308/db_login"; // Servidor de banco de dados
    private final String usuario = "root";                                  // Usuário do banco de dados
    private final String senha = "";                                        // Senha do banco de dados
    private final String driver = "com.mysql.cj.jdbc.Driver";               // Driver de conexão

    // Método de abertura da conexão com o banco de dados
    public Connection abrirConexao() {
        try {
            Class.forName(driver); // Driver de utilização

            // Atributos de conexão
            con = DriverManager.getConnection(servidor, usuario, senha);
            stmt = con.createStatement(); // Fazendo a conexão com o banco de dados

            // Mensagem informando que a conexão foi realizada com sucesso
            System.out.println("Conexão com banco aberta com sucesso!");
        } catch (ClassNotFoundException | SQLException e) {
            // Mensagem de erro caso haja problema
            System.out.println("Erro ao acessar banco de dados, verifique! " + e.getMessage());
        }
        return con; // Retorna a conexão
    }

    // Método de fechamento da conexão com o banco de dados
    public void fecharConexao() {
        try {
            if (con != null && !con.isClosed()) {
                con.close(); // Fechamento da conexão com o banco de dados
            }
            System.out.println("Conexão finalizada com sucesso!");
        } catch (SQLException e) {
            // Mensagem de erro ao fechar a conexão
            System.out.println("Erro ao encerrar conexão: " + e.getMessage());
        }
    }
}

