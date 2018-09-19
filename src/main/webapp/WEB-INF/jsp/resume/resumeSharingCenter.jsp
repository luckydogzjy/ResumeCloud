<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta charset="UTF-8">
		<title></title>
		
		<link rel="stylesheet" type="text/css" href="../css/demo.css" />
		<link rel="stylesheet" type="text/css" href="../css/resumeCss/resumeSharingCenter.css" />
		
		<script type="text/javascript">
			function pageChange(page){
				var url = "${pageContext.request.contextPath}/SharingCenter/getSharingResumeListByCondition.do?page=" + page;
				location.href = url; 
			}
		</script>
	</head>
	
	<body>
		<div id="box">

			<div id="header">
				<jsp:include page="../common/header.jsp" flush="true" />
			</div>
			<div id="lefter">
				<jsp:include page="../common/lefter.jsp" flush="true" />
			</div>

			<div id="right">
				<div id="right-box">
					<form action="${pageContext.request.contextPath }/SharingCenter/getSharingResumeListByCondition.do">

						<div id="selectByJob">
							<span>求职意向</span>
							<input type="search" name="resumeJobIntension" placeholder="请输入求职意向" />
						</div>

						<div id="selectBySex">
							<span>性别</span>
							<select name="resumeSex">
								<option value="-1" <c:if test="${'-1' eq resumeSex}">selected="selected"</c:if>>全部</option>
								<option value="1" <c:if test="${'1' eq resumeSex}">selected="selected"</c:if>>男</option>
								<option value="0" <c:if test="${'0' eq resumeSex}">selected="selected"</c:if>>女</option>
							</select>
						</div>

						<div id="selectByEdu">
							<span>学历</span>
							<select name="resumeEducation">
								<option value="-1" <c:if test="${'-1' eq resumeEducation}">selected="selected"</c:if>>全部</option>
								<option value="0" <c:if test="${'0' eq resumeEducation}">selected="selected"</c:if>>高中及高中以下</option>
								<option value="1" <c:if test="${'1' eq resumeEducation}">selected="selected"</c:if>>专科</option>
								<option value="2" <c:if test="${'2' eq resumeEducation}">selected="selected"</c:if>>本科</option>
								<option value="3" <c:if test="${'3' eq resumeEducation}">selected="selected"</c:if>>研究生</option>
								<option value="4" <c:if test="${'4' eq resumeEducation}">selected="selected"</c:if>>硕士</option>
								<option value="5" <c:if test="${'5' eq resumeEducation}">selected="selected"</c:if>>博士</option>
								<option value="6" <c:if test="${'6' eq resumeEducation}">selected="selected"</c:if>>博士以上</option>
							</select>
						</div>
						<div id="selectByWorkYears">
							<span>工作年限</span>
							<select name="resumeWorkYears">
								<option value="-1" <c:if test="${'-1' eq resumeWorkYears}">selected="selected"</c:if>>全部</option>
								<option value="0" <c:if test="${'0' eq resumeWorkYears}">selected="selected"</c:if>>0-3年</option>
								<option value="1" <c:if test="${'1' eq resumeWorkYears}">selected="selected"</c:if>>3-5年</option>
								<option value="2" <c:if test="${'2' eq resumeWorkYears}">selected="selected"</c:if>>5-10年</option>
								<option value="3" <c:if test="${'3' eq resumeWorkYears}">selected="selected"</c:if>>10年以上</option>
							</select>
						</div>

						<div id="selectByGradu">
							<span>毕业院校</span>
							<input type="search" name="resumeGraduateInstitution" placeholder="请输入毕业院校" />
						</div>

						<div id="searchBtn">
							<input type="submit" id="search_button" value="搜索" />
						</div>

						<div id="table">
							<table class="displayTable" width="1000px" cellSpacing="6" cellPadding="3">
								<thead>
									<tr>
										<th width="10%">姓名</th>
										<th width="5%">性别</th>
										<th width="10%">工作年限</th>
										<th width="15%">毕业院校</th>
										<th width="8%">学历</th>
										<th width="10%">求职意向</th>
										<th width="12%">共享时间</th>
										<th width="10%">所需积分</th>
										<th width="10%">下载次数</th>
										<th width="10%">操作</th>
									</tr>
								</thead>

								<tbody>
									<c:forEach items="${sharingList }" var="sharing">

										<tr>
											<td>
												XX
												<%-- <a href="<%=request.getContextPath() %>/resumeDetails.do?resumeId_Details=${sharing.resumeId }">${resume.resumeName }</a> --%>
											</td>
											<td>
												<c:if test="${sharing.resume.resumeSex == 1}">
													男
												</c:if>
												<c:if test="${sharing.resume.resumeSex == 0}">
													女
												</c:if>
											</td>
											<td>${sharing.resume.resumeWorkYears }</td>
											<td>${sharing.resume.resumeGraduateInstitution }</td>

											<td>
												<c:if test="${sharing.resume.resumeEducation == 0}">
													高中及高中以下
												</c:if>
												<c:if test="${sharing.resume.resumeEducation == 1}">
													专科
												</c:if>
												<c:if test="${sharing.resume.resumeEducation == 2}">
													本科
												</c:if>
												<c:if test="${sharing.resume.resumeEducation == 3}">
													研究生
												</c:if>
												<c:if test="${sharing.resume.resumeEducation == 4}">
													硕士
												</c:if>
												<c:if test="${sharing.resume.resumeEducation == 5}">
													博士
												</c:if>
												<c:if test="${sharing.resume.resumeEducation == 6}">
													博士以上
												</c:if>
											</td>

											<td>${sharing.resume.resumeJobIntension }</td>
											<td>
												<fmt:formatDate value="${sharing.scCreateTime}" pattern="yyyy-MM-dd" />
											</td>

											<td>${sharing.scIntegral }</td>
											<td>${sharing.scDownloadCount }</td>

											<td>
												<!-- 如果该条简历不是自己共享的并且用户还未兑换过显示 兑换按钮 -->
												<c:if test="${sharing.flag == 0 && sharing.scUserId != 1}">
													<input type="button" id="getBtn" value="兑换" />
												</c:if>
												<!-- 如果该条简历不是自己共享的但是用户已经兑换过显示 已兑换 按钮 -->
												<c:if test="${sharing.flag == 1 && sharing.scUserId != 1}">
													<input type="button" id="NoBtn" value="已兑换" disabled="disabled" />
												</c:if>
												<c:if test="${sharing.scUserId == 1}">
													<input type="button" id="cancelBtn" value="取消共享" />
												</c:if>
										</tr>

									</c:forEach>
								</tbody>

							</table>

							<div id="pageDetail">
								<span>当前第${page.pageNum}页，一共${page.pages}页</span>
							</div>
							<div id="page">
								<span>
			       				
			       				
			       				<input type="button" value="首页" onclick="pageChange('1')"/>
			       				<input type="button" value="上一页" onclick="pageChange(${page.prePage})"/>
			       				<input type="button" value="下一页" onclick="pageChange(${page.nextPage})"/>
			       				<input type="button" value="尾页" onclick="pageChange(${page.pages})"/>
			       					 
          		 			</span>
							</div>

						</div>
					</form>

				</div>
			</div>
		</div>

	</body>

</html>