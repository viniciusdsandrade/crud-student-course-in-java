package bd.daos;

import bd.core.MeuResultSet;
import bd.dbos.Aluno;

import java.sql.SQLException;

import static bd.BDSQLServer.COMANDO;

public class Alunos {

    public static void incluir(Aluno aluno) throws Exception {
        if (aluno == null) throw new Exception("Aluno nao fornecido !!");
        if (!(Cursos.cadastrado(aluno.getCurso()))) throw new Exception("Curso n�o cadastrado");
        try {
            String sql;

            sql = "INSERT INTO projeto2.Aluno" +
                    "(RA, Curso, PrimeiroNome, UltimoNome, RG, DataNascimento, Endereco)" +
                    "VALUES" +
                    "(?, ?, ?, ?, ?, CONVERT(date, ?, 103), ?)";

            COMANDO.prepareStatement(sql);

            COMANDO.setInt(1, aluno.getRa());
            COMANDO.setInt(2, aluno.getCurso());
            COMANDO.setString(3, aluno.getPrimeiroNome());
            COMANDO.setString(4, aluno.getUltimoNome());
            COMANDO.setString(5, aluno.getRg());
            COMANDO.setString(6, aluno.getDatNascimento());
            COMANDO.setString(7, aluno.getEndereco());

            COMANDO.executeUpdate();
            COMANDO.commit();
        } catch (SQLException erro) {
            COMANDO.rollback();
            throw new Exception("Erro ao atualizar dados !!");
        }
    }

    public static boolean cadastrado(int ra) throws Exception {
        boolean ret;
        try {
            String sql;

            sql = "SELECT *" +
                    "FROM projeto2.Aluno " +
                    "WHERE Aluno.RA = ?";

            COMANDO.prepareStatement(sql);

            COMANDO.setInt(1, ra);

            MeuResultSet res = (MeuResultSet) COMANDO.executeQuery();
            ret = res.first();
        } catch (SQLException erro) {
            throw new Exception("Erro ao buscar aluno !!");
        }
        return ret;
    }

    public static Aluno getAluno(int ra) throws Exception {
        Aluno aluno;

        try {
            String sql;

            sql = "SELECT Aluno.RA, Aluno.Curso, Aluno.PrimeiroNome, Aluno.UltimoNome, Aluno.RG, FORMAT(Aluno.DataNascimento, 'd', 'pt-br')'DataNascimento', Aluno.Endereco " +
                    "FROM projeto2.Aluno " +
                    "WHERE Aluno.RA = ?";

            COMANDO.prepareStatement(sql);

            COMANDO.setInt(1, ra);

            MeuResultSet res = (MeuResultSet) COMANDO.executeQuery();

            if (!res.first())
                throw new Exception("Aluno n�o cadastrado");

            aluno = new Aluno(res.getInt("RA"), res.getInt("Curso"), res.getString("PrimeiroNome"),
                    res.getString("UltimoNome"), res.getString("RG"), res.getString("DataNascimento"), res.getString("Endereco"));
        } catch (SQLException erro) {
            throw new Exception("Erro ao procurar aluno !!");
        }
        return aluno;
    }

    public static void alterar(Aluno aluno) throws Exception {
        if (aluno == null) throw new Exception("Aluno nao fornecido !!");
        if (!(cadastrado(aluno.getRa()))) throw new Exception("Aluno nao cadastrado !!");

        try {
            String sql;

            sql = "UPDATE projeto2.Aluno " +
                    "SET PrimeiroNome = ? , " +
                    "UltimoNome = ? , " +
                    "DataNascimento = ? , " +
                    "Endereco = ? " +
                    "WHERE Aluno.RA = ?";

            COMANDO.prepareStatement(sql);

            COMANDO.setString(1, aluno.getPrimeiroNome());
            COMANDO.setString(2, aluno.getUltimoNome());
            COMANDO.setString(3, aluno.getDatNascimento());
            COMANDO.setString(4, aluno.getEndereco());
            COMANDO.setInt(5, aluno.getRa());

            COMANDO.executeUpdate();
            COMANDO.commit();
        } catch (SQLException erro) {
            COMANDO.rollback();
            throw new Exception("Erro ao atualizar dados do aluno !!");
        }
    }

    public static void excluir(int ra) throws Exception {
        if (!cadastrado(ra)) throw new Exception("Aluno nao cadastrado !!");

        try {
            String sql;

            sql = "DELETE FROM projeto2.Aluno " +
                    "WHERE Aluno.RA = ?";

            COMANDO.prepareStatement(sql);
            COMANDO.setInt(1, ra);
            COMANDO.executeUpdate();
            COMANDO.commit();
        } catch (SQLException erro) {
            COMANDO.rollback();
            throw new Exception("Erro ao excluir aluno !!");
        }
    }

    public static MeuResultSet getAlunos() throws Exception {
        MeuResultSet ret;

        try {
            String sql;

            sql = "SELECT * " +
                    "FROM projeto2.Aluno";

            COMANDO.prepareStatement(sql);

            ret = (MeuResultSet) COMANDO.executeQuery();
        } catch (SQLException erro) {
            throw new Exception("Erro ao buscar alunos !!");
        }
        return ret;
    }

    public static MeuResultSet getAlunoRS(Integer ra) throws Exception {
        MeuResultSet ret;
        try {
            String sql;

            sql = "SELECT * " +
                    "FROM projeto2.Aluno " +
                    "WHERE Aluno.RA = ?";

            COMANDO.prepareStatement(sql);

            COMANDO.setInt(1, ra);

            ret = (MeuResultSet) COMANDO.executeQuery();
        } catch (SQLException erro) {
            throw new Exception("Erro ao buscar aluno !!");
        }
        return ret;
    }
}