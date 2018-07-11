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
public class TipoEstadoContractualVO {
    private int idestadolaboral;
    private String estadolaboral;

     public TipoEstadoContractualVO(int idestadol, String estadol){
        this.idestadolaboral = idestadol;
        this.estadolaboral = estadol;
    }
    

    public int getIdestadolaboral() {
        return idestadolaboral;
    }

    public void setIdestadolaboral(int idestadolaboral) {
        this.idestadolaboral = idestadolaboral;
    }

    public String getEstadolaboral() {
        return estadolaboral;
    }

    public void setEstadolaboral(String estadolaboral) {
        this.estadolaboral = estadolaboral;
    }

   
    
     public String toString(){
        return this.estadolaboral;
    }
}
