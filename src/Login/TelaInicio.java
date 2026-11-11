package Login;

import java.awt.HeadlessException;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TelaInicio extends JFrame {
    private final JPanel tela;

    // Atributo para validar a exclusão
    private boolean exclusaoValida;

    public TelaInicio() {
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Menu - Fatec");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 200, 426, 212);

        tela = new JPanel();
        tela.setBackground(SystemColor.gray);
        setContentPane(tela);
        tela.setLayout(null);

        // Concatena o Label com o atributo global nome da classe Usuario
        JLabel lblUsuario = new JLabel("Bem vindo " + Usuario.nomeUsuario);
        lblUsuario.setBounds(24, 65, 300, 15);
        tela.add(lblUsuario);

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(2, 130, 117, 25);
        tela.add(btnExcluir);

        JButton btnAlterar = new JButton("Alterar Dados");
        btnAlterar.setBounds(150, 130, 117, 25);
        tela.add(btnAlterar);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(300, 130, 117, 25);
        tela.add(btnVoltar);

        // Ação do botão Voltar (retorna ao Login)
        btnVoltar.addActionListener((ActionEvent e) -> {
            try {
                TelaLogin telaLogin = new TelaLogin();
                telaLogin.setVisible(true);
                dispose();
            } catch (Exception ec) {
                System.out.println("Erro ao acessar a tela de login: " + ec.getMessage());
            }
        });

        // Ação do botão Alterar (abre TelaAlteracao)
        btnAlterar.addActionListener((ActionEvent e) -> {
            try {
                TelaAlteracao telaAlterar = new TelaAlteracao();
                telaAlterar.setVisible(true);
                dispose();
            } catch (Exception ec) {
                System.out.println("Erro ao acessar a tela de alteração: " + ec.getMessage());
            }
        });

        // Ação do botão Excluir (exclusão do usuário conectado com confirmação)
        btnExcluir.addActionListener((ActionEvent e) -> {
            try {
                // Opções para a caixa de diálogo
                Object[] options = { "Sim", "Não" };
                int opcao = JOptionPane.showOptionDialog(null,
                        Usuario.nomeUsuario + ", deseja se excluir?", "Atenção",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);

                if (opcao == 0) {
                    Usuario usu = new Usuario();
                    exclusaoValida = usu.excluiUsuario(Usuario.usuarioSistema);

                    if (exclusaoValida) {
                        JOptionPane.showMessageDialog(null,
                                "Usuário deletado corretamente, voltaremos a tela de login.",
                                "Atenção",
                                JOptionPane.INFORMATION_MESSAGE);

                        TelaLogin tLogin = new TelaLogin();
                        tLogin.abreTela();
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Problemas ao excluir o usuário",
                                "Atenção",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (HeadlessException ec) {
                System.out.println("Erro ao excluir usuário: " + ec.getMessage());
            }
        });
    }

    // Método para abrir a tela inicial
    public void abreTela() {
        TelaInicio telaInicio = new TelaInicio();
        telaInicio.setVisible(true);
    }
}
