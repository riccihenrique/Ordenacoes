package arquivos;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Main 
{
    static final int n = 10;
    private Arquivo ordenado, reverso, randomico, auxreverso, auxrandomico;
    private FileWriter txt;
    private PrintWriter escritor;
    private int tini, tfim, mov, com;
    private Lista lordenada, lreversa, lrandomica, lauxrandomica, lauxreversa;    
    
    private void initArquivos()
    {
        this.ordenado = new Arquivo("Odenado.dat");
        this.reverso = new Arquivo("Reverso.dat");
        this.randomico = new Arquivo("Randomico.dat");
        this.auxrandomico = new Arquivo();
        this.auxreverso = new Arquivo();
        try
        {
           this.txt = new FileWriter("tabela.txt");
           this.escritor = new PrintWriter(txt);
        }
        catch(Exception e)
        {
            System.out.println("Erro ao criar arquivo TXT");
            System.exit(-1);
        }
    }
    
    private void escreveCabecalho()
    {
        escritor.write("|MÉTODOS DE ORDENAÇÃO|ARQUIVO ORDENADO\t\t\t|ARQUIVO EM ORDEM REVERSA\t     "
                + "|ARQUIVO RANDÔMICO\n");
        escritor.write("|\t\t     |Comp. 1|Comp. 2|Mov. 1|Mov. 2|Tempo|Comp. 1|Comp. 2|Mov. 1|Mov. 2|Tempo|"
                + "Comp. 1|Comp. 2|Mov. 1|Mov. 2|Tempo|\n");
    }
    
    private void escreveTabela(String nomeMetodo, int cp, double ce, int mp, double me, double tempo)
    {
        escritor.printf("%s %d  | %.0f | %d | %.0f  | %.0f ||", nomeMetodo, cp, ce, mp, me, tempo);
    }
    
    private void insertionSort()
    {
        //INSERÇÃO DIRETA
        //Arquivo Odernado
        ordenado.initComp();
        ordenado.initMov();
        tini = (int) System.currentTimeMillis();
        ordenado.insercao_direta();
        tfim = (int) System.currentTimeMillis();
        com = ordenado.getComp();
        mov = ordenado.getMov();
        escreveTabela("|Insertion Sort\t     |", com, n - 1, mov, 3 * (n - 1) , tfim - tini);
        
        //Arquivo Reverso
        auxreverso.copiaArquivo(reverso.getFile());
        auxreverso.initComp();
        auxreverso.initMov();
        tini = (int) System.currentTimeMillis();
        auxreverso.insercao_direta();
        tfim = (int) System.currentTimeMillis();
        com = auxreverso.getComp();
        mov = auxreverso.getMov();
        escreveTabela("", com, (Math.pow(n, 2) + n - 4) / 4, mov, (Math.pow(n, 2) + 3 * n - 4) / 2 , tfim - tini);
        
        //Arquivo Randômico
        auxrandomico.copiaArquivo(randomico.getFile());
        auxrandomico.initComp();
        auxrandomico.initMov();
        tini = (int) System.currentTimeMillis();
        auxrandomico.insercao_direta();
        tfim = (int) System.currentTimeMillis();
        com = auxrandomico.getComp();
        mov = auxrandomico.getMov();
        escreveTabela("", com, (Math.pow(n, 2) + n - 2) / 4, mov, (Math.pow(n, 2) + 9 * n - 10) / 4, tfim - tini);
        escritor.write("\n");
    }
    
    private void binaryInsertion()
    {
        //INSERÇÃO BINÁRIA
        //Arquivo Odernado
        ordenado.initComp();
        ordenado.initMov();
        tini = (int) System.currentTimeMillis();
        ordenado.insercao_binaria();
        tfim = (int) System.currentTimeMillis();
        com = ordenado.getComp();
        mov = ordenado.getMov();
        escreveTabela("|Binary Insertion    |", com, 0, mov, 3 * (n - 1) , tfim - tini);
        
        //Arquivo Reverso
        auxreverso.copiaArquivo(reverso.getFile());
        auxreverso.initComp();
        auxreverso.initMov();
        tini = (int) System.currentTimeMillis();
        auxreverso.insercao_binaria();
        tfim = (int) System.currentTimeMillis();
        com = auxreverso.getComp();
        mov = auxreverso.getMov();
        escreveTabela("", com, (Math.pow(n, 2)- n) / 2, mov, (Math.pow(n, 2) + 3 * n - 4) / 2 , tfim - tini);
        
        //Arquivo Randômico
        auxrandomico.copiaArquivo(randomico.getFile());
        auxrandomico.initComp();
        auxrandomico.initMov();
        tini = (int) System.currentTimeMillis();
        auxrandomico.insercao_binaria();
        tfim = (int) System.currentTimeMillis();
        com = auxrandomico.getComp();
        mov = auxrandomico.getMov();
        escreveTabela("", com, n * (Math.log(n)), mov, (Math.pow(n, 2) + 9 * n - 10) / 2 , tfim - tini);
        escritor.write("\n");
    }
    
    private void selectionSort()
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
        escritor.write("\n");
    }
    
    private void bubbleSort()
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
        escreveTabela("|Bubble Sort\t     |", com, 0, mov, 3 * (n - 1) , tfim - tini);
        
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
        escritor.write("\n");
    }
    
    private void shakeSort()
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
        escritor.write("\n");
    }
    
    private void shellSort()
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
        escreveTabela("|Shell Sort\t     |", com, (Math.pow(n, 2)- n) / 2, mov, 3 * (n - 1) , tfim - tini);
        
        //Arquivo Reverso
        auxreverso.copiaArquivo(reverso.getFile());
        auxreverso.initComp();
        auxreverso.initMov();
        tini = (int) System.currentTimeMillis();
        auxreverso.shell();
        tfim = (int) System.currentTimeMillis();
        com = auxreverso.getComp();
        mov = auxreverso.getMov();
        escreveTabela("", com, (Math.pow(n, 2)- n) / 2, mov, Math.pow(n, 2) / (4 + 3 * (n - 1)) , tfim - tini);
        
        //Arquivo Randômico
        auxrandomico.copiaArquivo(randomico.getFile());
        auxrandomico.initComp();
        auxrandomico.initMov();
        tini = (int) System.currentTimeMillis();
        auxrandomico.shell();
        tfim = (int) System.currentTimeMillis();
        com = auxrandomico.getComp();
        mov = auxrandomico.getMov();
        escreveTabela("", com, (Math.pow(n, 2)- n) / 2, mov, n * (Math.log((double) n) + 0.577216f) , tfim - tini);
        escritor.write("\n");
    }
    
    private void heapSort()
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
        escreveTabela("|Heap Sort\t     |", com, (Math.pow(n, 2)- n) / 2, mov, 3 * (n - 1) , tfim - tini);
        
        //Arquivo Reverso
        auxreverso.copiaArquivo(reverso.getFile());
        auxreverso.initComp();
        auxreverso.initMov();
        tini = (int) System.currentTimeMillis();
        auxreverso.heap();
        tfim = (int) System.currentTimeMillis();
        com = auxreverso.getComp();
        mov = auxreverso.getMov();
        escreveTabela("", com, (Math.pow(n, 2)- n) / 2, mov, Math.pow(n, 2) / (4 + 3 * (n - 1)) , tfim - tini);
        
        //Arquivo Randômico
        auxrandomico.copiaArquivo(randomico.getFile());
        auxrandomico.initComp();
        auxrandomico.initMov();
        tini = (int) System.currentTimeMillis();
        auxrandomico.heap();
        tfim = (int) System.currentTimeMillis();
        com = auxrandomico.getComp();
        mov = auxrandomico.getMov();
        escreveTabela("", com, (Math.pow(n, 2)- n) / 2, mov, n * (Math.log((double) n) + 0.577216f) , tfim - tini);
        escritor.write("\n");
    }
        
    private void quickSortI()
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
        escreveTabela("|Quick Sort 1\t     |", com, (Math.pow(n, 2)- n) / 2, mov, 3 * (n - 1) , tfim - tini);
        
        //Arquivo Reverso
        auxreverso.copiaArquivo(reverso.getFile());
        auxreverso.initComp();
        auxreverso.initMov();
        tini = (int) System.currentTimeMillis();
        auxreverso.quick1();
        tfim = (int) System.currentTimeMillis();
        com = auxreverso.getComp();
        mov = auxreverso.getMov();
        escreveTabela("", com, (Math.pow(n, 2)- n) / 2, mov, Math.pow(n, 2) / (4 + 3 * (n - 1)) , tfim - tini);
        
        //Arquivo Randômico
        auxrandomico.copiaArquivo(randomico.getFile());
        auxrandomico.initComp();
        auxrandomico.initMov();
        tini = (int) System.currentTimeMillis();
        auxrandomico.quick1();
        tfim = (int) System.currentTimeMillis();
        com = auxrandomico.getComp();
        mov = auxrandomico.getMov();
        escreveTabela("", com, (Math.pow(n, 2)- n) / 2, mov, n * (Math.log((double) n) + 0.577216f) , tfim - tini);
        escritor.write("\n");
    }
    
    private void quickSortII()
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
        escreveTabela("|Quick Sort 2\t     |", com, (Math.pow(n, 2)- n) / 2, mov, 3 * (n - 1) , tfim - tini);
        
        //Arquivo Reverso
        auxreverso.copiaArquivo(reverso.getFile());
        auxreverso.initComp();
        auxreverso.initMov();
        tini = (int) System.currentTimeMillis();
        auxreverso.quick2();
        tfim = (int) System.currentTimeMillis();
        com = auxreverso.getComp();
        mov = auxreverso.getMov();
        escreveTabela("", com, (Math.pow(n, 2)- n) / 2, mov, Math.pow(n, 2) / (4 + 3 * (n - 1)) , tfim - tini);
        
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
        escritor.write("\n"); 
    }
    
    private void mergeI()
    {
        
    }
    
    private void mergeII()
    {
        
    }
    
    private void counting()
    {
        
    }
    
    private void bucket()
    {
        
    }
   
    private void radix()
    {
        
    }
    
    private void comb()
    {
        
    }
    
    private void gnome()
    {
        
    }
    
    private void tim()
    {
        
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
        lrandomica.insercao_binaria();
        lrandomica.exibeLista();
    }
    
    public void gerarTabela() throws IOException
    {
        initArquivos();
        escreveCabecalho();
        ordenado.geraArquivoOrdenado();
        reverso.geraArquivoReverso();
        randomico.geraArquivoRandomico();
        
//        insertionSort(); //OK
//        binaryInsertion(); //OK
//        selectionSort(); //OK
//        bubbleSort(); //Ok - falta complexidade
//        shakeSort(); //Ok
//        shellSort();
//        heapSort(); //Ok - falta complexidade
//        quickSortI();
//        quickSortII();
//        mergeI();
//        mergeII();
//        counting();
//        bucket();
//        radix();
//        comb();
//        gnome();
//        tim();

        ordenado.exibirArq(); System.out.println("");
        auxreverso.exibirArq(); System.out.println("");
        auxrandomico.exibirArq(); 
        txt.close();
    }
    
    public static void main(String[] args) throws IOException
    {
        Main m = new Main();
        //m.gerarTabela();
        m.geraLista();
    }
}
