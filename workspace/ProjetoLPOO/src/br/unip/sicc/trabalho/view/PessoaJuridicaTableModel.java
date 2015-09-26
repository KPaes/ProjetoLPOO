package br.unip.sicc.trabalho.view;

import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import br.unip.sicc.trabalho.modelo.PessoaJuridica;

public class PessoaJuridicaTableModel implements TableModel {

    private List<PessoaJuridica> pessoasJuridicas;

    public PessoaJuridicaTableModel(List<PessoaJuridica> pessoasJuridicas) {
        this.pessoasJuridicas = pessoasJuridicas;
    }

    @Override
    public int getRowCount() {
        return pessoasJuridicas.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Id";
            case 1:
                return "CNPJ";
            case 2:
                return "Razão Social";
        }
        return "";
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
        }
        return Void.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
    	PessoaJuridica pessoaJuridica = pessoasJuridicas.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return pessoaJuridica.getId();
            case 1:
                return pessoaJuridica.getCnpj();
            case 2:
                return pessoaJuridica.getRazaoSocial();
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    }

    PessoaJuridica getPessoaJuridica(int row) {
        return pessoasJuridicas.get(row);
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
    }

    public void update(PessoaJuridica pessoaJuridica){
        if (!pessoasJuridicas.contains(pessoaJuridica)){
        	pessoasJuridicas.add(pessoaJuridica);
        }
    }
    
    public void remove(PessoaJuridica pessoaJuridica) {
        if (pessoasJuridicas.contains(pessoaJuridica))  {
        	pessoasJuridicas.remove(pessoaJuridica);
        }
    }

}
