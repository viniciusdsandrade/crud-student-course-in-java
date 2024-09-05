package Tabelas;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ModeloTabela extends AbstractTableModel {
    private ArrayList<Object> linhas = null;
    private String[] colunas = null;

    public ModeloTabela(ArrayList<Object> lin, String[] col) {
        setLinhas(lin);
        setColunas(col);
    }

    public ArrayList<Object> getLinhas() {
        return linhas;
    }

    public void setLinhas(ArrayList<Object> dados) {
        linhas = dados;
    }

    public String[] getColunas() {
        return colunas;
    }

    public void setColunas(String[] nomes) {
        colunas = nomes;
    }

    public int getColumnCount() {
        return colunas.length;
    }

    public int getRowCount() {
        return linhas.size();
    }

    public String getColumnName(int numCol) {
        return colunas[numCol];
    }

    public Object getValueAt(int numLin, int numCol) {
        Object[] linha = (Object[]) getLinhas().get(numLin);
        return linha[numCol];
    }
}