/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

/**
 *
 * @author jsanchez
 */
public class clsMunicipios {
    private Integer idMunicipio;
    private Integer idDepartamento;
    private String Municipio;
    
    public clsMunicipios (Integer idMunicipios, Integer idDepartamento, String Municipio){
        this.idMunicipio = idMunicipio;
        this.idDepartamento = idDepartamento;
        this.Municipio = Municipio;
        
    }
    @Override
    public String toString() {
        return this.getMunicipio();
    }
   

    public Integer getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Integer idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public Integer getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Integer idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getMunicipio() {
        return Municipio;
    }

    public void setMunicipio(String Municipio) {
        this.Municipio = Municipio;
    }
    
}
