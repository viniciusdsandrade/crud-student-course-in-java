package bd.daos;

import bd.core.*;
import bd.dbos.*;

import java.sql.SQLException;

import static bd.BDSQLServer.COMANDO;

public class Cursos {
    public static boolean cadastrado(int idC) throws Exception {
        boolean ret;

        try {
            String sql;

            sql = "SELECT *" +
                    "FROM projeto2.Curso " +
                    "WHERE Curso.idCurso = ?";

            COMANDO.prepareStatement(sql);

            COMANDO.setInt(1, idC);
            MeuResultSet res = (MeuResultSet) COMANDO.executeQuery();
            ret = res.first();
        } catch (SQLException erro) {
            throw new Exception("Erro ao buscar curso !!");
        }
        return ret;
    }

    public static void incluir(Curso c) throws Exception {
        if (c == null) throw new Exception("Curso n�o fornecido !!");

        try {
            String sql;

            sql = "INSERT INTO projeto2.Curso " +
                    "(idCurso, nome)" +
                    "VALUES" +
                    "(?, ?)";

            COMANDO.prepareStatement(sql);

            COMANDO.setInt(1, c.getIdCurso());
            COMANDO.setString(2, c.getNome());

            COMANDO.executeUpdate();
            COMANDO.commit();
        } catch (SQLException erro) {
            COMANDO.rollback();
            throw new Exception("Erro ao inserir curso !!");
        }
    }

    public static void excluir(int c) throws Exception {
        if (!cadastrado(c)) throw new Exception("Curso n�o cadastrado !!");

        try {
            String sql;

            sql = "DELETE FROM projeto2.Curso " +
                    "WHERE Curso.idCurso = ?";

            COMANDO.prepareStatement(sql);

            COMANDO.setInt(1, c);

            COMANDO.executeUpdate();
            COMANDO.commit();
        } catch (SQLException erro) {
            COMANDO.rollback();
            throw new Exception("Erro ao exlcuir curso !!");
        }
    }

    public static Curso getCurso(int idC) throws Exception {
        Curso curso;

        try {
            String sql;

            sql = "SELECT * " +
                    "FROM projeto2.Curso " +
                    "WHERE Curso.idCurso = ?";

            COMANDO.prepareStatement(sql);

            COMANDO.setInt(1, idC);

            MeuResultSet res = (MeuResultSet) COMANDO.executeQuery();
            if (!res.first())
                throw new Exception("Curso n�o cadastrado");
            curso = new Curso(res.getInt("idCurso"), res.getString("nome"));
        } catch (SQLException erro) {
            throw new Exception("Erro ao procurar Curso !!");
        }
        return curso;
    }

    public static MeuResultSet getCursos() throws Exception {
        MeuResultSet ret;

        try {
            String sql;

            sql = "SELECT * " +
                    "FROM projeto2.Curso";

            COMANDO.prepareStatement(sql);
            ret = (MeuResultSet) COMANDO.executeQuery();
        } catch (SQLException erro) {
            throw new Exception("Erro ao buscar cursos !!");
        }
        return ret;
    }

    public static MeuResultSet getCursoRS(int idC) throws Exception {
        MeuResultSet ret;

        try {
            String sql;

            sql = "SELECT * " +
                    "FROM projeto2.Curso " +
                    "WHERE Curso.idCurso = ?";

            COMANDO.prepareStatement(sql);
            COMANDO.setInt(1, idC);
            ret = (MeuResultSet) COMANDO.executeQuery();
        } catch (SQLException erro) {
            throw new Exception("Erro ao buscar curso !!");
        }
        return ret;
    }

    public static void alterar(Curso curso) throws Exception {
        if (curso == null)
            throw new Exception("Curso n�o fornecido !!");
        if (!(cadastrado(curso.getIdCurso())))
            throw new Exception("Curso n�o cadastrado !!");

        try {
            String sql;

            sql = "UPDATE projeto2.Curso " +
                    "SET nome=? " +
                    "WHERE idCurso =?";

            COMANDO.prepareStatement(sql);

            COMANDO.setString(1, curso.getNome());
            COMANDO.setInt(2, curso.getIdCurso());

            COMANDO.executeUpdate();
            COMANDO.commit();
        } catch (SQLException erro) {
            COMANDO.rollback();
            throw new Exception("Erro ao alterar dados do curso !!");
        }
    }
}