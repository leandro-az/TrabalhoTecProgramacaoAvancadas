package visao;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import excecao.FuncionarioNaoEncontradoException;
import modelo.Dependente;
import modelo.Funcionario;
import service.DependenteService;
import service.FuncionarioService;
import util.ButtonColumn;
import util.DependentesModel;
import util.FuncionariosModelComBotao;

public class ListarDependentes extends JFrame implements ActionListener {

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table_1;
	private JScrollPane scrollPane;
	//private List<Dependente> depe;
	DependentesModel dependentes;
	Funcionario aux;
	public int aux2;
	public Long auxiliar;
	private Funcionario umfuncionario;
	FuncionariosModelComBotao funcionario;
	private JTextField caixaDoFuncionarioTitular;
	private static FuncionarioService  funcionarioAppService;
		
		
		 static
		    {
		    	@SuppressWarnings("resource")
				ApplicationContext fabrica = new ClassPathXmlApplicationContext("beans-jpa.xml");

		    	funcionarioAppService = (FuncionarioService)fabrica.getBean ("FuncionarioService");
		    }
		 
	@SuppressWarnings("unused")
	private Dependente umDependente;
	@SuppressWarnings("unused")
	private static DependenteService  dependenteAppService;
	private JButton btnListar;
	private JButton btnPesquisar;
	 static
	    {
	    	@SuppressWarnings("resource")
			ApplicationContext fabrica = new ClassPathXmlApplicationContext("beans-jpa.xml");

	    	dependenteAppService = (DependenteService)fabrica.getBean ("DependenteService");
	    }

	
	 public ListarDependentes() {
		 constroiJanela();
	 }
	 
	 
	public void constroiJanela() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 500);
		
	
		JLabel lblSelecioneOFuncionario = new JLabel("Selecione O Funcionario:");
		lblSelecioneOFuncionario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSelecioneOFuncionario.setBounds(10, 28, 157, 21);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(lblSelecioneOFuncionario);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table_1 = new JTable();
		table_1.setBounds(20, 127, 634, 250);
		contentPane.add(table_1);
		table_1.setVisible(true);
		
		scrollPane = new JScrollPane();
		scrollPane.setVisible(true);
		scrollPane.setBounds(20, 127, 634, 250);
		getContentPane().add(scrollPane);
		
		
		caixaDoFuncionarioTitular = new JTextField();
		caixaDoFuncionarioTitular.setBounds(191, 24, 463, 32);
		contentPane.add(caixaDoFuncionarioTitular);
		caixaDoFuncionarioTitular.setColumns(10);
		caixaDoFuncionarioTitular.setEditable(false);
		
		
		JButton botaoVoltar = new JButton("Voltar");
		botaoVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		botaoVoltar.setBounds(270, 415, 127, 36);
		contentPane.add(botaoVoltar);
		
		btnListar = new JButton("Listar");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				dependentes = new DependentesModel(umfuncionario.getNumero());
				
				table_1.setModel(dependentes);
				scrollPane.setViewportView(table_1);
				
			}
		});
		
		btnListar.setBounds(552, 85, 89, 23);
		contentPane.add(btnListar);
		btnListar.setEnabled(false);
		
		
		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listarFuncionariosComBotao();
				
			}
		});
		btnPesquisar.setBounds(187, 85, 89, 23);
		contentPane.add(btnPesquisar);
		
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


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
