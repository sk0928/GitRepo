package com.cg.demo.fixedlength;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ExcelReading {

	public static void main(String[] args) throws BiffException, IOException {
		String response = "JJJJAAAANNNNUUUUUAAAAARRRRRYYYYFFFFEEEBBBRRRUUUUAAAARRRRYYYYMMMMMMAAAAAARRRRRRRCCCCCCHHHHHHAAAAAAPPPPPPRRRRRRIIIIIILLLLLLMMMMMMMMMMAAAAAAAAAAAYYYYYYYYYYJJJJJJJUUUUUUUUNNNNNNNNEEEEEEEJJJJJJJUUUUUUUULLLLLLLLYYYYYYYYAAAAAUUUUUGGGGGUUUUUUSSSSSTTTTTSSSSEEEEPPPTTTEEEMMMBBBEEERRRROOOOCCCCCTTTTTOOOOOBBBBEEEERRRRNNNNOOOOVVVVEEEMMMBBBBEEEERRRRDDDDEEEECCCCEEEMmMMBBBBEEEERRRR";

		List<MessageName> messagename = new ExcelReading().excel();
		new ExcelReading().stringprint(response, messagename);
	}

	void stringprint(String str, List<MessageName> messageName) {
		int count = 0;
		int level = 0;
		for (int i = 0; i < messageName.size(); i++) {
			if ((null != messageName.get(i))&& (messageName.get(i).getName().contains("Jun") || messageName.get(i).getName().contains("jun"))) {
				int ctemp = 0;
				System.out
						.println(messageName.get(i).getName() + ":" + str.substring(0, messageName.get(i).getLength()));
				str = str.substring(messageName.get(i).getLength());
				/*messageName.remove(i);
				messageName.add(i, null);*/
				if (ctemp == 0) {
					level = messageName.get(i + 1).getLevel();
				}
				while (messageName.get(i + 1).getLevel() == (messageName.get(i).getLevel())) {
					for (int temp = 0; temp <= messageName.get(i).getLength(); temp++) {
						System.out.println("hello");
						}
					i++;
				}

				count++;

			} else {
				if (count == 0) {
					System.out.println(
							messageName.get(0).getName() + ":" + str.substring(0, messageName.get(0).getLength()));
					str = str.substring(messageName.get(0).getLength());

					count++;
				} else {
					String str1 = str;
					System.out.println(
							messageName.get(i).getName() + ":" + str.substring(0, messageName.get(i).getLength()));
					str = str.substring(messageName.get(i).getLength());

				}

			}
		}
	}

	public List<MessageName> excel() throws BiffException, IOException {
		List<MessageName> msgstrct = new ArrayList<MessageName>();
		String filepath = "C:\\Users\\00000000000000000000\\Desktop\\XcelSam.xls";
		FileInputStream fs = new FileInputStream(filepath);
		Workbook wb = Workbook.getWorkbook(fs);
		Sheet sh = wb.getSheet("MySheet");
		int totalNoOfRows = sh.getRows();
		System.out.println(totalNoOfRows);
		int totalNoOfCols = sh.getColumns();
		System.out.println(totalNoOfCols);
		for (int row = 0; row < totalNoOfRows; row++) {
			MessageName msgnm = new MessageName();

			int level = Integer.parseInt((sh.getCell(0, row).getContents()));
			int length = Integer.parseInt((sh.getCell(2, row).getContents()));
			msgnm.setLength(length);
			msgnm.setLevel(level);
			msgnm.setName(sh.getCell(1, row).getContents());
			msgstrct.add(msgnm);

		}
		System.out.println(msgstrct);
		return msgstrct;

	}
}
