package main;
//LoanConstants.java

public interface LoanConstants
{
public static final int MAXLOAN = 50000;
public static final int SHORT_TERM = 1;
public static final int MEDIUM_TERM = 3;
public static final int LONG_TERM = 5;
public static final String COMPANY = "JPL Company";
}

_______________________a

//Loan.java

import java.text.DecimalFormat;

public abstract class Loan implements LoanConstants {
protected int loanNum;s
protected String lastName;
protected double amount;
protected double rate;
protected int term;

public Loan(int num, String name, double amt, int yrs) {
    this.loanNum=num;
    this.lastName=name;
    this.amount=amt;
    this.term=yrs;
}


public String toString() {
    // DecimalFormat class is used to format the output
            DecimalFormat df = new DecimalFormat(".0");
    String str="Loan #"+loanNum+" Name: "+lastName+" $"+df.format(amount)+" for "+term+" year(s)";
    return str;
}

public boolean equals(Loan loan) {
    if(this.loanNum==loan.loanNum && lastName.equals(loan.lastName) && amount==loan.amount && term==loan.term)
        return true;
    else
        return false;
}
}

____________________________

//BusinessLoan.java

public class BusinessLoan extends Loan {
private double interestRate;

public BusinessLoan(int num, String name, double amt, int yrs, double prime) {
    super(num, name, amt, yrs);
    this.interestRate = (prime*100) + 1;
}

@Override
public String toString() {
    return super.toString() + " at " + (int)interestRate + "% interest";
}


}

______________________________

//PersonalLoan.java

public class PersonalLoan extends Loan {
private double interestRate;

public PersonalLoan(int num, String name, double amt, int yrs, double prime) {
    super(num, name, amt, yrs);
    this.interestRate = prime*100 + 2;
}

@Override
public String toString() {
    return super.toString() + " at " + (int)interestRate + "% interest";
}


}
_______________________________

//CreateLoans.java

import java.util.*;

public class CreateLoans implements LoanConstants {
public static void main(String[] args) {
    double primeInterestRate;

    /*
    * Creating an Scanner class object which is used to get the inputs
    * entered by the user
    */
    Scanner sc = new Scanner(System.in);
    int choice,accNo,term;
    String name;
    double loanAmt;
    System.out.println("Welcome to "+LoanConstants.COMPANY);
    System.out.println("Enter the current prime interest rate as a decimal number, for example, .05");
    primeInterestRate=sc.nextDouble();
    Loan loans[]=new Loan[5];
    for(int i=0;i<loans.length;i++)
    {
        System.out.println("Is this a (1) Business loan or (2) Personal loan");
        choice=sc.nextInt();
        if(choice==1)
        {
       
            System.out.println("Enter account number");
            accNo=sc.nextInt();
            System.out.println("Enter name:");
            name=sc.next();
       
            while(true)
            {
                System.out.println("Enter loan amount");
                loanAmt=sc.nextDouble();
                if(loanAmt>LoanConstants.MAXLOAN)
                {
                    System.out.println("Invalid.Must be <="+LoanConstants.MAXLOAN);
                }
                else
                    break;
            }

       
while(true)
{
            System.out.println("Enter term:");
            term=sc.nextInt();
            if(term!=LoanConstants.SHORT_TERM && term!=LoanConstants.MEDIUM_TERM && term!=LoanConstants.LONG_TERM)
            {
                System.out.println("** Invalid.Must be either 1 or 3 or 5 **");
            }
            else
            {
                break;
            }
}

            BusinessLoan bl=new BusinessLoan(accNo, name,loanAmt,term, primeInterestRate);
            loans[i]=bl;
        }
        else if(choice==2)
        {
   
            System.out.println("Enter account number");
            accNo=sc.nextInt();
            System.out.println("Enter name:");
            name=sc.next();
           
                System.out.println("Enter loan amount");
                loanAmt=sc.nextDouble();
       

            System.out.println("Enter term:");
            term=sc.nextInt();
            PersonalLoan pl=new PersonalLoan(accNo, name,loanAmt,term, primeInterestRate);
            loans[i]=pl;
        }
    }
   
    System.out.println("\n");
    System.out.println(LoanConstants.COMPANY);
    for(int i=0;i<loans.length;i++)
    {
        System.out.println(loans[i]);
    }
   
}
}