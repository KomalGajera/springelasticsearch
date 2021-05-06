package com.demo.springelasticsearch.service;

import java.awt.Color;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.poi.xslf.usermodel.SlideLayout;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFPictureData;
import org.apache.poi.xslf.usermodel.XSLFPictureShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFSlideLayout;
import org.apache.poi.xslf.usermodel.XSLFSlideMaster;
import org.apache.poi.xslf.usermodel.XSLFTable;
import org.apache.poi.xslf.usermodel.XSLFTextBox;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.poi.xslf.usermodel.XSLFTextRun;
import org.apache.poi.xslf.usermodel.XSLFTextShape;
import org.apache.poi.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.demo.springelasticsearch.model.Book;
import com.demo.springelasticsearch.model.UploadRequestDTO;
import com.demo.springelasticsearch.repository.BookRepository;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * @author inex3
 *
 */
@Service
public class BookServiceImpl implements BookService {

	/**
	 * 
	 */
	@Autowired
	private BookRepository repo;

	/**
	 *
	 */
	@Override
	public Book save(Book book) {
		return repo.save(book);
	}

	/**
	 *
	 */
	@Override
	public void delete(Book book) {
		repo.delete(book);
	}

	/**
	 *
	 */
	@Override
	public Book findOne(String id) {
		return repo.findById(id).get();
	}

	/**
	 *
	 */
	@Override
	public Iterable<Book> findAll() {
		return repo.findAll();
	}

	/**
	 *
	 */
	@Override
	public Page<Book> findByAuthor(String author, PageRequest pagerequest) {
		return repo.findByAuthor(author, pagerequest);
	}

	/**
	 *
	 */
	@Override
	public List<Book> fingBytitle(String title) {
		return repo.findByTitle(title);
	}

	/**
	 *
	 */
	@Override
	public void fileReleaseUpload(@Valid UploadRequestDTO dto) throws FileNotFoundException, IOException {
		File datafile = new File(dto.getFile().getOriginalFilename());
		//datafile = new File(FilenameUtils.getName(datafile.getCanonicalPath()));
		String name = FilenameUtils.getExtension(dto.getFile().getOriginalFilename());

		System.out.println("\n\n\n\n\n extension is:" + name);
		System.out.println("\n\n\n\n\n\n content type:" + dto.getFile().getContentType());
		System.out.println("\n\n\n\n\n\n get file name:" + dto.getFile().getName());
		System.out.println("\n\n\n\n\n\n get origin file name:" + dto.getFile().getOriginalFilename());
//		File dataFilenew = new File(dto.getFile().getOriginalFilename());
		// String dataFilenew= FilenameUtils.getName(dataFile.getCanonicalPath());
//		@SuppressWarnings("resource")
//		FileOutputStream outputStream = new FileOutputStream(datafile);
//		outputStream.write(dto.getFile().getBytes());
//		System.out.println("\n\n\n\n\n\n\n new file is:" + datafile);

	}

	@Override
	public void generatePpt() throws IOException, URISyntaxException {
		
		
		Document document = new Document(PageSize.A1);
		try {
			
		PdfWriter.getInstance(document, new FileOutputStream("iTextHelloWorld.pdf"));
		document.open();		
		Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
		
		PDPage page = new PDPage();
		

		
		Path path = Paths.get(ClassLoader.getSystemResource("aaa.png").toURI());	
		Image img = Image.getInstance(path.toAbsolutePath().toString());
		//img.setInitialRotation(100);
		//img.set
//		img.setAlignment(Image.ALIGN_LEFT);
//		img.setAbsolutePosition(45f, 10f);
//		img.scalePercent(30,20);
		
		
		
		
		document.add(img);

		document.close();
		} catch (FileNotFoundException | DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
//		
//		XMLSlideShow ppt = new XMLSlideShow();
//		ppt.createSlide();
//		XSLFSlideMaster defaultMaster = ppt.getSlideMasters().get(0);
//		XSLFSlideLayout layout 
//		  = defaultMaster.getLayout(SlideLayout.BLANK);
//		XSLFSlide slide1 = ppt.createSlide();
//		XSLFSlide slide2 = ppt.createSlide();
//		XSLFSlide slide3 = ppt.createSlide();
//		
////		XSLFTextShape titleShape = slide1.getPlaceholder(0);
////		XSLFTextShape contentShape = slide1.getPlaceholder(1);
//		
//		XSLFTextBox shape = slide1.createTextBox();
//		XSLFTextParagraph p = shape.addNewTextParagraph();
//		XSLFTextRun r = p.addNewTextRun();
//		r.setText("Baeldung");
//		r.setFontColor(Color.green);
//		r.setFontSize(24.);
//		
//		XSLFTable tbl = slide2.createTable(10,2);
//		tbl.setAnchor(new Rectangle(50, 50, 50000, 100));
//		tbl.setRowHeight(0, 5000);
//		
//		
//		FileOutputStream out = new FileOutputStream("powerpoint.pptx");
//		ppt.write(out);
//		out.close();
	}

	
//	/**
//	 * @param dataFile
//	 * @return
//	 * @throws IOException
//	 * @throws FileNotFoundException
//	 */
//	private String getFileContents(File dataFile) throws IOException, FileNotFoundException {
//
//		Path path = Paths.get(dataFile.getCanonicalPath());
//		byte[] fileContentBytes = Files.readAllBytes(path);
//		String fileContents = new String(fileContentBytes, StandardCharsets.UTF_8);		
//		return fileContents;
//	}

}
