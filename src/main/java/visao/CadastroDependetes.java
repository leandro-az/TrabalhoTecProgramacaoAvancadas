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
import javax.swing.JRadioButton;
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
import util.FuncionariosModelComBotao;

public class CadastroDependetes extends JFrame implements ActionListener {

	
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    public int aux2;
    private JRadioButton flag_F;
	private JRadioButton flag_M;
	private String resp;
	private JTextField testo_Da_Idade;
	private Dependente umDependente;
	private static DependenteService  dependenteAppService;
	private static FuncionarioService  funcionarioAppService;
	
	public Funcionario aux;
	public Long auxiliar;
	
	private Funcionario umfuncionario;
	
	
	FuncionariosModelComBotao funcionario;
	
	private JTextField caixaDoFuncionarioTitular;
	
	
		
		
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
	
		

	public CadastroDependetes() {
		constroiJanela();
				
	}


	private void constroiJanela() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 628, 382);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		JTextField Caixa_De_textoNomeDependente;
		contentPane.setLayout(null);
		Caixa_De_textoNomeDependente = new JTextField();
		Caixa_De_textoNomeDependente.setBounds(174, 104, 305, 26);
		getContentPane().add(Caixa_De_textoNomeDependente);
		Caixa_De_textoNomeDependente.setColumns(10);
		
		JLabel Texto_Da_Caixa_Dependente = new JLabel("Nome do Dependente:");
		Texto_Da_Caixa_Dependente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Texto_Da_Caixa_Dependente.setBounds(10, 102, 147, 26);
		getContentPane().add(Texto_Da_Caixa_Dependente);
		
		JLabel Testo_do_Sexo = new JLabel("Sexo:");
		Testo_do_Sexo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Testo_do_Sexo.setBounds(111, 221, 52, 21);
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
		flag_M.setBounds(174, 222, 46, 23);
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
		flag_F.setBounds(236, 222, 46, 23);
		getContentPane().add(flag_F);;
		
		JLabel Testo_da_FaixetariaDeIdade = new JLabel("Idade:");
		Testo_da_FaixetariaDeIdade.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Testo_da_FaixetariaDeIdade.setBounds(111, 162, 52, 21);
		getContentPane().add(Testo_da_FaixetariaDeIdade);
		
		testo_Da_Idade = new JTextField();
		testo_Da_Idade.setBounds(174, 161, 36, 26);
		contentPane.add(testo_Da_Idade);
		testo_Da_Idade.setColumns(10);
		
		JButton botao_de_Cadastrar = new JButton("Cadastrar");
		botao_de_Cadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				umDependente = new Dependente();
				umDependente.setFuncionario(umfuncionario);
				umDependente.setIdade(Integer.parseInt(testo_Da_Idade.getText()));
				umDependente.setNome(Caixa_De_textoNomeDependente.getText().toUpperCase().trim());
				umDependente.setSexo(resp);
						try {
							
							dependenteAppService.inclui(umDependente);
							
							JOptionPane.showMessageDialog(null, "Dependente Cadastrado Com Sucesso !");
						    dispose();
						} catch (FuncionarioNaoEncontradoException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				    
				
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
		
		caixaDoFuncionarioTitular = new JTextField();
		caixaDoFuncionarioTitular.setBounds(173, 32, 305, 32);
		contentPane.add(caixaDoFuncionarioTitular);
		caixaDoFuncionarioTitular.setColumns(10);
		caixaDoFuncionarioTitular.setEditable(false);
		
		JLabel testo_FuncionarioTitular = new JLabel("Funcionario Titular:");
		testo_FuncionarioTitular.setFont(new Font("Tahoma", Font.PLAIN, 14));
		testo_FuncionarioTitular.setBounds(32, 36, 131, 21);
		contentPane.add(testo_FuncionarioTitular);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setBounds(499, 32, 89, 33);
		contentPane.add(btnPesquisar);
		
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				listarFuncionariosComBotao();
				
				
			}
		
		});
		
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
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
