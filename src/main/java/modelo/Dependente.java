package modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@NamedQueries(
		{	@NamedQuery
			(	name = "Dependente.recuperaListaDeDependentes",
				query = "select f from Dependente f order by f.numero"
			),
			@NamedQuery
			(	name = "Dependente.recuperaUltimoDependente",
				query = "select f from Dependente f where f.funcionario = ?1 order by f.numero desc"
			),
			@NamedQuery
			(	name = "Dependente.recuperaUmDependenteComFuncionario",
				query = "select f from Dependente f left outer join fetch f.funcionario where f.numero = ?1"
			)
		,
		@NamedQuery
		(	name = "Dependente.recuperaDependentesPeloIdDoFuncionario",
			query = "select f from Dependente f left outer join fetch f.funcionario where f.numero = ?1 order by f.numero desc"
		)
		
		})
		
@Entity
@Table(name="DEPENDENTE")
@SequenceGenerator(name="SEQUENCIA2", 
		           sequenceName="SEQ_DEPENDENTE",
		           allocationSize=1)

public class Dependente
{	

	private Long numero;
	private String nome;
	private String sexo;
	private int idade;
		
	// Um Dependente se refere a um único Funcionario

	
	
	private Funcionario funcionario;

	// ********* Construtores *********

	public Dependente()
	{
	}

	public Dependente(String nome, String sexo, int idade, Funcionario titular) 
	{
		super();
		this.nome = nome;
		this.sexo = sexo;
		this.idade = idade;
		this.funcionario=(titular);
	}

	// ********* Métodos do Tipo Get *********

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQUENCIA2")
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

	// ********* Métodos para Associações *********

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="FUNCIONARIO_TITULAR")
	public Funcionario getFuncionario()
	{	return funcionario;
	}
	
	public void setFuncionario(Funcionario funcionario)
	{	this.funcionario = funcionario;
	}
	
	@Override
	public String toString()
	{	return "ID = " + numero + " ||  Nome = " + nome + " || Sexo = " + sexo + " || Idade = " + idade;
	}
}	