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
		String response = "10  Starts11   Starts12    starts13     starts00365                                                         starts65                                                         starts65                                                         starts56                                                starts56                                                starts32                        starts10  starts256                                                                                                                                                                                                                                                       starts256                                                                                                                                                                                                                                                       starts256                                                                                                                                                                                                                                                       starts002105                                                                                                starts105                                                                                                starts105                                                                                                starts105                                                                                                starts105                                                                                                starts105                                                                                                starts256                                                                                                                                                                                                                                                       starts256                                                                                                                                                                                                                                                       starts256                                                                                                                                                                                                                                                       starts";

		List<MessageName> messagename = new ExcelReading().excel();
		new ExcelReading().stringprint(response, messagename);
	}

	void stringprint(String str, List<MessageName> messageName) {
		int count = 0;
		int level = 0;
		for (int i = 0; i < messageName.size(); i++) {
			if ((null != messageName.get(i))
					&& (messageName.get(i).getName().contains("Count") || messageName.get(i).getName().contains("Count"))) {
				int ctemp = 0;
				messageName.get(i).setValue(str.substring(0, messageName.get(i).getLength()));
				System.out.println(messageName.get(i).getValue());
				int Counter = Integer.parseInt(messageName.get(i).getValue());
				str = str.substring(messageName.get(i).getLength());
				/*
				 * messageName.remove(i); messageName.add(i, null);
				 */
				if (ctemp == 0) {
					level = messageName.get(i + 1).getLevel();
				}
				int temp_index=i+1;
				int c =0;
				for (int temp = 1; temp <= Counter; temp++) {
					
					while(messageName.get(temp_index).getLevel()==level){
						c++;
						messageName.get(temp_index).setValue(str.substring(0, messageName.get(temp_index).getLength()));
						System.out.println(messageName.get(temp_index).getValue());
						str = str.substring(messageName.get(temp_index).getLength());
						temp_index++;
					}
					temp_index=i+1;
				}
				i=i+(c/Counter);
				count++;

			} else {
				if (count == 0) {
					messageName.get(i).setValue(str.substring(0, messageName.get(0).getLength()));
					System.out.println(messageName.get(i).getValue());
					str = str.substring(messageName.get(0).getLength());

					count++;
				} else {
					String str1 = str;
					messageName.get(i).setValue(str.substring(0, messageName.get(i).getLength()));
					System.out.println(messageName.get(i).getValue());
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
		Sheet sh = wb.getSheet("Sheet1");
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
