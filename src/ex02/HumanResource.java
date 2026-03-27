package ex02;

import java.util.Date;
import java.util.Scanner;
//입력 1줄 -> 결과 1줄
//입력data : 사번,이름,입사일,월급,부서번호
//출력     : 사번,이름,입사일,월급,보너스,수령액,부서명


//금액은 소수이하 두자리로 반올림
//보너스   =  근무연수에 따라 월급의 0.5% 로 계산한다
//수령액   =  월급 + 보너스
//부서명   =  10:인사,20:자재,30:총무,40:연구개발,50:생산,60:서비스

//모든기능은 class에 구현한다.
//입력data
//사번,이름,입사일,월급,부서번호
/*
100,사나,20110101,300.0,10      
200,모모,20120301,270.0,20      
300,정연,20091003,250.0,30      
400,나연,20110105,220.0,40      
500,미나,20180401,170.0,60      
600,쯔위,20150801,200.0,50      
*/

interface Ipo { // interface Ipo로 실제 업무 처리 과정을 나누었음
	void		input();		
	void		process();
	void		output();
} // interface Ipo end

class ColVo { // 
	// field
	// 입력data : 사번,이름,입사일,월급,부서번호
	//				num, name, start, sallary, part
	// 출력     : 사번,이름,입사일,월급,보너스,수령액,부서명
	//				num, name, start, sallary, bonus, income, pname
	// 입력
	private		int		num;
	private		String	name;
	private		int		start;
	private		double	sallary;
	private		int		part;
	// 출력
	private		double	bonus;
	private		double	income;
	private		String	pname;
	
	// constructor
	public ColVo(int num, String name, int start, double sallary, int part) {
		this.num = num;
		this.name = name;
		this.start = start;
		this.sallary = sallary;
		this.part = part;
	}
	// method

	@Override
	public String toString() {
		return "ColVo [num=" + num + ", name=" + name + ", start=" + start + ", sallary=" + sallary + ", part=" + part
				+ ", bonus=" + bonus + ", income=" + income + ", pname=" + pname + "]";
	}
	// getter, setter
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public double getSallary() {
		return sallary;
	}
	public void setSallary(double sallary) {
		this.sallary = sallary;
	}
	public int getPart() {
		return part;
	}
	public void setPart(int part) {
		this.part = part;
	}
	public double getBonus() {
		return bonus;
	}
	public void setBonus(double bonus) {
		this.bonus = bonus;
	}
	public double getIncome() {
		return income;
	}
	public void setIncome(double income) {
		this.income = income;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	
}

// 업무처리 클래스
class Colleague implements Ipo{
	// field
	private		ColVo			c;

	// method
	@Override
	public void input() {
		Scanner		in		=	new	Scanner(System.in); // 키보드로 입력 받을 것이다
		System.out.println("사번 이름 입사일 월급 부서번호"); // 어떤 data를 입력하라고 하는지 고시
		String			line	=	in.nextLine();
		String	[]		li		=	line.trim().split(",");
		int				num		=	Integer.parseInt(li[0].trim());
		String			name	=	li[1].trim();
		int				start	=	Integer.parseInt(li[2].trim());
		double			sallary	=	Double.parseDouble(li[3].trim());
		int				part	=	Integer.parseInt(li[4].trim());
		c						=	new ColVo(num, name, start, sallary, part);
		System.out.println(c);
	}

	@Override
	public void process() {
		//보너스   =  근무연수에 따라 월급의 0.5% 로 계산한다
		//수령액   =  월급 + 보너스
		//부서명   =  10:인사,20:자재,30:총무,40:연구개발,50:생산,60:서비스
		Date		now			=	new	Date();
		int			worky		=	0;
		int			joiny		=	c.getStart() / 10000;
		worky					=	now.getYear() + 1900 - joiny;
		
		c.setBonus(c.getSallary() * worky *0.005);
		c.setIncome(c.getSallary() + c.getBonus());
		String			pname	=	"";
		int				part	=	c.getPart();
		switch (part) {
		case 10 : pname="인사"; break;
		case 20 : pname="자재"; break;
		case 30 : pname="총무"; break;
		case 40 : pname="연구개발"; break;
		case 50 : pname="생산"; break;
		case 60 : pname="서비스"; break;
		
		default:
			throw new IllegalArgumentException("Unexpected value: " + part);
		}
		c.setPname(pname);
	}

	@Override
	public void output() {
		//출력     : 사번,이름,입사일,월급,보너스,수령액,부서명
		//		num, name, start, sallary, bonus, income, pname
		System.out.println(c);
		String			title		=	"사번 이름 입사일 월급 보너스 수령액 부서명";
		String			fmt			=	"%d %s %d %.2f %.2f %.2f %s\n";
		System.out.println(title);
		System.out.printf(fmt, c.getNum(), c.getName(), c.getStart(), c.getSallary(), c.getBonus(), c.getIncome(), c.getPname());
	}
	
	//
	
	
} // class Colleague end

public class HumanResource {

	public static void main(String[] args) {
		Colleague	c		=	new	Colleague();
			c.input();
			c.process();
			c.output();
			
	} // main end

} // class HumanResource end
