/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author jsanchez
 */
public class EmpresaVO {
    private int nitempresa;
    private String Tipoempresa;

    public int getNitempresa() {
        return nitempresa;
    }

    public void setNitempresa(int nitempresa) {
        this.nitempresa = nitempresa;
    }

    public String getTipoempresa() {
        return Tipoempresa;
    }

    public void setTipoempresa(String Tipoempresa) {
        this.Tipoempresa = Tipoempresa;
    }
    
       
    public EmpresaVO(int nitemp, String tipoemp){
        this.nitempresa = nitemp;
        this.Tipoempresa = tipoemp;
    }

    public String toString(){
        return this.Tipoempresa;
    }
    
}
