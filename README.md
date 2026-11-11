# Sistema de Gerenciamento de Usuarios - CRUD em Java

## Objetivo

Este projeto foi desenvolvido como avaliacao P2 da disciplina de Desenvolvimento para Servidores, ministrada na Fatec. O objetivo e construir um sistema completo de CRUD (Create, Read, Update, Delete) para gerenciamento de usuarios, utilizando interface grafica desktop com Java Swing e persistencia de dados em banco de dados MySQL. O sistema cobre o ciclo completo de autenticacao: cadastro de usuario, login, alteracao de dados e exclusao de conta.

---

## Linguagem e Versão

- Java (JDK 25 ou superior recomendado)

---

## Bibliotecas e Dependências

### Biblioteca Java padrao utilizada

- javax.swing: Responsavel pela construcao de todas as interfaces graficas (telas, campos de texto, botoes, caixas de dialogo)
- java.awt: Suporte a componentes graficos de baixo nivel (fontes, cores, eventos)
- java.sql: Responsavel pela comunicacao com o banco de dados MySQL (Connection, Statement, ResultSet, DriverManager, SQLException)

### Driver externo obrigatorio

- MySQL Connector/J 9.6.0 (mysql-connector-j-9.6.0.jar): Driver JDBC necessario para que o Java se comunique com o banco de dados MySQL. Deve ser adicionado manualmente ao classpath do projeto.

---

## Ferramentas Necessárias

### 1. JDK (Java Development Kit)

Necessario para compilar e executar o projeto. Recomenda-se o uso do OpenJDK 25 ou superior.

### 2. XAMPP

Utilizado para hospedar o servidor MySQL localmente de forma portatil. No projeto, o MySQL esta configurado para rodar na porta 3308 (nao na porta padrao 3306) para evitar conflitos com outras instalacoes.

Passos para configurar o XAMPP para este projeto:

1. Instale o XAMPP (https://www.apachefriends.org/)
2. Abra o XAMPP Control Panel
3. Clique em Config ao lado do servico MySQL e edite o arquivo my.ini
4. Localize a linha `port=3306` e altere para `port=3308`
5. Salve o arquivo e inicie o servico MySQL pelo painel do XAMPP

### 3. MySQL Workbench

Ferramenta grafica para administracao e visualizacao do banco de dados MySQL. Permite executar o script SQL de criacao do banco, inspecionar os registros da tabela e verificar o funcionamento das operacoes CRUD em tempo real.

Pode ser baixado em:
https://dev.mysql.com/downloads/workbench/

Para conectar o MySQL Workbench ao banco na porta 3308:

1. Abra o MySQL Workbench
2. Clique em "+" para criar uma nova conexao
3. Configure: Hostname: 127.0.0.1, Port: 3308, Username: root
4. Clique em "Test Connection" para verificar

---

## Configuração do Banco de Dados

O arquivo `schema.sql` localizado na raiz do projeto contem o script de criacao do banco de dados. Execute-o no MySQL Workbench ou diretamente no terminal MySQL antes de iniciar a aplicacao.

Configuracoes utilizadas na classe Conexao.java:

```
Servidor: jdbc:mysql://127.0.0.1:3308/db_login
Usuario:  root
Senha:    (sem senha, padrao XAMPP)
Driver:   com.mysql.cj.jdbc.Driver
```

Estrutura da tabela `usuarios`:

```
usuario  VARCHAR(10)  PRIMARY KEY
nome     VARCHAR(30)
senha    CHAR(32)      (armazenada com hash MD5)
```

---

## Estrutura do Projeto

```
ConexaoComBanco/
├── src/
│   ├── Main.java                  Ponto de entrada da aplicacao
│   └── Login/
│       ├── Conexao.java           Gerenciamento de conexao com o banco de dados
│       ├── Usuario.java           Classe de modelo com todos os metodos CRUD
│       ├── TelaLogin.java         Tela de autenticacao do usuario
│       ├── TelaCadastro.java      Tela de cadastro de novo usuario
│       ├── TelaInicio.java        Tela inicial pos-login (menu principal)
│       └── TelaAlteracao.java     Tela de alteracao de dados do usuario
├── schema.sql                     Script SQL de criacao do banco e da tabela
├── P2 - desenvolvimento para servidores.iml
└── README.md
```

---

## Descrição das Classes

### Main.java

Ponto de entrada da aplicacao. Realiza um teste inicial de conexao com o banco de dados e, em seguida, inicializa e exibe a tela de login.

### Login/Conexao.java

Responsavel por gerenciar a abertura e o fechamento da conexao com o banco de dados MySQL via JDBC. Expoe os atributos `con`, `stmt` e `resultset` para uso nos metodos da classe Usuario.

### Login/Usuario.java

Classe de modelo que encapsula todos os metodos de acesso ao banco de dados:

- `verificaUsuario(String usuario, String senha)`: Autentica o usuario comparando as credenciais com o banco, utilizando hash MD5 na senha. Popula os atributos estaticos de sessao `nomeUsuario` e `usuarioSistema` em caso de sucesso.
- `verificaUsuario(String usuario)`: Sobrecarga do metodo anterior. Verifica apenas se um nome de usuario ja existe no banco, utilizado para evitar duplicidade no cadastro.
- `cadastraUsuario(String nome, String usuario, String senha)`: Insere um novo registro na tabela, armazenando a senha com hash MD5.
- `alteraUsuario(String nome, String usuario, String senha)`: Atualiza o nome e a senha do usuario no banco de dados.
- `excluiUsuario(String usuario)`: Remove o registro do usuario da tabela.

Os atributos estaticos `nomeUsuario` e `usuarioSistema` sao utilizados para transitar as informacoes da sessao ativa entre as diferentes telas da aplicacao.

### Login/TelaLogin.java

Tela inicial da aplicacao. Permite que o usuario informe suas credenciais para autenticacao. Possui validacao de campos obrigatorios, mensagens de erro via JOptionPane e redireciona para a TelaInicio em caso de sucesso ou para a TelaCadastro ao clicar em "Cadastrar".

### Login/TelaCadastro.java

Tela de registro de novo usuario. Coleta nome, usuario, senha e confirmacao de senha. Realiza validacoes de campos obrigatorios, conferencia de senha e prevencao de cadastro duplicado antes de persistir o registro no banco.

### Login/TelaInicio.java

Tela principal exibida apos o login bem-sucedido. Apresenta uma mensagem de boas-vindas personalizada com o nome do usuario logado e oferece as opcoes de alterar dados, excluir a conta (com confirmacao via dialogo) ou voltar para o login.

### Login/TelaAlteracao.java

Tela de atualizacao de dados. Exibe o nome atual do usuario preenchido automaticamente e solicita a senha atual para validacao antes de permitir a alteracao. Verifica tambem se a nova senha e sua confirmacao sao identicas antes de gravar no banco.

---

## Fluxo da Aplicação

```
Main
 |
 +-- TelaLogin
      |
      +-- [Login bem-sucedido] --> TelaInicio
      |                               |
      |                               +-- [Alterar Dados] --> TelaAlteracao --> TelaLogin
      |                               |
      |                               +-- [Excluir conta] --> (confirmacao) --> TelaLogin
      |                               |
      |                               +-- [Voltar] --> TelaLogin
      |
      +-- [Cadastrar] --> TelaCadastro --> TelaLogin
```

---

## Como Executar

1. Certifique-se de que o XAMPP esta em execução com o MySQL na porta 3308
2. Execute o script `schema.sql` no MySQL Workbench ou no cliente MySQL para criar o banco e a tabela
3. Adicione o arquivo `mysql-connector-j-9.6.0.jar` ao classpath do projeto no IntelliJ (File > Project Structure > Modules > Dependencies > + > JARs or Directories)
4. Execute a classe `Main.java`
