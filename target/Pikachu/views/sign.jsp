<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Standard Meta -->
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

    <!-- Site Properties -->
    <title>Homepage</title>
    <%--<link rel="stylesheet" type="text/css" href="/semantic/dist/semantic.css">--%>
    <link rel="stylesheet" type="text/css" href="/semantic/dist/components/checkbox.css">
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
    <script>
        $('.ui.checkbox').checkbox();   //使得单选框可以被选中
    </script>
    <script src="/semantic/dist/components/checkbox.js"></script>


    <script src="/semantic/dist/semantic.js"></script>


</head>
<body>

<div class="ui inverted vertical masthead center aligned segment">
    <div class="ui container">
        <div class="ui large secondary inverted pointing menu">
            <a class="toc item">
                <i class="sidebar icon"></i>
            </a>
            <a class="item" href="/index">Home</a>
            <div class="right item">
                <a class="ui inverted button" href="/login">Log in</a>
                <a class="ui inverted button" href="/sign">Sign Up</a>
            </div>
        </div>
    </div>
</div>
<div class="text ui container">
    <br>
    <h1 class="ui dividing header">Sign Up</h1>
    <br>
    <form:form method="POST" modelAttribute="member" class="ui form">
        <%--<h3 class="ui dividing header">Login Information</h3>--%>
        <div class="ui form">
            <br>
            <div class="field">
                <label>Account ID</label>
                <form:input type="text" path="accountID" placeholder="Account ID"/>
                <div class="has-error" style="color: red">
                    <form:errors path="accountID" class="help-inline"/>
                </div>
            </div>
            <br>
            <div class="field">
                <label>Password</label>
                <form:input type="password" path="password" placeholder="Password"/>
                <div class="has-error" style="color: red">
                    <form:errors path="password" class="help-inline"/>
                </div>
            </div>
            <br>
            <div class="field">
                <label>Type</label>
                <div class="two wide field">
                    <select class="ui compact selection dropdown" name="typeCheck">
                        <option value="Member">Member</option>
                        <option value="Company">Company</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="field">
            <div class="error" style="color: red">
                    ${error}
            </div>
        </div>

        <h1 class="ui dividing header"></h1>
        <button class="ui button" type="submit">Submit</button>
        <%--<div class="ui error message"></div>--%>
    </form:form>
</div>
<br><br><br><br><br><br>
<div class="pusher">
    <div class="ui inverted vertical footer segment">
        <div class="ui center aligned container">
            <h3 class="ui header" style="color: white">Pikachu Team from CUFE</h3>
            <p>Pika Pika</p>
        </div>
    </div>
</div>


</body>
</html>
