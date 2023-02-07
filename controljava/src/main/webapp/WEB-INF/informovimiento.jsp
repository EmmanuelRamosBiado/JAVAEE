<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%-- Importo las clases necesarias --%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="modelo.Movimiento"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Consulta de movimientos</title>
</head>
<body>
	<%
	ArrayList<Movimiento> lista = (ArrayList <Movimiento>) request.getAttribute("listaMovimiento");
	%>
	<table border="1">
		<tr>
			<th>Nº Movimiento</th>
			<th>Cód. Cliente</th>
			<th>Concepto</th>
			<th>Importe</th>
			<%
			for (Movimiento l : lista) {
			%>
		
		<tr>

			<td><%=l.getNum_mov()%></td>
			<td><%=l.getCod_cliente()%></td>
			<td><%=l.getConcepto()%></td>
			<td><%=l.getImporte()%></td>

		</tr>
		<%
		}
		%>
	</table>

</body>
</html>