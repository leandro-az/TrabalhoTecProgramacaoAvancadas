package visao;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import modelo.Funcionario;
import service.FuncionarioService;

public class CadastroFuncionario extends JFrame implements ActionListener {

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JRadioButton flag_F;
	private JRadioButton flag_M;
	private String resp;
	private JTextField testto_Salario;
	private JTextField testo_Da_Idade;
	private Funcionario umfuncionario;
	
	
       ApplicationContext fabrica = new ClassPathXmlApplicationContext("beans-jpa.xml");	    
	   FuncionarioService funcionarioAppService = (FuncionarioService)fabrica.getBean ("FuncionarioService");
	    	
	    
		
	public CadastroFuncionario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 628, 382);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		JTextField Caixa_De_textoNome;
		contentPane.setLayout(null);
		Caixa_De_textoNome = new JTextField();
		Caixa_De_textoNome.setBounds(159, 43, 356, 26);
		getContentPane().add(Caixa_De_textoNome);
		Caixa_De_textoNome.setColumns(10);
		
		JLabel Texto_Da_Caixa_Funcionario = new JLabel("Nome do Funcionario:");
		Texto_Da_Caixa_Funcionario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Texto_Da_Caixa_Funcionario.setBounds(10, 41, 147, 26);
		getContentPane().add(Texto_Da_Caixa_Funcionario);
		
		JLabel Testo_do_Sexo = new JLabel("Sexo:");
		Testo_do_Sexo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Testo_do_Sexo.setBounds(105, 89, 52, 21);
		getContentPane().add(Testo_do_Sexo);
		
	    flag_M = new JRadioButton("M");
		flag_M.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(flag_M.isSelected()){
					flag_F.setSelected(false);
					resp="M";
				}
			}
		});
		flag_M.setBounds(159, 90, 46, 23);
		getContentPane().add(flag_M);
		
		flag_F = new JRadioButton("F");
		flag_F.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(flag_F.isSelected()){
					flag_M.setSelected(false);
					resp="F";
				}
			}
		});
		flag_F.setBounds(226, 90, 46, 23);
		getContentPane().add(flag_F);;
		
		JLabel Testo_da_FaixetariaDeIdade = new JLabel("Idade:");
		Testo_da_FaixetariaDeIdade.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Testo_da_FaixetariaDeIdade.setBounds(83, 140, 66, 21);
		getContentPane().add(Testo_da_FaixetariaDeIdade);
		
		JLabel Testo_Salario = new JLabel("Salario:");
		Testo_Salario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Testo_Salario.setBounds(98, 209, 59, 26);
		contentPane.add(Testo_Salario);
		
		testto_Salario = new JTextField();
		testto_Salario.setBounds(159, 214, 113, 21);
		contentPane.add(testto_Salario);
		testto_Salario.setColumns(10);
		
		testo_Da_Idade = new JTextField();
		testo_Da_Idade.setBounds(159, 142, 36, 26);
		contentPane.add(testo_Da_Idade);
		testo_Da_Idade.setColumns(10);
		
		JButton botao_de_Cadastrar = new JButton("Cadastrar");
		botao_de_Cadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Float aux = Float.parseFloat(testto_Salario.getText());
				umfuncionario = new Funcionario();
				    umfuncionario.setIdade(Integer.parseInt(testo_Da_Idade.getText()));
				    umfuncionario.setNome(Caixa_De_textoNome.getText().toUpperCase().trim());
				    umfuncionario.setSexo(resp);
				    umfuncionario.setSalario(aux);
				   
				    funcionarioAppService.inclui(umfuncionario);
				    
				    JOptionPane.showMessageDialog(null, "Funcionario Cadastrado Com Sucesso !");
				    dispose();
				
			}
		});
		botao_de_Cadastrar.setBounds(159, 294, 98, 23);
		contentPane.add(botao_de_Cadastrar);
		
		JButton botaoVoltar = new JButton("Voltar");
		botaoVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		botaoVoltar.setBounds(465, 294, 89, 23);
		contentPane.add(botaoVoltar);
		
		
		
		
		
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
