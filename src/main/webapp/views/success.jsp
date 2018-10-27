<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Standard Meta -->
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

    <!-- Site Properties -->
    <title>Success</title>
    <%--<link rel="stylesheet" type="text/css" href="/semantic/dist/semantic.css">--%>
    <link rel="stylesheet" type="text/css" href="/semantic/dist/semantic.css">

    <style type="text/css">

        .hidden.menu {
            display: none;
        }

        .masthead.segment {
            min-height: 70px;
            padding: 1em 0em;
        }
        .masthead .logo.item img {
            margin-right: 1em;
        }
        .masthead .ui.menu .ui.button {
            margin-left: 0.5em;
        }
        .masthead h1.ui.header {
            margin-top: 3em;
            margin-bottom: 0em;
            font-size: 4em;
            font-weight: normal;
        }
        .masthead h2 {
            font-size: 1.7em;
            font-weight: normal;
        }

        .ui.vertical.stripe {
            padding: 8em 0em;
        }
        .ui.vertical.stripe h3 {
            font-size: 2em;
        }
        .ui.vertical.stripe .button + h3,
        .ui.vertical.stripe p + h3 {
            margin-top: 3em;
        }
        .ui.vertical.stripe .floated.image {
            clear: both;
        }
        .ui.vertical.stripe p {
            font-size: 1.33em;
        }
        .ui.vertical.stripe .horizontal.divider {
            margin: 3em 0em;
        }

        .quote.stripe.segment {
            padding: 0em;
        }
        .quote.stripe.segment .grid .column {
            padding-top: 5em;
            padding-bottom: 5em;
        }

        .footer.segment {
            padding: 3em 0em;
        }

        .secondary.pointing.menu .toc.item {
            display: none;
        }

        @media only screen and (max-width: 700px) {
            .ui.fixed.menu {
                display: none !important;
            }
            .secondary.pointing.menu .item,
            .secondary.pointing.menu .menu {
                display: none;
            }
            .secondary.pointing.menu .toc.item {
                display: block;
            }
            .masthead.segment {
                min-height: 350px;
            }
            .masthead h1.ui.header {
                font-size: 2em;
                margin-top: 1.5em;
            }
            .masthead h2 {
                margin-top: 0.5em;
                font-size: 1.5em;
            }
        }


    </style>

    <script src="/js/jquery.min.js"></script>
    <script src="/semantic/dist/semantic.js"></script>


</head>
<body>

<div class="ui inverted vertical masthead center aligned segment">
    <div class="ui container">
        <div class="ui large secondary inverted pointing menu">
            <a class="toc item">
                <i class="sidebar icon"></i>
            </a>
            <a class="item" href="/indexUser">Home</a>
            <a class="active item" href="/welcomeUser">User Center</a>
            <a class="item" href="/transaction">Transaction</a>
            <div class="right item">
                <div class="ui inverted button disabled">
                    ID:&nbsp;${sessionScope.login.accountID}
                </div>
                <div class="ui inverted button disabled">
                    Credit:&nbsp;${sessionScope.login.credit}
                </div>
                <div class="ui inverted button disabled">
                    Point:&nbsp;${sessionScope.login.point}
                </div>
                <a class="ui inverted button" href="/logout">Sign Out</a>
            </div>
        </div>
    </div>
</div>
<br><br><br><br><br><br><br><br><br>

<div class="ui text container">
    <h2 class="ui header">Success</h2>
    <br>
</div>

<div class="ui text container">
    <h3>You need to re-Login to update the dataset</h3>
    <a class="ui fluid large submit button" type="submit" href="/logout">re-Login</a>
</div>



<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<div class="pusher">
    <div class="ui inverted vertical footer segment">
        <div class="ui center aligned container">
            <h3 class="ui header" style="color: white">Pikachu Team of Hackathon</h3>
            <p> Support for the continued development of Semantic UI comes directly from the community.</p>
        </div>
    </div>
</div>


</body>
</html>

