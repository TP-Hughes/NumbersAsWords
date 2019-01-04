import java.util.Scanner;
public class NumbersAsWords{
	
	private String num;
	private int length;
	public static final String[] digits = {"1","2","3","4","5","6","7","8","9"};
	public static final String[] unitsAsWords = {"one ","two ","three ","four ","five ","six ","seven ","eight ","nine "};
	public static final String[] teensAsWords = {"eleven ","twelve ","thirteen ","fourteen ","fifteen ","sixteen ","seventeen ","eighteen ","nineteen "};
	public static final String[] tensAsWords = {"ten", "twenty ","thirty ","forty ","fifty ","sixty ","seventy ","eighty ","ninety "};
	public static final int THOUSAND_TAIL_LENGTH = 3;
	public static final int MILLION_TAIL_LENGTH = 6;
	public static final String MILLION = "million";
	public static final String THOUSAND = "thousand";

	
	NumbersAsWords(int num){
		this.num = Integer.toString(num);
		length = this.num.length();
		returnWord();
	}

	private void commas(int tailLength){
		if(nonzero(num.substring(length-tailLength,length-2))){
			System.out.print(", ");
		}else{ 
			System.out.print(" ");
		}
	}

	public void setNum(int num){
		this.num = Integer.toString(num);
		length = this.num.length();
	}
	public String getNum(){
		return num;
	}

	public void writeDigits(String digit,String[] words){
		for(int i =0; i<9; i++){
			if(digit.equals(digits[i])){
				System.out.print(words[i]);
				break;
			}
		}
	}
		private void prefix(int tailLength, String magnitude){
		int hPos = length - (tailLength+3);
		int tPos = length - (tailLength+2);
		int uPos = length - (tailLength+1);
		int endPos = length - tailLength;

		if(length>(tailLength+2)){
			String hun = num.substring(hPos,endPos);
			hundreds(hun);
			if(nonzero(hun)){
				System.out.print(magnitude);
				commas(tailLength);
			}
		}else if(length>(tailLength+1)){
			String ten = num.substring(tPos,endPos);
			tens(ten);
			if(nonzero(ten)){
				System.out.print(magnitude);
				commas(tailLength);
			}
		}else{
			String unit = num.substring(uPos,endPos);
			units(unit);
			if(nonzero(unit)){
				System.out.print(magnitude);
				commas(tailLength);
			}
		}
	}
	private boolean nonzero(String digits){
		int l = digits.length();
		for(int pos=0; pos<l; pos++){
			if(!(digits.substring(pos,pos+1).equals("0"))){
				return true;
			}
		}
		return false;
	}
	public void units(String number){
	//take unit and return to command line the digit spelled out
		int l = number.length();
		String units = number.substring(l-1,l);
		writeDigits(units,unitsAsWords);
	}
	public void tens(String number){
		int l = number.length();
		String tens = number.substring(l-2,l-1);
		String unit = number.substring(l-1,l);
		if(tens.equals("1")){
			if(unit.equals("0")){
				System.out.print("ten ");
			}else writeDigits(unit,teensAsWords);
		}else{
			writeDigits(tens,tensAsWords);
			units(number);
		}
	}
	public void hundreds(String number){
		int l = number.length();
		String hundreds = number.substring(l-3,l-2);
		if(!hundreds.equals("0")){
			writeDigits(hundreds,unitsAsWords);
			System.out.print("hundred ");
			if(!(number.substring(l-2,l).equals("00"))){
				System.out.print("and ");
			}
		}else if(l>3 && !(number.substring(l-2,l).equals("00"))){
			System.out.print("and ");
		}
		tens(number);
	}
	public void thousands(){
	//hundreds() + "thousand"
		prefix(THOUSAND_TAIL_LENGTH, THOUSAND);
		hundreds(num);
	}

	public void millions(){
	//hundred() tens() units() +"million" 
		prefix(MILLION_TAIL_LENGTH,MILLION);
		thousands();	
	}
	public void billions(){
	// 2 billion or 1 billion"
		units(num.substring(0,1));
		System.out.print("billion");
		if(nonzero(num.substring(length-9,length-2))){
			System.out.print(", ");
			millions(); 
		}else{ 
			System.out.print(" ");
			tens(num);
		}		
	}

	public void returnWord(){
		if(num.equals("0")){
			System.out.print("zero");
		}
		else if (length==10) {
			billions();
		}
		else if (length<10 && length>6) {
			millions();			
		}
		else if (length<7 && length>3) {
			thousands();			
		}
		else if (length==3) {
			hundreds(num);
		}
		else if (length==2) {
			tens(num); 
		}
		else if (length==1) {
			units(num);
		}
		System.out.println();
	}


	public static void main(String args[]){
		boolean ask = true;
		Scanner sc = new Scanner(System.in);
		int i;

		NumbersAsWords number; 

		i = sc.nextInt();
		number = new NumbersAsWords(i);

			
		}

}	

