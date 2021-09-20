package it.uniroma3.siw.upload;

import java.awt.Color;
import java.io.IOException;
import java.util.List;
 
import javax.servlet.http.HttpServletResponse;
 
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import it.uniroma3.siw.spring.model.Exam;
import it.uniroma3.siw.spring.model.Result;
 
 
public class ExamPDFExporter {
    private Exam exam;
    private List<Result> results;
     
    public ExamPDFExporter(Exam exam) {
        this.exam = exam;
        this.results=this.exam.getResult();
    }
 
    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
         
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
         
        cell.setPhrase(new Phrase("Nome", font));
         
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Valore", font));
        table.addCell(cell);
         
             
    }
     
    private void writeTableData(PdfPTable table) {
        for (Result result : results) {
            table.addCell(result.getName());
            table.addCell(String.valueOf(result.getValue()));
            
        }
    }
     
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setSize(12);
        font.setColor(Color.BLACK);
        Paragraph p0 = new Paragraph("Esame", font);
        p0.setAlignment(Paragraph.ALIGN_CENTER);
        
        Paragraph p1 = new Paragraph(this.exam.toStringPDF(), font);
        p1.setAlignment(Paragraph.ALIGN_LEFT);
        Paragraph p2 = new Paragraph(this.exam.getPatient().toStringPDF(), font);
        p2.setAlignment(Paragraph.ALIGN_LEFT);
        Paragraph p3 = new Paragraph(this.exam.getDoctor().toStringPDF(), font);
        p3.setAlignment(Paragraph.ALIGN_LEFT);
        Paragraph p4 = new Paragraph(this.exam.getTypeOfExamination().toStringPDF(), font);
        p4.setAlignment(Paragraph.ALIGN_LEFT);
        Paragraph p5 = new Paragraph("List of Result", font);
        p5.setAlignment(Paragraph.ALIGN_CENTER);
         
        document.add(p0);
        document.add(p1);
        document.add(p2);
        document.add(p3);
        document.add(p4);
        document.add(p5);
         
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] { 3.5f, 4.0f});
        table.setSpacingBefore(15);
         
        writeTableHeader(table);
        writeTableData(table);
         
        document.add(table);
         
        document.close();
         
    }
}