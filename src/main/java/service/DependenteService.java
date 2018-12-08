package service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import dao.DependenteDAO;
import dao.FuncionarioDAO;
import excecao.DependenteNaoEncontradoException;
import excecao.FuncionarioNaoEncontradoException;
import excecao.ObjetoNaoEncontradoException;
import modelo.Dependente;

public class DependenteService
{	
	@SuppressWarnings("unused")
	private FuncionarioDAO funcionarioDAO = null;
	private DependenteDAO dependenteDAO = null;
	
	public void setFuncionarioDAO(FuncionarioDAO funcionarioDAO)
	{	this.funcionarioDAO = funcionarioDAO;
	}

	public void setDependenteDAO(DependenteDAO dependenteDAO)
	{	this.dependenteDAO = dependenteDAO;
	}

	@Transactional
	public long inclui(Dependente umDependente) throws FuncionarioNaoEncontradoException 
	{
		@SuppressWarnings("unused")
		Dependente dependente = dependenteDAO.inclui(umDependente);
		return umDependente.getNumero();
	}	
	
	@Transactional
	public long altera(Dependente umDependente) throws FuncionarioNaoEncontradoException 
	{
		dependenteDAO.altera(umDependente);
		return umDependente.getNumero();
	}

	@Transactional
	public void exclui(Dependente umDependente) 
		throws DependenteNaoEncontradoException 
	{	try
		{	umDependente = dependenteDAO.getPorId(umDependente.getNumero());
			dependenteDAO.exclui(umDependente);
		} 
		catch(ObjetoNaoEncontradoException e)
		{	throw new DependenteNaoEncontradoException("Dependente não encontrado.");
		}
	}

	public Dependente recuperaUmDependente(long numero) 
		throws DependenteNaoEncontradoException
	{	try
		{	return dependenteDAO.getPorId(numero);
		} 
		catch(ObjetoNaoEncontradoException e)
		{	throw new DependenteNaoEncontradoException("Dependente não encontrado");
		}
	}

	public List<Dependente> recuperaDependentes()
	{	return dependenteDAO.recuperaListaDeDependentes();
	}

	public long recuperaQuantPeloIdDoFuncionario(long ident) {
		return dependenteDAO.recuperaQuantPeloIdDoFuncionario(ident);
		
	}

	public List<Dependente> recuperaPeloID(long resp, 
			  int deslocamento, 
			  int linhasPorPagina){
		
		return dependenteDAO.recuperaPeloID(resp, deslocamento, linhasPorPagina);
	}

	public List<Dependente> recuperaDependentesPeloIdDoFuncionario(long resp) throws ObjetoNaoEncontradoException
	{	
		return dependenteDAO.recuperaDependentesPeloIdDoFuncionario(resp);
	}
}