
package arquivos;

import java.util.Random;

public class Lista 
{
    private No inicio;
    
    public Lista()
    {
        inicio = null;
    }
    
    public No getLista()
    {
        return inicio;
    }
    
    public void copiaLista(No l)
    {
        inicio = null;
        No aux = l, novo = null;
        
        while(aux != null)
        {
            novo = new No(aux.getCod(), null, null);
            insereFim(novo);
            aux = aux.getProx();
        }
    }
    
    private void insereFim(No novo)
    {
        if(inicio == null)
            inicio = novo;
        else
        {
            No aux = inicio;
            while(aux.getProx() != null)
                aux = aux.getProx();
            
            novo.setAnt(aux);
            aux.setProx(novo);
        }
    }      
    
    public void exibeLista()
    {
        No aux = inicio;
        
        while(aux != null)
        {
            System.out.println(aux + " ");
            aux = aux.getProx();
        }
    }

    public void geraOrdenada()
    {
        for(int i = 0; i < Main.n; i++)
        {
            No aux = new No(i, null, null);
            insereFim(aux);
        }
    }
    
    public void geraReversa()
    {
        for(int i = 0; i < Main.n; i++)
        {
            No aux = new No(Main.n - i - 1, null, null);
            insereFim(aux);
        }
    }
    
    public void geraRandomica()
    {
        Random r = new Random();
        for(int i = 0; i < Main.n; i++)
        {
            No aux = new No(r.nextInt(1200), null, null);
            insereFim(aux);
        }
    }
    
    //Métodos de Ordenação
    public void insercao_direta()
    {
        No i, pos;
        int aux;
        String lixo;
        i = inicio.getProx();
        
        while(i != null)
        {
            pos = i;
            aux = i.getCod();
            lixo = i.getLixo();
            while(pos != inicio && aux < pos.getAnt().getCod())
            {
                pos.setCod(pos.getAnt().getCod());
                pos.setLixo(pos.getAnt().getLixo());
                pos = pos.getAnt();
            }
            pos.setCod(aux);
            pos.setLixo(lixo);
            i = i.getProx();
        } 
    }
    
    public void insercao_binaria()
    {
        
    }
    
    private No buscaBinaria()
    {
        return null;
    }
    
    public void selecao_direta()
    {
        
    }
    
    public void bolha()
    {
        
    }
    
    public void shake()
    {
        
    }
    
    public void heap()
    {
        
    }
}