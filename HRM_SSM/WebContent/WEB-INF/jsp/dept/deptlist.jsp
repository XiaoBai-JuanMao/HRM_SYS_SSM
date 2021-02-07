<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>人事管理系统 ——部门管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
<meta http-equiv="description" content="This is my page" />
<link href="${ctx}/css/css.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/js/ligerUI/skins/Aqua/css/ligerui-dialog.css" />
<link href="${ctx}/js/ligerUI/skins/ligerui-icons.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery-migrate-1.2.1.js"></script>
<script src="${ctx}/js/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="${ctx}/js/ligerUI/js/plugins/ligerDrag.js"
	type="text/javascript"></script>
<script src="${ctx}/js/ligerUI/js/plugins/ligerDialog.js"
	type="text/javascript"></script>
<script src="${ctx}/js/ligerUI/js/plugins/ligerResizable.js"
	type="text/javascript"></script>
<link href="${ctx}/css/pager.css" type="text/css" rel="stylesheet" />

<script type="text/javascript">

	function toPage(pageNum){
		$("#pageNum").attr("value",pageNum);
	    $("#deptform").attr("action", "${ctx}/dept/list.action");
		$("#deptform").submit();
	}
	
	function pagerJump(){
		var page_size=$('#pager_jump_page_size').val();
			
		var regu = /^[1-9]\d*$/;
			
		if (!regu.test(page_size)||page_size < 1 || page_size >"${pageModel.pages}"){
			alert("请输入[1-${pageModel.pages}]之间的页码！");
		}else{
 		    $("#pageNum").attr("value",page_size);
	        $("#typeform").attr("action", "${ctx}/document/list.action");
	    	$("#typeform").submit();
		}
	}
	
	$(function(){
		/** 获取上一次选中的部门数据 */
		var boxs  = $("input[type='checkbox'][id^='box_']");
		 
		/** 给全选按钮绑定点击事件  */
		$("#checkAll").click(function(){
		    // this是checkAll  this.checked是true
		    // 所有数据行的选中状态与全选的状态一致
		    boxs.attr("checked",this.checked);
		})
	   
	   	/** 给每个数据行绑定点击事件：判断如果数据行都选中全选也应该选中，反之  */
		boxs.click(function(event){
			/** 去掉复选按钮的事件传播：点击复选会触发行点击：因为复选在行中 */
			event.stopPropagation();
			
			/** 判断当前选中的数据行有多少个  */
			var checkedBoxs = boxs.filter(":checked");
			/** 判断选中的总行数是否等于总行数：以便控制全选按钮的状态   */
			$("#checkAll").attr("checked",checkedBoxs.length == boxs.length);
		})
	   
		/** 给数据行绑定鼠标覆盖以及鼠标移开事件  */
		$("tr[id^='data_']").hover(function(){
			$(this).css("backgroundColor","#eeccff");
		},function(){
			$(this).css("backgroundColor","#ffffff");
		})
	    	
		/** 删除部门绑定点击事件 */
		$("#delete").click(function(){
			/** 获取到用户选中的复选框  */
			var checkedBoxs = boxs.filter(":checked");
			if(checkedBoxs.length < 1){
				alert("请选择一个需要删除的部门！");
			}else{				
				$("#pageNum").attr("value",1);
		   		$("#deptform").attr("action", "${ctx}/dept/del.action");
				$("#deptform").submit();
		 	}
		})
 	   
		$("#query").click(function(){
			$("#pageNum").attr("value",1);
			$("#deptform").attr("action", "${ctx}/dept/list.action");
			$("#deptform").submit();
		})
    })
</script>
</head>
<body>
	<!-- 导航 -->
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td height="10"></td>
		</tr>
		<tr>
			<td width="15" height="32"><img
				src="${ctx}/images/main_locleft.gif" width="15" height="32"></td>
			<td class="main_locbg font2"><img
				src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：部门管理
				&gt; 部门查询</td>
			<td width="15" height="32"><img
				src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
		</tr>
	</table>
	<form name="deptform" method="post" id="deptform">

		<!-- 配置pageNum的隐藏域 -->
		<input type="hidden" name="pageNum" value="${pageModel.pageNum}"
			id="pageNum">
		<table width="100%" height="90%" border="0" cellpadding="5"
			cellspacing="0" class="main_tabbor">
			<!-- 查询区  -->
			<tr valign="top">
				<td height="30">
					<table width="100%" border="0" cellpadding="0" cellspacing="10"
						class="main_tab">
						<tr>
							<td class="fftd">

								<table width="100%" border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td class="font3">部门名称：<input type="text" name="name"
											value="${dept.name}" /> <input type="button" value="查询"
											id="query" /> <input type="button" value="删除" id="delete" />
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>

			<!-- 数据展示区 -->
			<tr valign="top">
				<td height="20">
					<table width="100%" border="1" cellpadding="5" cellspacing="0"
						style="border: #c2c6cc 1px solid; border-collapse: collapse;">
						<tr class="main_trbg_tit" align="center">
							<td><input type="checkbox" name="checkAll" id="checkAll"></td>
							<td>部门名称</td>
							<td>描述</td>
							<td align="center">操作</td>
						</tr>
						<c:forEach items="${pageModel.list}" var="dept" varStatus="stat">
							<tr id="data_${stat.index}" align="center" class="main_trbg">
								<td><input type="checkbox" id="box_${stat.index}"
									value="${dept.id}" name="deptIds" /></td>
								<td>${dept.name }</td>
								<td>${dept.remark}</td>
								<td align="center" width="40px;"><a
									href="${ctx}/dept/update.do?id=${dept.id}"> <img
										title="修改" src="${ctx}/images/update.gif" /></a></td>
							</tr>
						</c:forEach>
					</table>
				</td>
			</tr>
			<!-- 分页标签 -->
			<tr valign="top">
				<td align="center" class="font3"><%@include
						file="/page/page.jsp"%></td>
			</tr>
		</table>
	</form>
</body>
</html>