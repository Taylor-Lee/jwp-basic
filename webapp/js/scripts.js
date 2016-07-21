$(".answerWrite input[type=submit]").click(addAnswer);

function addAnswer(e) {
	e.preventDefault();
	
	var queryString = $("form[name=answer]").serialize(); 
	
	$.ajax({
		 type : 'post',
		 url : '/api/qna/addanswer',
		 data : queryString,
		 dataType : 'json',
		 error: onError,
		 success : onSuccess,
	});
	
	console.log(queryString);
}

function onSuccess(json, status) {
	 var answerTemplate = $("#answerTemplate").html();
	 var template = answerTemplate.format(json.writer, new Date(json.createdDate), json.contents, json.answerId, json.answerId);
	 $(".qna-comment-slipp-articles").prepend(template);
} 

function onError(json, status) {
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


$(".form-delete").click(deleteAnswer);

function deleteAnswer(e) {
	e.preventDefault();
	
	var deleteBtn = $(this);
	var answerId = deleteBtn.closest('form').serialize();
	
	$.ajax({
		 type : 'post',
		 url : '/api/qna/deleteanswer',
		 data : answerId,
		 dataType : 'json',
		 error: onError,
		 success : function (json, status) {
			if (json.status) {
				console.log("Delete Succuss by Ajax!");
				deleteBtn.closest('article').remove();
			} else {
				console.log("Delete Fail by Ajax! Message: " + json.message);
			}
		} 
	});
	
	console.log("answerId: " + answerId);
}

