/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafosprojeto.objetos;

import java.util.List;

/**
 *
 * @author vicga
 */
public class Grafo {
    List <Vertice>vlist;
    List <Aresta>alist;
    String tipo;
    boolean dir;

    public Grafo() {
    }

    public Grafo(boolean dir) {
        this.dir = dir;
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
