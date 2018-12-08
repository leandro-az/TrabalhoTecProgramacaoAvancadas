package dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import modelo.Dependente;
import dao.DependenteDAO;

@Repository
public abstract class DependenteDAOImpl 
	extends JPADaoGenerico<Dependente, Long> implements DependenteDAO
{
	public DependenteDAOImpl() 
	{	super(Dependente.class);		
	}
	
	// Este metodo e final e nao possui notacao especifica e usado somente para a geracao do  DependenteJtableModel
	
	@SuppressWarnings("unchecked")
	public final List<Dependente> recuperaPeloID(long resp, 
            							  int deslocamento, 
            							  int linhasPorPagina)
	{	
		List<Dependente> dependentes = em
			.createQuery("select c from Dependente c "
					   + "where c.funcionario.numero = :resp order by c.nome asc")
			.setParameter("resp", resp)
			.setFirstResult(deslocamento)
			.setMaxResults(linhasPorPagina)
			.getResultList();

		return dependentes;
	
	}
	
	// Este metodo e final e nao possui notacao especifica pois e usado somente para a geracao do DependenteJtableModel
	public final long recuperaQuantPeloIdDoFuncionario(long resp) {
		long qtd = (Long) em.createQuery("select count(c) from Dependente c  where  c.funcionario.numero = :resp")
				.setParameter("resp", resp)
				.getSingleResult();
      return qtd;
	}
	
	
	
	
}
