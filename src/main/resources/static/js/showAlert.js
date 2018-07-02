
	function alertMsg(title,content,fun,param){
		layer.open({
			  title: [
			    title,'font-size:14px;color:#fff'
			  ]
			  ,anim: 'up'
			  ,content: content
			  ,btn: ['确认', '取消']
			  ,yes:function(index){
				  if(fun){
					  fun(index,param); 
				  }else{
					  layer.close(index);
				  }
			  },
			  no:function(){
				  layer.close();
			  }
			});
	}
	//仅有确定按钮,无最大最小化
	function showMsg(content,fun,param){
	    layer.open({
	        title: [
	            '操作提示',
	            'font-size:14px;color:#fff'
	        ], maxmin: false
	        ,anim: 'up'
	        ,content: content
	        ,btn: ['确认']
	        ,end:function(index){
	            if(fun){
	                fun(index,param); 
	            }else{
	                layer.close(index);
	            }
	        }
	    });
	}
  //仅提示无按钮的弹框
  function alerttip(content){
	  layer.open({
		  content: content
		  ,style: 'color:#fff; border:none;' //自定风格
		  ,time: 3
		});
  }
  //无按钮的弹框----触发某方法
  function alerttips(content,fun,param){
	  layer.open({
		  content: content
		  ,style: 'color:#fff; border:none;' //自定风格
		  ,time: 5
		  ,end:function(index){
			 fun(param); 
		  }
		});
  }
  
  function alertNoButton(msg,func,param){//无按钮弹框 并在弹框结束后执行func
  	layer.open({
			  content: msg
			  ,style: 'color:#fff; border:none;' //自定风格
			  ,time: 3,
			  end:function(index){
			  	if(func){
					func(index,param);
			  	}
			  }
		});
  }
//打开某个层的弹框，两个按钮
  function divMsg(title,content,fun,param,width,height){
	  //如果传入宽高样式
	  var heights='auto';
	  var widths='auto';
	  if(height!=null){
		  heights=height;
	  }
	  if(width!=null){
		  widths=width;
	  }
// 	    alert("widths:"+widths+",heights:"+heights)
	  //打开弹框
	  layer.open({
	         type: 1,
	         title: [title,'font-size:14px;color:#fff'],
	         maxmin: false,
	         shadeClose: false,
	         area: [widths, heights],
	         content:content,
	         btn: ['确认', '取消'],
	         yes:function(index){
	        	 if(fun){
	        		 fun(param,index) 
	        	 }
			  },
			  no:function(){
				  layer.close();
			  }
	});
  }
  
//打开某个层的弹框，一个确定按钮
  function divMsgOne(title,content,fun,param,width,height){
	  //如果传入宽高样式
	  var heights='auto';
	  var widths='auto';
	   if(height!=null){
		  heights=height;
	  }
	  if(width!=null){
		  widths=width;
	  }
	  //打开弹框
	  layer.open({
	         type: 1,
	         title: [title,'font-size:14px;color:#fff'],
	         maxmin: false,
	         shadeClose: false,
	         area: [widths, heights],
	         content:content,
	         btn: ['确认'],
	         yes:function(index){
	        	 if(fun){
	        		 fun(param,index) 
	        	 }
			  }
	});
  }