package br.unip.sicc.trabalho.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import br.unip.sicc.trabalho.app.Aplicativo;
import br.unip.sicc.trabalho.dao.DaoException;
import br.unip.sicc.trabalho.modelo.GerenciadorPessoaJuridica;
import br.unip.sicc.trabalho.modelo.PessoaJuridica;


public class PainelCadastroPessoaJuridica extends JPanel {
    /* declarando os componentes de tela como atributos
     * do painel para ter acesso na classe inteira
     */

	private static final long serialVersionUID = 1L;
	private JLabel lblId, lblCnpj,
            lblRazaoSocial;
    private JTextField txtId, txtCnpj, txtRazaoSocial;
    private MaskFormatter ftmCnpj; 
    private JButton butOk, butCancelar, butExcluir;
    private PessoaJuridica pessoaJuridica;
    private Aplicativo app;
    
    /* inicializando os componentes no construtor */
    public PainelCadastroPessoaJuridica(Aplicativo app) {
        this.app = app;
        this.setLayout(new BorderLayout());
        this.add(geraPainelCampos(), BorderLayout.NORTH);
        this.add(geraPainelBotoes(), BorderLayout.SOUTH);
    }

    private JPanel geraPainelBotoes() {
        LayoutManager layout
                = new FlowLayout(FlowLayout.RIGHT, 5, 10);
        JPanel painelBotoes
                = new JPanel(layout);
        butOk = new JButton("Ok");
        butOk.setMnemonic(KeyEvent.VK_O);
        butOk.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
            	PessoaJuridica pessoaJuridica = getPessoaJuridica();
                try{
                    GerenciadorPessoaJuridica.getInstance().salvar(pessoaJuridica);
                    setPessoaJuridica(pessoaJuridica);
                    app.atualizaPessoasJuridicas(pessoaJuridica);                          
                }catch(DaoException ex){
                    JOptionPane.showMessageDialog(null, "Não foi possível salvar");
                }
            }
        });
        
        butCancelar = new JButton("Cancelar");
        butCancelar.setMnemonic(KeyEvent.VK_C);
        butCancelar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setPessoaJuridica(pessoaJuridica);
            }
        });
        
        butExcluir = new JButton("Excluir");
        butExcluir.setMnemonic(KeyEvent.VK_X);
        butExcluir.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    GerenciadorPessoaJuridica.getInstance().excluir(pessoaJuridica);
                    app.excluirPessoaJuridica(pessoaJuridica);
                }catch(DaoException ex){
                    JOptionPane.showMessageDialog(null, "Não foi possível excluir");
                }
            }
        });
        painelBotoes.add(butOk);
        painelBotoes.add(butCancelar);
        painelBotoes.add(butExcluir);
        return painelBotoes;

    }

    private JPanel geraPainelCampos() {        
        JPanel painel
                = new JPanel(new BorderLayout(10, 10));
        JPanel painelLabels
                = new JPanel(new GridLayout(6, 1, 5, 5));
        JPanel painelCampos
                = new JPanel(new GridLayout(6, 1, 5, 5));
        lblId = new JLabel("Id: ");
        txtId = new JTextField();
        txtId.setEditable(false);
        lblCnpj = new JLabel("CNPJ: ");
        try {
			ftmCnpj = new MaskFormatter("##.###.###/####-##");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        txtCnpj = new JFormattedTextField(ftmCnpj);
        lblRazaoSocial = new JLabel("Razão Social: ");
        txtRazaoSocial = new JTextField();
        txtRazaoSocial.setColumns(30);
        painelLabels.add(lblId);
        painelCampos.add(txtId);
        painelLabels.add(lblCnpj);
        painelCampos.add(txtCnpj);
        painelLabels.add(lblRazaoSocial);
        painelCampos.add(txtRazaoSocial);        
        painel.add(new JLabel(" "),BorderLayout.NORTH);
        painel.add(new JLabel(" "),BorderLayout.EAST);
        painel.add(painelLabels,BorderLayout.WEST);
        painel.add(painelCampos,BorderLayout.CENTER);
        this.setPessoaJuridica(new PessoaJuridica());
        return painel;
    }

    public PessoaJuridica getPessoaJuridica() {
        String strId = txtId.getText();
        if("".equals(strId)){
            strId = "0";
        }
        pessoaJuridica.setId(Integer.valueOf(strId));
        pessoaJuridica.setCnpj(txtCnpj.getText());
        pessoaJuridica.setRazaoSocial(txtRazaoSocial.getText());
        return pessoaJuridica;
    }

    public void setPessoaJuridica(PessoaJuridica pessoaJuridica){
        this.pessoaJuridica = pessoaJuridica;
        String strId = pessoaJuridica.getId().toString();
        txtId.setText(strId);
        txtCnpj.setText(pessoaJuridica.getCnpj());
        txtRazaoSocial.setText(pessoaJuridica.getRazaoSocial());
    }

}
