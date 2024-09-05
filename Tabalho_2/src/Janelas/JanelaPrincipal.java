package Janelas;

import Tabelas.ModeloTabela;
import bd.core.MeuResultSet;
import bd.daos.Alunos;
import bd.daos.Cursos;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class JanelaPrincipal extends JFrame implements ActionListener {
    protected JButton btnIncluirA = new JButton("Incluir Aluno"),
            btnAlterarA = new JButton("Alterar Aluno"),
            btnExlcuirA = new JButton("Excluir Aluno"),
            btnIncluirC = new JButton("Incluir Curso"),
            btnAlterarC = new JButton("Alterar Curso"),
            btnExcluirC = new JButton("Excluir Curso"),
            btnBuscarA = new JButton("Buscar Aluno"),
            btnBuscarC = new JButton("Buscar Curso"),
            btnBuscarAs = new JButton("Buscar Alunos"),
            btnBuscarCs = new JButton("Buscar Cursos");
    JFrame frame = new JFrame();
    JScrollPane jsp;

    protected JLabel A = new JLabel("Aluno"),
            C = new JLabel("Curso"),
            Ra = new JLabel("Ra do aluno:"),
            idC = new JLabel("Id do curso:");

    protected JFormattedTextField txtRa = new JFormattedTextField(),
            txtIdC = new JFormattedTextField();

    protected JTable tab = new JTable();

    public JanelaPrincipal() {
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setTitle("Manipulador de banco de dados");
        frame.setSize(570, 510);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);

        A.setBounds(30, 10, 105, 30);
        frame.add(A);

        btnIncluirA.setBounds(20, 50, 105, 30);
        btnIncluirA.setFocusable(false);
        btnIncluirA.addActionListener(this);
        frame.add(btnIncluirA);

        btnAlterarA.setBounds(145, 50, 110, 30);
        btnAlterarA.setFocusable(false);
        btnAlterarA.addActionListener(this);
        frame.add(btnAlterarA);

        btnExlcuirA.setBounds(275, 50, 110, 30);
        btnExlcuirA.setFocusable(false);
        btnExlcuirA.addActionListener(this);
        frame.add(btnExlcuirA);

        btnBuscarA.setBounds(405, 50, 120, 30);
        btnBuscarA.setFocusable(false);
        btnBuscarA.addActionListener(this);
        frame.add(btnBuscarA);

        btnBuscarAs.setBounds(135, 420, 120, 30);
        btnBuscarAs.setFocusable(false);
        btnBuscarAs.addActionListener(this);
        frame.add(btnBuscarAs);

        C.setBounds(30, 95, 105, 30);
        frame.add(C);

        Ra.setBounds(50, 180, 105, 30);
        frame.add(Ra);
        txtRa.setBounds(40, 205, 150, 30);
        FormatoRA();
        frame.add(txtRa);

        idC.setBounds(250, 180, 105, 30);
        frame.add(idC);
        txtIdC.setBounds(245, 205, 150, 30);
        FormatoIdCurso();
        frame.add(txtIdC);

        btnIncluirC.setBounds(20, 135, 110, 30);
        btnIncluirC.setFocusable(false);
        btnIncluirC.addActionListener(this);
        frame.add(btnIncluirC);

        btnAlterarC.setBounds(145, 135, 110, 30);
        btnAlterarC.setFocusable(false);
        btnAlterarC.addActionListener(this);
        frame.add(btnAlterarC);

        btnExcluirC.setBounds(275, 135, 110, 30);
        btnExcluirC.setFocusable(false);
        btnExcluirC.addActionListener(this);
        frame.add(btnExcluirC);

        btnBuscarC.setBounds(405, 135, 120, 30);
        btnBuscarC.setFocusable(false);
        btnBuscarC.addActionListener(this);
        frame.add(btnBuscarC);

        btnBuscarCs.setBounds(275, 420, 120, 30);
        btnBuscarCs.setFocusable(false);
        btnBuscarCs.addActionListener(this);
        frame.add(btnBuscarCs);

        jsp = new JScrollPane(tab);
        jsp.setBounds(30, 250, 480, 150);
        frame.add(jsp);

    }

    public void preencherTabA(MeuResultSet SQL) {
        ArrayList<Object> dados = new ArrayList<>();

        String[] colunas = new String[]{"RA", "Curso", "Nome", "Sobrenome", "RG", "Data de nascimento", "Endereço"};
        try {
            SQL.first();
            do {
                dados.add(new Object[]{SQL.getInt("RA"), SQL.getInt("Curso"), SQL.getString("PrimeiroNome"),
                        SQL.getString("UltimoNome"), SQL.getString("RG"), SQL.getString("DataNascimento"),
                        SQL.getString("Endereco")});
            } while (SQL.next());
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao preencher a tabela !!");
        }

        ModeloTabela modelo = new ModeloTabela(dados, colunas);
        tab.setModel(modelo);
        tab.getColumnModel().getColumn(0).setPreferredWidth(50);
        tab.getColumnModel().getColumn(0).setResizable(false);
        tab.getColumnModel().getColumn(1).setPreferredWidth(45);
        tab.getColumnModel().getColumn(1).setResizable(false);
        tab.getColumnModel().getColumn(2).setPreferredWidth(70);
        tab.getColumnModel().getColumn(2).setResizable(false);
        tab.getColumnModel().getColumn(3).setPreferredWidth(80);
        tab.getColumnModel().getColumn(3).setResizable(false);
        tab.getColumnModel().getColumn(4).setPreferredWidth(80);
        tab.getColumnModel().getColumn(4).setResizable(false);
        tab.getColumnModel().getColumn(5).setPreferredWidth(130);
        tab.getColumnModel().getColumn(5).setResizable(false);
        tab.getColumnModel().getColumn(6).setPreferredWidth(260);
        tab.getTableHeader().setReorderingAllowed(false);
        tab.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tab.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    public void preencherTabC(MeuResultSet SQL) {
        ArrayList<Object> dados = new ArrayList<>();

        String[] colunas = new String[]{"ID", "Nome do curso"};

        try {
            SQL.first();
            do {
                dados.add(new Object[]{SQL.getInt("idCurso"), SQL.getString("nome")});
            } while (SQL.next());
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao preencher a tabela !!");
        }
        ModeloTabela modelo = new ModeloTabela(dados, colunas);
        tab.setModel(modelo);
        tab.getColumnModel().getColumn(0).setPreferredWidth(240);
        tab.getColumnModel().getColumn(0).setResizable(false);
        tab.getColumnModel().getColumn(1).setPreferredWidth(240);
        tab.getColumnModel().getColumn(1).setResizable(false);
        tab.getTableHeader().setReorderingAllowed(false);
        tab.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    public void FormatoIdCurso() {
        try {
            MaskFormatter d = new MaskFormatter("###");
            d.install(txtIdC);
        } catch (ParseException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao formatar campo de id do curso !!");
        }
    }

    public void FormatoRA() {
        try {
            MaskFormatter ra = new MaskFormatter("#####");
            ra.install(txtRa);
        } catch (ParseException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao formatar campo de RA !!");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnIncluirA) {
            JanelaIncluirAluno jia = new JanelaIncluirAluno();
            frame.dispose();
        } else if (e.getSource() == btnIncluirC) {
            JanelaIncluirCurso jic = new JanelaIncluirCurso();
            frame.dispose();
        } else if (e.getSource() == btnAlterarA) {
            JanelaAlterarAluno jaa = new JanelaAlterarAluno();
            frame.dispose();
        } else if (e.getSource() == btnAlterarC) {
            JanelaAlterarCurso jac = new JanelaAlterarCurso();
            frame.dispose();
        } else if (e.getSource() == btnExlcuirA) {
            JanelaExlcuirAluno jea = new JanelaExlcuirAluno();
            frame.dispose();
        } else if (e.getSource() == btnExcluirC) {
            JanelaExcluirCurso jec = new JanelaExcluirCurso();
            frame.dispose();
        } else if (e.getSource() == btnBuscarA) {
            try {
                if (txtRa.getText().equals("     "))
                    throw new Exception("Ra não fornecido !!");

                preencherTabA(Alunos.getAlunoRS(Integer.parseInt(txtRa.getText())));
            } catch (Exception erro) {
                JOptionPane.showMessageDialog(null, erro.getMessage());
            }
        } else if (e.getSource() == btnBuscarC) {
            try {
                if (txtIdC.getText().equals("   "))
                    throw new Exception("Id do curso não fornecido !!");
                preencherTabC(Cursos.getCursoRS(Integer.parseInt(txtIdC.getText())));
            } catch (Exception erro) {
                JOptionPane.showMessageDialog(null, erro.getMessage());
            }
        } else if (e.getSource() == btnBuscarAs) {
            try {
                preencherTabA(Alunos.getAlunos());
            } catch (Exception erro) {
                JOptionPane.showMessageDialog(null, erro.getMessage());
            }
        } else if (e.getSource() == btnBuscarCs) {
            try {
                preencherTabC(Cursos.getCursos());
            } catch (Exception erro) {
                JOptionPane.showMessageDialog(null, erro.getMessage());
            }
        }
    }
}