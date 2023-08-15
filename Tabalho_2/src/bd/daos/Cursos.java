package bd.daos;

import bd.BDSQLServer;
import bd.core.*;
import bd.dbos.*;

import java.sql.SQLException;

public class Cursos {
    public static boolean cadastrado(int idC) throws Exception{
       boolean ret = false;

       try{
           String sql;

           sql = "SELECT *" +
                 "FROM projeto2.Curso " +
                 "WHERE Curso.idCurso = ?";

           BDSQLServer.COMANDO.prepareStatement(sql);

           BDSQLServer.COMANDO.setInt(1, idC);
           MeuResultSet res = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();
           ret = res.first();
       }catch (SQLException erro){
           throw new Exception("Erro ao buscar curso !!");
       }
       return ret;
    }

    public static void incluir(Curso c) throws Exception{
        if(c == null)
            throw new Exception("Curso não fornecido !!");

        try{
            String sql;

            sql = "INSERT INTO projeto2.Curso " +
                  "(idCurso, nome)" +
                  "VALUES" +
                  "(?, ?)";

            BDSQLServer.COMANDO.prepareStatement(sql);

            BDSQLServer.COMANDO.setInt(1, c.getIdCurso());
            BDSQLServer.COMANDO.setString(2, c.getNome());

            BDSQLServer.COMANDO.executeUpdate();
            BDSQLServer.COMANDO.commit();
        }catch (SQLException erro){
            BDSQLServer.COMANDO.rollback();
            throw new Exception("Erro ao inserir curso !!");
        }
    }

    public static void excluir(int c) throws Exception{
        if(!cadastrado(c))
            throw new Exception("Curso não cadastrado !!");

        try{
            String sql;

            sql = "DELETE FROM projeto2.Curso " +
                  "WHERE Curso.idCurso = ?";

            BDSQLServer.COMANDO.prepareStatement(sql);

            BDSQLServer.COMANDO.setInt(1, c);

            BDSQLServer.COMANDO.executeUpdate();
            BDSQLServer.COMANDO.commit();
        }catch (SQLException erro){
            BDSQLServer.COMANDO.rollback();
            throw new Exception("Erro ao exlcuir curso !!");
        }
    }

    public static Curso getCurso(int idC) throws Exception{
        Curso curso = null;

        try{
            String sql;

            sql = "SELECT * " +
                  "FROM projeto2.Curso " +
                  "WHERE Curso.idCurso = ?";

            BDSQLServer.COMANDO.prepareStatement(sql);

            BDSQLServer.COMANDO.setInt(1, idC);

            MeuResultSet res = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();
            if(!res.first())
                throw new Exception("Curso não cadastrado");
            curso = new Curso(res.getInt("idCurso"), res.getString("nome"));
        }catch (SQLException erro){
            throw new Exception("Erro ao procurar Curso !!");
        }
        return curso;
    }

    public static MeuResultSet getCursos() throws Exception{
        MeuResultSet ret = null;

        try{
            String sql;

            sql = "SELECT * " +
                  "FROM projeto2.Curso";

            BDSQLServer.COMANDO.prepareStatement(sql);
            ret = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();
        }catch (SQLException erro){
            throw new Exception("Erro ao buscar cursos !!");
        }
        return ret;
    }

    public static MeuResultSet getCursoRS(int idC) throws Exception{
        MeuResultSet ret = null;

        try{
            String sql;

            sql = "SELECT * " +
                  "FROM projeto2.Curso " +
                  "WHERE Curso.idCurso = ?";

            BDSQLServer.COMANDO.prepareStatement(sql);
            BDSQLServer.COMANDO.setInt(1, idC);
            ret = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();
        }catch (SQLException erro){
            throw new Exception("Erro ao buscar curso !!");
        }
        return ret;
    }

    public static void alterar(Curso curso) throws Exception{
        if(curso == null)
            throw new Exception("Curso não fornecido !!");
        if(!(cadastrado(curso.getIdCurso())))
            throw new Exception("Curso não cadastrado !!");

        try{
            String sql;

            sql = "UPDATE projeto2.Curso " +
                  "SET nome=? " +
                  "WHERE idCurso =?";

            BDSQLServer.COMANDO.prepareStatement(sql);

            BDSQLServer.COMANDO.setString(1, curso.getNome());
            BDSQLServer.COMANDO.setInt(2, curso.getIdCurso());

            BDSQLServer.COMANDO.executeUpdate();
            BDSQLServer.COMANDO.commit();
        }catch (SQLException erro){
            BDSQLServer.COMANDO.rollback();
            throw new Exception("Erro ao alterar dados do curso !!");
        }
    }
}