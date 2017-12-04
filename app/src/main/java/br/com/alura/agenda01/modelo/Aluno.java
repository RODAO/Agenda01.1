package br.com.alura.agenda01.modelo;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by DELL on 19/09/2017.
 */
public class Aluno implements Serializable {
    private Long id;
    private String nome;
    private String endereco;
    private String telefone;
    private String site;
    private Double nota;
    private String email;
    private String caminhofoto;

    public String getEmail() {  return email;    }

    public void setEmail(String email) {  this.email = email; }

    //private double nota;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public String getCaminhofoto() {
        return caminhofoto;
    }

    public void setCaminhofoto(String caminhofoto) {
        this.caminhofoto = caminhofoto;
    }


    @Override
    public String toString() {
        return Long.toString(this.getId()) + " - " + getNome();
    }


}


