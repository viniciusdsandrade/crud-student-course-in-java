package Janelas;

import bd.daos.Cursos;
import bd.dbos.Curso;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class JanelaAlterarCurso extends JFrame implements ActionListener {
    protected JLabel lblCurso   = new JLabel("Id do curso: ");
    protected JLabel lblNome    = new JLabel("Nome: ");

    protected JTextField txtNome            = new JTextField();
    protected JFormattedTextField txtCurso  = new JFormattedTextField();

    protected JButton btnAlterar    = new JButton("Alterar curso");
    protected JButton btnVoltar     = new JButton("Voltar");

    protected JFrame frame = new JFrame();

    public JanelaAlterarCurso(){
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(350, 150);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setTitle("Alterar Aluno");

        lblCurso.setBounds(20, 15, 100, 30);
        frame.add(lblCurso);
        txtCurso.setBounds(90, 15, 80, 30);
        FormatoIdCurso();
        frame.add(txtCurso);

        lblNome.setBounds(20, 60, 50, 30);
        frame.add(lblNome);
        txtNome.setBounds(90, 60, 80, 30);
        frame.add(txtNome);

        btnAlterar.setBounds(195, 15, 115, 30);
        btnAlterar.addActionListener(this);
        frame.add(btnAlterar);
        btnVoltar.setBounds(195, 60, 115, 30);
        btnVoltar.addActionListener(this);
        frame.add(btnVoltar);
    }

    public void FormatoIdCurso(){
        try{
            MaskFormatter d = new MaskFormatter("###");
            d.install(txtCurso);
        }catch (ParseException erro){
            JOptionPane.showMessageDialog(null, "Erro ao formatar campo de id do curso !!");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnAlterar) {
            try {
                if(txtCurso.getText().equals("   "))
                    throw new Exception("Id do curso não fornecido !!");
                Curso c = new Curso(Cursos.getCurso(Integer.parseInt(txtCurso.getText())));
                if(txtNome.getText().equals("")){}
                else {
                    c.setNome(txtNome.getText());
                }
                Cursos.alterar(c);
            }catch (Exception erro){
                JOptionPane.showMessageDialog(null, erro.getMessage());
            }
        } else
            if (e.getSource() == btnVoltar) {
                JanelaPrincipal jp = new JanelaPrincipal();
                frame.dispose();
        }
    }
}
