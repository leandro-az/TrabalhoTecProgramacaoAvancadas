package dao;

import java.util.List;

import modelo.Dependente;
import modelo.Funcionario;
import anotacao.RecuperaLista;
import anotacao.RecuperaUltimoOuPrimeiro;
import excecao.ObjetoNaoEncontradoException;

public interface DependenteDAO extends DaoGenerico<Dependente, Long>
{	
	@RecuperaLista
	List<Dependente> recuperaListaDeDependentes();
	
	@RecuperaUltimoOuPrimeiro
	Dependente recuperaUltimoDependente(Funcionario funcionario)
		throws ObjetoNaoEncontradoException;
	
	@RecuperaLista
	List<Dependente> recuperaDependentesPeloIdDoFuncionario(long resp); 
	
	
	// Metodos finais em DependenteDAOImpl pois nao possuem notacao e sao usados somente para a criacao do JtableMOdel
	

	long recuperaQuantPeloIdDoFuncionario(long ident);

	List<Dependente> recuperaPeloID(long resp, int deslocamento, int linhasPorPagina);

	
}
