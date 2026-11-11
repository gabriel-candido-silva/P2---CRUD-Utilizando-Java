package Login;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class TelaAlteracao extends JFrame {
    // Componentes de interface gráfica
    private final JPanel tela;
    private final JTextField txtNome;
    private final JPasswordField passAtual;
    private final JPasswordField passSenha;
    private final JPasswordField confPassSenha;

    // Atributo de validação
    private boolean atualizacaoValida;

    public TelaAlteracao() {
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Alteração - Fatec");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 200, 426, 212);

        tela = new JPanel();
        tela.setBackground(SystemColor.gray);
        setContentPane(tela);
        tela.setLayout(null);

        // Adicionando elementos na tela
        JLabel lblIdentificacao = new JLabel("Informar campos para alteração");
        lblIdentificacao.setBounds(60, 0, 500, 39);
        lblIdentificacao.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 19));
        tela.add(lblIdentificacao);

        JLabel lblNome = new JLabel("Nome");
        lblNome.setBounds(24, 35, 100, 15);
        tela.add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(120, 35, 218, 20);
        tela.add(txtNome);
        txtNome.setColumns(10);
        // Preenche automaticamente o campo com o nome do usuário logado
        txtNome.setText(Usuario.nomeUsuario);

        JLabel lblSenhaAtual = new JLabel("Senha Atual");
        lblSenhaAtual.setBounds(24, 60, 70, 15);
        tela.add(lblSenhaAtual);

        passAtual = new JPasswordField();
        passAtual.setBounds(120, 60, 219, 19);
        tela.add(passAtual);

        JLabel lblNovaSenha = new JLabel("Nova Senha");
        lblNovaSenha.setBounds(24, 85, 70, 15);
        tela.add(lblNovaSenha);

        passSenha = new JPasswordField();
        passSenha.setBounds(120, 85, 219, 19);
        tela.add(passSenha);

        JLabel lblConfSenha = new JLabel("Confirmar Senha");
        lblConfSenha.setBounds(24, 110, 100, 15);
        tela.add(lblConfSenha);

        confPassSenha = new JPasswordField();
        confPassSenha.setBounds(120, 110, 219, 19);
        tela.add(confPassSenha);

        JButton btnAlterar = new JButton("Alterar");
        btnAlterar.setBounds(200, 136, 117, 25);
        tela.add(btnAlterar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(50, 136, 117, 25);
        tela.add(btnCancelar);

        // Ação do botão Cancelar (retorna para a TelaInicio)
        btnCancelar.addActionListener((ActionEvent e) -> {
            TelaInicio telaIni = new TelaInicio();
            telaIni.setVisible(true);
            dispose();
        });

        // Ação do botão Alterar (valida e efetiva a alteração no banco)
        btnAlterar.addActionListener((ActionEvent e) -> {
            try {
                // Instancio a classe usuario
                Usuario usu = new Usuario();

                usu.setSenha(new String(confPassSenha.getPassword()));
                usu.setUsuario(Usuario.usuarioSistema);

                String strNome = txtNome.getText();
                String strSenhaAtual = new String(passAtual.getPassword());
                String strNovaSenha = new String(passSenha.getPassword());
                String strConfSenha = new String(confPassSenha.getPassword());

                // Validação de nome vazio
                if ("".equals(strNome)) {
                    JOptionPane.showMessageDialog(null,
                            "Campo nome do usuário precisa ser informado!",
                            "Atenção",
                            JOptionPane.ERROR_MESSAGE);
                    txtNome.grabFocus();
                }
                // Validação de nova senha vazia
                else if ("".equals(strNovaSenha)) {
                    JOptionPane.showMessageDialog(null,
                            "Campo senha precisa ser informado!",
                            "Atenção",
                            JOptionPane.ERROR_MESSAGE);
                    passSenha.grabFocus();
                }
                // Validação da senha atual
                else if (!usu.verificaUsuario(usu.getUsuario(), strSenhaAtual)) {
                    JOptionPane.showMessageDialog(null,
                            "Senha inválida, verifique!",
                            "Atenção",
                            JOptionPane.ERROR_MESSAGE);
                    passAtual.grabFocus();
                }
                // Confirmação de nova senha
                else if (!strNovaSenha.equals(strConfSenha)) {
                    JOptionPane.showMessageDialog(null,
                            "Campos de Senha e Confirmação não são iguais!",
                            "Atenção",
                            JOptionPane.ERROR_MESSAGE);
                    passSenha.grabFocus();
                }
                // Efetiva a alteração do usuário no banco
                else {
                    atualizacaoValida = usu.alteraUsuario(strNome, usu.getUsuario(), strNovaSenha);

                    if (atualizacaoValida) {
                        JOptionPane.showMessageDialog(null,
                                "Dado(s) do usuário alterado(s) retornaremos a tela de login.",
                                "Atenção",
                                JOptionPane.INFORMATION_MESSAGE);

                        TelaLogin tLogin = new TelaLogin();
                        tLogin.abreTela();
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Problemas ao atualizar o usuário",
                                "Atenção",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (HeadlessException ec) {
                System.out.println("Erro ao alterar usuário: " + ec.getMessage());
            }
        });
    }

    // Método para abrir a tela de alteração
    public void abreTela() {
        TelaAlteracao telaAlteracao = new TelaAlteracao();
        telaAlteracao.setVisible(true);
    }
}

