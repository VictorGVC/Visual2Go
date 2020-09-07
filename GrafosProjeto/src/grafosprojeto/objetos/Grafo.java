package grafosprojeto.objetos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Grafo {
    
    private List<Vertice> vlist ;
    private List<Aresta> alist;
    private String tipo, status;
    private boolean dir, val;
    private int ma [][];
    private List<ArrayList> mi;
    private List<ArrayList> la;

    public Grafo(String tipo, boolean dir) {
        this.tipo = tipo;
        this.dir = dir;
        ma = new int [10][10];
        mi = new ArrayList<>();
        la = new ArrayList<>();
        initLa();
    }
    
    public Grafo() {
        this.status = "";
        vlist = new ArrayList<>();
        alist = new ArrayList<>();
        ma = new int [10][10];
        mi = new ArrayList<>();
        la = new ArrayList<>();
        initLa();
    }

    public Grafo(boolean dir, boolean val) {
        this.val = val;
        this.dir = dir;
        this.status = "";
        vlist = new ArrayList<>();
        alist = new ArrayList<>();
        ma = new int [10][10];
        mi = new ArrayList<>();
        la = new ArrayList<>();
        initLa();
    }
    
    public void initLa()
    {
        for (int i = 0; i < 10; i++) 
            la.add(new ArrayList());
    }
    
    public void addLa(Vertice v,int id)
    {
        la.get(id).add(v);
        la.get(id).sort(Comparator.comparing(Vertice::getID));
    }

    public List<ArrayList> getLa() {
        return la;
    }

    public void setLa(List<ArrayList> la) {
        this.la = la;
    }
    
    public void addMa(int x,int y,int info)
    {
        ma[x][y] = info;
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
}