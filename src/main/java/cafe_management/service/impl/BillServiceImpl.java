package cafe_management.service.impl;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.apache.el.lang.ELArithmetic;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import cafe_management.Utils.CafeUtils;
import cafe_management.constant.CafeConstant;
import cafe_management.dao.BillDao;
import cafe_management.jwt.JwtFilter;
import cafe_management.pojo.Bill;
import cafe_management.service.BillService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BillServiceImpl implements BillService {
	@Autowired
	JwtFilter jwtFilter;
	@Autowired
	BillDao billDao;

	@Override
	public ResponseEntity<String> generateReport(Map<String, Object> requestMap) {
		log.info("Inside generateReport ");
		try {
			String fileName;
			if (validateRequestMap(requestMap)) {
				if (requestMap.containsKey("isGenerate") && !(Boolean)requestMap.get("isGenerate")) {
					fileName = (String) requestMap.get("uuid");
				} else {
					fileName = CafeUtils.getUUID();
					requestMap.put("uuid", fileName);
					insertBill(requestMap);
				}
				String data = "Name : " + requestMap.get("name") + "\n" + " Contact Number : "
						+ requestMap.get("cantactNumber") + "\n " + " Email : " + requestMap.get("email") + "\n "
						+ " Payment Method : " + requestMap.get("paymentMethod") + "\n" + " Total Amount : "
						+ requestMap.get("total") + "\n" + " ProductDetails : "
						+ requestMap.get("productDetails") + "\n" + " CreatedBy : " + requestMap.get("createdBy")
						+ "\n";
				Document document = new Document();
				PdfWriter.getInstance(document,
						new FileOutputStream(CafeConstant.STORE_LOCATION  + fileName + ".pdf"));
				document.open();
				setRectangleInPdf(document);

				Paragraph chunk = new Paragraph("Cafe Management System", getFont("Header"));
				chunk.setAlignment(Element.ALIGN_CENTER);
				document.add(chunk);

				Paragraph p = new Paragraph(data + "\n \n", getFont("data"));
				document.add(p);

				PdfPTable table = new PdfPTable(5);
				table.setWidthPercentage(100);
				addTableHeader(table);

				JSONArray jsonArray = CafeUtils.getJsonArrayFromString((String) requestMap.get("productDetails"));
				for (int i = 0; i < jsonArray.length(); i++) {
					addRows(table, CafeUtils.getMapFromJson(jsonArray.getString(i)));
				}
				document.add(table);

				Paragraph footer = new Paragraph("Total : " + requestMap.get("totalAmount") + "\n"
						+ "Thanks you for visiting. Please Visit Again!!", getFont(data));
				document.add(footer);
				document.close();
				return new ResponseEntity<String>("{\"uuid\" :\"" + fileName + "\"}", HttpStatus.OK);
			}

			return CafeUtils.getResponse("required Data Not Found", HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {

			ex.printStackTrace();
		}
		return CafeUtils.getResponse(CafeConstant.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private void addRows(PdfPTable table, Map<String, Object> data) {
		log.info("Inside addrows");
		table.addCell((String) data.get("name"));
		table.addCell((String) data.get("category"));
		table.addCell((String) data.get("quantity"));
		table.addCell(Double.toString((Double) data.get("price")));
		table.addCell(Double.toString((Double) data.get("total")));
	}

	private void addTableHeader(PdfPTable table) {
		log.info("Inside table");
		Stream.of("Name", "Category", "Quantity", "Price", "Sub Total").forEach(columnTitle -> {
			PdfPCell header = new PdfPCell();
			header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			header.setBorderWidth(2);
			header.setPhrase(new Phrase(columnTitle));
			header.setBackgroundColor(BaseColor.YELLOW);
			header.setHorizontalAlignment(Element.ALIGN_CENTER);
			header.setVerticalAlignment(Element.ALIGN_CENTER);
			table.addCell(header);
		});
	}

	private Font getFont(String type) {
		log.info("Inside getFont");
		switch (type) {
		case "Header":
			Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE, 18, BaseColor.BLACK);
			headerFont.setStyle(Font.BOLD);
			return headerFont;
		case "Data":
			Font dataFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, BaseColor.BLACK);
			dataFont.setStyle(Font.BOLD);
			return dataFont;
		default:
			return new Font();
		}

	}

	private void setRectangleInPdf(Document document) throws DocumentException {

		log.info("Inside setRectangleInPdf");
		Rectangle rect = new Rectangle(577, 825, 18, 15);
		rect.enableBorderSide(1);
		rect.enableBorderSide(2);
		rect.enableBorderSide(4);
		rect.enableBorderSide(8);
		rect.setBorderColor(BaseColor.BLACK);
		rect.setBorderWidth(1);
		document.add(rect);
	}

	private void insertBill(Map<String, Object> requestMap) {
		try {

			Bill bill = new Bill();
			bill.setUuid((String) requestMap.get("uuid"));
			bill.setName((String) requestMap.get("name"));
			bill.setEmail( (String)requestMap.get("email"));
			bill.setContactNumber((String) requestMap.get("contactNumber"));
			bill.setPaymentMethod((String) requestMap.get("paymentMethod"));
			bill.setTotal(Integer.parseInt((String) requestMap.get("totalAmount")));
			bill.setProductDetails((String) requestMap.get("productDetails"));
			bill.setCreatedBy(jwtFilter.getCurrentUser());
			billDao.save(bill);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	private boolean validateRequestMap(Map<String, Object> requestMap) {

		return requestMap.containsKey("name") && requestMap.containsKey("contactNumber")
				&& requestMap.containsKey("email") && requestMap.containsKey("paymentMethod")
				&& requestMap.containsKey("totalAmount") && requestMap.containsKey("productDetails")

		;
	}

	@Override
	public ResponseEntity<List<Bill>> getBills() {
		      List<Bill>list = new ArrayList<Bill>();
		       if (jwtFilter.isAdmin()) {
				 list = billDao.getAllBills();
			} else {
             list = billDao.billByUserName(jwtFilter.getCurrentUser());
			}
		       return new ResponseEntity<List<Bill>>(list ,HttpStatus.OK);
	     
	}

}
