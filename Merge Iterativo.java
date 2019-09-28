  
public void mergeSort()
{
    Arquivo aux = new Arquivo("auxtempmerge");
    int esq,dir, meio;
    Stack<Integer> p1 = new Stack();
    Stack<Integer> p2 = new Stack();
    p1.push(0);
    p1.push(filesize()-1);
    while(!p1.isEmpty())
    {
        dir = p1.pop();
        esq = p1.pop();
        if(esq < dir)
        {
            p2.push(esq);
            p2.push(dir);
            meio = (esq+dir)/2;
            p1.push(esq);
            p1.push(meio);
            p1.push(meio+1);
            p1.push(dir);
        }
    }
    while(!p2.isEmpty())
    {
        dir = p2.pop();
        esq = p2.pop();
        meio = (esq+dir)/2;
        fusao(aux, esq, meio, meio+1, dir);
    }
    aux.truncate(0);
}

public void fusao(Arquivo aux, int ini1, int fim1, int ini2, int fim2)
    {
        int k = ini1, aux_ini;
        Registro reg1,reg2;
        reg1 = new Registro();
        reg2 = new Registro();
        aux.seekArq(0);
        aux_ini = ini1;
        while(ini1 <= fim1 && ini2 <= fim2)
        {
            seekArq(ini1);
            reg1.leDoArq(arquivo);
            seekArq(ini2);
            reg2.leDoArq(arquivo);
            if(reg1.getNumero() < reg2.getNumero())
            {
                reg1.gravaNoArq(aux.getFile());
                ini1++;
            }
            else
            {
                reg2.gravaNoArq(aux.getFile());
                ini2++;
            }
        }
        while(ini1 <= fim1)
        {
            seekArq(ini1);
            reg1.leDoArq(arquivo);
            reg1.gravaNoArq(aux.getFile());
                ini1++;
        }
        while(ini2 <= fim2)
        {
            seekArq(ini2);
            reg1.leDoArq(arquivo);
            reg1.gravaNoArq(aux.getFile());
                ini2++;
        }
        aux.seekArq(0);
        for(int i = aux_ini; i <= fim2; i++)
        {
            reg1.leDoArq(aux.getFile());
            seekArq(i);
            reg1.gravaNoArq(arquivo);
        }
    }