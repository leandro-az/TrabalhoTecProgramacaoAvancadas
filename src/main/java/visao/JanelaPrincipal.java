package visao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;



public class JanelaPrincipal extends JFrame implements ActionListener {
 private JMenuBar menuBar;
 private JMenu botaoIniciar;
 @SuppressWarnings("unused")
private static JanelaPrincipal frame;
 
	private static final long serialVersionUID = 1L;
	private JButton botaoCadastrarFuncionarios;
	private JButton botaoCadastrarDependentes;
	private JButton botaoListarDependentes;
	private JButton botaoListarFuncionarios;
	private JButton botaoExcluirFuncionarios;
	private JButton botaoExcluirDependentes;
	
	public JanelaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		this.setBounds(100, 100, 628, 382);
		getContentPane().setLayout(null);
		
		
		botaoCadastrarFuncionarios = new JButton("Cadastrar Funcionarios");
		botaoCadastrarFuncionarios.setBounds(216, 31, 188, 23);
		getContentPane().add(botaoCadastrarFuncionarios);
		botaoCadastrarFuncionarios.setVisible(false);
		botaoCadastrarFuncionarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroFuncionario funcionario = new CadastroFuncionario();
				funcionario.setVisible(true);
				
			}
		});
		
		
		botaoCadastrarDependentes = new JButton("Cadastrar Dependentes");
		botaoCadastrarDependentes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastroDependetes novo_dependente = new CadastroDependetes();
				novo_dependente.setVisible(true);
			}
		});
		botaoCadastrarDependentes.setBounds(216, 65, 188, 23);
		getContentPane().add(botaoCadastrarDependentes);
		botaoCadastrarDependentes.setVisible(false);
		
		botaoListarDependentes = new JButton("Listar Dependentes");
		botaoListarDependentes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ListarDependentes dep = new ListarDependentes();
				dep.setVisible(true);
			}
		});
		botaoListarDependentes.setBounds(216, 144, 188, 23);
		getContentPane().add(botaoListarDependentes);
		botaoListarDependentes.setVisible(false);
		
		botaoListarFuncionarios = new JButton("Listar Funcionarios");
		botaoListarFuncionarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ListarFuncionarios novo_funcionarios = new ListarFuncionarios();
				novo_funcionarios.setVisible(true);
			}
		});
		botaoListarFuncionarios.setBounds(216, 110, 188, 23);
		getContentPane().add(botaoListarFuncionarios);
		
		botaoExcluirDependentes = new JButton("Excluir Dependentes");
		botaoExcluirDependentes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ExcluirDependente exdep= new ExcluirDependente();
				exdep.setVisible(true);
			}
		});
		botaoExcluirDependentes.setBounds(216, 203, 188, 23);
		getContentPane().add(botaoExcluirDependentes);
		botaoExcluirDependentes.setVisible(false);
		
		botaoExcluirFuncionarios = new JButton("Excluir Funcionarios");
		botaoExcluirFuncionarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExcluirFuncionario exF = new ExcluirFuncionario();
				exF.setVisible(true);
			}
		});
		botaoExcluirFuncionarios.setBounds(216, 237, 188, 23);
		botaoExcluirFuncionarios.setVisible(false);
		getContentPane().add(botaoExcluirFuncionarios);
		botaoListarFuncionarios.setVisible(false);
		
			
		 menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
	    botaoIniciar = new JMenu("Iniciar");
		menuBar.add(botaoIniciar);
	
		
		JMenuItem botaoCadastrar = new JMenuItem("Cadastrar");
		botaoCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object obj =  e.getSource();
				if(obj==botaoCadastrar){
					
               botaoCadastrarFuncionarios.setVisible(true);
				botaoCadastrarDependentes.setVisible(true);
				 botaoListarDependentes.setVisible(false);
		            botaoListarFuncionarios.setVisible(false);
		            botaoExcluirDependentes.setVisible(false);
		            botaoExcluirFuncionarios.setVisible(false);
				}
								
			}
		});
		botaoIniciar.add(botaoCadastrar);
		
		JMenuItem botaoBuscar = new JMenuItem("Buscar/Atualizar");
		botaoBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object obj =  e.getSource();
				if(obj==botaoBuscar){
					 botaoCadastrarFuncionarios.setVisible(false);
						botaoCadastrarDependentes.setVisible(false);
						 botaoListarDependentes.setVisible(true);
				            botaoListarFuncionarios.setVisible(true);
				            botaoExcluirDependentes.setVisible(false);
				            botaoExcluirFuncionarios.setVisible(false);
				}
				
			}
		});
		botaoIniciar.add(botaoBuscar);
		
		JMenuItem botaoExcluir = new JMenuItem("Excluir");
		botaoExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 botaoCadastrarFuncionarios.setVisible(false);
					botaoCadastrarDependentes.setVisible(false);
					 botaoListarDependentes.setVisible(false);
			            botaoListarFuncionarios.setVisible(false);
			            botaoExcluirDependentes.setVisible(true);
			            botaoExcluirFuncionarios.setVisible(true);
				
			}
		});
		botaoIniciar.add(botaoExcluir);
	}

	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new JanelaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	@Override
	public void actionPerformed(ActionEvent e) {
		//Object obj = e.getSource();
		
	}
}
