<%@page
 contentType="text/html; charset=UTF-8" 
 pageEncoding="UTF-8"%>


 <ol>
    <li>(1) Declaration Tags(선언태그) : <p/>
            -가. 인스턴스 필드 선언:
                <%! private String name = "Tae";%><br>
            -나. 인스턴스 필드 선언: 
                <%! private int age =27; %> <br>
                <%! private boolean isMale =true; %> <br>

            -다. 정적 필드 선언 :
                <%! private static double PI = 3.14159; %><br>
            -라. 인스턴스 메소드 선언:
            
                <%! public void instanceMethod(){
            		System.out.println("instanceMethod() invoked.");
            	} %><br>
            	
                <%! public String getName(){
                    return this.name;
                }
                %>

                <%! public int getAge(){
                    return this.age;
                }
                %>
                <%! public boolean isMale(){
                    return this.isMale;
                } %><br>
            -마. 정적 메소드 선언:
                <%! public static void staticMethod(){
                    System.out.println("staticMethod() invoked.");
                }
                %><br>
    </li>

    <li>(2) Scriptlet Tag: Code Snippet 선언 - JSP에 자바코드 삽입 <p/>
        -가. 인스턴스 필드 출력: <% out.println(this.name); %><br>
        -나. 인스턴스 필드 값 변경: <% ++this.age; %><br>
        -다. 정적 필드 출력: <% out.println(PI); %><br>
        -라. 인스턴스 메소드 호출: <% this.instanceMethod(); %><br>
        -마. 정적 메소드 호출: <% staticMethod(); %><br>
        -바. for문 수행: <% 
                            for(int dansu=2; dansu<= 9; ++dansu){ 
						%>
							<br>===<%= dansu %> dan====<br>
						<%	
                                for(int operand=1; operand<=9; operand++){
                        %>   
                        	  <%= dansu %> x <%= operand %> =  <%= dansu * operand %> <br>
                        
                        <%       
                                }
                            }
                        %><br>
        -사. Switch Expression 수행 및 출력: 
        							<%
        
                               		int mode =new java.util.Random().nextInt(1,6);
        
							        switch(mode){
									case 1: %> <span style="font-size: 70px"> <%= 1+2 %> </span><% break;
									case 2: %> <span style="font-size: 70px"><%= 1*2 %> </span><%	break;
									case 3: %> <span style="font-size: 70px"><%= 1-2 %> </span><%	break;
									case 4: %> <span style="font-size: 70px"><%= 1%2 %> </span><%	break;
									default: %><span style="font-size: 70px"> <%= 1/2 %> </span><%
									}

                                  	%><br>

        -아. If문 수행 및 출력: 
        						<%
                                    if(isMale) %> <%= isMale %> ; 
                                    <%= isMale %> ; 
                                <br>
                            
        
    </li>

    <li>(3) Expression Tag: 표현식의 평가값 출력<p/>

        -가. 인스턴스 필드를 이 자리에 출력: <%= this.name %> <br>
        -나. 인스턴스 필드를 이 자리에 출력: <%= this.age %> <br>
        -다. 인스턴스 필드를 이 자리에 출력: <%= this.isMale %> <br>
        -라. 정적 필드를 이 자리에 출력: <%= PI %> <br>
        -마. 리턴 타입이 있는 인스턴스 메소드 호출 값 출력: <%= this.getName() %> <br>
        -바. 리턴 타입이 있는 인스턴스 메소드 호출 값 출력: <%= this.getAge() %> <br>
        -사. 리턴 타입이 있는 인스턴스 메소드 호출 값 출력: <%= this.isMale() %> <br>
        -아. Expression 출력: <%= 1 + 2 * 3 / 4 % 5 %><br>
        


    </li>

 </ol>