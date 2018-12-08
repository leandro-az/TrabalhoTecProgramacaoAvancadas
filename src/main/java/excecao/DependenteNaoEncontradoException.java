package excecao;

import anotacao.ExcecaoDeAplicacao;

@ExcecaoDeAplicacao
public class DependenteNaoEncontradoException extends Exception
{	
	private final static long serialVersionUID = 1;

	public DependenteNaoEncontradoException(String msg)
	{	super(msg);
	}
}	