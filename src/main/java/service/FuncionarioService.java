package service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import dao.FuncionarioDAO;
import excecao.FuncionarioComDependentesException;
import excecao.FuncionarioNaoEncontradoException;
import excecao.ObjetoNaoEncontradoException;
import modelo.Funcionario;

public class FuncionarioService
{	
	
	private FuncionarioDAO funcionarioDAO=null;

	 public void setFuncionarioDAO(FuncionarioDAO FuncionarioDAO)
	{	this.funcionarioDAO = FuncionarioDAO;
	 
	}
	
	 
	 public FuncionarioDAO getFuncionario()
		{
         return this.funcionarioDAO ;
		 
		}
	 
	
	public long inclui(Funcionario umFuncionario) 
	{	return funcionarioDAO.inclui(umFuncionario).getNumero();
	}

	@Transactional
	public void altera(Funcionario umFuncionario)
		throws FuncionarioNaoEncontradoException
	{	try
		{	funcionarioDAO.getPorIdComLock(umFuncionario.getNumero());
			funcionarioDAO.altera(umFuncionario);
		} 
		catch(ObjetoNaoEncontradoException e)
		{	throw new FuncionarioNaoEncontradoException("Funcionario não encontrado");
		}
	}

	@Transactional
	public void exclui(Funcionario umFuncionario) 
		throws FuncionarioNaoEncontradoException, FuncionarioComDependentesException
	{	try
		{	
			Funcionario funcionario = funcionarioDAO.getPorId(umFuncionario.getNumero());

		if(funcionario.getDependentes().size() > 0)
		{	throw new FuncionarioComDependentesException("Este Funcionario possui dependentes e não pode ser removido");
			}

			funcionarioDAO.exclui(funcionario);
		} 
		catch(ObjetoNaoEncontradoException e)
		{	throw new FuncionarioNaoEncontradoException("Funcionario não encontrado");
		}
	}

	public Funcionario recuperaUmFuncionario(long numero) 
		throws FuncionarioNaoEncontradoException
	{	try
		{	return funcionarioDAO.getPorId(numero);
		} 
		catch(ObjetoNaoEncontradoException e)
		{	throw new FuncionarioNaoEncontradoException("Funcionario não encontrado");
		}
	}

	public Funcionario recuperaUmFuncionarioEDependentes(long numero) 
		throws FuncionarioNaoEncontradoException
	{	try
		{	return funcionarioDAO.recuperaUmFuncionarioEDependentes(numero);
		} 
		catch(ObjetoNaoEncontradoException e)
		{	throw new FuncionarioNaoEncontradoException("Funcionario não encontrado");
		}
	}

	public List<Funcionario> recuperaFuncionariosEDependentes()
	{	
		return funcionarioDAO.recuperaListaDeFuncionariosEDependentes();
	}

	public long recuperaQtdTotal() 
	{	
		return funcionarioDAO.recuperaQtdTotal();
	}

	public  List<Funcionario> recuperaNaOrdem( int deslocamento, int linhasPorPagina) 
	{	
		List<Funcionario> funcionario = funcionarioDAO.recuperaNaOrdem( deslocamento, linhasPorPagina);

		return funcionario;
	}
	
	public List<Funcionario> recuperaPeloNome(String nome, int deslocamento, int linhasPorPagina){
		
		List<Funcionario> funcionario = funcionarioDAO.recuperaPeloNome(nome, deslocamento, linhasPorPagina);

		return funcionario;
	}
}