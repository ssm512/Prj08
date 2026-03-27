package ex021;

import java.time.LocalDateTime;
import java.util.Scanner;

interface	Ipo {
	void input();
	void process();
	void output();
}

class EmpVo {
	// field
	//입력data : 사번,이름,입사일,월급,부서번호
	// 			num, name, hiredate, salary, deptid
	private String num;
	private String name;
	private String hiredate;
	private double salary;
	private int deptid;
	//출력     : 사번,이름,입사일,월급,보너스,수령액,부서명
//		num, name, hiredate, salary, deptid, bonus, pay, deptname
	private double bonus;
	private double pay;
	private String deptname;
	
	// constuctor
	public EmpVo(String num, String name, String hiredate, double salary, int deptid) {
		this.num = num;
		this.name = name;
		this.hiredate = hiredate;
		this.salary = salary;
		this.deptid = deptid;
	}
	
	// getter, setter
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHiredate() {
		return hiredate;
	}
	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}
	public int getDeptid() {
		return deptid;
	}
	public void setDeptid(int deptid) {
		this.deptid = deptid;
	}
	public double getSalary() {
		return salary;
	}
	public double getBonus() {
		return bonus;
	}
	public double getPay() {
		return pay;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public void setBonus(double bonus) {
		this.bonus = bonus;
	}
	public void setPay(double pay) {
		this.pay = pay;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	// toString
	@Override
	public String toString() {
		return "EmpVo [num=" + num + ", name=" + name + ", hiredate=" + hiredate + ", salary=" + salary + ", deptid="
				+ deptid + ", bonus=" + bonus + ", pay=" + pay + ", deptname=" + deptname + "]";
	}
	
}

class Emp implements Ipo {
	
	private EmpVo		empvo;
	@Override
	public void input() {
		Scanner in	= new Scanner(System.in);
		System.out.println("입력:사번,이름,입사일,월급,부서번");
		String		line	=	in.nextLine();
		String	[]	li		=	line.trim().split(",");
		String		num		=	li[0].trim();
		String		name	=	li[1].trim();
		String		hiredate=	li[2].trim();
		double		salary		=	Double.parseDouble(li[3].trim());
		int		deptid		=	Integer.parseInt(li[4].trim());
		// 여기까지만 하면 Scanner in으로 받은 변수들이 input() {}를 벗어나면 다 없어짐
		//그래서 private EmpVo 만들어주는 거임
		empvo			=	new	EmpVo(num, name, hiredate, salary, deptid);
		System.out.println(empvo);
	}

	@Override
	public void process() {
		//금액은 소수이하 두자리로 반올림
		//보너스   =  근무연수에 따라 월급의 0.5% 로 계산한다
		int 		thisYear	=	LocalDateTime.now().getYear();
		int			hireYear	=	Integer.parseInt(empvo.getHiredate().substring(0,4));
		int			years		=	thisYear - hireYear; // 올해연도 - 입사연도
		double		salary		=	empvo.getSalary();
		double		bonus		=	years * salary * 0.005;
		empvo.setBonus(bonus);
		//수령액   =  월급 + 보너스
		double		pay			=	empvo.getSalary() + bonus;
		empvo.setPay(pay);
		//부서명   =  10:인사,20:자재,30:총무,40:연구개발,50:생산,60:서비스
		String	deptname	=	"";
		int		deptid		=	empvo.getDeptid();
		switch (deptid) {
		case 10: deptname = "인사"; 	break;
		case 20: deptname = "자재"; 	break;
		case 30: deptname = "총무"; 	break;
		case 40: deptname = "연구개발"; break;
		case 50: deptname = "생산"; 	break;
		case 60: deptname = "서비스"; 	break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + deptid);
		}
		empvo.setDeptname(deptname);
		System.out.println(empvo);
	}

	@Override
	public void output() {
		//출력     : 사번,이름,입사일,월급,보너스,수령액,부서명
		//		num, name, hiredate, salary, deptid, bonus, pay, deptname
		System.out.println("사번  이름    입사일        월급        보너스     수령액     부서명");
		String		fmt	=	"%s   %s   %s      %.2f       %.2f      %.2f      %s\n";
		System.out.printf(fmt, empvo.getNum(), empvo.getName(), empvo.getHiredate(), 
				empvo.getSalary(), empvo.getBonus(), empvo.getPay(), empvo.getDeptname());
	}
	
}

public class TestEmp {

	public static void main(String[] args) {
		Emp  e1		= new	Emp ();
		e1.input();
		e1.process();
		e1.output();

	}

}
