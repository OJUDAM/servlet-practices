## jsp(c,v) model.1

Maven Project(부모)에 공통 부분 pom.xml 넣기

_utf-8 인코딩_

```xml
	<properties>
		<project.build.sourceEncoding>utf-8</project.build.sourceEncoding>
		<project.reporting.outputEncoding>utf-8</project.reporting.outputEncoding>
	</properties>
```

_리소스, 플러그인(maven, war)_

```xml
	<build>
		<sourceDirectory>src/main/java</sourceDirectory>
		
		<resources>
			<resource>
				<directory>${project.basedir}/src/main/resources</directory>
				<excludes>
					<exclude>**/*.java</exclude>
				</excludes>
			</resource>
		</resources>
		
		<plugins>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-compiler-plugin</artifactId>
				<version>3.8.0</version>
				<configuration>
					<source>1.8</source>
					<target>1.8</target>
				</configuration>
			</plugin>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-resources-plugin</artifactId>
				<configuration>
					<encoding>UTF-8</encoding>
				</configuration>
			</plugin>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-war-plugin</artifactId>
				<version>3.2.1</version>
				<configuration>
					<warSourceDirectory>src/main/webapp</warSourceDirectory>
					<failOnMissingWebXml>false</failOnMissingWebXml>
				</configuration>
			</plugin>
		</plugins>
		
	</build>
	
```
 부모 완료
 ***
 
 자식 프로젝트(모듈 war)에 pom.xml 수정 -의존성 주입
 
 ```xml
 	<dependencies>
		<dependency>
			<groupId>org.mariadb.jdbc</groupId>
			<artifactId>mariadb-java-client</artifactId>
			<version>2.6.2</version>
		</dependency>
	</dependencies>
 ```
 
 그리고 빌드 이름 설정-> localhost:8080/finalName
 
 ```xml
 	<build>
		<finalName>emaillist01</finalName>
	</build>
 ```
 
 그 다음 모듈에 가서 Deployment Descripor 오른쪽 클릭-> generate dpoyDeployment ...해서 web.xml파일 생성
 
 # DAO, VO
 
 DAO(Data Access Object) : 데이터베이스에 데이터에 sql문(DCL)을 써서 만진다.
 
 VO : DAO가 어떤 데이터를 가져갈지(parameter)를 클래스로 정의

***

jsp에서는 자바언어 쓸 때 <% %>안에 작성하면됨  

```jsp
	<%
		for(EmaillistVo vo : list){
	%>
```
값만 넣을 때는 <%= %>

```jsp
	<%=vo.getFirstName() %>
```

반복 할 구문을 잘 찾아서 <% %>로 감싸줌

```jsp
	<%
		for(EmaillistVo vo : list){
	%>
	<table border="1" cellpadding="5" cellspacing="2">
		<tr>
			<td align=right>First name:</td>
			<td><%=vo.getFirstName() %></td>
		</tr>
		<tr>
			<td align=right width="110">Last name:</td>
			<td width="110"><%=vo.getLastName() %></td>
		</tr>
		<tr>
			<td align=right>Email address:</td>
			<td><%=vo.getEmail() %></td>
		</tr>
	</table>
	<br>
	<%
		}
	%>
```

jsp utf-8 encoding  

```jsp
	<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
```

응답에대한 endcoding

```jsp
	<%
	request.setCharacterEncoding("UTF-8");
	~~
	%>
```
 
 