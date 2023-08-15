package Janelas;

import bd.daos.Alunos;
import bd.dbos.Aluno;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;

public class JanelaIncluirAluno extends JFrame implements ActionListener {

    protected JButton btnCadastrar = new JButton("Cadastrar Aluno");
    protected JButton btnVoltar = new JButton("Voltar");

    protected  JFormattedTextField txtRA            = new JFormattedTextField();
    protected  JFormattedTextField txtCurso         = new JFormattedTextField();
    protected  JTextField txtPNome                  = new JTextField();
    protected  JTextField txtUNome                  = new JTextField();
    protected  JFormattedTextField txtRG            = new JFormattedTextField();
    protected  JFormattedTextField txtDtNascimento  = new JFormattedTextField();
    protected  JTextField txtEndereco               = new JTextField();

    protected JLabel lblRA              = new JLabel("RA: ");
    protected JLabel lblCurso           = new JLabel("Curso: ");
    protected JLabel lblPNome           = new JLabel("Nome: ");
    protected JLabel lblUNome           = new JLabel("Sobrenome: ");
    protected JLabel lblRG              = new JLabel("RG: ");
    protected JLabel lblDtNascimento    = new JLabel("Data de nascimento: ");
    protected JLabel lblEndereco        = new JLabel("Endereço: ");

    JFrame frame = new JFrame();

    public void FormatoDataNasc(){
        try {
            MaskFormatter d = new MaskFormatter("##/##/####");
            d.install(txtDtNascimento);
        }catch (ParseException erro){
            JOptionPane.showMessageDialog(null, "Erro ao formatar campo de data !!");
        }
    }

    public void FormatoRA(){
        try{
            MaskFormatter ra = new MaskFormatter("#####");
            ra.install(txtRA);
        }catch (ParseException erro){
            JOptionPane.showMessageDialog(null, "Erro ao formatar campo de RA !!");
        }
    }

    public void FormatoRG(){
        try{
            MaskFormatter rg = new MaskFormatter("##.###.###-##");
            rg.install(txtRG);
        }catch (ParseException erro){
            JOptionPane.showMessageDialog(null, "Erro ao formatar campo de RG !!");
        }
    }
    public void FormatoIdCurso(){
        try{
            MaskFormatter d = new MaskFormatter("###");
            d.install(txtCurso);
        }catch (ParseException erro){
            JOptionPane.showMessageDialog(null, "Erro ao formatar campo de id do curso !!");
        }
    }


    public JanelaIncluirAluno() {
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(470, 260);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setTitle("Incluir Aluno");
        frame.setResizable(false);

        lblRA.setBounds(20, 10, 50, 30);
        frame.add(lblRA);
        txtRA.setBounds(85, 10, 100, 30);
        FormatoRA();
        frame.add(txtRA);

        lblCurso.setBounds(200, 10, 50, 30);
        frame.add(lblCurso);
        txtCurso.setBounds(330, 10, 100, 30);
        FormatoIdCurso();
        frame.add(txtCurso);

        lblPNome.setBounds(20, 50, 90, 30);
        frame.add(lblPNome);
        txtPNome.setBounds(85, 50, 100, 30);
        frame.add(txtPNome);


        lblUNome.setBounds(200, 50, 90, 30);
        frame.add(lblUNome);
        txtUNome.setBounds(330, 50, 100, 30);
        frame.add(txtUNome);

        lblRG.setBounds(20, 90, 50, 30);
        frame.add(lblRG);
        txtRG.setBounds(85, 90, 100, 30);
        FormatoRG();
        frame.add(txtRG);

        lblDtNascimento.setBounds(200, 90, 120, 30);
        frame.add(lblDtNascimento);
        txtDtNascimento.setBounds(330, 90, 100, 30);
        FormatoDataNasc();
        frame.add(txtDtNascimento);

        lblEndereco.setBounds(20, 130, 70, 30);
        frame.add(lblEndereco);
        txtEndereco.setBounds(85, 130, 100, 30);
        frame.add(txtEndereco);

        btnCadastrar.setBounds(95, 180, 130, 25);
        btnCadastrar.setFocusable(false);
        btnCadastrar.addActionListener(this);
        frame.add(btnCadastrar);

        btnVoltar.setBounds(235, 180, 130, 25);
        btnVoltar.setFocusable(false);
        btnVoltar.addActionListener(this);
        frame.add(btnVoltar);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == btnCadastrar){
            try {
                if(txtRA.getText().equals("     "))
                    throw new Exception("Campo de RA em branco !!");
                if(txtCurso.getText().equals("   "))
                    throw new Exception("Campo de Curso em branco !!");
                if(txtPNome.getText().equals(""))
                    throw new Exception("Campo de Nome em branco !!");
                if(txtUNome.getText().equals(""))
                    throw new Exception("Campo de Sobrenome em branco !!");
                if (txtRG.getText().equals("  .   .   -  "))
                    throw new Exception("Campo de RG em branco !!");
                if(txtDtNascimento.getText().equals("  /  /    "))
                    throw new Exception("Campo de Data de nascimento em branco !!");
                if (txtEndereco.getText().equals(""))
                    throw new Exception("Campo de Endereço em branco !!");
                Alunos.incluir(new Aluno(Integer.parseInt(txtRA.getText()), Integer.parseInt(txtCurso.getText()), txtPNome.getText(),
                        txtUNome.getText(), txtRG.getText(), txtDtNascimento.getText(), txtEndereco.getText()));
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