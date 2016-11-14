package br.edu.fatecpg.carlos.oop;

/**
 * TODO
 *
 * @author Carlos Henrique
 */
public class Employee {

    private String id;
    private String nome;
    private String numeroContato;
    private int pontos;

    private BusinessUnit businessUnit;
    private Manager manager;

    Employee() {
        id = "NOTSET";
        nome = "NOTSET";
        numeroContato = "NOTSET";
        pontos = 0;
        businessUnit = null;
        manager = null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }    

    public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNumeroContato() {
		return numeroContato;
	}

	public void setNumeroContato(String numeroContato) {
		this.numeroContato = numeroContato;
	}

	public int getPontos() {
		return pontos;
	}

	public void setPontos(int pontos) {
		this.pontos = pontos;
	}

	public BusinessUnit getBusinessUnit() {
        return businessUnit;
    }

    public void setBusinessUnit(BusinessUnit businessUnit) {
        this.businessUnit = businessUnit;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public void darPontos(int pontos) {
        this.pontos += pontos;
    }

    public String toString() {
        return "[" + id + "," + nome + "," + numeroContato + "," + pontos + "]";
    }
}
