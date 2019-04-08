$('#mail').blur(function () {
    var getMail = RegExp(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/);
    if ($('#mail').val() === "") {
        $('#mailSuccess').html('<p style="text-align: center; color:red; margin-top: -13px; font-size: 12px">이메일은 빈 칸일 수 없습니다</p>');
        return false;
    } else if (!getMail.test($('#mail').val())) {
        $('#mailSuccess').html('<p style="text-align: center; color:red; margin-top: -13px; font-size: 12px">이메일 형식에 맞게 입력해주세요.</p>');
        return false;
    } else {
        var email = $('#mail').val();
        $.ajax({
            url: "http://localhost:8080/register/commit",
            type: "POST",
            data: email,
            contentType: "application/json",
            dataType: "text",
            success: function () {
                $('#mailSuccess').html('<p style="text-align: center; color:blue; margin-top: -13px; font-size: 12px">사용 가능합니다.</p>');
            },
            error: function () {
                $('#mailSuccess').html('<p style="text-align: center; color:red; margin-top: -13px; font-size: 12px">이메일이 중복입니다.</p>');
            }
        });
    }
});

$('#password').blur(function () {
    var getCheck = RegExp(/^[a-zA-Z0-9]{4,12}$/);
    var check1;
    if ($('#password').val() === "") {
        $('#passwordSuccess').html('<p style="text-align: center; color:red; margin-top: -13px; font-size: 12px">비밀번호는 빈 칸일 수 없습니다</p>');
        check1 = false;
        return check1;
    } else if (!getCheck.test($('#password').val())) {
        $('#passwordSuccess').html('<p style="text-align: center; color:red; margin-top: -13px; font-size: 12px">비밀번호 형식에 맞게 입력해주세요.</p>');
        check1 = false;
        return check1;
    } else {
        $('#passwordSuccess').html('<p style="text-align: center; color:blue; margin-top: -13px; font-size: 12px">사용 가능합니다.</p>');
        return true;
    }
});

$('#register').click(function () {
    var jsonData = JSON.stringify({
        email: $('#mail').val(),
        password: $('#password').val()
    });
    $.ajax({
        url: "http://localhost:8080/register/create",
        type: "POST",
        data: jsonData,
        contentType: "application/json",
        dataType: "json",
        success: function () {
            alert('회원가입 성공!');
            location.href = '/login';
        },

        error: function () {
            if ($('#mail').val() === "" && $('#password').val() !== "") {
                $('#mailSuccess').html('<p style="text-align: center; color:red; margin-top: -13px; font-size: 12px">필수 정보를 입력해 주세요.</p>');
            } else if (($('#password').val() === "" && $('#mail').val() !== "")) {
                $('#passwordSuccess').html('<p style="text-align: center; color:red; margin-top: -13px; font-size: 12px">필수 정보를 입력해 주세요.</p>');
            } else if ($('#mail').val() === "" && ($('#password').val() === "")) {
                $('#mailSuccess').html('<p style="text-align: center; color:red; margin-top: -13px; font-size: 12px">필수 정보를 입력해 주세요.</p>');
                $('#passwordSuccess').html('<p style="text-align: center; color:red; margin-top: -13px; font-size: 12px">필수 정보를 입력해 주세요.</p>');
            }
        }
    });
});