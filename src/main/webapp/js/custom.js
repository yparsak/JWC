/****************************************************
*
*
*
*
*
*****************************************************/
var login_width = 300;

$(document).ready(function() {
	
  /* Dialog */
  $('#dialog').dialog({
      autoOpen: false,
      width: login_width + "px"
  });

  /* Login button */
  $('#loginbtn').click(function() {
	$("span.ui-dialog-title").text('Login');  
    $('#coverup').show();
    $('#name').val("");
    $('#password').val("");
    $('#loginform').show();
    $('#menuform').hide();
    $('#dialog').dialog('open');
    $('#name').focus();
  });
  
  $('#menubtn').click(function() {
		$("span.ui-dialog-title").text('Add Item');  
	    $('#coverup').show();
	    $('#name').val("");
	    $('#password').val("");
	    $('#loginform').hide();
	    $('#menuform').show();
	    $('#dialog').dialog('open');
	    $('#name').focus();
  });
  
  $('#userbtn').click(function() {
	  $("span.ui-dialog-title").text('Add New User');
	  $('#coverup').show();
	  $('#name').val("");
	  $('#lastname').val("");
	  $('#email').val("");
	  $('#password').val("");
	  $('#dialog').dialog('open');
	  $('#name').focus();
  });
  
  $('#password').click(function() {
	  $('#password').val(""); 
  });
  
  $('#addrolebtn').click(function() {
	  $("span.ui-dialog-title").text('Add New Role');
	  $('#coverup').show();
	  $('#dialog').dialog('open');
  }
  );

  $('#deptbtn').click(function() {
	  $("span.ui-dialog-title").text('Add New Department');
	  $('#coverup').show();
	  $('#newdptform').show();
	  $('#newroleform').hide();
	  $('#dialog').dialog('open');
  }
  );
  
  $('#rolebtn').click(function() {
	  $("span.ui-dialog-title").text('Add New Role');
	  $('#newdptform').hide();
	  $('#newroleform').show();  
	  $('#coverup').show();
	  $('#dialog').dialog('open');
  }
  );
  
  /* Coverup */
  $('#coverup').hide();

  $( "#coverup" ).click(function() {
    $('#dialog').dialog('close');
  });
  /* */  

  $('#dialog').on('dialogclose', function(event) {
    $('#coverup').hide();
  });
  
  $('#loginsubmit').click(
    function() {
      $.get( "login", function( data ) {
        $( "#result" ).html( data );
      });
    }
  );
  
  $('#adduser').click(
	function() {
	  $.get( "add_user", function( data ) {
		  $( "#result" ).html( data );
	  });
	}
  );
  
  $('#updateuser').click(
	function() {
		$.get( "update_user", function( data ) {
			$( "#result" ).html( data );
		});
	}
  );
  
  $('#edituser').click(
     function() {
		$.get( "edit_user", function( data ) {
			$( "#result" ).html( data );
		});
	 }
  );

  $('#removeuser').click(
	function() {
		$.get( "remove_user", function( data ) {
			$( "#result" ).html( data );
		});
	}
  );
  
  $('#pageview').click(
		function() {
			$.get( "index", function( data ) {
				$( "#result" ).html( data );
			});
		}
	);
  

});