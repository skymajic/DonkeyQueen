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
		
		//モーダルの処理
		  // #logoutをクリックすると
		  $('#logout').click(function(){


		    //以下2行は、オーバーレイが多重起動するのを防止する処理
		    //ボタンからフォーカスを外す
		    $(this).blur();
		    //新しくモーダルウィンドウを起動しない
		    if( $('#modal-overlay')[0] ) return false;

		    //オーバーレイを出現させる処理
		    $('body').append('<div id="modal-overlay"><div>');
		    $('#modal-overlay').fadeIn('slow');

		    //モーダルをセンタリングする処理
		    centeringModal();

		    //モーダルを徐々に表示させる
		    $('#modal-content').fadeIn('slow');

		    // #modal-closeをクリックしたら
		    $('#modal-close').click(function(){

		      //#modal-contentと#modal-overlayをフェードアウトしたあとに、
		      $('#modal-content,#modal-overlay').fadeOut('slow',function(){

		        // #modal-overlayを削除する
		        $('#modal-overlay').remove();

		      });

		    });




		  });


		  //モーダルをセンタリングする関数
		  function centeringModal(){

		    //ウィンドウ全体の縦と横の長さを取得する
		    var w = $(window).width();
		    var h = $(window).height();

		    //モーダルの縦と横の長さを取得する
		    var cw = $('#modal-content').outerWidth();
		    var ch = $('#modal-content').outerHeight();

		    // (全体の長さ - モーダルの長さ = 余白) / 2 というロジックで、それをcssに適用し、センタリングする
		    $('#modal-content').css( { 'left':((w-cw)/2)+'px' , 'top':((h-ch)/2)+'px' } );
		  }
		
		
	});
	
})();