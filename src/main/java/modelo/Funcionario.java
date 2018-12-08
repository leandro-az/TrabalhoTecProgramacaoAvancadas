package modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@NamedQueries(
		{	@NamedQuery
			(	name = "Funcionario.recuperaUmFuncionarioEDependentes",
				query = "select c from Funcionario c left outer join fetch c.dependentes where c.numero= ?1"
			),
			@NamedQuery
			(	name = "Funcionario.recuperaListaDeFuncionarios",
				query = "select c from Funcionario c order by c.numero"
			),
			@NamedQuery
			(	name = "Funcionario.recuperaListaDeFuncionariosEDependentes",
				query = "select distinct c from Funcionario c left outer join fetch c.dependentes order by c.numero asc"
			),
			@NamedQuery
			(	name = "Funcionario.recuperaConjuntoDeFuncionariosEDependentes",
				query = "select c from Funcionario c left outer join fetch c.dependentes order by c.numero asc"
			)
		})

@Entity
@Table(name="FUNCIONARIO")
@SequenceGenerator(name="SEQUENCIA", 
		           sequenceName="SEQ_FUNCIONARIO",
		           allocationSize=1)

public class Funcionario
{	

	private Long numero;
	private String nome;
	private String sexo;
	private int idade;
	private float salario;

	//  Um Funcionario possui dependentes

	private List<Dependente> dependentes = new ArrayList<Dependente>();
	
	// ********* Construtores *********

	public Funcionario()
	{
	}

	public Funcionario(String nome, String sexo, int idade, float salario) 
	{
		super();
		this.nome = nome;
		this.sexo = sexo;
		this.idade = idade;
		this.salario=salario;
		
	}

	// ********* Métodos do Tipo Get *********

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQUENCIA")
	@Column(name="NUMERO")
	public Long getNumero() 
	{	return numero;
	}
	
	public String getNome() 
	{	return nome.toUpperCase();
	}

	public String getSexo() 
	{	return sexo;
	}
	
	public int getIdade() 
	{	return idade;
	}
	
	public float getSalario() {
		return salario;
	}
	
	
	

	// ********* Métodos do Tipo Set *********

	
	@SuppressWarnings("unused")
	private void setNumero(Long numero) 
	{	this.numero = numero;
	}
	
	public void setNome(String nome)
	{	this.nome = nome;
	}
	
	
	public void setSexo(String sexo) 
	{	this.sexo = sexo;
	}
	
	public void setIdade(int idade) 
	{	this.idade = idade;
	}
	
	public void setSalario(float salario) {
		this.salario = salario;
	}
	
	
	public String toString()
	{	return "ID = " + numero + "  || Nome = " + nome ;
	}
	
	// ********* Métodos para Associações *********

	@OneToMany(mappedBy = "funcionario")
	@OrderBy
	/*
	 * Com o atributo mappedBy. Sem ele a  JPA irá procurar  pela 
	 * tabela funcionario_depe. Com ele, ao se  tentar recuperar  um  
	 * funcionario  e  todos  os  seus  dependentes, o  join de funcionario  e 
	 * dependente irá acontecer através da chave estrangeira existente
	 * em  filmes.  
	 */
	public List<Dependente> getDependentes()
	{	return dependentes;
	}
	
	public void setDependentes(List<Dependente> dependentes)
	{	this.dependentes = dependentes;	
	}
}

