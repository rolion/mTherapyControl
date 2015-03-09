/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.Component;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.internal.util.collections.CollectionHelper;

/**
 *
 * @author Lion
 */
public class mExcel {
    private String path;

    public void setPath(String path) {
        this.path = path;
    }

    public mExcel() {
    }

    public static String init(Component parent){
        String path="";
        JFileChooser chooser= new JFileChooser();
        int rslt=chooser.showOpenDialog(parent);
        if(rslt== JFileChooser.APPROVE_OPTION)
        {
            path=chooser.getSelectedFile().getAbsolutePath();
        }
        return path;
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
