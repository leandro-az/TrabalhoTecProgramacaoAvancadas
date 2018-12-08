package dao;

import java.util.List;
import java.util.Set;

import modelo.Funcionario;
import anotacao.RecuperaConjunto;
import anotacao.RecuperaLista;
import anotacao.RecuperaObjeto;
import excecao.ObjetoNaoEncontradoException;

public interface FuncionarioDAO extends DaoGenerico<Funcionario, Long>
{   
	/* ****** M�todos Gen�ricos ******* */

	@RecuperaObjeto
	Funcionario recuperaUmFuncionarioEDependentes(long numero) 
		throws ObjetoNaoEncontradoException;

	@RecuperaLista
	List<Funcionario> recuperaListaDeFuncionarios();
	
	@RecuperaLista
	List<Funcionario> recuperaListaDeFuncionariosEDependentes();

	@RecuperaConjunto
	Set<Funcionario> recuperaConjuntoDeFuncionariosEDependentes();
	
	
	
	
	
	/* ****** M�todos n�o Gen�ricos ******* */

	

	// Um m�todo definido aqui, que n�o seja anotado, dever� ser
	// implementado como final em FuncionarioDAOImpl.
	
	
	
	// Metodos finais pois nao possuem anotacao pois e usado somente para a criacao do FuncionarioJtableModel
	
	
     long recuperaQtdTotal();
	
	
	List<Funcionario> recuperaNaOrdem(int deslocamento, int linhasPorPagina);
	
	public  List<Funcionario> recuperaPeloNome(String nome, int deslocamento, int linhasPorPagina);
	
	
}
