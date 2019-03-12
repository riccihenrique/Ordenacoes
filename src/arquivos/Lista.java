
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
        int i;
        for(i = 0; i < Main.n; i++)
        {
            No aux = new No(i, null, null);
            insereFim(aux);
        }
    }
    
    public void geraReversa()
    {
        int i;
        for(i = 0; i < Main.n; i++)
        {
            No aux = new No(Main.n - i - 1, null, null);
            insereFim(aux);
        }
    }
    
    public void geraRandomica()
    {
        Random r = new Random();
        int i;
        for(i = 0; i < Main.n; i++)
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
        No i, j, pos;
        
        i = inicio.getProx();
        
        while(i != null)
        {
            pos = buscaBinaria(i.getCod(), i);
            
            while(pos != i)
            {
                permutacao(pos, pos.getAnt());
                pos = localizaNo(pos, -1);
            }
            i = localizaNo(i, 1);
        }
    }
    
    private No buscaBinaria(int cod, No fim)
    {
        No ini = inicio, meio = null;
        
        while(ini != meio)
        {            
            
        }
        
        return null;
    }
    
    public void selecao_direta()
    {
        No menor, i = inicio, j;
        
        while(i != null)
        {
            j = i;  //Verificar o caso do i.getProx nulo
            menor = j;
            while(j != null)
            {
                if(j.getCod() < menor.getCod())
                    menor = j;
                
                j = j.getProx();
            }
            
            permutacao(i, menor);
            
            i = i.getProx();
        }
    }
    
    public void bolha()
    {
        No i, j;
        
        j = localizaNo(inicio, 0);
        
        while(j != inicio.getProx())
        {
            i = inicio;
            while(i != j)
            {
                if(i.getCod() > i.getProx().getCod())
                    permutacao(i, i.getProx());
                
                i = i.getProx();
            }
            j = j.getAnt();
        }
    }
    
    public void shake()
    {
        No i, j, aux;
        
        j = localizaNo(inicio, 0);
        
        i = inicio;
        while(j != i)
        {
            aux = i;
            while(aux != j)
            {
                if(aux.getCod() > aux.getProx().getCod())
                    permutacao(aux, aux.getProx());
                
                aux = aux.getProx();
            }
            aux = aux.getAnt();
            while(aux != i)
            {
                if(aux.getCod() < aux.getAnt().getCod())
                    permutacao(aux, aux.getAnt());
                aux = aux.getAnt();
            }
            
            i = i.getProx();
            if(i != j)
                j = j.getAnt();
        }
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
    
    public void quick1()
    {
        No aux = inicio;
        
        while(aux.getProx() != null)
            aux = aux.getProx();
        
        quicksp(inicio, aux);
    }
    
    private void quicksp(No ini, No fim)
    {
        No i = ini, j = fim;
        
        while(i != j)
        {
            while(i != j && i.getCod() <= j.getCod())
                i = localizaNo(i, 1);
            
            permutacao(i, j);
            while(i != j && j.getCod() >= i.getCod())
                j = localizaNo(j, -1);
            
            permutacao(i, j);
        }
        
        if(i.getAnt() != null &&ini != i.getAnt())
            quicksp(ini, i.getAnt());
        if(j.getProx() != null && fim != j.getProx())
            quicksp(j.getProx(), fim);
        
    }
    
    private No localizaNo(No no, int qntd)
    {
        if(qntd == 0) //Posicionar no fim da fila
        {
            while(no.getProx() != null)
                no = no.getProx();
        }
        else
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