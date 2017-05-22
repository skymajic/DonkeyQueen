(function(){
	'use strict';
	
	// jQuery
	$(function(){
		
		// DropDown。#menu の子要素のliをホバーすると、そのliの子要素であるulをshowさせ、ホバーが離れると、ulをhideさせる
		$("#menu li").hover(function(){
			$("ul", this).show();
		},function(){
			$("ul", this).hide();
		});
		
		// トップへ戻るボタン
		$(function(){
		   
			// トップへ戻るボタンをデフォルトで非表示にしておく
		   $('#toTop').hide();
		   
		   // スクロール位置が100ピクセルを越えればトップへ戻るボタンをファイドインさせ、そうでなくなればフェイドアウトさせる
		   $(window).scroll(function(){
		      if ($(window).scrollTop() > 100) {
		        $('#toTop').fadeIn(500);
		      } else {
		        $('#toTop').fadeOut(500);
		      }
		   });
		   
		   // トップへ戻るボタンをクリックすると、ページ最上部まで600msかけてアニメーションしながら移動。return falseで処理を正常終了させる。
		   $('#toTop').click(function(){
		     $('body').animate({
		        scrollTop:0
		     },600);
		        return false;
		   });

		});
		
	});
	
})();