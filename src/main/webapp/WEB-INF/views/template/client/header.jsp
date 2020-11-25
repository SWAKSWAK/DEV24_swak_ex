<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page trimDirectiveWhitespaces="true" %>
		
<script src="/resources/include/js/common.js"></script>
<style>
	#afterLogin{
		clear:both;
		margin-top:10px;
	}
</style>
<script>
	$(function(){
		
		$("#h_searchmenu").val($("#b_searchSelect").val()).attr("selected", "true");
		
		$("#h_searchtext").val($("#b_searchKeyword").val());	
		
		
		$("#h_searchBtn").click(function(){
			search();
		});
		
		//로그인시 엔터키 기능 작동
		$("#h_searchtext").keydown(function(key){
			if(key.keyCode == "13"){
				search();
			}
		});
	});
		
	function search(){
	
		var b_searchSelect = $("#h_searchmenu").val();
		var b_searchKeyword = $("#h_searchtext").val();
		console.log(b_searchKeyword);
		console.log(b_searchSelect);
		
		if(!chkData("#h_searchtext", "검색어를")) return;
		
		$("#b_searchSelect").val(b_searchSelect);
		$("#b_searchKeyword").val(b_searchKeyword);
		$("#b_sort").val("");
		$("#startPage").val(1);
		$("#page").val(1);
		$("#b_stateKeyword").val("all");
		
		goPage("00");
	};
	
	//패이지 이동 URI값 조합 함수
	function goPage(category){
		var url = "/book/"+category;
		$("#goPage").attr({
				"method" : "get",
				"action" : url
		});
		
		$("#goPage").submit();
	};
</script>
	<header>
		<form id="goPage" name="goPage">
			<input type="hidden" name="page" id="page" value="${pagination.page}"/>
			<input type="hidden" name="startPage" id="startPage" value="${pagination.startPage}" />
			<input type="hidden" name="endPage" id="endPage" value="${pagination.endPage}" />
			<input type="hidden" name="pageLength" id="pageLength" value="${pagination.pageLength}" />
			<input type="hidden" name="range" id="range" value="${pagination.range}" />
			<input type="hidden" name="listRange" id="listRange" value="${pagination.listRange}" />
			<input type="hidden" name="b_sort" id="b_sort" value="${ pagination.b_sort }" />
			<input type="hidden" name="b_searchKeyword" id="b_searchKeyword" value="${ pagination.b_searchKeyword }"/>
			<c:if test="${ not empty pagination.b_searchSelect }">
				<input type="hidden" name="b_searchSelect" id="b_searchSelect" value="${ pagination.b_searchSelect }"/>
			</c:if>
			<c:if test="${ empty pagination.b_searchSelect }">
				<input type="hidden" name="b_searchSelect" id="b_searchSelect" value="all"/>
			</c:if>
		</form>
        <div id="header_wrap">
            <div id="logo"><a href="/"><img src="/resources/image/logo.png" alt="로고"></a></div>
			 
           <nav>
		      <ul id="gnb">
                <li class="dropBox" id="book">
                    <span>일반도서</span>
                    <ul class="dropmenu">
                      <li><a class="bookLink" id="" href="/book/10">일반도서 전체</a></li>
                      <li class=""><a href="/book/11">프로그래밍 언어</a></li>
                      <li><a href="/book/12">OS/데이터베이스</a></li>
                      <li><a href="/book/13">웹사이트</a></li>
                      <li><a href="/book/14">컴퓨터 입문/활용</a></li>
                      <li><a href="/book/15">네트워크/해킹/보안</a></li>
                    </ul> <!-- dropmenu for book -->
                </li>
		        <li class="dropBox" id="ebook">
		            <span>eBook</span>
		          <ul class="dropmenu">
		          	  <li><a class="bookLink" href="/book/20">eBook 전체</a></li>
                      <li><a href="/book/26">IT전문서</a></li>
                      <li><a href="/book/27">컴퓨터 수험서</a></li>
                      <li><a href="/book/28">웹/컴퓨터 입문&활용</a></li>
                </ul> <!-- dropmenu for ebook -->      
		      </li>
		        <li>
		        	<span>커뮤니티</span>
		        	<ul class="dropmenu">
		        		<li><a href="/freeboard/freeboardList">자유게시판</a></li>
		        		<li><a href="/ne/neList">공지사항/이벤트</a></li>
		        	</ul>
		        </li> <!-- 자유게시판, 공지사항/이벤트 -->
		        <li><a href="/faq/faqMain">고객지원</a></li> <!-- 주문내역/배송조회, QnA, FAQ -->
		      </ul> <!-- gnb -->
		      
		      <ul id="util">
				<c:if test="${empty login.c_id}">
				    <li id="toLogin"><a href="/customer/login"><i class="far fa-user"></i>로그인</a></li>
				    <li id="toJoin"><a href="/customer/join"><i class="fas fa-user-tie"></i>회원가입</a></li>
			    </c:if>
			    <c:if test="${not empty login.c_id}">
				    <li id="toJoin"><a href="/customer/logout"><i class="fas fa-user-tie"></i>로그아웃</a></li>
				    <li id="toMypage"><a href="/mypage/mypage"><i class="fas fa-user-tie"></i>마이페이지</a></li>
				    <li><a href="/cart/cartList"><i class="fas fa-shopping-cart"></i>장바구니</a></li>
				    
				    <li id="afterLogin"><strong>[ ${login.c_nickname}] 님 반갑습니다.</strong></li>
			    </c:if>
			    
			    
			 </ul> <!-- util -->
			   
		    </nav> <!-- nav -->
       
           <div id="h_search">
	               <div id="h_search_select">
	                   <select name="h_searchmenu" id="h_searchmenu">
							<option value="all" selected="selected">전체검색</option>
							<option value="b_name">책제목</option>
							<option value="b_author">저자</option>
							<option value="b_pub">출판사</option>
							<option value="b_info">책정보</option>
	                   </select>
	               </div>
	              	 <div id="h_search_input"><input type="text" name="" id="h_searchtext" placeholder="검색어를 입력하세요." /></div>
	               <button type="button" id="h_searchBtn"><i class="fas fa-search"></i></button>
           </div><!-- search -->
        </div><!-- header_wrap -->
    </header>