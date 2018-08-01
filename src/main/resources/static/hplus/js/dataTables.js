//设置默认值
$.extend( $.fn.dataTable.defaults, {
    "searching": true,
    "ordering":  true,
    "paging": true,
	"info": true,
	"pagingType":   "full_numbers",//simple ,simple_numbers ,full ,full_numbers 
	"lengthMenu": [10, 25, 50, 100,200],
	"pageLength": 10,
	"dom": "<'row'<'col-sm-12'tr>>" +
			"<'row'<'col-sm-5'l><'col-sm-7'p>>",
	"language": {
		"lengthMenu": "每页 _MENU_ 条记录",
		"zeroRecords": "没有找到记录",
		"emptyTable": "暂无数据！",
		"info": "第 _PAGE_ / _PAGES_ 页  ",
		"infoEmpty": "无记录",
		"infoFiltered": "(从 _MAX_ 条记录过滤)",
		"processing":"加载中...",
		"paginate": {
			"previous": "上页",
			"next": "下页",
			"last": "末页",
			"first": "首页"
		},
		"search": "搜索：",
		 
	},
	//当处理大数据时，延迟渲染数据，有效提高Datatables处理能力
   "deferRender": true,
});