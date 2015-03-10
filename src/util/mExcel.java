/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import Entities.Grupo;
import Entities.Par;
import Entities.Punto;
import Entities.Resonancia;
import Negocio.NPares;
import Negocio.NPunto;
import Negocio.NResonancia;
import java.awt.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.HibernateException;
import org.hibernate.internal.util.collections.CollectionHelper;

/**
 *
 * @author Lion
 */
public class mExcel {
    private String path;
    private int numSheet;
    private List<String> listSheet;
    private FileInputStream mFileInputStream;
    private XSSFWorkbook workbook;
    private String selectSheet;
    private NPunto mNPunto;
    private NResonancia mNResonancia;
    private NPares mNPares;
    private Grupo grupo;
    public void setPath(String path) {
        this.path = path;
    }

    public List<String> getListSheet() {
        return listSheet;
    }
    
    public mExcel() {
        this.mNPunto=new NPunto();
        this.mNResonancia= new NResonancia();
        this.mNPares=new NPares();
        this.grupo=new Grupo();
        grupo.setDescripcion("Sin Grupo");
        grupo.setId(1000);
    }

//    public static String init(Component parent){
//        String path="";
//        JFileChooser chooser= new JFileChooser();
//        int rslt=chooser.showOpenDialog(parent);
//        if(rslt== JFileChooser.APPROVE_OPTION)
//        {
//            path=chooser.getSelectedFile().getAbsolutePath();
//        }
//        return path;
//    }
    public void init(Component parent) throws Exception
    {
        try
        {
            JFileChooser chooser= new JFileChooser();
            int rslt=chooser.showOpenDialog(parent);
            if(rslt== JFileChooser.APPROVE_OPTION)
            {
                this.path=chooser.getSelectedFile().getAbsolutePath();
            }
            mFileInputStream = new FileInputStream(new File(this.path));
            //Create Workbook instance holding reference to .xlsx file
            workbook = new XSSFWorkbook(mFileInputStream);
            //Get first/desired sheet from the workbook
            this.numSheet=workbook.getNumberOfSheets();
            this.listSheet=new ArrayList<>();
            for(int i=0; i<this.numSheet;i++){
                this.listSheet.add(workbook.getSheetName(i));
            }
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
            throw new Exception("No se pudo abrir el documento, verifique que el formato se '.xlsx'");
        }
    
    }
    public void close() throws Exception{
        try {
            this.mFileInputStream.close();
             this.workbook.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            throw new Exception("Erro al cerrar el archivo");
        }
       
    }
    public void initDB(String sheetName) throws Exception
    {
        try
        {
            this.selectSheet=sheetName;
            XSSFSheet sheet = workbook.getSheet(this.selectSheet);
            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            ArrayList listaFila= new ArrayList();
            ArrayList<String> listaColum=null;
            int count=0;
            while (rowIterator.hasNext())
            {   
                Row row = rowIterator.next();
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();
                if(row.getRowNum()>0)
                {
                    Punto punto=new Punto();
                    Resonancia resonancia= new Resonancia();
                    Par par=new Par();
                    while (cellIterator.hasNext())
                    {
                         Cell cell = cellIterator.next();
                         //Check the cell type and format accordingly
                        int countColum=cell.getColumnIndex();
                        switch(countColum){
                            case 1:
                                punto.setDescripcion(getObject(cell));
                                break;
                            case 2:
                                resonancia.setDescription(getObject(cell));
                                break;
                            case 3:
                                punto.setLocalizacion(getObject(cell));
                                break;
                            case 4:
                                resonancia.setLocalizacion(getObject(cell));
                                break;
                            case 5:
                                par.setPatogeno(getObject(cell));
                                break;
                            case 6:
                                par.setTipo(getObject(cell));
                                break;
                            case 7:
                                 par.setSintomatologia(getObject(cell));
                                break;               
                        }
                    }
                    punto.setGrupo(grupo);
                    punto=this.mNPunto.guardarPunto(punto);
                    resonancia=this.mNResonancia.guardarResonancia(resonancia);
                    par.setPunto(punto);
                    par.setResonancia(resonancia);
                    par=this.mNPares.insertPar(par);
                }
            }
        }catch(HibernateException he){
            System.out.println(he.getMessage());
            throw new Exception("Error al guardar en la base de datos");
        }
    }
    private String getObject(Cell cell){
        String value=" ";
        switch(cell.getCellType())
        {
            case Cell.CELL_TYPE_STRING:
                value= cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                value=String.valueOf(cell.getNumericCellValue());
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                value= String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA:
                value=cell.getCellFormula();
        }
        return value;
    }
    public DefaultTableModel getModel(){
        DefaultTableModel model=null;
        Object colum[]={"Punto","Resonancia","Localizacion1", "Localizacion2","Patogeno","Tipo","Sintomatologia"};
        Object data[][]=new Object[1085][];
        if(this.path!= "")
        {
            try
                {
                    FileInputStream file = new FileInputStream(new File(this.path));
                    //Create Workbook instance holding reference to .xlsx file
                    XSSFWorkbook workbook = new XSSFWorkbook(file);
                    //Get first/desired sheet from the workbook
                    XSSFSheet sheet = workbook.getSheet("Regulares");
                    //Iterate through each rows one by one
                    Iterator<Row> rowIterator = sheet.iterator();
                    ArrayList listaFila= new ArrayList();
                    ArrayList<String> listaColum=null;
                    int count=0;
                    while (rowIterator.hasNext())
                    {
                        Row row = rowIterator.next();
                        //For each row, iterate through all the columns
                        Iterator<Cell> cellIterator = row.cellIterator();
                        if(row.getRowNum()>0)
                        {   listaColum= new ArrayList<>();
                            while (cellIterator.hasNext())
                            {
                                Cell cell = cellIterator.next();
                                //Check the cell type and format accordingly
                                int countColum=cell.getColumnIndex();
                                if(countColum>0 && countColum<8)
                                {
                                    switch (cell.getCellType())
                                    {
                                        case Cell.CELL_TYPE_BLANK:
                                            listaColum.add(" ");
                                            break;
                                        case Cell.CELL_TYPE_STRING:
                                            listaColum.add(cell.getStringCellValue());
                                            break;
                                    }
                                }
                            }
                            System.out.println("Fila # "+ count+ "agregada");
                            System.out.println(listaColum.toString());
                            data[count]=listaColum.toArray();
                            count++;
                        }
                      
                        
                    }
                    file.close();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            model= new DefaultTableModel(data, colum);
        }
        return model;
    }
    
}
