package Login;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class TelaLogin extends JFrame {
    // tela do objeto JPanel
    private final JPanel panelTela;

    // txtUsuario do objeto JTextField (campo de tela)
    private final JTextField txtUsuario;

    // pswSenha do objeto JPasswordField (campo de tela)
    private final JPasswordField pswSenha;

    // Atributo para validar se o usuário é correto
    private boolean usuarioValido;

    // Método construtor
    public TelaLogin() {
        // Coloca o objeto na referência do centro da tela
        setLocationRelativeTo(null);

        // Não permite que o objeto seja expandido
        setResizable(false);

        // Coloca título na caixa JFrame
        setTitle("Login - Fatec");

        // Quando clicado no X encerra todo o programa
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Define o posicionamento e tamanho
        setBounds(500, 200, 426, 212);

        // Criando objeto JPanel e atribuindo a variável painel
        panelTela = new JPanel();

        // Definindo cor de fundo do JPanel
        panelTela.setBackground(SystemColor.gray);
        setContentPane(panelTela);

        // Layout nulo para posicionamento absoluto
        panelTela.setLayout(null);

        // Adicionando elementos na tela do painel
        JLabel lbIdentificacao = new JLabel("IDENTIFICAÇÃO");
        lbIdentificacao.setBounds(144, 0, 160, 39);
        lbIdentificacao.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 19));
        panelTela.add(lbIdentificacao);

        // Identificação e posicionamento dos Labels
        JLabel lbUsuario = new JLabel("Usuario");
        lbUsuario.setBounds(24, 65, 70, 15);
        panelTela.add(lbUsuario);

        JLabel lbSenha = new JLabel("Senha");
        lbSenha.setBounds(24, 92, 70, 15);
        panelTela.add(lbSenha);

        // Identificação e posicionamento dos TextFields
        txtUsuario = new JTextField();
        txtUsuario.setBounds(122, 63, 219, 19);
        panelTela.add(txtUsuario);
        txtUsuario.setColumns(10);

        pswSenha = new JPasswordField();
        pswSenha.setBounds(122, 90, 219, 19);
        panelTela.add(pswSenha);

        // Criando e posicionando botões
        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setBounds(200, 136, 117, 25);
        panelTela.add(btnEntrar);

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(50, 136, 117, 25);
        panelTela.add(btnCadastrar);

        // Ação no botão Entrar no sistema
        btnEntrar.addActionListener((ActionEvent e) -> {
            // Instancio a classe usuario
            Usuario usu = new Usuario();

            // Utilizo o setter de usuario e senha
            usu.setUsuario(txtUsuario.getText());
            usu.setSenha(new String(pswSenha.getPassword()));

            if ("".equals(txtUsuario.getText())) {
                JOptionPane.showMessageDialog(null,
                        "Campo usuário precisa ser informado!",
                        "Atenção",
                        JOptionPane.ERROR_MESSAGE);
                txtUsuario.grabFocus();
            } else if ("".equals(new String(pswSenha.getPassword()))) {
                JOptionPane.showMessageDialog(null,
                        "Campo senha precisa ser informado!",
                        "Atenção",
                        JOptionPane.ERROR_MESSAGE);
                pswSenha.grabFocus();
            } else {
                // Verifico se o usuário consta no banco de dados
                usuarioValido = usu.verificaUsuario(usu.getUsuario(), usu.getSenha());

                if (usuarioValido) {
                    // Garante que o usuario de sessão esteja devidamente registrado
                    Usuario.usuarioSistema = usu.getUsuario();
                    if (usu.getNome() != null && !usu.getNome().trim().isEmpty()) {
                        Usuario.nomeUsuario = usu.getNome();
                    }

                    JOptionPane.showMessageDialog(null,
                            "Usuário válido em nossa base de dados",
                            "Atenção",
                            JOptionPane.INFORMATION_MESSAGE);

                    // Redireciona para TelaInicio
                    TelaInicio telaInicio = new TelaInicio();
                    telaInicio.abreTela();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Usuário inválido ou inexistente",
                            "Atenção",
                            JOptionPane.ERROR_MESSAGE);

                    // Método para limpar os campos de texto
                    limpaText();

                    // Foco no campo usuário
                    txtUsuario.grabFocus();
                }
            }
        });

        // Ação no botão para cadastrar Usuário
        btnCadastrar.addActionListener((ActionEvent e) -> {
            // Instancio a classe TelaCadastro
            TelaCadastro tCadastro = new TelaCadastro();
            tCadastro.abreTela();
            dispose();
        });
    }

    // Método para limpar os textos
    public void limpaText() {
        txtUsuario.setText("");
        pswSenha.setText("");
    }

    // Método para abrir a tela
    public void abreTela() {
        TelaLogin tela = new TelaLogin();
        tela.setVisible(true);
    }

    // Sobrecarga / método de conveniência
    public void abrirTela() {
        abreTela();
    }
}
