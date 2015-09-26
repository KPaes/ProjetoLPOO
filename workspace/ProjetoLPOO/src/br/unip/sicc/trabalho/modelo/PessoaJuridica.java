package br.unip.sicc.trabalho.modelo;

import java.util.Objects;

public class PessoaJuridica{
	
	private Integer id=0;
	private String cnpj;
	private String razaoSocial;

	/* Construtores */
	public PessoaJuridica(Integer id, String cnpj, String razaoSocial){
		this.id = id;
		this.cnpj = cnpj;
		this.razaoSocial = razaoSocial;
	}

	public PessoaJuridica(){
	}
		
	
	/* Getters and Setters*/
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}


    @Override
    public String toString() {
        return "PessoaJuridica{" + "id=" + id + ", cnpj=" + cnpj + ", razaoSocial=" + razaoSocial + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.cnpj);
        hash = 97 * hash + Objects.hashCode(this.razaoSocial);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PessoaJuridica other = (PessoaJuridica) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.cnpj, other.cnpj)) {
            return false;
        }
        if (!Objects.equals(this.razaoSocial, other.razaoSocial)) {
            return false;
        }
        return true;
    }
    
    public boolean eNova(){
        if(this.getId().equals(0)){
            return true;
        }else{
            return false;
        }
    }


}
