<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ page import="dbconnect.domain.FreshFishes"%>
<%@ page import="dbconnect.domain.FreshFish"%>
<%@ page import="java.util.List"%>
<!DOCTYPE HTML SYSTEM "about:legacy-compat">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="UTF-8">
<link
  href="https://fonts.googleapis.com/earlyaccess/notosansjapanese.css"
  rel="stylesheet" />
<link rel="stylesheet"
  href="${pageContext.request.contextPath}/css/main.css" />
<title>DB接続テスト</title>
</head>
<body>
  <header>
    <h1>DB接続テスト</h1>
  </header>
  <article>
    <table>
      <tr>
        <th>接続対象のテーブル名</th>
        <th>レコードの抽出条件</th>
      </tr>
      <tr>
        <td>fresh_fish</td>
        <td>魚種コード = 501 もしくは 魚種名 = いわし</td>
      </tr>
    </table>

    <form action="DatabaseAccessService" method="post">
      <input type="submit" name="ExecFindAll" value="DB からデータを取得する" />
    </form>
    <%
    	String message = (String) session.getAttribute("Message");
    	String errorMessage = (String) session.getAttribute("ErrorMessage");
    	if (message == null)
    		message = "";
    	if (errorMessage == null)
    		errorMessage = "";
    %>
    <p class="message"><%=message%></p>
    <p class="error-message"><%=errorMessage%></p>
    <%
    	if (session.getAttribute("FreshFishes") != null) {
    %>
    <p>上記条件でDBから抽出したレコードの詳細は以下の通りです。</p>
    <table>
      <tr>
        <th>鮮魚ID</th>
        <th>魚種コード</th>
        <th>魚種名</th>
        <th>産地</th>
        <th>価格</th>
      </tr>
      <%
      	FreshFishes fishes = (FreshFishes) session.getAttribute("FreshFishes");
      		int i = 0;
      		for (FreshFish fish : fishes.deepCopyListValue()) {
      %>
      <tr>
        <td><%=fish.freshFishIdValue()%></td>
        <td><%=fish.fishCodeValue()%></td>
        <td><%=fish.fishNameValue()%></td>
        <td><%=fish.districtValue()%></td>
        <td><%=fish.unitPriceValue()%>&nbsp;円</td>
      </tr>
      <%
      	}
      %>
    </table>
    <form action="SessionInvalidService" method="post">
      <input type="submit" name="ExecDeleteDisplayContents"
        value="表示結果をクリアする" />
    </form>
    <%
    	}
    %>
  </article>
</body>
</html>