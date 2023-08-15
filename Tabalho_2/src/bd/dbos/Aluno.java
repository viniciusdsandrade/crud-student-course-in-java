package bd.dbos;

//import Utilitarios.Data;
import bd.core.MeuResultSet;

public class Aluno implements Cloneable {

    private int ra;
    private int curso;
    private String primeiroNome;
    private String ultimoNome;
    private String rg;
    private String datNascimento;
    private String endereco;

    private void setRa(int ra) throws Exception {
        if (ra <= 0)
            throw new Exception("RA Inv�lido !!");

        this.ra = ra;
    }

    public void setPrimeiroNome(String nome1) throws Exception {
        if (nome1 == null || nome1.equals(""))
            throw new Exception("Nome n�o fornecido !!");

        this.primeiroNome = nome1;
    }

    public void setUltimoNome(String nome2) throws Exception {
        if (nome2 == null || nome2.equals(""))
            throw new Exception("Sobrenome nome n�o fornecido !!");

        this.ultimoNome = nome2;
    }

    private void setRg(String rg) throws Exception {
        if (rg == null || rg.equals(""))
            throw new Exception("RG inv�lido !!");

        this.rg = rg;
    }

    public void setEndereco(String end) throws Exception {
        if (end == null || end.equals(""))
            throw new Exception("Endere�o N�o fornecido !!");

        this.endereco = end;
    }

    public void setDatNascimento(String dN) throws Exception {
        String d, m, a;
        if (dN == null || dN.equals(""))
            throw new Exception("Data de nascimento n�o fornecida !!");

        d = dN.substring(0, 2);
        m = dN.substring(3, 5);
        a = dN.substring(6, 10);

        Data data = new Data(Byte.parseByte(d), Byte.parseByte(m), Short.parseShort(a));
        this.datNascimento = data.getDia() + "/" + data.getMes() + "/" + data.getAno();
    }

    public void setCurso(int curso) throws Exception {
        if (curso <= 0)
            throw new Exception("Curso inv�lido !!");

        this.curso = curso;
    }

    public int getRa() {
        return this.ra;
    }

    public String getPrimeiroNome() {
        return this.primeiroNome;
    }

    public String getUltimoNome() {
        return this.ultimoNome;
    }

    public String getRg() {
        return this.rg;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public String getDatNascimento() {
        return this.datNascimento;
    }

    public int getCurso() {
        return this.curso;
    }

    public Aluno(int ra, int idC, String pNome, String uNome, String rg, String dNasc, String end) throws Exception {
        this.setRa(ra);
        this.setCurso(idC);
        this.setPrimeiroNome(pNome);
        this.setUltimoNome(uNome);
        this.setRg(rg);
        this.setDatNascimento(dNasc);
        this.setEndereco(end);
    }

    @Override
    public String toString() {
        String ret = "";

        ret += "RA................: " + this.ra + "\n";
        ret += "Primeiro nome.....: " + this.primeiroNome + "\n";
        ret += "Ultimo nome.......: " + this.ultimoNome + "\n";
        ret += "Curso.............: " + this.curso + "\n";
        ret += "RG................: " + this.rg + "\n";
        ret += "Data de Nascimento: " + this.datNascimento + "\n";
        ret += "Endere�o..........: " + this.endereco + "\n";

        return ret;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Aluno))
            return false;

        Aluno a = (Aluno) obj;

        if (this.ra != a.ra)
            return false;
        if (this.curso != a.curso)
            return false;
        if (this.primeiroNome != a.primeiroNome)
            return false;
        if (this.ultimoNome != a.ultimoNome)
            return false;
        if (this.rg != a.rg)
            return false;
        if (this.datNascimento != a.datNascimento)
            return false;
        if (this.endereco != a.endereco)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int ret = 9;

        ret = ret * 11 + Integer.valueOf(this.ra);
        ret = ret * 11 + Integer.valueOf(this.curso);
        ret = ret * 11 + this.primeiroNome.hashCode();
        ret = ret * 11 + this.ultimoNome.hashCode();
        ret = ret * 11 + this.rg.hashCode();
        ret = ret * 11 + this.datNascimento.hashCode();
        ret = ret * 11 + this.endereco.hashCode();

        return ret;
    }

    public Aluno(Aluno modelo) throws Exception {
        if (modelo == null)
            throw new Exception("Objeto ausente");
        this.ra = modelo.ra;
        this.curso = modelo.curso;
        this.primeiroNome = modelo.ultimoNome;
        this.ultimoNome = modelo.ultimoNome;
        this.rg = modelo.rg;
        this.datNascimento = modelo.datNascimento;
        this.endereco = modelo.endereco;
    }

    public Object clone() {
        Aluno ret = null;
        try {
            ret = new Aluno(this);
        } catch (Exception e) {
        }

        return ret;
    }
}