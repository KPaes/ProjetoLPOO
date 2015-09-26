package br.unip.sicc.trabalho.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import br.unip.sicc.trabalho.app.Aplicativo;
import br.unip.sicc.trabalho.modelo.GerenciadorPessoaJuridica;
import br.unip.sicc.trabalho.modelo.PessoaJuridica;

public class TelaControleDePessoaJuridica extends JFrame implements WindowListener{
    
	private static final long serialVersionUID = 1L;

	private static final String MSG_SOBRE = 
            "Software desenvolvido para aplicar Orientação a \n"
                                + "Objetos com Java";
    
    private PainelCadastroPessoaJuridica painelCadastroPessoaJuridica;
    private PainelBuscaPessoaJuridica painelBuscaPessoaJuridica;
    private JMenuBar barraDeMenu;
    private JMenu menuOpcoes, menuAjuda;
    private Aplicativo app;

    public TelaControleDePessoaJuridica(Aplicativo app) {
        this.app = app;
        this.setTitle("Controle de Empresas");
        this.setSize(1000, 600);
        this.setLocationRelativeTo(null);
        this.addWindowListener(this);
        //this.setResizable(false);
        painelCadastroPessoaJuridica = app.getPainelCadastroPessoaJuridica();
        painelBuscaPessoaJuridica = app.getPainelBuscaPessoasJuridicas();
        this.setJMenuBar(geraBarraDeMenu());
        this.add(painelBuscaPessoaJuridica, BorderLayout.CENTER);
        this.add(painelCadastroPessoaJuridica, BorderLayout.EAST);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
    private JMenuBar geraBarraDeMenu(){
        barraDeMenu = new JMenuBar();
        menuOpcoes = new JMenu("Opções");
        menuOpcoes.setMnemonic(KeyEvent.VK_P);
        menuAjuda = new JMenu("Ajuda");
        menuAjuda.setMnemonic(KeyEvent.VK_H);
        barraDeMenu.add(menuOpcoes);
        barraDeMenu.add(menuAjuda);
        JMenuItem itemNovo = new JMenuItem("Novo");
        itemNovo.setMnemonic(KeyEvent.VK_N);
        itemNovo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	PessoaJuridica t = GerenciadorPessoaJuridica.getInstance().criaNovaPessoaJuridica();
                app.setPessoaJuridica(t);
            }
        });
        JMenuItem itemSair = new JMenuItem("Sair");
        itemSair.setMnemonic(KeyEvent.VK_S);
        itemSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menuOpcoes.add(itemNovo);
        menuOpcoes.add(itemSair);
        JMenuItem itemSobre = new JMenuItem("Sobre");
        itemSobre.setMnemonic(KeyEvent.VK_B);
        itemSobre.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {                
                JOptionPane.showMessageDialog(null, 
                                MSG_SOBRE,
                                "Sobre",
                                JOptionPane.INFORMATION_MESSAGE
                        );
            }
        });
        menuAjuda.add(itemSobre);
        return barraDeMenu;
    }

    
    @Override
    public void windowOpened(WindowEvent e) {
        System.out.println("windowOpened");
    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("windowClosing");
    }

    @Override
    public void windowClosed(WindowEvent e) {
        System.out.println("windowClosed");
    }

    @Override
    public void windowIconified(WindowEvent e) {
        System.out.println("windowIconified");
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        System.out.println("windowDeiconified");
    }

    @Override
    public void windowActivated(WindowEvent e) {
        System.out.println("windowActivated");
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        System.out.println("windowDeactivated");
    }


}
