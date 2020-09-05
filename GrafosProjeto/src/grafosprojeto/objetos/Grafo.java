package grafosprojeto.objetos;

import java.util.ArrayList;
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
    }
    
    public Grafo() {
        this.status = "";
        vlist = new ArrayList<>();
        alist = new ArrayList<>();
        ma = new int [10][10];
        mi = new ArrayList<>();
        la = new ArrayList<>();
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
    }
    
    public void addMa(int x,int y,int info)
    {
        ma[x][y] = info;
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