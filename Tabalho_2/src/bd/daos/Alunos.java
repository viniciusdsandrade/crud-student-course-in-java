package bd.daos;

import bd.BDSQLServer;
import bd.core.MeuResultSet;
import bd.dbos.Aluno;

import java.sql.SQLException;
public class Alunos {

    public static void incluir(Aluno aluno) throws Exception{
        if(aluno == null)
            throw new Exception("Aluno não fornecido !!");
        if(!(Cursos.cadastrado(aluno.getCurso())))
            throw new Exception("Curso não cadastrado");
        try{
            String sql;

            sql = "INSERT INTO projeto2.Aluno" +
                    "(RA, Curso, PrimeiroNome, UltimoNome, RG, DataNascimento, Endereco)" +
                    "VALUES" +
                    "(?, ?, ?, ?, ?, CONVERT(date, ?, 103), ?)";

            BDSQLServer.COMANDO.prepareStatement(sql);

            BDSQLServer.COMANDO.setInt(1, aluno.getRa());
            BDSQLServer.COMANDO.setInt(2, aluno.getCurso());
            BDSQLServer.COMANDO.setString(3, aluno.getPrimeiroNome());
            BDSQLServer.COMANDO.setString(4, aluno.getUltimoNome());
            BDSQLServer.COMANDO.setString(5, aluno.getRg());
            BDSQLServer.COMANDO.setString(6, aluno.getDatNascimento());
            BDSQLServer.COMANDO.setString(7, aluno.getEndereco());

            BDSQLServer.COMANDO.executeUpdate();
            BDSQLServer.COMANDO.commit();
        }catch (SQLException erro){
            BDSQLServer.COMANDO.rollback();
            throw new Exception("Erro ao atualizar dados !!");
        }
    }

    public static boolean cadastrado(int ra) throws Exception{
        boolean ret = false;
        try{
            String sql;

            sql = "SELECT *"+
                  "FROM projeto2.Aluno " +
                  "WHERE Aluno.RA = ?";

            BDSQLServer.COMANDO.prepareStatement(sql);

            BDSQLServer.COMANDO.setInt(1, ra);

            MeuResultSet res = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();
            ret = res.first();
        }catch (SQLException erro){
            throw new Exception("Erro ao buscar aluno !!");
        }
        return ret;
    }

    public static Aluno getAluno(int ra) throws Exception{
        Aluno aluno = null;

        try {
            String sql;

            sql = "SELECT Aluno.RA, Aluno.Curso, Aluno.PrimeiroNome, Aluno.UltimoNome, Aluno.RG, FORMAT(Aluno.DataNascimento, 'd', 'pt-br')'DataNascimento', Aluno.Endereco " +
                  "FROM projeto2.Aluno " +
                  "WHERE Aluno.RA = ?";

            BDSQLServer.COMANDO.prepareStatement(sql);

            BDSQLServer.COMANDO.setInt(1, ra);

            MeuResultSet res = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();

            if(!res.first())
                throw new Exception("Aluno não cadastrado");

            aluno = new Aluno(res.getInt("RA"), res.getInt("Curso"), res.getString("PrimeiroNome"),
                              res.getString("UltimoNome"), res.getString("RG"), res.getString("DataNascimento"), res.getString("Endereco"));
        }catch (SQLException erro){
            throw new Exception("Erro ao procurar aluno !!");
        }
        return aluno;
    }

    public static void alterar(Aluno aluno) throws Exception{
        if(aluno == null)
            throw new Exception("Aluno não fornecido !!");
        if(!(cadastrado(aluno.getRa())))
            throw new Exception("Aluno não cadastrado !!");

        try{
            String sql;

            sql = "UPDATE projeto2.Aluno " +
                    "SET PrimeiroNome = ? , " +
                    "UltimoNome = ? , " +
                    "DataNascimento = ? , " +
                    "Endereco = ? " +
                    "WHERE Aluno.RA = ?";

            BDSQLServer.COMANDO.prepareStatement(sql);

            BDSQLServer.COMANDO.setString(1, aluno.getPrimeiroNome());
            BDSQLServer.COMANDO.setString(2, aluno.getUltimoNome());
            BDSQLServer.COMANDO.setString(3, aluno.getDatNascimento());
            BDSQLServer.COMANDO.setString(4, aluno.getEndereco());
            BDSQLServer.COMANDO.setInt(5,aluno.getRa());

            BDSQLServer.COMANDO.executeUpdate();
            BDSQLServer.COMANDO.commit();
        }catch (SQLException erro){
            BDSQLServer.COMANDO.rollback();
            throw new Exception("Erro ao atualizar dados do aluno !!");
        }
    }

    public static void excluir(int ra) throws Exception{
        if(!cadastrado(ra)){
            throw new Exception("Aluno não cadastrado !!");
        }

        try {
            String sql;

            sql = "DELETE FROM projeto2.Aluno " +
                  "WHERE Aluno.RA = ?";

            BDSQLServer.COMANDO.prepareStatement(sql);
            BDSQLServer.COMANDO.setInt(1, ra);
            BDSQLServer.COMANDO.executeUpdate();
            BDSQLServer.COMANDO.commit();
        }catch (SQLException erro){
            BDSQLServer.COMANDO.rollback();
            throw new Exception("Erro ao excluir aluno !!");
        }
    }

    public static MeuResultSet getAlunos() throws Exception{
        MeuResultSet ret = null;

        try{
            String sql;

            sql = "SELECT * " +
                  "FROM projeto2.Aluno";

            BDSQLServer.COMANDO.prepareStatement(sql);

            ret = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();
        }catch (SQLException erro){
            throw new Exception("Erro ao buscar alunos !!");
        }
        return ret;
    }

    public static MeuResultSet getAlunoRS(Integer ra) throws Exception{
        MeuResultSet ret = null;
        try{
            String sql;

            sql =  "SELECT * " +
                   "FROM projeto2.Aluno " +
                   "WHERE Aluno.RA = ?";

            BDSQLServer.COMANDO.prepareStatement(sql);

            BDSQLServer.COMANDO.setInt(1, ra);

            ret = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();
        }catch (SQLException erro){
            throw new Exception("Erro ao buscar aluno !!");
        }
        return ret;
    }
}