package dao.impl;


import modelo.Funcionario;

import java.util.List;

import org.springframework.stereotype.Repository;

import dao.FuncionarioDAO;

@Repository
public abstract class FuncionarioDAOImpl
       extends JPADaoGenerico<Funcionario, Long> implements FuncionarioDAO 
{   
    //Construtor
	public FuncionarioDAOImpl()
    { 	
    	super(Funcionario.class); 
    }
    
	
	
	
	
	
	
	
	
	
	//********************** Metodos usados para Paginacao *******************************************************************
	
	
	// Metodo final  nao possui anotacao pois e usado somente para a criacao do FuncionarioJtableModel
	
	
    public final long recuperaQtdTotal(){	
    	long qtd = (Long) em.createQuery("select count(c) from Funcionario c")		.getSingleResult();
		return qtd;
	}
    
 // Metodo final pois nao possui anotacao pois e usado somente para a criacao do FuncionarioJtableModel
	public final  List<Funcionario> recuperaNaOrdem(int deslocamento, int linhasPorPagina){	
		@SuppressWarnings("unchecked")
		List<Funcionario> funcionario = em
			.createQuery("select c from Funcionario c " + "order by c.id asc").setFirstResult(deslocamento)
			.setMaxResults(linhasPorPagina)
			.getResultList();

		return funcionario;
	}
	
	// Metodo final pois nao possui anotacao pois e usado somente para a criacao do FuncionarioJtableModel
	@SuppressWarnings("unchecked")
	public  final List<Funcionario> recuperaPeloNome(String nome,int deslocamento, int linhasPorPagina){	
		List<Funcionario> funcionarios = em
			.createQuery("select c from Funcionario c "+ "where c.nome like '%"+ nome + "%'order by c.nome asc")
			.setFirstResult(deslocamento)
			.setMaxResults(linhasPorPagina)
			.getResultList();

		return funcionarios;
		}
}
