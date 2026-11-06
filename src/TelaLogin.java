import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class TelaLogin extends JFrame {
    //tela do objeto JPanel
    private final JPanel panelTela;

    //txtusuario do Objeto JTextField (campo de tela)
    private final JTextField txtUsuario;

    //pswSenha do objeto JPasswordField (campo de tela)
    private final JPasswordField pswSenha;

    //metodo construtor
    public TelaLogin(){
        //coloca o objeto de referencia do centro da tela
        setLocationRelativeTo(null);

        //não permite que o objeto seja expandido
        setResizable(false);

        //coloca titulo na caixa JFrame
        setTitle("Login - Fatec");

        //Quando clicado no X eu encerro todo o programa
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //define o posicionamento e tamanho
        setBounds(500, 200, 426, 212);

        //criando objeto JPanel e atribuindo a uma variavel tela
        panelTela = new JPanel();

        //definindo cor de fundo do JPanel
        panelTela.setBackground(SystemColor.gray);
        setContentPane(panelTela);

        //utilizando o próprio sem o padrão
        panelTela.setLayout(null);

        //adicionando elementos na tela do painel
        //criando um objeto do tipo JLabel e atribuindo valor
        JLabel lbIdentificacao = new JLabel("LOGIN");

        //localização na nossa tela
        lbIdentificacao.setBounds(144, 0, 160, 39);

        //definição de fonte
        lbIdentificacao.setFont(new Font("Arial", 3, 19));

        //adicionando no Panel
        panelTela.add(lbIdentificacao);

        //identificação de posicionamento dos Labels
        JLabel lbUsuario = new JLabel("Usuario");
        lbUsuario.setBounds(24, 65, 70, 15);
        panelTela.add(lbUsuario);

        JLabel lbSenha = new JLabel("Senha");
        lbSenha.setBounds(24, 92, 70, 15);
        panelTela.add(lbSenha);

        //Identificação e posicionamento dos texts fields
        txtUsuario = new JTextField();
        txtUsuario.setBounds(122, 63, 219, 19);
        panelTela.add(txtUsuario);
        txtUsuario.setColumns(10);

        pswSenha = new JPasswordField();
        pswSenha.setBounds(122, 90, 219, 19);
        panelTela.add(pswSenha);

        //criando e posicionando botões
        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setBounds(200, 136, 117, 25);
        panelTela.add(btnEntrar);

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(50, 136, 117, 25);
        panelTela.add(btnCadastrar);

    }

    public void abrirTela() {
        TelaLogin tela = new TelaLogin();
        tela.setVisible(true);
    }
}
