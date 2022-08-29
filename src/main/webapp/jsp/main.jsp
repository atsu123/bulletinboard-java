<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 
CSS読み込み
<%=request.getContextPath() %> プロジェクト名をゲット
 -->
 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">

<!--　HTML記述 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!-- javascript記述 -->
<script type="text/javascript">

function check(){
    var flag = 0;

    if(document.form1.name.value == ""){
        flag = 1;
    }
    else if(document.form1.comment.value == ""){
        flag = 1;
    }

    if(flag){
        window.alert('名前とコメントを入力してください'); 
        return false; // 送信を中止
    }
    else{
        return true; // 送信を実行
    }
}
</script>

</head>
<body>
<h2>掲示板</h2>

<form action="/bulletinboard/BoardServlet" method="post" name="form1" onSubmit="return check()"><p>
<label><input type="radio" name="name" id="name">名前:<input type="text" value=" " id ="name_box" disabled /></label>
<!-- 匿名機能（チェックボックス） -->
<label><input class="check" type="radio" name="name" id="tokumei" checked>匿名</label>
</p>

<script type="text/javascript">
<!-- イベントが発生した時の処理 -->
function disablecheck(){
	let rd1 = document.getElementById("name");
	var box_elem = document.getElementById("name_box");
	/* rd1にチェックが入ってたらテキストボックスを有効 */
	if(rd1.checked){
		box_elem.disabled = false;
	/* rd1にチェックが入ってたらテキストボックスを無効 */
	}else {
		box_elem.disabled = true;
	}
}

</script>

<p>コメント:<br>
<textarea name="comment" rows="5" cols="40"></textarea>
</p>
<p><input type="submit" value="送信"><input type="reset" value="リセット">
</p>
</form>

<c:forEach var="list" items="${listAttribute}">
<p>ID:<c:out value="${list.id}"/>　名前:<c:out value="${list.name}"/>　日付:<c:out value="${list.time}"/><br>
<c:out value="${list.comment}"/></p>
</c:forEach>

</body>
</html>