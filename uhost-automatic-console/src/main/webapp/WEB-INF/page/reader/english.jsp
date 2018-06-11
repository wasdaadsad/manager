<%--
  Created by IntelliJ IDEA.
  User: huangxq
  Date: 2017/12/5
  Time: 16:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="Content-Style-Type" content="text/css"/>
    <title>
    </title>
    <style type="text/css">
        body {
            text-align: justify;
            widows: 0;
            orphans: 0;
            font-family: Calibri;
            font-size: 10.5pt
        }

        h1, p {
            margin: 0pt
        }

        li, table {
            margin-top: 0pt;
            margin-bottom: 0pt
        }

        h1 {
            margin-top: 17pt;
            margin-bottom: 16.5pt;
            text-align: justify;
            page-break-inside: avoid;
            page-break-after: avoid;
            line-height: 241%;
            widows: 0;
            orphans: 0;
            font-family: Calibri;
            font-size: 22pt;
            font-weight: bold
        }

        .BalloonText {
            text-align: justify;
            widows: 0;
            orphans: 0;
            font-family: Calibri;
            font-size: 9pt
        }

        .DocumentMap {
            text-align: justify;
            widows: 0;
            orphans: 0;
            font-family: 宋体;
            font-size: 9pt
        }

        .Footer {
            text-align: left;
            widows: 0;
            orphans: 0;
            font-family: Calibri;
            font-size: 9pt
        }

        .HTMLPreformatted {
            text-align: left;
            widows: 2;
            orphans: 2;
            font-family: 宋体;
            font-size: 12pt
        }

        .Header {
            text-align: center;
            widows: 0;
            orphans: 0;
            border-bottom-style: solid;
            border-bottom-width: 0.75pt;
            border-bottom-color: #000000;
            padding-bottom: 1pt;
            font-family: Calibri;
            font-size: 9pt
        }

        .TOC1 {
            text-align: justify;
            widows: 0;
            orphans: 0;
            font-family: Calibri;
            font-size: 10.5pt
        }

        .TOC2 {
            margin-left: 21pt;
            text-align: justify;
            widows: 0;
            orphans: 0;
            font-family: Calibri;
            font-size: 10.5pt
        }

        .TOC3 {
            margin-left: 42pt;
            text-align: justify;
            widows: 0;
            orphans: 0;
            font-family: Calibri;
            font-size: 10.5pt
        }

        .TOC4 {
            margin-left: 63pt;
            text-align: justify;
            widows: 0;
            orphans: 0;
            font-family: Calibri;
            font-size: 10.5pt
        }

        .TOC5 {
            margin-left: 84pt;
            text-align: justify;
            widows: 0;
            orphans: 0;
            font-family: Calibri;
            font-size: 10.5pt
        }

        .TOC6 {
            margin-left: 105pt;
            text-align: justify;
            widows: 0;
            orphans: 0;
            font-family: Calibri;
            font-size: 10.5pt
        }

        .TOC7 {
            margin-left: 126pt;
            text-align: justify;
            widows: 0;
            orphans: 0;
            font-family: Calibri;
            font-size: 10.5pt
        }

        .TOC8 {
            margin-left: 147pt;
            text-align: justify;
            widows: 0;
            orphans: 0;
            font-family: Calibri;
            font-size: 10.5pt
        }

        .TOC9 {
            margin-left: 168pt;
            text-align: justify;
            widows: 0;
            orphans: 0;
            font-family: Calibri;
            font-size: 10.5pt
        }

        .TOC10 {
            margin-top: 24pt;
            margin-bottom: 0pt;
            text-align: left;
            page-break-inside: avoid;
            page-break-after: avoid;
            line-height: 115%;
            widows: 2;
            orphans: 2;
            font-family: Cambria;
            font-size: 14pt;
            font-weight: bold;
            color: #365f91
        }

        .1
        {
            text-indent: 21pt
        ;
            text-align: justify
        ;
            widows: 0
        ;
            orphans: 0
        ;
            font-family: Calibri
        ;
            font-size: 10.5pt
        }
        span.HTMLCode {
            font-family: 宋体;
            font-size: 12pt
        }

        span.HTMLChar {
            font-family: 宋体;
            font-size: 12pt
        }

        span.Hyperlink {
            text-decoration: underline;
            color: #0000ff
        }

        span.Char1 {
            font-size: 9pt
        }

        span.Char2 {
            font-family: 宋体;
            font-size: 9pt
        }

        span

        .1
        Char {
            font-size: 22pt;
            font-weight: bold
        }

        span.Char {
            font-size: 9pt
        }

        span.Char0 {
            font-size: 9pt
        }

        .

        -
        11
        {
            color: #365f91
        }
    </style>
</head>
<body>
<div>
    <p class="Header" style="text-align:left">
        <img src="${contextPath}/static/assets/images/vivo_sda_user_guide_env0.8/vivo_sda_user_guide_env0.8.001.jpg"
             width="552" height="552" alt=""
             style="margin-top:0pt; margin-left:-40.05pt; position:absolute; z-index:-131069"/>
        <img src="${contextPath}/static/assets/images/vivo_sda_user_guide_env0.8/vivo_sda_user_guide_env0.8.002.jpg" width="95" height="60"
             alt="E:\售后工具\images\vivo-logo.png"/>              PostSale Diagnostic Assistant (SDA)               User
        Guide</p>
    <p>&#xa0;</p>
    <p>&#xa0;</p>
    <p>&#xa0;</p>
    <p>&#xa0;</p>
    <p>&#xa0;</p>
    <p>&#xa0;</p>
    <p>&#xa0;</p>
    <p>&#xa0;</p>
    <p>&#xa0;</p>
    <p>&#xa0;</p>
    <p>&#xa0;</p>
    <p>&#xa0;</p>
    <p style="text-align:center"><span style="font-size:16pt">vivo Communication Technology Co., Ltd</span></p>
    <p style="text-align:center"><span style="font-size:14pt">Post</span><span style="font-size:14pt">Sale Diagnostic Assistant</span><span
            style="font-size:14pt">（</span><span style="font-size:14pt">SDA</span><span
            style="font-size:14pt">）</span><span style="font-size:14pt">User Guaid V0.6</span></p>
    <p style="text-align:left">&#xa0;</p>
    <table cellspacing="0" cellpadding="0" style="border-collapse:collapse; margin-left:0pt; width:426.1pt">
        <tr>
            <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.5pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.5pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.5pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.5pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:69.2pt">
                <p style="text-align:center; widows:0; orphans:0"><strong><span
                        style="font-family:Calibri; font-size:7.5pt; color:#404040">Version</span></strong></p>
            </td>
            <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.5pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.5pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.5pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.5pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:70.9pt">
                <p style="text-align:center; widows:0; orphans:0"><strong><span
                        style="font-family:Calibri; font-size:7.5pt; color:#404040">Date</span></strong></p>
            </td>
            <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.5pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.5pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.5pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.5pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:63.75pt">
                <p style="text-align:center; widows:0; orphans:0"><strong><span
                        style="font-family:Calibri; font-size:7.5pt; color:#404040">Author</span></strong></p>
            </td>
            <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.5pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.5pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.5pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.5pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:141.75pt">
                <p style="text-align:center; widows:0; orphans:0"><strong><span
                        style="font-family:Calibri; font-size:7.5pt; color:#404040">Descript</span></strong></p>
            </td>
            <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.5pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.5pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.5pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.5pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:80.5pt">
                <p style="text-align:center; widows:0; orphans:0"><strong><span
                        style="font-family:Calibri; font-size:7.5pt; color:#404040">Remarks</span></strong></p>
            </td>
        </tr>
        <tr>
            <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.5pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.5pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.5pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.5pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:69.2pt">
                <p style="text-align:left; widows:0; orphans:0"><span
                        style="font-family:Calibri; font-size:7.5pt; color:#404040">V0.1</span></p>
            </td>
            <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.5pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.5pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.5pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.5pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:70.9pt">
                <p style="text-align:left; widows:0; orphans:0"><span
                        style="font-family:Calibri; font-size:7.5pt; color:#404040">2016-10-10</span></p>
            </td>
            <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.5pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.5pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.5pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.5pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:63.75pt">
                <p style="text-align:left; widows:0; orphans:0"><span
                        style="font-family:Calibri; font-size:7.5pt; color:#404040">yelingjun</span></p>
            </td>
            <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.5pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.5pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.5pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.5pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:141.75pt">
                <p style="text-align:left; widows:0; orphans:0"><span
                        style="font-family:Calibri; font-size:7.5pt; color:#404040">Create first Edition</span></p>
            </td>
            <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.5pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.5pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.5pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.5pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:80.5pt">
                <p style="text-align:left; widows:0; orphans:0"><span
                        style="font-family:Calibri; font-size:7.5pt; color:#404040">&#xa0;</span></p>
            </td>
        </tr>
        <tr>
            <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.5pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.5pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.5pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.5pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:69.2pt">
                <p style="text-align:left; widows:0; orphans:0"><span
                        style="font-family:Calibri; font-size:7.5pt; color:#404040">V0.2</span></p>
            </td>
            <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.5pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.5pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.5pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.5pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:70.9pt">
                <p style="text-align:left; widows:0; orphans:0"><span
                        style="font-family:Calibri; font-size:7.5pt; color:#404040">2016-11-22</span></p>
            </td>
            <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.5pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.5pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.5pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.5pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:63.75pt">
                <p style="text-align:left; widows:0; orphans:0"><span
                        style="font-family:Calibri; font-size:7.5pt; color:#404040">yelingjun</span></p>
            </td>
            <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.5pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.5pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.5pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.5pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:141.75pt">
                <p style="text-align:left; widows:0; orphans:0"><span
                        style="font-family:Calibri; font-size:7.5pt; color:#404040">Improve some screenshots</span></p>
            </td>
            <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.5pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.5pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.5pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.5pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:80.5pt">
                <p style="text-align:left; widows:0; orphans:0"><span
                        style="font-family:Calibri; font-size:7.5pt; color:#404040">&#xa0;</span></p>
            </td>
        </tr>
        <tr>
            <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.5pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.5pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.5pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.5pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:69.2pt">
                <p style="text-align:left; widows:0; orphans:0"><span
                        style="font-family:Calibri; font-size:7.5pt; color:#404040">V0.3</span></p>
            </td>
            <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.5pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.5pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.5pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.5pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:70.9pt">
                <p style="text-align:left; widows:0; orphans:0"><span
                        style="font-family:Calibri; font-size:7.5pt; color:#404040">2016-11-22</span></p>
            </td>
            <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.5pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.5pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.5pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.5pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:63.75pt">
                <p style="text-align:left; widows:0; orphans:0"><span
                        style="font-family:Calibri; font-size:7.5pt; color:#404040">yelingjun</span></p>
            </td>
            <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.5pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.5pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.5pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.5pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:141.75pt">
                <p style="text-align:left; widows:0; orphans:0"><span
                        style="font-family:Calibri; font-size:7.5pt; color:#404040">Add Mailbox Setup Instructions and Release Notes</span>
                </p>
            </td>
            <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.5pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.5pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.5pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.5pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:80.5pt">
                <p style="text-align:left; widows:0; orphans:0"><span
                        style="font-family:Calibri; font-size:7.5pt; color:#404040">&#xa0;</span></p>
            </td>
        </tr>
        <tr>
            <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.5pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.5pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.5pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.5pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:69.2pt">
                <p style="text-align:left; widows:0; orphans:0"><span
                        style="font-family:Calibri; font-size:7.5pt; color:#404040">V0.4</span></p>
            </td>
            <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.5pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.5pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.5pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.5pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:70.9pt">
                <p style="text-align:left; widows:0; orphans:0"><span
                        style="font-family:Calibri; font-size:7.5pt; color:#404040">2016-02-14</span></p>
            </td>
            <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.5pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.5pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.5pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.5pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:63.75pt">
                <p style="text-align:left; widows:0; orphans:0"><span
                        style="font-family:Calibri; font-size:7.5pt; color:#404040">yelingjun</span></p>
            </td>
            <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.5pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.5pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.5pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.5pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:141.75pt">
                <p style="text-align:left; widows:0; orphans:0"><span
                        style="font-family:Calibri; font-size:7.5pt; color:#404040">Report management, automatic connection related instructions</span>
                </p>
            </td>
            <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.5pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.5pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.5pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.5pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:80.5pt">
                <p style="text-align:left; widows:0; orphans:0"><span
                        style="font-family:Calibri; font-size:7.5pt; color:#404040">&#xa0;</span></p>
            </td>
        </tr>
        <tr>
            <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.5pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.5pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.5pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.5pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:69.2pt">
                <p style="text-align:left; widows:0; orphans:0"><span
                        style="font-family:Calibri; font-size:7.5pt; color:#404040">V0.5</span></p>
            </td>
            <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.5pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.5pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.5pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.5pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:70.9pt">
                <p style="text-align:left; widows:0; orphans:0"><span
                        style="font-family:Calibri; font-size:7.5pt; color:#404040">2016-04-26</span></p>
            </td>
            <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.5pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.5pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.5pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.5pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:63.75pt">
                <p style="text-align:left; widows:0; orphans:0"><span
                        style="font-family:Calibri; font-size:7.5pt; color:#404040">yelingjun</span></p>
            </td>
            <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.5pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.5pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.5pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.5pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:141.75pt">
                <p style="text-align:left; widows:0; orphans:0"><span
                        style="font-family:Calibri; font-size:7.5pt; color:#404040">Remove </span><span
                        style="font-family:Calibri; font-size:7.5pt; color:#404040">Login, </span><span
                        style="font-family:Calibri; font-size:7.5pt; color:#404040">QCOM</span><span
                        style="font-family:Calibri; font-size:7.5pt; color:#404040"> log analysis, accurate equipment testing</span>
                </p>
            </td>
            <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.5pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.5pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.5pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.5pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:80.5pt">
                <p style="text-align:left; widows:0; orphans:0"><span
                        style="font-family:Calibri; font-size:7.5pt; color:#404040">&#xa0;</span></p>
            </td>
        </tr>
        <tr>
            <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.5pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.5pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.5pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.5pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:69.2pt">
                <p style="text-align:left; widows:0; orphans:0"><span
                        style="font-family:Calibri; font-size:7.5pt; color:#404040">V0.6</span></p>
            </td>
            <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.5pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.5pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.5pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.5pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:70.9pt">
                <p style="text-align:left; widows:0; orphans:0"><span
                        style="font-family:Calibri; font-size:7.5pt; color:#404040">2016-05-18</span></p>
            </td>
            <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.5pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.5pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.5pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.5pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:63.75pt">
                <p style="text-align:left; widows:0; orphans:0"><span
                        style="font-family:Calibri; font-size:7.5pt; color:#404040">yelingjun</span></p>
            </td>
            <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.5pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.5pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.5pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.5pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:141.75pt">
                <p style="text-align:left; widows:0; orphans:0"><span
                        style="font-family:Calibri; font-size:7.5pt; color:#404040">3.0.5 Release</span></p>
            </td>
            <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.5pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.5pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.5pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.5pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:80.5pt">
                <p style="text-align:left; widows:0; orphans:0"><span
                        style="font-family:Calibri; font-size:7.5pt; color:#404040">&#xa0;</span></p>
            </td>
        </tr>
        <tr>
            <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.5pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.5pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.5pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.5pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:69.2pt">
                <p style="text-align:left; widows:0; orphans:0"><span
                        style="font-family:Calibri; font-size:7.5pt; color:#404040">V0.7</span></p>
            </td>
            <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.5pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.5pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.5pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.5pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:70.9pt">
                <p style="text-align:left; widows:0; orphans:0"><span
                        style="font-family:Calibri; font-size:7.5pt; color:#404040">2017-08-31</span></p>
            </td>
            <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.5pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.5pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.5pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.5pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:63.75pt">
                <p style="text-align:left; widows:0; orphans:0"><span
                        style="font-family:Calibri; font-size:7.5pt; color:#404040">yelingjun</span></p>
            </td>
            <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.5pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.5pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.5pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.5pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:141.75pt">
                <p style="text-align:left; widows:0; orphans:0"><span
                        style="font-family:Calibri; font-size:7.5pt; color:#404040">3.0.6 Release</span></p>
            </td>
            <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.5pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.5pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.5pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.5pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:80.5pt">
                <p style="text-align:left; widows:0; orphans:0"><span
                        style="font-family:Calibri; font-size:7.5pt; color:#404040">&#xa0;</span></p>
            </td>
        </tr>
        <tr>
            <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.5pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.5pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.5pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.5pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:69.2pt">
                <p style="text-align:left; widows:0; orphans:0"><span
                        style="font-family:Calibri; font-size:7.5pt; color:#404040">V</span><span
                        style="font-family:Calibri; font-size:7.5pt; color:#404040">0.8</span></p>
            </td>
            <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.5pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.5pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.5pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.5pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:70.9pt">
                <p style="text-align:left; widows:0; orphans:0"><span
                        style="font-family:Calibri; font-size:7.5pt; color:#404040">2017-11-27</span></p>
            </td>
            <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.5pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.5pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.5pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.5pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:63.75pt">
                <p style="text-align:left; widows:0; orphans:0"><span
                        style="font-family:Calibri; font-size:7.5pt; color:#404040">Q</span><span
                        style="font-family:Calibri; font-size:7.5pt; color:#404040">iaobaowen</span></p>
            </td>
            <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.5pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.5pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.5pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.5pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:141.75pt">
                <p style="text-align:left; widows:0; orphans:0"><span
                        style="font-family:Calibri; font-size:7.5pt; color:#404040">3.1.0 Release</span></p>
            </td>
            <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.5pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.5pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.5pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.5pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:80.5pt">
                <p style="text-align:left; widows:0; orphans:0"><span
                        style="font-family:Calibri; font-size:7.5pt; color:#404040">&#xa0;</span></p>
            </td>
        </tr>
    </table>
    <p style="text-align:left">&#xa0;</p>
    <p style="text-align:left; widows:2; orphans:2"><br style="page-break-before:always; clear:both"/></p>
    <p class="TOC10">Table of Contents</p>
    <p class="TOC1"><a href="#_Toc499570088"><span class="Hyperlink" style="font-weight:bold">1.</span>             
        <span class="Hyperlink" style="font-weight:bold">AfterSale Diagnostic Assistant</span></a></p>
    <p class="TOC2"><a href="#_Toc499570089"><span class="Hyperlink">1.1</span>              <span class="Hyperlink">Background</span></a>
    </p>
    <p class="TOC2"><a href="#_Toc499570090"><span class="Hyperlink">1.2</span>              <span class="Hyperlink">Confidential Principle</span></a>
    </p>
    <p class="TOC2"><a href="#_Toc499570091"><span class="Hyperlink">1.3</span>              <span class="Hyperlink">Release Notes</span></a>
    </p>
    <p class="TOC1"><a href="#_Toc499570092"><span class="Hyperlink" style="font-weight:bold">2.</span>             
        <span class="Hyperlink" style="font-weight:bold">Software Environment</span></a></p>
    <p class="TOC2"><a href="#_Toc499570093"><span class="Hyperlink">2.1</span>              <span class="Hyperlink">Develop Environment</span></a>
    </p>
    <p class="TOC2"><a href="#_Toc499570094"><span class="Hyperlink">2.2</span>              <span class="Hyperlink">Running Environment</span></a>
    </p>
    <p class="TOC1"><a href="#_Toc499570095"><span class="Hyperlink" style="font-weight:bold">3.</span>             
        <span class="Hyperlink" style="font-weight:bold">Installation and Uninstalltion</span></a></p>
    <p class="TOC2"><a href="#_Toc499570096"><span class="Hyperlink">3.1</span>              <span class="Hyperlink">Installation</span></a>
    </p>
    <p class="TOC2"><a href="#_Toc499570097"><span class="Hyperlink">3.2</span>              <span class="Hyperlink">Uninstallation</span></a>
    </p>
    <p class="TOC1"><a href="#_Toc499570098"><span class="Hyperlink" style="font-weight:bold">4.</span>             
        <span class="Hyperlink" style="font-weight:bold">User Authentication</span></a></p>
    <p class="TOC2"><a href="#_Toc499570099"><span class="Hyperlink">4.1</span>              <span class="Hyperlink">User Interface</span></a>
    </p>
    <p class="TOC2"><a href="#_Toc499570100"><span class="Hyperlink">4.2</span>              <span class="Hyperlink">Cetification Process</span></a>
    </p>
    <p class="TOC3"><a href="#_Toc499570101"><span class="Hyperlink">4.2.1</span>              <span class="Hyperlink">Obtain Verification Code</span></a>
    </p>
    <p class="TOC3"><a href="#_Toc499570102"><span class="Hyperlink">4.2.2</span>              <span class="Hyperlink">Launch Certification</span></a>
    </p>
    <p class="TOC1"><a href="#_Toc499570103"><span class="Hyperlink" style="font-weight:bold">5.</span>             
        <span class="Hyperlink" style="font-weight:bold">Phone Connection</span></a></p>
    <p class="TOC2"><a href="#_Toc499570104"><span class="Hyperlink">5.1</span>              <span class="Hyperlink">Debug Port</span></a>
    </p>
    <p class="TOC2"><a href="#_Toc499570105"><span class="Hyperlink">5.2</span>              <span class="Hyperlink">Manual Connection</span></a>
    </p>
    <p class="TOC2"><a href="#_Toc499570106"><span class="Hyperlink">5.3</span>              <span class="Hyperlink">Automatic Connection</span></a>
    </p>
    <p class="TOC1"><a href="#_Toc499570107"><span class="Hyperlink" style="font-weight:bold">6.</span>             
        <span class="Hyperlink" style="font-weight:bold">APK Installation</span></a></p>
    <p class="TOC2"><a href="#_Toc499570108"><span class="Hyperlink">6.1</span>              <span class="Hyperlink">Install APK</span></a>
    </p>
    <p class="TOC3"><a href="#_Toc499570109"><span class="Hyperlink">6.1.1</span>              <span class="Hyperlink">Force Mode</span></a>
    </p>
    <p class="TOC3"><a href="#_Toc499570110"><span class="Hyperlink">6.1.2</span>              <span class="Hyperlink">Version Compare Mode</span></a>
    </p>
    <p class="TOC3"><a href="#_Toc499570111"><span class="Hyperlink">6.1.3</span>              <span class="Hyperlink">Automatic Installation</span></a>
    </p>
    <p class="TOC1"><a href="#_Toc499570112"><span class="Hyperlink" style="font-weight:bold">7.</span>             
        <span class="Hyperlink" style="font-weight:bold">Import and Export CheckItems</span></a></p>
    <p class="TOC2"><a href="#_Toc499570113"><span class="Hyperlink">7.1</span>              <span class="Hyperlink">Import</span></a>
    </p>
    <p class="TOC2"><a href="#_Toc499570114"><span class="Hyperlink">7.2</span>              <span class="Hyperlink">Export</span></a>
    </p>
    <p class="TOC1"><a href="#_Toc499570115"><span class="Hyperlink" style="font-weight:bold">8.</span>             
        <span class="Hyperlink" style="font-weight:bold">Check</span></a></p>
    <p class="TOC1"><a href="#_Toc499570116"><span class="Hyperlink">8.1</span>              <span class="Hyperlink">Chose Check Items</span></a>
    </p>
    <p class="TOC1"><a href="#_Toc499570117"><span class="Hyperlink">8.2</span>              <span class="Hyperlink">Check</span></a>
    </p>
    <p class="TOC3"><a href="#_Toc499570118"><span class="Hyperlink">8.2.1</span>              <span class="Hyperlink">Interactive Check</span></a>
    </p>
    <p class="TOC1"><a href="#_Toc499570119"><span class="Hyperlink" style="font-weight:bold">9.</span>             
        <span class="Hyperlink" style="font-weight:bold">Historical temperature statistics</span></a></p>
    <p class="TOC1"><a href="#_Toc499570120"><span class="Hyperlink" style="font-weight:bold">9.1Da</span><span
            class="Hyperlink" style="font-weight:bold">t</span><span class="Hyperlink"
                                                                     style="font-weight:bold">e Choose</span></a></p>
    <p class="TOC1"><a href="#_Toc499570121"><span class="Hyperlink" style="font-weight:bold">9.2Tenperature curves switch</span></a>
    </p>
    <p class="TOC1"><a href="#_Toc499570122"><span class="Hyperlink" style="font-weight:bold">9.3Tooltip</span></a></p>
    <p class="TOC1"><a href="#_Toc499570123"><span class="Hyperlink" style="font-weight:bold">9.4 Historical temperature advice</span></a>
    </p>
    <p class="TOC1"><a href="#_Toc499570124"><span class="Hyperlink" style="font-weight:bold">10.</span>             
        <span class="Hyperlink" style="font-weight:bold">Report Management</span></a></p>
    <p class="TOC2"><a href="#_Toc499570125"><span class="Hyperlink">10.1</span>              <span class="Hyperlink">Generate Report</span></a>
    </p>
    <p class="TOC3"><a href="#_Toc499570126"><span class="Hyperlink">10.1.1</span>              <span class="Hyperlink">Automatic Mode</span></a>
    </p>
    <p class="TOC3"><a href="#_Toc499570127"><span class="Hyperlink">10.1.2</span>              <span class="Hyperlink">Manual Mode</span></a>
    </p>
    <p class="TOC2"><a href="#_Toc499570128"><span class="Hyperlink">10.2</span>              <span class="Hyperlink">Upload Report</span></a>
    </p>
    <p class="TOC3"><a href="#_Toc499570129"><span class="Hyperlink">10.2.1</span>              <span class="Hyperlink">Automatic Mode</span></a>
    </p>
    <p class="TOC3"><a href="#_Toc499570130"><span class="Hyperlink">10.2.2</span>              <span class="Hyperlink">Manual Mode</span></a>
    </p>
    <p class="TOC2"><a href="#_Toc499570131"><span class="Hyperlink">10.3</span>              <span class="Hyperlink">Query Check History</span></a>
    </p>
    <p class="TOC1"><a href="#_Toc499570132"><span class="Hyperlink" style="font-weight:bold">11.</span>             
        <span class="Hyperlink" style="font-weight:bold">Options</span></a></p>
    <p class="TOC2"><a href="#_Toc499570133"><span class="Hyperlink">11.1</span>              <span class="Hyperlink">SDA Edition</span></a>
    </p>
    <p class="TOC2"><a href="#_Toc499570134"><span class="Hyperlink">11.2</span>              <span class="Hyperlink">UI Style</span></a>
    </p>
    <p class="TOC2"><a href="#_Toc499570135"><span class="Hyperlink">11.3</span>              <span class="Hyperlink">Language Setting</span></a>
    </p>
    <p class="TOC2"><a href="#_Toc499570136"><span class="Hyperlink">11.4</span>              <span class="Hyperlink">Check Parameter</span></a>
    </p>
    <p class="TOC2"><a href="#_Toc499570137"><span class="Hyperlink">11.5</span>              <span class="Hyperlink">Misc Setting</span></a>
    </p>
    <p class="TOC1"><a href="#_Toc499570138"><span class="Hyperlink" style="font-weight:bold">12.</span>             
        <span class="Hyperlink" style="font-weight:bold">Upgrade</span></a></p>
    <p class="TOC1"><a href="#_Toc499570139"><span class="Hyperlink" style="font-weight:bold">13.</span>             
        <span class="Hyperlink" style="font-weight:bold">Use Help</span></a></p>
    <p class="TOC1"><a href="#_Toc499570140"><span class="Hyperlink" style="font-weight:bold">14.</span>             
        <span class="Hyperlink" style="font-weight:bold">Shortcuts List</span></a></p>
    <p class="TOC1"><a href="#_Toc499570141"><span class="Hyperlink" style="font-weight:bold">15.</span>             
        <span class="Hyperlink" style="font-weight:bold">Technical Support</span></a></p>
    <p>
    </p>
    <p style="text-align:left; widows:2; orphans:2"><br style="page-break-before:always; clear:both"/></p>
    <p style="text-align:center"><span style="font-size:14pt">Post</span><span style="font-size:14pt">Sale Diagnostic Assistant</span><span
            style="font-size:14pt"> (SDA) User Guide</span></p>
    <ol type="1" style="margin:0pt; padding-left:0pt">
        <li class="1"
            style="margin-top:7.8pt; margin-left:15.83pt; margin-bottom:7.8pt; text-indent:0pt; text-align:left; padding-left:2.02pt; font-family:Calibri; font-size:14pt; font-weight:bold">
            <a name="_Toc499570088"><strong><span style="font-family:Calibri; font-size:14pt; ">AfterSale Diagnostic Assistant</span></strong></a>
        </li>
        <ol type="1" style="margin:0pt; padding-left:0pt">
            <li class="1"
                style="margin-left:35.7pt; text-indent:0pt; text-align:left; font-family:Calibri; font-size:10.5pt"><a
                    name="_Toc499570089"><span style="font-family:Calibri; font-size:10.5pt">Background</span></a></li>
        </ol>
    </ol>
    <p style="text-indent:17.85pt; text-align:left">In order to improve the post-sale technical staff to the "after-sale
        support center" application program efficiency, strengthen the after-sales technical staff and technical support
        , we develop the diagnostic assistant.</p>
    <ol start="2" type="1" style="margin:0pt; padding-left:0pt">
        <ol start="2" type="1" style="margin:0pt; padding-left:0pt">
            <li class="1"
                style="margin-left:35.7pt; text-indent:0pt; text-align:left; font-family:Calibri; font-size:10.5pt"><a
                    name="_Toc499570090"><span
                    style="font-family:Calibri; font-size:10.5pt">Confidential Principle</span></a></li>
        </ol>
    </ol>
    <p style="text-indent:17.85pt; text-align:left"><strong><span style="color:#ff0000">The Access Code from SDA certification, user </span></strong><strong><span
            style="color:#ff0000">information</span></strong><strong><span style="color:#ff0000"> and session.log are confidential information, Please don</span></strong><strong><span
            style="color:#ff0000">’</span></strong><strong><span
            style="color:#ff0000">t leak! Otherwise, it</span></strong><strong><span
            style="color:#ff0000">’</span></strong><strong><span style="color:#ff0000">s your own risk!</span></strong>
    </p>
    <ol start="3" type="1" style="margin:0pt; padding-left:0pt">
        <ol start="3" type="1" style="margin:0pt; padding-left:0pt">
            <li class="1"
                style="margin-left:35.7pt; text-indent:0pt; text-align:left; font-family:Calibri; font-size:10.5pt"><a
                    name="_Toc499570091"><span style="font-family:Calibri; font-size:10.5pt">Release Notes</span></a>
            </li>
        </ol>
    </ol>
    <table cellspacing="0" cellpadding="0" style="border-collapse:collapse; margin-left:0pt; width:426.1pt">
        <tr>
            <td style="border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:2.25pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:62.1pt">
                <p style="margin-top:0pt; margin-bottom:0pt; text-align:left; line-height:normal; widows:0; orphans:0; font-size:10.5pt">
                    <strong><span style="font-family:Cambria; font-size:10.5pt; ">Version</span></strong></p>
            </td>
            <td style="border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:2.25pt; border-left-color:#000000; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:276.45pt">
                <p style="margin-top:0pt; margin-bottom:0pt; text-align:left; line-height:normal; widows:0; orphans:0; font-size:10.5pt">
                    <strong><span style="font-family:Cambria; font-size:10.5pt; ">Descript</span></strong></p>
            </td>
            <td style="border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:2.25pt; border-left-color:#000000; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:87.55pt">
                <p style="margin-top:0pt; margin-bottom:0pt; text-align:left; line-height:normal; widows:0; orphans:0; font-size:10.5pt">
                    <strong><span style="font-family:Cambria; font-size:10.5pt; ">Release Time</span></strong></p>
            </td>
        </tr>
        <tr>
            <td style="background-color:#ffffff; border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:62.1pt">
                <p style="text-align:left; widows:0; orphans:0"><strong><span
                        style="font-family:Cambria; font-size:10.5pt; ">V</span></strong><strong><span
                        style="font-family:Cambria; font-size:10.5pt; ">3.1.0</span></strong></p>
            </td>
            <td style="background-color:#ffffff; border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:276.45pt">
                <ol type="1" style="margin:0pt; padding-left:0pt">
                    <li class="1"
                        style="margin-left:20.97pt; text-indent:0pt; text-align:left; widows:0; orphans:0; padding-left:15.03pt; font-family:Calibri; font-size:10.5pt">
                        <span style="font-family:Calibri; font-size:10.5pt">UI </span><span
                            style="font-family:Calibri; font-size:10.5pt">improvement, add Fast Check, Free Check, </span><span
                            style="font-family:Calibri; font-size:10.5pt">All Check, Basic Check.</span></li>
                    <li class="1"
                        style="margin-left:20.97pt; text-indent:0pt; text-align:left; widows:0; orphans:0; padding-left:15.03pt; font-family:Calibri; font-size:10.5pt">
                        <span style="font-family:Calibri; font-size:10.5pt">Add </span><span
                            style="font-family:Calibri; font-size:10.5pt">file server data storage</span><span
                            style="font-family:Calibri; font-size:10.5pt">.</span></li>
                    <li class="1"
                        style="margin-left:20.97pt; text-indent:0pt; text-align:left; widows:0; orphans:0; padding-left:15.03pt; font-family:Calibri; font-size:10.5pt">
                        <span style="font-family:Calibri; font-size:10.5pt">APK buil</span><span
                            style="font-family:Calibri; font-size:10.5pt">t-in, internal and external </span><span
                            style="font-family:Calibri; font-size:10.5pt">version unified into a service</span><span
                            style="font-family:Calibri; font-size:10.5pt">.</span></li>
                    <li class="1"
                        style="margin-left:20.97pt; text-indent:0pt; text-align:left; widows:0; orphans:0; padding-left:15.03pt; font-family:Calibri; font-size:10.5pt">
                        <span style="font-family:Calibri; font-size:10.5pt">Adjust check items.</span></li>
                    <li class="1"
                        style="margin-left:20.97pt; text-indent:0pt; text-align:left; widows:0; orphans:0; padding-left:15.03pt; font-family:Calibri; font-size:10.5pt">
                        <span style="font-family:Calibri; font-size:10.5pt">Add </span><span
                            style="font-family:Calibri; font-size:10.5pt">the power consumption fever data detection</span><span
                            style="font-family:Calibri; font-size:10.5pt">.</span></li>
                    <li class="1"
                        style="margin-left:20.97pt; text-indent:0pt; text-align:left; widows:0; orphans:0; padding-left:15.03pt; font-family:Calibri; font-size:10.5pt">
                        <span style="font-family:Calibri; font-size:10.5pt">Optimizes the display of the </span><span
                            style="font-family:Calibri; font-size:10.5pt">check</span><span
                            style="font-family:Calibri; font-size:10.5pt"> results</span><span
                            style="font-family:Calibri; font-size:10.5pt">.</span></li>
                    <li class="1"
                        style="margin-left:20.97pt; text-indent:0pt; text-align:left; widows:0; orphans:0; padding-left:15.03pt; font-family:Calibri; font-size:10.5pt">
                        <span style="font-family:Calibri; font-size:10.5pt">I</span><span
                            style="font-family:Calibri; font-size:10.5pt">ntegrated MTK PA detection</span><span
                            style="font-family:Calibri; font-size:10.5pt">.</span></li>
                    <li class="1"
                        style="margin-left:20.97pt; text-indent:0pt; text-align:left; widows:0; orphans:0; padding-left:15.03pt; font-family:Calibri; font-size:10.5pt">
                        <span style="font-family:Calibri; font-size:10.5pt">The acquisition of machine code has been optimized</span><span
                            style="font-family:Calibri; font-size:10.5pt">.</span></li>
                </ol>
            </td>
            <td style="background-color:#ffffff; border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:87.55pt">
                <p style="text-align:left; widows:0; orphans:0"><span style="font-family:Calibri; font-size:10.5pt">&#xa0;</span>
                </p>
            </td>
        </tr>
        <tr>
            <td style="background-color:#d3dfee; border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:62.1pt">
                <p style="text-align:left; widows:0; orphans:0"><strong><span
                        style="font-family:Cambria; font-size:10.5pt; ">V3.0.6</span></strong></p>
            </td>
            <td style="background-color:#d3dfee; border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:276.45pt">
                <ol start="9" type="1" style="margin:0pt; padding-left:0pt">
                    <li class="1"
                        style="margin-left:20.97pt; text-indent:0pt; text-align:left; widows:0; orphans:0; padding-left:15.03pt; font-family:Calibri; font-size:10.5pt">
                        <span style="font-family:Calibri; font-size:10.5pt">Upload Android Log file Automatic</span>
                    </li>
                    <li class="1"
                        style="margin-left:26.29pt; text-indent:0pt; text-align:left; widows:0; orphans:0; padding-left:9.71pt; font-family:Calibri; font-size:10.5pt">
                        <span style="font-family:Calibri; font-size:10.5pt">Compatible with Domestic Overseas and Internal Special Edition</span>
                    </li>
                    <li class="1"
                        style="margin-left:26.29pt; text-indent:0pt; text-align:left; widows:0; orphans:0; padding-left:9.71pt; font-family:Calibri; font-size:10.5pt">
                        <span style="font-family:Calibri; font-size:10.5pt">Only Single Application Should be Run for SDA</span>
                    </li>
                    <li class="1"
                        style="margin-left:26.29pt; text-indent:0pt; text-align:left; widows:0; orphans:0; padding-left:9.71pt; font-family:Calibri; font-size:10.5pt">
                        <span style="font-family:Calibri; font-size:10.5pt">Set SDA Default language through the windows local language</span>
                    </li>
                </ol>
            </td>
            <td style="background-color:#d3dfee; border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:87.55pt">
                <p style="text-align:left; widows:0; orphans:0"><span style="font-family:Calibri; font-size:10.5pt">2017-08-31</span>
                </p>
            </td>
        </tr>
        <tr>
            <td style="background-color:#d3dfee; border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:62.1pt">
                <p style="text-align:left; widows:0; orphans:0"><strong><span
                        style="font-family:Cambria; font-size:10.5pt; ">V3.0.5</span></strong></p>
            </td>
            <td style="background-color:#d3dfee; border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:276.45pt">
                <ol start="13" type="1" style="margin:0pt; padding-left:0pt">
                    <li class="1"
                        style="margin-left:26.29pt; text-indent:0pt; text-align:left; widows:0; orphans:0; padding-left:9.71pt; font-family:Calibri; font-size:10.5pt">
                        <span style="font-family:Calibri; font-size:10.5pt">A small version to unify apk name</span>
                    </li>
                </ol>
            </td>
            <td style="background-color:#d3dfee; border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:87.55pt">
                <p style="text-align:left; widows:0; orphans:0"><span style="font-family:Calibri; font-size:10.5pt">2017-05-19</span>
                </p>
            </td>
        </tr>
        <tr>
            <td style="border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:62.1pt">
                <p style="text-align:left; widows:0; orphans:0"><strong><span
                        style="font-family:Cambria; font-size:10.5pt; ">V3.0.4</span></strong></p>
            </td>
            <td style="border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:276.45pt">
                <ol type="1" style="margin:0pt; padding-left:0pt">
                    <li class="1"
                        style="margin-left:20.97pt; text-indent:0pt; text-align:left; widows:0; orphans:0; padding-left:15.03pt; font-family:Calibri; font-size:10.5pt">
                        <span style="font-family:Calibri; font-size:10.5pt">Remove Login Function.</span></li>
                    <li class="1"
                        style="margin-left:20.97pt; text-indent:0pt; text-align:left; widows:0; orphans:0; padding-left:15.03pt; font-family:Calibri; font-size:10.5pt">
                        <span style="font-family:Calibri; font-size:10.5pt">Add device id for adb.</span></li>
                    <li class="1"
                        style="margin-left:20.97pt; text-indent:0pt; text-align:left; widows:0; orphans:0; padding-left:15.03pt; font-family:Calibri; font-size:10.5pt">
                        <span style="font-family:Calibri; font-size:10.5pt">Open Check Report when Double Click.</span>
                    </li>
                    <li class="1"
                        style="margin-left:20.97pt; text-indent:0pt; text-align:left; widows:0; orphans:0; padding-left:15.03pt; font-family:Calibri; font-size:10.5pt">
                        <span style="font-family:Calibri; font-size:10.5pt">Multi-Language settings send to Phone</span>
                    </li>
                    <li class="1"
                        style="margin-left:20.97pt; text-indent:0pt; text-align:left; widows:0; orphans:0; padding-left:15.03pt; font-family:Calibri; font-size:10.5pt">
                        <span style="font-family:Calibri; font-size:10.5pt">Analyze log in bbkpanic and bbk_log/log/com.* for QCOM platform</span>
                    </li>
                    <li class="1"
                        style="margin-left:20.97pt; text-indent:0pt; text-align:left; widows:0; orphans:0; padding-left:15.03pt; font-family:Calibri; font-size:10.5pt">
                        <span style="font-family:Calibri; font-size:10.5pt">Add tooltip for </span><span
                            style="font-family:Calibri; font-size:10.5pt">“</span><span
                            style="font-family:Calibri; font-size:10.5pt">recomments</span><span
                            style="font-family:Calibri; font-size:10.5pt">”</span><span
                            style="font-family:Calibri; font-size:10.5pt"> in check result table.</span></li>
                    <li class="1"
                        style="margin-left:20.97pt; text-indent:0pt; text-align:left; widows:0; orphans:0; padding-left:15.03pt; font-family:Calibri; font-size:10.5pt">
                        <span style="font-family:Calibri; font-size:10.5pt">Precise detection for Android device in windows</span>
                    </li>
                </ol>
            </td>
            <td style="border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:87.55pt">
                <p style="text-align:left; widows:0; orphans:0"><span style="font-family:Calibri; font-size:10.5pt">2017-05-18</span>
                </p>
            </td>
        </tr>
        <tr>
            <td style="background-color:#d3dfee; border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:62.1pt">
                <p style="text-align:left; widows:0; orphans:0"><strong><span
                        style="font-family:Cambria; font-size:10.5pt; ">V3.0.3</span></strong></p>
            </td>
            <td style="background-color:#d3dfee; border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:276.45pt">
                <ol type="1" style="margin:0pt; padding-left:0pt">
                    <li class="1"
                        style="margin-left:20.97pt; text-indent:0pt; text-align:left; widows:0; orphans:0; padding-left:15.03pt; font-family:Calibri; font-size:10.5pt">
                        <span style="font-family:Calibri; font-size:10.5pt">Add user login guide</span></li>
                    <li class="1"
                        style="margin-left:20.97pt; text-indent:0pt; text-align:left; widows:0; orphans:0; padding-left:15.03pt; font-family:Calibri; font-size:10.5pt">
                        <span style="font-family:Calibri; font-size:10.5pt">A</span><span
                            style="font-family:Calibri; font-size:10.5pt">dd session Store Funtion</span></li>
                    <li class="1"
                        style="margin-left:20.97pt; text-indent:0pt; text-align:left; widows:0; orphans:0; padding-left:15.03pt; font-family:Calibri; font-size:10.5pt">
                        <span style="font-family:Calibri; font-size:10.5pt">Auto Connect after APK installed.</span>
                    </li>
                    <li class="1"
                        style="margin-left:20.97pt; text-indent:0pt; text-align:left; widows:0; orphans:0; padding-left:15.03pt; font-family:Calibri; font-size:10.5pt">
                        <span style="font-family:Calibri; font-size:10.5pt">Check Report Generated automatically and upload download functions.</span>
                    </li>
                    <li class="1"
                        style="margin-left:20.97pt; text-indent:0pt; text-align:left; widows:0; orphans:0; padding-left:15.03pt; font-family:Calibri; font-size:10.5pt">
                        <span style="font-family:Calibri; font-size:10.5pt">SDA configrations from Server.</span></li>
                    <li class="1"
                        style="margin-left:20.97pt; text-indent:0pt; text-align:left; widows:0; orphans:0; padding-left:15.03pt; font-family:Calibri; font-size:10.5pt">
                        <span style="font-family:Calibri; font-size:10.5pt">User prompts for USB Debug</span></li>
                    <li class="1"
                        style="margin-left:20.97pt; text-indent:0pt; text-align:left; widows:0; orphans:0; padding-left:15.03pt; font-family:Calibri; font-size:10.5pt">
                        <span style="font-family:Calibri; font-size:10.5pt">APK can be Uninstalled.</span></li>
                    <li class="1"
                        style="margin-left:20.97pt; text-indent:0pt; text-align:left; widows:0; orphans:0; padding-left:15.03pt; font-family:Calibri; font-size:10.5pt">
                        <span style="font-family:Calibri; font-size:10.5pt">Optimize some bugs.</span></li>
                </ol>
            </td>
            <td style="background-color:#d3dfee; border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:87.55pt">
                <p style="text-align:left; widows:0; orphans:0"><span style="font-family:Calibri; font-size:10.5pt">2017-02-28</span>
                </p>
            </td>
        </tr>
        <tr>
            <td style="border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:62.1pt">
                <p style="text-align:left; widows:0; orphans:0"><strong><span
                        style="font-family:Cambria; font-size:10.5pt; ">V3.0.2</span></strong></p>
            </td>
            <td style="border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:276.45pt">
                <ol type="1" style="margin:0pt; padding-left:0pt">
                    <li class="1"
                        style="margin-left:20.97pt; text-indent:0pt; text-align:left; widows:0; orphans:0; padding-left:15.03pt; font-family:Calibri; font-size:10.5pt">
                        <span style="font-family:Calibri; font-size:10.5pt">Clean temporarily unpack dir in androidlog</span>
                    </li>
                </ol>
            </td>
            <td style="border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:87.55pt">
                <p style="text-align:left; widows:0; orphans:0"><span style="font-family:Calibri; font-size:10.5pt">2016-12-20</span>
                </p>
            </td>
        </tr>
        <tr>
            <td style="background-color:#d3dfee; border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:62.1pt">
                <p style="text-align:left; widows:0; orphans:0"><strong><span
                        style="font-family:Cambria; font-size:10.5pt; ">V3.0.0</span></strong></p>
            </td>
            <td style="background-color:#d3dfee; border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:276.45pt">
                <p style="text-align:left; widows:0; orphans:0"><span style="font-family:Calibri; font-size:10.5pt">New Design named SDA</span>
                </p>
                <ol type="1" style="margin:0pt; padding-left:0pt">
                    <li class="1"
                        style="margin-left:20.97pt; text-indent:0pt; text-align:left; widows:0; orphans:0; padding-left:15.03pt; font-family:Calibri; font-size:10.5pt">
                        <span style="font-family:Calibri; font-size:10.5pt">Reuse Authentication Information for AFTool</span><span
                            style="font-family:宋体; font-size:10.5pt">，</span><span
                            style="font-family:Calibri; font-size:10.5pt">and Add Login Funtion.</span></li>
                    <li class="1"
                        style="margin-left:20.97pt; text-indent:0pt; text-align:left; widows:0; orphans:0; padding-left:15.03pt; font-family:Calibri; font-size:10.5pt">
                        <span style="font-family:Calibri; font-size:10.5pt">Monitor device inserted and connect Phone Autamatically.</span>
                    </li>
                    <li class="1"
                        style="margin-left:20.97pt; text-indent:0pt; text-align:left; widows:0; orphans:0; padding-left:15.03pt; font-family:Calibri; font-size:10.5pt">
                        <span style="font-family:Calibri; font-size:10.5pt">APK Download Automatically and silent installtion</span><span
                            style="font-family:Calibri; font-size:10.5pt"> </span></li>
                    <li class="1"
                        style="margin-left:20.97pt; text-indent:0pt; text-align:left; widows:0; orphans:0; padding-left:15.03pt; font-family:Calibri; font-size:10.5pt">
                        <span style="font-family:Calibri; font-size:10.5pt">Check Items Customize and Check one by one.</span>
                    </li>
                    <li class="1"
                        style="margin-left:20.97pt; text-indent:0pt; text-align:left; widows:0; orphans:0; padding-left:15.03pt; font-family:Calibri; font-size:10.5pt">
                        <span style="font-family:Calibri; font-size:10.5pt">Generat report and Manage it on Server</span>
                    </li>
                    <li class="1"
                        style="margin-left:20.97pt; text-indent:0pt; text-align:left; widows:0; orphans:0; padding-left:15.03pt; font-family:Calibri; font-size:10.5pt">
                        <span style="font-family:Calibri; font-size:10.5pt">U</span><span
                            style="font-family:Calibri; font-size:10.5pt">pgrade, including mandatory upgrades and selective upgrades</span>
                    </li>
                    <li class="1"
                        style="margin-left:20.97pt; text-indent:0pt; text-align:left; widows:0; orphans:0; padding-left:15.03pt; font-family:Calibri; font-size:10.5pt">
                        <span style="font-family:Calibri; font-size:10.5pt">U</span><span
                            style="font-family:Calibri; font-size:10.5pt">ser can </span><span
                            style="font-family:Calibri; font-size:10.5pt">Issue for</span><span
                            style="font-family:Calibri; font-size:10.5pt"> SDA or APK by mail</span><span
                            style="font-family:Calibri; font-size:10.5pt"> </span></li>
                    <li class="1"
                        style="margin-left:20.97pt; text-indent:0pt; text-align:left; widows:0; orphans:0; padding-left:15.03pt; font-family:Calibri; font-size:10.5pt">
                        <span style="font-family:Calibri; font-size:10.5pt">Strong interaction and ease for Use</span>
                    </li>
                </ol>
            </td>
            <td style="background-color:#d3dfee; border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:87.55pt">
                <p style="text-align:left; widows:0; orphans:0"><span style="font-family:Calibri; font-size:10.5pt">2016-11-30</span>
                </p>
            </td>
        </tr>
        <tr>
            <td style="border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:62.1pt">
                <p style="text-align:left; widows:0; orphans:0"><strong><span
                        style="font-family:Cambria; font-size:10.5pt; ">V1.1.0</span></strong></p>
            </td>
            <td style="border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:276.45pt">
                <p style="text-align:left; widows:0; orphans:0"><span style="font-family:Calibri; font-size:10.5pt">A Simple Cersion for ctool</span>
                </p>
            </td>
            <td style="border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:87.55pt">
                <p style="text-align:left; widows:0; orphans:0"><span style="font-family:Calibri; font-size:10.5pt">2016-08-01</span>
                </p>
            </td>
        </tr>
        <tr>
            <td style="background-color:#d3dfee; border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:62.1pt">
                <p style="text-align:left; widows:0; orphans:0"><strong><span
                        style="font-family:Cambria; font-size:10.5pt; ">V0.0.1</span></strong></p>
            </td>
            <td style="background-color:#d3dfee; border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:276.45pt">
                <p style="text-align:left; widows:0; orphans:0"><span style="font-family:Calibri; font-size:10.5pt">Command Line version by Script</span>
                </p>
            </td>
            <td style="background-color:#d3dfee; border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:87.55pt">
                <p style="text-align:left; widows:0; orphans:0"><span style="font-family:Calibri; font-size:10.5pt">unknown</span>
                </p>
            </td>
        </tr>
    </table>
    <p style="text-align:left"><span style="color:#ff0000">&#xa0;</span></p>
    <ol start="2" type="1" style="margin:0pt; padding-left:0pt">
        <li class="1"
            style="margin-top:7.8pt; margin-left:15.83pt; margin-bottom:7.8pt; text-indent:0pt; text-align:left; padding-left:2.02pt; font-family:Calibri; font-size:14pt; font-weight:bold">
            <a name="_Toc499570092"><strong><span
                    style="font-family:Calibri; font-size:14pt; ">Software Environment</span></strong></a></li>
        <ol type="1" style="margin:0pt; padding-left:0pt">
            <li class="1"
                style="margin-left:36pt; text-indent:0pt; text-align:left; font-family:Calibri; font-size:10.5pt"><a
                    name="_Toc499570093"><span style="font-family:Calibri; font-size:10.5pt">Develop Environment</span></a>
            </li>
        </ol>
    </ol>
    <table cellspacing="0" cellpadding="0" style="border-collapse:collapse; margin-left:0pt; width:426.1pt">
        <tr>
            <td style="border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:2.25pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:142pt">
                <p style="margin-top:0pt; margin-bottom:0pt; text-align:left; line-height:normal; widows:0; orphans:0; font-size:10.5pt">
                    <strong><span style="font-family:Cambria; font-size:10.5pt; ">Tool</span></strong></p>
            </td>
            <td style="border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:2.25pt; border-left-color:#000000; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:142.05pt">
                <p style="margin-top:0pt; margin-bottom:0pt; text-align:left; line-height:normal; widows:0; orphans:0; font-size:10.5pt">
                    <strong><span style="font-family:Cambria; font-size:10.5pt; ">Name</span></strong></p>
            </td>
            <td style="border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:2.25pt; border-left-color:#000000; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:142.05pt">
                <p style="margin-top:0pt; margin-bottom:0pt; text-align:left; line-height:normal; widows:0; orphans:0; font-size:10.5pt">
                    <strong><span style="font-family:Cambria; font-size:10.5pt; ">Version</span></strong></p>
            </td>
        </tr>
        <tr>
            <td style="background-color:#d3dfee; border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:142pt">
                <p style="text-align:left; widows:0; orphans:0"><strong><span
                        style="font-family:Cambria; font-size:10.5pt; ">OS</span></strong></p>
            </td>
            <td style="background-color:#d3dfee; border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:142.05pt">
                <p style="text-align:left; widows:0; orphans:0"><span style="font-family:Calibri; font-size:10.5pt">Windows</span>
                </p>
            </td>
            <td style="background-color:#d3dfee; border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:142.05pt">
                <p style="text-align:left; widows:0; orphans:0"><span
                        style="font-family:Calibri; font-size:10.5pt">W</span><span
                        style="font-family:Calibri; font-size:10.5pt">in7</span></p>
            </td>
        </tr>
        <tr>
            <td style="border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:142pt">
                <p style="text-align:left; widows:0; orphans:0"><strong><span
                        style="font-family:Cambria; font-size:10.5pt; ">Compiler</span></strong></p>
            </td>
            <td style="border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:142.05pt">
                <p style="text-align:left; widows:0; orphans:0"><span style="font-family:Calibri; font-size:10.5pt">Microsoft Visual C++ 2013</span>
                </p>
            </td>
            <td style="border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:142.05pt">
                <p style="text-align:left; widows:0; orphans:0"><span style="font-family:Calibri; font-size:10.5pt">vs2013</span>
                </p>
            </td>
        </tr>
        <tr>
            <td style="background-color:#d3dfee; border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:142pt">
                <p style="text-align:left; widows:0; orphans:0"><strong><span
                        style="font-family:Cambria; font-size:10.5pt; ">Framwork</span></strong></p>
            </td>
            <td style="background-color:#d3dfee; border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:142.05pt">
                <p style="text-align:left; widows:0; orphans:0"><span
                        style="font-family:Calibri; font-size:10.5pt">QT</span></p>
            </td>
            <td style="background-color:#d3dfee; border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:142.05pt">
                <p style="text-align:left; widows:0; orphans:0"><span style="font-family:Calibri; font-size:10.5pt">QT5.7</span>
                </p>
            </td>
        </tr>
    </table>
    <p style="text-align:center">（table 2-1 develop environment）</p>
    <ol start="2" type="1" style="margin:0pt; padding-left:0pt">
        <ol start="2" type="1" style="margin:0pt; padding-left:0pt">
            <li class="1"
                style="margin-left:36pt; text-indent:0pt; text-align:left; font-family:Calibri; font-size:10.5pt"><a
                    name="_Toc499570094"><span style="font-family:Calibri; font-size:10.5pt">Running Environment</span></a>
            </li>
        </ol>
    </ol>
    <table cellspacing="0" cellpadding="0" style="border-collapse:collapse; margin-left:0pt; width:426.1pt">
        <tr>
            <td style="border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:2.25pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:97.55pt">
                <p style="margin-top:0pt; margin-bottom:0pt; text-align:left; line-height:normal; widows:0; orphans:0; font-size:10.5pt">
                    <strong><span style="font-family:Cambria; font-size:10.5pt; ">CPU</span></strong></p>
            </td>
            <td style="border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:2.25pt; border-left-color:#000000; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:155.95pt">
                <p style="margin-top:0pt; margin-bottom:0pt; text-align:left; line-height:normal; widows:0; orphans:0; font-size:10.5pt">
                    <strong><span style="font-family:Cambria; font-size:10.5pt; ">Intel @ 1.80GHz</span></strong></p>
            </td>
            <td style="border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:2.25pt; border-left-color:#000000; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:172.6pt">
                <p style="margin-top:0pt; margin-bottom:0pt; text-align:left; line-height:normal; widows:0; orphans:0; font-size:10.5pt">
                    <strong><span style="font-family:Cambria; font-size:10.5pt; ">CPUfrequency higher is better.</span></strong>
                </p>
            </td>
        </tr>
        <tr>
            <td style="background-color:#d3dfee; border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:97.55pt">
                <p style="text-align:left; widows:0; orphans:0"><strong><span
                        style="font-family:Cambria; font-size:10.5pt; ">Disk</span></strong></p>
            </td>
            <td style="background-color:#d3dfee; border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:155.95pt">
                <p style="text-align:left; widows:0; orphans:0"><span
                        style="font-family:Calibri; font-size:10.5pt">128G</span></p>
            </td>
            <td style="background-color:#d3dfee; border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:172.6pt">
                <p style="text-align:left; widows:0; orphans:0"><span style="font-family:Calibri; font-size:10.5pt">&#xa0;</span>
                </p>
            </td>
        </tr>
        <tr>
            <td style="border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:97.55pt">
                <p style="text-align:left; widows:0; orphans:0"><strong><span
                        style="font-family:Cambria; font-size:10.5pt; ">Memory</span></strong></p>
            </td>
            <td style="border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:155.95pt">
                <p style="text-align:left; widows:0; orphans:0"><span
                        style="font-family:Calibri; font-size:10.5pt">1G</span></p>
            </td>
            <td style="border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:172.6pt">
                <p style="text-align:left; widows:0; orphans:0"><span style="font-family:Calibri; font-size:10.5pt">&#xa0;</span>
                </p>
            </td>
        </tr>
        <tr>
            <td style="background-color:#d3dfee; border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:97.55pt">
                <p style="text-align:left; widows:0; orphans:0"><strong><span
                        style="font-family:Cambria; font-size:10.5pt; ">Displayer</span></strong></p>
            </td>
            <td style="background-color:#d3dfee; border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:155.95pt">
                <p style="text-align:left; widows:0; orphans:0"><span style="font-family:Calibri; font-size:10.5pt">1024*768</span>
                </p>
            </td>
            <td style="background-color:#d3dfee; border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:172.6pt">
                <p style="text-align:left; widows:0; orphans:0"><span style="font-family:Calibri; font-size:10.5pt">Widescreen display better</span>
                </p>
            </td>
        </tr>
        <tr>
            <td style="border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:97.55pt">
                <p style="text-align:left; widows:0; orphans:0"><strong><span
                        style="font-family:Cambria; font-size:10.5pt; ">Peripherals</span></strong></p>
            </td>
            <td style="border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:155.95pt">
                <p style="text-align:left; widows:0; orphans:0"><span style="font-family:Calibri; font-size:10.5pt">USB interface, keyboard, mouse</span>
                </p>
            </td>
            <td style="border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:172.6pt">
                <p style="text-align:left; widows:0; orphans:0"><span style="font-family:Calibri; font-size:10.5pt">&#xa0;</span>
                </p>
            </td>
        </tr>
        <tr>
            <td style="background-color:#d3dfee; border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:97.55pt">
                <p style="text-align:left; widows:0; orphans:0"><strong><span
                        style="font-family:Cambria; font-size:10.5pt; ">Network Bandwidth</span></strong></p>
            </td>
            <td style="background-color:#d3dfee; border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:155.95pt">
                <p style="text-align:left; widows:0; orphans:0"><span style="font-family:Calibri; font-size:10.5pt">Requires 512K bandwidth; recommended 2M or more</span>
                </p>
            </td>
            <td style="background-color:#d3dfee; border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:172.6pt">
                <p style="text-align:left; widows:0; orphans:0"><span style="font-family:Calibri; font-size:10.5pt">&#xa0;</span>
                </p>
            </td>
        </tr>
        <tr>
            <td style="border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:97.55pt">
                <p style="text-align:left; widows:0; orphans:0"><strong><span
                        style="font-family:Cambria; font-size:10.5pt; ">OS</span></strong></p>
            </td>
            <td style="border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:155.95pt">
                <p style="text-align:left; widows:0; orphans:0"><span style="font-family:Calibri; font-size:10.5pt">Windows 2000/2003/XP/Vista/Windows7</span>
                </p>
            </td>
            <td style="border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:172.6pt">
                <p style="text-align:left; widows:0; orphans:0"><span style="font-family:Calibri; font-size:10.5pt">Compatible with 32-bit and 64-bit systems</span>
                </p>
            </td>
        </tr>
    </table>
    <p style="text-align:center">（table 2-2 running environment）</p>
    <ol start="3" type="1" style="margin:0pt; padding-left:0pt">
        <li class="1"
            style="margin-top:7.8pt; margin-left:15.83pt; margin-bottom:7.8pt; text-indent:0pt; text-align:left; padding-left:2.02pt; font-family:Calibri; font-size:14pt; font-weight:bold">
            <a name="_Toc499570095"><strong><span style="font-family:Calibri; font-size:14pt; ">Installation and Uninstalltion</span></strong></a>
        </li>
        <ol type="1" style="margin:0pt; padding-left:0pt">
            <li class="1"
                style="margin-left:36pt; text-indent:0pt; text-align:left; font-family:Calibri; font-size:10.5pt"><a
                    name="_Toc499570096"><span style="font-family:Calibri; font-size:10.5pt">Installation</span></a>
            </li>
        </ol>
    </ol>
    <p style="text-indent:18pt; text-align:left">After unpacked, double click SDA.exe, then enter installation.</p>
    <p style="text-indent:18pt">Chose Installation Language, then click “next” to complete the process.</p>
    <p style="text-align:center">
        <img src="${contextPath}/static/assets/images/vivo_sda_user_guide_env0.8/vivo_sda_user_guide_env0.8.003.jpg" width="554" height="396" alt=""/>
        <img src="${contextPath}/static/assets/images/vivo_sda_user_guide_env0.8/vivo_sda_user_guide_env0.8.004.jpg" width="554" height="393" alt=""/>
    </p>
    <p style="text-align:center">
        <img src="${contextPath}/static/assets/images/vivo_sda_user_guide_env0.8/vivo_sda_user_guide_env0.8.005.jpg" width="554" height="395" alt=""/>
        <img src="${contextPath}/static/assets/images/vivo_sda_user_guide_env0.8/vivo_sda_user_guide_env0.8.006.jpg" width="554" height="392" alt=""/>
    </p>
    <p style="margin-left:5.25pt; text-indent:-5.25pt">              SDA will be installed in C:\SDA（<strong><span
            style="color:#ff0000">Please don</span></strong><strong><span
            style="color:#ff0000">’</span></strong><strong><span style="color:#ff0000">t Modify Installaton Path</span></strong>），add
        a shortcut on并在desktop, system menu is also generated.</p>
    <p>              </p>
    <p>              So, Installation is Completed Now.</p>
    <p>&#xa0;</p>
    <ol start="2" type="1" style="margin:0pt; padding-left:0pt">
        <ol start="2" type="1" style="margin:0pt; padding-left:0pt">
            <li class="1"
                style="margin-left:36pt; text-indent:0pt; text-align:left; font-family:Calibri; font-size:10.5pt"><a
                    name="_Toc499570097"><span style="font-family:Calibri; font-size:10.5pt">Uninstallation</span></a>
            </li>
        </ol>
    </ol>
    <p style="margin-left:17.85pt; text-align:left">Enter Click C:\SDA, double click unins000.exe, then SDA will be
        uninstalled.</p>
    <p style="text-align:left">&#xa0;</p>
    <ol start="4" type="1" style="margin:0pt; padding-left:0pt">
        <li class="1"
            style="margin-top:7.8pt; margin-left:15.83pt; margin-bottom:7.8pt; text-indent:0pt; text-align:left; padding-left:2.02pt; font-family:Calibri; font-size:14pt; font-weight:bold">
            <a name="_Toc499570098"><strong><span
                    style="font-family:Calibri; font-size:14pt; ">User Authentication</span></strong></a></li>
        <ol type="1" style="margin:0pt; padding-left:0pt">
            <li class="1"
                style="margin-left:36pt; text-indent:0pt; text-align:left; font-family:Calibri; font-size:10.5pt"><a
                    name="_Toc499570099"><span style="font-family:Calibri; font-size:10.5pt">User Interface</span></a>
            </li>
        </ol>
    </ol>
    <p style="text-align:center">
        <img src="${contextPath}/static/assets/images/vivo_sda_user_guide_env0.8/vivo_sda_user_guide_env0.8.007.jpg" width="553" height="311" alt=""/>
    </p>
    <p style="text-align:left">&#xa0;</p>
    <ol start="2" type="1" style="margin:0pt; padding-left:0pt">
        <ol start="2" type="1" style="margin:0pt; padding-left:0pt">
            <li class="1"
                style="margin-left:36pt; text-indent:0pt; text-align:left; font-family:Calibri; font-size:10.5pt"><a
                    name="_Toc499570100"><span style="font-family:Calibri; font-size:10.5pt">Cetification Process</span></a>
            </li>
        </ol>
    </ol>
    <p class="1" style="margin-left:36pt; text-indent:0pt; text-align:left">If you computer has certificated by AFToll,
        Not need this. Use SDA directory.</p>
    <ol type="1" style="margin:0pt; padding-left:0pt">
        <ol type="1" style="margin:0pt; padding-left:0pt">
            <ol type="1" style="margin:0pt; padding-left:0pt">
                <li class="1"
                    style="margin-left:62.27pt; text-indent:0pt; text-align:left; padding-left:9.73pt; font-family:Calibri; font-size:10.5pt">
                    <a name="_Toc499570101"><span
                            style="font-family:Calibri; font-size:10.5pt">Obtain Verification Code</span></a></li>
            </ol>
        </ol>
    </ol>
    <p style="text-align:center">
        <img src="${contextPath}/static/assets/images/vivo_sda_user_guide_env0.8/vivo_sda_user_guide_env0.8.008.jpg" width="554" height="373" alt=""/>
    </p>
    <p style="text-align:center">&#xa0;</p>
    <p style="text-align:center">
        <img src="${contextPath}/static/assets/images/vivo_sda_user_guide_env0.8/vivo_sda_user_guide_env0.8.009.jpg" width="554" height="374" alt=""/>
    </p>
    <p style="text-align:center">
        <img src="${contextPath}/static/assets/images/vivo_sda_user_guide_env0.8/vivo_sda_user_guide_env0.8.010.jpg" width="554" height="150" alt=""/>
    </p>
    <ol start="2" type="1" style="margin:0pt; padding-left:0pt">
        <ol start="2" type="1" style="margin:0pt; padding-left:0pt">
            <ol start="2" type="1" style="margin:0pt; padding-left:0pt">
                <li class="1"
                    style="margin-left:62.27pt; text-indent:0pt; text-align:left; padding-left:9.73pt; font-family:Calibri; font-size:10.5pt">
                    <a name="_Toc499570102"><span
                            style="font-family:Calibri; font-size:10.5pt">Launch Certification</span></a></li>
            </ol>
        </ol>
    </ol>
    <p style="text-align:center">
        <img src="${contextPath}/static/assets/images/vivo_sda_user_guide_env0.8/vivo_sda_user_guide_env0.8.011.jpg" width="554" height="377" alt=""/>
    </p>
    <p style="text-align:center">
        <img src="${contextPath}/static/assets/images/vivo_sda_user_guide_env0.8/vivo_sda_user_guide_env0.8.012.jpg" width="554" height="367" alt=""/>
    </p>
    <p style="text-align:left">&#xa0;</p>
    <p style="margin-left:18pt; text-align:left">If Certificated, You will get an Email like this: </p>
    <p style="text-align:center">
        <img src="${contextPath}/static/assets/images/vivo_sda_user_guide_env0.8/vivo_sda_user_guide_env0.8.013.jpg" width="423" height="199" alt=""/>
    </p>
    <p style="margin-left:18pt; text-align:left">Restart SDA, Regist button is Grey.</p>
    <p style="text-align:center">
        <img src="${contextPath}/static/assets/images/vivo_sda_user_guide_env0.8/vivo_sda_user_guide_env0.8.014.jpg" width="195" height="128" alt=""/>
    </p>
    <p style="text-align:center">
        <img src="${contextPath}/static/assets/images/vivo_sda_user_guide_env0.8/vivo_sda_user_guide_env0.8.015.jpg" width="419" height="36" alt=""/>
    </p>
    <p style="text-align:left">&#xa0;</p>
    <ol start="5" type="1" style="margin:0pt; padding-left:0pt">
        <li class="1"
            style="margin-top:7.8pt; margin-left:15.83pt; margin-bottom:7.8pt; text-indent:0pt; text-align:left; padding-left:2.02pt; font-family:Calibri; font-size:14pt; font-weight:bold">
            <a name="_Toc499570103"><strong><span style="font-family:Calibri; font-size:14pt; ">Phone Connection</span></strong></a>
        </li>
        <ol type="1" style="margin:0pt; padding-left:0pt">
            <li class="1"
                style="margin-left:36pt; text-indent:0pt; text-align:left; font-family:Calibri; font-size:10.5pt"><a
                    name="_Toc499570104"><span style="font-family:Calibri; font-size:10.5pt">Debug Port</span></a></li>
        </ol>
    </ol>
    <p style="text-align:left">If Disconnected or USB Debug port not open, this dialog will show：</p>
    <p style="text-align:center">
        <img src="${contextPath}/static/assets/images/vivo_sda_user_guide_env0.8/vivo_sda_user_guide_env0.8.016.jpg" width="387" height="217" alt=""/>
    </p>
    <p style="text-align:left">Click “How<span style="color:#c0c0c0"> </span>to<span style="color:#c0c0c0"> </span>Open?”,
        then USB debug port open steps will show：</p>
    <p style="text-align:center">
        <img src="${contextPath}/static/assets/images/vivo_sda_user_guide_env0.8/vivo_sda_user_guide_env0.8.017.jpg" width="554" height="490" alt=""/>
    </p>
    <p style="text-align:left">&#xa0;</p>
    <p style="text-indent:21pt; text-align:left"><strong><span style="color:#ff0000">If usb debug is opened in develop options, but still can not connect to SDA, Please enter </span></strong><strong><span
            style="color:#ff0000">“</span></strong><strong><span
            style="color:#ff0000">*#558#</span></strong><strong><span
            style="color:#ff0000">”</span></strong><strong><span
            style="color:#ff0000">, then goto </span></strong><strong><span
            style="color:#ff0000">“</span></strong><strong><span style="color:#ff0000">Quality verification test</span></strong><strong><span
            style="color:#ff0000">”</span></strong><strong><span
            style="color:#ff0000">, selet </span></strong><strong><span
            style="color:#ff0000">“</span></strong><strong><span
            style="color:#ff0000">Debugging port</span></strong><strong><span
            style="color:#ff0000">”</span></strong><strong><span style="color:#ff0000"> as bellow:</span></strong></p>
    <p style="text-indent:21pt; text-align:center">
        <img src="${contextPath}/static/assets/images/vivo_sda_user_guide_env0.8/vivo_sda_user_guide_env0.8.018.jpg" width="227" height="405" alt=""/>     
        <img src="${contextPath}/static/assets/images/vivo_sda_user_guide_env0.8/vivo_sda_user_guide_env0.8.019.jpg" width="227" height="405" alt=""/></p>
    <p style="text-indent:21pt; text-align:left"><strong><span style="color:#ff0000">After numerous tests, if the two debugging port open, and then select the "always allow the machine debugging", most models can be connected to SDA success.</span></strong>
    </p>
    <p style="text-align:left">&#xa0;</p>
    <ol start="2" type="1" style="margin:0pt; padding-left:0pt">
        <ol start="2" type="1" style="margin:0pt; padding-left:0pt">
            <li class="1"
                style="margin-left:36pt; text-indent:0pt; text-align:left; font-family:Calibri; font-size:10.5pt"><a
                    name="_Toc499570105"><span
                    style="font-family:Calibri; font-size:10.5pt">Manual Connection</span></a></li>
        </ol>
    </ol>
    <p style="text-indent:18pt; text-align:left">Use <strong><span style="color:#0070c0">Alt+C</span></strong>, will
        Connect Phon，ADB communication is slow, Please be patient , it tackes about <strong><span style="color:#ff0000">10 </span></strong>seconds.
    </p>
    <p style="text-indent:18pt; text-align:left">If connection is successful, some promption is printed in status bar,
        then you can get hardware and software information to the phone by <strong><span
                style="color:#0070c0">Alt+M</span></strong></p>
    <ol start="3" type="1" style="margin:0pt; padding-left:0pt">
        <ol start="3" type="1" style="margin:0pt; padding-left:0pt">
            <li class="1"
                style="margin-left:36pt; text-indent:0pt; text-align:left; font-family:Calibri; font-size:10.5pt"><a
                    name="_Toc499570106"><span style="font-family:Calibri; font-size:10.5pt">Automatic Connection</span></a>
            </li>
        </ol>
    </ol>
    <p style="text-indent:18pt; text-align:left">Software detects a mobile phone data line insertion, will connect
        automatically, then access software and hardware information in mobile phone.</p>
    <p style="text-indent:18pt; text-align:left">&#xa0;</p>
    <p style="text-align:center">
        <img src="${contextPath}/static/assets/images/vivo_sda_user_guide_env0.8/vivo_sda_user_guide_env0.8.020.jpg" width="553" height="299" alt=""/>
    </p>
    <p style="text-align:center">
        <img src="${contextPath}/static/assets/images/vivo_sda_user_guide_env0.8/vivo_sda_user_guide_env0.8.021.jpg" width="554" height="300" alt=""/>
    </p>
    <p style="text-align:center">
        <img src="${contextPath}/static/assets/images/vivo_sda_user_guide_env0.8/vivo_sda_user_guide_env0.8.022.jpg" width="554" height="98" alt=""/>
    </p>
    <p style="margin-left:38.85pt; text-indent:3.15pt; text-align:left">If Phone line is Pulled out, this will show：</p>
    <p style="text-align:center">
        <img src="${contextPath}/static/assets/images/vivo_sda_user_guide_env0.8/vivo_sda_user_guide_env0.8.023.jpg" width="554" height="300" alt=""/>
    </p>
    <p style="text-align:left">&#xa0;</p>
    <ol start="6" type="1" style="margin:0pt; padding-left:0pt">
        <li class="1"
            style="margin-top:7.8pt; margin-left:15.83pt; margin-bottom:7.8pt; text-indent:0pt; text-align:left; padding-left:2.02pt; font-family:Calibri; font-size:14pt; font-weight:bold; color:#f79646">
            <a name="_Toc499570107"><strong><span style="font-family:Calibri; font-size:14pt; color:#f79646">APK Installation</span></strong></a>
        </li>
        <ol type="1" style="margin:0pt; padding-left:0pt">
            <li class="1"
                style="margin-left:36pt; text-indent:0pt; text-align:left; font-family:Calibri; font-size:10.5pt; color:#f79646">
                <a name="_Toc499570108"><span
                        style="font-family:Calibri; font-size:10.5pt; color:#f79646">Install APK</span></a></li>
            <ol type="1" style="margin:0pt; padding-left:0pt">
                <li class="1"
                    style="margin-left:62.27pt; text-indent:0pt; text-align:left; padding-left:9.73pt; font-family:Calibri; font-size:10.5pt; color:#f79646">
                    <a name="_Toc499570109"><span style="font-family:Calibri; font-size:10.5pt; color:#f79646">Force Mode</span></a>
                </li>
            </ol>
        </ol>
    </ol>
    <p style="text-indent:21pt; text-align:left"><span style="color:#f79646">P</span><span style="color:#f79646">erform an installation process</span><span
            style="color:#f79646"> Anyway.</span></p>
    <ol start="2" type="1" style="margin:0pt; padding-left:0pt">
        <ol start="2" type="1" style="margin:0pt; padding-left:0pt">
            <ol start="2" type="1" style="margin:0pt; padding-left:0pt">
                <li class="1"
                    style="margin-left:62.27pt; text-indent:0pt; text-align:left; padding-left:9.73pt; font-family:Calibri; font-size:10.5pt; color:#f79646">
                    <a name="_Toc499570110"><span style="font-family:Calibri; font-size:10.5pt; color:#f79646">Version Compare Mode</span></a>
                </li>
            </ol>
        </ol>
    </ol>
    <p class="1" style="margin-left:17.95pt; text-indent:5.25pt; text-align:left"><span style="color:#f79646">Compare APK version, Perform an </span><span
            style="color:#f79646">installation</span><span
            style="color:#f79646"> process if New version is higher.</span></p>
    <ol start="3" type="1" style="margin:0pt; padding-left:0pt">
        <ol start="3" type="1" style="margin:0pt; padding-left:0pt">
            <ol start="3" type="1" style="margin:0pt; padding-left:0pt">
                <li class="1"
                    style="margin-left:62.27pt; text-indent:0pt; text-align:left; padding-left:9.73pt; font-family:Calibri; font-size:10.5pt; color:#f79646">
                    <a name="_Toc499570111"><span style="font-family:Calibri; font-size:10.5pt; color:#f79646">Automatic Installation</span></a>
                </li>
            </ol>
        </ol>
    </ol>
    <p style="margin-left:3.15pt; text-indent:17.85pt; text-align:left"><span style="color:#f79646">When the phone is plugged into the computer, SDA will automatically detect whether the </span><span
            style="color:#f79646">APK</span><span style="color:#f79646"> is installed, and if it is not installed, the APK will be installed automatically. </span><span
            style="color:#ff0000">If the server configuration automatically downloads the application, SDA will automatically detect the new version of the </span><span
            style="color:#ff0000">APK</span><span style="color:#ff0000"> and download it to C: / SDA / dat</span><span
            style="color:#ff0000">a</span><span style="color:#ff0000">.</span></p>
    <ol start="7" type="1" style="margin:0pt; padding-left:0pt">
        <li class="1"
            style="margin-top:7.8pt; margin-left:15.83pt; margin-bottom:7.8pt; text-indent:0pt; text-align:left; padding-left:2.17pt; font-family:Calibri; font-size:14pt; font-weight:bold">
            <a name="_Toc499570112"><strong><span style="font-family:Calibri; font-size:14pt; ">Import and Export CheckItems</span></strong></a>
        </li>
        <ol type="1" style="margin:0pt; padding-left:0pt">
            <li class="1"
                style="margin-left:36pt; text-indent:0pt; text-align:left; font-family:Calibri; font-size:10.5pt"><a
                    name="_Toc499570113"><span style="font-family:Calibri; font-size:10.5pt">Import</span></a></li>
        </ol>
    </ol>
    <p style="text-align:center">
        <img src="${contextPath}/static/assets/images/vivo_sda_user_guide_env0.8/vivo_sda_user_guide_env0.8.024.jpg" width="520" height="215" alt=""/>
    </p>
    <p style="text-align:center">
        <img src="${contextPath}/static/assets/images/vivo_sda_user_guide_env0.8/vivo_sda_user_guide_env0.8.025.jpg" width="554" height="375" alt=""/>
    </p>
    <p style="text-align:left">              This Opration will import check items form mycheck.xml to tree list
        left.</p>
    <ol start="2" type="1" style="margin:0pt; padding-left:0pt">
        <ol start="2" type="1" style="margin:0pt; padding-left:0pt">
            <li class="1"
                style="margin-left:36pt; text-indent:0pt; text-align:left; font-family:Calibri; font-size:10.5pt"><a
                    name="_Toc499570114"><span style="font-family:Calibri; font-size:10.5pt">Export</span></a></li>
        </ol>
    </ol>
    <p style="text-align:center">
        <img src="${contextPath}/static/assets/images/vivo_sda_user_guide_env0.8/vivo_sda_user_guide_env0.8.026.jpg" width="513" height="221" alt=""/>
    </p>
    <p style="text-align:center">
        <img src="${contextPath}/static/assets/images/vivo_sda_user_guide_env0.8/vivo_sda_user_guide_env0.8.027.jpg" width="554" height="377" alt=""/>
    </p>
    <p style="text-indent:17.85pt; text-align:left">This opration will generate C:/ sda/data/ export.xml from tree list
        content left.</p>
    <ol start="8" type="1" style="margin:0pt; padding-left:0pt">
        <li class="1"
            style="margin-top:7.8pt; margin-left:15.83pt; margin-bottom:7.8pt; text-indent:0pt; text-align:left; padding-left:2.02pt; font-family:Calibri; font-size:14pt; font-weight:bold">
            <a name="_Toc499570115"><strong><span
                    style="font-family:Calibri; font-size:14pt; ">Check</span></strong></a></li>
        <ol type="1" style="margin:0pt; padding-left:0pt">
            <li class="1"
                style="margin-left:36pt; text-indent:0pt; text-align:left; font-family:Calibri; font-size:10.5pt"><a
                    name="_Toc499570116"><span
                    style="font-family:Calibri; font-size:10.5pt">Chose Check Items</span></a></li>
        </ol>
    </ol>
    <p style="margin-left:18pt; text-align:left">You can chose check items according your needs.</p>
    <p style="text-align:center">
        <img src="${contextPath}/static/assets/images/vivo_sda_user_guide_env0.8/vivo_sda_user_guide_env0.8.028.jpg" width="268" height="711" alt=""/>
    </p>
    <p style="margin-left:18pt; text-align:left">&#xa0;</p>
    <ol start="2" type="1" style="margin:0pt; padding-left:0pt">
        <ol start="2" type="1" style="margin:0pt; padding-left:0pt">
            <li class="1"
                style="margin-left:36pt; text-indent:0pt; text-align:left; font-family:Calibri; font-size:10.5pt"><a
                    name="_Toc499570117"><span style="font-family:Calibri; font-size:10.5pt">Check</span></a></li>
        </ol>
    </ol>
    <p style="text-indent:18pt; text-align:left">Click check menu，<img
            src="${contextPath}/static/assets/images/vivo_sda_user_guide_env0.8/vivo_sda_user_guide_env0.8.029.jpg" width="43" height="39" alt=""/>，then,
        SDA will start to check the phone, and check result are displayed in the list on the right, it’t real time.</p>
    <p style="text-align:center">&#xa0;</p>
    <p style="text-align:center">
        <img src="${contextPath}/static/assets/images/vivo_sda_user_guide_env0.8/vivo_sda_user_guide_env0.8.030.jpg" width="554" height="172" alt=""/>
    </p>
    <p style="text-align:left">&#xa0;</p>
    <ol type="1" style="margin:0pt; padding-left:0pt">
        <ol type="1" style="margin:0pt; padding-left:0pt">
            <ol type="1" style="margin:0pt; padding-left:0pt">
                <li class="1"
                    style="margin-left:62.27pt; text-indent:0pt; text-align:left; padding-left:9.73pt; font-family:Calibri; font-size:10.5pt">
                    <a name="_Toc499570118"><span style="font-family:Calibri; font-size:10.5pt">Interactive Check</span></a>
                </li>
            </ol>
        </ol>
    </ol>
    <p style="text-align:left"><span style="font-family:宋体; color:#000000">Eighth item, it</span><span
            style="color:#000000">’</span><span style="font-family:宋体; color:#000000">s </span><span
            style="font-family:宋体; color:#000000">Engineering</span><span style="font-family:宋体; color:#000000"> mode test, it</span><span
            style="color:#000000">’</span><span style="color:#000000"> </span><span
            style="font-family:宋体; color:#000000">interactive checker.</span></p>
    <p style="text-align:center">
        <img src="${contextPath}/static/assets/images/vivo_sda_user_guide_env0.8/vivo_sda_user_guide_env0.8.031.jpg" width="387" height="531" alt=""/>
    </p>
    <p style="text-indent:17.85pt; text-align:left"><span style="font-family:宋体; color:#000000">Interactive checker need user to judge the results is correct or abnormal. Such as handwriting test, it will enter the detection mode, as shown below.</span>
    </p>
    <p style="text-align:center">
        <img src="${contextPath}/static/assets/images/vivo_sda_user_guide_env0.8/vivo_sda_user_guide_env0.8.032.jpg" width="207" height="367"/><span
            style="font-family:宋体; color:#000000">         </span><img
            src="${contextPath}/static/assets/images/vivo_sda_user_guide_env0.8/vivo_sda_user_guide_env0.8.033.jpg" width="212" height="362" alt=""/></p>
    <p>              When a checker is started, it will switch from the detection progress display interface to the
        current detection item interface. Maintenance personnel can be tested and verified according to the actual
        situation. Such as handwriting test, need to manually scribe to confirm.</p>
    <p>              After the test is completed, the service personnel need to manually click the return key. Return to
        the "Detection item display" interface. As follows:</p>
    <p style="text-align:center">
        <img src="${contextPath}/static/assets/images/vivo_sda_user_guide_env0.8/vivo_sda_user_guide_env0.8.034.jpg" width="290" height="515"/>
    </p>
    <p style="text-align:left">&#xa0;</p>
    <p style="text-indent:21pt">At this point, need user click phone to select the results, then SDA will display the
        results. As follows.</p>
    <p style="text-align:center">
        <img src="${contextPath}/static/assets/images/vivo_sda_user_guide_env0.8/vivo_sda_user_guide_env0.8.035.jpg" width="554" height="107" alt=""/>
    </p>
    <p><span style="font-family:宋体; color:#000000">&#xa0;</span></p>
    <p style="text-indent:21pt"><span style="font-family:宋体; color:#000000">If the detection interface does not switch, it indicates that the model does not support the </span><span
            style="font-family:宋体; color:#000000">check item</span><span style="font-family:宋体; color:#000000">. Please click the wrong (do not support) button directly. </span><span
            style="font-family:宋体; color:#000000">Then SDA</span><span style="font-family:宋体; color:#000000"> will display:</span>
    </p>
    <p style="text-align:center">
        <img src="${contextPath}/static/assets/images/vivo_sda_user_guide_env0.8/vivo_sda_user_guide_env0.8.036.jpg" width="422" height="34" alt=""/>
    </p>
    <p style="text-align:left">&#xa0;</p>
    <ol start="9" type="1" style="margin:0pt; padding-left:0pt">
        <li class="1"
            style="margin-top:7.8pt; margin-left:15.83pt; margin-bottom:7.8pt; text-indent:0pt; text-align:left; padding-left:2.02pt; font-family:Calibri; font-size:14pt; font-weight:bold">
            <a name="_Toc499570119"><strong><span
                    style="font-family:Calibri; font-size:14pt; ">Histor</span></strong><strong><span
                    style="font-family:Calibri; font-size:14pt; ">ical temperature statistics</span></strong></a></li>
    </ol>
    <p>
        <img src="${contextPath}/static/assets/images/vivo_sda_user_guide_env0.8/vivo_sda_user_guide_env0.8.037.jpg" width="554" height="340" alt=""/>
    </p>
    <p style="text-indent:17.85pt">The historical temperature curve shows the temperature change in one day, the
        abscissa is the time, the ordinate is the temperature.</p>
    <p class="1" style="margin-top:7.8pt; margin-left:17.85pt; margin-bottom:7.8pt; text-indent:0pt; text-align:left"><a
            name="_Toc499570120"><strong><span style="font-size:14pt; ">9.1</span></strong><strong><span
            style="font-size:14pt; ">Date Choose</span></strong></a></p>
    <p>
        <img src="${contextPath}/static/assets/images/vivo_sda_user_guide_env0.8/vivo_sda_user_guide_env0.8.038.jpg" width="546" height="323" alt=""/>
    </p>
    <p style="text-indent:17.85pt">Click the button of calendar for the date selection, double-click the date will show
        the date of the temperature curve.</p>
    <p class="1" style="margin-top:7.8pt; margin-left:17.85pt; margin-bottom:7.8pt; text-indent:0pt; text-align:left"><a
            name="_Toc499570121"><strong><span style="font-size:14pt; ">9.</span></strong><strong><span
            style="font-size:14pt; ">2Tenperature curves switch</span></strong></a></p>
    <p>
        <img src="${contextPath}/static/assets/images/vivo_sda_user_guide_env0.8/vivo_sda_user_guide_env0.8.039.jpg" width="554" height="320" alt=""/>
    </p>
    <p>
        <img src="${contextPath}/static/assets/images/vivo_sda_user_guide_env0.8/vivo_sda_user_guide_env0.8.040.jpg" width="554" height="310" alt=""/>
    </p>
    <p style="text-indent:17.85pt">Click the battery temperature (motherboard temperature) button to select whether to
        display the battery temperature curve, in which the green curve corresponds to the motherboard temperature, and
        the blue curve corresponds to the battery temperature. Red line is the warning line of whether the temperature
        is too high.</p>
    <p class="1" style="margin-top:7.8pt; margin-left:17.85pt; margin-bottom:7.8pt; text-indent:0pt; text-align:left"><a
            name="_Toc499570122"><strong><span style="font-size:14pt; ">9.3Tooltip</span></strong></a></p>
    <p>
        <img src="${contextPath}/static/assets/images/vivo_sda_user_guide_env0.8/vivo_sda_user_guide_env0.8.041.jpg" width="497" height="191" alt=""/>
    </p>
    <p style="text-indent:17.85pt">P is the package, C is cpu usage time, WK for the period of time to lock (s), WR for
        the period WIFI received data (KB), WT for the period of the data sent by WIFI (KB), MR for the period mobile
        received data (KB), MT for the period mobile sent date(KB), G for the time of using GPS, A for the moment
        whether the playback of audio. </p>
    <p class="1" style="margin-top:7.8pt; margin-left:17.85pt; margin-bottom:7.8pt; text-indent:0pt; text-align:left"><a
            name="_Toc499570123"><strong><span style="font-size:14pt; ">9.4</span></strong><strong><span
            style="font-size:14pt; "> Historical temperature advice</span></strong></a></p>
    <p>
        <img src="${contextPath}/static/assets/images/vivo_sda_user_guide_env0.8/vivo_sda_user_guide_env0.8.042.jpg" width="521" height="204" alt=""/>
    </p>
    <p style="text-indent:17.85pt">Statistics of the temperature exceeds the warning line of the date, and when the
        temperature exceeds the warning line, list programs which occupy resources too much and the use of resources,
        and give the corresponding recommendations.</p>
    <ol start="10" type="1" style="margin:0pt; padding-left:0pt">
        <li class="1"
            style="margin-top:7.8pt; margin-left:17.85pt; margin-bottom:7.8pt; text-indent:-17.85pt; text-align:left; font-family:Calibri; font-size:14pt; font-weight:bold; list-style-position:inside">
            <a name="_Toc499570124"><strong><span style="font-family:Calibri; font-size:14pt; ">Report Management</span></strong></a>
        </li>
        <ol type="1" style="margin:0pt; padding-left:0pt">
            <li class="1"
                style="margin-left:36pt; text-indent:-18pt; text-align:left; font-family:Calibri; font-size:10.5pt; list-style-position:inside">
                <span style="font:7.0pt 'Times New Roman'"> </span><a name="_Toc499570125"><span
                    style="font-family:Calibri; font-size:10.5pt">Generate Report</span></a></li>
            <ol type="1" style="margin:0pt; padding-left:0pt">
                <li class="1"
                    style="margin-left:67.59pt; text-indent:0pt; text-align:left; padding-left:4.41pt; font-family:Calibri; font-size:10.5pt">
                    <a name="_Toc499570126"><span
                            style="font-family:Calibri; font-size:10.5pt">Automatic Mode</span></a></li>
            </ol>
        </ol>
    </ol>
    <p style="text-align:left">Once all of the check item has been completed, the report is automatically generated.
        Such as </p>
    <p style="text-align:left">D:/Program Files/vivo_sda_v3.0.0_win32/</p>
    <p style="text-align:left">vivo X6PlusA#256530715800014#2016-10-17-17-01-21#M17A10</p>
    <p style="text-align:center">
        <img src="${contextPath}/static/assets/images/vivo_sda_user_guide_env0.8/vivo_sda_user_guide_env0.8.043.jpg" width="554" height="119" alt=""/>
    </p>
    <p style="text-indent:21pt; text-align:left">CheckReport.xlsx it the check report for this time.</p>
    <p style="text-align:left">&#xa0;</p>
    <ol start="2" type="1" style="margin:0pt; padding-left:0pt">
        <ol start="2" type="1" style="margin:0pt; padding-left:0pt">
            <ol start="2" type="1" style="margin:0pt; padding-left:0pt">
                <li class="1"
                    style="margin-left:67.59pt; text-indent:0pt; text-align:left; padding-left:4.41pt; font-family:Calibri; font-size:10.5pt">
                    <a name="_Toc499570127"><span style="font-family:Calibri; font-size:10.5pt">Manual Mode</span></a>
                </li>
            </ol>
        </ol>
    </ol>
    <p style="text-align:left">Click “Generate” menu or press <strong><span style="color:#0070c0">Alt+7,</span></strong>
        SDA will generate the report.</p>
    <p style="text-align:center">
        <img src="${contextPath}/static/assets/images/vivo_sda_user_guide_env0.8/vivo_sda_user_guide_env0.8.044.jpg" width="520" height="249" alt=""/>
    </p>
    <p style="text-align:left">&#xa0;</p>
    <ol start="2" type="1" style="margin:0pt; padding-left:0pt">
        <ol start="2" type="1" style="margin:0pt; padding-left:0pt">
            <li class="1"
                style="margin-left:36pt; text-indent:-18pt; text-align:left; font-family:Calibri; font-size:10.5pt; list-style-position:inside">
                <span style="font:7.0pt 'Times New Roman'"> </span><a name="_Toc499570128"><span
                    style="font-family:Calibri; font-size:10.5pt">Upload Report</span></a></li>
            <ol type="1" style="margin:0pt; padding-left:0pt">
                <li class="1"
                    style="margin-left:67.59pt; text-indent:0pt; text-align:left; padding-left:4.41pt; font-family:Calibri; font-size:10.5pt">
                    <a name="_Toc499570129"><span
                            style="font-family:Calibri; font-size:10.5pt">Automatic Mode</span></a></li>
            </ol>
        </ol>
    </ol>
    <p style="text-indent:21pt; text-align:left">After the detection is complete, the following dialog will be
        shown.</p>
    <p style="text-align:center">
        <img src="${contextPath}/static/assets/images/vivo_sda_user_guide_env0.8/vivo_sda_user_guide_env0.8.045.jpg" width="554" height="338" alt=""/>
    </p>
    <p style="text-indent:21pt; text-align:left">Click ”Submit” menu, the check report will be submitted to SDA
        server.</p>
    <p style="text-indent:21pt; text-align:center">
        <img src="${contextPath}/static/assets/images/vivo_sda_user_guide_env0.8/vivo_sda_user_guide_env0.8.046.jpg" width="440" height="219" alt=""/>
    </p>
    <p style="text-indent:21pt; text-align:left">If reports generated automatically, it will be uploaded to server. You
        don’t need to do anything.</p>
    <ol start="2" type="1" style="margin:0pt; padding-left:0pt">
        <ol start="2" type="1" style="margin:0pt; padding-left:0pt">
            <ol start="2" type="1" style="margin:0pt; padding-left:0pt">
                <li class="1"
                    style="margin-left:67.59pt; text-indent:0pt; text-align:left; padding-left:4.41pt; font-family:Calibri; font-size:10.5pt">
                    <a name="_Toc499570130"><span style="font-family:Calibri; font-size:10.5pt">Manual Mode</span></a>
                </li>
            </ol>
        </ol>
    </ol>
    <p style="text-indent:23.2pt; text-align:left">Ckick “Upload” menu or press <strong><span style="color:#0070c0">Alt+9</span></strong>,
        the report will be uploaded to server.</p>
    <ol start="3" type="1" style="margin:0pt; padding-left:0pt">
        <ol start="3" type="1" style="margin:0pt; padding-left:0pt">
            <li class="1"
                style="margin-left:36pt; text-indent:-18pt; text-align:left; font-family:Calibri; font-size:10.5pt; list-style-position:inside">
                <span style="font:7.0pt 'Times New Roman'"> </span><a name="_Toc499570131"><span
                    style="font-family:Calibri; font-size:10.5pt">Query Check History</span></a></li>
        </ol>
    </ol>
    <p style="text-align:left">Tools/Report/Query or press <strong><span style="color:#0070c0">Alt+8</span></strong>,
        then query the check history for the phone connected.</p>
    <p style="text-align:center">
        <img src="${contextPath}/static/assets/images/vivo_sda_user_guide_env0.8/vivo_sda_user_guide_env0.8.047.jpg" width="532" height="237" alt=""/>
    </p>
    <p style="text-align:center">
        <img src="${contextPath}/static/assets/images/vivo_sda_user_guide_env0.8/vivo_sda_user_guide_env0.8.048.jpg" width="410" height="240" alt=""/>
    </p>
    <p style="text-align:center">
        <img src="${contextPath}/static/assets/images/vivo_sda_user_guide_env0.8/vivo_sda_user_guide_env0.8.049.jpg" width="553" height="110" alt=""/>
    </p>
    <p>Select a line, double click, then the report will be downloaded to C:/SDA/data.</p>
    <p style="text-align:center">
        <img src="${contextPath}/static/assets/images/vivo_sda_user_guide_env0.8/vivo_sda_user_guide_env0.8.050.jpg" width="553" height="85" alt=""/>
    </p>
    <p>&#xa0;</p>
    <p style="text-align:center">
        <img src="${contextPath}/static/assets/images/vivo_sda_user_guide_env0.8/vivo_sda_user_guide_env0.8.051.jpg" width="554" height="195" alt=""/>
    </p>
    <ol start="11" type="1" style="margin:0pt; padding-left:0pt">
        <li class="1"
            style="margin-top:7.8pt; margin-left:17.85pt; margin-bottom:7.8pt; text-indent:-17.85pt; text-align:left; font-family:Calibri; font-size:14pt; font-weight:bold; list-style-position:inside">
            <a name="_Toc499570132"><strong><span style="font-family:Calibri; font-size:14pt; ">Options</span></strong></a>
        </li>
    </ol>
    <p class="1" style="margin-left:17.85pt; text-indent:0pt; text-align:left">Press Alt+F8, go to options setting
        dialog. </p>
    <p class="1" style="margin-left:17.85pt; text-indent:0pt; text-align:left">&#xa0;</p>
    <ol type="1" style="margin:0pt; padding-left:0pt">
        <ol type="1" style="margin:0pt; padding-left:0pt">
            <li class="1"
                style="margin-left:35.7pt; text-indent:-17.85pt; text-align:left; font-family:Calibri; font-size:10.5pt; list-style-position:inside">
                <span style="font:7.0pt 'Times New Roman'"> </span><a name="_Toc499570133"><span
                    style="font-family:Calibri; font-size:10.5pt">SDA Edition</span></a></li>
        </ol>
    </ol>
    <p style="text-align:center">
        <img src="${contextPath}/static/assets/images/vivo_sda_user_guide_env0.8/vivo_sda_user_guide_env0.8.052.jpg" width="554" height="271" alt=""/>
    </p>
    <ol start="2" type="1" style="margin:0pt; padding-left:0pt">
        <ol start="2" type="1" style="margin:0pt; padding-left:0pt">
            <li class="1"
                style="margin-left:35.7pt; text-indent:-17.85pt; text-align:left; font-family:Calibri; font-size:10.5pt; list-style-position:inside">
                <span style="font:7.0pt 'Times New Roman'"> </span><a name="_Toc499570134"><span
                    style="font-family:Calibri; font-size:10.5pt">UI Style</span></a></li>
        </ol>
    </ol>
    <p style="text-align:center">
        <img src="${contextPath}/static/assets/images/vivo_sda_user_guide_env0.8/vivo_sda_user_guide_env0.8.053.jpg" width="554" height="276" alt=""/>
    </p>
    <ol start="3" type="1" style="margin:0pt; padding-left:0pt">
        <ol start="3" type="1" style="margin:0pt; padding-left:0pt">
            <li class="1"
                style="margin-left:35.7pt; text-indent:-17.85pt; text-align:left; font-family:Calibri; font-size:10.5pt; list-style-position:inside">
                <span style="font:7.0pt 'Times New Roman'"> </span><a name="_Toc499570135"><span
                    style="font-family:Calibri; font-size:10.5pt">Language Setting</span></a></li>
        </ol>
    </ol>
    <p style="text-align:center">
        <img src="${contextPath}/static/assets/images/vivo_sda_user_guide_env0.8/vivo_sda_user_guide_env0.8.054.jpg" width="554" height="267" alt=""/>
    </p>
    <p class="1" style="margin-left:36pt; text-indent:0pt; text-align:left">&#xa0;</p>
    <ol start="4" type="1" style="margin:0pt; padding-left:0pt">
        <ol start="4" type="1" style="margin:0pt; padding-left:0pt">
            <li class="1"
                style="margin-left:35.7pt; text-indent:-17.85pt; text-align:left; font-family:Calibri; font-size:10.5pt; list-style-position:inside">
                <span style="font:7.0pt 'Times New Roman'"> </span><a name="_Toc499570136"><span
                    style="font-family:Calibri; font-size:10.5pt">Check Parameter</span></a></li>
        </ol>
    </ol>
    <p style="text-align:center">
        <img src="${contextPath}/static/assets/images/vivo_sda_user_guide_env0.8/vivo_sda_user_guide_env0.8.055.jpg" width="554" height="271" alt=""/>
    </p>
    <p>              The unit of time is second.</p>
    <p style="margin-left:15.75pt; text-indent:-15.75pt">              <span style="color:#ff0000">【</span><span
            style="color:#ff0000">Check Pamarater</span><span style="color:#ff0000">】</span><span style="color:#ff0000">has been move to server</span><span
            style="color:#ff0000">，</span><span style="color:#ff0000">Managed by </span><span
            style="color:#ff0000">the</span><span style="color:#ff0000"> SDA server, It takes no effect if you set a parameter.</span>
    </p>
    <ol start="5" type="1" style="margin:0pt; padding-left:0pt">
        <ol start="5" type="1" style="margin:0pt; padding-left:0pt">
            <li class="1"
                style="margin-left:35.7pt; text-indent:-17.85pt; text-align:left; font-family:Calibri; font-size:10.5pt; list-style-position:inside">
                <span style="font:7.0pt 'Times New Roman'"> </span><a name="_Toc499570137"><span
                    style="font-family:Calibri; font-size:10.5pt">Misc Setting</span></a></li>
        </ol>
    </ol>
    <p style="text-align:center">
        <img src="${contextPath}/static/assets/images/vivo_sda_user_guide_env0.8/vivo_sda_user_guide_env0.8.056.jpg" width="554" height="275" alt=""/>
    </p>
    <p><span style="color:#ff0000">【</span><span style="color:#ff0000">Misc Setting</span><span
            style="color:#ff0000">】</span><span style="color:#ff0000">has been move to server</span><span
            style="color:#ff0000">，</span><span style="color:#ff0000">Managed by </span><span
            style="color:#ff0000">the</span><span style="color:#ff0000"> SDA server, It takes no effect if you set a parameter.</span>
    </p>
    <ol start="12" type="1" style="margin:0pt; padding-left:0pt">
        <li class="1"
            style="margin-top:7.8pt; margin-left:17.85pt; margin-bottom:7.8pt; text-indent:-17.85pt; text-align:left; font-family:Calibri; font-size:14pt; font-weight:bold; list-style-position:inside">
            <a name="_Toc499570138"><strong><span style="font-family:Calibri; font-size:14pt; ">Upgrade</span></strong></a>
        </li>
    </ol>
    <p style="margin-left:5.25pt; text-indent:12.6pt; text-align:left">SDA will detect whether there is a new version
        automatically, if you want to detect manually, please press F11.</p>
    <p style="text-align:center">
        <img src="${contextPath}/static/assets/images/vivo_sda_user_guide_env0.8/vivo_sda_user_guide_env0.8.057.jpg" width="554" height="367" alt=""/>
    </p>
    <p style="text-align:left">              If there is a new version, the new copy will be downloaded to
        C:/SDA/data.</p>
    <ol start="13" type="1" style="margin:0pt; padding-left:0pt">
        <li class="1"
            style="margin-top:7.8pt; margin-left:17.85pt; margin-bottom:7.8pt; text-indent:-17.85pt; text-align:left; font-family:Calibri; font-size:14pt; font-weight:bold; list-style-position:inside">
            <a name="_Toc499570139"><strong><span style="font-family:Calibri; font-size:14pt; ">Use Help</span></strong></a>
        </li>
    </ol>
    <p class="1" style="margin-left:17.85pt; text-indent:0pt; text-align:left">For help, press F1.</p>
    <ol start="14" type="1" style="margin:0pt; padding-left:0pt">
        <li class="1"
            style="margin-top:7.8pt; margin-left:17.85pt; margin-bottom:7.8pt; text-indent:-17.85pt; text-align:left; font-family:Calibri; font-size:14pt; font-weight:bold; list-style-position:inside">
            <a name="_Toc499570140"><strong><span
                    style="font-family:Calibri; font-size:14pt; ">Shortcuts List</span></strong></a></li>
    </ol>
    <table cellspacing="0" cellpadding="0" style="border-collapse:collapse; margin-left:0pt; width:426.1pt">
        <tr>
            <td style="border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:2.25pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:213.05pt">
                <p style="margin-top:0pt; margin-bottom:0pt; text-align:left; line-height:normal; widows:0; orphans:0; font-size:10.5pt">
                    <strong><span style="font-family:Cambria; font-size:10.5pt; ">Functions</span></strong></p>
            </td>
            <td style="border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:2.25pt; border-left-color:#000000; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:213.05pt">
                <p style="margin-top:0pt; margin-bottom:0pt; text-align:left; line-height:normal; widows:0; orphans:0; font-size:10.5pt">
                    <strong><span style="font-family:Cambria; font-size:10.5pt; ">Shortcuts</span></strong></p>
            </td>
        </tr>
        <tr>
            <td style="background-color:#d3dfee; border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:213.05pt">
                <p style="text-align:left; widows:0; orphans:0"><strong><span
                        style="font-family:Cambria; font-size:10.5pt; ">Help</span></strong><strong><span
                        style="font-family:宋体; font-size:10.5pt; ">（</span></strong><strong><span
                        style="font-family:Cambria; font-size:10.5pt; ">User Guide</span></strong><strong><span
                        style="font-family:宋体; font-size:10.5pt; ">）</span></strong></p>
            </td>
            <td style="background-color:#d3dfee; border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:213.05pt">
                <p style="text-align:left; widows:0; orphans:0"><span
                        style="font-family:Calibri; font-size:10.5pt">F1</span></p>
            </td>
        </tr>
        <tr>
            <td style="border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:213.05pt">
                <p style="text-align:left; widows:0; orphans:0"><strong><span
                        style="font-family:Cambria; font-size:10.5pt; ">Exit</span></strong></p>
            </td>
            <td style="border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:213.05pt">
                <p style="text-align:left; widows:0; orphans:0"><span style="font-family:Calibri; font-size:10.5pt">ALT+E</span>
                </p>
            </td>
        </tr>
        <tr>
            <td style="background-color:#d3dfee; border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:213.05pt">
                <p style="text-align:left; widows:0; orphans:0"><strong><span
                        style="font-family:Cambria; font-size:10.5pt; ">Connect</span></strong></p>
            </td>
            <td style="background-color:#d3dfee; border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:213.05pt">
                <p style="text-align:left; widows:0; orphans:0"><span style="font-family:Calibri; font-size:10.5pt">ALT+C</span>
                </p>
            </td>
        </tr>
        <tr>
            <td style="border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:213.05pt">
                <p style="text-align:left; widows:0; orphans:0"><strong><span
                        style="font-family:Cambria; font-size:10.5pt; ">DisConnect</span></strong></p>
            </td>
            <td style="border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:213.05pt">
                <p style="text-align:left; widows:0; orphans:0"><span style="font-family:Calibri; font-size:10.5pt">ALT+D</span>
                </p>
            </td>
        </tr>
        <tr>
            <td style="background-color:#d3dfee; border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:213.05pt">
                <p style="text-align:left; widows:0; orphans:0"><strong><span
                        style="font-family:Cambria; font-size:10.5pt; ">DeviceInfo</span></strong></p>
            </td>
            <td style="background-color:#d3dfee; border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:213.05pt">
                <p style="text-align:left; widows:0; orphans:0"><span style="font-family:Calibri; font-size:10.5pt">ALT+M</span>
                </p>
            </td>
        </tr>
        <tr>
            <td style="border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:213.05pt">
                <p style="text-align:left; widows:0; orphans:0"><strong><span
                        style="font-family:Cambria; font-size:10.5pt; ">Fask Install Apk</span></strong></p>
            </td>
            <td style="border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:213.05pt">
                <p style="text-align:left; widows:0; orphans:0"><span
                        style="font-family:Calibri; font-size:10.5pt">F</span><span
                        style="font-family:Calibri; font-size:10.5pt">7</span></p>
            </td>
        </tr>
        <tr>
            <td style="background-color:#d3dfee; border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:213.05pt">
                <p style="text-align:left; widows:0; orphans:0"><strong><span
                        style="font-family:Cambria; font-size:10.5pt; ">Install Apk by Version</span></strong></p>
            </td>
            <td style="background-color:#d3dfee; border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:213.05pt">
                <p style="text-align:left; widows:0; orphans:0"><span style="font-family:Calibri; font-size:10.5pt">ALT+F7</span>
                </p>
            </td>
        </tr>
        <tr>
            <td style="border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:213.05pt">
                <p style="text-align:left; widows:0; orphans:0"><strong><span
                        style="font-family:Cambria; font-size:10.5pt; ">Uninstall Apk</span></strong></p>
            </td>
            <td style="border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:213.05pt">
                <p style="text-align:left; widows:0; orphans:0"><span style="font-family:Calibri; font-size:10.5pt">ALT+F6</span>
                </p>
            </td>
        </tr>
        <tr>
            <td style="background-color:#d3dfee; border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:213.05pt">
                <p style="text-align:left; widows:0; orphans:0"><strong><span
                        style="font-family:Cambria; font-size:10.5pt; ">Import Check Items</span></strong></p>
            </td>
            <td style="background-color:#d3dfee; border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:213.05pt">
                <p style="text-align:left; widows:0; orphans:0"><span
                        style="font-family:Calibri; font-size:10.5pt">F9</span></p>
            </td>
        </tr>
        <tr>
            <td style="border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:213.05pt">
                <p style="text-align:left; widows:0; orphans:0"><strong><span
                        style="font-family:Cambria; font-size:10.5pt; ">Export Check Items</span></strong></p>
            </td>
            <td style="border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:213.05pt">
                <p style="text-align:left; widows:0; orphans:0"><span style="font-family:Calibri; font-size:10.5pt">ALT+F9</span>
                </p>
            </td>
        </tr>
        <tr>
            <td style="background-color:#d3dfee; border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:213.05pt">
                <p style="text-align:left; widows:0; orphans:0"><strong><span
                        style="font-family:Cambria; font-size:10.5pt; ">Check(Diagnosis)</span></strong></p>
            </td>
            <td style="background-color:#d3dfee; border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:213.05pt">
                <p style="text-align:left; widows:0; orphans:0"><span
                        style="font-family:Calibri; font-size:10.5pt">F5</span></p>
            </td>
        </tr>
        <tr>
            <td style="border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:213.05pt">
                <p style="text-align:left; widows:0; orphans:0"><strong><span
                        style="font-family:Cambria; font-size:10.5pt; ">Generate Report</span></strong></p>
            </td>
            <td style="border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:213.05pt">
                <p style="text-align:left; widows:0; orphans:0"><span style="font-family:Calibri; font-size:10.5pt">ALT+7</span>
                </p>
            </td>
        </tr>
        <tr>
            <td style="background-color:#d3dfee; border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:213.05pt">
                <p style="text-align:left; widows:0; orphans:0"><strong><span
                        style="font-family:Cambria; font-size:10.5pt; ">Query History Report</span></strong></p>
            </td>
            <td style="background-color:#d3dfee; border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:213.05pt">
                <p style="text-align:left; widows:0; orphans:0"><span style="font-family:Calibri; font-size:10.5pt">ALT+8</span>
                </p>
            </td>
        </tr>
        <tr>
            <td style="border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:213.05pt">
                <p style="text-align:left; widows:0; orphans:0"><strong><span
                        style="font-family:Cambria; font-size:10.5pt; ">Upload Report</span></strong></p>
            </td>
            <td style="border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:213.05pt">
                <p style="text-align:left; widows:0; orphans:0"><span style="font-family:Calibri; font-size:10.5pt">ALT+9</span>
                </p>
            </td>
        </tr>
        <tr>
            <td style="background-color:#d3dfee; border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:213.05pt">
                <p style="text-align:left; widows:0; orphans:0"><strong><span
                        style="font-family:Cambria; font-size:10.5pt; ">Options</span></strong></p>
            </td>
            <td style="background-color:#d3dfee; border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:213.05pt">
                <p style="text-align:left; widows:0; orphans:0"><span style="font-family:Calibri; font-size:10.5pt">ALT+F8</span>
                </p>
            </td>
        </tr>
        <tr>
            <td style="border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:213.05pt">
                <p style="text-align:left; widows:0; orphans:0"><strong><span
                        style="font-family:Cambria; font-size:10.5pt; ">Feedback</span></strong></p>
            </td>
            <td style="border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:213.05pt">
                <p style="text-align:left; widows:0; orphans:0"><span
                        style="font-family:Calibri; font-size:10.5pt">F12</span></p>
            </td>
        </tr>
        <tr>
            <td style="background-color:#d3dfee; border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:213.05pt">
                <p style="text-align:left; widows:0; orphans:0"><strong><span
                        style="font-family:Cambria; font-size:10.5pt; ">Upgrade</span></strong></p>
            </td>
            <td style="background-color:#d3dfee; border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:213.05pt">
                <p style="text-align:left; widows:0; orphans:0"><span
                        style="font-family:Calibri; font-size:10.5pt">F11</span></p>
            </td>
        </tr>
        <tr>
            <td style="border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:213.05pt">
                <p style="text-align:left; widows:0; orphans:0"><strong><span
                        style="font-family:Cambria; font-size:10.5pt; ">SDAlog</span></strong></p>
            </td>
            <td style="border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:213.05pt">
                <p style="text-align:left; widows:0; orphans:0"><span
                        style="font-family:Calibri; font-size:10.5pt">F3</span></p>
            </td>
        </tr>
        <tr>
            <td style="background-color:#d3dfee; border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:213.05pt">
                <p style="text-align:left; widows:0; orphans:0"><strong><span
                        style="font-family:Cambria; font-size:10.5pt; ">PhoneLog</span></strong></p>
            </td>
            <td style="background-color:#d3dfee; border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:213.05pt">
                <p style="text-align:left; widows:0; orphans:0"><span style="font-family:Calibri; font-size:10.5pt">ALT+F3</span>
                </p>
            </td>
        </tr>
        <tr>
            <td style="border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:213.05pt">
                <p style="text-align:left; widows:0; orphans:0"><strong><span
                        style="font-family:Cambria; font-size:10.5pt; ">P</span></strong><strong><span
                        style="font-family:Cambria; font-size:10.5pt; ">rint</span></strong></p>
            </td>
            <td style="border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:213.05pt">
                <p style="text-align:left; widows:0; orphans:0"><span style="font-family:Calibri; font-size:10.5pt">ALT+P</span>
                </p>
            </td>
        </tr>
        <tr>
            <td style="background-color:#d3dfee; border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:213.05pt">
                <p style="text-align:left; widows:0; orphans:0"><strong><span
                        style="font-family:Cambria; font-size:10.5pt; ">Basic</span></strong><strong><span
                        style="font-family:Cambria; font-size:10.5pt; "> Check</span></strong></p>
            </td>
            <td style="background-color:#d3dfee; border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:213.05pt">
                <p style="text-align:left; widows:0; orphans:0"><span style="font-family:Calibri; font-size:10.5pt">ALT+1</span>
                </p>
            </td>
        </tr>
        <tr>
            <td style="background-color:#ffffff; border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:213.05pt">
                <p style="text-align:left; widows:0; orphans:0"><strong><span
                        style="font-family:Cambria; font-size:10.5pt; ">Fast</span></strong><strong><span
                        style="font-family:Cambria; font-size:10.5pt; "> Check</span></strong></p>
            </td>
            <td style="background-color:#ffffff; border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:213.05pt">
                <p style="text-align:left; widows:0; orphans:0"><span style="font-family:Calibri; font-size:10.5pt">ALT+2</span>
                </p>
            </td>
        </tr>
        <tr>
            <td style="background-color:#d3dfee; border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:213.05pt">
                <p style="text-align:left; widows:0; orphans:0"><strong><span
                        style="font-family:Cambria; font-size:10.5pt; ">All Check</span></strong></p>
            </td>
            <td style="background-color:#d3dfee; border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:213.05pt">
                <p style="text-align:left; widows:0; orphans:0"><span style="font-family:Calibri; font-size:10.5pt">ALT+3</span>
                </p>
            </td>
        </tr>
        <tr>
            <td style="background-color:#ffffff; border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:213.05pt">
                <p style="text-align:left; widows:0; orphans:0"><strong><span
                        style="font-family:Cambria; font-size:10.5pt; ">Free Check</span></strong></p>
            </td>
            <td style="background-color:#ffffff; border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:213.05pt">
                <p style="text-align:left; widows:0; orphans:0"><span style="font-family:Calibri; font-size:10.5pt">ALT+4</span>
                </p>
            </td>
        </tr>
        <tr>
            <td style="background-color:#d3dfee; border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:213.05pt">
                <p style="text-align:left; widows:0; orphans:0"><strong><span
                        style="font-family:Cambria; font-size:10.5pt; ">Log</span></strong><strong><span
                        style="font-family:Cambria; font-size:10.5pt; "> Analysis</span></strong></p>
            </td>
            <td style="background-color:#d3dfee; border-bottom-color:#4f81bd; border-bottom-style:solid; border-bottom-width:1pt; border-left-color:#4f81bd; border-left-style:solid; border-left-width:1pt; border-right-color:#4f81bd; border-right-style:solid; border-right-width:1pt; border-top-color:#4f81bd; border-top-style:solid; border-top-width:1pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:213.05pt">
                <p style="text-align:left; widows:0; orphans:0"><span style="font-family:Calibri; font-size:10.5pt">ALT+5</span>
                </p>
            </td>
        </tr>
    </table>
    <p style="text-align:left">&#xa0;</p>
    <ol start="15" type="1" style="margin:0pt; padding-left:0pt">
        <li class="1"
            style="margin-top:7.8pt; margin-left:17.85pt; margin-bottom:7.8pt; text-indent:-17.85pt; text-align:left; font-family:Calibri; font-size:14pt; font-weight:bold; list-style-position:inside">
            <a name="_Toc499570141"><strong><span
                    style="font-family:Calibri; font-size:14pt; ">Technical </span></strong><strong><span
                    style="font-family:Calibri; font-size:14pt; ">S</span></strong><strong><span
                    style="font-family:Calibri; font-size:14pt; ">upport</span></strong></a></li>
    </ol>
    <p class="1">If you are having trouble reading the design, or if you need to modify the document, please
        contact(yelingjun@vivo.com.cn).</p>
    <p style="text-align:left">&#xa0;</p>
    <p class="Footer" style="text-align:center"><strong>1</strong> / <strong>33</strong></p>
</div>
</body>
</html>
