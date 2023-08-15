package Janelas;

import Utilitarios.Data;
import bd.daos.Alunos;
import bd.dbos.Aluno;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class JanelaAlterarAluno extends JFrame implements ActionListener {
    protected JLabel lblRa = new JLabel("RA do aluno: ");
    protected JLabel lblPNome           = new JLabel("Nome: ");
    protected JLabel lblSobrenome       = new JLabel("Sobrenome: ");
    protected JLabel lblEndereco        = new JLabel("Endereço: ");
    protected JLabel lblDtNascimento    = new JLabel("Data de nascimento: ");

    protected  JFormattedTextField txtRa            = new JFormattedTextField();
    protected JTextField txtNome                    = new JTextField();
    protected JTextField txtSobrenome               = new JTextField();
    protected JTextField txtEndereco                = new JTextField();
    protected JFormattedTextField txtdtNascimento   = new JFormattedTextField();


    protected JButton btnAlterar    = new JButton("Alterar aluno");
    protected JButton btnVoltar     = new JButton("Voltar");

    protected JFrame frame = new JFrame();

    public JanelaAlterarAluno(){
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(460, 230);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setTitle("Alterar Aluno");

        lblRa.setBounds(140, 5, 80, 30);
        frame.add(lblRa);
        txtRa.setBounds(215, 5, 60, 30);
        FormatoRA();
        frame.add(txtRa);

        lblPNome.setBounds(20, 50, 80, 30);
        frame.add(lblPNome);
        txtNome.setBounds(140, 50, 100, 30);
        frame.add(txtNome);
        lblSobrenome.setBounds(260, 50, 110, 30);
        frame.add(lblSobrenome);
        txtSobrenome.setBounds(335, 50, 100, 30);
        frame.add(txtSobrenome);

        lblDtNascimento.setBounds(20, 95, 150, 30);
        frame.add(lblDtNascimento);
        txtdtNascimento.setBounds(140, 95, 100, 30);
        FormatoDataNasc();
        frame.add(txtdtNascimento);
        lblEndereco.setBounds(260, 95, 80, 30);
        frame.add(lblEndereco);
        txtEndereco.setBounds(335, 95, 80, 30);
        frame.add(txtEndereco);

        btnAlterar.setBounds(120, 150, 115, 30);
        btnAlterar.addActionListener(this);
        frame.add(btnAlterar);
        btnVoltar.setBounds(250, 150, 80, 30);
        btnVoltar.addActionListener(this);
        frame.add(btnVoltar);
    }

    public void FormatoRA(){
        try{
            MaskFormatter ra = new MaskFormatter("#####");
            ra.install(txtRa);
        }catch (ParseException erro){
            JOptionPane.showMessageDialog(null, "Erro ao formatar campo de RA !!");
        }
    }

    public void FormatoDataNasc(){
        try {
            MaskFormatter d = new MaskFormatter("##/##/####");
            d.install(txtdtNascimento);
        }catch (ParseException erro){
            JOptionPane.showMessageDialog(null, "Erro ao formatar campo de data !!");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnAlterar){
            try {
                if(txtRa.getText().equals("     "))
                    throw new Exception("Ra não fornecido !!");
                Aluno a = new Aluno(Alunos.getAluno(Integer.parseInt(txtRa.getText())));
                if (txtNome.getText().equals("")){
                }
                else{
                    a.setPrimeiroNome(txtNome.getText());
                }
                if (txtSobrenome.getText().equals("")){
                }
                else{
                    a.setUltimoNome(txtSobrenome.getText());
                }
                if (txtEndereco.getText().equals("")){
                }
                else{
                    a.setEndereco(txtEndereco.getText());
                }
                if (txtdtNascimento.getText().equals("  /  /    ")){
                }
                else {
                    a.setDatNascimento(txtdtNascimento.getText());
                }
                Alunos.alterar(a);
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
