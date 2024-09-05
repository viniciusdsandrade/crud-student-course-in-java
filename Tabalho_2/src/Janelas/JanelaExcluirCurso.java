package Janelas;

import bd.daos.Cursos;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class JanelaExcluirCurso extends JFrame implements ActionListener {

    protected JLabel lblIdC = new JLabel("Id do curso: ");

    protected JFormattedTextField txtIdC = new JFormattedTextField();

    protected JButton btnExcluir = new JButton("Excluir curso");
    protected JButton btnVoltar = new JButton("Voltar");

    protected JFrame frame = new JFrame();

    public JanelaExcluirCurso() {
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("Excluir curso");
        frame.setSize(340, 145);
        frame.setLayout(null);
        frame.setVisible(true);

        lblIdC.setBounds(15, 40, 75, 30);
        frame.add(lblIdC);
        txtIdC.setBounds(90, 40, 75, 30);
        FormatoIdCurso();
        frame.add(txtIdC);

        btnExcluir.setBounds(195, 20, 110, 30);
        btnExcluir.setFocusable(false);
        btnExcluir.addActionListener(this);
        frame.add(btnExcluir);
        btnVoltar.setBounds(195, 60, 110, 30);
        btnVoltar.setFocusable(false);
        btnVoltar.addActionListener(this);
        frame.add(btnVoltar);
    }

    public void FormatoIdCurso() {
        try {
            MaskFormatter d = new MaskFormatter("###");
            d.install(txtIdC);
        } catch (ParseException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao formatar campo de id do curso !!");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnExcluir) {
            try {
                if (txtIdC.getText().equals("   "))
                    throw new Exception("Id do curso n�o fornecido !!");
                Cursos.excluir(Integer.parseInt(txtIdC.getText()));
            } catch (Exception erro) {
                JOptionPane.showMessageDialog(null, erro.getMessage());
            }
        } else if (e.getSource() == btnVoltar) {
            JanelaPrincipal jp = new JanelaPrincipal();
            frame.dispose();
        }
    }
}
