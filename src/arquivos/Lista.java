
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
//        insereFim(new No(9, null, null));
//        insereFim(new No(6, null, null));
//        insereFim(new No(3, null, null));
//        insereFim(new No(2, null, null));
//        insereFim(new No(7, null, null));
        
        
        Random r = new Random();
        int i;
        for(i = 0; i < Main.n; i++)
        {
            No aux = new No(r.nextInt(10), null, null);
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
            pos = buscaBinaria(i.getCod(), i.getAnt());
            j = i;
            while(pos != j)
            {
                permutacao(j, j.getAnt());
                j = localizaNo(j, -1);
            }
            permutacao(pos, j);
            i = localizaNo(i, 1);
        }
    }
    
    private No buscaBinaria(int cod, No tl)
    {
        No ini = inicio, meio, fim = tl;
        int pos = (lenLista(inicio, fim ) - 1) / 2;
        boolean flag;
        if(pos == 0)
            meio = ini;
        else
            meio = localizaNo(ini, pos);
            
        
        while(ini != meio && meio.getCod() != cod)
        {            
            if(cod < meio.getCod())
            {
                fim = meio;
                flag = true;
            }
            else
            {
                flag = false;
                ini = meio;
            }
            
            pos = lenLista(ini, fim) / 2;
        
            
            
            if(pos == 0)
                meio = ini;
            else
                if(flag)
                {
                    pos *= -1;
                    meio = localizaNo(fim, pos);
                }
            else
                   meio = localizaNo(ini, pos);  
        }
        
        if(cod > tl.getCod())
            return tl;
        
        if(cod > meio.getCod())
            return meio.getProx();
        
        return meio;
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
        int n, tl = 0;
        No pai, fd, fe, fmaior, fim = inicio;
        
        while(fim.getProx() != null)
        {
            fim = fim.getProx();
            tl++;
        }
        tl ++;
        
        while(fim != inicio)
        {
            for(n = tl / 2 - 1; n >= 0; n--)
            {
                if(n == 0)
                    pai = inicio;
                else                    
                    pai = localizaNo(inicio, n);
                fe = localizaNo(inicio, n + n + 1);
                fd = localizaNo(fe, 1);

                fmaior = fe;

                if(n + n + 2 < tl && fd.getCod() > fe.getCod())
                    fmaior = fd;

                if(fmaior.getCod() > pai.getCod())
                    permutacao(pai, fmaior);
            }
            
            permutacao(inicio, fim);
            
            tl--;
            fim = fim.getAnt();
        }
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
        quicksp(inicio, localizaNo(inicio, 0));
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
        
        if(j.getAnt() != null && j != ini && ini != j.getAnt())
            quicksp(ini, j.getAnt());
        if(i.getProx() != null && i != fim && fim != i.getProx())
            quicksp(i.getProx(), fim);
    }
    
    public void quick2()
    {       
        quickcp(inicio, localizaNo(inicio, 0));
    }
    
    private void quickcp(No ini, No fim)
    {
        No i = ini, j = fim, pivo;
        int pos = lenLista(ini, fim); 
        if(pos == 0)
            pivo = inicio;
        else
            pivo = localizaNo(ini, pos / 2);
        
        while(i != j)
        {
            while(i.getCod() < pivo.getCod())
                i = localizaNo(i, 1);
            
            while(j.getCod() > pivo.getCod())
                j = localizaNo(j, -1);
            
            permutacao(i, j);
            
            i = localizaNo(i, 1);
            if(i != j)
                j = localizaNo(j, -1);
        }
        
        if(j.getAnt() != null && j != ini && ini != j.getAnt())
            quickcp(ini, j.getAnt());
        if(i.getProx() != null && i != fim && fim != i.getProx())
            quickcp(i.getProx(), fim);
        
    }
    
    private No localizaNo(No no, int qntd)
    {
        if(qntd == 0) //Posicionar no fim da lista
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
    
    private void permutacao(No no1, No no2)
    {
        No aux = new No(no1.getCod(), null, null);
        
        no1.setCod(no2.getCod());
        no2.setCod(aux.getCod());
    }
    
    private int lenLista(No l1, No l2)
    {
        int i = 0;
        if(l1 != l2)
            i++;
        while(l1 != l2)
        {
            l1 = l1.getProx();
            i++;
        }
        
        return i;
    }
}