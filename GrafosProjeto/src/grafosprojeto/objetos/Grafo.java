/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafosprojeto.objetos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vicga
 */
public class Grafo {
    private List <Vertice>vlist ;
    private List <Aresta>alist;
    private String tipo,status;
    private boolean dir,val;

    public Grafo() {
        this.status = "";
        vlist = new ArrayList<>();
        alist = new ArrayList<>();
    }

    public Grafo(boolean dir) {
        this.dir = dir;
        this.status = "";
        vlist = new ArrayList<>();
        alist = new ArrayList<>();
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

    public Grafo(String tipo, boolean dir) {
        this.tipo = tipo;
        this.dir = dir;
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
