
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
    
    public void shell()
    {
        int dist;
        No i, auxDist, j, jdist, k, kdist;
        
        for(dist = 4; dist > 0; dist /= 2)
        {
            auxDist = localizaNo(inicio, dist);
            i = inicio;
            while(i != auxDist)
            {
                j = i;
                jdist = localizaNo(j, dist);
                while(jdist != null)
                {
                    if(j.getCod() > jdist.getCod())
                    {
                        permutacao(j, jdist);
                        
                        k = j;
                        kdist = localizaNo(k, -dist);
                        
                        while(kdist != null)
                        {
                            if(k.getCod() < kdist.getCod())
                                permutacao(k, kdist);
                            
                            k = kdist;
                            kdist = localizaNo(k, -dist);
                        }
                    }
                    
                    j = jdist;
                    jdist = localizaNo(j, dist);
                }
                
                i = i.getProx();
            }
        }
    }
    
    private No localizaNo(No no, int qntd)
    {
        while(qntd != 0 && no != null)
        {
            if(qntd > 0)
            {
                no = no.getProx();
                qntd--;
            }
            else
            {
                no = no.getAnt();
                qntd++;
            }
        }
        return no;
    }
    
    public void permutacao(No no1, No no2)
    {
        No aux = new No(no1.getCod(), null, null);
        
        no1.setCod(no2.getCod());
        no2.setCod(aux.getCod());
    }
}