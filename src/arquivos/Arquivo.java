package arquivos;

import java.io.RandomAccessFile;
import java.io.IOException;
import java.util.Random;
import java.util.Stack;

public class Arquivo
{
    private String nomearquivo;
    private RandomAccessFile arquivo;
    private int comp, mov, cont = 0;
    

    public Arquivo(String nomearquivo)
    {
        try
        {
            arquivo = new RandomAccessFile(nomearquivo, "rw");
        }
        catch (IOException e) { System.out.println(e.getMessage()); }
    }

    public Arquivo() { }

    public void copiaArquivo(RandomAccessFile arquivoOrigem)
    {
        try
        {
            Registro reg = new Registro();
            int i = 0, tam = (int) arquivoOrigem.length() / Registro.length();
            this.arquivo = new RandomAccessFile("temp.dat", "rw");
            truncate(0);
            arquivoOrigem.seek(0);
            while(i < tam)
            {
                reg.leDoArq(arquivoOrigem);
                reg.gravaNoArq(arquivo);
                i++;
            }
        }
        catch(IOException e)
        {

        }
    }

    public RandomAccessFile getFile()
    {
        return arquivo;
    }

    public void truncate(long pos)
    {
        try
        {
            arquivo.setLength(pos * Registro.length());
        }
        catch (IOException exc) { }
    }

    public boolean eof()
    {
        boolean retorno = false;
        try
        {
            if (arquivo.getFilePointer() == arquivo.length())
                retorno = true;
        }
        catch (IOException e) { }

        return retorno;
    }

    public int filesize()
    {
        try
        {
            return (int) (arquivo.length() / Registro.length());
        }
        catch(IOException e)
        {
            return -1;
        }
    }

    public void initComp()
    {
        this.comp = 0;
    }

    public void initMov()
    {
        this.mov = 0;
    }

    public int getComp()
    {
        return comp;
    }

    public int getMov()
    {
        return mov;
    }

    public void exibirArq()
    {
        Registro aux = new Registro();
        seekArq(0);
        while (!this.eof())
        {
            aux.leDoArq(arquivo);
            aux.exibirReg();
        }
    }

    public void seekArq(int pos)
    {
        try
        {
            arquivo.seek(pos * Registro.length());
        }
        catch (IOException e){ }
    }
    
    // Geração de arquivos aleatórios
    
    public void geraArquivoOrdenado()
    {
        int j;
        for(j = 0; j < Main.n; j++)
        {
            Registro reg = new Registro(j);
            reg.gravaNoArq(arquivo);
        }
    }

    public void geraArquivoReverso()
    {
        int j;
        for(j = 0; j < Main.n; j++)
        {
            Registro reg = new Registro(Main.n - j - 1);
            reg.gravaNoArq(arquivo);
        }
    }

    public void geraArquivoRandomico()
    {
        Random rand = new Random();
        int j;
        for(j = 0; j < Main.n; j++)
        {
            Registro reg = new Registro(rand.nextInt(Main.n));
            reg.gravaNoArq(arquivo);
        }
    }    

    // Métodos de Ordenação
    
    public void selecao_direta()
    {
        Registro aux = new Registro(), reg = new Registro();
        int pos, i, j, tl = filesize();

        for(i = 0; i < tl - 1; i++)
        {
            pos = i;
            seekArq(pos);
            reg.leDoArq(arquivo);

            j = i + 1;
            while(j < tl)
            {
                seekArq(j);
                aux.leDoArq(arquivo);

                comp++;
                if(aux.getCodigo() < reg.getCodigo())
                {
                    pos = j;
                    seekArq(pos);
                    reg.leDoArq(arquivo);
                }
                j++;
            }

            seekArq(i);
            aux.leDoArq(arquivo);

            mov++;
            seekArq(pos);
            aux.gravaNoArq(arquivo);

            mov++;
            seekArq(i);
            reg.gravaNoArq(arquivo);
        }
    }
    
    public void insertionSort()
    {
        insercao_direta(0, filesize());
    } 

    private void insercao_direta(int ini, int fim)
    {
        Registro regaux = new Registro(), regpos1 = new Registro();
        int i = ini + 1, tl = fim, pos;
        
        
        while(i < tl)
        {
            pos = i;

            seekArq(pos - 1);
            regpos1.leDoArq(arquivo);
            regaux.leDoArq(arquivo);

            comp++;
            while(pos > ini && regaux.getCodigo() < regpos1.getCodigo())
            {
                mov++;
                seekArq(pos);
                regpos1.gravaNoArq(arquivo);

                pos--;

                seekArq(pos - 1);
                regpos1.leDoArq(arquivo);

                comp++;
            }

            mov++;
            seekArq(pos);
            regaux.gravaNoArq(arquivo);

            i++;
            System.out.print(mov + " ");
        }
    }

    public void insercao_binaria(int inicio, int fim)
    {
        Registro regAux = new Registro(), regPos1 = new Registro(), regPos = new Registro();
        int i, tl, pos;
        
        if(fim == -1)
            tl = filesize();
        else
            tl = fim;

        i = inicio + 1;
        while(i < tl)
        {
            seekArq(i);
            regAux.leDoArq(arquivo);

            pos = buscaBinaria(regAux.getCodigo(), inicio, i);

            for(int j = i; j > pos; j--)
            {
                seekArq(j - 1);
                regPos1.leDoArq(arquivo);
                regPos.leDoArq(arquivo);

                mov += 2;
                seekArq(j - 1);
                regPos.gravaNoArq(arquivo);
                regPos1.gravaNoArq(arquivo);
            }
            i++;
        }
    }

    private int buscaBinaria(int elemento, int ini, int tl)
    {
        int fim = tl - 1, meio = (ini + fim) / 2;
        Registro reg = new Registro();

        seekArq(meio);
        reg.leDoArq(arquivo);

        comp++;
        while(meio != ini && elemento != reg.getCodigo())
        {
            comp++;
            if(elemento < reg.getCodigo())
                fim = meio;
            else
                ini = meio;

            meio = (ini + fim) / 2;
            seekArq(meio);
            reg.leDoArq(arquivo);
            comp++;
        }

        comp++;
        seekArq(tl - 1);
        reg.leDoArq(arquivo);
        if(elemento > reg.getCodigo())
            return tl;

        comp++;
        seekArq(meio);
        reg.leDoArq(arquivo);
        if(elemento > reg.getCodigo())
            return meio + 1;

        return meio;
    }

    public void bolha()
    {
        Registro regA = new Registro(), regB = new Registro();
        int tl = filesize(), i = 0;

        while(tl > 1)
        {
            i = 0;
            while(i < tl - 1)
            {
                seekArq(i);
                regA.leDoArq(arquivo);
                regB.leDoArq(arquivo);

                comp++;
                if(regB.getCodigo() < regA.getCodigo())
                {
                    mov += 2;
                    seekArq(i);
                    regB.gravaNoArq(arquivo);
                    regA.gravaNoArq(arquivo);
                }

                i++;
            }
            tl--;
        }
    }

    public void shake()
    {
        int ini = 0, fim = filesize(), i;
        Registro regA = new Registro(), regB = new Registro();

        while(ini < fim)
        {
            for(i = ini; i < fim - 1; i++)
            {
                seekArq(i);
                regA.leDoArq(arquivo);
                regB.leDoArq(arquivo);

                comp++;
                if(regA.getCodigo() > regB.getCodigo())
                {
                    mov += 2;
                    seekArq(i);
                    regB.gravaNoArq(arquivo);
                    regA.gravaNoArq(arquivo);
                }
            }
            for(; i > ini; i--)
            {
                seekArq(i - 1);
                regA.leDoArq(arquivo);
                regB.leDoArq(arquivo);

                comp++;
                if(regB.getCodigo() < regA.getCodigo())
                {
                    mov += 2;
                    seekArq(i - 1);
                    regB.gravaNoArq(arquivo);
                    regA.gravaNoArq(arquivo);
                }
            }

            fim--;
            ini++;
        }
    }

    public void heap()
    {
        Registro regA = new Registro(), regB = new Registro();
        int tl = filesize(), pai, fd, fe, maiorf;

        while(tl > 1)
        {
            for(pai = tl / 2 - 1; pai >= 0; pai--)
            {
                fe = pai + pai + 1;
                fd = fe + 1;

                maiorf = fe;

                seekArq(fe);
                regA.leDoArq(arquivo);

                seekArq(fd);
                regB.leDoArq(arquivo);
                
                if(fd < tl)
                {
                    comp++;
                    if(regB.getCodigo() > regA.getCodigo())
                        maiorf = fd;
                }

                seekArq(pai);
                regA.leDoArq(arquivo);

                seekArq(maiorf);
                regB.leDoArq(arquivo);

                comp++;
                if(regB.getCodigo() > regA.getCodigo())
                {
                    mov++;
                    seekArq(pai);
                    regB.gravaNoArq(arquivo);

                    mov++;
                    seekArq(maiorf);
                    regA.gravaNoArq(arquivo);
                }
            }

            seekArq(0);
            regA.leDoArq(arquivo);

            seekArq(tl - 1);
            regB.leDoArq(arquivo);

            mov++;
            seekArq(0);
            regB.gravaNoArq(arquivo);

            mov++;
            seekArq(tl - 1);
            regA.gravaNoArq(arquivo);

            tl--;
        }
    }

    public void shell()
    {
        Registro reg1 = new Registro(), reg2 = new Registro();
        int i, j, k, dist, tl = filesize();

        for(dist = 4; dist > 0; dist /= 2)
        {
            for(i = 0; i < dist; i++)
            {
                for(j = i; j + dist < tl; j += dist)
                {
                    seekArq(j);
                    reg1.leDoArq(arquivo);
                    seekArq(j + dist);
                    reg2.leDoArq(arquivo);

                    comp++;
                    if(reg1.getCodigo() > reg2.getCodigo())
                    {
                        mov += 2;
                        seekArq(j);
                        reg2.gravaNoArq(arquivo);
                        seekArq(j + dist);
                        reg1.gravaNoArq(arquivo);

                        k = j;

                        if(k - dist >= 0)
                        {
                            comp++;

                            seekArq(k);
                            reg1.leDoArq(arquivo);
                            seekArq(k - dist);
                            reg2.leDoArq(arquivo);
                        }

                        for(; k >= 0 && reg1.getCodigo() < reg2.getCodigo(); k -= dist)
                        {
                            mov += 2;
                            seekArq(k);
                            reg2.gravaNoArq(arquivo);
                            seekArq(k - dist);
                            reg1.gravaNoArq(arquivo);

                            if(k - dist >= 0)
                            {
                                comp++;

                                seekArq(k);
                                reg1.leDoArq(arquivo);
                                seekArq(k - dist);
                                reg2.leDoArq(arquivo);
                            }
                        }
                    }
                }
            }
        }
    }

    public void quick1()
    {
        quicksp(0, filesize() - 1);
    }

    private void quicksp(int ini, int fim)
    {
        int i = ini, j = fim;
        Registro reg1 = new Registro(), reg2 = new Registro();

        while(i < j)
        {
            seekArq(i);
            reg1.leDoArq(arquivo);
            seekArq(j);
            reg2.leDoArq(arquivo);

            while(i < j && reg1.getCodigo() <= reg2.getCodigo())
            {
                comp++;

                i++;
                seekArq(i);
                reg1.leDoArq(arquivo);
            }

            if(i < j)
            {
                mov += 2;
                seekArq(i);
                reg2.gravaNoArq(arquivo);
                seekArq(j);
                reg1.gravaNoArq(arquivo);
            }

            seekArq(i);
            reg1.leDoArq(arquivo);
            seekArq(j);
            reg2.leDoArq(arquivo);

            while(j > i && reg2.getCodigo() >= reg1.getCodigo())
            {
                comp++;

                j--;
                seekArq(j);
                reg2.leDoArq(arquivo);
            }

            if(i < j)
            {
                mov += 2;

                seekArq(i);
                reg2.gravaNoArq(arquivo);
                seekArq(j);
                reg1.gravaNoArq(arquivo);
            }
        }

        if(ini < j - 1)
            quicksp(ini, j - 1);

        if(fim > i + 1)
            quicksp(j + 1, fim);

    }

    public void quick2()
    {
        quickcp(0, filesize() - 1);
    }

    private void quickcp(int ini, int fim)
    {
        int i = ini, j = fim;
        Registro reg1 = new Registro(), reg2 = new Registro(), pivo = new Registro();

        seekArq((ini + fim) / 2);
        pivo.leDoArq(arquivo);

        while(i < j)
        {
            seekArq(i);
            reg1.leDoArq(arquivo);

            comp ++;
            while(reg1.getCodigo() < pivo.getCodigo())
            {
                i++;
                seekArq(i);
                reg1.leDoArq(arquivo);

                comp ++;
            }
            seekArq(j);
            reg2.leDoArq(arquivo);

            comp ++;
            while(reg2.getCodigo() > pivo.getCodigo())
            {
                j--;
                seekArq(j);
                reg2.leDoArq(arquivo);

                comp ++;
            }

            if(i <= j)
            {
                mov += 2;

                seekArq(i);
                reg2.gravaNoArq(arquivo);
                seekArq(j);
                reg1.gravaNoArq(arquivo);

                i++; j--;
            }
        }

        if(ini < j)
            quickcp(ini, j);

        if(fim > i)
            quickcp(i, fim);
    }

    public void merge1()
    {
        int seq = 1, tl = filesize(), meio = tl / 2;
        int i, j, k, aux_seq, aux_seq2;
        Registro reg1 = new Registro(), reg2 = new Registro();
        truncate(2 * tl);
        
        while(seq < tl)
        {
            //Partição ---------------------------------------------------------
           
            for(i = 0; i < meio; i++)
            {
                seekArq(i);
                reg1.leDoArq(arquivo);
                seekArq(tl + i);
                reg1.gravaNoArq(arquivo);
                seekArq(i + meio);
                reg2.leDoArq(arquivo);
                seekArq(tl + meio + i);
                reg2.gravaNoArq(arquivo);
            }
            //------------------------------------------------------------------
            // Fusão -----------------------------------------------------------
            k = i = j = 0;
            aux_seq = seq;
            aux_seq2 = seq;
            while(k < tl)
            {
                while(i < aux_seq2 && j < aux_seq2)
                {
                    seekArq(tl + i);
                    reg1.leDoArq(arquivo);
                    seekArq(tl + meio + j);
                    reg2.leDoArq(arquivo);
                    
                    comp++;
                    if(reg1.getCodigo() < reg2.getCodigo())
                    {
                        mov++;
                        seekArq(k);
                        reg1.gravaNoArq(arquivo);
                        i++;
                    }
                    else
                    {
                        mov++;
                        seekArq(k);
                        reg2.gravaNoArq(arquivo);
                        j++;
                    }
                    k++;
                }

                while(i < aux_seq2)
                {
                    mov++;
                    seekArq(k++);
                    reg1.gravaNoArq(arquivo);
                    
                    seekArq(++i + tl);
                    reg1.leDoArq(arquivo);                    
                }

                while(j < aux_seq2)
                {
                    mov++;
                    seekArq(k++);
                    reg2.gravaNoArq(arquivo);
                    
                    seekArq(++j + tl + meio);
                    reg2.leDoArq(arquivo);
                }

                aux_seq2 += aux_seq;
            }
            //------------------------------------------------------------------
            seq += seq;
        }
        truncate(tl);
    }
    
    public void merge2()
    {
        int meio, esq, dir;
        Stack<Integer> p1 = new Stack<Integer>();
        Stack<Integer> p2 = new Stack<Integer>();
        Arquivo aux = new Arquivo("auxMerge.dat");
        p1.push(0);
        p1.push(filesize() - 1);
        while(!p1.isEmpty())
        {
            dir = p1.pop();
            esq = p1.pop();
            
            if(esq < dir)
            {
                p2.push(esq);
                p2.push(dir);
                meio = (esq + dir) / 2;
                p1.push(esq);
                p1.push(meio);
                p1.push(meio + 1);
                p1.push(dir);
            }
        }
        
        while(!p2.isEmpty())
        {
            dir = p2.pop();
            esq = p2.pop();
            meio = (esq + dir) / 2;
            
            fusao(aux, esq, meio, meio + 1, dir);
        }
        aux.truncate(0);
    }
    
    private void fusao(Arquivo aux, int ini1, int fim1, int ini2, int fim2)
    {
        int k = 0, i = ini1, j = ini2;
        Registro reg1= new Registro(), reg2 = new Registro();
        
        aux.seekArq(0);
        while(i <= fim1 && j <= fim2)
        {            
            seekArq(i);
            reg1.leDoArq(arquivo);
            seekArq(j);
            reg2.leDoArq(arquivo);
            
            comp++;
            if(reg1.getCodigo() < reg2.getCodigo())
            {
                seekArq(k++);
                reg1.gravaNoArq(aux.getFile());
                i++;
            }
            else
            {
                seekArq(k++);
                reg2.gravaNoArq(aux.getFile());
                j++;
            }
        }
        
        while(i <= fim1)
        {
            seekArq(i++);
            reg1.leDoArq(arquivo);
            
            seekArq(k++);
            reg1.gravaNoArq(aux.getFile());
        }
        
        while(j <= fim2)
        {
            seekArq(j++);
            reg2.leDoArq(arquivo);
            
            seekArq(k++);
            reg2.gravaNoArq(aux.getFile());
        }
        
        
        aux.seekArq(0);
        for(i = 0; i < k; i++)
        {
            seekArq(i + ini1);
            mov++;
            reg1.leDoArq(aux.getFile());
            reg1.gravaNoArq(arquivo);
        }
        aux.truncate(0);
    }
    
    public void countingSort()
    {
        int range = Main.n, TL = filesize(), i, aux;
        Registro reg = new Registro();
        Registro aux_arq[] = new Registro[TL];
        Arquivo auxc = new Arquivo("auxMerge.dat");
        auxc.truncate(TL);
        int count[] = new int[range];
        
        //contar os elementos
        for(i = 0; i < TL; i++)
        {
            seekArq(i);
            reg.leDoArq(arquivo);
            count[reg.getCodigo()]++;
        }
        
        //arrumar o vetor de contador
        for(i = 0; i < range - 1; i++)
            count[i + 1] += count[i];
        
        //ordenando em um array auxiliar
        for(i = TL - 1; i >= 0; i--)
        {
            seekArq(i);
            reg.leDoArq(arquivo);
            auxc.seekArq(--count[reg.getCodigo()]);
            reg.gravaNoArq(auxc.getFile());
        } 
        
        //gravando no arquivo
        seekArq(0);
        auxc.seekArq(0);
        for(i = 0; i < TL; i++)
        {
            mov++;
            reg.leDoArq(auxc.getFile());
            reg.gravaNoArq(arquivo);
        }
        auxc.truncate(0);
    }
    
    public void gnome()
    {
        int i = 0, tl = filesize(), j;
        Registro reg1 = new Registro(), reg2 = new Registro();
        
        for(i = 0; i < tl - 1; i++)
        {
            seekArq(i);
            reg1.leDoArq(arquivo);
            reg2.leDoArq(arquivo);
            
            comp++;
            if(reg1.getCodigo() > reg2.getCodigo())
            {
                j = i;
                
                comp++;
                while(j >= 0 && reg2.getCodigo() < reg1.getCodigo())
                {
                    mov += 2;
                    seekArq(j);
                    reg2.gravaNoArq(arquivo);
                    reg1.gravaNoArq(arquivo);
                    
                    j--;
                    if(j >= 0)
                    {
                        seekArq(j);
                        reg1.leDoArq(arquivo);
                        reg2.leDoArq(arquivo);
                        comp++;
                    }
                }
            }
        }
    }
    
    public void bucket()
    {
        Registro mat[][] = new Registro[10][Main.n];
        int[] index = new int[10];
        Registro reg;
        int i, tl = filesize(), j, k;
        
        // Insere os elementos na lista
        for(i = 0; i < tl; i++)
        {
            reg = new Registro();
            seekArq(i);
            reg.leDoArq(arquivo);
            mat[(int) ((reg.getCodigo() / (float) Main.n) * 10)][index[(int) ((reg.getCodigo() / (float) Main.n) * 10)]++] = reg;
        }
        
        for(i = 0; i < 10; i++)
        {
            j = 1;
            while(j < index[i])
            {
                k = j;
                comp++;
                while(mat[i][k].getCodigo() < mat[i][k - 1].getCodigo())
                {
                    reg = mat[i][k];
                    mat[i][k] = mat[i][k - 1];
                    mat[i][k - 1] = reg;
                    comp++;
                }
                j++;
            }
           
            seekArq(0);
            for(i = 0; i < 10; i++)
                for(j = 0; j < index[i]; j++)
                {
                    mov++;
                    mat[i][j].gravaNoArq(arquivo);
                }
        }
    }
    
    public void radix()
    {
        int i, j, max = Main.n, TL = filesize();
        Registro reg = new Registro();
        int count[];
        Arquivo aux = new Arquivo("auxMerge.dat");
        for(i = 1; i < max; i *= 10)
        {
            aux.truncate(TL);
            aux.seekArq(0);
            count = new int[10];
            
            //contando os elementos
            seekArq(0);
            for(j = 0; j < TL; j++)
            {
                reg.leDoArq(arquivo);
                count[(reg.getCodigo() / i) % 10]++;
            }
            
            //arrumar o vetor de contador
            for(j = 0; j < 9; j++)
                count[j + 1] += count[j];
            
            //ordenar no vetor auxiliar
            for(j = TL - 1; j >= 0; j--)
            {
                seekArq(j);     
                reg.leDoArq(arquivo);
                aux.seekArq(--count[(reg.getCodigo() / i) % 10]);
                reg.gravaNoArq(aux.getFile());
            }
            
            //gravar no arquivo
            aux.seekArq(0);
            seekArq(0);
            for(j = 0; j < TL; j++)
            {
                mov++;
                reg.leDoArq(aux.getFile());
                reg.gravaNoArq(arquivo);
            }
        }
        aux.truncate(0);
    }
    
    public void comb()
    {
        int i = 0, tl = filesize(), fator = (int) (tl / 1.3);
        Registro reg1 = new Registro(), reg2 = new Registro();
        
        while(fator > 0 && i != tl - 1)
        {
            i = 0;
            while(i + fator < tl)
            {
                seekArq(i);
                reg1.leDoArq(arquivo);
                seekArq(fator + i);
                reg2.leDoArq(arquivo);
                
                comp++;
                if(reg1.getCodigo() > reg2.getCodigo())
                {
                    mov += 2;
                    seekArq(i);
                    reg2.gravaNoArq(arquivo);
                    seekArq(fator + i);
                    reg1.gravaNoArq(arquivo);
                }
                i++;
            }
            fator = (int) (fator / 1.3);
        }
    }
    
    public void tim()
    {
        int i, tl = filesize(), run = 32, size, esq, dir, meio;
        Arquivo aux = new Arquivo("auxMerge.dat");
        aux.truncate(tl);
        
        for(i = 0; i < tl; i += run)
        {
            if(i + run < tl)
                insercao_binaria(i, i + run);
            else
                insercao_binaria(i, tl);
        }
        
        for(size = run; size < tl; size *= 2)
            for(esq = 0; esq < tl; esq += 2 * size)
            {
                if(esq + 2 * size < tl)
                    dir = esq + 2 * size - 1;
                else
                    dir = tl - 1;

                meio = (esq + dir) / 2;

                fusao(aux, esq, meio, meio + 1, dir);
            }
    }
}
