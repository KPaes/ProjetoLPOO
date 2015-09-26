package br.unip.sicc.trabalho.app;

import javax.swing.SwingUtilities;

import br.unip.sicc.trabalho.modelo.PessoaJuridica;
import br.unip.sicc.trabalho.view.PainelBuscaPessoaJuridica;
import br.unip.sicc.trabalho.view.PainelCadastroPessoaJuridica;
import br.unip.sicc.trabalho.view.TelaControleDePessoaJuridica;

public class Aplicativo {
    private static Aplicativo instance;

    private TelaControleDePessoaJuridica telaControleDePessoaJuridica = null;
    private PainelBuscaPessoaJuridica painelBuscaPessoasJuridicas = null;
    private PainelCadastroPessoaJuridica painelCadastroPessoasJuridicas = null;

    private Aplicativo() {
    }

    public static Aplicativo getInstance() {
        if (instance == null) {
            instance = new Aplicativo();
        }
        return instance;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                getInstance().getTelaControleDePessoaJuridica();
            }

        });
    }

    public TelaControleDePessoaJuridica getTelaControleDePessoaJuridica () {
        if (telaControleDePessoaJuridica == null) {
        	telaControleDePessoaJuridica = new TelaControleDePessoaJuridica(this);
        }
        return telaControleDePessoaJuridica;
    }

    public PainelBuscaPessoaJuridica getPainelBuscaPessoasJuridicas() {
        if (painelBuscaPessoasJuridicas == null) {
        	painelBuscaPessoasJuridicas = new PainelBuscaPessoaJuridica(this);
        }

        return painelBuscaPessoasJuridicas;
    }

    public PainelCadastroPessoaJuridica getPainelCadastroPessoaJuridica() {
        if (painelCadastroPessoasJuridicas == null) {
        	painelCadastroPessoasJuridicas = new PainelCadastroPessoaJuridica(this);
        }

        return painelCadastroPessoasJuridicas;
    }
    public void atualizaPessoasJuridicas(PessoaJuridica pessoaJuridica) {
    	getPainelBuscaPessoasJuridicas().atualizaPessoaJuridica(pessoaJuridica);
    }
    public void excluirPessoaJuridica(PessoaJuridica pessoaJuridica) {
        getPainelBuscaPessoasJuridicas().excluirPessoaJuridica(pessoaJuridica);
    }
    public void setPessoaJuridica(PessoaJuridica pessoaJuridicaSelecionada) {
        getPainelCadastroPessoaJuridica().setPessoaJuridica(pessoaJuridicaSelecionada);
    }
}
