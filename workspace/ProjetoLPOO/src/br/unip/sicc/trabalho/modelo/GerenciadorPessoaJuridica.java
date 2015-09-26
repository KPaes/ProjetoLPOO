package br.unip.sicc.trabalho.modelo;

import java.util.List;

import br.unip.sicc.trabalho.dao.DaoException;
import br.unip.sicc.trabalho.dao.PessoaJuridicaDAO;
import br.unip.sicc.trabalho.modelo.PessoaJuridica;
import br.unip.sicc.trabalho.dao.PessoaJuridicaList;

public class GerenciadorPessoaJuridica {
    
    private static GerenciadorPessoaJuridica instance;
    private final PessoaJuridicaDAO dao = new PessoaJuridicaList();

    private GerenciadorPessoaJuridica() {
    }

    public static GerenciadorPessoaJuridica getInstance() {
        if(instance==null){
            instance = new GerenciadorPessoaJuridica();                    
        }
        return instance;
    }
    
    public void salvar(PessoaJuridica pessoaJuridica) throws DaoException{
        if(pessoaJuridica.eNova()){
            dao.incluir(pessoaJuridica);
        }else{
            dao.atualizar(pessoaJuridica);
        }
    }
    
    public void excluir(PessoaJuridica pessoaJuridica) throws DaoException{
        dao.excluir(pessoaJuridica);
    }
    
    public PessoaJuridica criaNovaPessoaJuridica(){
        return new PessoaJuridica();
    }
    
    public List<PessoaJuridica> getPessoasJuridicas() throws DaoException {
        return dao.getPessoasJuridicas();
    }
    
    public List<PessoaJuridica> getPessoasJuridicasFiltradas(PessoaJuridica filtro) throws DaoException {
        return dao.getPessoasJuridicas(filtro);
    }
    
}
