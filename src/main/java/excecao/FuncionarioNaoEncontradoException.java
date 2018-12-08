package excecao;

import anotacao.ExcecaoDeAplicacao;

@ExcecaoDeAplicacao
public class FuncionarioNaoEncontradoException extends Exception
{	
	private final static long serialVersionUID = 1;
	
	public FuncionarioNaoEncontradoException(String msg)
	{	super(msg);
	}
}	