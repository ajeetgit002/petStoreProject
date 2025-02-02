package api.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataPrividers {
	
	
	@DataProvider(name="Data")
	public String[][] getAllData() throws IOException {
		
		

		String filePath = "src/test/resources/testData/UserData.xlsx";


		
		XLUtility xl=new XLUtility(filePath);
		
		
	int rowcount=	xl.getRowCount("Sheet1");
	
	int colcount=xl.getCellCount("Sheet1", 1);
	
	
	String apidata[][]=new String[rowcount][colcount];
	
	
	for (int i = 1; i <=rowcount; i++) {
		
		for(int j=0;j<colcount;j++) {
			apidata[i-1][j]=xl.getCellData("Sheet1", i, j);
		}
	}
	
	return apidata;
	
	
	
	}
	@DataProvider(name="UserNames")
	public String[] getUserName() throws IOException {
		
		String path=System.getProperty("user.dir")+"\\testData\\UserData.xlsx";
		XLUtility xl=new XLUtility(path);
		
		int rowcount=	xl.getRowCount("Sheet1");
		
		String apidata[]=new String[rowcount];
		
		for (int i = 1; i <=rowcount; i++) {
			apidata[i-1]=xl.getCellData("Sheet1", i, 1);
		}
		return apidata;
	}
	
	
	
	

}
