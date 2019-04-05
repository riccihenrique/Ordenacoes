package arquivos;

import java.io.IOException;
import java.time.LocalTime;

public class Main 
{
    static final int n = 16;
    private Arquivo ordenado, reverso, randomico, auxreverso, auxrandomico, tabela;
    private int tini, tfim, mov, com;
    private Lista lordenada, lreversa, lrandomica, lauxrandomica, lauxreversa;    
    
    private void initArquivos()
    {
        this.ordenado = new Arquivo("Odenado.dat");
        this.reverso = new Arquivo("Reverso.dat");
        this.randomico = new Arquivo("Randomico.dat");
        this.tabela = new Arquivo("Tabela.txt");
        this.auxrandomico = new Arquivo();
        this.auxreverso = new Arquivo();
    }
    
    private void escreveCabecalho() throws IOException
    {
        tabela.getFile().writeBytes("|MÉTODOS DE ORDENAÇÃO|ARQUIVO ORDENADO\t\t\t\t|ARQUIVO EM ORDEM REVERSA\t\t "
                + "|ARQUIVO RANDÔMICO\n");
        tabela.getFile().writeBytes("|\t\t     |Comp. 1\t|Comp. 2|Mov. 1\t|Mov. 2\t|Tempo\t|Comp. 1|Comp. 2|Mov. 1\t|Mov. 2\t|Tempo\t|"
                + "Comp. 1|Comp. 2|Mov. 1\t|Mov. 2\t|Tempo\t|\n");
    }
    
    private void escreveTabela(String nomeMetodo, int cp, double ce, int mp, double me, double tempo) throws IOException
    {
        tabela.getFile().writeBytes(nomeMetodo + " " + cp + "\t| " + (int) ce + "\t| " + mp + "\t| " +  (int) me + "\t| " + (int) tempo + "\t|");
    }
    
    private void insertionSort() throws IOException
    {
        //INSERÇÃO DIRETA
        //Arquivo Odernado
        ordenado.initComp();
        ordenado.initMov();
        tini = (int) System.currentTimeMillis();
        ordenado.insertionSort();
        tfim = (int) System.currentTimeMillis();
        com = ordenado.getComp();
        mov = ordenado.getMov();
        escreveTabela("|Insertion Sort\t     |", com, n - 1, mov, 3 * (n - 1) , tfim - tini);
        
        //Arquivo Reverso
        auxreverso.copiaArquivo(reverso.getFile());
        auxreverso.initComp();
        auxreverso.initMov();
        tini = (int) System.currentTimeMillis();
        auxreverso.insertionSort();
        tfim = (int) System.currentTimeMillis();
        com = auxreverso.getComp();
        mov = auxreverso.getMov();
        escreveTabela("", com, (Math.pow(n, 2) + n - 4) / 4, mov, (Math.pow(n, 2) + (3 * n) - 4) / 2 , tfim - tini);
        
//        Arquivo Randômico
        auxrandomico.copiaArquivo(randomico.getFile());
        auxrandomico.initComp();
        auxrandomico.initMov();
        tini = (int) System.currentTimeMillis();
        auxrandomico.insertionSort();
        tfim = (int) System.currentTimeMillis();
        com = auxrandomico.getComp();
        mov = auxrandomico.getMov();
        escreveTabela("", com, (Math.pow(n, 2) + n - 2) / 4, mov, (Math.pow(n, 2) + (9 * n) - 10) / 4, tfim - tini);
        tabela.getFile().writeBytes("\n");
    }
    
    private void binaryInsertion() throws IOException
    {
        //INSERÇÃO BINÁRIA
        //Arquivo Odernado
        ordenado.initComp();
        ordenado.initMov();
        tini = (int) System.currentTimeMillis();
        ordenado.insercao_binaria(0, -1);
        tfim = (int) System.currentTimeMillis();
        com = ordenado.getComp();
        mov = ordenado.getMov();
        escreveTabela("|Binary Insertion    |", com, 0, mov, 3 * (n - 1) , tfim - tini);
        
        //Arquivo Reverso
        auxreverso.copiaArquivo(reverso.getFile());
        auxreverso.initComp();
        auxreverso.initMov();
        tini = (int) System.currentTimeMillis();
        auxreverso.insercao_binaria(0, -1);
        tfim = (int) System.currentTimeMillis();
        com = auxreverso.getComp();
        mov = auxreverso.getMov();
        escreveTabela("", com, (Math.pow(n, 2)- n) / 2, mov, (Math.pow(n, 2) + 3 * n - 4) / 2 , tfim - tini);
        
        //Arquivo Randômico
        auxrandomico.copiaArquivo(randomico.getFile());
        auxrandomico.initComp();
        auxrandomico.initMov();
        tini = (int) System.currentTimeMillis();
        auxrandomico.insercao_binaria(0, -1);
        tfim = (int) System.currentTimeMillis();
        com = auxrandomico.getComp();
        mov = auxrandomico.getMov();
        escreveTabela("", com, n * (Math.log(n)), mov, (Math.pow(n, 2) + 9 * n - 10) / 4 , tfim - tini);
        tabela.getFile().writeBytes("\n");
    }
    
    private void selectionSort() throws IOException
    {
        //SELEÇÃO DIRETA
        //Arquivo Odernado
        ordenado.initComp();
        ordenado.initMov();
        tini = (int) System.currentTimeMillis();
        ordenado.selecao_direta();
        tfim = (int) System.currentTimeMillis();
        com = ordenado.getComp();
        mov = ordenado.getMov();
        escreveTabela("|Select Sort\t     |", com, (Math.pow(n, 2)- n) / 2, mov, 3 * (n - 1) , tfim - tini);
        
        //Arquivo Reverso
        auxreverso.copiaArquivo(reverso.getFile());
        auxreverso.initComp();
        auxreverso.initMov();
        tini = (int) System.currentTimeMillis();
        auxreverso.selecao_direta();
        tfim = (int) System.currentTimeMillis();
        com = auxreverso.getComp();
        mov = auxreverso.getMov();
        escreveTabela("", com, (Math.pow(n, 2)- n) / 2, mov, Math.pow(n, 2) / 4 + (3 * (n - 1)) , tfim - tini);
        
        //Arquivo Randômico
        auxrandomico.copiaArquivo(randomico.getFile());
        auxrandomico.initComp();
        auxrandomico.initMov();
        tini = (int) System.currentTimeMillis();
        auxrandomico.selecao_direta();
        tfim = (int) System.currentTimeMillis();
        com = auxrandomico.getComp();
        mov = auxrandomico.getMov();
        escreveTabela("", com, (Math.pow(n, 2)- n) / 2, mov, n * (Math.log((double) n) + 0.577216f), tfim - tini);
        tabela.getFile().writeBytes("\n");
    }
    
    private void bubbleSort() throws IOException
    {
        //BOLHA
        //Arquivo Odernado
        ordenado.initComp();
        ordenado.initMov();
        tini = (int) System.currentTimeMillis();
        ordenado.bolha();
        tfim = (int) System.currentTimeMillis();
        com = ordenado.getComp();
        mov = ordenado.getMov();
        escreveTabela("|Bubble Sort\t     |", com, (Math.pow(n, 2)- n) / 2, mov, 0, tfim - tini);
        
        //Arquivo Reverso
        auxreverso.copiaArquivo(reverso.getFile());
        auxreverso.initComp();
        auxreverso.initMov();
        tini = (int) System.currentTimeMillis();
        auxreverso.bolha();
        tfim = (int) System.currentTimeMillis();
        com = auxreverso.getComp();
        mov = auxreverso.getMov();
        escreveTabela("", com, (Math.pow(n, 2)- n) / 2, mov, Math.pow(n, 2) / (4 + 3 * (n - 1)) , tfim - tini);
        
        //Arquivo Randômico
        auxrandomico.copiaArquivo(randomico.getFile());
        auxrandomico.initComp();
        auxrandomico.initMov();
        tini = (int) System.currentTimeMillis();
        auxrandomico.bolha();
        tfim = (int) System.currentTimeMillis();
        com = auxrandomico.getComp();
        mov = auxrandomico.getMov();
        escreveTabela("", com, (Math.pow(n, 2)- n) / 2, mov, n * (Math.log((double) n) + 0.577216f) , tfim - tini);
        tabela.getFile().writeBytes("\n");
    }
    
    private void shakeSort() throws IOException
    {
        //SHELL
        //Arquivo Odernado
        ordenado.initComp();
        ordenado.initMov();
        tini = (int) System.currentTimeMillis();
        ordenado.shake();
        tfim = (int) System.currentTimeMillis();
        com = ordenado.getComp();
        mov = ordenado.getMov();
        escreveTabela("|Shake Sort\t     |", com, (Math.pow(n, 2) - n) / 2, mov, 0 , tfim - tini);
        
        //Arquivo Reverso
        auxreverso.copiaArquivo(reverso.getFile());
        auxreverso.initComp();
        auxreverso.initMov();
        tini = (int) System.currentTimeMillis();
        auxreverso.shake();
        tfim = (int) System.currentTimeMillis();
        com = auxreverso.getComp();
        mov = auxreverso.getMov();
        escreveTabela("", com, (Math.pow(n, 2) - n) / 2, mov, 3 * (Math.pow(n, 2) - n) / 4, tfim - tini);
        
        //Arquivo Randômico
        auxrandomico.copiaArquivo(randomico.getFile());
        auxrandomico.initComp();
        auxrandomico.initMov();
        tini = (int) System.currentTimeMillis();
        auxrandomico.shake();
        tfim = (int) System.currentTimeMillis();
        com = auxrandomico.getComp();
        mov = auxrandomico.getMov();
        escreveTabela("", com, (Math.pow(n, 2) - n) / 2, mov, 3 * (Math.pow(n, 2) - n) / 2, tfim - tini);
         tabela.getFile().writeBytes("\n");
    }
    
    private void shellSort() throws IOException
    {
        //SHELL
        //Arquivo Odernado
        ordenado.initComp();
        ordenado.initMov();
        tini = (int) System.currentTimeMillis();
        ordenado.shell();
        tfim = (int) System.currentTimeMillis();
        com = ordenado.getComp();
        mov = ordenado.getMov();
        escreveTabela("|Shell Sort\t     |", com, -1, mov, -1, tfim - tini);
        
        //Arquivo Reverso
        auxreverso.copiaArquivo(reverso.getFile());
        auxreverso.initComp();
        auxreverso.initMov();
        tini = (int) System.currentTimeMillis();
        auxreverso.shell();
        tfim = (int) System.currentTimeMillis();
        com = auxreverso.getComp();
        mov = auxreverso.getMov();
        escreveTabela("", com, -1, mov, -1, tfim - tini);
        
        //Arquivo Randômico
        auxrandomico.copiaArquivo(randomico.getFile());
        auxrandomico.initComp();
        auxrandomico.initMov();
        tini = (int) System.currentTimeMillis();
        auxrandomico.shell();
        tfim = (int) System.currentTimeMillis();
        com = auxrandomico.getComp();
        mov = auxrandomico.getMov();
        escreveTabela("", com, -1, mov, -1, tfim - tini);
         tabela.getFile().writeBytes("\n");
    }
    
    private void heapSort() throws IOException
    {
        //HEAP
        //Arquivo Odernado
        ordenado.initComp();
        ordenado.initMov();
        tini = (int) System.currentTimeMillis();
        ordenado.heap();
        tfim = (int) System.currentTimeMillis();
        com = ordenado.getComp();
        mov = ordenado.getMov();
        escreveTabela("|Heap Sort\t     |", com, -1, mov, -1, tfim - tini);
        
        //Arquivo Reverso
        auxreverso.copiaArquivo(reverso.getFile());
        auxreverso.initComp();
        auxreverso.initMov();
        tini = (int) System.currentTimeMillis();
        auxreverso.heap();
        tfim = (int) System.currentTimeMillis();
        com = auxreverso.getComp();
        mov = auxreverso.getMov();
        escreveTabela("", com, -1, mov, -1, tfim - tini);
        
        //Arquivo Randômico
        auxrandomico.copiaArquivo(randomico.getFile());
        auxrandomico.initComp();
        auxrandomico.initMov();
        tini = (int) System.currentTimeMillis();
        auxrandomico.heap();
        tfim = (int) System.currentTimeMillis();
        com = auxrandomico.getComp();
        mov = auxrandomico.getMov();
        escreveTabela("", com, -1, mov, -1, tfim - tini);
         tabela.getFile().writeBytes("\n");
    }
        
    private void quickSortI() throws IOException
    {
        //QUICK SEM PIVO
        //Arquivo Odernado
        ordenado.initComp();
        ordenado.initMov();
        tini = (int) System.currentTimeMillis();
        ordenado.quick1();
        tfim = (int) System.currentTimeMillis();
        com = ordenado.getComp();
        mov = ordenado.getMov();
        escreveTabela("|Quick Sort 1\t     |", com, -1, mov, -1, tfim - tini);
        
        //Arquivo Reverso
        auxreverso.copiaArquivo(reverso.getFile());
        auxreverso.initComp();
        auxreverso.initMov();
        tini = (int) System.currentTimeMillis();
        auxreverso.quick1();
        tfim = (int) System.currentTimeMillis();
        com = auxreverso.getComp();
        mov = auxreverso.getMov();
        escreveTabela("", com, -1, mov, -1, tfim - tini);
        
        //Arquivo Randômico
        auxrandomico.copiaArquivo(randomico.getFile());
        auxrandomico.initComp();
        auxrandomico.initMov();
        tini = (int) System.currentTimeMillis();
        auxrandomico.quick1();
        tfim = (int) System.currentTimeMillis();
        com = auxrandomico.getComp();
        mov = auxrandomico.getMov();
        escreveTabela("", com, -1, mov, -1, tfim - tini);
         tabela.getFile().writeBytes("\n");
    }
    
    private void quickSortII() throws IOException
    {
        //QUICK COM PIVO
        //Arquivo Odernado
        ordenado.initComp();
        ordenado.initMov();
        tini = (int) System.currentTimeMillis();
        ordenado.quick2();
        tfim = (int) System.currentTimeMillis();
        com = ordenado.getComp();
        mov = ordenado.getMov();
        escreveTabela("|Quick Sort 2\t     |", com, -1, mov, -1, tfim - tini);
        
        //Arquivo Reverso
        auxreverso.copiaArquivo(reverso.getFile());
        auxreverso.initComp();
        auxreverso.initMov();
        tini = (int) System.currentTimeMillis();
        auxreverso.quick2();
        tfim = (int) System.currentTimeMillis();
        com = auxreverso.getComp();
        mov = auxreverso.getMov();
        escreveTabela("", com, -1, mov, -1, tfim - tini);
        
        //Arquivo Randômico
        auxrandomico.copiaArquivo(randomico.getFile());
        auxrandomico.initComp();
        auxrandomico.initMov();
        tini = (int) System.currentTimeMillis();
        auxrandomico.quick2();
        tfim = (int) System.currentTimeMillis();
        com = auxrandomico.getComp();
        mov = auxrandomico.getMov();
        escreveTabela("", com, (Math.pow(n, 2)- n) / 2, mov, n * (Math.log((double) n) + 0.577216f) , tfim - tini);
         tabela.getFile().writeBytes("\n");
    }
    
    private void mergeI() throws IOException
    {
        //MERGE 1ª Implementação
        //Arquivo Odernado
        ordenado.initComp();
        ordenado.initMov();
        tini = (int) System.currentTimeMillis();
        ordenado.merge1();
        tfim = (int) System.currentTimeMillis();
        com = ordenado.getComp();
        mov = ordenado.getMov();
        escreveTabela("|Merge 1\t     |", com, -1, mov, -1, tfim - tini);
        
        //Arquivo Reverso
        auxreverso.copiaArquivo(reverso.getFile());
        auxreverso.initComp();
        auxreverso.initMov();
        tini = (int) System.currentTimeMillis();
        auxreverso.merge1();
        tfim = (int) System.currentTimeMillis();
        com = auxreverso.getComp();
        mov = auxreverso.getMov();
        escreveTabela("", com, -1, mov, -1, tfim - tini);
        
        //Arquivo Randômico
        auxrandomico.copiaArquivo(randomico.getFile());
        auxrandomico.initComp();
        auxrandomico.initMov();
        tini = (int) System.currentTimeMillis();
        auxrandomico.merge1();
        tfim = (int) System.currentTimeMillis();
        com = auxrandomico.getComp();
        mov = auxrandomico.getMov();
        escreveTabela("", com, -1, mov, -1, tfim - tini);
         tabela.getFile().writeBytes("\n");

    }
    
    private void mergeII() throws IOException
    {
        //MERGE 2ª Implementação
        //Arquivo Odernado
        ordenado.initComp();
        ordenado.initMov();
        tini = (int) System.currentTimeMillis();
        ordenado.merge2();
        tfim = (int) System.currentTimeMillis();
        com = ordenado.getComp();
        mov = ordenado.getMov();
        escreveTabela("|Merge 2\t     |", com, -1, mov, -1, tfim - tini);
        
        //Arquivo Reverso
        auxreverso.copiaArquivo(reverso.getFile());
        auxreverso.initComp();
        auxreverso.initMov();
        tini = (int) System.currentTimeMillis();
        auxreverso.merge2();
        tfim = (int) System.currentTimeMillis();
        com = auxreverso.getComp();
        mov = auxreverso.getMov();
        escreveTabela("", com, -1, mov, -1, tfim - tini);
        
        //Arquivo Randômico
        auxrandomico.copiaArquivo(randomico.getFile());
        auxrandomico.initComp();
        auxrandomico.initMov();
        tini = (int) System.currentTimeMillis();
        auxrandomico.merge2();
        tfim = (int) System.currentTimeMillis();
        com = auxrandomico.getComp();
        mov = auxrandomico.getMov();
        escreveTabela("", com, -1, mov, -1, tfim - tini);
         tabela.getFile().writeBytes("\n");

    }
    
    private void counting() throws IOException
    {
        //Counting
        //Arquivo Odernado
        ordenado.initComp();
        ordenado.initMov();
        tini = (int) System.currentTimeMillis();
        ordenado.countingSort();
        tfim = (int) System.currentTimeMillis();
        com = ordenado.getComp();
        mov = ordenado.getMov();
        escreveTabela("|CountingSort\t     |", com, -1, mov, -1, tfim - tini);
        
        //Arquivo Reverso
        auxreverso.copiaArquivo(reverso.getFile());
        auxreverso.initComp();
        auxreverso.initMov();
        tini = (int) System.currentTimeMillis();
        auxreverso.countingSort();
        tfim = (int) System.currentTimeMillis();
        com = auxreverso.getComp();
        mov = auxreverso.getMov();
        escreveTabela("", com, -1, mov, -1, tfim - tini);
        
        //Arquivo Randômico
        auxrandomico.copiaArquivo(randomico.getFile());
        auxrandomico.initComp();
        auxrandomico.initMov();
        tini = (int) System.currentTimeMillis();
        auxrandomico.countingSort();
        tfim = (int) System.currentTimeMillis();
        com = auxrandomico.getComp();
        mov = auxrandomico.getMov();
        escreveTabela("", com, -1, mov, -1, tfim - tini);
         tabela.getFile().writeBytes("\n");
    }
    
    private void bucket() throws IOException
    {
        //Bucket
        //Arquivo Odernado
        ordenado.initComp();
        ordenado.initMov();
        tini = (int) System.currentTimeMillis();
        ordenado.bucket();
        tfim = (int) System.currentTimeMillis();
        com = ordenado.getComp();
        mov = ordenado.getMov();
        escreveTabela("|Gnome Sort\t     |", com, -1, mov, -1, tfim - tini);
        
        //Arquivo Reverso
        auxreverso.copiaArquivo(reverso.getFile());
        auxreverso.initComp();
        auxreverso.initMov();
        tini = (int) System.currentTimeMillis();
        auxreverso.bucket();
        tfim = (int) System.currentTimeMillis();
        com = auxreverso.getComp();
        mov = auxreverso.getMov();
        escreveTabela("", com, -1, mov, -1, tfim - tini);
        
        //Arquivo Randômico
        auxrandomico.copiaArquivo(randomico.getFile());
        auxrandomico.initComp();
        auxrandomico.initMov();
        tini = (int) System.currentTimeMillis();
        auxrandomico.bucket();
        tfim = (int) System.currentTimeMillis();
        com = auxrandomico.getComp();
        mov = auxrandomico.getMov();
        escreveTabela("", com, -1, mov, -1, tfim - tini);
         tabela.getFile().writeBytes("\n");
    }
   
    private void radix() throws IOException
    {
        //Radix
        //Arquivo Odernado
        ordenado.initComp();
        ordenado.initMov();
        tini = (int) System.currentTimeMillis();
        ordenado.radix();
        tfim = (int) System.currentTimeMillis();
        com = ordenado.getComp();
        mov = ordenado.getMov();
        escreveTabela("|Radix\t\t     |", com, -1, mov, -1, tfim - tini);
        
        //Arquivo Reverso
        auxreverso.copiaArquivo(reverso.getFile());
        auxreverso.initComp();
        auxreverso.initMov();
        tini = (int) System.currentTimeMillis();
        auxreverso.radix();
        tfim = (int) System.currentTimeMillis();
        com = auxreverso.getComp();
        mov = auxreverso.getMov();
        escreveTabela("", com, -1, mov, -1, tfim - tini);
        
        //Arquivo Randômico
        auxrandomico.copiaArquivo(randomico.getFile());
        auxrandomico.initComp();
        auxrandomico.initMov();
        tini = (int) System.currentTimeMillis();
        auxrandomico.radix();
        tfim = (int) System.currentTimeMillis();
        com = auxrandomico.getComp();
        mov = auxrandomico.getMov();
        escreveTabela("", com, -1, mov, -1, tfim - tini);
         tabela.getFile().writeBytes("\n");
    }
    
    private void comb() throws IOException
    {
        //Comb
        //Arquivo Odernado
        ordenado.initComp();
        ordenado.initMov();
        tini = (int) System.currentTimeMillis();
        ordenado.comb();
        tfim = (int) System.currentTimeMillis();
        com = ordenado.getComp();
        mov = ordenado.getMov();
        escreveTabela("|Comb\t\t     |", com, -1, mov, -1, tfim - tini);
        
        //Arquivo Reverso
        auxreverso.copiaArquivo(reverso.getFile());
        auxreverso.initComp();
        auxreverso.initMov();
        tini = (int) System.currentTimeMillis();
        auxreverso.comb();
        tfim = (int) System.currentTimeMillis();
        com = auxreverso.getComp();
        mov = auxreverso.getMov();
        escreveTabela("", com, -1, mov, -1, tfim - tini);
        
        //Arquivo Randômico
        auxrandomico.copiaArquivo(randomico.getFile());
        auxrandomico.initComp();
        auxrandomico.initMov();
        tini = (int) System.currentTimeMillis();
        auxrandomico.comb();
        tfim = (int) System.currentTimeMillis();
        com = auxrandomico.getComp();
        mov = auxrandomico.getMov();
        escreveTabela("", com, -1, mov, -1, tfim - tini);
        tabela.getFile().writeBytes("\n");
    }
    
    private void gnome() throws IOException
    {
        //Gnome
        //Arquivo Odernado
        ordenado.initComp();
        ordenado.initMov();
        tini = (int) System.currentTimeMillis();
        ordenado.gnome();
        tfim = (int) System.currentTimeMillis();
        com = ordenado.getComp();
        mov = ordenado.getMov();
        escreveTabela("|Gnome Sort\t     |", com, -1, mov, -1, tfim - tini);
        
        //Arquivo Reverso
        auxreverso.copiaArquivo(reverso.getFile());
        auxreverso.initComp();
        auxreverso.initMov();
        tini = (int) System.currentTimeMillis();
        auxreverso.gnome();
        tfim = (int) System.currentTimeMillis();
        com = auxreverso.getComp();
        mov = auxreverso.getMov();
        escreveTabela("", com, -1, mov, -1, tfim - tini);
        
        //Arquivo Randômico
        auxrandomico.copiaArquivo(randomico.getFile());
        auxrandomico.initComp();
        auxrandomico.initMov();
        tini = (int) System.currentTimeMillis();
        auxrandomico.gnome();
        tfim = (int) System.currentTimeMillis();
        com = auxrandomico.getComp();
        mov = auxrandomico.getMov();
        escreveTabela("", com, -1, mov, -1, tfim - tini);
        tabela.getFile().writeBytes("\n");
    }
    
    private void tim() throws IOException
    {
        //Tim
        //Arquivo Odernado
        ordenado.initComp();
        ordenado.initMov();
        tini = (int) System.currentTimeMillis();
        ordenado.tim();
        tfim = (int) System.currentTimeMillis();
        com = ordenado.getComp();
        mov = ordenado.getMov();
        escreveTabela("|Tim Sort\t     |", com, -1, mov, -1, tfim - tini);
        
        //Arquivo Reverso
        auxreverso.copiaArquivo(reverso.getFile());
        auxreverso.initComp();
        auxreverso.initMov();
        tini = (int) System.currentTimeMillis();
        auxreverso.tim();
        tfim = (int) System.currentTimeMillis();
        com = auxreverso.getComp();
        mov = auxreverso.getMov();
        escreveTabela("", com, -1, mov, -1, tfim - tini);
        
//        Arquivo Randômico
        auxrandomico.copiaArquivo(randomico.getFile());
        auxrandomico.initComp();
        auxrandomico.initMov();
        tini = (int) System.currentTimeMillis();
        auxrandomico.tim();
        tfim = (int) System.currentTimeMillis();
        com = auxrandomico.getComp();
        mov = auxrandomico.getMov();
        escreveTabela("", com, -1, mov, -1, tfim - tini);
        tabela.getFile().writeBytes("\n");
    }
    
    private void initLista()
    {
        lauxrandomica = new Lista();
        lauxreversa = new Lista();
        lordenada = new Lista();
        lreversa = new Lista();
        lrandomica = new Lista();
    }
    
    public void geraLista()
    {
        initLista();
        lordenada.geraOrdenada();
        lreversa.geraReversa();
        lrandomica.geraRandomica();
        
        lrandomica.exibeLista(); System.out.println("");
        lrandomica.quickSortS();
        lrandomica.exibeLista();
    }
    
    public void gerarTabela() throws IOException
    {
        initArquivos();
        escreveCabecalho();
        ordenado.geraArquivoOrdenado();
        reverso.geraArquivoReverso();
        randomico.geraArquivoRandomico();
        
        System.out.println(LocalTime.now());
        
        System.out.println("Insetion...");
        insertionSort();System.out.println("Binario...");
        binaryInsertion();System.out.println("Selection...");
        selectionSort(); System.out.println("Bubble...");
        bubbleSort();System.out.println("Shake...");
        shakeSort();System.out.println("Shell...");
        shellSort();System.out.println("Heap...");
        heapSort();System.out.println("Quick1...");
        quickSortI();System.out.println("Quick2...");
        quickSortII();System.out.println("Merge1...");
        mergeI();System.out.println("Merge2...");
        mergeII();System.out.println("Counting...");
        counting();System.out.println("Bucket...");
        bucket();System.out.println("Radix...");
        radix();System.out.println("Comb...");
        comb();System.out.println("Gnome...");
        gnome();System.out.println("Tim...");
//        tim();

        ordenado.exibirArq();
        System.out.println(LocalTime.now());
    }
    
    public static void main(String[] args) throws IOException
    {
        Main m = new Main();
        m.gerarTabela();
        //m.geraLista();
    }
}
