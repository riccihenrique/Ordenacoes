package arquivos;

public class No 
{
    private final int tf = 1022;
    private int cod;
    private char[] lixo = new char[tf];
    private No prox;
    private No ant;

    public No() {
    }

    public No(int cod, No prox, No ant) {
        this.cod = cod;
        this.prox = prox;
        this.ant = ant;
        
        for(int i = 0; i < tf; i++)
            lixo[i] = 'X';
    }
    
    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getLixo() {
        return new String(lixo);
    }

    public void setLixo(String lixo) {
        for(int i = 0; i < tf; i++)
            this.lixo[i] = lixo.charAt(i);
    }

    public No getProx() {
        return prox;
    }

    public void setProx(No prox) {
        this.prox = prox;
    }

    public No getAnt() {
        return ant;
    }

    public void setAnt(No ant) {
        this.ant = ant;
    }    

    @Override
    public String toString() {
        return "" + cod;
    }
}