package com.loremipsum.recifeguide;

/**
 * Created by Dannyllo on 30/09/2016.
 */
public class Usuario {
    String Nome;
    int IdUsuario;
    String Email;
    String DataNascimento;
    String SocialId;
    String Sobrenome;
    String Cidade;
    String UF;
    String Sexo;
    String Senha;
    String TipoUsuario;
    String StatusRegistro;

    public Usuario() {
        Nome = "";
        IdUsuario = 0;
        Email = "";
        DataNascimento = "";
        SocialId = "";
        Sobrenome = "";
        Cidade = "";
        UF = "";
        Sexo = "";
        Senha = "";
        TipoUsuario = "";
        StatusRegistro = "";
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        IdUsuario = idUsuario;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getDataNascimento() {
        return DataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        DataNascimento = dataNascimento;
    }

    public String getSocialId() {
        return SocialId;
    }

    public void setSocialId(String socialId) {
        SocialId = socialId;
    }

    public String getSobrenome() {
        return Sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        Sobrenome = sobrenome;
    }

    public String getCidade() {
        return Cidade;
    }

    public void setCidade(String cidade) {
        Cidade = cidade;
    }

    public String getUF() {
        return UF;
    }

    public void setUF(String UF) {
        this.UF = UF;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String sexo) {
        Sexo = sexo;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String senha) {
        Senha = senha;
    }

    public String getTipoUsuario() {
        return TipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        TipoUsuario = tipoUsuario;
    }

    public String getStatusRegistro() {
        return StatusRegistro;
    }

    public void setStatusRegistro(String statusRegistro) {
        StatusRegistro = statusRegistro;
    }
}
