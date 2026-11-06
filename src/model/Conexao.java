package model;

import java.sql.Connection; //conexão com banco de dados
import java.sql.DriverManager; // driver para conexão em nosso caso Mysql
import java.sql.SQLException; // acesso de exceções (erros)
import java.sql.ResultSet; // Resultados das querys em banco de dados
import java.sql.Statement; // interpretador dos comandos do MySQL

public class Conexao {
    public Connection con = null;
    public Statement stmt = null;
    public ResultSet resultSet = null;


    private final String servidor = "jdbc:mysql://127.0.0.1:3308/db_login"; //servidor de banco de dados
    private final String usuario = "root"; //usuario do banco de dados
    private final String senha = ""; // senha do banco
    private final String driver = "com.mysql.cj.jdbc.Driver"; // driver de conexão


    public Connection abrirConexao() {
        try {
            Class.forName(driver); // driver de utilização

            //atributos de conexao

            con = DriverManager.getConnection(servidor, usuario, senha);
            stmt = con.createStatement(); // fazendo a conexão com o bando de dados

            // mensagem de sucesso
            System.out.println("Conexão com banco aberta com sucesso!");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("erro ao conectar com banco:" + e.getMessage());
        }
        return con; // retorna a conexão
    }

    public void fecharConexao() {
        try {
            con.close(); // fechando a conexão
            System.out.println("conexão fechada com sucesso!");
        } catch (SQLException e) {
            System.out.println("erro ao fechar a conexao" + e.getMessage());
        }
    }
}