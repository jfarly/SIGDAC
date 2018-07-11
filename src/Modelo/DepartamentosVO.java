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
public class DepartamentosVO {
    private Integer iddepartamento;
    private String departamento;
    
    public DepartamentosVO (){}
    
    public DepartamentosVO(Integer iddepartamento, String departamento){
        this.iddepartamento = iddepartamento;
        this.departamento = departamento;
    }
    @Override
    public String toString(){
        return this.departamento;
    }

    public Integer getIddepartamento() {
        return iddepartamento;
    }

    public void setIddepartamento(Integer iddepartamento) {
        this.iddepartamento = iddepartamento;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
    
}
