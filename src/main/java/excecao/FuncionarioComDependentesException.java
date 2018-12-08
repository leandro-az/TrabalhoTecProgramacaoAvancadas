package excecao;

import anotacao.ConstraintViolada;
import anotacao.ExcecaoDeAplicacao;

@ExcecaoDeAplicacao
@ConstraintViolada(nome="FILME_CLIENTE_FK")
public class FuncionarioComDependentesException extends RuntimeException
{	
	private final static long serialVersionUID = 1;
	
	public FuncionarioComDependentesException()
	{	super();
	}

	public FuncionarioComDependentesException(String msg)
	{	super(msg);
	}
}	