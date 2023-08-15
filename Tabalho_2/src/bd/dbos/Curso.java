package bd.dbos;

public class Curso implements Cloneable{
    private int     idCurso;
    private String  nome;

    public void setIdCurso(int idC) throws Exception{
        if(idC <= 0){
            throw new Exception("Curso inv�lido !!");
        }

        this.idCurso = idC;
    }

    public void  setNome(String n) throws Exception{
        if(n == null || n.equals(""))
            throw new Exception("Nome inv�lido !!");

        this.nome = n;
    }

    public int getIdCurso(){
        return this.idCurso;
    }

    public String getNome(){
        return this.nome;
    }

    public Curso(int idC, String n) throws Exception{
        this.setIdCurso(idC);
        this.setNome(n);
    }

    @Override
    public String toString(){
        String ret = "";

        ret += "ID do curso: " + this.idCurso + "\n";
        ret += "Nome do curso: " + this.nome + "\n";

        return ret;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj)
            return true;
        if (obj == null)
            return false;
        if(!(obj instanceof Curso))
            return false;

        Curso c = (Curso) obj;

        if(this.idCurso != c.idCurso)
            return false;
        if(this.nome != c.nome)
            return false;

        return true;
    }

    @Override
    public int hashCode(){
        int ret = 9;

        ret = ret*11 +  Integer.valueOf(this.idCurso);
        ret = ret*11 + this.nome.hashCode();

        return ret;
    }

    public Curso (Curso modelo) throws Exception{
        if(modelo == null)
            throw new Exception("Objeto ausente !!");
        this.idCurso = modelo.idCurso;
        this.nome = modelo.nome;
    }

    public Object clone(){
        Curso ret = null;
        try {
            ret = new Curso(this);
        }catch (Exception e){}

        return ret;
    }
}
