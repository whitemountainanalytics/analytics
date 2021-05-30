<html>
<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta http-equiv="Pragma" content="no-cache">
  <meta http-equiv="Cache-Control" content="no-cache">
  <title>Homepage</title>

  <!-- Global site tag (gtag.js) - Google Analytics -->
  <script async src="https://www.googletagmanager.com/gtag/js?id=G-88RVMXN1BR"></script>
  <script>
    window.dataLayer = window.dataLayer || [];
    function gtag(){dataLayer.push(arguments);}
    gtag('js', new Date());

    gtag('config', 'G-88RVMXN1BR');
</script>

</head>

<body>
<img src= 'images/apolo.jpg' style="width:100px;height:80px;">

<br>
<br>
<B>My Home Page</B>
<br><br>
<a href='/homepage.php'> <b>Home page</b> </a>
<br><br>
<a href='/infopage.php'> <b>Info page</b> </a>
<br><br>
<a href='/anotherpage.php'> <b>Another page</b> </a>
<br><br>

<?php

require "utils.php";

setSessionAndCookies();

// Create page logging insert statement
$page_view_insert="insert into page_viewed (page_id, page_view_date) values (1,now())";

// Create connection
$db_conn = mysqli_connect($servername, $username, $password, $database)
or die('Error connecting to MySQL server.');

// Execute insert statement
mysqli_query($db_conn, $page_view_insert) or die('Error doing page view insert.');

// Close DB connection
mysqli_close($db_conn);

?>
</body>
</html>

