/*---LEFT BAR ACCORDION----*/
$(function() {
  $('#nav-accordion').dcAccordion({
    eventType: 'click',
    autoClose: true,
    saveState: true,
    disableLink: true,
    speed: 'slow',
    showCount: false,
    autoExpand: true,
    //        cookie: 'dcjq-accordion-1',
    classExpand: 'dcjq-current-parent'
  });
});

var Script = function() {

  //    sidebar dropdown menu auto scrolling
  jQuery('#sidebar .sub-menu > a').click(function() {
    var o = ($(this).offset());
    diff = 250 - o.top;
    if (diff > 0)
      $("#sidebar").scrollTo("-=" + Math.abs(diff), 500);
    else
      $("#sidebar").scrollTo("+=" + Math.abs(diff), 500);
  });

  //  sidebar toggle
  $(function() {
    function responsiveView() {
      var wSize = $(window).width();
      if (wSize <= 168) {
        $('#container').addClass('sidebar-close');
        $('#sidebar > ul').hide();
      }

      if (wSize > 168) {
        $('#container').removeClass('sidebar-close');
        $('#sidebar > ul').show();
      }
    }
    $(window).on('load', responsiveView);
    $(window).on('resize', responsiveView);
  });

  $('header .fa-bars').click(function() {
    if ($('#sidebar > ul').is(":visible") === true) {
      $('#body-content').css({
        'margin-left': '0px'
      });
      $('#sidebar').css({
        'margin-left': '-210px'
      });
      $('#sidebar > ul').hide();
      $("#container").addClass("sidebar-closed");
    } else {
      $('#body-content').css({
        'margin-left': '210px'
      });
      $('#sidebar > ul').show();
      $('#sidebar').css({
        'margin-left': '0'
      });
      $("#container").removeClass("sidebar-closed");
    }
  });

  // custom scrollbar
  $("#sidebar").niceScroll({
    styler: "fb",
    cursorcolor: "#4ECDC4",
    cursorwidth: '3',
    cursorborderradius: '10px',
    background: '#404040',
    spacebarenabled: false,
    cursorborder: ''
  });

  //  $("html").niceScroll({styler:"fb",cursorcolor:"#4ECDC4", cursorwidth: '6', cursorborderradius: '10px', background: '#404040', spacebarenabled:false,  cursorborder: '', zindex: '1000'});
  // widget tools
  jQuery('.panel .tools .fa-chevron-down').click(function() {
    var el = jQuery(this).parents(".panel").children(".panel-body");
    if (jQuery(this).hasClass("fa-chevron-down")) {
      jQuery(this).removeClass("fa-chevron-down").addClass("fa-chevron-up");
      el.slideUp(200);
    } else {
      jQuery(this).removeClass("fa-chevron-up").addClass("fa-chevron-down");
      el.slideDown(200);
    }
  });

  jQuery('.panel .tools .fa-times').click(function() {
    jQuery(this).parents(".panel").parent().remove();
  });

  //    tool tips
  $('.tooltips').tooltip();

  //    popovers
  $('.popovers').popover();

  // custom bar chart
  if ($(".custom-bar-chart")) {
    $(".bar").each(function() {
      var i = $(this).find(".value").html();
      $(this).find(".value").html("");
      $(this).find(".value").animate({
        height: i
      }, 2000)
    })
  }
}();

//-- 스크롤 감지하여 헤더 보이거나 감추기 (Hide Header on on scroll down)
var didScroll;
var lastScrollTop = 0;
var delta = 5;
var navbarHeight = $('header').outerHeight();

$(window).scroll(function(event){
	didScroll = true;
});

setInterval(function() {
	if (didScroll) {
		hasScrolled();
		didScroll = false;
	}
}, 250);	//원값 250

function hasScrolled() {
	var st = $(this).scrollTop();
	
	// Make sure they scroll more than delta
	if(Math.abs(lastScrollTop - st) <= delta)
		return;
	
	// If they scrolled down and are past the navbar, add class .nav-up.
	// This is necessary so you never see what is "behind" the navbar.
	if (st > lastScrollTop && st > navbarHeight){
		// Scroll Down
		$('header').removeClass('nav-down').addClass('nav-up');
	} else {
		// Scroll Up
		if(st + $(window).height() < $(document).height()) {
			$('header').removeClass('nav-up').addClass('nav-down');
		}
	}	
	lastScrollTop = st;
}

//---------- 퍼블리싱용
$(document).ready(function(){
	// click
	$('.click-active a').click(function() {
		$(this).parent().parent().find('a').removeClass('active');
		$(this).addClass('active');
	});
	$('.click-active2 a').click(function() {
		$(this).parent().parent().find('li').removeClass('active');
		$(this).parent().addClass('active');
	});
	$('.date-select a').click(function() {
		$(this).parent().find('a').removeClass('btn-primary');
		$(this).addClass('btn-primary');
	});

	// 게시판 검색영역 Reset
	$("#btnReset").click(function() {
		$("form").each(function() {
			if(this.className  == ".Board-Search") this.reset();  
        });  
    }); 

	// tab별 view
	$(".tab-view li").click(function () {
		$(".tab-view li").removeClass("active");
		$(this).addClass("active");	
		$(".tab-contents").removeClass("show");
		$(".tab-contents").hide()
			var activeTab = $(this).attr("rel");
		$("#" + activeTab).fadeIn('fast');
	});

	//최상단 체크박스 클릭
    $("#checkall").click(function(){
        if($("#checkall").prop("checked")){
            $("input[name=chk]").prop("checked",true);
        }else{
            $("input[name=chk]").prop("checked",false);
        }
    });

	// CustomScrollbar
	$(".mCustomScrollbar").mCustomScrollbar({
		scrollInertia : 100
	});
});


jQuery(document).ready(function( $ ) {
  // Go to top
  $('.go-top').on('click', function(e) {
    e.preventDefault();
    $('html, body').animate({scrollTop : 0},500);
  });
});

$(function(){
	$('.default-date-picker').datepicker({
		calendarWeeks: false,
		todayHighlight: true,
		autoclose: true,
		format: "yyyy-mm-dd",
		language: "kr"
	});
});


// -- 팝업 ----- //
function pop_accCnt() {
	window.open('/CUG/pages/ContentsMgt/Account/AccountingMgt/pop_accCnt.shtml','','status=no, width=1000, height=700, scrollbars=yes');
	}
function pop_public() {
	window.open('/CUG/pages/TaxlawRevision/LegislationNotice/pop_public_List.shtml','','status=no, width=900, height=700, scrollbars=yes');
	}
function pop_oldForm() {
	window.open('/CUG/pages/FormatMgt/TaxFormat/pop_oldForm.shtml','','status=no, width=1000, height=700, scrollbars=yes');
	}