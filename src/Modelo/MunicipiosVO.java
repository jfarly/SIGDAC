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
public class MunicipiosVO {
    private int idmunicipio;
    private int iddepartamento;
    private String municipio;
    
    
    public MunicipiosVO(){}
    
    public MunicipiosVO(int idmunic, String munic){
        this.idmunicipio = idmunic;
        this.municipio = munic;
        
    }

    /**
     * @return the idmunicipio
     */
    public int getIdmunicipio() {
        return idmunicipio;
    }

    /**
     * @param idmunicipio the idmunicipio to set
     */
    public void setIdmunicipio(int idmunicipio) {
        this.idmunicipio = idmunicipio;
    }

    /**
     * @return the iddepartamento
     */
    public int getIddepartamento() {
        return iddepartamento;
    }

    /**
     * @param iddepartamento the iddepartamento to set
     */
    public void setIddepartamento(int iddepartamento) {
        this.iddepartamento = iddepartamento;
    }

    /**
     * @return the municipio
     */
    public String getMunicipio() {
        return municipio;
    }

    /**
     * @param municipio the municipio to set
     */
    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }
    
    public String toString(){
        return this.municipio;
        
    }
}
