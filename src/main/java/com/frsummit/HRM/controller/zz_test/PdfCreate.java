package com.frsummit.HRM.controller.zz_test;

import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.frsummit.HRM.configuration.MyAuthorization;
import com.frsummit.HRM.service.LeaveService;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;

@Controller
public class PdfCreate {

    private static String FILE = "C:/Users/F R Summit/Desktop/doc.pdf";

    @Autowired
    private MyAuthorization myAuthorization;

    @Autowired
    private LeaveService leaveService;

    @RequestMapping(value = "/pdf")
    public String MyFun(){
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();

            Font headerFont=new Font(Font.FontFamily.TIMES_ROMAN,20.0f,Font.BOLD,BaseColor.BLACK);
            Paragraph header = new Paragraph("Application For Leave", headerFont);
            header.setAlignment(Element.ALIGN_CENTER);
            document.add(header);

            Font headerUserFont=new Font(Font.FontFamily.TIMES_ROMAN,20.0f, Font.NORMAL,BaseColor.BLACK);
            Paragraph headerUser = new Paragraph("(For Employer Only)", headerUserFont);
            headerUser.setAlignment(Element.ALIGN_CENTER);
            document.add(headerUser);

///////////////////////////////////////////////////////////////////////////////////////////////////////////////
//            Employee Details
///////////////////////////////////////////////////////////////////////////////////////////////////////////////

            Font generalHeaderFont=new Font(Font.FontFamily.TIMES_ROMAN,20.0f, Font.BOLD,BaseColor.BLACK);
            Paragraph empDetailsHeader = new Paragraph("Employer Details", generalHeaderFont);
            document.add(empDetailsHeader);

            Font generalFont=new Font(Font.FontFamily.TIMES_ROMAN,14.0f, Font.BOLD,BaseColor.BLACK);

            Paragraph empId = new Paragraph("Employer ID                 : " + myAuthorization.userFromEmailOrId().getId(), generalFont);
            document.add(empId);

            Paragraph empName = new Paragraph("Employer Name            : " + myAuthorization.userFullName(), generalFont);
            document.add(empName);

            Paragraph empDept = new Paragraph("Employer Department : " + myAuthorization.userFromEmailOrId().getDepartment(), generalFont);
            document.add(empDept);

            Paragraph empDesg = new Paragraph("Employer Designation : " + myAuthorization.userFromEmailOrId().getDesignation(), generalFont);
            document.add(empDesg);

///////////////////////////////////////////////////////////////////////////////////////////////////////////////
//            Leave Request
///////////////////////////////////////////////////////////////////////////////////////////////////////////////

            Paragraph leaveReq = new Paragraph("\nLeave Request", generalHeaderFont);
            document.add(leaveReq);

            /*Paragraph applyDate = new Paragraph("Apply Date   : ", generalFont);
            document.add(applyDate);

            Paragraph applyFrom = new Paragraph("Apply From   : ", generalFont);
            document.add(applyFrom);

            Paragraph applyTo = new Paragraph("Apply To     : ", generalFont);
            document.add(applyTo);

            Paragraph leaveType = new Paragraph("Leave Type     : ", generalFont);
            document.add(leaveType);

            Paragraph leaveReason = new Paragraph("Leave Reason   : ", generalFont);
            document.add(leaveReason);

            Paragraph totalL = new Paragraph("Total Leaves   : ", generalFont);
            document.add(totalL);

            Paragraph desc = new Paragraph("Leaves Description  : ", generalFont);
            document.add(desc);*/




            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100.0f);
//            table.setWidths(new float[] {3.0f, 2.0f, 2.0f, 2.0f, 1.0f});
            table.setWidths(new int[] {20, 50});
            table.setSpacingBefore(10);

            // define font for table header row
            Font font = FontFactory.getFont(FontFactory.HELVETICA);
            font.setColor(BaseColor.BLACK);

            // define table header cell
            PdfPCell cell = new PdfPCell();
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setPadding(1);

            // write table header
            cell.setPhrase(new Phrase("Apply Date", font));
            table.addCell(cell);
            cell.setPhrase(new Phrase("Author", font));
            table.addCell(cell);


            cell.setPhrase(new Phrase("Apply From", font));
            table.addCell(cell);
            cell.setPhrase(new Phrase("Author", font));
            table.addCell(cell);

            cell.setPhrase(new Phrase("Apply To", font));
            table.addCell(cell);
            cell.setPhrase(new Phrase("Author", font));
            table.addCell(cell);


            cell.setPhrase(new Phrase("Leave Type", font));
            table.addCell(cell);
            cell.setPhrase(new Phrase("Author", font));
            table.addCell(cell);


            cell.setPhrase(new Phrase("Leave Reason", font));
            table.addCell(cell);
            cell.setPhrase(new Phrase("Author", font));
            table.addCell(cell);


            cell.setPhrase(new Phrase("Total leave days", font));
            table.addCell(cell);
            cell.setPhrase(new Phrase("Author", font));
            table.addCell(cell);

            cell.setPhrase(new Phrase("Leave Description", font));
            table.addCell(cell);
            cell.setPhrase(new Phrase("Author", font));
            table.addCell(cell);

            document.add(table);

///////////////////////////////////////////////////////////////////////////////////////////////////////////////
//            HR Record          //////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////

            Paragraph hr = new Paragraph("\nHR Records", generalHeaderFont);
            document.add(hr);

            Paragraph hrTotalLeave = new Paragraph("Total Leave : ", generalFont);
            document.add(hrTotalLeave);

            Paragraph hrTaken = new Paragraph("Taken   : ", generalFont);
            document.add(hrTaken);

            Paragraph hrBalance = new Paragraph("Balance : ", generalFont);
            document.add(hrBalance);

            PdfPTable hrTable = new PdfPTable(4);
            hrTable.setWidthPercentage(100.0f);
            hrTable.setWidths(new int[] {20, 15, 15, 15});
            hrTable.setSpacingBefore(10);

            // define font for table header row
            Font hrFont = FontFactory.getFont(FontFactory.HELVETICA);
            hrFont.setColor(BaseColor.BLACK);

            // define table header cell
            PdfPCell hrCell = new PdfPCell();
            hrCell.setBackgroundColor(BaseColor.WHITE);
            hrCell.setPadding(1);

            // write table header
            hrCell.setPhrase(new Phrase("Reason", hrFont));
            hrTable.addCell(hrCell);

            hrCell.setPhrase(new Phrase("Total", hrFont));
            hrTable.addCell(hrCell);

            hrCell.setPhrase(new Phrase("Taken", hrFont));
            hrTable.addCell(hrCell);

            hrCell.setPhrase(new Phrase("Remaining", hrFont));
            hrTable.addCell(hrCell);

            // write table value
            hrCell.setPhrase(new Phrase("Personal", hrFont));
            hrTable.addCell(hrCell);
            hrCell.setPhrase(new Phrase("Personal Total", hrFont));
            hrTable.addCell(hrCell);
            hrCell.setPhrase(new Phrase("Personal Taken", hrFont));
            hrTable.addCell(hrCell);
            hrCell.setPhrase(new Phrase("Personal Remaining", hrFont));
            hrTable.addCell(hrCell);

            // write table value
            hrCell.setPhrase(new Phrase("Sick", hrFont));
            hrTable.addCell(hrCell);
            hrCell.setPhrase(new Phrase("Sick Total", hrFont));
            hrTable.addCell(hrCell);
            hrCell.setPhrase(new Phrase("Sick Taken", hrFont));
            hrTable.addCell(hrCell);
            hrCell.setPhrase(new Phrase("Sick Remaining", hrFont));
            hrTable.addCell(hrCell);

            // write table value
            hrCell.setPhrase(new Phrase("Sick", hrFont));
            hrTable.addCell(hrCell);
            hrCell.setPhrase(new Phrase("Planned Total", hrFont));
            hrTable.addCell(hrCell);
            hrCell.setPhrase(new Phrase("Planned Taken", hrFont));
            hrTable.addCell(hrCell);
            hrCell.setPhrase(new Phrase("Planned Remaining", hrFont));
            hrTable.addCell(hrCell);

            // write table value
            hrCell.setPhrase(new Phrase("Vacation", hrFont));
            hrTable.addCell(hrCell);
            hrCell.setPhrase(new Phrase("Vacation Total", hrFont));
            hrTable.addCell(hrCell);
            hrCell.setPhrase(new Phrase("Vacation Taken", hrFont));
            hrTable.addCell(hrCell);
            hrCell.setPhrase(new Phrase("Vacation Remaining", hrFont));
            hrTable.addCell(hrCell);

            // write table value
            hrCell.setPhrase(new Phrase("Maternity", hrFont));
            hrTable.addCell(hrCell);
            hrCell.setPhrase(new Phrase("Maternity Total", hrFont));
            hrTable.addCell(hrCell);
            hrCell.setPhrase(new Phrase("Maternity Taken", hrFont));
            hrTable.addCell(hrCell);
            hrCell.setPhrase(new Phrase("Maternity Remaining", hrFont));
            hrTable.addCell(hrCell);

            // write table value
            hrCell.setPhrase(new Phrase("Other", hrFont));
            hrTable.addCell(hrCell);
            hrCell.setPhrase(new Phrase("Other Total", hrFont));
            hrTable.addCell(hrCell);
            hrCell.setPhrase(new Phrase("Other Taken", hrFont));
            hrTable.addCell(hrCell);
            hrCell.setPhrase(new Phrase("Other Remaining", hrFont));
            hrTable.addCell(hrCell);

            document.add(hrTable);

///////////////////////////////////////////////////////////////////////////////////////////////////////////////
//            Emergency Contact
///////////////////////////////////////////////////////////////////////////////////////////////////////////////

            Paragraph hrContact = new Paragraph("\nEmergency Contact", generalHeaderFont);
            document.add(hrContact);

            Paragraph conName = new Paragraph("Name : ", generalFont);
            document.add(conName);

            Paragraph conPhone = new Paragraph("Phone : ", generalFont);
            document.add(conPhone);

            Paragraph conAd = new Paragraph("Address   : ", generalFont);
            document.add(conAd);

///////////////////////////////////////////////////////////////////////////////////////////////////////////////
//            Approve
///////////////////////////////////////////////////////////////////////////////////////////////////////////////


//            Paragraph ownerSign = new Paragraph("\n.............................", generalFont);
//            ownerSign.setAlignment(Element.ALIGN_LEFT);
//            document.add(ownerSign);
//
//            ownerSign.add(new Paragraph("Something"));
//            ownerSign.setAlignment(Element.ALIGN_RIGHT);
//            document.add(ownerSign);

//
//            Paragraph chairmanSign = new Paragraph(".............................", generalFont);
//            chairmanSign.setAlignment(Element.ALIGN_CENTER);
//            document.add(chairmanSign);
//
//            Paragraph supervisorSign = new Paragraph(".............................", generalFont);
//            supervisorSign.setAlignment(Element.ALIGN_RIGHT);
//            document.add(supervisorSign);

            Paragraph newLine = new Paragraph("\n", generalFont);
            document.add(newLine);

            Chunk glue = new Chunk(new VerticalPositionMark());
            Paragraph p = new Paragraph(".........................");
            p.add(new Chunk(glue));
            p.add(".........................");
            p.add(new Chunk(glue));
            p.add(".........................");
            document.add(p);

//            Chunk glue2 = new Chunk(new VerticalPositionMark());
//            Paragraph p2 = new Paragraph(myAuthorization.userFullName());
//            p2.add(new Chunk(glue2));
//            p2.add("Supervisor");
//            p2.add(new Chunk(glue2));
//            p2.add("Chairman");
//            document.add(p2);


            document.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return "home";
    }
}

/*
*
* // get data model which is passed by the Spring container
        List<Book> listBooks = (List<Book>) model.get("listBooks");

        doc.add(new Paragraph("Recommended books for Spring framework"));

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100.0f);
        table.setWidths(new float[] {3.0f, 2.0f, 2.0f, 2.0f, 1.0f});
        table.setSpacingBefore(10);

        // define font for table header row
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(BaseColor.WHITE);

        // define table header cell
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.BLUE);
        cell.setPadding(5);

        // write table header
        cell.setPhrase(new Phrase("Book Title", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Author", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("ISBN", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Published Date", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Price", font));
        table.addCell(cell);

        // write table row data
        for (Book aBook : listBooks) {
            table.addCell(aBook.getTitle());
            table.addCell(aBook.getAuthor());
            table.addCell(aBook.getIsbn());
            table.addCell(aBook.getPublishedDate());
            table.addCell(String.valueOf(aBook.getPrice()));
        }

        doc.add(table);
* */









/*

List<Course> courses = (List<Course>) model.get("courses");

        PdfPTable table = new PdfPTable(3);

        table.addCell("ID");
        table.addCell("Name");
        table.addCell("Date");

        for (Course course : courses){
            table.addCell(String.valueOf(course.getId()));
            table.addCell(course.getName());
            table.addCell(DATE_FORMAT.format(course.getDate()));
        }

        document.add(table);


 */


/*
*
* List<Course> courses = (List<Course>) model.get("courses");

        PdfPTable table = new PdfPTable(3);
        table.setWidths(new int[]{10, 60, 30});

        table.addCell("ID");
        table.addCell("Name");
        table.addCell("Date");

        for (Course course : courses){
            table.addCell(String.valueOf(course.getId()));
            table.addCell(course.getName());
            table.addCell(DATE_FORMAT.format(course.getDate()));
        }
* */