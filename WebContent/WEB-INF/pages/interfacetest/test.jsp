<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>test</title>
</head>
<body>
<form action="/AbilityTest/test/add" method="POST" >
	<table width="500px" border="1px #FFFFFF soild">
    	<tr>
        	<th colspan="2" align="left">test add</th>
        <tr>
        <tr>
        	<td width="30%">name(必填)</td>
            <td><input type="text" name="name" value="" /></td>
        </tr>
        <tr>
        	<td width="30%">money(必填)</td>
            <td><input type="text" name="money" value="" /></td>
        </tr>
        <tr>
        	<td colspan="2" align="left"><input type="submit" value="go"/> </td>
        <tr>
    </table>
</form>

<form action="/AbilityTest/test/delete" method="POST" >
	<table width="500px" border="1px #FFFFFF soild">
    	<tr>
        	<th colspan="2" align="left">test delete</th>
        <tr>
        <tr>
        	<td width="30%">name(必填)</td>
            <td><input type="text" name="name" value="" /></td>
        </tr>
        <tr>
        	<td colspan="2" align="left"><input type="submit" value="go"/> </td>
        <tr>
    </table>
</form>

<form action="/AbilityTest/test/get" method="GET" >
	<table width="500px" border="1px #FFFFFF soild">
    	<tr>
        	<th colspan="2" align="left">test find</th>
        <tr>
        <tr>
        	<td width="30%">id(必填)</td>
            <td><input type="text" name="id" value="" /></td>
        </tr>
        <tr>
        	<td colspan="2" align="left"><input type="submit" value="go"/> </td>
        <tr>
    </table>
</form>

<form action="/AbilityTest/test/update" method="GET" >
	<table width="500px" border="1px #FFFFFF soild">
    	<tr>
        	<th colspan="2" align="left">test Update</th>
        <tr>
        <tr>
        	<td colspan="2" align="left"><input type="submit" value="go"/> </td>
        <tr>
    </table>
</form>
</body>
</html>