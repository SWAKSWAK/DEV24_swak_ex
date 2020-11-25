<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title><tiles:getAsString name="title" /></title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
		
		<!-- 모바일 웹 페이지 설정 -->
		<link rel="shortcut icon" href="/resources/image/icon.png" />
		<link rel="apple-touch-icon" href="/resources/image/icon.png" />
		
		<!--IE8이하 브라우저에서 HTML5를 인식하기 위해서는 아래의 패스필터를 적용하면 된다.(조건부주석) -->
		<!--[if lt IE 9]>
			<script src="/resources/js/html5shiv.js"></script>
		<![endif]-->
		
		<link rel="stylesheet" href="/resources/include/css/style_boot.css" />
		<link rel="stylesheet" href="/resources/include/css/style_headerfooter.css" />
		<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
		<script src="/resources/include/js/jquery-1.12.4.min.js"></script>
    	<script src="/resources/include/js/jquery-3.5.1.min.js"></script>
		<script src="https://kit.fontawesome.com/a333e3670c.js" crossorigin="anonymous"></script>
		<script>
        $(function(){
           // gnb 메뉴 클릭 시
           $("#gnb > li").click(function(){
               var i = $(this).index();
               console.log(i);
               
               $(this).siblings("li").removeClass("on");
               $(this).addClass("on");
               
               $("#gnb > li > ul").removeClass("on");
               $("#gnb > li > ul").eq(i).addClass("on");
           });

            // 하위메뉴 마우스 커서 이동으로 메뉴 이동
            $(".dropmenu > li").mouseover(function(){
               $(this).siblings("li").removeClass("on");
               $(this).addClass("on");
           });
            $(".dropmenu").mouseleave(function(){
               $(".dropmenu > li").removeClass("on");
                $("#gnb > li > ul").removeClass("on");
           });
          
            var c_num = "${login.c_num}";
            $("#util > li").click(function(){
            	var id = $(this).attr("id");
            	if(id != "toJoin"){
            		if(c_num == ""){
            			alert("로그인 후 이용해주세요.");
            			location.href="/customer/login";
            		}
            	}
             });
            
            $("#h_searchBtn").click(function(){
            	search();
            });
            
	 		//로그인시 엔터키 기능 작동
	 		$("#h_searchtext").on("keypress", function(){
	 			if(e.keyCode == "13"){
					search();
	 			}
	 		});
        });
        
		function search(){
			
			var b_searchSelect = $("#searchSelect").val();
			var b_searchKeyword = $("#searchKeyword").val();
			console.log(b_searchKeyword);
			console.log(b_searchSelect);
			
			if(!chkData("#searchKeyword", "검색어를")) return;
			
			$("#b_searchSelect").val(b_searchSelect);
			$("#b_searchKeyword").val(b_searchKeyword);
			$("#b_sort").val("");
			$("#startPage").val(1);
			$("#page").val(1);
			$("#b_stateKeyword").val("all");
			
			goURL("00");
		};
		
		//패이지 이동 URI값 조합 함수
		function goURL(category){
			var url = "/book/"+category;
			$("#goURL").attr({
					"method" : "get",
					"action" : url
			});
			
			$("#goURL").submit();
		};
    </script> 
	</head>
	<body>
		<tiles:insertAttribute name="header" />
		<tiles:insertAttribute name="body" />
		<tiles:insertAttribute name="footer" />
	</body>
</html>