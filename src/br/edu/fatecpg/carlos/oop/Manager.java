package br.edu.fatecpg.carlos.oop;

/**
 * TODO
 *
 * @author Carlos Henrique
 */
public class Manager extends Employee {

	private String projetoGerenciado;

    Manager() {
        projetoGerenciado = "NAOEXISTE";
    }

    public String getManagingProject() {
        return projetoGerenciado;
    }

    public void setManagingProject(String managingProject) {
        this.projetoGerenciado = managingProject;
    }
}
