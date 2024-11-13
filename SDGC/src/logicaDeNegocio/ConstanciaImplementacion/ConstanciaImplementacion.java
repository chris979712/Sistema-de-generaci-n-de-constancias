package logicaDeNegocio.ConstanciaImplementacion;


import java.util.List;
import org.apache.log4j.Logger;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import logicaDeNegocio.Clases.Profesor;
import logicaDeNegocio.Clases.TrabajoRecepcional;

public class ConstanciaImplementacion {
    
    private static final Logger LOG=Logger.getLogger(ConstanciaImplementacion.class);
    private static final String gillSansPath = "src/logicaDeNegocio/Utilidades/Gill Sans MT.ttf";
    
    public ConstanciaImplementacion(){
        
    }
    
    public Document crearInformeDeColaboracion(Profesor profesor,List<TrabajoRecepcional> trabajos){
        Document informeColaboracion = new Document();
        informeColaboracion.setMargins(50, 50, 50, 50);
        String rutaInforme = "C:\\Users\\chris\\OneDrive\\Escritorio\\Constancia"+profesor.getNombre()+profesor.getPrimerApellido()+profesor.getSegundoApellido()+".pdf";
        try{
            PdfWriter pdfEscritor = PdfWriter.getInstance(informeColaboracion,new FileOutputStream(rutaInforme));
            Paragraph tituloInforme = obtenerTituloDeInforme();
            Paragraph[] cuerposDeInforme = obtenerCuerpoDelInforme(profesor,trabajos);
            PdfPTable tablaActividades = obtenerTrabajoRecepcionalDeInforme(trabajos);
            Paragraph[] cierreDeInforme = obtenerCierreDeInforme();
            Paragraph separador = new Paragraph();
            separador.add(" \n");
            informeColaboracion.open();
            //Rectangle bordeDePagina = crearBordeDelInforme(informeColaboracion);
            //informeColaboracion.add(bordeDePagina);
            informeColaboracion.add(tituloInforme);
            informeColaboracion.add(cuerposDeInforme[0]);
            informeColaboracion.add(cuerposDeInforme[1]);
            informeColaboracion.add(cuerposDeInforme[2]);
            informeColaboracion.add(cuerposDeInforme[3]);
            informeColaboracion.add(separador);
            informeColaboracion.add(tablaActividades);
            informeColaboracion.add(cierreDeInforme[0]);
            informeColaboracion.add(cierreDeInforme[1]);
            informeColaboracion.close();
        }catch(BadElementException | IOException excepcion){
            LOG.error(excepcion.getMessage());
        }catch(DocumentException  excepcion){
            LOG.error(excepcion.getMessage());
        }
        return informeColaboracion;
    }
    
    /*
        public Rectangle crearBordeDelInforme(Document informeColaboracion){
            float margenIzquierdo = informeColaboracion.leftMargin() - 10;
            float margenDerecho = informeColaboracion.getPageSize().getWidth() - informeColaboracion.rightMargin() + 10;
            float margenSuperior = informeColaboracion.getPageSize().getHeight() - informeColaboracion.topMargin() + 10;
            float margenInferior = informeColaboracion.bottomMargin() -10 ;
            Rectangle bordePagina = new Rectangle(margenIzquierdo, margenInferior, margenDerecho, margenSuperior);
            bordePagina.setBorder(Rectangle.BOX);
            bordePagina.setBorderColor(BaseColor.BLACK);
            bordePagina.setBorderWidth(2);
            return bordePagina;
        }
    */
 
    public PdfPTable obtenerTrabajoRecepcionalDeInforme(List<TrabajoRecepcional> trabajos) throws DocumentException, IOException{
        PdfPTable tablaActividades = new PdfPTable(5);
        String fontTimesNewRomanPath = "src/logicaDeNegocio/Utilidades/Times New Roman.ttf";
        BaseFont timesNewRomanFont = BaseFont.createFont(fontTimesNewRomanPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font times1Font = new Font(timesNewRomanFont, 14, Font.BOLD);
        Font times2Font = new Font(timesNewRomanFont, 12);
        PdfPCell celdaNombreAlumno = new PdfPCell(new Phrase("Nombre(s) del (los) alumno(s)", times1Font));
        celdaNombreAlumno.setBackgroundColor(BaseColor.LIGHT_GRAY);
        tablaActividades.addCell(celdaNombreAlumno);
        PdfPCell celdaTituloTrabajo = new PdfPCell(new Phrase("Título de trabajo", times1Font));
        celdaTituloTrabajo.setBackgroundColor(BaseColor.LIGHT_GRAY);
        tablaActividades.addCell(celdaTituloTrabajo);
        PdfPCell celdaModalidad = new PdfPCell(new Phrase("Modalidad", times1Font));
        celdaModalidad.setBackgroundColor(BaseColor.LIGHT_GRAY);
        tablaActividades.addCell(celdaModalidad);
        PdfPCell celdaFechaPresentacion = new PdfPCell(new Phrase("Fecha de presentación", times1Font));
        celdaFechaPresentacion.setBackgroundColor(BaseColor.LIGHT_GRAY);
        tablaActividades.addCell(celdaFechaPresentacion);
        PdfPCell celdaResultadoObtenido = new PdfPCell(new Phrase("Resultado obtenido en la defensa", times1Font));
        celdaResultadoObtenido.setBackgroundColor(BaseColor.LIGHT_GRAY);
        tablaActividades.addCell(celdaResultadoObtenido);
        for(TrabajoRecepcional trabajo : trabajos){
            tablaActividades.addCell(new PdfPCell(new Phrase(trabajo.getAlumno().getNombreCompleto(), times2Font)));
            tablaActividades.addCell(new PdfPCell(new Phrase(trabajo.getTituloDeTrabajo(), times2Font)));
            tablaActividades.addCell(new PdfPCell(new Phrase(trabajo.getModalidad(), times2Font)));
            tablaActividades.addCell(new PdfPCell(new Phrase(trabajo.getFechaDePresentacion(), times2Font)));
            tablaActividades.addCell(new PdfPCell(new Phrase(trabajo.getResultadoObtenido(), times2Font)));
        }
        return tablaActividades;
    }
    
    public Paragraph obtenerTituloDeInforme() throws DocumentException, IOException{
        BaseFont gillSansBaseFont = BaseFont.createFont(gillSansPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font gillSansFont = new Font(gillSansBaseFont, 12, Font.BOLD);
        Font gillSansFont2 = new Font(gillSansBaseFont, 10,Font.BOLD);
        Paragraph tituloDeInforme = new Paragraph();
        tituloDeInforme.setAlignment(Paragraph.ALIGN_RIGHT);
        Chunk textoEnNegrita = new Chunk("\n\nFacultad de Estadistica e Informática", gillSansFont);
        Chunk textoEnNegrita2 = new Chunk("\n\nRegión Xalapa", gillSansFont2);
        tituloDeInforme.add(textoEnNegrita);
        tituloDeInforme.add(textoEnNegrita2);
        return tituloDeInforme;
    }
    
    public Paragraph[] obtenerCuerpoDelInforme(Profesor profesor,List<TrabajoRecepcional> trabajosRecepcionales) throws DocumentException, IOException{
        String fontTimesNewRomanPath = "src/logicaDeNegocio/Utilidades/Times New Roman.ttf";
        BaseFont timesNewRomanFont = BaseFont.createFont(fontTimesNewRomanPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font times1Font = new Font(timesNewRomanFont, 14);
        Font times2Font = new Font(timesNewRomanFont, 14, Font.BOLDITALIC);
        Paragraph cuerpoDeInforme1 = new Paragraph();
        Paragraph cuerpoDeInforme2 = new Paragraph();
        Paragraph cuerpoDeInforme3 = new Paragraph();
        Paragraph cuerpoDeInforme4 = new Paragraph();
        cuerpoDeInforme1.setAlignment(Paragraph.ALIGN_LEFT);
        cuerpoDeInforme1.setFont(times1Font);
        Chunk textoEnNegrita = new Chunk("\nA quien corresponda",times2Font);
        cuerpoDeInforme1.add(textoEnNegrita);
        cuerpoDeInforme2.setFont(times1Font);
        cuerpoDeInforme2.setAlignment(Paragraph.ALIGN_JUSTIFIED);
        cuerpoDeInforme2.add("\nEl que suscribe, Director de la Facultad de Estadistica e Informática, de la Universidad Veracruzana");
        cuerpoDeInforme3.setAlignment(Paragraph.ALIGN_CENTER);
        cuerpoDeInforme3.setFont(times1Font);
        Chunk textoEnNegrita2 = new Chunk("\nHACE CONSTAR",times2Font);
        cuerpoDeInforme3.add(textoEnNegrita2);
        cuerpoDeInforme4.setFont(times1Font);
        cuerpoDeInforme4.setAlignment(Paragraph.ALIGN_JUSTIFIED);
        cuerpoDeInforme4.add("\nque el MTRO. "+profesor.getNombre()+" "+profesor.getPrimerApellido()+" "+profesor.getSegundoApellido()+", fungió como "+trabajosRecepcionales.get(0).getRol()+" "
                + "en los siguientes trabajos recepcionales de la licenciatura "+trabajosRecepcionales.get(0).getCarrera()+".");
        Paragraph[] detalles = new Paragraph[4];
        detalles[0] = cuerpoDeInforme1;
        detalles[1] = cuerpoDeInforme2;
        detalles[2] = cuerpoDeInforme3;
        detalles[3] = cuerpoDeInforme4;
        return detalles;
    }
    
    public Paragraph[] obtenerCierreDeInforme() throws DocumentException, IOException{
        String fontTimesNewRomanPath = "src/logicaDeNegocio/Utilidades/Times New Roman.ttf";
        BaseFont timesNewRomanFont = BaseFont.createFont(fontTimesNewRomanPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font times1Font = new Font(timesNewRomanFont, 14);
        Font times2Font = new Font(timesNewRomanFont, 12);
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd 'dia(s) de' MMMM 'de' yyyy");
        String fechaFormateada = fechaActual.format(formatter);
        Paragraph cuerpoDeInforme1 = new Paragraph();
        Paragraph cuerpoDeInforme2 = new Paragraph();
        cuerpoDeInforme1.setAlignment(Paragraph.ALIGN_JUSTIFIED);
        cuerpoDeInforme1.setFont(times1Font);
        cuerpoDeInforme1.add("\n\nA petición del interesado y para fines legales que al mismo convenga, se extiende la presente en la ciudad de Xalapa-Enríquez, Veracruz a los "+
                fechaFormateada+".");
        cuerpoDeInforme2.setAlignment(Paragraph.ALIGN_CENTER);
        cuerpoDeInforme2.setFont(times1Font);
        cuerpoDeInforme2.add("\n\nAtentamente");
        cuerpoDeInforme2.add("\n'Lis de Veracruz: Arte,Ciencia,Luz'");
        cuerpoDeInforme2.add("\n\n\nDr.Luis Gerardo Montané Jiménez");
        cuerpoDeInforme2.add("\nDirector");
        Paragraph[] cuerposDeCierre = new Paragraph[2];
        cuerposDeCierre[0] = cuerpoDeInforme1;
        cuerposDeCierre[1] = cuerpoDeInforme2;
        return cuerposDeCierre;
    }
    
    public boolean validarExistenciaDeInforme(int idColaboracion){
        boolean resultadoValidacion;
        String rutaInforme = "Informes\\informeDeColaboracion"+idColaboracion+".pdf";
        Path informeAGenerar = Paths.get(rutaInforme);
        if(Files.exists(informeAGenerar)){
            resultadoValidacion = true;
        }else{
            resultadoValidacion = false;
        }
        return resultadoValidacion;
    }
    
    public int guardarArchivoDeInforme(File archivo, int idColaboracion){
        int resultadoGuardado = 0;
        Document informeColaboracion = new Document();
        try{
            PdfReader lectorPdf = new PdfReader("Informes/informeDeColaboracion"+idColaboracion+".pdf");
            int numPaginas = lectorPdf.getNumberOfPages();
            PdfCopy copiaPdf = new PdfCopy(informeColaboracion, new FileOutputStream(archivo));
            informeColaboracion.open();
            for (int i = 1; i <= numPaginas; i++) {
                copiaPdf.addPage(copiaPdf.getImportedPage(lectorPdf, i));
            }
            informeColaboracion.close();
            lectorPdf.close();
            copiaPdf.close();
            resultadoGuardado = 1;
        }catch(IOException | DocumentException excepcion){
            LOG.error(excepcion.getCause());
            resultadoGuardado = -1;
        }
        return resultadoGuardado;
    }
    
}
