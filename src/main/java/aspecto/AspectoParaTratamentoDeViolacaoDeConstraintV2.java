package aspecto;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.reflections.Reflections;
import org.springframework.dao.DataIntegrityViolationException;

import excecao.ViolacaoDeConstraintDesconhecidaException;

//Para utilizar este aspecto � preciso:
// 1. Sempre que for definida uma nova constraint no CREATE TABLE ser� necess�rio criar  
//    uma exce��o que dever� ser anotada com:
//    1. ExcecaoDeAplicacao
//    2. ConstraintViolada(nome="nome-da-constraint-violada")
// 2. Acrescentar no programa principal um catch para capturar essa exce��o.

@Aspect
public class AspectoParaTratamentoDeViolacaoDeConstraintV2 
{
	private static Map<String, Class<?>> map = new HashMap<String, Class<?>>();
	private static List<String> listaDeNomesDeConstraints;
	
	static
	{
		Reflections reflections = new Reflections("excecao");

		Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(anotacao.ConstraintViolada.class);	

		for(Class<?> classe : annotated)
		{
			map.put(classe.getAnnotation(anotacao.ConstraintViolada.class).nome(), classe);
		}
		
		listaDeNomesDeConstraints = new ArrayList<String>(map.keySet());
	}
	
	@Pointcut("call(* service.*.*(..))")
	public void traduzExcecaoAround() {}

	@Around("traduzExcecaoAround()")
	public Object traduzExcecaoAround(ProceedingJoinPoint joinPoint) throws Throwable 
	{	try
		{	return joinPoint.proceed();
		}
		catch(org.springframework.dao.DataAccessException e)
		{	
			System.out.println("1. " + e.getClass().getName());
			
			Throwable t = e;
			
			if( t instanceof DataIntegrityViolationException)
			{	
				System.out.println("2. " + e.getClass().getName());

				t = t.getCause();
				while (t != null && !(t instanceof SQLException))
				{
					t = t.getCause();
				}
				
				String msg = (t.getMessage() != null) ? t.getMessage() : "";
				
				for(String nomeDeConstraint : listaDeNomesDeConstraints)
				{
					if(msg.indexOf(nomeDeConstraint) != -1)
					{
						throw (Exception)map.get(nomeDeConstraint).newInstance();
					}
				}
				throw new ViolacaoDeConstraintDesconhecidaException
					("A opera��o n�o foi realizada em fun��o da viola��o de uma restri��o no banco da dados.");
			}
			else
			{	
				System.out.println("3. " + e.getClass().getName());
				throw e;
			}
		}
	}
}