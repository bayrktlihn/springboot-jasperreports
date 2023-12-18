package io.bayrktlihn.jasperreports.util;

import net.sf.jasperreports.engine.*;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class JasperReports {

	public static byte[] createPdf(InputStream inputStream, Map<String, Object> params) {

		return createPdf(inputStream, params, new JREmptyDataSource());
	}

	public static byte[] createPdf(InputStream inputStream, JRDataSource jrDataSource) {
		return createPdf(inputStream, new HashMap<>(), jrDataSource);
	}

	public static byte[] createPdf(InputStream inputStream, Map<String, Object> params, JRDataSource jrDataSource) {
		try {
			JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, jrDataSource);
			return JasperExportManager.exportReportToPdf(jasperPrint);
		} catch (JRException e) {
			throw new RuntimeException(e);
		}
	}

}