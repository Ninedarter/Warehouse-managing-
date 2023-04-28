package com.warehouse.warehouse.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.warehouse.warehouse.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
@Service
public class PdfService {
    @Autowired
    private final MailSendingService mailSendingService;

    public PdfService(MailSendingService mailSendingService) {
        this.mailSendingService = mailSendingService;
    }


    public void convertToPdf(List<Company> companies) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("companiesList.pdf"));
        document.open();

        PdfPTable table = new PdfPTable(3); // 3 columns
        table.addCell("Company name");
        table.addCell("Product");
        table.addCell("Price");



        companies.stream()
                        .flatMap(company -> company.getProducts().stream())
                                .forEach(product -> {
                                    table.addCell(product.getCompany().getName());
                                    table.addCell(product.getName());
                                    table.addCell(product.getPrice().toString());
                                });

        document.add(table);

        document.close();

      mailSendingService.sendSimpleMessage();

    }
}
