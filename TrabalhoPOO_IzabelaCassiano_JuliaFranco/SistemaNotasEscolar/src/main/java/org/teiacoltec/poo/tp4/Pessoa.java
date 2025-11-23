package org.teiacoltec.poo.tp4;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * Classe base de usuário do sistema com criptografia de senha.
 */
public abstract class Pessoa {

    private String cpf;
    private String nome;
    private Date nascimento;
    private String email;
    private String endereco;
    private String login;
    private String senhaCriptografada;

    public Pessoa(String cpf, String nome, Date nascimento, String email, String endereco, String login, String senha) {
        this.cpf = cpf;
        this.nome = nome;
        this.nascimento = nascimento;
        this.email = email;
        this.endereco = endereco;
        this.login = login;
        this.senhaCriptografada = criptografarSenha(senha);
    }

    public Pessoa(String cpf, String nome, Date nascimento, String email, String endereco, String login, String senha, boolean senhaJaCriptografada) {
        this.cpf = cpf;
        this.nome = nome;
        this.nascimento = nascimento;
        this.email = email;
        this.endereco = endereco;
        this.login = login;
        this.senhaCriptografada = senhaJaCriptografada ? senha : criptografarSenha(senha);
    }

    private String criptografarSenha(String senha) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(senha.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao criptografar senha", e);
        }
    }

    public boolean verificarSenha(String senha) {
        return this.senhaCriptografada.equals(criptografarSenha(senha));
    }

    public String obterInformacoes() {
        return "Nome: " + nome + "\n"
                + "CPF: " + cpf + "\n"
                + "Data de Nascimento: " + nascimento + "\n"
                + "E-mail: " + email + "\n"
                + "Endereco: " + endereco + "\n"
                + "Login: " + login;
    }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public Date getNascimento() { return nascimento; }
    public void setNascimento(Date nascimento) { this.nascimento = nascimento; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }
    public void alterarSenha(String novaSenha) { this.senhaCriptografada = criptografarSenha(novaSenha); }
    public String getSenhaCriptografada() { return this.senhaCriptografada; }
}
