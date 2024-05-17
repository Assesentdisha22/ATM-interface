import java.io.*;
import java.util.*;

class Account {
    static int acc_number = 1111;
    String acc_holder_name;
    int pin ;
    double balance;
    String unique_id;
    int a_no;

    void createAcc(){
        a_no = acc_number ;
        Scanner sc = new Scanner(System.in);
        System.out.println("enter account holder name ");
        acc_holder_name = sc.nextLine();
        System.out.println("\nenter username");
        unique_id = sc.nextLine();
        int length_pin = 0 ;
        do{
            System.out.println("enter your 4 digit pin ");
            pin = sc.nextInt();
            length_pin = String.valueOf(pin).length();
        }while(length_pin != 4);
        System.out.println("enter intitial deposit amount ");
        balance = sc.nextDouble();
        System.out.println("Cogratulation Your Account Successfully Created \n");
        System.out.println("Account detail -\n" + "Account number "+ a_no +"\n Account holder name "+ acc_holder_name + "\n balance"+ balance+"\n thank you ");
        // CREATE A FILE WITH THE ACCOUNT NUMBER 
        String fileName = acc_number +".txt";
        File file = new File(fileName);
        try {
            file.createNewFile();
            FileWriter  writer = new FileWriter(file);
            writer.write("Account Create \n");
            writer.write("Account Number :" + acc_number +"\n");
            writer.write("USER ID-r:"+ unique_id +"\n");
            writer.write("Account Holder Name"+ acc_holder_name + "\n");
            writer.write("PIN"+pin+"\n");
            writer.write("Balance"+balance+"\n");
            writer.write("Date :"+new Date()+"\n\n\n");
            writer.close();

        }
        catch(Exception e){
            System.out.println("An error occurred while creating the file "+ fileName );
            e.printStackTrace();
        }
        try {
            Thread.sleep(5000);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        acc_number++;
    }
}
class ATM{
    void withdraw(Account acc ){
        Scanner sc = new Scanner(System.in);
        System.out.println("\033[H\033[2J");
        System.out.flush();
        System.out.println("Withdraw monay mode\n");
        System.out.println("Enter amount in multeple of 100 -");
        double amount = sc.nextDouble();
        if(amount % 100 ==0 ){
         if (acc.balance>= amount ){
           acc.balance -= amount ;
           System.out.print("your transaction is processing \n") ;
           try {
            String fileName = acc.a_no + ".txt ";
            FileWriter fileWriter = new FileWriter(fileName , true );
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write("Date:"+ new Date() +"\n");
            bufferedWriter.write("Withdraw :"+ amount +"\n");
            bufferedWriter.write("Account Number :"+ acc.a_no +"\n");
            bufferedWriter.write("Remaining Balance :"+ acc.balance +"\n\n");
            
            bufferedWriter.close();
            fileWriter.close();  
        } catch (IOException e ){
            System.out.println("An error occurred while writin to the file .");
            e.printStackTrace();
        }
        System.out.println("thankyou for banking with us! ");
        try{
            Thread.sleep(6000);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.print("\033[H\033[2J");
        System.out.flush();
         } 
         else{
            System.out.println("Insufficient Funds ");
         } 
        }else{
            System.out.println("Amount not in multiples of 100!");
            System.out.println("try again!");
        }
    }
       void deposit_by_transfer(Account acc,double amount ){
        acc.balance += amount ;
        try{
            
            String fileName = acc.a_no + ".txt ";
            FileWriter fileWriter = new FileWriter(fileName , true );
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write("Deposit :"+ amount  +"\n");
            bufferedWriter.write("Withdraw :"+ new Date() +"\n");
            bufferedWriter.write("Account Number :"+ acc.a_no +"\n");
            bufferedWriter.write("Remaining Balance :"+ acc.balance +"\n\n");
            
            bufferedWriter.close();
            fileWriter.close();  

        }catch (IOException e){
            System.out.println("An error occurred while writing to the file. ");
            e.printStackTrace();
        }
       }
       void deposit(Account acc ){
        Scanner sc = new Scanner(System.in);
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("monay deposit Mode\n");
        System.out.println("Enter amount you want to deposit ?");
        double amount =sc.nextDouble();
        acc.balance += amount;
        System.out.println("\033[H\033[2J");
        System.out.flush();
        try{
            String fileName = acc.a_no + ".txt ";
            System.out.println("the file name -"+ fileName );
            FileWriter fileWriter = new FileWriter(fileName , true );  
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write("Deposit :"+ amount  +"\n");
            bufferedWriter.write("Withdraw :"+ new Date() +"\n");
            bufferedWriter.write("Account Number :"+ acc.a_no +"\n");
            bufferedWriter.write("Remaining Balance :"+ acc.balance +"\n\n");
            
            bufferedWriter.close();
            fileWriter.close();  
        }
        catch (IOException e){
            System.out.println("An error occurred while writing to the file. ");
            e.printStackTrace();
        }
        System.out.println("processing your request ! please Wait ");
        try {
            Thread.sleep(5000); 
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("transaction complite succesfully ");
        System.out.println("\n\n thankyou for Banking with us  ");
        System.out.println("going to loading page  ");
        try {
            Thread.sleep(5000); 
        }catch (Exception e){
            e.printStackTrace();
        }
    } 
    void Transfer(Account acc1, Account acc2, double amount) {

        if (acc1.balance >= amount) {
             acc1.balance -= amount;
        
        ATM a = new ATM();
        
        a.deposit_by_transfer (acc2, amount);
        try {
            String fileName = acc1.a_no + ".txt ";
            FileWriter fileWriter = new FileWriter(fileName , true );
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write("transfered:"+ amount  +"\n");
            bufferedWriter.write(" Date :"+ new Date() +"\n");
            bufferedWriter.write("Account Number :"+ acc1.a_no +"\n");
            bufferedWriter.write("Remaining Balance :"+ acc1.balance +"\n\n");
            
            bufferedWriter.close();
            fileWriter.close();  

        }catch (IOException e){
            System.out.println("An error occurred while writing to the file. ");
            e.printStackTrace();
        } 
        System.out.println("processing your request ,please wait \n");
        try {
            Thread.sleep(5000); 
        }catch (Exception e){
            e.printStackTrace();
        } 
        }
        }
        void display_details(Account acc ){
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("Display amount Detail\n ");
            try {
                Thread.sleep(2000); 
            }catch (Exception e){
                e.printStackTrace();
            } 
            String file_name = String .valueOf(acc.a_no) +".txt";
            File file = new File(file_name);
            try{
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String line = null;
                while ((line = bufferedReader.readLine ()) !=null){
                    System.out.println(line);
                } 
                bufferedReader.close(); 
            } catch(FileNotFoundException e){
                System.out.println("file nou t fond :"+e.getMessage());
            }catch(IOException e){
                System.out.println("error reading file :"+e.getMessage());
            }
            System.out.println("Screen will automatically timeout after 30m second ....");
            try {
                Thread.sleep(3000); 
            }catch (Exception e){
                e.printStackTrace();
            } 
        }
        void quit(){
            System.out.print("thank you for banking with us !!\n");
            exit() ;
        }
    }
class run_atm{
    int account_search_by_unique_id(Account[] ac , String  unique_id){
     for (int i=0;i<5;i++){
        if (Objects.equals(unique_id ,ac[i].unique_id))
        return i;
     }
     return -1;
    }
    int account_search_by_unique_id(Account[] ac,int account_number){
        for(int i=0 ;i<5;i++){
            if (Objects.equals(account_number ,ac[i].a_no))
            return i;
        }
        return -1;
    }
    void demo(Account [] ac) {
        Scanner sc = new Scanner(System.in);
        System.out.print("\n\n\nWelcome to ATM\n");
        System.out.println("\nEnter your card/unique ID");
        String unique_id = sc.nextLine();
        int i = account_search_by_unique_id(ac ,unique_id);
        if(i ==-1){
            System.out.println("Account not registered yet !");
            try {
                Thread.sleep(3000); 
            }catch (Exception e){
                e.printStackTrace();
            } 
            return;  
        } else{
            System.out.println("enter your pin ");
            int pin = sc.nextInt();
            if (pin ==ac[i].pin){
                System.out.println("Select the Option as given below !\nwithdeaw.---1\nDeposit ?----2\nAccount transfer ?---3\nDisplay account DetAILS?---4\nquit----5");
                int ch ;
                ATM atm = new ATM();
                ch = sc.nextInt();
                switch(ch){
                    case 1: atm.withdraw(ac[i]); break;
                    case 2 : atm.deposit(ac[i]); break;
                    case 3 :{
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        System.out.println("enter account number to transfer funds? ");
                        int account_transfer = sc.nextInt();
                    int j = account_search_by_unique_id(ac , account_transfer);
                if(j == -1 ){
                    System.out.println("account not yet registered!");
                    return;
                }else{
                    System.out.println("enter amount for transferring funds?");
                    double amount = sc.nextDouble();
                    atm.Transfer(ac[i], ac[j], amount);  
                }
                         }
                         break;
                         case 4: atm.display_details(ac[i]); break;
                         case 5 :atm.quit();
                }
            }
                else {
                    System.out.println("Wrong PIN Entered !\n");
                    try {
                        Thread.sleep(3000); 
                    }catch (Exception e){
                        e.printStackTrace();
                    } 
                    return ;
                }
            }
        }
    }
    
public class hellow {

    public static void main (String arg[]) throws Exception{
        Scanner sc = new Scanner(System.in);
        Account[] ac = new Account [5];
        for (int i=0; i<2 ;i++){
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("Creating Account mode-\n ");
            ac[i] = new Account();
            ac[i].createAcc();
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
        run_atm ob = new run_atm();

        for(; ; ){
            System.out.print("\033[H\033[2J");
            System.out.flush();
            ob.demo(ac);
        }
    }
}
         
        
    
