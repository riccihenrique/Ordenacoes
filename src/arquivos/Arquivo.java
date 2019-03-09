package arquivos;

import java.io.RandomAccessFile;
import java.io.IOException;
import java.util.Random;

public class Arquivo
{
    private String nomearquivo;
    private RandomAccessFile arquivo;
    private int comp, mov;

    public Arquivo(String nomearquivo)
    {
        try
        {
            arquivo = new RandomAccessFile(nomearquivo, "rw");
        } 
        catch (IOException e) { }
    }
    
    public Arquivo()
    {
        
    }
    
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
        catch(Exception e)
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
        catch(Exception e)
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
    
    public void insercao_direta()
    {
        Registro regaux = new Registro(), regpos1 = new Registro();
        int i = 1, tl = filesize(), pos;
        
        while(i < tl)
        {
            pos = i;
            
            seekArq(pos - 1);
            regpos1.leDoArq(arquivo);
            regaux.leDoArq(arquivo);
            
            comp++;
            while(pos > 0 && regaux.getCodigo() < regpos1.getCodigo())
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
        }
    }
    
    public void insercao_binaria()
    {
        Registro regAux = new Registro(), regPos1 = new Registro(), regPos = new Registro();
        int i, tl = filesize(), pos;
        
        i = 1;
        while(i < tl)
        {
            seekArq(i);
            regAux.leDoArq(arquivo);
            
            pos = buscaBinaria(regAux.getCodigo(), i);
            
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
    
    private int buscaBinaria(int elemento, int tl)
    {
        int ini = 0, fim = tl - 1, meio = fim / 2;
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
                
                comp++;
                if(fd < tl && regB.getCodigo() > regA.getCodigo())
                    maiorf = fd;
                
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
    
    public void geraArquivoOrdenado() 
    {
        for(int i = 0; i < Main.n; i++)
        {
            Registro reg = new Registro(i);
            reg.gravaNoArq(arquivo);
        }
    }
    
    public void geraArquivoReverso() 
    {
        for(int i = 0; i < Main.n; i++)
        {
            Registro reg = new Registro(Main.n - i - 1);
            reg.gravaNoArq(arquivo);
        }
    }
    
    public void geraArquivoRandomico() 
    {
        Random rand = new Random();
        for(int i = 0; i < Main.n; i++)
        {
            Registro reg = new Registro(rand.nextInt(1200));
            reg.gravaNoArq(arquivo);
        }
    }
}