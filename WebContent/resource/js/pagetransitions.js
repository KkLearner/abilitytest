var PageTransitions = (function() {

	var $main = $( '#pt-main' ),  //主要部分，包含各个page
		$pages = $main.children( 'div.pt-page' ),
		$iterate = $( '#iterateEffects' ),  //切换页面按钮
		animcursor = 1,
		pagesCount = $pages.length,//一个有多少页
		current = 0,
		isAnimating = false,
		endCurrPage = false,
		endNextPage = false,
		animEndEventNames = {
			'WebkitAnimation' : 'webkitAnimationEnd',
			'OAnimation' : 'oAnimationEnd',
			'msAnimation' : 'MSAnimationEnd',
			'animation' : 'animationend'
		},
		// animation end event name
		animEndEventName = animEndEventNames[ Modernizr.prefixed( 'animation' ) ],  //在chrome运行时animEndEventName = "animationend"
		// support css animations
		support = Modernizr.cssanimations;  //Detects whether or not elements can be animated using CSS  在chrome运行时support = true
	
	function init() {

		//设置每个'div.pt-page'的originalClassList属性设置为当前的class="***"中的内容
		$pages.each( function(index) {
			var $page = $( this ); //$page是一个包含了当前对象的jquery元素
			$page.data( 'originalClassList', $page.attr( 'class' ) ); //originalClassList在函数resetPage中用到
		} );

		$pages.eq( current ).addClass( 'pt-page-current' );//eq()函数用于获取当前jQuery对象所匹配的元素中指定索引的元素，current一开始是0
		//.pt-page的visibility属性是hidden，pt-page-current的visibility是visible，即需要通过上述语句来实现pt-page元素的可见

		//************页面切换按钮被点击时的处理函数，这里需要修改为两个：“上一题”按钮和“下一题”按钮****************
		$iterate.on( 'click', function() {
			if( isAnimating ) {
				return false;
			}
			//************这里到时候需要进行修改，用于到达最后一题时的处理*************************************
			if( animcursor > 67 ) {
				animcursor = 1;
			}
			nextPage( animcursor );
			++animcursor;
		} );

	}

	//***********这里要修改为：两个函数参数，一个是切换页的页数（上一页，下一页，题目列表），一个是向左滑动还是向右滑动切换**************
	function nextPage(options ) {
		var animation =  options;

		if( isAnimating ) {
			return false;
		}

		isAnimating = true;
		
		var $currPage = $pages.eq( current );

		if( current < pagesCount - 1 ) {
			++current;
		}
		else {
			current = 0;
		}

		var $nextPage = $pages.eq( current ).addClass( 'pt-page-current' ),//让下一页从“隐藏”到“可见”
			outClass = '', inClass = '';


		switch( animation ) {
			case 1:
				outClass = 'pt-page-moveToLeft';
				inClass = 'pt-page-moveFromRight';
				break;
			case 2:
				outClass = 'pt-page-moveToRight';
				inClass = 'pt-page-moveFromLeft';
				break;
		}

		//在chrome浏览器中，animEndEventName= "animationend"
		$currPage.addClass( outClass ).on( animEndEventName, function() {
			$currPage.off( animEndEventName );
			endCurrPage = true;
			if( endNextPage ) {
				onEndAnimation( $currPage, $nextPage );
			}
		} );

		$nextPage.addClass( inClass ).on( animEndEventName, function() {
			$nextPage.off( animEndEventName );
			endNextPage = true;
			if( endCurrPage ) {
				onEndAnimation( $currPage, $nextPage );
			}
		} );

		if( !support ) {
			onEndAnimation( $currPage, $nextPage );
		}

	}

	function onEndAnimation( $outpage, $inpage ) {
		endCurrPage = false;
		endNextPage = false;
		resetPage( $outpage, $inpage );
		isAnimating = false;
	}

	function resetPage( $outpage, $inpage ) {
		$outpage.attr( 'class', $outpage.data( 'originalClassList' ) );
		$inpage.attr( 'class', $inpage.data( 'originalClassList' ) + ' pt-page-current' );
	}

	init();

	return { 
		init : init,
		nextPage : nextPage
	};

})();