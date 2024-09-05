package bd;

import bd.core.*;

import javax.swing.*;

public class BDSQLServer {
    public static final MeuPreparedStatement COMANDO;

    static {
        MeuPreparedStatement comando = null;
        try{
            comando = new MeuPreparedStatement("com.microsoft.sqlserver.jdbc.SQLServerDriver",
                                               "jdbc:sqlserver://regulus.cotuca.unicamp.br:1433;databasename=BD22333",
                                               "BD22333", "BD22333");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Problemas de conex?o com o banco de dados !!");
            System.exit(0);
        }
        COMANDO = comando;
    }
}