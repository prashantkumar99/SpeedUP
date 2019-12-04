package speedup.typing.para;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

class ParaDetailTable extends DefaultTableModel{
	public ParaDetailTable(int rows,JTable jtable) {
		String str[]={"S No.","Letter\\Min","Word\\Min","No Of Letters","Correct Letter","Wrong Letter","Time Taken(min : sec)","Marks(%)"};
		
		for(String val:str)addColumn(val);
		for(int i=0;i<rows;i++){
			String table[]=new String[str.length];
			for(int j=0;j<str.length;j++){
				if(j==0)table[j]=""+(i+1);
				else table[j]="0";
			}
			addRow(table);
		}
	}
	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		return false;
	}
}
