package Formularios;
import SolicitudMaterialesBodega.*;
import SolicitudesMateriales.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


public class FormatoTablaVencimientos extends DefaultTableCellRenderer{    
    Color colorRosa=new Color(255,255,255);
    Font normal = new Font( "Arial",Font.PLAIN,12);
    //Font negrilla = new Font( "Helvetica",Font.BOLD,18 );
    //Font cursiva = new Font( "Times new roman",Font.ITALIC,12 );

    @Override 
  
    public Component getTableCellRendererComponent ( JTable table, Object value, boolean selected, boolean focused, int row, int column ) 
    {
       // setEnabled(table == null || table.isEnabled()); 
        
        setBackground(Color.getHSBColor(0,0,10));//color de fondo
        table.setFont(new java.awt.Font("Tahoma", 0, 12)); 
        //table.setFont(normal);//tipo de fuente
       // table.setForeground(Color.black);//color de texto
        setHorizontalAlignment(0);//derecha
                
        //si la celda esta vacia se reemplaza por el texto "<vacio>" y se rellena la celda de color negro y fuente color blanco
       /* if(String.valueOf(table.getValueAt(row,column)).equals("")||String.valueOf(table.getValueAt(row,column)).equals("<vacio>")){            
            table.setValueAt("<vacio>", row, column);
            setBackground(Color.black);                         
            table.setForeground(Color.white);
            table.setFont(cursiva);
        }*/
        /*--------*/
        /*if(String.valueOf(table.getValueAt(row,column)).equals("jc Mouse")){
            setBackground(Color.red); 
            table.setFont(negrilla);                
            setHorizontalAlignment(0);//centro
        } */                       
        /*--------*/
       /* if(String.valueOf(table.getValueAt(row,column)).equals("de")){
            setBackground(Color.yellow);         
            table.setFont(negrilla);                
            setHorizontalAlignment(0);//centro
        }*/
         /*--------*/   
        
        if(String.valueOf(table.getValueAt(row,column)).equals("TRABAJO VENCIDO")){
            setBackground(Color.RED); 
            table.setFont(normal);                
            setHorizontalAlignment(0);//centro
        }   
        
        if(String.valueOf(table.getValueAt(row,column)).equals("TRABAJO A TIEMPO")){
            setBackground(Color.GREEN); 
            table.setFont(normal);                
            setHorizontalAlignment(0);//centro
        } 
        
        if(String.valueOf(table.getValueAt(row,column)).equals("TRABAJO PROXIMO A VENCER")){
            setBackground(Color.ORANGE); 
            table.setFont(normal);                
            setHorizontalAlignment(0);//centro
        } 
        /*--------*/
        //si la celda contiene números
       /* if(isNumber(String.valueOf(table.getValueAt(row,column)))){
            setBackground(Color.BLUE); 
            setHorizontalAlignment(4);//izquierda
        }  */    
        
        super.getTableCellRendererComponent(table, value, selected, focused, row, column);         
        return this;
    }
        public boolean isCellEditable(int row, int column){
               return true;}
        
 
    
   
 //
 private boolean isNumber(String cadena){
         try {
             Double.parseDouble(cadena.replace(",", " "));
         } catch (NumberFormatException nfe){
             String newCadena = cadena.replace(".", "").replace(',', '.');
             try{
                 Double.parseDouble(newCadena);
             } catch (NumberFormatException nfe2){
                 return false;
             }
        }
         return true;
    }

}
