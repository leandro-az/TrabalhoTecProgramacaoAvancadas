package visao;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import excecao.DependenteNaoEncontradoException;
import excecao.FuncionarioNaoEncontradoException;
import modelo.Dependente;
import modelo.Funcionario;
import service.DependenteService;
import service.FuncionarioService;
import util.ButtonColumn;
import util.DependentesModel;
import util.FuncionariosModelComBotao;

public class ExcluirDependente extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTable table_1;
	public JScrollPane scrollPane;
	private static DependenteService  dependenteAppService;
	DependentesModel dependentes;
	static FuncionarioService funcionarioAppService;
	
	@SuppressWarnings("unused")
	private Dependente dependente;
	

	private JTextField caixaDetextoDoIDDoDependente;
	public int aux2;
    public Long auxiliar;
	private Funcionario umfuncionario;
	FuncionariosModelComBotao funcionario;
	private JTextField caixaDoFuncionarioTitular;
	private JButton btnPesquisarFuncionario;
	private JButton btnListar;
	private Dependente umDependente;
	
		 static
		    {
		    	@SuppressWarnings("resource")
				ApplicationContext fabrica = new ClassPathXmlApplicationContext("beans-jpa.xml");

		    	funcionarioAppService = (FuncionarioService)fabrica.getBean ("FuncionarioService");
		    }
		 static
		    {
		    	@SuppressWarnings("resource")
				ApplicationContext fabrica = new ClassPathXmlApplicationContext("beans-jpa.xml");

		    	dependenteAppService = (DependenteService)fabrica.getBean ("DependenteService");
		    }

	
		 public ExcluirDependente() {
			 constroiJanela();
		 }
		 
	    public void constroiJanela() {
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 677, 413);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setVisible(true);
		scrollPane.setBounds(20, 103, 634, 186);
		getContentPane().add(scrollPane);
		
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVoltar.setBounds(562, 341, 89, 23);
		contentPane.add(btnVoltar);
		
		JLabel lblEscolhaOFuncionario = new JLabel("Pesquise O Funcionario Para Listar Seus Dependentes:");
		lblEscolhaOFuncionario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEscolhaOFuncionario.setHorizontalAlignment(SwingConstants.CENTER);
		lblEscolhaOFuncionario.setBounds(10, 11, 359, 30);
		contentPane.add(lblEscolhaOFuncionario);
		
		
		table_1 = new JTable();
		table_1.setBounds(20, 103, 634, 186);
		contentPane.add(table_1);
		table_1.setVisible(true);
		
		
		
		caixaDoFuncionarioTitular = new JTextField();
		caixaDoFuncionarioTitular.setBounds(379, 15, 272, 26);
		contentPane.add(caixaDoFuncionarioTitular);
		caixaDoFuncionarioTitular.setColumns(10);
		caixaDoFuncionarioTitular.setEditable(false);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Long temporario= Long.parseLong(caixaDetextoDoIDDoDependente.getText());
				try {
					umDependente=dependenteAppService.recuperaUmDependente(temporario);
					dependenteAppService.exclui(umDependente);
					JOptionPane.showMessageDialog(null, "Dependente Excluido Com Sucesso !");
					dispose();
				} catch (DependenteNaoEncontradoException e) {
					JOptionPane.showMessageDialog(null, "Dependente nao Encontrado !");
					
				}
				
			}
				
				
		});
		btnExcluir.setBounds(29, 341, 89, 23);
		contentPane.add(btnExcluir);
		
		caixaDetextoDoIDDoDependente = new JTextField();
		caixaDetextoDoIDDoDependente.setBounds(239, 300, 86, 30);
		contentPane.add(caixaDetextoDoIDDoDependente);
		caixaDetextoDoIDDoDependente.setColumns(10);
		
		btnPesquisarFuncionario = new JButton("Pesquisar Funcionario");
		btnPesquisarFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listarFuncionariosComBotao();
			}
		});
		btnPesquisarFuncionario.setBounds(474, 53, 177, 30);
		contentPane.add(btnPesquisarFuncionario);
		
		btnListar = new JButton("Listar");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dependentes = new DependentesModel(umfuncionario.getNumero());
				table_1.setModel(dependentes);
				scrollPane.setViewportView(table_1);
			}
		});
		btnListar.setBounds(20, 57, 89, 35);
		contentPane.add(btnListar);
		btnListar.setEnabled(false);
		
		JLabel lblDigiteOId = new JLabel("Digite o ID do Dependente:");
		lblDigiteOId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDigiteOId.setBounds(30, 300, 184, 30);
		contentPane.add(lblDigiteOId);
		
		
		
	}
		
	
	
	public void setValor(int n){
		   this.aux2=n;
	   }
	
	
	public  void listarFuncionariosComBotao() {
		  this.aux2=0;
		
		//auxiliar=funcionarioAppService.recuperaFuncionariosEDependentes().get(0).getNumero();
      
		setBounds(100, 100, 600, 400);
	    JPanel contentPane2 = new JPanel();
		contentPane2.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane2);
		contentPane2.setLayout(null);
		contentPane2.setVisible(true);
		
		
		JButton botaoVoltar = new JButton("Voltar");
		botaoVoltar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				 constroiJanela();
               setValor(1);
			}
			
		});
		if(this.aux2==1){
			try {
				this.finalize();
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		botaoVoltar.setBounds(55, 55, 89, 23);
		contentPane2.add(botaoVoltar);
		
		JTable table_1 = new JTable();
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			    auxiliar= (funcionario.id);
				
				try {
					
					umfuncionario=funcionarioAppService.recuperaUmFuncionario(auxiliar);
					constroiJanela();
					caixaDoFuncionarioTitular.setText(umfuncionario.toString());
					btnListar.setEnabled(true);
					
					
				    setValor(1);
				
				} catch (FuncionarioNaoEncontradoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			
			}
			
		});
		
		if(this.aux2==1){
			try {
				this.finalize();
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		table_1.setBounds(24, 89, 532, 228);
		contentPane2.add(table_1);
		
		
		table_1.setVisible(true);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVisible(true);
		scrollPane.setBounds(24, 89, 532, 228);
		getContentPane().add(scrollPane);
		
		
		
		JLabel lblDigiteONome = new JLabel("Digite o nome do Funcionario:");
		lblDigiteONome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDigiteONome.setBounds(21, 11, 191, 28);
		contentPane2.add(lblDigiteONome);
		
		JTextField caixaDetextoNomeFunc = new JTextField();
		caixaDetextoNomeFunc.setBounds(246, 13, 310, 28);
		contentPane2.add(caixaDetextoNomeFunc);
		caixaDetextoNomeFunc.setColumns(10);
		
		JButton btnListar = new JButton("Listar");
		//Pesquisa Pelo nome ---------------------------------
		
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String nome = caixaDetextoNomeFunc.getText() ;
				
				 funcionario = new FuncionariosModelComBotao(nome.toUpperCase());
				 table_1.setModel(funcionario);
				 scrollPane.setViewportView(table_1);
				 @SuppressWarnings("unused")
				ButtonColumn novoB= new ButtonColumn(table_1,5);
			  
			}
		});
		btnListar.setBounds(462, 55, 89, 23);
		contentPane2.add(btnListar);
		
		scrollPane.setVisible(true);
		
	}
}
