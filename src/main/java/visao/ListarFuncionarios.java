package visao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import modelo.Funcionario;
import service.FuncionarioService;
import util.FuncionariosModel;

public class ListarFuncionarios extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scrollPane;
    @SuppressWarnings("unused")
	private static FuncionarioService  funcionarioAppService;
	@SuppressWarnings("unused")
	private Funcionario umfuncionario;
	private JTable table_1;
	
	 static
	    {
	    	@SuppressWarnings("resource")
			ApplicationContext fabrica = new ClassPathXmlApplicationContext("beans-jpa.xml");

	    	funcionarioAppService = (FuncionarioService)fabrica.getBean ("FuncionarioService");
	    }
	 
	@SuppressWarnings({ })
	public ListarFuncionarios() {
				
		FuncionariosModel funcionario = new FuncionariosModel();
		
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JButton botaoVoltar = new JButton("Voltar");
		botaoVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		botaoVoltar.setBounds(261, 328, 89, 23);
		contentPane.add(botaoVoltar);
		
		table_1 = new JTable();
		table_1.setBounds(27, 44, 531, 253);
		contentPane.add(table_1);
		
		table_1.setModel(funcionario);
		
		//ButtonColumn novoB= new ButtonColumn(table_1,5);
		table_1.setVisible(true);
		
		scrollPane = new JScrollPane();
		scrollPane.setVisible(true);
		scrollPane.setBounds(24, 61, 517, 233);
		getContentPane().add(scrollPane);
		
		scrollPane.setViewportView(table_1);
		
		scrollPane.setVisible(true);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
