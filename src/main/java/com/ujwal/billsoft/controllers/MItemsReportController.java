package com.ujwal.billsoft.controllers;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.ujwal.billsoft.commons.Constants;
import com.ujwal.billsoft.commons.DateConvertor;
import com.ujwal.billsoft.commons.ExportToExcel;
import com.ujwal.billsoft.models.BillDetails;
import com.ujwal.billsoft.models.BillHeader;
import com.ujwal.billsoft.models.CompReport;
import com.ujwal.billsoft.models.FocItemBean;
import com.ujwal.billsoft.models.ItemBean;
import com.ujwal.billsoft.models.MCompany;
import com.ujwal.billsoft.models.MGetPart;
import com.ujwal.billsoft.models.MPart;
import com.ujwal.billsoft.models.MTax;
import com.ujwal.billsoft.models.MUom;
import com.ujwal.billsoft.models.MUser;
import com.ujwal.billsoft.models.TaxBillBean;

@Controller
@Scope("session")

public class MItemsReportController {
	
	RestTemplate rest = null;
	List<ItemBean> getList = new ArrayList<>();

	List<FocItemBean> focList = new ArrayList();

	/***********************************Item Report**************************************/
	@RequestMapping(value="/showItemsReport", method=RequestMethod.GET)
	
	public ModelAndView ShowItemForm(HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView("report/ItemReport");
		try {
			
			HttpSession session = request.getSession();
			MUser userResponse = (MUser) session.getAttribute("userBean");
			int compId = (int)session.getAttribute("conpanyId");
			int locationId = (int)session.getAttribute("locationId");
			System.out.println("Comps Itm Id="+compId+" "+locationId);
			rest = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("compId", compId);
		
			List<MPart> partList = rest.postForObject(Constants.url + "/ujwal/getAllPart",map, List.class);
			mav.addObject("partList", partList);
			mav.addObject("title", "Items Report");
		}catch(Exception e){
			System.out.println(e.getMessage());
		}

		return mav;		
	} 
	
	@RequestMapping(value="/getItemListBetweenDate", method = RequestMethod.GET)
	
	public @ResponseBody List<ItemBean>  itemReport(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		int compId = (int)session.getAttribute("conpanyId");
		int locationId = (int)session.getAttribute("locationId");
		
		int itemId = Integer.parseInt(request.getParameter("itemId"));
		String fromDate =  request.getParameter("fromDate");
		String toDate =  request.getParameter("toDate");
		
		
		System.out.println("Item Data = "+ itemId+" "+fromDate+" "+toDate+" "+compId+" "+locationId);
			
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		
		map.add("itemId", itemId);
		map.add("compId", compId);
		map.add("locationId", locationId);
		map.add("fromDate", DateConvertor.convertToYMD(fromDate));
		map.add("toDate", DateConvertor.convertToYMD(toDate));
		
		ItemBean[]  itemList = rest.postForObject(Constants.url + "/ujwal/getItemsBetweenDate", map,ItemBean[].class);
		getList = new ArrayList<ItemBean>(Arrays.asList(itemList));

		System.out.println("Response Listt  "+getList);
		List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

		ExportToExcel expoExcel = new ExportToExcel();
		List<String> rowData = new ArrayList<String>();

		rowData.add("Sr. No");
		rowData.add("Item Name");
		rowData.add("HSN Code");		
		rowData.add("Tax Rate");	
	
		rowData.add("Qty");	
		rowData.add("CGST Amt");
		rowData.add("SGST Amt");
		rowData.add("IGST Amt");
	
		rowData.add("Taxable Amt");
		rowData.add("Total Tax");
		rowData.add("Total Amt");

		expoExcel.setRowData(rowData);
		exportToExcelList.add(expoExcel);
		int cnt = 1;
		for (int i = 0; i < getList.size(); i++) {
			expoExcel = new ExportToExcel();
			rowData = new ArrayList<String>();
			cnt = cnt + i;
			float totalAmt = getList.get(i).getTaxableAmount()+getList.get(i).getTotalTax();
			rowData.add("" + (i + 1));

			rowData.add("" + getList.get(i).getPartName());
			rowData.add("" + getList.get(i).getHsnCode());
			rowData.add("" + getList.get(i).getTaxPer());
			rowData.add("" + getList.get(i).getQty());
			
			rowData.add("" + getList.get(i).getCgst());
			rowData.add("" + getList.get(i).getSgst());
			rowData.add("" + getList.get(i).getIgst());
			rowData.add("" + getList.get(i).getTaxableAmount());
			rowData.add("" + getList.get(i).getTotalTax());
			rowData.add("" + totalAmt);
		

			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);

		}

		
		session.setAttribute("exportExcelList", exportToExcelList);
		session.setAttribute("excelName", "GetMatIssueHeader");
		return getList;
		
	}     
	
	
	@RequestMapping(value = "/showItemWisePdf/{fromDate}/{toDate}/{itemId}", method = RequestMethod.GET)
	public void showTaxwisePdf(@PathVariable("fromDate") String fromDate, @PathVariable("toDate") String toDate,@PathVariable("itemId") int itemId,
			HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException {
		BufferedOutputStream outStream = null;
		System.out.println("Inside Pdf showItemWisePdf");
		Document document = new Document(PageSize.A4);

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();

		System.out.println("time in Gen Bill PDF ==" + dateFormat.format(cal.getTime()));
		String FILE_PATH = Constants.REPORT_SAVE;
		File file = new File(FILE_PATH);

		PdfWriter writer = null;

		FileOutputStream out = new FileOutputStream(FILE_PATH);
		try
		{
			writer = PdfWriter.getInstance(document, out);
		} catch (DocumentException e) {

			e.printStackTrace();
		}

		PdfPTable table = new PdfPTable(11);
		try {
			System.out.println("Inside PDF Table try");
			
			

			table.setWidthPercentage(100);
			table.setWidths(new float[] {2.4f,4.4f, 3.4f, 3.2f, 3.2f, 3.2f, 3.2f, 3.2f, 3.2f, 3.2f,3.2f});
			Font headFont = new Font(FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK);
			Font headFont1 = new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLACK);
			headFont1.setColor(BaseColor.WHITE);
			Font f = new Font(FontFamily.TIMES_ROMAN, 12.0f, Font.UNDERLINE, BaseColor.BLUE);

			PdfPCell hcell = new PdfPCell();
			hcell.setBackgroundColor(BaseColor.PINK);

			hcell.setPadding(3);
			hcell = new PdfPCell(new Phrase("Sr.No.", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

		
			
			hcell = new PdfPCell(new Phrase("Item Name", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);
			hcell = new PdfPCell(new Phrase("HSN Code", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);
			table.addCell(hcell);
			hcell = new PdfPCell(new Phrase("Tax Rate", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);
			table.addCell(hcell);
			
			hcell = new PdfPCell(new Phrase("Qty", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);
			table.addCell(hcell);
	
			hcell = new PdfPCell(new Phrase("CGST", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);
			table.addCell(hcell);
			
			hcell = new PdfPCell(new Phrase("SGST", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);
			table.addCell(hcell);
			
			hcell = new PdfPCell(new Phrase("IGST", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);
			table.addCell(hcell);
			
			hcell = new PdfPCell(new Phrase("Taxable Amt", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);
			table.addCell(hcell);
			
		
			hcell = new PdfPCell(new Phrase("Total Tax", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);
			table.addCell(hcell);
			
			hcell = new PdfPCell(new Phrase("Total Amt", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);
			table.addCell(hcell);
		
			int index = 0;
			for (ItemBean work : getList) {
				float ttlAmt = work.getTotalTax()+work.getTaxableAmount();
				index++;
				PdfPCell cell;

				cell = new PdfPCell(new Phrase(String.valueOf(index), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPadding(3);
				cell.setPaddingRight(2);
				table.addCell(cell);

				
				cell = new PdfPCell(new Phrase("" + work.getPartName(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase("" + work.getHsnCode(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + work.getTaxPer(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase("" + work.getQty(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				
				cell = new PdfPCell(new Phrase("" + work.getCgst(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);
				
				
				cell = new PdfPCell(new Phrase("" + work.getSgst(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase("" + work.getIgst(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase("" + work.getTaxableAmount(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);
				
	
				
				cell = new PdfPCell(new Phrase("" + work.getTotalTax(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase("" + ttlAmt, headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);
				
				
		

			}
			document.open();
			Paragraph name = new Paragraph("Ujwal Billing Report\n", f);
			name.setAlignment(Element.ALIGN_CENTER);
			document.add(name);
			document.add(new Paragraph(" "));
			Paragraph company = new Paragraph("Bill Item Wise Report\n", f);
			company.setAlignment(Element.ALIGN_CENTER);
			document.add(company);
			document.add(new Paragraph(" "));

			DateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
			String reportDate = DF.format(new Date());
			Paragraph p1 = new Paragraph("From Date:" + fromDate + "  To Date:" + toDate, headFont);
			p1.setAlignment(Element.ALIGN_CENTER);
			document.add(p1);
			document.add(new Paragraph("\n"));
			document.add(table);

			int totalPages = writer.getPageNumber();

			System.out.println("Page no " + totalPages);

			document.close();

			if (file != null) {

				String mimeType = URLConnection.guessContentTypeFromName(file.getName());

				if (mimeType == null) {

					mimeType = "application/pdf";

				}

				response.setContentType(mimeType);

				response.addHeader("content-disposition", String.format("inline; filename=\"%s\"", file.getName()));

				response.setContentLength((int) file.length());

				InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

				try {
					FileCopyUtils.copy(inputStream, response.getOutputStream());
				} catch (IOException e) {
					System.out.println("Excep in Opening a Pdf File");
					e.printStackTrace();
				}
			}

		} catch (DocumentException ex) {

			System.out.println("Pdf Generation Error: " + ex.getMessage());

			ex.printStackTrace();

		}

	}
	
	/************************************FOC Report******************************************/
	
@RequestMapping(value="/focSalesReport", method=RequestMethod.GET)
	
	public ModelAndView showfocSalesReport(HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView("report/focSalesReport");
		try {
			
			HttpSession session = request.getSession();
			MUser userResponse = (MUser) session.getAttribute("userBean");
			int compId = (int)session.getAttribute("conpanyId");
			int locationId = (int)session.getAttribute("locationId");
			System.out.println("Comps Itm Id="+compId+" "+locationId);
			rest = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("compId", compId);
		
			List<MPart> partList = rest.postForObject(Constants.url + "/ujwal/getAllPart",map, List.class);
			mav.addObject("partList", partList);
			mav.addObject("title", "FOC Sales Report");
		
		}catch(Exception e){
			System.out.println(e.getMessage());
		}

		return mav;		
	} 


@RequestMapping(value="/listfocItemSales", method=RequestMethod.GET)
public @ResponseBody List<FocItemBean> listfocItemSales(HttpServletRequest request) {
	
		try {
		
		
			int itemId = Integer.parseInt(request.getParameter("itemId"));
			String fromDate =  request.getParameter("fromDate");
			String toDate =  request.getParameter("toDate");
			
		HttpSession session = request.getSession();
		MUser userResponse = (MUser) session.getAttribute("userBean");
		int compId = (int)session.getAttribute("conpanyId");
		int locationId = (int)session.getAttribute("locationId");
		System.out.println("Comps Itm Id="+compId+" "+locationId+" "+itemId+" "+fromDate+" "+toDate);
		rest = new RestTemplate();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		
		map.add("itemId", itemId);
		map.add("fromDate", DateConvertor.convertToYMD(fromDate));
		map.add("toDate", DateConvertor.convertToYMD(toDate));
		map.add("locationId", locationId);
		map.add("compId", compId);
		
		FocItemBean[] focItemList= rest.postForObject(Constants.url + "/ujwal/getAllFocPart",map, FocItemBean[].class);
		focList = new ArrayList<FocItemBean>(Arrays.asList(focItemList));
		
		System.out.println("Foc Items:"+focList);	
	
	List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

	ExportToExcel expoExcel = new ExportToExcel();
	List<String> rowData = new ArrayList<String>();

	rowData.add("Sr. No");
	rowData.add("Invoice No");		
	rowData.add("Invoice Date");
	rowData.add("Item Name");
	rowData.add("Unit Of Measure");	
	rowData.add("Mrp.");
	rowData.add("Qty.");

	expoExcel.setRowData(rowData);
	exportToExcelList.add(expoExcel);
	int cnt = 1;
	for (int i = 0; i < focList.size(); i++) {
		expoExcel = new ExportToExcel();
		rowData = new ArrayList<String>();
		cnt = cnt + i;
		
		rowData.add("" + (i + 1));

		rowData.add("" + focList.get(i).getInvoiceNo());
		rowData.add("" + focList.get(i).getBillDate());
		rowData.add("" + focList.get(i).getPartName());
		rowData.add("" + focList.get(i).getUomName());
		
		rowData.add("" + focList.get(i).getMrp());
		rowData.add("" + focList.get(i).getQty());
	

		expoExcel.setRowData(rowData);
		exportToExcelList.add(expoExcel);

	}

	
	session.setAttribute("exportExcelList", exportToExcelList);
	session.setAttribute("excelName", "GetMatIssueHeader");

	
	}catch(Exception e){
		System.out.println(e.getMessage());
	}

	return focList;		
} 
	

@RequestMapping(value = "/showFocItemSalesPdf/{fromDate}/{toDate}/{itemId}", method = RequestMethod.GET)
public void showFocItemSalesPdf(@PathVariable("fromDate") String fromDate, @PathVariable("toDate") String toDate,@PathVariable("itemId") int itemId,
		HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException {
	
	System.out.println("List Her:"+focList);
	
	BufferedOutputStream outStream = null;
	System.out.println("Inside Pdf showItemWisePdf");
	Document document = new Document(PageSize.A4);

	DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	Calendar cal = Calendar.getInstance();

	System.out.println("time in Gen Bill PDF ==" + dateFormat.format(cal.getTime()));
	String FILE_PATH = Constants.REPORT_SAVE;
	File file = new File(FILE_PATH);

	PdfWriter writer = null;

	FileOutputStream out = new FileOutputStream(FILE_PATH);
	try
	{
		writer = PdfWriter.getInstance(document, out);
	} catch (DocumentException e) {

		e.printStackTrace();
	}

	PdfPTable table = new PdfPTable(7);
	try {
		System.out.println("Inside PDF Table try");
		
		System.out.println("List Her:"+focList);

		table.setWidthPercentage(100);
		table.setWidths(new float[] {2.4f,4.4f, 3.4f, 3.2f, 3.2f, 3.2f, 3.2f});
		Font headFont = new Font(FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK);
		Font headFont1 = new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLACK);
		headFont1.setColor(BaseColor.WHITE);
		Font f = new Font(FontFamily.TIMES_ROMAN, 12.0f, Font.UNDERLINE, BaseColor.BLUE);

		PdfPCell hcell = new PdfPCell();
		hcell.setBackgroundColor(BaseColor.PINK);

		hcell.setPadding(3);
		hcell = new PdfPCell(new Phrase("Sr.No.", headFont1));
		hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		hcell.setBackgroundColor(BaseColor.PINK);
		table.addCell(hcell);

	
		
		hcell = new PdfPCell(new Phrase("Invoice No.", headFont1));
		hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		hcell.setBackgroundColor(BaseColor.PINK);
		table.addCell(hcell);
		
		hcell = new PdfPCell(new Phrase("Invoice Date", headFont1));
		hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		hcell.setBackgroundColor(BaseColor.PINK);
		table.addCell(hcell);
		
		hcell = new PdfPCell(new Phrase("Item Name", headFont1));
		hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		hcell.setBackgroundColor(BaseColor.PINK);
		table.addCell(hcell);
		
		hcell = new PdfPCell(new Phrase("Unit Of Measeure", headFont1));
		hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		hcell.setBackgroundColor(BaseColor.PINK);
		table.addCell(hcell);

		hcell = new PdfPCell(new Phrase("Mrp.", headFont1));
		hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		hcell.setBackgroundColor(BaseColor.PINK);
		table.addCell(hcell);
		
		hcell = new PdfPCell(new Phrase("Qty", headFont1));
		hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		hcell.setBackgroundColor(BaseColor.PINK);
		table.addCell(hcell);
		
		
	
		int index = 0;
		for (FocItemBean work : focList) {
			
			index++;
			PdfPCell cell;

			cell = new PdfPCell(new Phrase(String.valueOf(index), headFont));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setPadding(3);
			cell.setPaddingRight(2);
			table.addCell(cell);

			
			cell = new PdfPCell(new Phrase("" + work.getInvoiceNo(), headFont));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setPaddingRight(2);
			cell.setPadding(3);
			table.addCell(cell);
			
			cell = new PdfPCell(new Phrase("" + work.getBillDate(), headFont));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setPaddingRight(2);
			cell.setPadding(3);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("" + work.getPartName(), headFont));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setPaddingRight(2);
			cell.setPadding(3);
			table.addCell(cell);

			
			cell = new PdfPCell(new Phrase("" + work.getUomName(), headFont));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setPaddingRight(2);
			cell.setPadding(3);
			table.addCell(cell);
			
			
			cell = new PdfPCell(new Phrase("" + work.getMrp(), headFont));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setPaddingRight(2);
			cell.setPadding(3);
			table.addCell(cell);
			
			cell = new PdfPCell(new Phrase("" + work.getQty(), headFont));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setPaddingRight(2);
			cell.setPadding(3);
			table.addCell(cell);
						
	

		}
		document.open();
		Paragraph name = new Paragraph("Ujwal Billing Report\n", f);
		name.setAlignment(Element.ALIGN_CENTER);
		document.add(name);
		document.add(new Paragraph(" "));
		Paragraph company = new Paragraph("FOC Items Sales Report\n", f);
		company.setAlignment(Element.ALIGN_CENTER);
		document.add(company);
		document.add(new Paragraph(" "));

		DateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
		String reportDate = DF.format(new Date());
		Paragraph p1 = new Paragraph("From Date:" + fromDate + "  To Date:" + toDate, headFont);
		p1.setAlignment(Element.ALIGN_CENTER);
		document.add(p1);
		document.add(new Paragraph("\n"));
		document.add(table);

		int totalPages = writer.getPageNumber();

		System.out.println("Page no " + totalPages);

		document.close();

		if (file != null) {

			String mimeType = URLConnection.guessContentTypeFromName(file.getName());

			if (mimeType == null) {

				mimeType = "application/pdf";

			}

			response.setContentType(mimeType);

			response.addHeader("content-disposition", String.format("inline; filename=\"%s\"", file.getName()));

			response.setContentLength((int) file.length());

			InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

			try {
				FileCopyUtils.copy(inputStream, response.getOutputStream());
			} catch (IOException e) {
				System.out.println("Excep in Opening a Pdf File");
				e.printStackTrace();
			}
		}

	} catch (DocumentException ex) {

		System.out.println("Pdf Generation Error: " + ex.getMessage());

		ex.printStackTrace();

	}

}

	
}
