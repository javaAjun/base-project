////
var HttpUrlCommon ="http://localhost:8383"
//获取商品列表
var _goodsList="/goods/getGoodsList";
//修改商品
var _editGoods="/goods/operGoodsInfo?oper=edit";
//删除
var _delGoods="/goods/operGoodsInfo?oper=del";
//根据id获取商品/getGoodsById
var _getGoodsInfoById="/goods/getGoodsById";
//获取分类下拉框
var _getGoodsCate="/goods/getGoodsCategoryInfo";


//获取商品分类列表
var _getGoodsCatePageInfo="/goods/getGoodsCateInfo";
	
	///goods/getGoodsCateInfoByParentId
var _getGoodsCatePageById="/goods/getGoodsCateTopLevel";
//编辑分类
var _operEditGoodsCateInfor="/goods/operGoodsCateInfo?oper=edit"