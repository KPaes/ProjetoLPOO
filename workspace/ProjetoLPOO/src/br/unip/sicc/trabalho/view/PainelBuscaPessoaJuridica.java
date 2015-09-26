package br.unip.sicc.trabalho.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import br.unip.sicc.trabalho.app.Aplicativo;
import br.unip.sicc.trabalho.dao.DaoException;
import br.unip.sicc.trabalho.modelo.GerenciadorPessoaJuridica;
import br.unip.sicc.trabalho.modelo.PessoaJuridica;
import br.unip.sicc.trabalho.view.PessoaJuridicaTableModel;

public class PainelBuscaPessoaJuridica extends JPanel {

	private JLabel lblBusca;
    private JTextField txtBusca;
    private JButton butBusca;
    private JTable tabelaPessoasJuridicas;
    private JScrollPane scroll;
    private Aplicativo app;

    public PainelBuscaPessoaJuridica(Aplicativo app) {
        this.app = app;
        this.setLayout(new BorderLayout());
        this.add(geraPainelBuscas(), BorderLayout.PAGE_START);
        this.add(geraPainelTabela(), BorderLayout.CENTER);
    }

    private JPanel geraPainelBuscas() {
        JPanel painel = new JPanel(
                new FlowLayout(FlowLayout.CENTER, 5, 10));
        lblBusca = new JLabel("Razão Social:");
        txtBusca = new JTextField(20);
        butBusca = new JButton("Buscar");
        butBusca.setMnemonic(KeyEvent.VK_B);
        butBusca.addActionListener(new BuscaListener());
        painel.add(lblBusca);
        painel.add(txtBusca);
        painel.add(butBusca);
        return painel;
    }

    private JScrollPane geraPainelTabela() {
        try {
        	PessoaJuridicaTableModel pessoaJuridicaTableModel;
        	pessoaJuridicaTableModel = new PessoaJuridicaTableModel(GerenciadorPessoaJuridica.getInstance().getPessoasJuridicas());
        	tabelaPessoasJuridicas = new JTable(pessoaJuridicaTableModel);
        	tabelaPessoasJuridicas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        	tabelaPessoasJuridicas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (e.getValueIsAdjusting()) {
                        return;
                    }
                    int linha = tabelaPessoasJuridicas.getSelectedRow();
                    PessoaJuridica pessoaJuridicaSelecionada = pessoaJuridicaTableModel.getPessoaJuridica(linha);
                    app.setPessoaJuridica(pessoaJuridicaSelecionada);
                }
            });
            scroll = new JScrollPane(tabelaPessoasJuridicas);
            return scroll;
        } catch (DaoException ex) {
            JOptionPane.showMessageDialog(this, "Não foi possível carregar as pessoas jurídicas ");
            scroll = new JScrollPane(new JTable());
            return scroll;
        }
    }

    public void atualizaPessoaJuridica(PessoaJuridica pessoaJuridica) {
    	PessoaJuridicaTableModel model = (PessoaJuridicaTableModel) tabelaPessoasJuridicas.getModel();
        model.update(pessoaJuridica);
        tabelaPessoasJuridicas.repaint();
        tabelaPessoasJuridicas.revalidate();
    }

    public void excluirPessoaJuridica(PessoaJuridica pessoaJuridica) {
    	PessoaJuridicaTableModel model = (PessoaJuridicaTableModel) tabelaPessoasJuridicas.getModel();
        model.remove(pessoaJuridica);
        tabelaPessoasJuridicas.repaint();
        tabelaPessoasJuridicas.revalidate();
    }

    private class BuscaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            BuscaPessoaJuridica busca = new BuscaPessoaJuridica();
            Thread t = new Thread(busca);
            t.start();
        }
    }

    private class BuscaPessoaJuridica implements Runnable {

        @Override
        public void run() {
            // Processo demorado
        	PessoaJuridica filtro = new PessoaJuridica();
            filtro.setRazaoSocial(txtBusca.getText());
            try {
                List<PessoaJuridica> pessoasJuridicasFiltradas = GerenciadorPessoaJuridica.getInstance().getPessoasJuridicasFiltradas(filtro);
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                    	tabelaPessoasJuridicas.setModel(new PessoaJuridicaTableModel(pessoasJuridicasFiltradas));
                    }
                });

            } catch (DaoException ex) {
                JOptionPane.showMessageDialog(null, "Não foi possível filtrar as tarefas");
            }

        }

    }

}
