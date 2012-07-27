<%@ page import="org.apache.commons.fileupload.*, org.apache.commons.fileupload.servlet.ServletFileUpload, org.apache.commons.fileupload.disk.DiskFileItemFactory, org.apache.commons.io.FilenameUtils, java.util.*, java.io.File, java.lang.Exception" %>

<%
//Check that we have a file upload request
boolean isMultipart = ServletFileUpload.isMultipartContent(request);
   
out.println("Multipart is " + isMultipart);

%>