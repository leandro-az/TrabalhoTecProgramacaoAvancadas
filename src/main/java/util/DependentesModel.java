package util;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import excecao.FuncionarioNaoEncontradoException;
import modelo.Dependente;
import service.DependenteService;


public class DependentesModel extends AbstractTableModel 
{
	private static final long serialVersionUID = 1L;
	public static final int COLUNA_NUMERO = 0;
	public static final int COLUNA_SEXO = 2;
	public static final int COLUNA_NOME = 1;
	//public static final int COLUNA_FUNCIONARIO_TITULAR = 4;
	public static final int COLUNA_IDADE = 3;

	private final static int NUMERO_DE_LINHAS_POR_PAGINA = 9;
	
	private static DependenteService dependenteService;
	
    static
    {
    	@SuppressWarnings("resource")
		ApplicationContext fabrica = new ClassPathXmlApplicationContext("beans-jpa.xml");

    	dependenteService = (DependenteService)fabrica.getBean ("DependenteService");
    }

    private Map<Integer, Dependente> cache;
    private int rowIndexAnterior = 0;
    private Integer qtd;
    private long ident;
    
    public DependentesModel(long ident)
	{	
    	this.qtd = null;
		this.cache = new HashMap<Integer, Dependente>(NUMERO_DE_LINHAS_POR_PAGINA * 2 * 100 / 75 + NUMERO_DE_LINHAS_POR_PAGINA / 3);
		this.ident = ident;
	}

   /* public void setTitular(long ident)
    {
    	this.ident = ident;
    }*/
    
	public String getColumnName(int c)
	{
		if(c == COLUNA_NUMERO) return "ID";
		if(c == COLUNA_SEXO) return "SEXO";
		if(c == COLUNA_NOME) return "Nome";
		//if(c == COLUNA_FUNCIONARIO_TITULAR) return "ID_DO_FUNC_TITULAR";
		if(c == COLUNA_IDADE) return "idade";
		return null;
	}
	
	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public int getRowCount() {
		if(qtd == null)
			qtd = (int)dependenteService.recuperaQuantPeloIdDoFuncionario(ident);
		

		return qtd;
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex)
	{   
		if (!cache.containsKey(rowIndex)) 
		{	
			System.out.println(">>>>>>>>>>>> cache não tem rowIndex = " + rowIndex);
			System.out.println(">>>>>>>>>>>> tamanho = " + this.cache.size());
				
			if(cache.size() > (NUMERO_DE_LINHAS_POR_PAGINA * 2))
			{	
				System.out.println(">>>>>>>>>>>>>>>>>...... Vai limpar .......");
				
				cache.clear();
				
				if(rowIndex >= rowIndexAnterior) 
				{
					// O cache é maior do que 80 e estamos navegando para baixo
					
					// Se, por exemplo, rowindex = 120 queremos recuperar 2 páginas:
					
					// de 101 a 120 (página atual)
					// de 121 a 140 (próxima página - para baixo)

					System.out.println("Como estamos navegando para baixo e como a linha " + rowIndex + " não foi encontrada no cache (que foi apagado), vamos recuperar do banco 40 linhas com deslocamento de " + (rowIndex - 19));
					
					// A tabela não pode ter mais de 20 linhas
					List<Dependente> resultados = dependenteService
						.recuperaPeloID(ident, rowIndex - (NUMERO_DE_LINHAS_POR_PAGINA - 1), NUMERO_DE_LINHAS_POR_PAGINA * 2);
				
					for (int j = 0; j < resultados.size(); j++) 
					{	Dependente dependente = resultados.get(j);
						cache.put(rowIndex - (NUMERO_DE_LINHAS_POR_PAGINA - 1) + j, dependente);
					}
				}
				else
				{
					int inicio = rowIndex - NUMERO_DE_LINHAS_POR_PAGINA;
					if (inicio < 0) inicio = 0;
				
					// O cache é maior do que 80 e estamos navegando para cima
					
					// Se, por exemplo, rowindex = 121 então queremos recuperar 2 páginas:
					
					// de 101 a 120 (página anterior - para cima)
					// de 121 a 140 (página atual)
					
					System.out.println("Como estamos navegando para cima e como a linha " + rowIndex + " não foi encontrada no cache (que foi apagado), vamos recuperar do banco 40 linhas com deslocamento de " + inicio);
					
					List<Dependente> resultados = dependenteService
							.recuperaPeloID(ident, inicio, NUMERO_DE_LINHAS_POR_PAGINA * 2);
					
					System.out.println("resultados = " + resultados.size());
					
					for (int j = 0; j < resultados.size(); j++) 
					{	Dependente dependente = resultados.get(j);
						cache.put(inicio + j, dependente);
					}
				}
				
				System.out.println(">>>>>>>>>>>>>>>>>...... Tamanho = " + this.cache.size());
			}
			else
			{
				if(rowIndex >= rowIndexAnterior) 
				{
					// O cache não é maior do que 80 e estamos navegando para baixo
					
					// Se, por exemplo, rowindex = 121 vamos recuperar 2 páginas (além das entradas que estão no cache):
					
					// de 121 a 160 (a linha atual (121) e mais 39 linhas (quase duas páginas - supondo cada página com 20 linhas))

					System.out.println("Como estamos navegando para baixo e a linha " + rowIndex + " não foi encontrada, vamos recuperar do banco 40 linhas com um deslocamento de " + rowIndex);
					
					List<Dependente> resultados = dependenteService
							.recuperaPeloID(ident, rowIndex, 
								NUMERO_DE_LINHAS_POR_PAGINA * 2);
					
					for (int j = 0; j < resultados.size(); j++) 
					{	Dependente dependente = resultados.get(j);
						cache.put(rowIndex + j, dependente);
					}
				}
				else
				{
					int inicio = rowIndex - (NUMERO_DE_LINHAS_POR_PAGINA * 2 - 1);
					if (inicio < 0) inicio = 0;
				
					// O cache não é maior do que 80 e estamos navegando para cima
					
					// Se, por exemplo, rowindex = 139 vamos recuperar 2 páginas (além das entradas que estão no cache):
					
					// de 100 a 139 (a linha atual (139) e mais 39 linhas anteriores (quase duas páginas - supondo cada página com 20 linhas))

					System.out.println("Como estamos navegando para cima e a linha " 
							+ rowIndex + " não foi encontrada, vamos recuperar do banco "
									+ "40 linhas com inicio a partir de " + inicio);
					
					List<Dependente> resultados = dependenteService
							.recuperaPeloID(ident, inicio, NUMERO_DE_LINHAS_POR_PAGINA * 2);
					
					System.out.println("resultados = " + resultados.size());
					
					for (int j = 0; j < resultados.size(); j++) 
					{	Dependente dependente = resultados.get(j);
						cache.put(inicio + j, dependente);
					}
				}
			}
        }

		rowIndexAnterior = rowIndex;
        
		Dependente dependente = cache.get(rowIndex);

		if(columnIndex == COLUNA_NUMERO)
			return dependente.getNumero();
		else if (columnIndex == COLUNA_SEXO)
			return dependente.getSexo();
		else if (columnIndex == COLUNA_NOME)
			return dependente.getNome();
		//else if (columnIndex == COLUNA_FUNCIONARIO_TITULAR)
		//	return dependente.getFuncionario().getNome();
		else if (columnIndex == COLUNA_IDADE)
			return dependente.getIdade();
		else
			return null;
	}
	
	// Para que os campos booleanos sejam renderizados como check box.
	// Neste caso, não há campo boleano.
	public Class<?> getColumnClass(int c)
	{
		Class<?> classe = null;
		if(c == COLUNA_NUMERO) classe = Long.class;
		if(c == COLUNA_SEXO) classe = String.class;
		if(c == COLUNA_NOME) classe = String.class;
		//if(c == COLUNA_FUNCIONARIO_TITULAR) classe = Long.class;
		if(c == COLUNA_IDADE) classe = Integer.class;
		return classe;
	}
	
	// Para que as células referentes às colunas 1 em diante possam ser editadas
	public boolean isCellEditable(int r, int c)
	{
		return true;
	}
	
	@Override
	public void setValueAt(Object obj, int r, int c) 
	{
		Dependente umDependente = cache.get(r);

		//if(c == COLUNA_NUMERO) um.setNumero((String)obj);
		if(c == COLUNA_SEXO) umDependente.setSexo((String)obj);
		if(c == COLUNA_NOME) umDependente.setNome((String)obj);
		//if(c == COLUNA_SALARIO) umDependente.setSalario((float)obj);
		if(c == COLUNA_IDADE) umDependente.setIdade((int)obj);

		try 
		{	dependenteService.altera(umDependente);
		} 
		catch (FuncionarioNaoEncontradoException e) 
		{	e.printStackTrace();
		}
	}
}


