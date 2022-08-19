/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import java.util.Date;

/**
 *
 * @author jluis
 */
public class CerrararTra {
    
    private Date fecha;
    private int no;
    private String nota;
    private int Estado;
    private String FechaS;

    public String getFechaS() {
        return FechaS;
    }

    public void setFechaS(String FechaS) {
        this.FechaS = FechaS;
    }
    
    

    public int getEstado() {
        return Estado;
    }

    public void setEstado(int Estado) {
        this.Estado = Estado;
    }

    public String getNota() {
        return nota;
    }
    public void setNota(String nota) {
        this.nota = nota;
    }
    public int getNo() {
        return no;
    }
    public void setNo(int no) {
        this.no = no;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
