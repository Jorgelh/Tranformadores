/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import java.sql.Blob;
import java.util.Date;
/**
 *
 * @author jluis
 */
public class inspe {
    
    
    public int  no;
    public String trabajo;
    public String PN;
    public Date fecha1;
    public String fecha2;
    public int cantidadIn;
    public int cantidadOut;
    public String proceso;
    public String lote;
    public String realizadopor;
    public String notas;
    public String fechain;
    public String fechaout;
    

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getTrabajo() {
        return trabajo;
    }

    public void setTrabajo(String trabajo) {
        this.trabajo = trabajo;
    }

    public String getPN() {
        return PN;
    }

    public void setPN(String PN) {
        this.PN = PN;
    }

    public int getCantidadIn() {
        return cantidadIn;
    }

    public void setCantidadIn(int cantidadIn) {
        this.cantidadIn = cantidadIn;
    }

    public int getCantidadOut() {
        return cantidadOut;
    }

    public void setCantidadOut(int cantidadOut) {
        this.cantidadOut = cantidadOut;
    }

    public String getProceso() {
        return proceso;
    }

    public void setProceso(String proceso) {
        this.proceso = proceso;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getRealizadopor() {
        return realizadopor;
    }

    public void setRealizadopor(String realizadopor) {
        this.realizadopor = realizadopor;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

   
    public String getFechain() {
        return fechain;
    }

    public void setFechain(String fechain) {
        this.fechain = fechain;
    }

    public String getFechaout() {
        return fechaout;
    }

    public void setFechaout(String fechaout) {
        this.fechaout = fechaout;
    }

    public Date getFecha1() {
        return fecha1;
    }

    public void setFecha1(Date fecha1) {
        this.fecha1 = fecha1;
    }

    public String getFecha2() {
        return fecha2;
    }

    public void setFecha2(String fecha2) {
        this.fecha2 = fecha2;
    }
    
    
    
    
}
