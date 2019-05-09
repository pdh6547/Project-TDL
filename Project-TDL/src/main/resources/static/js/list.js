$('.reply').click(function () {
    var thisValue = $(this).parent().parent().parent().parent().parent().parent().find(".replyDiv");
    thisValue.toggle("slow");
});

//댓글 추가
$('.replyAdd').click(function () {
    var thisValue = $(this).parent().parent().find(".myList");
    var v = $(this).val() - 1;
    var reply_text = $('.replyText').eq(v).val();
    var jsonData = JSON.stringify({
        content: reply_text
    });

    $.ajax({
        url: "http://localhost:8080/reply/" + $(this).val(),
        type: "POST",
        data: jsonData,
        contentType: "application/json",
        dataType: "json",
        success: function () {
            var node = document.createElement("LI");
            var textNode = document.createTextNode(reply_text);
            node.appendChild(textNode);
            thisValue.append(node);
        },
        error: function () {
            alert('저장 실패!');
        }
    });
});

//댓글 삭제
$('.reply_delete').click(function () {
    $.ajax({
        url: "http://localhost:8080/reply/delete/" + $(this).val(),
        type: "DELETE",
        success: function () {
            alert('삭제 성공!');
            location.href = '/tdl/list';
        },
        error: function () {
            alert('삭제 실패!');
        }
    });
});

//댓글 수정
$('.reply_update').click(function () {
    var updateCon = prompt("내용을 입력하세요", "");
    console.log(updateCon);
    $.ajax({
        url: "http://localhost:8080/reply/update/" + $(this).val(),
        type: "PUT",
        data: updateCon,
        contentType: "application/json",
        dataType: "json",
        success: function () {
            alert('수정 성공!');
            location.href = '/tdl/list';
        },
        error: function () {
            alert('수정 실패!');
        }
    });
});

//리스트 등록
$('#insert').click(function () {
    var jsonData = JSON.stringify({
        description: $('#description').val()
    });
    $.ajax({
        url: "http://localhost:8080/tdl",
        type: "POST",
        data: jsonData,
        contentType: "application/json",
        dataType: "json",
        success: function () {
            alert('저장 성공!');
            location.href = '/tdl/list';
        },
        error: function () {
            alert('저장 실패!');
        }
    });
});
//리스트 삭제
$('.delete').click(function () {
    $.ajax({
        url: "http://localhost:8080/tdl/delete/" + $(this).val(),
        type: "DELETE",
        success: function () {
            alert('삭제 성공!');
            location.href = '/tdl/list';
        },
        error: function () {
            alert('삭제 실패!');
        }
    });
});
//리스트 수정
$('.update').click(function () {
    var updateDes = prompt("내용을 입력하세요", "");
    console.log(updateDes);
    $.ajax({
        url: "http://localhost:8080/tdl/update/" + $(this).val(),
        type: "PUT",
        data: updateDes,
        contentType: "application/json",
        dataType: "json",
        success: function () {
            alert('수정 성공!');
            location.href = '/tdl/list';
        },
        error: function () {
            alert('수정 실패!');
        }
    });
});
//리스트 완료
$('.complete').click(function () {
    document.img2.src = "/images/checked.png";
    var jsonData = JSON.stringify({
        completedDate: $('#list_complete').val()
    });
    $.ajax({
        url: "http://localhost:8080/tdl/complete/" + $(this).val(),
        type: "PUT",
        data: jsonData,
        contentType: "application/json",
        dataType: "json",
        success: function () {
            alert('완료!');
            location.href = '/tdl/list';
        },
        error: function () {
            alert('완료 실패!');
        }
    });
});