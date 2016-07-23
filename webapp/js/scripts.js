$(".qna-comment").on("click", ".answerWrite input[type=submit]", addAnswer);
$(".qna-comment").on("click", ".form-delete button[type=submit]", delAnswer);

function addAnswer(e) {
  e.preventDefault();

  var formData = $("form[name=answer]").serialize();

  $.ajax({
    type : 'post',
    url : '/api/qna/addAnswer',
    data : formData,
    dataType : 'json',
    error: onError,
    success : function (json, status){
	  var answer = json.answer;
	  var answerTemplate = $("#answerTemplate").html();
	  var template = answerTemplate.format(answer.writer, new Date(answer.createdDate), answer.contents, answer.answerId, answer.answerId);
	  $(".qna-comment-slipp-articles").prepend(template);
	  
	  document.getElementById('countOfComment').firstChild.data = json.countOfComment;
   	}});
}

function delAnswer(e) {
  e.preventDefault();

  var deleteBtn = $(this);
  var formData = deleteBtn.closest('form').serialize();

  $.ajax({
    type : 'post',
    url : '/api/qna/deleteAnswer',
    data : formData,
    dataType : 'json',
    error: onError,
    success : function (json, status){
    	var result = json.result.status;
    	if (result) {
    		console.log("Delete succuss!!");
    		deleteBtn.closest('article').remove();
    		
    		document.getElementById('countOfComment').firstChild.data = json.countOfComment;
    	} else {
    	  alert("Delete Error!!");
    	}
    }});
}

function onError(xhr, status) {
  alert("error");
}

String.prototype.format = function() {
  var args = arguments;
  return this.replace(/{(\d+)}/g, function(match, number) {
    return typeof args[number] != 'undefined'
        ? args[number]
        : match
        ;
  });
};