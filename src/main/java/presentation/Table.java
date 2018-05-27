package presentation;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import javax.swing.JTable;

public class Table {
	
	public static <T> JTable createTable(List<T> t) {
		
		Object[][] resultData = new Object[t.size()][t.get(0).getClass().getDeclaredFields().length];
		String[] resultColumnName = new String[t.get(0).getClass().getDeclaredFields().length];
		int i = 0;
		
		for(T objectTable: t) {
			int j = 0;
			for (Field field : objectTable.getClass().getDeclaredFields()) {
				field.setAccessible(true); 
				Object value;
				try {
					value = field.get(objectTable);
					resultData[i][j] = value;
					resultColumnName[j] = field.getName();

				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				++j;
			}
			++i;
		}
		
		return new JTable(resultData, resultColumnName);	
	}
	
	public static JTable mapToTable(Map<String, Boolean> mapData){

		   String columnNames[] = { "Student", "Laboratory Number" };
		   Object[][] data = new Object[mapData.size()][2];

		   int i = 0;
		      for(Map.Entry<String, Boolean> entry : mapData.entrySet()){
		    	  data[i][0] = entry.getKey();
		    	  data[i][1] = entry.getValue();
		    	  i++;
		      }

		   JTable table = new JTable(data, columnNames);

		   return table;
		}
	
	public static JTable tableGrades(Map<String, Float> mapData){

		   String columnNames[] = { "Student", "Grades" };
		   Object[][] data = new Object[mapData.size()][2];

		   int i = 0;
		      for(Map.Entry<String, Float> entry : mapData.entrySet()){
		    	  data[i][0] = entry.getKey();
		    	  data[i][1] = entry.getValue();
		    	  i++;
		      }

		   JTable table = new JTable(data, columnNames);

		   return table;
		}
}
