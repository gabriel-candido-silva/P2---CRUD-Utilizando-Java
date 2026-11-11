package Login;

import java.sql.SQLException; // Tratar as exceções no banco de dados

public class Usuario {
    // Criação dos atributos privados da classe
    private String usuario;
    private String nome;
    private String senha;

    // Atributos que armazenarão os retornos do banco de dados
    private boolean resultUsuario;
    private boolean resultCadastro;
    private boolean resultAlteracao;
    private boolean resultExclusao;

    // Atributos estáticos do sistema para tráfego entre telas
    public static String nomeUsuario;
    public static String usuarioSistema;

    // Criação dos getters e setters
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    // Método de verificação da autenticidade do usuário (Login)
    public boolean verificaUsuario(String usuario, String senha) {
        // Fazer a instância da conexão com o banco de dados
        Conexao banco = new Conexao();

        try {
            // Abro a conexão com o banco de dados
            banco.abrirConexao();

            // Criando parâmetro de retorno
            banco.stmt = banco.con.createStatement();

            // Executando a consulta no banco de dados
            banco.resultset = banco.stmt.executeQuery("SELECT * FROM usuarios "
                    + "WHERE usuario = '" + usuario + "' "
                    + "AND senha = md5('" + senha + "')");

            // Verificando se existe retorno de dados no banco
            if (banco.resultset.next()) {
                // Caso tenha
                resultUsuario = true;

                // Tenta atribuir pelo nome exato das colunas para evitar inversão
                try {
                    setUsuario(banco.resultset.getString("usuario"));
                    setNome(banco.resultset.getString("nome"));
                } catch (SQLException e) {
                    // Fallback conforme apostila do professor
                    setNome(banco.resultset.getString(1));
                    setUsuario(banco.resultset.getString(2));
                }

                // Garante que o usuario não fique nulo/vazio
                if (getUsuario() == null || getUsuario().trim().isEmpty()) {
                    setUsuario(usuario);
                }

                // Nos atributos estáticos, realizo as atribuições
                nomeUsuario = this.getNome();
                usuarioSistema = this.getUsuario();
            } else {
                // Caso não tenha
                resultUsuario = false;
            }

            banco.fecharConexao(); // Fecha nossa conexão com o banco de dados
        } catch (SQLException ec) {
            System.out.println("Erro ao consultar usuário " + ec.getMessage());
        }

        return resultUsuario;
    }

    // Método de verificação do usuário (Sobrecarga de método para cadastro)
    public boolean verificaUsuario(String usuario) {
        // Fazer a instância da conexão com o banco de dados
        Conexao banco = new Conexao();

        try {
            // Abro a conexão com o banco de dados
            banco.abrirConexao();

            // Criando parâmetro de retorno
            banco.stmt = banco.con.createStatement();

            // Executando a consulta no banco de dados
            banco.resultset = banco.stmt.executeQuery("SELECT * FROM usuarios "
                    + "WHERE usuario = '" + usuario + "'");

            // Verificando se existe retorno de dados no banco
            if (banco.resultset.next()) {
                // Caso tenha
                resultUsuario = true;
            } else {
                // Caso não tenha
                resultUsuario = false;
            }

            banco.fecharConexao(); // Fecha nossa conexão com o banco de dados
        } catch (SQLException ec) {
            System.out.println("Erro ao consultar usuário " + ec.getMessage());
        }

        return resultUsuario;
    }

    // Método para cadastro de usuário em nosso sistema
    public boolean cadastraUsuario(String nome, String usuario, String senha) {
        // Fazer a instância da conexão com o banco de dados
        Conexao banco = new Conexao();

        try {
            // Abertura da conexão com o banco de dados
            banco.abrirConexao();

            // Criando parâmetro de retorno
            banco.stmt = banco.con.createStatement();

            // Executando a inserção dos dados
            banco.stmt.execute("INSERT INTO usuarios (nome, usuario, senha) "
                    + "VALUES ('" + nome + "','" + usuario + "', md5('" + senha + "'))");

            resultCadastro = true;
        } catch (SQLException ec) {
            System.out.println("Erro ao inserir o usuário " + ec.getMessage());
            resultCadastro = false;
        } finally {
            banco.fecharConexao();
        }

        return resultCadastro;
    }

    // Método para alteração dos dados no nosso sistema
    public boolean alteraUsuario(String nome, String usuario, String senha) {
        // Fazer a instância da conexão com o banco de dados
        Conexao banco = new Conexao();

        try {
            // Abre a conexão com o banco de dados
            banco.abrirConexao();

            // Criando o parâmetro de retorno
            banco.stmt = banco.con.createStatement();

            // Executando a alteração no banco de dados
            int linhasAfetadas = banco.stmt.executeUpdate("UPDATE usuarios SET nome = '" + nome + "', "
                    + "senha = md5('" + senha + "') "
                    + "WHERE usuario = '" + usuario + "'");

            resultAlteracao = (linhasAfetadas > 0);
            if (resultAlteracao) {
                this.nome = nome;
                nomeUsuario = nome;
            }
        } catch (SQLException ec) {
            System.out.println("Erro ao atualizar usuário " + ec.getMessage());
            resultAlteracao = false;
        } finally {
            banco.fecharConexao();
        }

        return resultAlteracao;
    }

    // Método para exclusão do usuário do sistema
    public boolean excluiUsuario(String usuario) {
        // Fazer a instância da conexão com o banco de dados
        Conexao banco = new Conexao();

        try {
            // Abro a conexão com o banco de dados
            banco.abrirConexao();

            // Criando o parâmetro de retorno
            banco.stmt = banco.con.createStatement();

            // Executando a exclusão no banco de dados
            int linhasAfetadas = banco.stmt.executeUpdate("DELETE FROM usuarios WHERE usuario = '" + usuario + "'");

            // Caso exclua efetivamente alguma linha
            resultExclusao = (linhasAfetadas > 0);
        } catch (SQLException ec) {
            System.out.println("Erro ao excluir usuário " + ec.getMessage());
            resultExclusao = false;
        } finally {
            banco.fecharConexao();
        }

        return resultExclusao;
    }
}
