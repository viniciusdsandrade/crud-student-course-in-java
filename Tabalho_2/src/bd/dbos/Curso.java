package bd.dbos;

import java.util.Objects;

public class Curso implements Cloneable {

    private int idCurso;
    private String nome;

    public void setIdCurso(int idC) throws Exception {
        if (idC <= 0) throw new Exception("Curso inv�lido !!");
        this.idCurso = idC;
    }

    public void setNome(String n) throws Exception {
        if (n == null || n.isEmpty()) throw new Exception("Nome inv�lido !!");
        this.nome = n;
    }

    public int getIdCurso() {
        return this.idCurso;
    }

    public String getNome() {
        return this.nome;
    }

    public Curso(int idC, String n) throws Exception {
        this.setIdCurso(idC);
        this.setNome(n);
    }

    @Override
    public String toString() {
        String ret = "";

        ret += "ID do curso: " + this.idCurso + "\n";
        ret += "Nome do curso: " + this.nome + "\n";

        return ret;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;

        Curso that = (Curso) obj;

        if (this.idCurso != that.idCurso) return false;
        return Objects.equals(this.nome, that.nome);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = 9;

        hash *= prime + this.idCurso;
        hash *= prime + this.nome.hashCode();

        return hash;
    }

    public Curso(Curso modelo) throws Exception {
        if (modelo == null)
            throw new Exception("Objeto ausente !!");
        this.idCurso = modelo.idCurso;
        this.nome = modelo.nome;
    }

    @Override
    public Object clone() {
        Curso ret = null;
        try {
            ret = new Curso(this);
        } catch (Exception ignored) {
        }
        return ret;
    }
}
