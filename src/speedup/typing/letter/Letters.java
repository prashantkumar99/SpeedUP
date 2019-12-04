package speedup.typing.letter;

import java.util.Random;
import java.util.Vector;

class Letters {
	Vector<String> smallLetterList,capitaLetterList,numberList,symbolList,list;
	Random r;
	public Letters() {
		smallLetterList=new Vector<>();
		for(char ch='a';ch<='z';ch++)smallLetterList.add(String.valueOf(ch));
		
		capitaLetterList=new Vector<>();
		for(char ch='A';ch<='Z';ch++)capitaLetterList.add(String.valueOf(ch));
		
		numberList=new Vector<>();
		for(int i=0;i<10;i++)numberList.add(String.valueOf(i));
		
		symbolList=new Vector<>();
		symbolList.add("/");
		symbolList.add("?");
		symbolList.add(">");
		symbolList.add("<");
		symbolList.add("'");
		symbolList.add("\"");
		symbolList.add(";");
		symbolList.add(":");
		symbolList.add("\\");
		symbolList.add("|");
		symbolList.add("]");
		symbolList.add("[");
		symbolList.add("}");
		symbolList.add("{");
		symbolList.add("+");
		symbolList.add("=");
		symbolList.add("-");
		symbolList.add("_");
		symbolList.add(")");
		symbolList.add("(");
		symbolList.add("*");
		symbolList.add("&");
		symbolList.add("^");
		symbolList.add("%");
		symbolList.add("$");
		symbolList.add("#");
		symbolList.add("@");
		symbolList.add("!");
		symbolList.add("~");
		
		list=new Vector<>();
		
		r=new Random();
	}
	void addSmallLetter(){
		list.addAll(smallLetterList);
	}
	void addCapitalLetter(){
		list.addAll(capitaLetterList);
	}
	void addNumber(){
		list.addAll(numberList);
	}
	void addSymbol(){
		list.addAll(symbolList);
	}
	void removeAll(){
		list.removeAllElements();
	}
	String getNextLetter(){
		return list.get(r.nextInt(list.size()));
	}
}
