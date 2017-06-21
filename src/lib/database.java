package lib;
import java.sql.*;


public class database  {
    
    Connection con;
    Statement st;
    ResultSet rs,rs1,rs2,rs3;
    	
    public database()
    {
        
    
      try
      {
          
          con=DriverManager.getConnection("jdbc:mysql://localhost/mydb", "saiteja", "2411");
          st=(Statement) con.createStatement();
        
      }
      catch(Exception e){
          e.printStackTrace();
          }
    }
    
      public String[] submit(String s1)
      {
          
          long a=Integer.parseInt(s1);
          String s="select * from stud where stdno="+a+";";
          String q[]=new String[12];         
         
         try{
             con=DriverManager.getConnection("jdbc:mysql://localhost/mydb", "saiteja", "2411");
          st=(Statement) con.createStatement();
             rs=st.executeQuery(s);
            while(rs.next())
            {
                
              q[1]=rs.getString(1);
              q[2]=rs.getString(2);
              q[3]=rs.getString(3);
              q[4]=rs.getString(4);
              q[5]=rs.getString(5);
              q[6]=rs.getString(6);
              q[7]=rs.getString(7);
              q[8]=rs.getString(8);
              q[9]=rs.getString(9);
            }
          
         }
         catch(Exception e)
         {
             e.printStackTrace();
         }
        return (q);
         
      }
      
      public int check(String s)
      {
        String s1="select availability from book where bookname='"+s+"';";
        int a=0;
        try
        {
        rs=st.executeQuery(s1);
        while(rs.next())
        {
        a=rs.getInt(1);
        }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return a;
        }
      
      public int issue(String s,int q, int a)
      {
       int k=0;
       int flag=0;
       String s2="update book set availability="+(a-1)+" where bookname='"+s+"';";
       String s3="select available from stud where stdno="+q+";";
       
       try
       {
           rs=st.executeQuery(s3);
           while(rs.next())
           {
               k=rs.getInt(1);
           }
           if(k==1) {
               st.executeUpdate("update stud set book2='"+s+"',available="+(k+1)+",issue2=current_date where stdno="+q+";");
           }
           if(k==2){
               st.executeUpdate("update stud set book3='"+s+"',available="+(k+1)+",issue3=current_date where stdno="+q+";");      
           }
           if(k==0){
               st.executeUpdate("update stud set book1='"+s+"',available="+(k+1)+",issue1=current_date where stdno="+q+";");
           }
           st.executeUpdate(s2);
           flag=1;
       }
       catch(Exception e)
       {
           e.printStackTrace();
       }
       if(flag==1)
       {
           return 1;
       }
       else 
       {
           return 0;
       }
      
      
      }
      
      
      public int returnn(int i, int j)
      {
          int flag1=0;
          int k1=0;
          int t=0;
          int s=0;
          String x="";
          String q1[]=new String[13];  
        String f=new String();
        f=" ";
         
          try
          {
              rs=st.executeQuery("select availability from book where bookid="+i+";");
              while(rs.next())
              {
                  k1=rs.getInt(1);
              }
              t=k1+1;
              st.executeUpdate("update book set availability="+t+" where bookid='"+i+"';");
              rs1=st.executeQuery("select bookname from book where bookid="+i+";");
              while(rs1.next())
              {
                  x=rs1.getString(1);
              }
              rs2=st.executeQuery("select book1,book2,book3,available,issue1,issue2,issue3 from stud where stdno="+j+";");
              while(rs2.next())
              {
                 if(rs2.getString(1) == null) 
                 {
                      q1[1]="0";
                  }
                 else
                 {
                      q1[1]=rs2.getString(1);
                  }//bok1
                 if(rs2.getString(2) == null)
                 {
                      q1[2]="0";
                  }
                 else 
                 {
                      q1[2]=rs2.getString(2);
                  }//bok2
                 if(rs2.getString(3) == null) 
                 {
                      q1[3]="0";
                  }
                 else
                 {
                      q1[3]=rs2.getString(3);
                  }//bok3
                 if(rs2.getString(4) == null) 
                 {
                      q1[4]="0";
                  }
                 else 
                 {
                      q1[4]=rs2.getString(4);
                  }//available
                 if(rs2.getString(5) == null) 
                 {
                      q1[5]="0";
                  }
                 else 
                 {
                      q1[5]=rs2.getString(5);
                  }//issue1
                 if(rs2.getString(6) == null)
                 {
                      q1[6]="0";
                  }
                 else 
                 {
                      q1[6]=rs2.getString(6);
                  }//issue2
                 if(rs2.getString(7) == null)
                 {
                      q1[7]="0";
                  }
                 else
                 {
                      q1[7]=rs2.getString(7);
                  }//issue3
              }
              rs3=st.executeQuery("select issue3 from stud where stdno="+j+";");
              while(rs3.next())
              {
                  f=rs3.getString(1);
              }
                  s=Integer.parseInt(q1[4]);
                  if(x.equals(q1[1]))
                  {
                      if(!("0".equals(q1[2]))&&("0".equals(q1[3]))) {
                          st.executeUpdate("update stud set book1='"+q1[2]+"',book2=NULL,book3=NULL, available="+(s-1)+",issue1='"+q1[6]+"',issue2=NULL,issue3=NULL where stdno="+j+";");
                      }
                      else if(!("0".equals(q1[2]))&&!("0".equals(q1[3]))) {
                          st.executeUpdate("update stud set book1='"+q1[2]+"',book2='"+q1[3]+"',book3=NULL, available="+(s-1)+",issue1='"+q1[6]+"',issue2='"+q1[7]+"',issue3=NULL where stdno="+j+";");
                      }
                      else if(("0".equals(q1[2]))&&("0".equals(q1[3]))) {
                          st.executeUpdate("update stud set book1=NULL, available="+(s-1)+",issue1=NULL where stdno="+j+";");
                      }
                  }
                  else
                  if(x.equals(q1[2]))
                  {
                      
                       if("0".equals(q1[3])) {
                          st.executeUpdate("update stud set book2=NULL,book3=NULL, available="+(s-1)+",issue2=NULL,issue3=NULL where stdno="+j+";");
                      }
                       else if(!("0".equals(q1[3]))) {
                           st.executeUpdate("update stud set book2='"+q1[3]+"',book3=NULL,available="+(s-1)+", issue2=NULL where stdno="+j+"");
                          st.executeUpdate("update stud set issue2='"+f+"',issue3=NULL where stdno="+j+";");
                      }
                      
                  }      
                  
                  else if(x.equals(q1[3]))
                  {
                      st.executeUpdate("update stud set book3=NULL, available="+(s-1)+",issue3=NULL where stdno="+j+";");
                  }
                
              flag1=1;
              
              
              
          }
          catch(Exception e)
          {
              e.printStackTrace();
          }
          return flag1;
          
      }
      
      
    
   public int register(int i, String j)
   {
       int flag=0;
       String s3="insert into stud(stdno,stdname,available) values("+i+",'"+j+"',0);";
       try
       {
           st.executeUpdate(s3);
           flag=1;
       }
       catch(Exception e){
           e.printStackTrace();
       }
       return flag;
   }
    
    

}
