package Janelas;

import bd.daos.Alunos;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class JanelaExlcuirAluno extends JFrame implements ActionListener {

    protected JLabel lblRa = new JLabel("RA do aluno: ");

    protected JFormattedTextField txtRa = new JFormattedTextField();

    protected JButton btnExcluir = new JButton("Excluir aluno");
    protected JButton btnVoltar = new JButton("Voltar");

    protected JFrame frame = new JFrame();

    public JanelaExlcuirAluno() {
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("Excluir aluno");
        frame.setSize(350, 145);
        frame.setLayout(null);
        frame.setVisible(true);

        lblRa.setBounds(15, 40, 75, 30);
        frame.add(lblRa);
        txtRa.setBounds(90, 40, 75, 30);
        FormatoRA();
        frame.add(txtRa);

        btnExcluir.setBounds(195, 20, 110, 30);
        btnExcluir.setFocusable(false);
        btnExcluir.addActionListener(this);
        frame.add(btnExcluir);
        btnVoltar.setBounds(195, 60, 110, 30);
        btnVoltar.setFocusable(false);
        btnVoltar.addActionListener(this);
        frame.add(btnVoltar);
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
        if (e.getSource() == btnExcluir) {
            try {
                if (txtRa.getText().equals("     "))
                    throw new Exception("Ra n�o fornecido !!");
                Alunos.excluir(Integer.parseInt(txtRa.getText()));
            } catch (Exception erro) {
                JOptionPane.showMessageDialog(null, erro.getMessage());
            }
        } else if (e.getSource() == btnVoltar) {
            JanelaPrincipal jp = new JanelaPrincipal();
            frame.dispose();
        }
    }
}
