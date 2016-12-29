package jdbcUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;



/**
 * 把数据库中的数据取出，用一定的数据结构存到内存当中，以便于在数据库连接断开之后， 还可以使用这些数据。
 * 
 * @author iceblade
 * @date 2006-06-21
 * 
 */
public class RSUtil {
	
	public static Log log = LogFactory.getLog(DBUtil.class);

	/**
	 * 
	 * @param rs
	 *            select count(1) 的结果集(只有一条记录)
	 * @return long 得到的是长整型的数数据
	 */
	public static long getCount(ResultSet rs) throws SQLException {
		long i = 0;
		while (rs.next()) {
			i = rs.getLong(1);
		}
		return i;
	}

	/**
	 * 
	 * @param rs
	 *            结果集(只有一条记录)
	 * @return 得到的是 String[]
	 */
	public static String[] getStrs(ResultSet rs) throws SQLException {
		int cols = rs.getMetaData().getColumnCount();
		String strs[] = new String[cols];
		while (rs.next()) {
			for (int i = 1; i <= cols; i++) {
				strs[i - 1] = rs.getString(i);
			}
		}

		return strs;

	}

	/**
	 * 
	 * @param rs
	 *            二维结果
	 * @return list.get() 后得到的是 String[]
	 */
	public static List<String[]> getStrsList(ResultSet rs) throws SQLException {
		List<String[]> ls = new ArrayList<String[]>();
		int cols = rs.getMetaData().getColumnCount();
		while (rs.next()) {
			String strs[] = new String[cols];
			for (int i = 1; i <= cols; i++) {

				strs[i - 1] = rs.getString(i);
			}
			ls.add(strs);
		}

		return ls;

	}

	/**
	 * 
	 * @param rs
	 *            二维结果
	 * @param begin_row
	 *            开始行(从第一行开始)
	 * @param end_row
	 *            结束行 begin_row<= $ <=end_row
	 * 
	 * @return list.get() 后得到的是 String[]
	 */
	public static List<String[]> getStrsList(ResultSet rs, int begin_row, int end_row)
			throws SQLException {
		List<String[]> ls = new ArrayList<String[]>();
		int cols = rs.getMetaData().getColumnCount();
		int n = 0;
		while (rs.next()) {
			n++;
			if (n < begin_row)
				continue;

			String strs[] = new String[cols];
			for (int i = 1; i <= cols; i++) {

				strs[i - 1] = rs.getString(i);
			}
			ls.add(strs);

			if (n >= end_row)
				break;
		}

		return ls;

	}

	/**
	 * 
	 * @param rs
	 *            二维结果
	 * @return list.get() 后得到的是 String[] 第一行是字段的Label ，第二行开始才是数据
	 */

	public static List<String[]> getStrsListLabel(ResultSet rs) throws SQLException {
		List<String[]> ls = new ArrayList<String[]>();
		int cols = rs.getMetaData().getColumnCount();
		String strs1[] = new String[cols];
		for (int i = 0; i < cols; i++) {
			strs1[i] = rs.getMetaData().getColumnLabel(i + 1);
		}
		ls.add(strs1);
		while (rs.next()) {
			String strs[] = new String[cols];
			for (int i = 1; i <= cols; i++) {

				strs[i - 1] = rs.getString(i);
			}
			ls.add(strs);
		}
		return ls;

	}

	
	public static boolean writeExcel2File(OutputStream outstream, List list) {
		int datarows = list.size();
		int datacols = ((String[]) list.get(0)).length;
		String[][] data = new String[datarows][datacols];
		for (int i = 0; i < datarows; i++) {
			data[i] = (String[]) list.get(i);
		}
		try {
			log.debug("begin writeExcel2FileSimple");
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet();
			sheet.setDefaultColumnWidth((short) 11);

			HSSFFont font2 = workbook.createFont();
			font2.setFontHeightInPoints((short) 10);
			font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

			HSSFFont font4 = workbook.createFont();
			font4.setFontHeightInPoints((short) 9);
			font4.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);

			HSSFCellStyle cellStyle2 = workbook.createCellStyle(); // data head
			HSSFCellStyle cellStyle4 = workbook.createCellStyle(); // data

			cellStyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			cellStyle2.setBorderRight(HSSFCellStyle.BORDER_THIN);
			cellStyle2.setBorderTop(HSSFCellStyle.BORDER_THIN);
			cellStyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellStyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			cellStyle2.setWrapText(true);
			cellStyle2.setFont(font2);

			cellStyle4.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			cellStyle4.setBorderRight(HSSFCellStyle.BORDER_THIN);
			cellStyle4.setBorderTop(HSSFCellStyle.BORDER_THIN);
			cellStyle4.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			cellStyle4.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
			cellStyle4.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			cellStyle4.setFont(font4);
			// cellStyle4.setDataFormat(HSSFDataFormat.getFormat("(#,##0.00_);[Red](#,##0.00)"));
			cellStyle4.setDataFormat(HSSFDataFormat
					.getBuiltinFormat("(#,##0.00_);[Red](#,##0.00)"));

			HSSFRow row;
			HSSFCell cell;

			// data head
			row = sheet.createRow((short) 1);
			row.setHeightInPoints(30);
			for (int j = 0; j < datacols; j++) {
				cell = row.createCell((short) j);
				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellStyle(cellStyle2);
				cell.setCellValue(data[0][j]);
			}
			// data left & data
			for (int i = 1; i < datarows; i++) {
				row = sheet.createRow((short) (i + 1));
				row.setHeightInPoints(15);
				for (int j = 0; j < datacols; j++) {
					cell = row.createCell((short) j);
					cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					cell.setCellStyle(cellStyle4);
					cell.setCellValue(data[i][j]);
				}
			}

			workbook.write(outstream);

			log.debug("end writeExcel2FileSimple");
		} catch (Exception ex) {
			ex.printStackTrace();

			return false;
		}
		return true;
	}

	/**
	 * 文件名：一个文件、分页导出 这是一个文件、分页导出最重要的一个方法 author linxy 2010-03-18 带表头 所有数据从一个文件导出
	 * 每个sheet50000条记录 超过50000就新增sheet
	 * 
	 * @param outstream
	 * @param resultSet
	 * @param headList
	 * @return
	 * @throws SQLException
	 */
	public static boolean writeExcelFile_OnlyOneFile(String filepath,
			ResultSet resultSet, List<String> headList) throws Exception {
		int datacols = resultSet.getMetaData().getColumnCount();
		log.debug("begin writeExcelFile By 林小应 2010-03-18");
		HSSFWorkbook workbook = new HSSFWorkbook();

		HSSFFont font2 = workbook.createFont();
		font2.setFontHeightInPoints((short) 10);
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

		HSSFFont font4 = workbook.createFont();
		font4.setFontHeightInPoints((short) 9);
		font4.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);

		HSSFCellStyle cellStyle2 = workbook.createCellStyle(); // data head
		HSSFCellStyle cellStyle4 = workbook.createCellStyle(); // data

		cellStyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStyle2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cellStyle2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cellStyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cellStyle2.setWrapText(true);
		cellStyle2.setFont(font2);

		cellStyle4.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStyle4.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cellStyle4.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cellStyle4.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cellStyle4.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		cellStyle4.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cellStyle4.setFont(font4);
		cellStyle4.setDataFormat(HSSFDataFormat
				.getBuiltinFormat("(#,##0.00_);[Red](#,##0.00)"));

		HSSFSheet sheet = null;
		HSSFRow row;
		HSSFCell cell;

		// resultSet.first();
		int cur = 0;
		while (resultSet.next()) {
			if (cur % 50000 == 0) {
				sheet = workbook.createSheet();
				sheet.setDefaultColumnWidth((short) 11);
				cur = 0;
			}
			if (cur == 0 && (null != headList && headList.size() >= datacols)) {// 表头
				row = sheet.createRow((short) 0);
				row.setHeightInPoints(30);
				for (int j = 0; j < datacols; j++) {
					cell = row.createCell((short) j);
					cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					cell.setCellStyle(cellStyle2);
					cell.setCellValue(headList.get(j));
				}
				cur++;
			}
			// data left & data
			row = sheet.createRow((short) (cur));
			row.setHeightInPoints(15);
			for (int j = 0; j < datacols; j++) {
				cell = row.createCell((short) j);
				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellStyle(cellStyle4);
				cell.setCellValue(resultSet.getString(j + 1));
			}
			cur++;
		}
		resultSet.close();
		workbook.write(new FileOutputStream(filepath));
		log.debug("end writeExcelFile By 林小应");
		return true;
	}

	/**
	 * 这是分文件、分表单导出最重要的一个方法 author linxy 2010-03-18 带表头 海量数据导出时，请合理设置
	 * totallines_per_page 和 sheetcount_per_file 每个文件控制在10万条记录以内，否则可能会导致
	 * 溢出错误，内存不够用，系统崩溃 默认每个sheet最多存放 5万条记录，每个文件最多存两个sheet ,返回文件名
	 * 类似：20100319172251_营销成功信息统计(1~1000).XLS
	 * 20100319172251_营销成功信息统计(1001~2000).XLS
	 * 
	 * @param filepath
	 *            完整文件路径
	 *            如：/ngmkt01/application/crm_market/ngape/market/uploadfiles
	 *            /20100319121212_营销成功信息统计.xls 后缀名可选
	 * @param resultSet
	 *            查询结果集
	 * @param headList
	 *            表头 ，可为空 null
	 * @param totallines_per_page
	 *            每页显示多少条记录 最大限制在每页5w条
	 * @param sheetcount_per_file
	 *            每个文件最多开个sheet
	 * @return 返回文件名列表
	 * @throws Exception
	 */
	public static List<String> writeExcelFile(String filepath,
			ResultSet resultSet, List<String> headList,
			int totallines_per_page, int sheetcount_per_file) throws Exception {
		System.out.println("begin writeExcelFile By 林小应 2010-03-18");
		if (null == filepath || "".equals(filepath)) {
			throw new Exception("文件路径不能为空！");
		}
		if (null == resultSet) {
			throw new Exception("传入的数据集为空！");
		}
		if (totallines_per_page > 50000) {
			throw new Exception("最大限制在每页5万条！");
		}
		String path = filepath.substring(0,
				filepath.lastIndexOf(File.separator) + 1);
		String filename = filepath.substring(filepath
				.lastIndexOf(File.separator) + 1);
		String testStr = filepath;
		if (Pattern.matches(".*\\.XLS", testStr.toUpperCase())) {
			filename = filename.substring(0, filename.length() - 4);
		}

		System.out.println("path = " + path);
		System.out.println("filename = " + filename);

		int datacols = resultSet.getMetaData().getColumnCount();

		HSSFWorkbook workbook = null;
		HSSFFont font2 = null;
		HSSFFont font4 = null;
		HSSFCellStyle cellStyle2 = null;
		HSSFCellStyle cellStyle4 = null;
		HSSFSheet sheet = null;
		HSSFRow row = null;
		HSSFCell cell = null;

		List<String> filepathList = new ArrayList<String>();

		int cur = 0;
		int totalrecordcount = 0;
		while (resultSet.next()) {
			if (totalrecordcount % (totallines_per_page * sheetcount_per_file) == 0) {// 换表
				// 换页前将webook写入文件
				if (totalrecordcount > 0) {
					String tempPath = filename
							+ "("
							+ ((totalrecordcount
									/ (totallines_per_page * sheetcount_per_file) - 1)
									* sheetcount_per_file * totallines_per_page + 1)
							+ "~" + totalrecordcount + ").XLS";
					workbook.write(new FileOutputStream(tempPath));
					filepathList.add(tempPath);
				}
				workbook = new HSSFWorkbook();
				font2 = workbook.createFont();
				font2.setFontHeightInPoints((short) 10);
				font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

				font4 = workbook.createFont();
				font4.setFontHeightInPoints((short) 9);
				font4.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);

				cellStyle2 = workbook.createCellStyle(); // data head
				cellStyle4 = workbook.createCellStyle(); // data

				cellStyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
				cellStyle2.setBorderRight(HSSFCellStyle.BORDER_THIN);
				cellStyle2.setBorderTop(HSSFCellStyle.BORDER_THIN);
				cellStyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				cellStyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				cellStyle2.setWrapText(true);
				cellStyle2.setFont(font2);

				cellStyle4.setBorderLeft(HSSFCellStyle.BORDER_THIN);
				cellStyle4.setBorderRight(HSSFCellStyle.BORDER_THIN);
				cellStyle4.setBorderTop(HSSFCellStyle.BORDER_THIN);
				cellStyle4.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				cellStyle4.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
				cellStyle4.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				cellStyle4.setFont(font4);
				cellStyle4.setDataFormat(HSSFDataFormat
						.getBuiltinFormat("(#,##0.00_);[Red](#,##0.00)"));
			}
			if (cur % totallines_per_page == 0) { // 换页
				sheet = workbook.createSheet();
				sheet.setDefaultColumnWidth((short) 11);
				cur = 0;
			}
			if (cur == 0 && (null != headList && headList.size() >= datacols)) {// 表头
				row = sheet.createRow((short) 0);
				row.setHeightInPoints(30);
				for (int j = 0; j < datacols; j++) {
					cell = row.createCell((short) j);
					cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					cell.setCellStyle(cellStyle2);
					cell.setCellValue(headList.get(j));
					// System.out.print(headList.get(j) + " ");
				}
				// System.out.println("");
				cur++;
				// totalrecordcount++;
			}
			// data left & data
			row = sheet.createRow((short) (cur));
			row.setHeightInPoints(15);
			for (int j = 0; j < datacols; j++) {
				cell = row.createCell((short) j);
				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellStyle(cellStyle4);
				cell.setCellValue(resultSet.getString(j + 1));
				// System.out.print(resultSet.getString(j + 1) + " ");
			}
			// System.out.println("");
			cur++;
			totalrecordcount++;
		}
		resultSet.close();
		String tempPath2 = "";// Just like :
								// F:\file\20100319155158_营销成功信息统计(1~5000).XLS
		if (totalrecordcount <= totallines_per_page) {
			tempPath2 = filename + ".XLS";
		} else {
			tempPath2 = filename
					+ "("
					+ ((totalrecordcount
							/ (totallines_per_page * sheetcount_per_file) - 1)
							* sheetcount_per_file * totallines_per_page + 1)
					+ "~" + totalrecordcount + ").XLS";
		}
		workbook.write(new FileOutputStream(tempPath2));
		filepathList.add(tempPath2);
		System.out.println("end writeExcelFile By 林小应");
		return filepathList;
	}

}
