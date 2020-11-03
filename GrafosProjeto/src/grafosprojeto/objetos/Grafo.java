package grafosprojeto.objetos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Grafo {
    
    private List<Vertice> vlist ;
    private List<Aresta> alist;
    private String tipo, status;
    private boolean dir, val;
    private boolean [] gstatus;
    private int ma [][];
    private List<ArrayList> mi;
    private List<ArrayList<Vertice>> la;
    private List<Vertice> lbfsc;
    private int [][] matcor;
    private int [][] mfloyd;
    private char [][] msfloyd;

    public Grafo(String tipo, boolean dir) {
        this.tipo = tipo;
        this.dir = dir;
        ma = new int [10][10];
        mfloyd = new int [10][10];
        for (int i = 0; i < 10; i++) 
        {
            for (int j = 0; j < 10; j++) 
            {
                if(i != j)
                    mfloyd[i][j] = 99999999;
            }
        }
        msfloyd = new char [10][10];
        mi = new ArrayList<>();
        la = new ArrayList<>();
        initLa();
        gstatus = new boolean[4];
    }
    
    public Grafo() {
        this.status = "";
        vlist = new ArrayList<>();
        alist = new ArrayList<>();
        ma = new int [10][10];
        mfloyd = new int [10][10];
        msfloyd = new char [10][10];
        for (int i = 0; i < 10; i++) 
        {
            for (int j = 0; j < 10; j++) 
            {
                if(i != j)
                    mfloyd[i][j] = 99999999;
                msfloyd[i][j] = ' ';
            }
        }
        mi = new ArrayList<>();
        la = new ArrayList<>();
        initLa();
        gstatus = new boolean[4];
    }

    public Grafo(boolean dir, boolean val) {
        this.val = val;
        this.dir = dir;
        this.status = "";
        vlist = new ArrayList<>();
        alist = new ArrayList<>();
        ma = new int [10][10];
        mfloyd = new int [10][10];
        msfloyd = new char [10][10];
        for (int i = 0; i < 10; i++) 
        {
            for (int j = 0; j < 10; j++) 
            {
                if(i != j)
                    mfloyd[i][j] = 99999999;
                msfloyd[i][j] = ' ';
            }
        }
        mi = new ArrayList<>();
        la = new ArrayList<>();
        initLa();
        gstatus = new boolean[4];
    }
    
    public void atualizaCor() 
    {
        lbfsc = new ArrayList<>();
        matcor = new int[10][10];
        
        getBFSByGrau();
        
        for (int i = 0; i < lbfsc.size(); i++) 
        {
            int j = 0;
            while(j < 10 && matcor[lbfsc.get(i).getID()-65][j] == -1) 
                j++;
            matcor[lbfsc.get(i).getID()-65][j] = j+1;
            for (int k = 0; k < la.get(lbfsc.get(i).getID()-65).size(); k++) {
                if(la.get(lbfsc.get(i).getID()-65).get(k) != lbfsc.get(i))
                    matcor[la.get(lbfsc.get(i).getID()-65).get(k).getID()-65][j] = -1;
            }
        }
        
        pintaCor();
    }
    
    private void pintaCor()
    {
        for (int i = 0; i < vlist.size(); i++) 
        {
            int j = 0;
            while(j < 10 && matcor[vlist.get(i).getID()-65][j] == -1)
                j++;
            
            switch(matcor[vlist.get(i).getID()-65][j])
            {
                case 1:
                    vlist.get(i).getBola().setFill(Paint.valueOf("0x00FFFF"));
                break;
                case 2:
                    vlist.get(i).getBola().setFill(Color.CORAL);
                break;
                case 3:
                    vlist.get(i).getBola().setFill(Color.CORNFLOWERBLUE);
                break;
                case 4:
                    vlist.get(i).getBola().setFill(Color.BURLYWOOD);
                break;
                case 5:
                    vlist.get(i).getBola().setFill(Color.YELLOWGREEN);
                break;
                case 6:
                    vlist.get(i).getBola().setFill(Color.WHITESMOKE);
                break;
                case 7:
                    vlist.get(i).getBola().setFill(Color.TURQUOISE);
                break;
                case 8:
                    vlist.get(i).getBola().setFill(Color.THISTLE);
                break;
                case 9:
                    vlist.get(i).getBola().setFill(Color.SIENNA);
                break;
                case 10:
                    vlist.get(i).getBola().setFill(Color.SLATEGREY);
                break;
            }
        }
    }
    
    private void getBFSByGrau()
    {
        List ver = new ArrayList();
        while(lbfsc.size() < vlist.size())
        {
            Vertice v = getMaiorGrau(vlist,ver);
            lbfsc.add(v);
            BFS(v,ver);
        }
    }
    
    private void BFS(Vertice v, List ver)
    {
        ver.add(v);
        
        Vertice aux;
        for (int i = 0; i < la.get(v.getID()-65).size(); i++) 
        {
            aux = getMaiorGrau(la.get(v.getID()-65), lbfsc);
            if(aux.getGrauE() != -1)
                lbfsc.add(aux);
        }
        for (int i = 0; i < la.get(v.getID()-65).size(); i++) 
        {
            Vertice a1 = getMaiorGrau(la.get(v.getID()-65), ver);
            if(a1.getGrauE() != -1)
                BFS(a1,ver);
        }
    }
    
    private Vertice getMaiorGrau(List<Vertice> l, List ver)
    {
        Vertice v = new Vertice();
        v.setGrauE(-1);
        Vertice v1 = null;
        for (int i = 0; i < l.size(); i++) 
        {
            v1 = l.get(i);
            if(v1.getGrauE() > v.getGrauE() && !ver.contains(v1))
                v = v1;
        }
        
        return v;
    }

    public List<Vertice> getLbfsc() {
        return lbfsc;
    }

    public void setLbfsc(List<Vertice> lbfsc) {
        this.lbfsc = lbfsc;
    }

    public int[][] getMatcor() {
        return matcor;
    }

    public void setMatcor(int[][] matcor) {
        this.matcor = matcor;
    }
    
    public void initLa()
    {
        for (int i = 0; i < 10; i++) 
            la.add(new ArrayList<Vertice>());
    }
    
    public boolean isSimples()
    {
        return gstatus[0];
    }
    
    public boolean isRegular()
    {
        return gstatus[1];
    }
    
    public boolean isCompleto()
    {
        return gstatus[2];
    }
    
    public boolean isMultigrafo()
    {
        return gstatus[3];
    }
    
    public void setSimples(boolean b)
    {
        gstatus[0] = b;
    }
    
    public void setRegular(boolean b)
    {
        gstatus[1] = b;
    }
    
    public void setCompleto(boolean b)
    {
        gstatus[2] = b;
    }
    
    public void setMultigrafo(boolean b)
    {
        gstatus[3] = b;
    }
    
    public void addLa(Vertice v,int id)
    {
        la.get(id).add(v);
        la.get(id).sort(Comparator.comparing(Vertice::getID));
    }

    public List<ArrayList<Vertice>> getLa() {
        return la;
    }

    public void setLa(List<ArrayList<Vertice>> la) {
        this.la = la;
    }
    
    public void addMa(int x,int y,int info)
    {
        ma[x][y] = info;
        mfloyd[x][y] = info;
    }

    public int[][] getMa() {
        return ma;
    }
    
    public void addMi(int v, int valor)
    {
        if(mi.get(alist.size()).size() < 8)
            for (int i = 0; i < 10; i++) 
                mi.get(alist.size()).add(0);
        mi.get(alist.size()).set(v, valor);
    }

    public List<ArrayList> getMi() {
        return mi;
    }

    public void setMi(List<ArrayList> mi) {
        this.mi = mi;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isVal() {
        return val;
    }

    public void setVal(boolean val) {
        this.val = val;
    }
    
    public List<Vertice> getVlist() {
        return vlist;
    }

    public void setVlist(List<Vertice> vlist) {
        this.vlist = vlist;
    }

    public List<Aresta> getAlist() {
        return alist;
    }

    public void setAlist(List<Aresta> alist) {
        this.alist = alist;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isDir() {
        return dir;
    }

    public void setDir(boolean dir) {
        this.dir = dir;
    }

    public int[][] getMfloyd() {
        return mfloyd;
    }

    public void setMfloyd(int[][] mfloyd) {
        this.mfloyd = mfloyd;
    }

    public char[][] getMsfloyd() {
        return msfloyd;
    }

    public void setMsfloyd(char[][] msfloyd) {
        this.msfloyd = msfloyd;
    }
    
    public void cheapestPath()
    {
        for (int i = 0; i < 10; i++) 
        {
            for (int j = 0; j < 10; j++) 
            {
                for (int k = 0; k < 10; k++) 
                {
                    if(ma[j][k] > mfloyd[j][i] + mfloyd[i][k])
                    {
                        mfloyd[j][k] = mfloyd[j][i] + mfloyd[i][k];
                        msfloyd[j][k] = (char)(i + 65);
                    }
                }
            }
        }
    }
}