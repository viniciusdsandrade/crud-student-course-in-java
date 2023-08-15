package Janelas;

import bd.daos.Cursos;
import bd.dbos.Curso;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class JanelaIncluirCurso extends JFrame implements ActionListener {
    protected JButton btnCadastrar  = new JButton("Cadastrar curso"),
                      btnVoltar     = new JButton("Voltar");

    protected JLabel lblIdC     = new JLabel("Id do curso: "),
                     lblNome    = new JLabel("Nome do curso: ");

    protected JFormattedTextField txtIdC = new JFormattedTextField();
    protected JTextField txtNome = new JTextField();

    JFrame frame = new JFrame();

    public void FormatoIdCurso(){
        try{
            MaskFormatter d = new MaskFormatter("###");
            d.install(txtIdC);
        }catch (ParseException erro){
            JOptionPane.showMessageDialog(null, "Erro ao formatar campo de id do curso !!");
        }
    }

    public JanelaIncluirCurso(){
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setTitle("Incluir Curso");
        frame.setSize(470, 150);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);

        lblIdC.setBounds(20, 10, 70, 30);
        frame.add(lblIdC);
        txtIdC.setBounds(95, 10, 100, 30);
        FormatoIdCurso();
        frame.add(txtIdC);

        lblNome.setBounds(230, 10, 100, 30);
        frame.add(lblNome);
        txtNome.setBounds(330, 10, 100, 30);
        frame.add(txtNome);

        btnCadastrar.setBounds(110, 70, 130, 30);
        btnCadastrar.setFocusable(false);
        btnCadastrar.addActionListener(this);
        frame.add(btnCadastrar);
        btnVoltar.setBounds(260, 70, 70, 30);
        btnVoltar.setFocusable(false);
        btnVoltar.addActionListener(this);
        frame.add(btnVoltar);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnCadastrar){
            try {
                Cursos.incluir(new Curso(Integer.parseInt(txtIdC.getText()), txtNome.getText()));
            }catch (Exception erro){
                JOptionPane.showMessageDialog(null, "Não foi possível incluir o curso !!");
            }
        } else
            if (e.getSource() == btnVoltar) {
                JanelaPrincipal jp = new JanelaPrincipal();
                frame.dispose();
        }
    }

}
