<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>OAuth2.0 Third APP</title>
</head>
<body>
<a href="/thirdRes/${accessToken!}/admin">thirdRes-admin</a>
<a href="/thirdRes/${accessToken!}/user">thirdRes-user</a>
<a href="/thirdRes/${accessToken!}/guest">thirdRes-guest</a>
<div>
    结果：${msg!} ${errorMsg!}
</div>

</body>
</html>