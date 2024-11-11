package modelo;

import java.util.Date;

public class HistoricoPeso {
    private int id;
    private String cpfAluno;
    private Date data;
    private double peso;
    private String nomeAluno;

   
    // Construtor com parâmetros
    public HistoricoPeso(int id, String cpfAluno, Date data, double peso,String nomeAluno) {
        this.id = id;
        this.cpfAluno = cpfAluno;
        this.data = data;
        this.peso = peso;
        this.nomeAluno=nomeAluno;
    }
    
    public HistoricoPeso(String cpfAluno, Date data, double peso,String nomeAluno) {
        this.cpfAluno = cpfAluno;
        this.data = data;
        this.peso = peso;
        this.nomeAluno=nomeAluno;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCpfAluno() {
        return cpfAluno;
    }

    public void setCpfAluno(String cpfAluno) {
        this.cpfAluno = cpfAluno;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }
}
