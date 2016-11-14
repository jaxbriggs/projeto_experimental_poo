package br.edu.fatecpg.carlos.oop;

/**
 * TODO
 *
 * @author Carlos Henrique
 */
public class BusinessUnit {
    private String id;
    private String nome;

    BusinessUnit() {
        id = "NAOEXISTE";
        nome = "NAOEXISTE";
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
    
}
