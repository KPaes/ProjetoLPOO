package br.unip.sicc.trabalho.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.unip.sicc.trabalho.modelo.PessoaJuridica;

public class PessoaJuridicaList implements PessoaJuridicaDAO {
    private static int NEXT_ID;

    private List<PessoaJuridica> pessoasJuridicas = new ArrayList();
    private List<PessoaJuridica> pessoasJuridicasFiltradas = new ArrayList();

    public PessoaJuridicaList() {
        PessoaJuridica pessoaJuridica1 = new PessoaJuridica(1,"88.140.000/0001-01", "GPA");
        pessoasJuridicas = new ArrayList<>();
        
        pessoasJuridicas.add(pessoaJuridica1);
        pessoasJuridicas.add(new PessoaJuridica(2,"00.033.055/0001-08", "”tica AP"));
        pessoasJuridicas.add(new PessoaJuridica(3,"00.033.055/0002-08", "”tica AP - Filial"));
        pessoasJuridicas.add(new PessoaJuridica(4,"10.893.125/0001-00", "Contadora JP Eireli"));
        NEXT_ID = 5;
    }

    
    
    @Override
    public void incluir(PessoaJuridica pessoaJuridica) throws DaoException {
        if(pessoaJuridica.eNova()){
        	pessoaJuridica.setId(NEXT_ID++);
            pessoasJuridicas.add(pessoaJuridica);
        }
    }

    @Override
    public void atualizar(PessoaJuridica pessoaJuridica) throws DaoException {
        if (!pessoasJuridicas.contains(pessoaJuridica)) {
            throw new DaoException("Pessoa Juridica n√£o existe");
        }else{
            int indice = pessoasJuridicas.indexOf(pessoaJuridica);
            pessoasJuridicas.set(indice, pessoaJuridica);
        }
    }

    @Override
    public void excluir(PessoaJuridica pessoaJuridica) throws DaoException {
        if (!pessoasJuridicas.contains(pessoaJuridica)) {
            throw new DaoException("PessoaJuridica n√£o existe");
        } else {
        	pessoasJuridicas.remove(pessoaJuridica);
        }
    }

    @Override
    public List<PessoaJuridica> getPessoasJuridicas() throws DaoException {
        return pessoasJuridicas;
    }

    @Override
    public List<PessoaJuridica> getPessoasJuridicas(PessoaJuridica filtro) throws DaoException {
    	pessoasJuridicasFiltradas.clear();
        if (!filtro.getId().equals(0)) {
            return getPessoaJuridicasById(filtro);
        }
        for (PessoaJuridica pessoaJuridica : pessoasJuridicas) {
            String razaoSocialFiltro = filtro.getRazaoSocial().toUpperCase();
            String razaoSocialPessoaJuridica = pessoaJuridica.getRazaoSocial().toUpperCase();
            if (razaoSocialFiltro.contains(razaoSocialPessoaJuridica)) {
            	pessoasJuridicasFiltradas.add(pessoaJuridica);
            }
        }
        return pessoasJuridicasFiltradas;
    }

    private List<PessoaJuridica> getPessoaJuridicasById(PessoaJuridica filtro) {
        for (PessoaJuridica pessoaJuridica : pessoasJuridicas) {
            if (pessoaJuridica.getId().equals(filtro.getId())) {
                pessoasJuridicasFiltradas.add(pessoaJuridica);
                return pessoasJuridicasFiltradas;
            }
        }
        return pessoasJuridicasFiltradas;
    }

}
