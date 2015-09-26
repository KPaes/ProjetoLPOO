package br.unip.sicc.trabalho.dao;

import java.util.List;

import br.unip.sicc.trabalho.modelo.PessoaJuridica;

public interface PessoaJuridicaDAO {
    
    void incluir(PessoaJuridica pessoaJuridica) throws DaoException;
    
    void atualizar(PessoaJuridica pessoaJuridica) throws DaoException;
    
    void excluir(PessoaJuridica pessoaJuridica) throws DaoException;
    
    List<PessoaJuridica> getPessoasJuridicas() throws DaoException;    
    
    List<PessoaJuridica> getPessoasJuridicas(PessoaJuridica filtro) throws DaoException;
      
    
}
