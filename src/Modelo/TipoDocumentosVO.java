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
public class TipoDocumentosVO {
    private int idtipodocumento;
    private String tipodocumento;
    
       
    public TipoDocumentosVO(int idtipodoc, String tipodoc){
        this.idtipodocumento = idtipodoc;
        this.tipodocumento = tipodoc;
    }

    /**
     * @return the idtipodocumento
     */
    public int getIdtipodocumento() {
        return idtipodocumento;
    }

    /**
     * @param idtipodocumento the idtipodocumento to set
     */
    public void setIdtipodocumento(int idtipodocumento) {
        this.idtipodocumento = idtipodocumento;
    }

    /**
     * @return the tipodocumento
     */
    public String getTipodocumento() {
        return tipodocumento;
    }

    /**
     * @param tipodocumento the tipodocumento to set
     */
    public void setTipodocumento(String tipodocumento) {
        this.tipodocumento = tipodocumento;
    }

    /**
     * @return the idtipodocumento
     */

    public String toString(){
        return this.tipodocumento;
    }
    
}
